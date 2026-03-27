import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServicesMap;

    public AddOnServiceManager() {
        reservationServicesMap = new HashMap<>();
    }

    // Add service to a reservation
    public void addServiceToReservation(String reservationId, AddOnService service) {
        reservationServicesMap.putIfAbsent(reservationId, new ArrayList<>());
        reservationServicesMap.get(reservationId).add(service);

        System.out.println(service.getServiceName() + " added to Reservation ID " + reservationId);
    }

    // Display services for a reservation
    public void displayServicesForReservation(String reservationId) {
        System.out.println("\n===== ADD-ON SERVICES FOR RESERVATION " + reservationId + " =====");

        List<AddOnService> services = reservationServicesMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        for (AddOnService service : services) {
            service.displayServiceDetails();
        }
    }

    // Calculate total add-on cost
    public double calculateTotalAddOnCost(String reservationId) {
        List<AddOnService> services = reservationServicesMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (AddOnService service : services) {
            total += service.getServiceCost();
        }

        return total;
    }
}