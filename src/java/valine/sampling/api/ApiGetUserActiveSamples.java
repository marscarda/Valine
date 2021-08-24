package valine.sampling.api;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.sample.SampleCenterField;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.Session;
import tryptophan.sample.Sample;
import valine.ApiAlpha;
import valine.FlowAlpha;
//***************************************************************************
@WebServlet(name = "ApiGetUserEntrustedSamples", urlPatterns = {ApiGetUserActiveSamples.URL}, loadOnStartup=1)
public class ApiGetUserActiveSamples extends ApiAlpha {
    public static final String URL = "/api/sampling/getuseractivesamples";
    public static final String JSESSION = "session";
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
        try {
            SampleCenterField center = new SampleCenterField();
            center.setSampleLambda(flowalpha.getAurigaObject().getSampleLambda());
            Sample[] samples = center.getUserActiveSamples(session.getUserId());
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Samples List"));
            
            //JSON with samples here.
            
            this.sendResponse(resp, jsonresp);
        }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to get active samples for userid " + session.getUserId());
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
