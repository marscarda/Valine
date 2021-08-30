package valine.jbuilders;
//***************************************************************************
import lycine.sample.FinalForm;
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import tryptophan.design.Form;
import tryptophan.design.FormQuestion;
//***************************************************************************
public class JFormAndQ {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String FORMID = "formid";
    public static final String INTROSUMARY = "introsumary";
    public static final String STARTLABEL = "startlabel";
    public static final String THANKSLABEL = "thankslabel";
    public static final String QUESTIONID = "questionid";
    public static final String VARIABLETYPE = "variabletype";
    public static final String VARIABLEID = "variableid";
    public static final String LABEL = "label";
    public static final String QUESTIONFORK = "fork";
    public static final String OPTIONS = "options";
    public static final String LBLPVIEWFORMULATION = "labelpvformulation";
    public static final String LBLPVIEWPOSITIVE = "labelpvpositive";
    public static final String LBLPVIEWNEUTRAL = "labelpvneutral";
    public static final String LBLPVIEWNEGATIVE = "labelpvnegative";
    public static final String LBLPVIEWUNKNOWN = "labelpvunknown";
    //***********************************************************************
    /**
     * 
     * @param finalform
     * @return 
     */
    public static JsonObject getForm (FinalForm finalform) {
        Form form = finalform.getForm();
        JsonObject jform = new JsonObject();
        jform.addPair(new JsonPair(FORMID, form.formID()));
        jform.addPair(new JsonPair(INTROSUMARY, form.getSumary()));
        jform.addPair(new JsonPair(STARTLABEL, form.startLabel()));
        jform.addPair(new JsonPair(THANKSLABEL, form.thanksLabel()));
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
    /**
     * 
     * @param questions
     * @return 
     */
    public static JsonObject getQuestions (FormQuestion[] questions) {
        JsonObject jquestions = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (FormQuestion question : questions) {
            jarray.addPair(new JsonPair(QUESTIONID, question.questionID()));
            jarray.addPair(new JsonPair(VARIABLETYPE, question.getType()));
            jarray.addPair(new JsonPair(VARIABLEID, question.variableID()));
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
