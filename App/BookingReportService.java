import java.util.List;

public class BookingReportService {

    // Generate summary report from booking history
    public void generateSummaryReport(BookingHistory bookingHistory) {
        System.out.println("\n===== BOOKING SUMMARY REPORT =====");

        List<Reservation> reservations = bookingHistory.getConfirmedBookings();

        if (reservations.isEmpty()) {
            System.out.println("No bookings available for reporting.");
            return;
        }

        int totalBookings = reservations.size();

        System.out.println("Total Confirmed Bookings : " + totalBookings);

        System.out.println("\nReservation Summary:");
        for (Reservation reservation : reservations) {
            System.out.println("Reservation ID : " + reservation.getReservationId()
                    + " | Guest Name : " + reservation.getGuestName()
                    + " | Room Type : " + reservation.getRoomType()
                    + " | Room ID : " + reservation.getAllocatedRoomId());
        }

        System.out.println("----------------------------------------");
        System.out.println("Report generated successfully without modifying booking history.");
    }
}