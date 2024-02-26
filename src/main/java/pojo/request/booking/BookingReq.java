package pojo.request.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingReq {
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private Bookingdates bookingdates;
	private String additionalneeds;
}
