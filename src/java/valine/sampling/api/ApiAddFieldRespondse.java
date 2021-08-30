package valine.sampling.api;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.sample.SampleCenterField;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import mars.jsonsimple.JsonParser;
import methionine.AppException;
import methionine.auth.AuthErrorCodes;
import methionine.auth.Session;
import tryptophan.sample.Responder;
import valine.ApiAlpha;
import valine.FlowAlpha;
//***************************************************************************
@WebServlet(name = "ApiAddFieldRespondse", urlPatterns = {ApiAddFieldRespondse.URL}, loadOnStartup=1)
public class ApiAddFieldRespondse extends ApiAlpha {
    public static final String URL = "/api/sampling/addfieldresponse";
    public static final String SAMPLEID = "sampleid";
    public static final String VALUES = "values";
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //===================================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //===================================================================
        Session session = flowalpha.getSession();
        if (!session.isValid()) {
            this.sendErrorResponse(resp, "No valid session", AuthErrorCodes.NOVALIDSESSION);
            fleeRequest(flowalpha);
            return;
        }
        //===================================================================
        long sampleid = 0;
        String values = req.getParameter(VALUES);
        try { sampleid = Long.parseLong(req.getParameter(SAMPLEID)); } catch (Exception e) {}
        try {
            //----------------------------------------------------
            JsonObject jvalues = JsonParser.parseAndCreateJsonObject(values);
            //----------------------------------------------------
            SampleCenterField center = new SampleCenterField();
            center.setSampleLambda(flowalpha.getAurigaObject().getSampleLambda());
            center.setProjectLambda(flowalpha.getAurigaObject().getProjectLambda());
            center.setBillingLambda(flowalpha.getAurigaObject().getBillingLambda());
            
            Responder responder = new Responder();
            responder.setSampleId(sampleid);
            
            center.AddResponse(responder, session.getUserId());
            //----------------------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Response received"));
            this.sendResponse(resp, jsonresp);
            //----------------------------------------------------
        }
        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to get Form by Sample " + sampleid);
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

