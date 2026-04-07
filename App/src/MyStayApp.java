import java.util.*;

// Custom Exception
class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}

// Booking Class
class Booking {
    String bookingId;
    String roomType;
    String roomId;
    boolean isActive;

    public Booking(String bookingId, String roomType, String roomId) {
        this.bookingId = bookingId;
        this.roomType = roomType;
        this.roomId = roomId;
        this.isActive = true;
    }
}

// Hotel System
class HotelSystem {

    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Booking> bookings = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();

    public HotelSystem() {
        inventory.put("STANDARD", 5);
        inventory.put("DELUXE", 3);
        inventory.put("SUITE", 2);
    }

    // Booking method (to create sample data)
    public void bookRoom(String bookingId, String roomType) throws BookingException {

        if (!inventory.containsKey(roomType)) {
            throw new BookingException("Invalid room type.");
        }

        if (inventory.get(roomType) <= 0) {
            throw new BookingException("No rooms available.");
        }

        String roomId = roomType + "-" + (inventory.get(roomType));
        inventory.put(roomType, inventory.get(roomType) - 1);

        Booking booking = new Booking(bookingId, roomType, roomId);
        bookings.put(bookingId, booking);

        System.out.println("Booking confirmed: " + bookingId + " | Room: " + roomId);
    }

    // Cancellation with rollback
    public void cancelBooking(String bookingId) throws BookingException {

        // Validate booking existence
        if (!bookings.containsKey(bookingId)) {
            throw new BookingException("Booking does not exist.");
        }

        Booking booking = bookings.get(bookingId);

        // Prevent duplicate cancellation
        if (!booking.isActive) {
            throw new BookingException("Booking already cancelled.");
        }

        // Step 1: Push room ID into rollback stack
        rollbackStack.push(booking.roomId);

        // Step 2: Restore inventory
        inventory.put(booking.roomType, inventory.get(booking.roomType) + 1);

        // Step 3: Mark booking inactive
        booking.isActive = false;

        System.out.println("Booking cancelled: " + bookingId);
        System.out.println("Room released: " + booking.roomId);
    }

    public void showInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }

    public void showRollbackStack() {
        System.out.println("\nRollback Stack (LIFO): " + rollbackStack);
    }
}

// MAIN CLASS (must match filename)
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        HotelSystem system = new HotelSystem();

        try {
            system.bookRoom("B101", "DELUXE");
            system.bookRoom("B102", "STANDARD");

            system.showInventory();

            System.out.println("\n--- Cancelling Booking B101 ---");
            system.cancelBooking("B101");

            system.showInventory();
            system.showRollbackStack();

            System.out.println("\n--- Attempt Invalid Cancellation ---");
            system.cancelBooking("B999");

        } catch (BookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}