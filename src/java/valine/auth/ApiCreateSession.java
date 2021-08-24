package valine.auth;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.AuthErrorCodes;
import methionine.auth.Session;
import valine.ApiAlpha;
import valine.FlowAlpha;
//***************************************************************************
@WebServlet(name = "ApiSignInSession", urlPatterns = {ApiCreateSession.URL}, loadOnStartup=1)
public class ApiCreateSession extends ApiAlpha {
    public static final String URL = "/auth/createsession";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String APPID = "appid";
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //===================================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //===================================================================
        String user = req.getParameter(USER);
        String pass = req.getParameter(PASSWORD);
        String app = req.getParameter(APPID);
        //==========================================================
        try {
            Session session = flowalpha.getAurigaObject().getAuthLambda().createSession(req.getRemoteAddr(), user, pass);
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "signin succeded"));
            jsonresp.addPair(new JsonPair(CREDENTIALTOKEN, session.getLoginToken()));
            jsonresp.addPair(new JsonPair(USERID, session.getUserId()));
            this.sendResponse(resp, jsonresp);
        }
        catch (AppException e) {
            switch (e.getErrorCode()) {
                case AuthErrorCodes.USERNOTFOUND:
                case AuthErrorCodes.INVALIDPASSWORD:
                    this.sendErrorResponse(resp, "Invalid Credentials", AuthErrorCodes.INVALIDCREDENTIALS);
                    break;
            }
            this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode());
        }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to sign up user");
            System.out.println(e.getMessage());            
        }
        //===================================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //===================================================================
   }
    //***********************************************************************
}
//***************************************************************************
