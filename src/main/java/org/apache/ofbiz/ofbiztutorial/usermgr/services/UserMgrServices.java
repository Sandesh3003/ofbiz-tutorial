package org.apache.ofbiz.ofbiztutorial.usermgr.services;


import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.ModelService;
import org.apache.ofbiz.service.ServiceUtil;
import org.apache.ofbiz.base.util.UtilDateTime;


import java.util.HashMap;
import java.util.Map;

public class UserMgrServices{
    public static final String MODULE = UserMgrServices.class.getName();

    public static Map<String, Object> createUserByJavaService(DispatchContext dctx, Map<String, ? extends Object> context) throws GenericServiceException {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Map<String, Object> serviceContext = null;
        try{
            serviceContext=dctx.makeValidContext("createPersonAndUserLogin", ModelService.IN_PARAM, context);
            Map<String, Object> createPersonResult = dctx.getDispatcher().runSync("createPersonAndUserLogin", serviceContext);
            if (ServiceUtil.isError(createPersonResult)) {
                return createPersonResult;
            }
            GenericValue newUserLogin = (GenericValue) createPersonResult.get("newUserLogin");
            serviceContext.clear();

            serviceContext = dctx.makeValidContext("addUserLoginToSecurityGroup", ModelService.IN_PARAM, context);
            serviceContext.put("groupId", "PARTYADMIN");
            Map<String, Object> addSecurityGroupResult = dctx.getDispatcher().runSync("addUserLoginToSecurityGroup", serviceContext);
            if(ServiceUtil.isError(addSecurityGroupResult)){
                return addSecurityGroupResult;
            }
            serviceContext.clear();

            serviceContext=dctx.makeValidContext("createPostalAddressJava", ModelService.IN_PARAM, context);
            serviceContext.replace("userLogin", newUserLogin);
            Map<String, Object> createPostalAddressResult = dctx.getDispatcher().runSync("createPostalAddressJava", serviceContext);
            if (ServiceUtil.isError(createPostalAddressResult)) {
                return createPostalAddressResult;
            }
            serviceContext.clear();

            serviceContext = dctx.makeValidContext("createTelecomNumberJava", ModelService.IN_PARAM, context);
            serviceContext.replace("userLogin", newUserLogin);
            Map<String, Object> createTelecomNumberResult= dctx.getDispatcher().runSync("createTelecomNumberJava", serviceContext);
            if (ServiceUtil.isError(createTelecomNumberResult)) {
                return createTelecomNumberResult;
            }
            serviceContext.clear();

            serviceContext = dctx.makeValidContext("createEmailAddressJava", ModelService.IN_PARAM, context);
            serviceContext.replace("userLogin", newUserLogin);
            Map<String, Object> createEmailAddressResult = dctx.getDispatcher().runSync("createEmailAddressJava", serviceContext);
            if (ServiceUtil.isError(createEmailAddressResult)) {
                return createEmailAddressResult;
            }
            result.put("newUserLogin", newUserLogin);
            result.put("partyId", createPersonResult.get("partyId").toString());
        }catch (GenericServiceException e) {
            return ServiceUtil.returnError("Error creating User !!!! " + e.getMessage());
        }
        return result;
    }
}


