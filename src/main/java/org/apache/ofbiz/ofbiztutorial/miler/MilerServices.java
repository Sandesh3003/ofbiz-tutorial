package org.apache.ofbiz.ofbiztutorial.miler;
import java.util.Map;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class MilerServices{
    public static final String MODULE=MilerServices.class.getName();

    public static Map<String, Object> addMilerProductByJava(DispatchContext dctx, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        try {
            GenericValue milerProduct = delegator.makeValue("MilerProduct");
            // Auto generating next sequence of productId primary key
            milerProduct.setNextSeqId();
            // Setting up all non primary key field values from context map
            milerProduct.setNonPKFields(context);
            // Creating record in database for MilerProduct entity for prepared value
            milerProduct = delegator.create(milerProduct);
            result.put("productId", milerProduct.getString("productId"));
            Debug.log("====This is my first Java Service implementation in Apache OFBiz. OfbizDemo record created successfully with productId:" + milerProduct.getString("productId"));
        } catch (GenericEntityException e) {
            Debug.logError(e, MODULE);
            return ServiceUtil.returnError("Error in creating record in MilerProduct entity ........" + MODULE);
        }
        return result;
    }
}
