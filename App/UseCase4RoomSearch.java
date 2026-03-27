import java.util.HashMap;
import java.util.Map;

public class UseCase4RoomSearch {
    public static void main(String[] args) {

        // Create room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Create room catalog
        Map<String, Room> roomCatalog = new HashMap<>();
        roomCatalog.put("Single", singleRoom);
        roomCatalog.put("Double", doubleRoom);
        roomCatalog.put("Suite", suiteRoom);

        // Centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Add availability
        inventory.addRooms("Single", 3);
        inventory.addRooms("Double", 2);
        inventory.addRooms("Suite", 0); // unavailable, should not display

        // Create search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Guest searches for available rooms
        System.out.println("Guest is searching for available rooms...\n");
        searchService.displayAvailableRooms(roomCatalog);

        // Verify inventory remains unchanged
        System.out.println("\n===== INVENTORY AFTER SEARCH (UNCHANGED) =====");
        System.out.println("Single Rooms Available: " + inventory.getAvailableRooms("Single"));
        System.out.println("Double Rooms Available: " + inventory.getAvailableRooms("Double"));
        System.out.println("Suite Rooms Available: " + inventory.getAvailableRooms("Suite"));
    }
}