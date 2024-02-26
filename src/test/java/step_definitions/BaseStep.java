package step_definitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigManager;

public class BaseStep {

    protected static Response response;

    protected static RequestSpecification request;

    protected final String AUTH_ENDPOINT;

    protected final String BOOKING_ENDPOINT;

    public BaseStep() {
        RestAssured.baseURI = ConfigManager.getProperty("BaseURI");
        AUTH_ENDPOINT = ConfigManager.getProperty("api.auth.endpoint");
        BOOKING_ENDPOINT = ConfigManager.getProperty("api.order.endpoint");
    }
}
