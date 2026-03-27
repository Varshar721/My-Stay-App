public class UseCase2RoomInitialization {
    public static void main(String[] args) {

        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        System.out.println("========================================");
        System.out.println("     BOOK MY STAY APP - USE CASE 2.0    ");
        System.out.println(" Basic Room Types & Static Availability ");
        System.out.println("========================================\n");

        System.out.println("Room 1 Details:");
        singleRoom.displayRoomDetails();
        System.out.println("Availability    : " + singleRoomAvailability + " rooms available");
        System.out.println("----------------------------------------");

        System.out.println("Room 2 Details:");
        doubleRoom.displayRoomDetails();
        System.out.println("Availability    : " + doubleRoomAvailability + " rooms available");
        System.out.println("----------------------------------------");

        System.out.println("Room 3 Details:");
        suiteRoom.displayRoomDetails();
        System.out.println("Availability    : " + suiteRoomAvailability + " rooms available");
        System.out.println("----------------------------------------");

        System.out.println("Application terminated successfully.");
    }
}