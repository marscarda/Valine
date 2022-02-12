package valine.feedback.web;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
