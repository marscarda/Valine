package valine;
//**************************************************************************
import methionine.Electra;
import methionine.auth.AuthLamda;
import tryptophan.survey.SurveyLambda;
import tryptophan.survey.metric.MetricQueryInterface;
import tryptophan.survey.publicview.InterfaceQueryPubView;
import tryptophan.survey.sampling.SampleLamda;
import tryptophan.universe.UniverseQryInterface;
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
    AuthLamda authinterface = null;
    public AuthLamda getAuthInterface () throws Exception {
        if (authinterface == null) {
            authinterface = new AuthLamda();
            authinterface.setElectraObject(electra);
            authinterface.setDataBaseName(LifeTimeValues.dbauth);
        }
        return authinterface;
    }
    //*************************************************************
    UniverseQryInterface universeqryinterface = null;
    public UniverseQryInterface getUniverseQryInterface () throws Exception {
        if (universeqryinterface == null) {
            universeqryinterface = new UniverseQryInterface();
            universeqryinterface.setElectraObject(electra);
            universeqryinterface.setDataBaseName(LifeTimeValues.dbuniverse);
        }
        return universeqryinterface;
    }
    //*************************************************************
    MetricQueryInterface metricsinterface = null;
    public MetricQueryInterface getMetricsQryInterface () throws Exception {
        if (metricsinterface == null) {
            metricsinterface = new MetricQueryInterface();
            metricsinterface.setElectraObject(electra);
            metricsinterface.setDataBaseName(LifeTimeValues.dbmetrics);
        }
        return metricsinterface;
    }
    //*************************************************************
    SurveyLambda surveylambda = null;
    public SurveyLambda getSurveyLambda () {
        if (surveylambda == null) {
            surveylambda = new SurveyLambda();
            surveylambda.setElectraObject(electra);
            surveylambda.setDataBaseName(LifeTimeValues.dbsurvey);
        }
        return surveylambda;
    }
    //*************************************************************
    InterfaceQueryPubView ifacepublicvie = null;
    public InterfaceQueryPubView getPubViewInterface () throws Exception {
        if (ifacepublicvie == null) {
            ifacepublicvie = new InterfaceQueryPubView();
            ifacepublicvie.setElectraObject(electra);
            ifacepublicvie.setDataBaseName(LifeTimeValues.dbsurvey);
        }
        return ifacepublicvie;
    }
    //*************************************************************
    SampleLamda samplelamda = null;
    public SampleLamda getSampleLambda () throws Exception {
        if (samplelamda == null) {
            samplelamda = new SampleLamda();
            samplelamda.setElectraObject(electra);
            samplelamda.setDataBaseName(LifeTimeValues.dbsurvey);
        }
        return samplelamda;
    }
    //*************************************************************
}
//**************************************************************************
