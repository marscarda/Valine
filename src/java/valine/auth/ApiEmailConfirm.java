package valine.auth;
//***************************************************************************
import valine.ApiAlpha;
import valine.FlowAlpha;
import histidine.auth.ExcAuth;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "ApiEmailConfirm", urlPatterns = {ApiEmailConfirm.URL}, loadOnStartup=1)
public class ApiEmailConfirm extends ApiAlpha {
    public static final String URL = "/confirmemailaddress";
    public static final String CODE = "code";
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //==========================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //==========================================================
        Session session = flowalpha.getSession();
        boolean allowed = false;
        if (session.isValid()) allowed = true;
        if (!allowed) {
            this.sendErrorResponse(resp, "Unuthorized", UNAUTHORIZED);
            fleeRequest(flowalpha);
            return;
        }
        //==========================================================
        String code = req.getParameter(CODE);
        try {
            ExcAuth exc = new ExcAuth();
            exc.setAuriga(flowalpha.getAurigaObject());
            exc.confirmEmail(session.getUserId(), code);
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Email confirmed"));
            //------------------------------------------------------
            this.sendResponse(resp, jsonresp);
            //------------------------------------------------------
        }
        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Failed to create trial space (ventol)");
            System.out.println(e.getMessage());            
        }
        //==========================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //==========================================================
   }
    //***********************************************************************
}
//***************************************************************************
