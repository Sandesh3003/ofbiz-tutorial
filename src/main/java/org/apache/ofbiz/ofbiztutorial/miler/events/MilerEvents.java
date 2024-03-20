package org.apache.ofbiz.ofbiztutorial.miler.events;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;

import java.math.BigDecimal;
import java.util.Map;


public class MilerEvents {

    public static final String MODULE = MilerEvents.class.getName();

    public static String addMilerProductEvent(HttpServletRequest request, HttpServletResponse response) {
        Delegator delegator = (Delegator) request.getAttribute("delegator");
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        GenericValue userLogin = (GenericValue) request.getSession().getAttribute("userLogin");

        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        BigDecimal productRating = new BigDecimal(request.getParameter("productRating"));
        String productTypeId = request.getParameter("productTypeId");
        BigDecimal productPrice = new BigDecimal(request.getParameter("productPrice"));

        // Validate productName
        if (UtilValidate.isEmpty(productName)) {
            String errMsg = "Product name is a required field and can't be empty.";
            request.setAttribute("_ERROR_MESSAGE_", errMsg);
            return "error";
        }

        // Validate description
        if (UtilValidate.isEmpty(description)) {
            String errMsg = "Description is a required field and can't be empty.";
            request.setAttribute("_ERROR_MESSAGE_", errMsg);
            return "error";
        }

        // Validate productRating
        if (productRating == null || productRating.compareTo(BigDecimal.ZERO) < 0 || productRating.compareTo(BigDecimal.TEN) > 0) {
            String errMsg = "Product rating must be between 0 and 10.";
            request.setAttribute("_ERROR_MESSAGE_", errMsg);
            return "error";
        }

        // Validate productTypeId
        if (UtilValidate.isEmpty(productTypeId)) {
            String errMsg = "Product type ID is a required field and can't be empty.";
            request.setAttribute("_ERROR_MESSAGE_", errMsg);
            return "error";
        }

        // Validate productPrice
        if (productPrice == null || productPrice.compareTo(BigDecimal.ZERO) <= 0) {
            String errMsg = "Product price must be greater than zero.";
            request.setAttribute("_ERROR_MESSAGE_", errMsg);
            return "error";
        }

        // Call the service
        try {
            Map<String, Object> serviceContext = UtilMisc.toMap(
                    "productName", productName,
                    "description", description,
                    "productRating", productRating,
                    "productTypeId", productTypeId,
                    "productPrice", productPrice,
                    "userLogin", userLogin
            );

            Debug.logInfo("Creating MilerProduct record in event using service addMilerProductByJava", MODULE);
            dispatcher.runSync("addMilerProductByJavaService", serviceContext);
        } catch (GenericServiceException e) {
            String errMsg = "Unable to create new record in MilerProduct entity: " + e.toString();
            request.setAttribute("_ERROR_MESSAGE_", errMsg);
            return "error";
        }

        request.setAttribute("_EVENT_MESSAGE_", "MilerProduct created successfully.");
        return "success";
    }
}