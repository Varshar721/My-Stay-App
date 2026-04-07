import java.util.*;

// Booking Request Class
class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Hotel System
class ConcurrentHotelSystem {

    private Map<String, Integer> inventory = new HashMap<>();
    private Queue<BookingRequest> bookingQueue = new LinkedList<>();

    public ConcurrentHotelSystem() {
        inventory.put("STANDARD", 3);
        inventory.put("DELUXE", 2);
        inventory.put("SUITE", 1);
    }

    // Add request to queue (synchronized)
    public synchronized void addRequest(BookingRequest request) {
        bookingQueue.add(request);
        System.out.println(request.guestName + " requested " + request.roomType);
    }

    // Process booking (critical section)
    public synchronized void processBooking() {
        if (bookingQueue.isEmpty()) {
            return;
        }

        BookingRequest request = bookingQueue.poll();

        if (!inventory.containsKey(request.roomType)) {
            System.out.println("Invalid room type for " + request.guestName);
            return;
        }

        int available = inventory.get(request.roomType);

        if (available > 0) {
            inventory.put(request.roomType, available - 1);
            System.out.println("Booking CONFIRMED for " + request.guestName +
                    " -> " + request.roomType);
        } else {
            System.out.println("Booking FAILED (No rooms) for " + request.guestName);
        }
    }

    public void showInventory() {
        System.out.println("\nFinal Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

// Worker Thread
class BookingProcessor extends Thread {

    private ConcurrentHotelSystem system;

    public BookingProcessor(ConcurrentHotelSystem system) {
        this.system = system;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            system.processBooking();
            try {
                Thread.sleep(100); // simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// MAIN CLASS (must match filename)
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        ConcurrentHotelSystem system = new ConcurrentHotelSystem();

        // Simulate multiple users adding requests
        system.addRequest(new BookingRequest("Alice", "DELUXE"));
        system.addRequest(new BookingRequest("Bob", "DELUXE"));
        system.addRequest(new BookingRequest("Charlie", "DELUXE")); // extra (should fail)

        system.addRequest(new BookingRequest("David", "STANDARD"));
        system.addRequest(new BookingRequest("Eve", "STANDARD"));

        // Multiple threads processing bookings
        BookingProcessor t1 = new BookingProcessor(system);
        BookingProcessor t2 = new BookingProcessor(system);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.showInventory();
    }
}