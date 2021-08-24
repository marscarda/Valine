package valine;
//**************************************************************************
import methionine.billing.BillingLambda;
import methionine.Electra;
import methionine.auth.AuthLamda;
import methionine.project.ProjectLambda;
import serine.access.AccessLambda;
import serine.blogging.publication.PubsLambda;
import serine.pubs.object.ObjectPubLambda;
import serine.webmedia.WebMediaList;
import threonine.map.MapsLambda;
import tryptophan.design.DesignLambda;
import tryptophan.sample.SampleLambda;
import tryptophan.survey.action.ActionSetLambda;
import tryptophan.survey.metric.MetricQueryInterface;
import tryptophan.survey.reaction.ReactionLambda;
//**************************************************************************
/**
 * Holds instances of interfaces to interact with the database.
 * @author marian
 */
public class AurigaObject {
    //*************************************************************
    private final Electra electra = new Electra();
    public Electra getElectra () { return electra; }
    //*************************************************************
    /**
     * The direct connection to the auth data in DB.
     */
    AuthLamda authlambda = null;
    public AuthLamda getAuthLambda () throws Exception {
        if (authlambda == null) {
            authlambda = new AuthLamda();
            authlambda.setElectraObject(electra);
            authlambda.setDataBaseName(LifeTimeValues.dbauth);
        }
        return authlambda;
    }
    //*************************************************************
    /**
     * The direct connection to projects data
     */
    ProjectLambda workteamlamda = null;
    public ProjectLambda getProjectLambda () throws Exception {
        if (workteamlamda == null) {
            workteamlamda = new ProjectLambda();
            workteamlamda.setElectraObject(electra);
            workteamlamda.setDataBaseName(LifeTimeValues.dbauth);
        }
        return workteamlamda;
    }
    //*************************************************************
    BillingLambda billinglambda = null;
    public BillingLambda getBillingLambda () {
        if (billinglambda == null) {
            billinglambda = new BillingLambda();
            billinglambda.setElectraObject(electra);
            billinglambda.setDataBaseName(LifeTimeValues.dbbilling);
        }
        return billinglambda;
    }
    //*************************************************************
    DesignLambda designlambda = null;
    public DesignLambda getDesignLambda () throws Exception {
        if (designlambda == null) {
            designlambda = new DesignLambda();
            designlambda.setElectraObject(electra);
            designlambda.setDataBaseName(LifeTimeValues.dbdesign);
        }
        return designlambda;
    }
    //*************************************************************
    SampleLambda samplelambda = null;
    public SampleLambda getSampleLambda () {
        if (samplelambda == null) {
            samplelambda = new SampleLambda();
            samplelambda.setElectraObject(electra);
            billinglambda.setDataBaseName(LifeTimeValues.dbsample);
        }
        return samplelambda;
    }
    //*************************************************************
    MapsLambda mapslambda = null;
    public MapsLambda getMapsLambda () {
        if (mapslambda == null) {
            mapslambda = new MapsLambda();
            mapslambda.setElectraObject(electra);
            mapslambda.setDataBaseName(LifeTimeValues.dbmaps);
        }
        return mapslambda;
    }
    //*************************************************************
    MetricQueryInterface metricsinterface = null;
    @Deprecated
    public MetricQueryInterface getMetricsQryInterface () throws Exception {
        if (metricsinterface == null) {
            metricsinterface = new MetricQueryInterface();
            metricsinterface.setElectraObject(electra);
            metricsinterface.setDataBaseName(LifeTimeValues.dbmetrics);
        }
        return metricsinterface;
    }
    //*************************************************************
    ActionSetLambda surveylambda = null;
    @Deprecated
    public ActionSetLambda getSurveyLambda () {
        if (surveylambda == null) {
            surveylambda = new ActionSetLambda();
            surveylambda.setElectraObject(electra);
            surveylambda.setDataBaseName(LifeTimeValues.dbsurvey);
        }
        return surveylambda;
    }
    //*************************************************************
    ReactionLambda responcelambda = null;
    @Deprecated
    public ReactionLambda getResponseLambda () throws Exception {
        if (responcelambda == null) {
            responcelambda = new ReactionLambda();
            responcelambda.setElectraObject(electra);
            responcelambda.setDataBaseName(LifeTimeValues.dbsurvey);
        }
        return responcelambda;
    }
    //*************************************************************
    AccessLambda accesslambda = null;
    @Deprecated
    public AccessLambda getAccessLambda () throws Exception {
        if (accesslambda == null) {
            accesslambda = new AccessLambda();
            accesslambda.setElectraObject(electra);
            accesslambda.setDataBaseName(LifeTimeValues.dbaccess);
        }
        return accesslambda;
    }
    //*************************************************************
    PubsLambda pubslambda = null;
    @Deprecated
    public PubsLambda getPubsLambda () throws Exception {
        if (pubslambda == null) {
            pubslambda = new PubsLambda();
            pubslambda.setElectraObject(electra);
            pubslambda.setDataBaseName(LifeTimeValues.dbpublication);
        }
        return pubslambda;
    }
    //*************************************************************
    ObjectPubLambda objpubslambda = null;
    @Deprecated
    public ObjectPubLambda getObjectPubsLambda () throws Exception {
        if (objpubslambda == null) {
            objpubslambda = new ObjectPubLambda();
            objpubslambda.setElectraObject(electra);
            objpubslambda.setDataBaseName(LifeTimeValues.dbpublication);
        }
        return objpubslambda;
    }
    //*************************************************************
    static final WebMediaList MEDIALIST = new WebMediaList();
    @Deprecated
    public WebMediaList mediaList () { return MEDIALIST; }
    //*************************************************************
}
//**************************************************************************
