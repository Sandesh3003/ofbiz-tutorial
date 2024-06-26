package org.apache.ofbiz.ofbiztutorial.usermgr.events;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilHttp;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ModelService;
import org.apache.ofbiz.service.ServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserEvents {

    public static final String MODULE = UserEvents.class.getName();

    public static String createUserEvent(HttpServletRequest request, HttpServletResponse response) {
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        // Extract data from the form
        Map<String, Object> context = UtilHttp.getCombinedMap(request);
        List<String> errors = new ArrayList<>();
        // Perform Validations
        if (UtilValidate.isEmpty(context.get("userLoginId"))) {
            errors.add("User Login Id cannot be empty!!");
        }
        if (UtilValidate.isEmpty(context.get("currentPassword"))) {
            errors.add("Password cannot be left empty!!");
        }
        if (!context.get("currentPassword").equals(context.get("currentPasswordVerify"))) {
            errors.add("Failed to verify password: Confirm password doesn't match the password!!");
        }
        if (UtilValidate.isEmpty(context.get("firstName"))) {
            errors.add("First Name cannot be empty!!");
        }
        if(UtilValidate.isEmpty(context.get("gender"))){
            errors.add("Gender cannot be left empty!!");
        }
        if (UtilValidate.isEmpty(context.get("lastName"))) {
            errors.add("Last Name cannot be empty!!");
        }
        if (UtilValidate.isEmpty(context.get("address1"))) {
            errors.add("Address Line 1 cannot be empty!!");
        }
        if (UtilValidate.isEmpty(context.get("city"))) {
            errors.add("City cannot be empty!!");
        }
        if (UtilValidate.isEmpty(context.get("postalCode"))) {
            errors.add("Postal code cannot be empty!!");
        }
        if (UtilValidate.isEmpty(context.get("countryGeoId"))) {
            errors.add("Country is required field!!");
        }
        if (UtilValidate.isEmpty(context.get("countryCode"))) {
            errors.add("Country Code of the phone number is required field!!");
        }
        if (UtilValidate.isEmpty(context.get("contactNumber"))) {
            errors.add("Invalid contact number!!!");
        }
        if (!UtilValidate.isEmail((String) context.get("emailAddress"))) {
            errors.add("Enter valid email address");
        }

        try {
            DispatchContext dispatchContext = dispatcher.getDispatchContext();
            Map<String, Object> serviceParams = dispatchContext.makeValidContext("createUserByJavaService", ModelService.IN_PARAM, context);
            Map<String, Object> serviceResult = dispatcher.runSync("createUserByJavaService", serviceParams);
            if (ServiceUtil.isError(serviceResult)) {
                return ServiceUtil.getErrorMessage(serviceResult).toString();
            }
        } catch (Exception e) {
            Debug.logError("Error creating user: " + e.getMessage(), MODULE);
            return "error";
        }
        return "success";
    }
}
