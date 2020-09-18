package valine;
//**************************************************************************
public class FlowBeta extends FlowAlpha {
    //**********************************************************************
    private CookiesFlow cookieflow = null;
    //**********************************************************************
    @Override
    public void initialize () {
        super.initialize();
        cookieflow = new CookiesFlow(request, response);
        cookieflow.setDomain(this.host);
        //-----------------------------------------------------------------
        StringBuilder gobackto = new StringBuilder();
        gobackto.append(request.getRequestURI());
        String qstr = request.getQueryString();
        if (qstr != null) {
            gobackto.append("?");
            gobackto.append(qstr);
        }
        cookieflow.setGoBackTo(gobackto.toString());
        //-----------------------------------------------------------------
    }
    //**********************************************************************
    public CookiesFlow getCookiesFlow () { return cookieflow; }
    public CookiesFlow.LANGUAGE getLanguage () { return cookieflow.getLanguage(); }
    public void setLanguage (CookiesFlow.LANGUAGE lang) { cookieflow.setLanguage(lang); }
    //**********************************************************************
}
//**************************************************************************
