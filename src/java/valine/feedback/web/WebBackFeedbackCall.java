package valine.feedback.web;
//***************************************************************************
import tryptophan.design.Form;
import tryptophan.sample.ResponseCall;
import valine.WebBackAlpha;
import valine.jsontreatment.JFeedback;
//***************************************************************************
public class WebBackFeedbackCall extends WebBackAlpha {
    
    
    //***********************************************************************
    Form form = null;
    void setForm (Form form) { this.form = form; }
    public Form getForm () {
        if (form == null) return new Form();
        return form;
    }
    public String jForm () { return JFeedback.getForm(getForm()).toString(); }
    //***********************************************************************
    ResponseCall call = null;
    void setCall (ResponseCall call) { this.call = call; }
    public ResponseCall getCall () {
        if (call == null) return new ResponseCall();
        return call;
    }
    public String jCall () { return JFeedback.getCall(getCall()).toString(); }
    //***********************************************************************    
    
}
//***************************************************************************
