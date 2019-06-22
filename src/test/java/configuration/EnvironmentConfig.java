package configuration;
import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:environment/${envName}.env.properties"
})
public interface EnvironmentConfig extends Config {

    @DefaultValue("uat")
    String environment();

    @DefaultValue("https://www.gobear.com/ph?x_session_type=UAT")
    String url();

    @DefaultValue("https://www.gobear.com/ph/insurance/travel")
    String travelUrl();

    @DefaultValue("https://www.gobear.com/ph/insurance/travel/quote-online?")
    String travelResultsUrl();

    @DefaultValue("dohoangvu2")
    String browserstackUsername();

    @DefaultValue("aWc5qRSWCrsMs5zEqBKk")
    String browserstackAccessKey();

    @DefaultValue("chrome")
    String browser();
}