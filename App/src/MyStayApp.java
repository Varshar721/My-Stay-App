import java.io.*;
import java.util.*;

// Booking class (Serializable)
class Booking implements Serializable {
    String bookingId;
    String roomType;

    public Booking(String bookingId, String roomType) {
        this.bookingId = bookingId;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return bookingId + " -> " + roomType;
    }
}

// Hotel System (Serializable)
class HotelSystem implements Serializable {

    Map<String, Integer> inventory = new HashMap<>();
    Map<String, Booking> bookings = new HashMap<>();

    public HotelSystem() {
        inventory.put("STANDARD", 5);
        inventory.put("DELUXE", 3);
        inventory.put("SUITE", 2);
    }

    public void bookRoom(String bookingId, String roomType) {
        if (!inventory.containsKey(roomType)) {
            System.out.println("Invalid room type.");
            return;
        }

        if (inventory.get(roomType) <= 0) {
            System.out.println("No rooms available.");
            return;
        }

        inventory.put(roomType, inventory.get(roomType) - 1);
        bookings.put(bookingId, new Booking(bookingId, roomType));

        System.out.println("Booked: " + bookingId);
    }

    public void showData() {
        System.out.println("\nInventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }

        System.out.println("\nBookings:");
        for (Booking b : bookings.values()) {
            System.out.println(b);
        }
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "hotel_data.ser";

    // Save to file
    public static void save(HotelSystem system) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(system);
            System.out.println("\nData saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load from file
    public static HotelSystem load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("Data loaded successfully.");
            return (HotelSystem) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data. Starting fresh.");
        }
        return new HotelSystem(); // fallback
    }
}

// MAIN CLASS
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        // Step 1: Load existing data
        HotelSystem system = PersistenceService.load();

        // Step 2: Show current state
        system.showData();

        // Step 3: Perform operations
        System.out.println("\n--- New Bookings ---");
        system.bookRoom("B201", "DELUXE");
        system.bookRoom("B202", "STANDARD");

        // Step 4: Show updated state
        system.showData();

        // Step 5: Save state before exit
        PersistenceService.save(system);

        System.out.println("\nSystem shutdown complete.");
    }
}
