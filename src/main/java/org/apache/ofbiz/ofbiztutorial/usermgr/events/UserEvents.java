package org.apache.ofbiz.ofbiztutorial.usermgr.events;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ServiceUtil;

import java.util.HashMap;
import java.util.Map;

public class UserEvents {

    public static final String MODULE = UserEvents.class.getName();

    public static String createUserEvent(HttpServletRequest request, HttpServletResponse response) {
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        // Extract data from the form
        HttpSession session = request.getSession();
        GenericValue userLogin = (GenericValue) session.getAttribute("userLogin");
        System.out.println("userLogin "+userLogin+"========================");
        String userLoginId = request.getParameter("userLoginId");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String middleName = request.getParameter("middleName");
//        String gender = request.getParameter("gender");
<<<<<<< HEAD
//        String birthDate = request.getParameter("birthDate");
=======
        String birthDate = request.getParameter("birthDate");
>>>>>>> b5bdf4e25b3ebee19ea8ad8ad27302fa32a4f9f6
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postalCode");
        String countryGeoId = request.getParameter("countryGeoId");
        String countryCode = request.getParameter("countryCode");
        String areaCode = request.getParameter("areaCode");
        String contactNumber = request.getParameter("contactNumber");
        String emailAddress = request.getParameter("emailAddress");
        // Perform Validations
        if(UtilValidate.isEmpty(userLoginId)){
            return ServiceUtil.returnError("User Login Id cannot be empty!!").toString();
        }
        if(UtilValidate.isEmpty(password)){
            return ServiceUtil.returnError("Password cannot be left empty!!").toString();
        }
        if(UtilValidate.isEmpty(confirmPassword) && !password.equals(confirmPassword)){
            return ServiceUtil.returnError("Failed to verify password: Confirm password doesn't match the password!!").toString();
        }
        if(UtilValidate.isEmpty(firstName)){
            return ServiceUtil.returnError("First Name cannot be empty!!").toString();
        }
        if(UtilValidate.isEmpty(lastName)){
            return ServiceUtil.returnError("Last Name cannot be empty!!").toString();
        }
//        if(UtilValidate.isEmpty(gender)){
//            return ServiceUtil.returnError("Gender cannot be empty!!").toString();
//        }
<<<<<<< HEAD
//        if (!UtilValidate.isDate(birthDate)) {
//            return ServiceUtil.returnError("Incorrect Date Format in Birth Date!!!" + birthDate).toString();
//        }
=======
        if (!UtilValidate.isDate(birthDate)) {
            return ServiceUtil.returnError("Incorrect Date Format in Birth Date!!!" + birthDate).toString();
        }
>>>>>>> b5bdf4e25b3ebee19ea8ad8ad27302fa32a4f9f6
        if(UtilValidate.isEmpty(address1)){
            return ServiceUtil.returnError("Address Line 1 cannot be empty!!").toString();
        }
        if(UtilValidate.isEmpty(city)){
            return ServiceUtil.returnError("City cannot be empty!!").toString();
        }
        if(UtilValidate.isEmpty(postalCode)){
            return ServiceUtil.returnError("Postal code cannot be empty!!").toString();
        }
        if(UtilValidate.isEmpty(countryGeoId)){
            return ServiceUtil.returnError("Country is required field!!").toString();
        }
        if(UtilValidate.isEmpty(countryCode)){
            return ServiceUtil.returnError("Country Code of the phone number is required field!!").toString();
        }
        if (UtilValidate.isEmpty(contactNumber)){
            return ServiceUtil.returnError("Invalid contact number!!!").toString();
        }
        if(!UtilValidate.isEmail(emailAddress)){
            return ServiceUtil.returnError("Enter valid email address").toString();
        }

        try {
            Map<String, Object> serviceParams = new HashMap<>();
            serviceParams.put("userLogin", userLogin);
            serviceParams.put("userLoginId", userLoginId);
            serviceParams.put("password", password);
            serviceParams.put("confirmPassword", confirmPassword);
            serviceParams.put("firstName", firstName);
            serviceParams.put("lastName", lastName);
            serviceParams.put("middleName", middleName);
<<<<<<< HEAD
//            serviceParams.put("birthDate", birthDate);
=======
            serviceParams.put("birthDate", birthDate);
>>>>>>> b5bdf4e25b3ebee19ea8ad8ad27302fa32a4f9f6
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
                return ServiceUtil.getErrorMessage(serviceResult);
            }
        } catch (Exception e) {
            Debug.logError("Error creating user: " + e.getMessage(), MODULE);
            return "error";
        }
        return "success";
    }
}

