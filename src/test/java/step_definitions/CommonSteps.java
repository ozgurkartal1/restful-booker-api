package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import pojo.response.auth.CreateTokenRes;
import utils.TestDataReader;


public class CommonSteps extends BaseStep {

    @Given("the user is in base uri")
    public void theUserIsInBaseUri() {
       request = RestAssured.given();
    }

    @And("the authorization process is completed")
    public void theAuthorizationProcessIsCompleted() {
        String token = TestDataReader.dataReader("token.json", CreateTokenRes.class).getToken();
        request = request.cookie("token", token);
    }
}
