package valine.sampling;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.Session;
import tryptophan.epsilon.AsertyTBN;
import tryptophan.epsilon.SampleForm;
import tryptophan.epsilon.SamplingCenter;
import tryptophan.survey.SurveyItemPointer;
import tryptophan.survey.publicview.PVCandidate;
import valine.ApiAlpha;
import valine.FlowAlpha;
//***************************************************************************
@WebServlet(name = "ApiGetSampleForm", urlPatterns = {ApiGetSampleForm.URL}, loadOnStartup=1)
public class ApiGetSampleForm extends ApiAlpha {
    public static final String URL = "/sampling/getsampleform";
    public static final String SAMPLEID = "sampleid";
    public static final String TITLE = "title";
    public static final String BRIEF = "brief";
    public static final String LABEL = "label";
    public static final String JFORMITEMS = "formitems";
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
        long sampleid = 0;
        try { sampleid = Long.parseLong(req.getParameter(SAMPLEID)); } catch (Exception e) {} 
        //==========================================================        
        try {
            //-------------------------------------------------------
            SamplingCenter center = new SamplingCenter();
            center.setAuthLambda(flowalpha.getAurigaObject().getAuthLambda());
            center.setSampleLambda(flowalpha.getAurigaObject().getSampleLambda());
            center.setSurveyLambda(flowalpha.getAurigaObject().getSurveyLambda());
            center.setPublicViewLambda(flowalpha.getAurigaObject().getPubViewInterface());
            //-------------------------------------
            SampleForm sform = center.getSampleForm(sampleid, session.getUserId());
            //-------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Samples Ok"));
            //-------------------------------------------------------
            jsonresp.addPair(new JsonPair(JFORMITEMS, JSampleForm(sform)));
            //-------------------------------------------------------
            this.sendResponse(resp, jsonresp);
        }
        catch (AppException e) {
            this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode());
        }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to get form for sample " + sampleid);
            System.out.println(e.getMessage());            
        }
        //===================================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //===================================================================
   }
    //***********************************************************************
    public static final String ITEMTYPE = "itemtype";
    public static final String ITEMID = "itemid";
    private JsonObject JSampleForm (SampleForm sform) {
        //===================================================================
        AsertyTBN[] items = sform.getItems();
        JsonArray array = new JsonArray();
        for (AsertyTBN item : items) {
            switch(item.getType()) {
                case SurveyItemPointer.ITEMTYPE_PUBIMAGE: {
                    PVCandidate candidate = (PVCandidate)item.getItem();
                    array.addPair(new JsonPair(ITEMTYPE, item.getType()));
                    array.addPair(new JsonPair(ITEMID, item.getItemId()));
                    array.addPair(new JsonPair(LABEL, candidate.getLabel()));
                }
            }
            array.addToArray();
        }
        //===================================================================
        JsonObject jitems = new JsonObject();
        jitems.addPair(new JsonPair(TITLE, sform.getTitle()));
        jitems.addPair(new JsonPair(BRIEF, sform.getBrief()));
        jitems.addPair(new JsonPair(COUNT, array.getCount()));
        jitems.addPair(new JsonPair(ITEMS, array.getArray()));
        //===================================================================
        return jitems;
    }
    //***********************************************************************
}
//***************************************************************************
