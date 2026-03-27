public class UseCase6RoomAllocationService {
    public static void main(String[] args) {

        // Step 1: Create inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRooms("Single", 2);
        inventory.addRooms("Double", 1);
        inventory.addRooms("Suite", 1);

        // Step 2: Create booking request queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Step 3: Guests submit booking requests (FIFO order)
        Reservation request1 = new Reservation("Varsha", "Single");
        Reservation request2 = new Reservation("Rahul", "Double");
        Reservation request3 = new Reservation("Ananya", "Single");
        Reservation request4 = new Reservation("Kiran", "Suite");
        Reservation request5 = new Reservation("Priya", "Single"); // should fail (only 2 singles)

        System.out.println("Guests are submitting booking requests...\n");

        bookingQueue.addBookingRequest(request1);
        bookingQueue.addBookingRequest(request2);
        bookingQueue.addBookingRequest(request3);
        bookingQueue.addBookingRequest(request4);
        bookingQueue.addBookingRequest(request5);

        // Step 4: Display queue before processing
        bookingQueue.displayBookingQueue();

        // Step 5: Process queue and allocate rooms
        RoomAllocationService allocationService = new RoomAllocationService(inventory);
        allocationService.processBookingQueue(bookingQueue);

        // Step 6: Display final allocated room IDs
        allocationService.displayAllocatedRooms();

        // Step 7: Display final inventory after allocation
        inventory.displayInventory();
    }
}