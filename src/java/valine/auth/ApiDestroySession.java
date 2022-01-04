package valine.auth;
//***************************************************************************
import histidine.auth.ExcAuth;
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
@WebServlet(name = "ApiDestroySession", urlPatterns = {ApiDestroySession.URL}, loadOnStartup=1)
public class ApiDestroySession extends ApiAlpha {
    public static final String URL = "/auth/destroysession";
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
            ExcAuth exc = new ExcAuth();
            exc.setAuriga(flowalpha.getAurigaObject());
            exc.logOut(session);
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Session destroyed"));
            this.sendResponse(resp, jsonresp);
        }
        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }
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
