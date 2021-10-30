package valine.jsontreatment;
//***************************************************************************
import mars.jsonsimple.JsonObject;
import tryptophan.sample.ResponseValue;
//***************************************************************************
public class JValuesDecoder extends JFormAndQ {
    //***********************************************************************
    public static ResponseValue[] decodeValues (JsonObject jvalues) {
        //=========================================================
        int count = jvalues.getPair(COUNT).getIntValue();
        JsonObject[] jarray = jvalues.getPair(ITEMS).getChildrenArray();
        //=========================================================
        ResponseValue[] values = new ResponseValue[count];
        long variableid;
        int variabletype, value;
        JsonObject jvalue;
        for (int n = 0; n < count; n++) {
            jvalue = jarray[n];
            variableid = jvalue.getPair(VARIABLEID).getLongValue();
            variabletype = jvalue.getPair(VARIABLETYPE).getIntValue();
            value = jvalue.getPair(VALUE).getIntValue();
            values[n] = new ResponseValue();
            values[n].setMetricId(variableid);
            values[n].setType(variabletype);
            values[n].setValue(value);
        }
        //=========================================================
        return values;
        //=========================================================
    }
    //***********************************************************************
}
//***************************************************************************
