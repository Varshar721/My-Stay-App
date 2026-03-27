import java.util.Map;

public class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void displayAvailableRooms(Map<String, Room> roomCatalog) {
        System.out.println("===== AVAILABLE ROOMS =====");

        boolean found = false;

        for (Map.Entry<String, Room> entry : roomCatalog.entrySet()) {
            String roomType = entry.getKey();
            Room room = entry.getValue();

            int availableCount = inventory.getAvailableRooms(roomType);

            if (availableCount > 0) {
                found = true;
                System.out.println();
                room.displayRoomDetails(availableCount);
            }
        }

        if (!found) {
            System.out.println("No rooms are currently available.");
        }
    }
}