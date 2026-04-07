import java.util.HashMap;
import java.util.Map;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory Class
class HotelInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public HotelInventory() {
        rooms.put("STANDARD", 5);
        rooms.put("DELUXE", 3);
        rooms.put("SUITE", 2);
    }

    public void validateRoomType(String roomType) throws InvalidBookingException {
        if (!rooms.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
    }

    public void validateAvailability(String roomType, int count) throws InvalidBookingException {
        int available = rooms.get(roomType);

        if (count <= 0) {
            throw new InvalidBookingException("Booking count must be greater than zero.");
        }

        if (available < count) {
            throw new InvalidBookingException("Not enough rooms available. Available: " + available);
        }
    }

    public void bookRoom(String roomType, int count) throws InvalidBookingException {
        validateRoomType(roomType); // fail-fast
        validateAvailability(roomType, count);

        rooms.put(roomType, rooms.get(roomType) - count);

        System.out.println("Booking successful for " + count + " " + roomType + " room(s).");
    }

    public void displayInventory() {
        System.out.println("\nCurrent Room Availability:");
        for (Map.Entry<String, Integer> entry : rooms.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

// MAIN CLASS (matches filename BookMyStay.java)
public class BookMyStay {

    public static void main(String[] args) {
        HotelInventory inventory = new HotelInventory();

        try {
            inventory.displayInventory();

            System.out.println("\n--- Valid Booking ---");
            inventory.bookRoom("DELUXE", 2);

            System.out.println("\n--- Invalid Room Type ---");
            inventory.bookRoom("PRESIDENTIAL", 1);

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("\n--- Overbooking ---");
            inventory.bookRoom("SUITE", 5);
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("\n--- Invalid Count ---");
            inventory.bookRoom("STANDARD", 0);
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        inventory.displayInventory();
    }
}