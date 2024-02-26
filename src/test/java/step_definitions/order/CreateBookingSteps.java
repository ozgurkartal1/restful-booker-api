package step_definitions.order;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import pojo.request.booking.Bookingdates;
import pojo.request.booking.BookingReq;
import pojo.response.booking.create_booking.CreateBookingRes;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataWriter;

import java.util.Map;

public class CreateBookingSteps extends BaseStep {

    BookingReq payload = new BookingReq();

    CreateBookingRes createBookingRes;

    @When("the user create a booking with following details:")
    public void theUserCreateABookingWithFollowingDetails(DataTable dataTable) {
        Map<String, String> customerDetail = dataTable.asMap(String.class, String.class);

        Bookingdates bookingdates = new Bookingdates(customerDetail.get("checkin"), customerDetail.get("checkout"));

        payload.setFirstname(customerDetail.get("firstname"));
        payload.setLastname(customerDetail.get("lastname"));
        payload.setTotalprice(Integer.parseInt(customerDetail.get("totalprice")));
        payload.setDepositpaid(Boolean.parseBoolean(customerDetail.get("depositpaid")));
        payload.setBookingdates(bookingdates);
        payload.setAdditionalneeds(customerDetail.get("additionalneeds")) ;

        response = APIUtils.sendPostRequest(request, BOOKING_ENDPOINT, payload);

        createBookingRes = response.as(CreateBookingRes.class);

        TestDataWriter.dataWriter(createBookingRes, "create_booking.json");
    }

    @And("the booiking id can not be empty or null")
    public void theBooikingIdCanNotBeEmptyOrNull() {
        Assertions.assertThat(createBookingRes.getBookingid()).isNotZero();
    }

    @And("the response details should match with entering details")
    public void theResponseDetailsShouldMatchWithEnteringDetails() {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(createBookingRes.getBooking().getFirstname()).isEqualTo(payload.getFirstname());
        softAssertions.assertThat(createBookingRes.getBooking().getLastname()).isEqualTo(payload.getLastname());
        softAssertions.assertThat(createBookingRes.getBooking().getTotalprice()).isEqualTo(payload.getTotalprice());
        softAssertions.assertThat(createBookingRes.getBooking().isDepositpaid()).isEqualTo(payload.isDepositpaid());
        softAssertions.assertThat(createBookingRes.getBooking().getBookingdates().getCheckin()).isEqualTo(payload.getBookingdates().getCheckin());
        softAssertions.assertThat(createBookingRes.getBooking().getBookingdates().getCheckout()).isEqualTo(payload.getBookingdates().getCheckout());
        softAssertions.assertThat(createBookingRes.getBooking().getAdditionalneeds()).isEqualTo(payload.getAdditionalneeds());

        softAssertions.assertAll();
    }

    @When("the user create a booking with sending request to invalid enpoint as {string} with following details:")
    public void theUserCreateABookingWithSendingRequestToInvalidEnpointAs(String endpoint, DataTable dataTable) {
        Map<String, String> customerDetail = dataTable.asMap(String.class, String.class);

        Bookingdates bookingdates = new Bookingdates(customerDetail.get("checkin"), customerDetail.get("checkout"));

        payload.setFirstname(customerDetail.get("firstname"));
        payload.setLastname(customerDetail.get("lastname"));
        payload.setTotalprice(Integer.parseInt(customerDetail.get("totalprice")));
        payload.setDepositpaid(Boolean.parseBoolean(customerDetail.get("depositpaid")));
        payload.setBookingdates(bookingdates);
        payload.setAdditionalneeds(customerDetail.get("additionalneeds")) ;

        response = APIUtils.sendPostRequest(request, endpoint, payload);
    }
}
