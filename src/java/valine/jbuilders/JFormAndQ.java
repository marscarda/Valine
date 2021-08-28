package valine.jbuilders;
//***************************************************************************
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import tryptophan.design.Form;
//***************************************************************************
public class JFormAndQ {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String FORMID = "formid";
    public static final String STARTLABEL = "startlabel";
    public static final String THANKSLABEL = "thankslabel";
    //***********************************************************************
    /**
     * 
     * @param form
     * @return 
     */
    public static JsonObject getForm (Form form) {
        JsonObject jform = new JsonObject();
        jform.addPair(new JsonPair(FORMID, form.questionaryID()));
        jform.addPair(new JsonPair(STARTLABEL, form.startLabel()));
        jform.addPair(new JsonPair(THANKSLABEL, form.thanksLabel()));
        return jform;
    }
    //***********************************************************************
}
//***************************************************************************
