package valine.jsontreatment;
//***************************************************************************
import lycine.sample.DisplayLabels;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
//***************************************************************************
public class JDisplayLabels {
    //***********************************************************************
    public static JsonObject getLabels (DisplayLabels labels) {
        JsonObject jcall = new JsonObject();
        //-------------------------------------------------------------------
        //Public View
        jcall.addPair(new JsonPair("pubviewformulation", labels.pubviewFormulation()));
        jcall.addPair(new JsonPair("pubviewpositive", labels.pubViewPositive()));
        jcall.addPair(new JsonPair("pubviewneutral", labels.pubViewNeutral()));
        jcall.addPair(new JsonPair("pubviewnegative", labels.pubViewNegative()));
        jcall.addPair(new JsonPair("pubviewunknown", labels.pubViewUnknown()));
        //-------------------------------------------------------------------
        return jcall;
        //-------------------------------------------------------------------
    }    
    //***********************************************************************
}
//***************************************************************************
