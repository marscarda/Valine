package valine;
//**************************************************************************
import methionine.billing.BillingLambda;
import methionine.Electra;
import methionine.auth.AuthLamda;
import methionine.project.ProjectLambda;
import threonine.map.MapsLambda;
import tryptophan.design.DesignAtlas;
import tryptophan.sample.SampleAtlas;
import tryptophan.survey.metric.MetricQueryInterface;
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
    DesignAtlas designlambda = null;
    public DesignAtlas getDesignLambda () throws Exception {
        if (designlambda == null) {
            designlambda = new DesignAtlas();
            designlambda.setElectraObject(electra);
            designlambda.setDataBaseName(LifeTimeValues.dbdesign);
        }
        return designlambda;
    }
    //*************************************************************
    SampleAtlas samplelambda = null;
    public SampleAtlas getSampleLambda () {
        if (samplelambda == null) {
            samplelambda = new SampleAtlas();
            samplelambda.setElectraObject(electra);
            samplelambda.setDataBaseName(LifeTimeValues.dbsample);
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
}
//**************************************************************************
