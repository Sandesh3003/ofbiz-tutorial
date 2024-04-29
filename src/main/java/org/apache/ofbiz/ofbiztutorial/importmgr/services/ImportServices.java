package org.apache.ofbiz.ofbiztutorial.importmgr.services;

import net.sf.cglib.core.Local;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.transaction.GenericTransactionException;
import org.apache.ofbiz.entity.transaction.TransactionUtil;
import org.apache.ofbiz.service.*;
import org.apache.ofbiz.base.util.UtilMisc;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportServices{
    public static final String MODULE=ImportServices.class.getName();

    public static Map<String, Object> importPIESSetupFile(DispatchContext dctx, Map<String, ? extends Object> context){
        Map<String, Object> result = ServiceUtil.returnSuccess();
        LocalDispatcher dispatcher = dctx.getDispatcher();
        Map<String, Object> serviceContext = new HashMap<>();
        try {
            serviceContext=dctx.makeValidContext("parsePIESSetupFile", ModelService.IN_PARAM, context);
            result=dispatcher.runSync("parsePIESSetupFile", serviceContext);
            if(ServiceUtil.isError(result)){
                return result;
            }

        }catch (GenericServiceException e){
            return ServiceUtil.returnError("Error Parsing the Setup File !!! "+e.getMessage());
        }
        return result;
    }
    public static Map<String, Object> parsePIESSetupFile(DispatchContext dctx, Map<String,? extends Object> context){
        String filePath=(String) context.get("filePath");
        Map<String, Object> resultMap = new HashMap<>();
        LocalDispatcher dispatcher = dctx.getDispatcher();
        List<Map<String, String>> failedItems = new ArrayList<>();
        int successItemCount=0;
        int failedItemCount=0;
        try(InputStream inputStream =new FileInputStream(filePath)){
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

            String currentItem ="";
            Map<String, String> itemDetails = new HashMap<>();
            while(reader.hasNext()){
                int event = reader.next();
                switch (event){
                    case XMLStreamConstants.START_ELEMENT:
                        currentItem = reader.getLocalName();
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();
                        if(!text.isEmpty()){
                            itemDetails.put(currentItem, text);
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        if(reader.getLocalName().equals("Item")){
                            Map<String, Object> serviceContext = dctx.makeValidContext("mapPIESSetupFile", ModelService.IN_PARAM, itemDetails);
                            resultMap=dispatcher.runSync("mapPIESSetupFile", serviceContext);
                            if(ServiceUtil.isError(resultMap)){
                                Map<String, String> failedItem = UtilMisc.toMap(
                                        "PartNumber", itemDetails.get("PartNumber"),
                                        "BrandAAIAID", itemDetails.get("BrandAAIAID"),
                                        "BrandLabel", itemDetails.get("BrandLabel")
                                );
                                failedItems.add(failedItem);
                                failedItemCount++;
                            }
                            else {
                                successItemCount++;
                            }
                            itemDetails.clear();
                        }
                }
            }
            resultMap.put("successItemCount", successItemCount);
            resultMap.put("failedImports", failedItems);
        }catch (IOException | XMLStreamException e){
            return ServiceUtil.returnError("File not found!!  "+ e.getMessage());
        } catch (GenericServiceException e) {
            throw new RuntimeException(e);
        }
        return resultMap;
    }

    public static Map<String, Object> mapPIESSetupFile(DispatchContext dctx, Map<String, ? extends Object> context) throws GenericEntityException {
        Map<String, Object> resultMap = new HashMap<>();
        LocalDispatcher dispatcher=dctx.getDispatcher();
        Delegator delegator = dctx.getDelegator();

        // Product Entity Map(Item --> Product)

        try{
            TransactionUtil.begin();

            // Check if brand party group exists, create if it does not exist
            String brandAAIAId = (String) context.get("BrandAAIAID");
            String brandPartyGroupId = checkPartyGroup(delegator, brandAAIAId);

            if(brandPartyGroupId==null){
                resultMap=dispatcher.runSync("createPartyGroup", UtilMisc.toMap("groupName", context.get("BrandLabel"),
                        "externalId", context.get("BrandAAIAID")));
                brandPartyGroupId = resultMap.get("partyId").toString();
                resultMap.clear();
            }



        } catch (GenericTransactionException e) {
            throw new RuntimeException(e);
        } catch (GenericServiceException e) {
            throw new RuntimeException(e);
        }

        return (Map<String, Object>) resultMap.get("productId");
    }
    private static String checkPartyGroup(Delegator delegator, String brandAAIAId) throws GenericEntityException {
        GenericValue partyGroup = delegator.findOne("PartyGroup", UtilMisc.toMap("externalId", brandAAIAId), false);
        return partyGroup.get("partyId").toString();
    }
}