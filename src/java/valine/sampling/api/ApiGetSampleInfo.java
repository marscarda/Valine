package valine.sampling.api;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.sample.SampleCenterField;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.AuthErrorCodes;
import methionine.auth.Session;
import tryptophan.sample.Sample;
import valine.ApiAlpha;
import valine.FlowAlpha;
import valine.jbuilders.JSample;
//***************************************************************************
@WebServlet(name = "ApiGetSampleInfo", urlPatterns = {ApiGetSampleInfo.URL}, loadOnStartup=1)
public class ApiGetSampleInfo extends ApiAlpha{
    public static final String URL = "/api/sampling/getsampleinfo";
    public static final String SAMPLEID = "sampleid";
    public static final String JSAMPLE = "sample";
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
        try { sampleid = Long.parseLong(req.getParameter(SAMPLEID)); } catch (Exception e) {}
        try {
            SampleCenterField center = new SampleCenterField();
            center.setSampleLambda(flowalpha.getAurigaObject().getSampleLambda());
            Sample sample = center.getSample(sampleid, session.getUserId());
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Samples Ok"));
            jsonresp.addPair(new JsonPair(JSAMPLE, JSample.getSample(sample)));
            this.sendResponse(resp, jsonresp);
        }
        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to get sample " + sampleid);
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
