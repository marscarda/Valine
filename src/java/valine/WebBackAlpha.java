package valine;
//***************************************************************************
/*
import valine.main.WebFrontAuth;
import alanine.main.WebFrontHome;
import alanine.main.WebFrontLogout;
import alanine.maps.WebFrontMapRecords;
import alanine.metrics.WebFrontMyMetrics;
import alanine.survey.WebFrontSurveyDashboard;
import alanine.survey.publicview.WebFrontPublicView;
import alanine.universe.WebFrontUniverses;
import alanine.users.WebFrontUsers;
*/
import methionine.auth.User;

//***************************************************************************
public class WebBackAlpha {
    //***********************************************************************
    public static final int ERRINTERNAL = 1;
    //-----------------------------------------------------------------------
    int errmessagecode = 0;
    public void setErrorMessageCode (int error) { this.errmessagecode = error; }
    public int getErrorMessageCode () { return this.errmessagecode; }
    //***********************************************************************
    String logintoken = null;
    public void setLoginToken (String logintoken) { this.logintoken = logintoken; }
    public String loginToken () {
        if (logintoken == null) return "";
        return logintoken;
    }
    //***********************************************************************
    String rooturl = null;    
    public void setRootURL (String u) { rooturl = u; }
    public String getRootURL () {
        if (rooturl == null) return "";
        return rooturl;
    }
    //***********************************************************************
    User loggedinuser = null;
    public void setLoggedInUser (User loggedinuser) { this.loggedinuser = loggedinuser; }
    public User getLoggedInUser () {
        if (loggedinuser == null) return new User();
        return loggedinuser;
    }
    //***********************************************************************
    /*
    public String getAuthURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontAuth.PAGE);
        return url.toString();
    }
    //=======================================================================
    public String logOutURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontLogout.PAGE);
        return url.toString();
    }
    //=======================================================================
    public String usersURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontUsers.PAGE);
        return url.toString();
    }
    //=======================================================================
    public String universesURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontUniverses.PAGE);
        return url.toString();
    }
    //=======================================================================
    public String mapRecordsURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontMapRecords.PAGE);
        return url.toString();
    }
    //***********************************************************************
    //Menu
    public String getHomeURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontHome.PAGE);
        return url.toString();
    }
    //=======================================================================
    public String getMyMetrics () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontMyMetrics.PAGE);
        return url.toString();
    }
    //=======================================================================
    public String getSurveyPanelURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontSurveyDashboard.PAGE);
        return url.toString();
    }
    //***********************************************************************
    */
}
//***************************************************************************
