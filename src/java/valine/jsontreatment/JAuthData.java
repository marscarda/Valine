package valine.jsontreatment;
//***************************************************************************
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.User;
//***************************************************************************
public class JAuthData {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String USERID = "userid";
    public static final String USERNAME = "username";
    public static final String EMAILADDRESS = "emailaddress";
    public static final String EMAILCONFIRMED = "emailconfirmed";
    public static final String APROVAL = "aproval";
    //***********************************************************************
    public static JsonObject getUser (User user) {
        JsonObject juser = new JsonObject();
        juser.addPair(new JsonPair(USERID, user.userID()));
        juser.addPair(new JsonPair(USERNAME, user.loginName()));
        juser.addPair(new JsonPair(EMAILADDRESS, user.eMail()));
        juser.addPair(new JsonPair(EMAILCONFIRMED, user.emailConfirmed()));
        juser.addPair(new JsonPair(APROVAL, user.getAproval()));
        return juser;
    }
    //***********************************************************************
}
//***************************************************************************
