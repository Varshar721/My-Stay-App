public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {

        // Step 1: Create an existing confirmed reservation
        Reservation reservation = new Reservation("RES101", "Varsha", "Double");
        reservation.confirmReservation("D001");

        System.out.println("===== EXISTING RESERVATION DETAILS =====");
        reservation.displayReservationDetails();

        // Step 2: Create add-on service manager
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Step 3: Guest selects add-on services
        AddOnService breakfast = new AddOnService("Breakfast", 500.0);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 1200.0);
        AddOnService spaAccess = new AddOnService("Spa Access", 2000.0);

        System.out.println("\nGuest is selecting add-on services...\n");

        serviceManager.addServiceToReservation(reservation.getReservationId(), breakfast);
        serviceManager.addServiceToReservation(reservation.getReservationId(), airportPickup);
        serviceManager.addServiceToReservation(reservation.getReservationId(), spaAccess);

        // Step 4: Display selected services
        serviceManager.displayServicesForReservation(reservation.getReservationId());

        // Step 5: Calculate total additional cost
        double totalAddOnCost = serviceManager.calculateTotalAddOnCost(reservation.getReservationId());

        System.out.println("Total Additional Cost for Reservation " +
                           reservation.getReservationId() + " : Rs." + totalAddOnCost);

        // Step 6: Core booking remains unchanged
        System.out.println("\nCore booking and inventory state remain unchanged.");
    }
}