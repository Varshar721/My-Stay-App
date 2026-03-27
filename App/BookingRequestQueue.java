import java.util.LinkedList;
import java.util.Queue;

public class BookingRequestQueue {
    private Queue<Reservation> bookingQueue;

    public BookingRequestQueue() {
        bookingQueue = new LinkedList<>();
    }

    // Add booking request to queue
    public void addBookingRequest(Reservation reservation) {
        bookingQueue.offer(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName() +
                           " (" + reservation.getRoomType() + ")");
    }

    // Display all queued requests in FIFO order
    public void displayBookingQueue() {
        System.out.println("\n===== BOOKING REQUEST QUEUE (FIFO ORDER) =====");

        if (bookingQueue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        int position = 1;
        for (Reservation reservation : bookingQueue) {
            System.out.println("Queue Position: " + position);
            reservation.displayReservationDetails();
            position++;
        }
    }
}