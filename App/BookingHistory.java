import java.util.ArrayList;
import java.util.List;

public class BookingHistory {
    private List<Reservation> confirmedBookings;

    public BookingHistory() {
        confirmedBookings = new ArrayList<>();
    }

    // Add only confirmed reservations to history
    public void addConfirmedReservation(Reservation reservation) {
        if (reservation != null && reservation.isConfirmed()) {
            confirmedBookings.add(reservation);
            System.out.println("Reservation " + reservation.getReservationId() + " added to booking history.");
        } else {
            System.out.println("Only confirmed reservations can be added to booking history.");
        }
    }

    // Return stored history
    public List<Reservation> getConfirmedBookings() {
        return confirmedBookings;
    }

    // Display all confirmed reservations in insertion order
    public void displayBookingHistory() {
        System.out.println("\n===== BOOKING HISTORY =====");

        if (confirmedBookings.isEmpty()) {
            System.out.println("No confirmed bookings found.");
            return;
        }

        for (Reservation reservation : confirmedBookings) {
            reservation.displayReservationDetails();
        }
    }
}