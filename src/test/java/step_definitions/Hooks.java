package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks extends BaseStep {

    Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUpTestEnvironment(Scenario scenario){
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        logger.info("Scenario " + scenario.getName() + " is started!");
    }

    @After
    public void tearDownTestEnvironment(Scenario scenario){
        if(scenario.isFailed()){
            logger.error("Scenario " + scenario.getName() + " is failed");

            if(response != null){
                logger.error("Logging response body for " + scenario.getName());
                logger.error(response.getBody().prettyPrint());
            }else{
                logger.error("Response can not be null for scenario " + scenario.getName());
            }
        }

        logger.info("Scenario " + scenario.getName() + " is finished!");
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::");
    }
}
