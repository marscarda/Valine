package valine.feedback.web;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.sample.ExcResponseSubmit;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import mars.jsonsimple.JsonParseException;
import mars.jsonsimple.JsonParser;
import methionine.AppException;
import valine.ApiAlpha;
import valine.FlowAlpha;
import tryptophan.sample.ResponseValue;
import valine.jsontreatment.JValuesDecoder;
//***************************************************************************
@WebServlet(name = "ApiSubmitResponse", urlPatterns = {ApiSubmitResponse.URL}, loadOnStartup=1)
public class ApiSubmitResponse extends ApiAlpha {
    public static final String URL = "/survey/submitresponse";
    public static final String RESPONSECALLID = "feedbackcallid";
    public static final String REPVALUES = "repvalues";
    public static final String COUNT = "count";
    public static final String ITEMS = "items";    
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //==========================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //==========================================================
        long callid = 0;
        String repvalues = req.getParameter(REPVALUES);
        
        
        System.out.println(repvalues);
        
        try { callid = Long.parseLong(req.getParameter(RESPONSECALLID)); } catch (Exception e) {}
        try {
            //------------------------------------------------------
            //We first recover the values.
            JsonObject jrepvalues = JsonParser.parseAndCreateJsonObject(repvalues);
            ResponseValue[] values = JValuesDecoder.decodeValues(jrepvalues);
            //------------------------------------------------------
            ExcResponseSubmit submit = new ExcResponseSubmit();
            submit.setAuriga(flowalpha.getAurigaObject());
            submit.setId(callid);
            submit.setValues(values);
            
            
            //submit.doFeedbackCall();
            
            
            
            //------------------------------------------------------
            JsonObject jsonresp = new JsonObject();
            JsonPair pairResp;
            pairResp = new JsonPair(RESULT, RESULTOK);
            jsonresp.addPair(pairResp);
            pairResp = new JsonPair(RESULTDESCRIPTION, "Response received");
            jsonresp.addPair(pairResp);
            //----------------------------------------
            this.sendResponse(resp, jsonresp);            
            //----------------------------------------
        }
        
        
        //catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }
        
        
        
        catch (JsonParseException e) { this.sendErrorResponse(resp, "Invalid data", AppException.INVALIDDATASUBMITED); }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Failed to submit response");
            System.out.println(e.getMessage());
        }        
        //==========================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //==========================================================
   }
   //************************************************************************
    
    
    
}
//***************************************************************************
