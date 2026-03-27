import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoomAllocationService {

    private RoomInventory inventory;
    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> allocatedRoomsByType;
    private Map<String, Integer> roomCounters;

    public RoomAllocationService(RoomInventory inventory) {
        this.inventory = inventory;
        this.allocatedRoomIds = new HashSet<>();
        this.allocatedRoomsByType = new HashMap<>();
        this.roomCounters = new HashMap<>();
    }

    // Process next booking request from queue
    public void processBookingQueue(BookingRequestQueue bookingQueue) {
        System.out.println("\n===== PROCESSING BOOKING REQUESTS =====");

        while (!bookingQueue.isEmpty()) {
            Reservation reservation = bookingQueue.getNextBookingRequest();

            if (reservation != null) {
                confirmReservation(reservation);
            }
        }
    }

    // Confirm reservation and allocate room
    public void confirmReservation(Reservation reservation) {
        String roomType = reservation.getRoomType();

        System.out.println("\nProcessing request for " + reservation.getGuestName() +
                           " (" + roomType + ")");

        // Check availability first
        if (inventory.getAvailableRooms(roomType) <= 0) {
            System.out.println("Reservation FAILED: No " + roomType + " rooms available.");
            return;
        }

        // Generate unique room ID
        String roomId = generateUniqueRoomId(roomType);

        // Allocate room in inventory immediately
        boolean allocated = inventory.allocateRoom(roomType);

        if (allocated) {
            // Confirm reservation
            reservation.confirmReservation(roomId);

            // Track allocated room IDs globally
            allocatedRoomIds.add(roomId);

            // Track allocated room IDs by room type
            allocatedRoomsByType.putIfAbsent(roomType, new HashSet<>());
            allocatedRoomsByType.get(roomType).add(roomId);

            System.out.println("Reservation CONFIRMED for " + reservation.getGuestName());
            System.out.println("Allocated Room ID: " + roomId);
            System.out.println("Remaining " + roomType + " Rooms: " + inventory.getAvailableRooms(roomType));
        } else {
            System.out.println("Reservation FAILED during allocation.");
        }
    }

    // Generate unique room ID
    private String generateUniqueRoomId(String roomType) {
        String prefix;

        switch (roomType.toLowerCase()) {
            case "single":
                prefix = "S";
                break;
            case "double":
                prefix = "D";
                break;
            case "suite":
                prefix = "SU";
                break;
            default:
                prefix = "R";
        }

        roomCounters.put(roomType, roomCounters.getOrDefault(roomType, 0) + 1);

        String roomId;
        do {
            int count = roomCounters.get(roomType);
            roomId = prefix + String.format("%03d", count);

            if (!allocatedRoomIds.contains(roomId)) {
                break;
            }

            roomCounters.put(roomType, count + 1);

        } while (true);

        return roomId;
    }

    // Display allocated rooms grouped by room type
    public void displayAllocatedRooms() {
        System.out.println("\n===== ALLOCATED ROOM IDS BY TYPE =====");

        if (allocatedRoomsByType.isEmpty()) {
            System.out.println("No rooms allocated yet.");
            return;
        }

        for (Map.Entry<String, Set<String>> entry : allocatedRoomsByType.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}