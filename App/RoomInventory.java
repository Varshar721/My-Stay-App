import java.util.HashMap;
import java.util.Map;

public class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Add room count to inventory
    public void addRooms(String roomType, int count) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + count);
    }

    // Get available room count
    public int getAvailableRooms(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Book one room if available
    public boolean bookRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }

        return false;
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("===== ROOM INVENTORY =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Available: " + entry.getValue());
        }
    }
}