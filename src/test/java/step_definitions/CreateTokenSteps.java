package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import pojo.request.auth.CreateTokenReq;
import pojo.response.auth.CreateTokenRes;
import utils.APIUtils;
import utils.TestDataWriter;

public class CreateTokenSteps extends BaseStep {

    CreateTokenReq payload = new CreateTokenReq();

    CreateTokenRes createTokenRes;
    @When("the user creates a token with valid username as {string} password as {string}")
    public void theUserCreatesATokenWithUsernameAsPasswordAs(String username, String password) {
        payload.setUsername(username);
        payload.setPassword(password);

        response = APIUtils.sendPostRequest(request, AUTH_ENDPOINT, payload);

        createTokenRes = response.as(CreateTokenRes.class);

        TestDataWriter.dataWriter(createTokenRes, "token.json");
    }

    @When("the user creates a token with invalid username as {string} password as {string}")
    public void theUserCreatesATokenWithInvalidUsernameAsPasswordAs(String username, String password) {
        payload.setUsername(username);
        payload.setPassword(password);

        response = APIUtils.sendPostRequest(request, AUTH_ENDPOINT, payload);
    }

    @When("the user creates a token with username as {string} password as {string} and invalid endpoint as {string}")
    public void theUserCreatesATokenWithUsernameAsPasswordAsAndInvalidEndpointAs(String username, String password, String endpoint) {
        payload.setUsername(username);
        payload.setPassword(password);

        response = APIUtils.sendPostRequest(request, endpoint, payload);

    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int statusCode) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @And("the response token can not be null or empty")
    public void theResponseTokenCanNotBeNullOrEmpty() {
        Assertions.assertThat(createTokenRes.getToken()).isNotEmpty();
        Assertions.assertThat(createTokenRes.getToken()).isNotNull();
    }



}
