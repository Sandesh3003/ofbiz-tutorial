package org.apache.ofbiz.ofbiztutorial.usermgr.services;


import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.ServiceUtil;
<<<<<<< HEAD
import org.apache.ofbiz.base.util.UtilDateTime;

=======
>>>>>>> b5bdf4e25b3ebee19ea8ad8ad27302fa32a4f9f6

import java.util.Map;

public class UserMgrServices{
    public static final String MODULE = UserMgrServices.class.getName();

    public static Map<String, Object> createUserByJavaService(DispatchContext dctx, Map<String, ? extends Object> context){
        Map<String, Object> result = ServiceUtil.returnSuccess();
        String userLoginId = (String) context.get("userLoginId");
        String password = (String) context.get("password");
        String confirmPassword = (String) context.get("confirmPassword");
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

        System.out.println("============="+userLogin+"===============");
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            return ServiceUtil.returnError("Passwords do not match.");
        }

        // Invoke createPersonAndUserLogin service to create user and associated person
        Map<String, Object> createPersonParams = Map.of(
                "userLoginId", userLoginId,
                "currentPassword", password,
                "currentPasswordVerify", confirmPassword,
                "firstName", firstName,
                "lastName", lastName,
                "middleName", middleName
//                "birthDate", birthDate
        );
        Map<String, Object> createPersonResult = null;
        try {
            createPersonResult = dctx.getDispatcher().runSync("createPersonAndUserLogin", createPersonParams);
        } catch (GenericServiceException e) {
            return ServiceUtil.returnError("Error creating person and user login: " + e.getMessage());
        }
        if (ServiceUtil.isError(createPersonResult)) {
            return createPersonResult;
        }
        GenericValue newUserLogin = (GenericValue) createPersonResult.get("newUserLogin");
<<<<<<< HEAD
        Map<String, Object> addUserLoginSecurityGroup = Map.of(
                "userLoginId", userLoginId,
                "groupId", "PARTYADMIN",
                "userLogin", userLogin,
                "fromDate", UtilDateTime.nowDate()
        );
        Map<String, Object> addSecurityGroupResult=null;
        try{
            addSecurityGroupResult = dctx.getDispatcher().runSync("addUserLoginToSecurityGroup", addUserLoginSecurityGroup);
        } catch (GenericServiceException e){
            return ServiceUtil.returnError("Error assigning security group: "+e.getMessage());
        }
        if(ServiceUtil.isError(addSecurityGroupResult)){
            return addSecurityGroupResult;
        }
=======
>>>>>>> b5bdf4e25b3ebee19ea8ad8ad27302fa32a4f9f6
        // Invoke createPostalAddress service
        Map<String, Object> createPostalAddressParams = Map.of(
                "address1", address1,
                "address2", address2,
                "city", city,
                "postalCode", postalCode,
                "countryGeoId", countryGeoId,
<<<<<<< HEAD
                "userLogin", newUserLogin
        );
        Map<String, Object> createPostalAddressResult = null;
        try {
            createPostalAddressResult = dctx.getDispatcher().runSync("createPostalAddressJava", createPostalAddressParams);
=======
                "userLogin", userLogin
        );
        Map<String, Object> createPostalAddressResult = null;
        try {
            createPostalAddressResult = dctx.getDispatcher().runSync("createPostalAddress", createPostalAddressParams);
>>>>>>> b5bdf4e25b3ebee19ea8ad8ad27302fa32a4f9f6
        } catch (GenericServiceException e) {
            return ServiceUtil.returnError("Error creating postal address: " + e.getMessage());
        }
        if (ServiceUtil.isError(createPostalAddressResult)) {
            return createPostalAddressResult;
        }

        // Invoke createTelecomNumber service
        Map<String, Object> createTelecomNumberParams = Map.of(
                "countryCode", countryCode,
                "areaCode", areaCode,
                "contactNumber", contactNumber,
<<<<<<< HEAD
                "userLogin", newUserLogin
        );
        Map<String, Object> createTelecomNumberResult = null;
        try {
            createTelecomNumberResult = dctx.getDispatcher().runSync("createTelecomNumberJava", createTelecomNumberParams);
=======
                "userLogin", userLogin
        );
        Map<String, Object> createTelecomNumberResult = null;
        try {
            createTelecomNumberResult = dctx.getDispatcher().runSync("createTelecomNumber", createTelecomNumberParams);
>>>>>>> b5bdf4e25b3ebee19ea8ad8ad27302fa32a4f9f6
        } catch (GenericServiceException e) {
            return ServiceUtil.returnError("Error creating telecom number: " + e.getMessage());
        }
        if (ServiceUtil.isError(createTelecomNumberResult)) {
            return createTelecomNumberResult;
        }

        // Invoke createEmailAddress service
        Map<String, Object> createEmailAddressParams = UtilMisc.toMap("emailAddress", emailAddress,
<<<<<<< HEAD
                "userLogin", newUserLogin);
        Map<String, Object> createEmailAddressResult = null;
        try {
            createEmailAddressResult = dctx.getDispatcher().runSync("createEmailAddressJava", createEmailAddressParams);
=======
                "userLogin", userLogin);
        Map<String, Object> createEmailAddressResult = null;
        try {
            createEmailAddressResult = dctx.getDispatcher().runSync("createEmailAddress", createEmailAddressParams);
>>>>>>> b5bdf4e25b3ebee19ea8ad8ad27302fa32a4f9f6
        } catch (GenericServiceException e) {
            return ServiceUtil.returnError("Error creating email address: " + e.getMessage());
        }
        if (ServiceUtil.isError(createEmailAddressResult)) {
            return createEmailAddressResult;
        }

        result.put("newUserLogin", newUserLogin);
        String partyId=(String) createPersonResult.get("partyId");
        result.put("partyId", partyId);
        return result;
    }
}


