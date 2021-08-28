package valine.jbuilders;
//***************************************************************************
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
    public static final String SUMARY = "sumary";
    public static final String STARTLABEL = "startlabel";
    public static final String THANKSLABEL = "thankslabel";
    public static final String QUESTIONID = "questionid";
    public static final String VARIABLETYPE = "variabletype";
    public static final String VARIABLEID = "variableid";
    public static final String LABEL = "label";
    public static final String OPTIONS = "options";
    //***********************************************************************
    /**
     * 
     * @param form
     * @return 
     */
    public static JsonObject getForm (Form form) {
        JsonObject jform = new JsonObject();
        jform.addPair(new JsonPair(FORMID, form.formID()));
        jform.addPair(new JsonPair(SUMARY, form.getSumary()));
        jform.addPair(new JsonPair(STARTLABEL, form.startLabel()));
        jform.addPair(new JsonPair(THANKSLABEL, form.thanksLabel()));
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
