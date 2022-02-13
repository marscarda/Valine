package valine;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//***************************************************************************
@WebServlet (name="WebFrontStatic22", urlPatterns= { WebFrontStatic22.URLPATTERN} )
public class WebFrontStatic22 extends WebFrontAlpha {
    //=======================================================================
    public static final String PAGE = "/static22";
    public static final String URLPATTERN = PAGE + "/*";
    //***********************************************************************
    static final int MAXAGE = 100000;
    //***********************************************************************
    //FONTS
    public static final String TEXTGRAL22 = "general";
    public static final String STRONG22 = "strong";
    //***********************************************************************
    //CSSs
    public static final String CSSROOT = "mfdgrdfkjkjtretdfgd";
    //=======================================================================
    //JS
    public static final String JSHTTP = "mdfjkretrt";
    public static final String JSTAGANIMATE = "nnfkdffdsrofkjsdsfg";
    //***********************************************************************
    //BITMAP
    public static final String HEADTITLELOGO = "nghdtertf";
    public static final String MAPRECINFOICON = "hfgyrtyryxcv";
    public static final String MAPENTERICON = "vdjfkjkjkdj";
    public static final String MAPSTATSICON = "bfgrtgfhghg";
    public static final String ZOOMINICON = "uofgkerndfbnb";
    public static final String ZOOMOUTICON = "nfervdfr4dsff";
    public static final String HANDICON = "rwerdfsdfsdfs";
    //-----------------------------------------------------------------------
    //Survey Icons
    public static final String SURVEYOPT_POSITIVE = "kgthdsfvrtrt";
    public static final String SURVEYOPT_NEUTRAL = "qwfvvchbfghgh";
    public static final String SURVEYOPT_NEGATIVE = "ghdfgderdfsf";
    public static final String SURVEYOPT_UNKNOWN = "qplfgdjfdghjd";    
    //***********************************************************************
    //THIRD PARTY ICONS
    public static final String PAYPALICON = "mgdftreter";
    public static final String TWITTERICON = "fkgjkjjtrt";
    public static final String YOUTUBEICON = "klerojrtrt";
    //***********************************************************************
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta libintfc = this.createFlowBeta(request, response);
        this.initialJob(libintfc);
        //===================================================================
        String resourcename = this.getURLsParamPart(request);
        if (resourcename == null) {
            response.setStatus(403);
            this.finallJob(libintfc);
            return;
        }
        //===================================================================
        String dispatchfile = null;
        //===================================================================
        //Fonts
        if (resourcename.equals(TEXTGRAL22)) dispatchfile = "/static2022/arm.ttfa";
        if (resourcename.equals(STRONG22)) dispatchfile = "/static2022/impact.ttf";
        //-------------------------------------------------------------------
        //CSSs
        if (resourcename.equals(CSSROOT)) dispatchfile = "/static2022/root.css";
        //-------------------------------------------------------------------
        //JS
        if (resourcename.equals(JSHTTP)) dispatchfile = "/static2022/httprequest.js";
        if (resourcename.equals(JSTAGANIMATE)) dispatchfile = "/static2022/taganimate.js";
        //-------------------------------------------------------------------
        //BITMAP
        //-------------------------------------------------------------------
        if (resourcename.equals(SURVEYOPT_POSITIVE)) dispatchfile = "/static2022/optionsicon/ic_positive.png";
        if (resourcename.equals(SURVEYOPT_NEUTRAL)) dispatchfile = "/static2022/optionsicon/ic_neutral.png";
        if (resourcename.equals(SURVEYOPT_NEGATIVE)) dispatchfile = "/static2022/optionsicon/ic_negative.png";
        if (resourcename.equals(SURVEYOPT_UNKNOWN)) dispatchfile = "/static2022/optionsicon/ic_nanhito.png";
        //-------------------------------------------------------------------
        if (dispatchfile == null) {
            libintfc.setStatusResponse(404);
            this.finallJob(libintfc);
            return;
        }
        //===================================================================
        StringBuilder maxage = new StringBuilder("max-age=");
        maxage.append(MAXAGE);
        maxage.append(", max-stale=");
        maxage.append(MAXAGE);
        response.setHeader("Cache-Control", maxage.toString());
        dispatchNormal(dispatchfile, request, response);
        //===================================================================
        this.finallJob(libintfc);
        this.destroyFlowBeta(libintfc);
        //===================================================================
    }    
    //=======================================================================
}
//***************************************************************************
