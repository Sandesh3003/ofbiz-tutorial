package org.apache.ofbiz.ofbiztutorial.usermgr.events;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserEvents {

    public static final String MODULE = UserEvents.class.getName();

    public static String createUserEvent(HttpServletRequest request, HttpServletResponse response) {
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        // Extract data from the form
        HttpSession session = request.getSession();
        GenericValue userLogin = (GenericValue) session.getAttribute("userLogin");
        String userLoginId = request.getParameter("userLoginId");
        String currentPassword = request.getParameter("password");
        String currentPasswordVerify = request.getParameter("confirmPassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String middleName = request.getParameter("middleName");
//        String gender = request.getParameter("gender");
//        String birthDate = request.getParameter("birthDate");
        String birthDate = request.getParameter("birthDate");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postalCode");
        String countryGeoId = request.getParameter("countryGeoId");
        String countryCode = request.getParameter("countryCode");
        String areaCode = request.getParameter("areaCode");
        String contactNumber = request.getParameter("contactNumber");
        String emailAddress = request.getParameter("emailAddress");

        Map<String, Object> errorMap=new HashMap<>();
        List<String> errors=new ArrayList<>();
        // Perform Validations
        if(UtilValidate.isEmpty(userLoginId)){
            errors.add("User Login Id cannot be empty!!");
        }
        if(UtilValidate.isEmpty(currentPassword)){
            errors.add("Password cannot be left empty!!");
        }
        if(UtilValidate.isEmpty(currentPasswordVerify) && !currentPassword.equals(currentPasswordVerify)){
            errors.add("Failed to verify password: Confirm password doesn't match the password!!");
        }
        if(UtilValidate.isEmpty(firstName)){
            errors.add("First Name cannot be empty!!");
        }
        if(UtilValidate.isEmpty(lastName)){
            errors.add("Last Name cannot be empty!!");
        }
//        if(UtilValidate.isEmpty(gender)){
//            return ServiceUtil.returnError("Gender cannot be empty!!").toString();
//        }
//        if (!UtilValidate.isDate(birthDate)) {
//            return ServiceUtil.returnError("Incorrect Date Format in Birth Date!!!" + birthDate).toString();
//        }
        if (!UtilValidate.isDate(birthDate)) {
            errors.add("Incorrect Date Format in Birth Date!!!" + birthDate);
        }
        if(UtilValidate.isEmpty(address1)){
            errors.add("Address Line 1 cannot be empty!!");
        }
        if(UtilValidate.isEmpty(city)){
            errors.add("City cannot be empty!!");
        }
        if(UtilValidate.isEmpty(postalCode)){
            errors.add("Postal code cannot be empty!!");
        }
        if(UtilValidate.isEmpty(countryGeoId)){
            errors.add("Country is required field!!");
        }
        if(UtilValidate.isEmpty(countryCode)){
            errors.add("Country Code of the phone number is required field!!");
        }
        if (UtilValidate.isEmpty(contactNumber)){
            errors.add("Invalid contact number!!!");
        }
        if(!UtilValidate.isEmail(emailAddress)){
            errors.add("Enter valid email address");
        }

        if(!errors.isEmpty()){
            return ServiceUtil.returnError(errors).toString();
        }
        try {
            Map<String, Object> serviceParams = new HashMap<>();
            serviceParams.put("userLogin", userLogin);
            serviceParams.put("userLoginId", userLoginId);
            serviceParams.put("currentPassword", currentPassword);
            serviceParams.put("currentPasswordVerify", currentPasswordVerify);
            serviceParams.put("firstName", firstName);
            serviceParams.put("lastName", lastName);
            serviceParams.put("middleName", middleName);
//            serviceParams.put("birthDate", birthDate);
//            serviceParams.put("birthDate", birthDate);
            serviceParams.put("address1", address1);
            serviceParams.put("address2", address2);
            serviceParams.put("city", city);
            serviceParams.put("postalCode", postalCode);
            serviceParams.put("countryGeoId", countryGeoId);
            serviceParams.put("countryCode", countryCode);
            serviceParams.put("areaCode", areaCode);
            serviceParams.put("contactNumber", contactNumber);
            serviceParams.put("emailAddress", emailAddress);

            Map<String, Object> serviceResult = dispatcher.runSync("createUserByJavaService", serviceParams);
            if(ServiceUtil.isError(serviceResult)){
                return ServiceUtil.getErrorMessage(serviceResult).toString();
            }
        } catch (Exception e) {
            Debug.logError("Error creating user: " + e.getMessage(), MODULE);
            return "error";
        }
        return "success";
    }
}

