package step_definitions.order;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import pojo.request.booking.BookingReq;
import pojo.request.booking.Bookingdates;
import pojo.response.auth.CreateTokenRes;
import pojo.response.booking.create_booking.CreateBookingRes;
import pojo.response.booking.update_booking.UpdateBookingRes;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;

import java.util.Map;

public class UpdateBookingSteps extends BaseStep {

    BookingReq payload = new BookingReq();

    UpdateBookingRes actualResult;

    String endpoint;
    @When("the user update a specific booking with following details:")
    public void theUserUpdateASpecificBookingWithFollowingDetails(DataTable dataTable) {
        int bookingId = TestDataReader.dataReader("create_booking.json", CreateBookingRes.class).getBookingid();

        endpoint = BOOKING_ENDPOINT + "/" +bookingId;

        Map<String, String> bookingDetails = dataTable.asMap(String.class, String.class);
        System.out.println(bookingDetails);

        Bookingdates bookingdates = new Bookingdates(bookingDetails.get("checkin"), bookingDetails.get("checkout"));

        payload.setFirstname(bookingDetails.get("firstname"));
        payload.setLastname(bookingDetails.get("lastname"));
        payload.setTotalprice(Integer.parseInt(bookingDetails.get("totalprice")));
        payload.setDepositpaid(Boolean.parseBoolean(bookingDetails.get("depositpaid")));
        payload.setBookingdates(bookingdates);
        payload.setAdditionalneeds(bookingDetails.get("additionalneeds"));

        response = APIUtils.sendPutRequest(request, endpoint, payload);

        actualResult = response.as(UpdateBookingRes.class);
    }

    @And("the run time must be lower than {int} ms")
    public void theRunTimeMustBeLowerThanMs(int runTime) {
        Assertions.assertThat(response.getTime()).isLessThan(runTime);
    }

    @And("the response details should match with specified details")
    public void theResponseDetailsShouldMatchWithSpecifiedDetails() {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResult.getFirstname()).isEqualTo(payload.getFirstname());
        softAssertions.assertThat(actualResult.getLastname()).isEqualTo(payload.getLastname());
        softAssertions.assertThat(actualResult.getTotalprice()).isEqualTo(payload.getTotalprice());
        softAssertions.assertThat(actualResult.isDepositpaid()).isEqualTo(payload.isDepositpaid());
        softAssertions.assertThat(actualResult.getBookingdates().getCheckin()).isEqualTo(payload.getBookingdates().getCheckin());
        softAssertions.assertThat(actualResult.getBookingdates().getCheckout()).isEqualTo(payload.getBookingdates().getCheckout());
        softAssertions.assertThat(actualResult.getAdditionalneeds()).isEqualTo(payload.getAdditionalneeds());

        softAssertions.assertAll();
    }

    @When("the user update a booking with invalid booking id as {string} following details:")
    public void theUserUpdateABookingWithInvalidBookingIdAsFollowingDetails(String invalidBookingId, DataTable dataTable) {

        endpoint = BOOKING_ENDPOINT + "/" + invalidBookingId;

        Map<String, String> bookingDetails = dataTable.asMap(String.class, String.class);
        System.out.println(bookingDetails);

        Bookingdates bookingdates = new Bookingdates(bookingDetails.get("checkin"), bookingDetails.get("checkout"));

        payload.setFirstname(bookingDetails.get("firstname"));
        payload.setLastname(bookingDetails.get("lastname"));
        payload.setTotalprice(Integer.parseInt(bookingDetails.get("totalprice")));
        payload.setDepositpaid(Boolean.parseBoolean(bookingDetails.get("depositpaid")));
        payload.setBookingdates(bookingdates);
        payload.setAdditionalneeds(bookingDetails.get("additionalneeds"));

        response = APIUtils.sendPutRequest(request, endpoint, payload);
    }
}
