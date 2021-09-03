package valine.sampling.api;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.sample.field.FinalForm;
import lycine.sample.field.SampleCenterField;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.AuthErrorCodes;
import methionine.auth.Session;
import valine.ApiAlpha;
import valine.FlowAlpha;
import valine.jsontreatment.JFormAndQ;
//***************************************************************************
@WebServlet(name = "ApiGetFieldForm", urlPatterns = {ApiGetFieldFinalForm.URL}, loadOnStartup=1)
public class ApiGetFieldFinalForm extends ApiAlpha {
    public static final String URL = "/api/sampling/getfinalform";
    public static final String SAMPLEID = "sampleid";
    public static final String JFORM = "form";
    public static final String JQUESTIONS = "questions";
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
            center.setVariableLambda(flowalpha.getAurigaObject().getDesignLambda());
            FinalForm form = center.getFinalFormBySample(sampleid, session.getUserId());
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Form by sample Ok"));
            jsonresp.addPair(new JsonPair(JFORM, JFormAndQ.getForm(form)));
            jsonresp.addPair(new JsonPair(JQUESTIONS, JFormAndQ.getQuestions(form.getQuestions())));
            this.sendResponse(resp, jsonresp);
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
