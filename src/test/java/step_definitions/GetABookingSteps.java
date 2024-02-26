package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import pojo.response.booking.create_booking.CreateBookingRes;
import pojo.response.booking.get_booking.GetBookingRes;
import utils.APIUtils;
import utils.TestDataReader;

public class GetABookingSteps extends BaseStep{

    GetBookingRes actualResult;

    CreateBookingRes expectedResult;

    String endpoint;
    @When("the user get a booking with specified id")
    public void theUserGetABookingWithSpecifiedId() {
        expectedResult = TestDataReader.dataReader("create_booking.json", CreateBookingRes.class);
        int bookingId = expectedResult.getBookingid();
        endpoint = BOOKING_ENDPOINT + "/" + bookingId;

        response = APIUtils.sendGetRequest(request, endpoint);

        actualResult = response.as(GetBookingRes.class);
    }

    @And("the response details should match specified booking details")
    public void theResponseDetailsShouldMatchSpecifiedOrderDetails() {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResult.getFirstname()).isEqualTo(expectedResult.getBooking().getFirstname());
        softAssertions.assertThat(actualResult.getLastname()).isEqualTo(expectedResult.getBooking().getLastname());
        softAssertions.assertThat(actualResult.getTotalprice()).isEqualTo(expectedResult.getBooking().getTotalprice());
        softAssertions.assertThat(actualResult.isDepositpaid()).isEqualTo(expectedResult.getBooking().isDepositpaid());
        softAssertions.assertThat(actualResult.getBookingdates().getCheckin()).isEqualTo(expectedResult.getBooking().getBookingdates().getCheckin());
        softAssertions.assertThat(actualResult.getBookingdates().getCheckout()).isEqualTo(expectedResult.getBooking().getBookingdates().getCheckout());
        softAssertions.assertThat(actualResult.getAdditionalneeds()).isEqualTo(expectedResult.getBooking().getAdditionalneeds());

        softAssertions.assertAll();
    }

    @When("the user get a booking with invalid id as {int}")
    public void theUserGetABookingWithIdAs(int id) {
        String endpoint = BOOKING_ENDPOINT + "/" + id;

        response = APIUtils.sendGetRequest(request, endpoint);
    }

    @When("the user get a booking with specified id and invalid enpoint as {string}")
    public void theUserGetABookingWithSpecifiedIdAndInvalidEnpointAs(String invalidEndpoint) {
        expectedResult = TestDataReader.dataReader("create_booking.json", CreateBookingRes.class);
        int bookingId = expectedResult.getBookingid();
        endpoint = invalidEndpoint + "/" + bookingId;

        response = APIUtils.sendGetRequest(request, endpoint);
    }
}
