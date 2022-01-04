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
import methionine.auth.User;
import valine.ApiAlpha;
import valine.FlowAlpha;
import valine.jsontreatment.JAuthData;
//***************************************************************************
@WebServlet(name = "ApiUserAccount", urlPatterns = {ApiUserAccount.URL}, loadOnStartup=1)
public class ApiUserAccount extends ApiAlpha {
    public static final String URL = "/auth/useraccount";
    public static final String JACCOUNT = "accountdata";
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //===================================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //===================================================================
        Session session = flowalpha.getSession();
        if (!session.isValid()) {
            this.sendErrorResponse(resp, "No valid session", AuthErrorCodes.NOVALIDSESSION);
            fleeRequest(flowalpha);
            return;
        }
        //===================================================================
        try {
            User user = flowalpha.getAurigaObject().getAuthLambda().getUser(session.getUserId());
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Your user account"));
            jsonresp.addPair(new JsonPair(JACCOUNT, JAuthData.getUser(user)));
            this.sendResponse(resp, jsonresp);
        }
        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to fetch user data");
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
