package valine.auth;
//***************************************************************************
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.Session;
import valine.ApiAlpha;
import valine.FlowAlpha;
//***************************************************************************
public class ApiCheckSession extends ApiAlpha {
    public static final String URL = "/auth/checksession";
    public static final String JSESSION = "session";
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //===================================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //===================================================================
        Session session = flowalpha.getSession();
        JsonObject jsonresp = new JsonObject();
        jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
        jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Session Status"));
        //-------------------------------------------------------------
        //If valid session is ok we send valid = true and the session data.
        if (session.isValid()) {
            jsonresp.addPair(new JsonPair(SESSIONVALID, true));
            JsonObject jsession = new JsonObject();
            jsession.addPair(new JsonPair(CREDENTIALTOKEN, session.getLoginToken()));
            jsession.addPair(new JsonPair(SESSIONID, session.getSessionId()));
            jsession.addPair(new JsonPair(USERID, session.getUserId()));
            jsonresp.addPair(new JsonPair(JSESSION, jsession));
        }
        //If valid session is ok we send valid = false.
        else { jsonresp.addPair(new JsonPair(SESSIONVALID, false)); }
        this.sendResponse(resp, jsonresp);
        //===================================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //===================================================================
   }
    //***********************************************************************
}
//***************************************************************************
