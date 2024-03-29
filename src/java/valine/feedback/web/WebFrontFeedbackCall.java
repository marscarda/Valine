package valine.feedback.web;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.sample.DisplayLabels;
import lycine.sample.FetchFeedbackCall;
import methionine.AppException;
import tryptophan.design.Form;
import tryptophan.design.FormMetricRef;
import tryptophan.sample.ResponseCall;
import tryptophan.sample.SampleErrorCodes;
import valine.FlowBeta;
import valine.WebFrontAlpha;
//***************************************************************************
@WebServlet(name = "WebFrontFeedbackCall", urlPatterns = {WebFrontFeedbackCall.URLPATTERN}, loadOnStartup=1)
public class WebFrontFeedbackCall extends WebFrontAlpha {
    //=======================================================================
    public static final String PAGE = "/web/feedback";
    public static final String URLPATTERN = PAGE + "/*";
    //***********************************************************************
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta flowbeta = this.createFlowBeta(request, response);
        this.initialJob(flowbeta);
        //===================================================================
        String code = this.getURLsParamPart(request);
        WebBackFeedbackCall back = new WebBackFeedbackCall();
        back.setRootURL(flowbeta.getRootURL());
        boolean available = true;
        try {
            //===================================================
            FetchFeedbackCall exc = new FetchFeedbackCall();
            exc.setAuriga(flowbeta.getAurigaObject());
            exc.setCallCode(code);
            exc.prepareIntro();
            exc.PrepareSurvey();
            //===================================================
            ResponseCall call = exc.getCall();
            if (call.isExpired()) available = false;
            if (call.responded()) available = false;
            if (available) {
                Form form = exc.getForm();
                DisplayLabels labels = exc.getLabels();
                FormMetricRef[] metrics = exc.getMetrics();
                back.setCall(call);
                back.setForm(form);
                back.setLabels(labels);
                back.setMetricRefs(metrics);
            }
            //===================================================
        }
        catch (AppException e) { if (e.getErrorCode() == SampleErrorCodes.RESPONSECALLNOTFOUND) available = false; }
        catch (Exception e) {
            //---------------------------------------------------
            System.out.println("Failure in response app page (gedoru)");
            System.out.println(e.getMessage());
            //---------------------------------------------------
        }        
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        if (available) this.dispatchNormal("/render2022/feedbackcall/feedbback.jsp", request, response);
        else this.dispatchNormal("/render2022/feedbackcall/invalid.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //***********************************************************************
}
//***************************************************************************
