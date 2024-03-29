package valine.sampling.api;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.sample.SampleCenterField;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.AuthErrorCodes;
import methionine.auth.Session;
import tryptophan.sample.Sample;
import valine.ApiAlpha;
import valine.FlowAlpha;
import valine.jsontreatment.JSample;
//***************************************************************************
@WebServlet(name = "ApiGetUserActiveSamples", urlPatterns = {ApiGetUserActiveSamples.URL}, loadOnStartup=1)
public class ApiGetUserActiveSamples extends ApiAlpha {
    public static final String URL = "/api/sampling/getuseractivesamples";
    public static final String JSAMPLES = "samples";
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
        try {
            SampleCenterField center = new SampleCenterField();
            center.setAuriga(flowalpha.getAurigaObject());
            Sample[] samples = center.getUserActiveSamples(session.getUserId());
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Samples List"));
            jsonresp.addPair(new JsonPair(JSAMPLES, JSample.getSamples(samples)));
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
