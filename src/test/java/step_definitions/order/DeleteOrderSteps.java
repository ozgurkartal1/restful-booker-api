package step_definitions.order;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import pojo.response.booking.create_booking.CreateBookingRes;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;

public class DeleteOrderSteps extends BaseStep {

    String endpoint;
    @When("the user deletes specified order")
    public void theUserDeletesSpecifiedOrder() {
        int orderId = TestDataReader.dataReader("create_booking.json", CreateBookingRes.class).getBookingid();
        endpoint = BOOKING_ENDPOINT + "/" + orderId;

        response = APIUtils.sendDeleteRequest(request, endpoint);
    }

    @And("Verify that specified order is deleted")
    public void verifyThatSpecifiedOrderIsDeleted() {
        response = APIUtils.sendGetRequest(request, endpoint);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(404);
    }
}
