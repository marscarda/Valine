package valine.jsontreatment;
//***************************************************************************
import lycine.sample.FinalForm;
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import tryptophan.design.Form;
import tryptophan.design.FormMetricRef;
import tryptophan.sample.ResponseCall;
//***************************************************************************
public class JFeedback {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String FORMID = "formid";
    public static final String INTROSUMARY = "introsumary";
    public static final String STARTLABEL = "startlabel";
    public static final String REACHENDLABEL = "reachendlabel";
    public static final String THANKSLABEL = "thankslabel";
    public static final String FINISHLABEL = "finishlabel";
    public static final String QUESTIONID = "questionid";
    public static final String METRICTYPE = "metrictype";
    public static final String METRICID = "metricid";
    public static final String LABEL = "label";
    public static final String QUESTIONFORK = "fork";
    public static final String OPTIONS = "options";
    public static final String LBLPVIEWFORMULATION = "labelpvformulation";
    public static final String LBLPVIEWPOSITIVE = "labelpvpositive";
    public static final String LBLPVIEWNEUTRAL = "labelpvneutral";
    public static final String LBLPVIEWNEGATIVE = "labelpvnegative";
    public static final String LBLPVIEWUNKNOWN = "labelpvunknown";
    //Response Call.
    public static final String FEEDBACKCALLID = "feedbackcallid";
    public static final String DATE = "date";
    public static final String NAME = "name";
    public static final String EMAIL = "email";    
    public static final String CODE = "code";
    public static final String VALIDHOURS = "validhours";
    public static final String FEEDBACK = "feedback";
    public static final String ELAPSEDHOURS = "hours";
    public static final String HOURSTOEXPIRE = "hourstoexpire";
    public static final String EXPIRED = "expired";
    //-------------------------
    public static final String VALUE = "value";
    //***********************************************************************
    /**
     * 
     * @param finalform
     * @return 
     */
    @Deprecated
    public static JsonObject getForm (FinalForm finalform) {
        Form form = finalform.getForm();
        JsonObject jform = new JsonObject();
        jform.addPair(new JsonPair(FORMID, form.formID()));
        jform.addPair(new JsonPair(INTROSUMARY, form.getSumary()));
        jform.addPair(new JsonPair(STARTLABEL, form.startLabel()));
        jform.addPair(new JsonPair(THANKSLABEL, form.thanksLabel()));
        jform.addPair(new JsonPair(FINISHLABEL, form.finishLabel()));
        //---------------------------
        jform.addPair(new JsonPair(LBLPVIEWFORMULATION, finalform.labelPuvViewFormlt()));
        jform.addPair(new JsonPair(LBLPVIEWPOSITIVE, finalform.labelPubViewPositive()));
        jform.addPair(new JsonPair(LBLPVIEWNEUTRAL, finalform.labelPubViewNeutral()));
        jform.addPair(new JsonPair(LBLPVIEWNEGATIVE, finalform.labelPubViewNegative()));
        jform.addPair(new JsonPair(LBLPVIEWUNKNOWN, finalform.labelPubViewUnknown()));
        //---------------------------
        return jform;
    }
    //***********************************************************************
    public static JsonObject getForm (Form form) {
        JsonObject jform = new JsonObject();
        //--------------------------------------
        jform.addPair(new JsonPair(FORMID, form.formID()));
        jform.addPair(new JsonPair(INTROSUMARY, form.getSumary()));
        jform.addPair(new JsonPair(STARTLABEL, form.startLabel()));
        jform.addPair(new JsonPair(REACHENDLABEL, form.rechEndLabel()));
        jform.addPair(new JsonPair(THANKSLABEL, form.thanksLabel()));
        jform.addPair(new JsonPair(FINISHLABEL, form.finishLabel()));        
        //--------------------------------------
        return jform;
    }
    //***********************************************************************
    public static JsonObject getCall (ResponseCall call) {
        JsonObject jcall = new JsonObject();
        jcall.addPair(new JsonPair(FEEDBACKCALLID, call.callID()));
        jcall.addPair(new JsonPair(DATE, call.getDate()));
        jcall.addPair(new JsonPair(NAME, call.getName()));
        jcall.addPair(new JsonPair(EMAIL, call.getEmail()));
        jcall.addPair(new JsonPair(CODE, call.getCode()));
        jcall.addPair(new JsonPair(VALIDHOURS, call.validHours()));
        jcall.addPair(new JsonPair(FEEDBACK, call.getResponded()));
        jcall.addPair(new JsonPair(ELAPSEDHOURS, call.elapsedHours()));
        jcall.addPair(new JsonPair(HOURSTOEXPIRE, call.expiresIn()));
        jcall.addPair(new JsonPair(EXPIRED, call.isExpired()));
        return jcall;
    }
    //***********************************************************************
    public static JsonObject getMetrics (FormMetricRef[] questions) {
        JsonObject jmetrics = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (FormMetricRef question : questions) {
            jarray.addPair(new JsonPair(QUESTIONID, question.questionID()));
            jarray.addPair(new JsonPair(METRICTYPE, question.getType()));
            jarray.addPair(new JsonPair(METRICID, question.variableID()));
            jarray.addPair(new JsonPair(QUESTIONFORK, question.getPath()));
            jarray.addPair(new JsonPair(LABEL, question.getLabel()));
            jarray.addToArray();
        }        
        jmetrics.addPair(new JsonPair(COUNT, jarray.getCount()));
        jmetrics.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jmetrics;
    }    
    //***********************************************************************
    /**
     * 
     * @param questions
     * @return 
     */
    public static JsonObject getQuestions (FormMetricRef[] questions) {
        JsonObject jquestions = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (FormMetricRef question : questions) {
            jarray.addPair(new JsonPair(QUESTIONID, question.questionID()));
            jarray.addPair(new JsonPair(METRICTYPE, question.getType()));
            jarray.addPair(new JsonPair(METRICID, question.variableID()));
            jarray.addPair(new JsonPair(QUESTIONFORK, question.getPath()));
            jarray.addPair(new JsonPair(LABEL, question.getLabel()));
            jarray.addToArray();
        }        
        jquestions.addPair(new JsonPair(COUNT, jarray.getCount()));
        jquestions.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jquestions;
    }
    //***********************************************************************
}
//***************************************************************************
