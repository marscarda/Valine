package valine.jbuilders;
//***************************************************************************
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import tryptophan.sample.Sample;
//***************************************************************************
public class JSample {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String SAMPLEID = "sampleid";
    public static final String PROJECTID = "projectid";
    public static final String FORMID = "formid";
    public static final String NAME = "name";
    public static final String USERID = "userid";
    public static final String USERNAME = "username";
    public static final String TASKTODO = "tasktodo";
    public static final String FORMNAME = "formname";
    public static final String MAXSIZE = "maxsize";
    //***********************************************************************
    public static JsonObject getSamples (Sample[] samples) {
        JsonObject juniverses = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (Sample sample : samples) {
            jarray.addPair(new JsonPair(SAMPLEID, sample.sampleID()));
            jarray.addPair(new JsonPair(PROJECTID, sample.projectID()));
            jarray.addPair(new JsonPair(FORMID, sample.formID()));
            jarray.addPair(new JsonPair(NAME, sample.getName()));
            jarray.addPair(new JsonPair(USERID, sample.userID()));
            jarray.addPair(new JsonPair(USERNAME, sample.getUserName()));
            jarray.addPair(new JsonPair(TASKTODO, sample.taskToDo()));
            jarray.addPair(new JsonPair(FORMNAME, sample.getFormName()));
            jarray.addToArray();
        }        
        juniverses.addPair(new JsonPair(COUNT, jarray.getCount()));
        juniverses.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return juniverses;
    } 
    //=======================================================================
    public static JsonObject getSample (Sample sample) {
        JsonObject jvariable = new JsonObject();
        jvariable.addPair(new JsonPair(SAMPLEID, sample.sampleID()));
        jvariable.addPair(new JsonPair(PROJECTID, sample.projectID()));
        jvariable.addPair(new JsonPair(FORMID, sample.formID()));
        jvariable.addPair(new JsonPair(NAME, sample.getName()));
        jvariable.addPair(new JsonPair(USERID, sample.userID()));
        jvariable.addPair(new JsonPair(USERNAME, sample.getUserName()));
        jvariable.addPair(new JsonPair(TASKTODO, sample.taskToDo()));
        jvariable.addPair(new JsonPair(FORMNAME, sample.getFormName()));
        return jvariable;
    }
    //***********************************************************************
}
//***************************************************************************
