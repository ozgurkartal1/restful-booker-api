package pojo.response.booking.create_booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pojo.response.booking.create_booking.Booking;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRes{
	private int bookingid;
	private Booking booking;
}
