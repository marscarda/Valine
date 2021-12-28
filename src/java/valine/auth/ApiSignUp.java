package valine.auth;
//***************************************************************************
import histidine.auth.ExcAuth;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.Session;
import methionine.auth.SignUpUser;
import valine.ApiAlpha;
import valine.FlowAlpha;
//***************************************************************************
@WebServlet(name = "ApiSignUp", urlPatterns = {ApiSignUp.URL}, loadOnStartup=1)
public class ApiSignUp extends ApiAlpha {
    public static final String URL = "/auth/signup";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String PASSWORDRETYPE = "passwordretype";
    public static final String EMAIL = "email";
   //************************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //===================================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //===================================================================
        String user = req.getParameter(USER);
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        String passwordretype = req.getParameter(PASSWORDRETYPE);
        String app = req.getParameter(APPLICATION);
        //===================================================================
        //We check both password match.
        if (!passwordretype.equals(password)) {
            this.sendErrorResponse(resp, "Password confirmation does not match", AppException.PASSWORDSETDIFFERENCE);
            fleeRequest(flowalpha);
            return;
        }
        //==========================================================
        try {
            SignUpUser sgnupuser = new SignUpUser();
            sgnupuser.setName(user);
            sgnupuser.setPassWord(password);
            sgnupuser.setEmail(email);
            sgnupuser.setApplication(app);
            ExcAuth exc = new ExcAuth();
            exc.setAuriga(flowalpha.getAurigaObject());
            Session session = exc.signUp(sgnupuser);
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Signup succeded"));
            jsonresp.addPair(new JsonPair(CREDENTIALTOKEN, session.getLoginToken()));
            this.sendResponse(resp, jsonresp);
        }
        catch (AppException e) {
            switch (e.getErrorCode()) {
                //-------------------------------------------
                case AppException.USERNAMENOTACCEPTED:
                    this.sendErrorResponse(resp, "Invalid user name", AppException.USERNAMENOTACCEPTED);
                    break;
                //-------------------------------------------
                case AppException.PASSWORDNOTACCEPTED:
                    this.sendErrorResponse(resp, "Invalid password", AppException.PASSWORDNOTACCEPTED);
                    break;
                //-------------------------------------------
                case AppException.EMAILNOTACCEPTED:
                    this.sendErrorResponse(resp, "Invalid email address", AppException.EMAILNOTACCEPTED);
                    break;
                //-------------------------------------------
                case AppException.USEREXISTS:
                    this.sendErrorResponse(resp, "User name already in use", AppException.USEREXISTS);
                    break;
                //-------------------------------------------
                case AppException.EMAILEXISTS:
                    this.sendErrorResponse(resp, "Email Address already in use", AppException.EMAILEXISTS);
                    break;
                //-------------------------------------------
            }
        }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to sign up user");
            System.out.println(e.getMessage());            
        }
        //==========================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //==========================================================
   }
   //************************************************************************
}
//***************************************************************************
