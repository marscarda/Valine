package valine.sampling;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.Session;
import tryptophan.epsilon.SamplingCenter;
import tryptophan.survey.sampling.Sample;
import valine.ApiAlpha;
import valine.FlowAlpha;
//***************************************************************************
@WebServlet(name = "ApiGetUserSamples", urlPatterns = {ApiGetUserActiveSamples.URL}, loadOnStartup=1)
public class ApiGetUserActiveSamples extends ApiAlpha {
    public static final String URL = "/sampling/getuseractivesamples";
    public static final String SAMPLEID = "sampleid";
    public static final String SURVEYID = "surveyid";
    public static final String TITLE = "title";
    public static final String TEXT = "text";
    public static final String BRIEF = "brief";
    public static final String USERID = "userid";
    public static final String USERNAME = "username";
    public static final String RESPCOUNT = "respcount";
    public static final String JSAMPLES = "samples";
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
        //==========================================================
        try {
            SamplingCenter center = new SamplingCenter();
            center.setSurveyLambda(flowalpha.getAurigaObject().getSurveyLambda());
            center.setSampleLambda(flowalpha.getAurigaObject().getSampleLambda());
            center.setResponseLambda(flowalpha.getAurigaObject().getResponseLambda());
            Sample[] samples = center.fetchCommitedSamples(session.getUserId());
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Samples Ok"));
            //-------------------------------------------------------
            JsonArray array = new JsonArray();
            for (Sample sample : samples) {
                array.addPair(new JsonPair(SAMPLEID, sample.getSampleId()));
                array.addPair(new JsonPair(SURVEYID, sample.getSurveyId()));
                array.addPair(new JsonPair(TITLE, sample.getTitle()));
                array.addPair(new JsonPair(TEXT, sample.getText()));
                array.addPair(new JsonPair(BRIEF, sample.getTask()));
                array.addPair(new JsonPair(USERID, sample.getUserId()));
                array.addPair(new JsonPair(RESPCOUNT, sample.getResponsesCount()));
                array.addToArray();
            }
            //-------------------------------------------------------
            JsonObject jsamples = new JsonObject();
            jsamples.addPair(new JsonPair(COUNT, array.getCount()));
            jsamples.addPair(new JsonPair(ITEMS, array.getArray()));
            jsonresp.addPair(new JsonPair(JSAMPLES, jsamples));
            //-------------------------------------------------------
            this.sendResponse(resp, jsonresp);
        }    
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to get samples for userid " + session.getUserId());
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
