package org.apache.ofbiz.ofbiztutorial.usermgr.services;


import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.*;
import org.apache.ofbiz.base.util.UtilDateTime;


import java.util.HashMap;
import java.util.Map;

public class UserMgrServices{
    public static final String MODULE = UserMgrServices.class.getName();

    public static Map<String, Object> createUserByJavaService(DispatchContext dctx, Map<String, ? extends Object> context) throws GenericServiceException {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        LocalDispatcher dispatcher= dctx.getDispatcher();
        Map<String, Object> serviceContext = new HashMap<>();

        try{
            serviceContext=dctx.makeValidContext("createPersonAndUserLogin", ModelService.IN_PARAM, context);
            result = dispatcher.runSync("createPersonAndUserLogin", serviceContext);
            if (ServiceUtil.isError(result)) {
                return result;
            }
            String partyId= result.get("partyId").toString();
            GenericValue newUserLogin = (GenericValue) result.get("newUserLogin");
            serviceContext.clear();
            result.clear();

            serviceContext = dctx.makeValidContext("addUserLoginToSecurityGroup", ModelService.IN_PARAM, context);
            serviceContext.put("groupId", "PARTYADMIN");
            serviceContext.put("fromDate", UtilDateTime.nowDate());
            result = dispatcher.runSync("addUserLoginToSecurityGroup", serviceContext);
            if(ServiceUtil.isError(result)){
                return result;
            }
            serviceContext.clear();
            result.clear();

            serviceContext=dctx.makeValidContext("createPostalAddressJava", ModelService.IN_PARAM, context);
            serviceContext.put("partyId",  partyId);
            result = dispatcher.runSync("createPostalAddressJava", serviceContext);
            if (ServiceUtil.isError(result)) {
                return result;
            }
            serviceContext.clear();
            result.clear();

            serviceContext = dctx.makeValidContext("createTelecomNumberJava", ModelService.IN_PARAM, context);
            serviceContext.put("partyId",  partyId);
            result= dispatcher.runSync("createTelecomNumberJava", serviceContext);
            if (ServiceUtil.isError(result)) {
                return result;
            }
            serviceContext.clear();
            result.clear();

            serviceContext = dctx.makeValidContext("createEmailAddressJava", ModelService.IN_PARAM, context);
            serviceContext.put("partyId",  partyId);
            result = dispatcher.runSync("createEmailAddressJava", serviceContext);
            if (ServiceUtil.isError(result)) {
                return result;
            }
            serviceContext.clear();
            result.clear();

            result.put("newUserLogin", newUserLogin);
            result.put("partyId", partyId);
        }catch (GenericServiceException e) {
            return ServiceUtil.returnError("Error creating User !!!! " + e.getMessage());
        }
        return result;
    }
}


