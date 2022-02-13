package valine.feedback.web;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.sample.FetchFeedbackCall;
import tryptophan.design.Form;
import tryptophan.sample.ResponseCall;
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
        try {
            //===================================================
            FetchFeedbackCall exc = new FetchFeedbackCall();
            exc.setAuriga(flowbeta.getAurigaObject());
            exc.setCallCode(code);
            exc.prepareIntro();
            //===================================================
            ResponseCall call = exc.getCall();
            Form form = exc.getForm();
            back.setCall(call);
            back.setForm(form);
            //===================================================
        }
        catch (Exception e) {
            //---------------------------------------------------
            System.out.println("Failure in response app page (gedoru)");
            System.out.println(e.getMessage());
            //---------------------------------------------------
        }        
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2022/feedbackcall/feedbback.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //***********************************************************************
}
//***************************************************************************
