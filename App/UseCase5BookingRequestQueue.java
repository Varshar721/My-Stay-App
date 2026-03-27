public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {

        // Create booking request queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Guests submit booking requests
        Reservation request1 = new Reservation("Varsha", "Single");
        Reservation request2 = new Reservation("Rahul", "Double");
        Reservation request3 = new Reservation("Ananya", "Suite");

        System.out.println("Guests are submitting booking requests...\n");

        // Add requests in arrival order
        bookingQueue.addBookingRequest(request1);
        bookingQueue.addBookingRequest(request2);
        bookingQueue.addBookingRequest(request3);

        // Display queued requests in FIFO order
        bookingQueue.displayBookingQueue();

        // No inventory mutation happens here
        System.out.println("\nNo room allocation or inventory update occurs at this stage.");
        System.out.println("Requests are only collected and preserved in arrival order.");
    }
}