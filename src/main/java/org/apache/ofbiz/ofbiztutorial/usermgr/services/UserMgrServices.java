package org.apache.ofbiz.ofbiztutorial.usermgr.services;


import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.ModelService;
import org.apache.ofbiz.service.ServiceUtil;
import org.apache.ofbiz.base.util.UtilDateTime;


import java.util.Map;

public class UserMgrServices{
    public static final String MODULE = UserMgrServices.class.getName();

    public static Map<String, Object> createUserByJavaService(DispatchContext dctx, Map<String, ? extends Object> context) throws GenericServiceException {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        String userLoginId = (String) context.get("userLoginId");
        String currentPassword = (String) context.get("currentPassword");
        String currentPasswordVerify = (String) context.get("currentPasswordVerify");
        String firstName = (String) context.get("firstName");
        String lastName = (String) context.get("lastName");
        String middleName = (String) context.get("middleName");
//        String birthDate = (String) context.get("birthDate");
        String address1 = (String) context.get("address1");
        String address2 = (String) context.get("address2");
        String city = (String) context.get("city");
        String postalCode = (String) context.get("postalCode");
        String countryGeoId = (String) context.get("countryGeoId");
        String countryCode = (String) context.get("countryCode");
        String areaCode = (String) context.get("areaCode");
        String contactNumber = (String) context.get("contactNumber");
        String emailAddress = (String) context.get("emailAddress");
        GenericValue userLogin = (GenericValue) context.get("userLogin");

        // Check if passwords match
        if (!currentPassword.equals(currentPasswordVerify)) {
            return ServiceUtil.returnError("Passwords do not match.");
        }

        // Invoke createPersonAndUserLogin service to create user and associated person
//        Map<String, Object> createPersonParams = UtilMisc.toMap(
//                "userLoginId", userLoginId,
//                "currentPassword", password,
//                "currentPasswordVerify", confirmPassworda,
//                "firstName", firstName,
//                "lastName", lastName,
//                "middleName", middleName
////                "birthDate", birthDate
//        );
        Map<String, Object> createPersonResult=null;
        try {
            Map<String, Object> createPersonParams=dctx.makeValidContext("createPersonAndUserLogin", ModelService.IN_PARAM, context);
            createPersonResult = dctx.getDispatcher().runSync("createPersonAndUserLogin", createPersonParams);
            if (ServiceUtil.isError(createPersonResult)) {
                return createPersonResult;
            }
        } catch (GenericServiceException e) {
            return ServiceUtil.returnError("Error creating person and user login: " + e.getMessage());
        }

        GenericValue newUserLogin = (GenericValue) createPersonResult.get("newUserLogin");
        Map<String, Object> addUserLoginSecurityGroup = UtilMisc.toMap(
                "userLoginId", userLoginId,
                "groupId", "PARTYADMIN",
                "userLogin", userLogin,
                "fromDate", UtilDateTime.nowDate()
        );
        try{
            Map<String, Object> addSecurityGroupResult = dctx.getDispatcher().runSync("addUserLoginToSecurityGroup", addUserLoginSecurityGroup);
            if(ServiceUtil.isError(addSecurityGroupResult)){
                return addSecurityGroupResult;
            }
        } catch (GenericServiceException e){
            return ServiceUtil.returnError("Error assigning security group: "+e.getMessage());
        }

        // Invoke createPostalAddress service
//        Map<String, Object> createPostalAddressParams = UtilMisc.toMap(
//                "address1", address1,
//                "address2", address2,
//                "city", city,
//                "postalCode", postalCode,
//                "countryGeoId", countryGeoId,
//                "userLogin", newUserLogin
//        );
        try {
            Map<String, Object>  createPostalAddressParams=dctx.makeValidContext("createPostalAddressJava", ModelService.IN_PARAM, context);
            createPostalAddressParams.replace("userLogin", newUserLogin);
            Map<String, Object> createPostalAddressResult = dctx.getDispatcher().runSync("createPostalAddressJava", createPostalAddressParams);
            if (ServiceUtil.isError(createPostalAddressResult)) {
                return createPostalAddressResult;
            }
        } catch (GenericServiceException e) {
            return ServiceUtil.returnError("Error creating postal address: " + e.getMessage());
        }

        // Invoke createTelecomNumber service
//        Map<String, Object> createTelecomNumberParams = UtilMisc.toMap(
//                "countryCode", countryCode,
//                "areaCode", areaCode,
//                "contactNumber", contactNumber,
//                "userLogin", newUserLogin
//        );
        try {
            Map<String, Object> createTelecomNumberParams = dctx.makeValidContext("createTelecomNumberJava", ModelService.IN_PARAM, context);
            createTelecomNumberParams.replace("userLogin", newUserLogin);
            Map<String, Object> createTelecomNumberResult= dctx.getDispatcher().runSync("createTelecomNumberJava", createTelecomNumberParams);
            if (ServiceUtil.isError(createTelecomNumberResult)) {
                return createTelecomNumberResult;
            }
        } catch (GenericServiceException e) {
            return ServiceUtil.returnError("Error creating telecom number: " + e.getMessage());
        }
        // Invoke createEmailAddress service

        try {
            Map<String, Object> createEmailAddressParams = dctx.makeValidContext("createEmailAddressJava", ModelService.IN_PARAM, context);
            createEmailAddressParams.replace("userLogin", newUserLogin);
            Map<String, Object> createEmailAddressResult = dctx.getDispatcher().runSync("createEmailAddressJava", createEmailAddressParams);
            if (ServiceUtil.isError(createEmailAddressResult)) {
                return createEmailAddressResult;
            }
        } catch (GenericServiceException e) {
            return ServiceUtil.returnError("Error creating email address: " + e.getMessage());
        }

        result.put("newUserLogin", newUserLogin);
        String partyId=(String) createPersonResult.get("partyId");
        result.put("partyId", partyId);
        return result;
    }
}


