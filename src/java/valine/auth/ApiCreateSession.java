package valine.auth;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.Session;
import valine.ApiAlpha;
import static valine.ApiAlpha.RESULT;
import static valine.ApiAlpha.RESULTDESCRIPTION;
import static valine.ApiAlpha.RESULTOK;
import valine.FlowAlpha;
//***************************************************************************
@WebServlet(name = "ApiSignInSession", urlPatterns = {ApiCreateSession.URL}, loadOnStartup=1)
public class ApiCreateSession extends ApiAlpha {
    public static final String URL = "/auth/createsession";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String APPLICATION = "application";
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //===================================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //===================================================================
        String user = req.getParameter(USER);
        String pass = req.getParameter(PASSWORD);
        String app = req.getParameter(APPLICATION);
        //==========================================================
        try {
            Session session = flowalpha.getAurigaObject().getAuthInterface().createSession(req.getRemoteAddr(), user, pass);
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Signup succeded"));
            jsonresp.addPair(new JsonPair(CREDENTIALTOKEN, session.getLoginToken()));
            jsonresp.addPair(new JsonPair(USERID, session.getUserId()));
            this.sendResponse(resp, jsonresp);
        }
        catch (AppException e) {
            switch (e.getErrorCode()) {
                //-------------------------------------------
                case AppException.USERNOTFOUND:
                case AppException.INVALIDPASS:
                    this.sendErrorResponse(resp, "Invalid Credentials", AppException.INVALIDCREDENTIAL);
                    break;
                //-------------------------------------------
            }
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
