import java.util.HashMap;
import java.util.Map;

public class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Add room count
    public void addRooms(String roomType, int count) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + count);
    }

    // Get available rooms
    public int getAvailableRooms(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Allocate one room if available
    public boolean allocateRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }

        return false;
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\n===== CURRENT ROOM INVENTORY =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Available: " + entry.getValue());
        }
    }
}