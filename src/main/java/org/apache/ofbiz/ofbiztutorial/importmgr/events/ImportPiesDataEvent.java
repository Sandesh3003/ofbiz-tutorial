package org.apache.ofbiz.ofbiztutorial.importmgr.events;


import org.apache.ofbiz.base.util.UtilHttp;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.base.util.UtilXml;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
public class ImportPiesDataEvent{

    public static final String MODULE= ImportPiesDataEvent.class.getName();

    public static String importPiesDataEvent(HttpServletRequest request, HttpServletResponse response){
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");

        //Extract data from the request body
        Map<String, Object> context = UtilHttp.getCombinedMap(request);
        String filePath = (String) context.get("filePath");
        //Perform validations
        if(UtilValidate.isNotEmpty(filePath)) {
            return ServiceUtil.returnError("File Path cannot be left empty!!!!").toString();
        }
        File file = new File(filePath);
        context.put("file", file);
        try{
            Map<String, Object> serviceResult = dispatcher.runSync("importPIESSetupFile", context);
            if(ServiceUtil.isError(serviceResult)){
                return ServiceUtil.returnError("Import of Setup File Failed").toString();
            }
        }
        catch (GenericServiceException e){
            return ServiceUtil.returnFailure().toString();
        }
        return ServiceUtil.returnSuccess("File Imported Successfully").toString();
    }
}