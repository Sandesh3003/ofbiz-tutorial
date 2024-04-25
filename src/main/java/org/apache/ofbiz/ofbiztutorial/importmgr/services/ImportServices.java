package org.apache.ofbiz.ofbiztutorial.importmgr.services;

import net.sf.cglib.core.Local;
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

//    public static Map<String, Object> mapPIESSetupFile(DispatchContext dctx, Map<String, ? extends Object> context){
//
//    }
}