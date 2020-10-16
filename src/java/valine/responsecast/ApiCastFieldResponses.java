package valine.responsecast;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.Session;
import lycine.epsilon.FieldCollector;
import lycine.epsilon.FieldCast;
import valine.ApiAlpha;
import valine.FlowAlpha;
//***************************************************************************
@WebServlet(name = "ApiCastFieldResponses", urlPatterns = {ApiCastFieldResponses.URL}, loadOnStartup=1)
public class ApiCastFieldResponses extends ApiAlpha {
    public static final String URL = "/response/castfieldresponse";
    public static final String SAMPLEID = "sampleid";
    public static final String RESPONSESTABLE = "responsestable";
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //===================================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //===================================================================
        Session session = flowalpha.getSession();
        boolean allowed = false;
        if (session.isValid()) allowed = true;
        if (!allowed) {
            this.sendErrorResponse(resp, "Unauthorized", UNAUTHORIZED);
            fleeRequest(flowalpha);
            return;
        }
        //===================================================================
        long sampleid = 0;
        String resptable = req.getParameter(RESPONSESTABLE);
        try { sampleid = Long.parseLong(req.getParameter(SAMPLEID)); } catch (Exception e) {}
        //===================================================================
        try {
            //-------------------------------------------------------
            FieldCast fieldcast = new FieldCast();
            fieldcast.setSampleID(sampleid);
            fieldcast.setUserID(session.getUserId());
            fieldcast.setResponsesTable(resptable);
            //-------------------------------------------------------
            FieldCollector fcollector = new FieldCollector();
            fcollector.setSurveyLambda(flowalpha.getAurigaObject().getSurveyLambda());
            fcollector.setSampleLambda(flowalpha.getAurigaObject().getSampleLambda());
            fcollector.setResponseLambda(flowalpha.getAurigaObject().getResponseLambda());
            fcollector.castFieldResponses(fieldcast);
            //-------------------------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Samples Ok"));
            //-------------------------------------------------------
            this.sendResponse(resp, jsonresp);
            //-------------------------------------------------------
        }
        catch (AppException e) {
            this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode());
        }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to cast responses for sample " + sampleid);
            System.out.println(e.getMessage());            
        }
        //===================================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //===================================================================
   }
    //***********************************************************************
}
//***************************************************************************
