package valine.feedback.web;
//***************************************************************************
import lycine.sample.DisplayLabels;
import tryptophan.design.Form;
import tryptophan.design.FormMetricRef;
import tryptophan.sample.ResponseCall;
import valine.WebBackAlpha;
import valine.jsontreatment.JDisplayLabels;
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
    DisplayLabels labels = null;
    void setLabels (DisplayLabels labels) { this.labels = labels; }
    public DisplayLabels getLables () {
        if (labels == null) return new DisplayLabels();
        return labels;
    }
    public String jLabels () { return JDisplayLabels.getLabels(getLables()).toString(); }    
    //***********************************************************************
    FormMetricRef[] metricrefs = null;
    void setMetricRefs (FormMetricRef[] metricrefs) { this.metricrefs = metricrefs; }
    public FormMetricRef[] getMetricRefs () {
        if (metricrefs == null) return new FormMetricRef[0];
        return metricrefs;
    }
    public String jMetrics () { return JFeedback.getMetrics(getMetricRefs()).toString(); }
    //***********************************************************************
    public String apiSubmitURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiSubmitResponse.URL);
        return url.toString();
    }    
    //***********************************************************************
}
//***************************************************************************
