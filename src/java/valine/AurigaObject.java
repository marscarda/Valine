package valine;
//**************************************************************************
import methionine.Electra;
import methionine.auth.AuthLamda;
import tryptophan.survey.action.VarClusterLambda;
import tryptophan.survey.metric.MetricQueryInterface;
import tryptophan.survey.publicview.PublicViewLambda;
import tryptophan.survey.reaction.ReactionLambda;
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
    VarClusterLambda surveylambda = null;
    public VarClusterLambda getSurveyLambda () {
        if (surveylambda == null) {
            surveylambda = new VarClusterLambda();
            surveylambda.setElectraObject(electra);
            surveylambda.setDataBaseName(LifeTimeValues.dbsurvey);
        }
        return surveylambda;
    }
    //*************************************************************
    PublicViewLambda ifacepublicvie = null;
    public PublicViewLambda getPubViewInterface () throws Exception {
        if (ifacepublicvie == null) {
            ifacepublicvie = new PublicViewLambda();
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
    ReactionLambda responcelambda = null;
    public ReactionLambda getResponseLambda () throws Exception {
        if (responcelambda == null) {
            responcelambda = new ReactionLambda();
            responcelambda.setElectraObject(electra);
            responcelambda.setDataBaseName(LifeTimeValues.dbsurvey);
        }
        return responcelambda;
    }
    //*************************************************************
}
//**************************************************************************
