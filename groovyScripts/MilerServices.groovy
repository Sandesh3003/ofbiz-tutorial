import org.apache.ofbiz.entity.GenericEntityException

def addMilerProductByGroovy(){
    result=[:];
    try {
        milerProduct = delegator.makeValue("MilerProduct");
        milerProduct.setNextSeqId();
        milerProduct.setNonPKFields(context);
        milerProduct=delegator.create(milerProduct);
        result.productId = milerProduct.productId;
        logInfo("<=======This is my first groovy service implementation, MilerProduct record with productId" + milerProduct.getString("productId")+" created successfully!!");
    }
    catch (GenericEntityException e){
        logError(e.getMessage());
        return error("Error in creating record in MilerProduct======>");
    }
    return result;
}
