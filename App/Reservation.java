public class Reservation {
    private String guestName;
    private String roomType;
    private String allocatedRoomId;
    private boolean confirmed;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.allocatedRoomId = null;
        this.confirmed = false;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getAllocatedRoomId() {
        return allocatedRoomId;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void confirmReservation(String roomId) {
        this.allocatedRoomId = roomId;
        this.confirmed = true;
    }

    public void displayReservationDetails() {
        System.out.println("Guest Name       : " + guestName);
        System.out.println("Requested Room   : " + roomType);
        if (confirmed) {
            System.out.println("Reservation      : CONFIRMED");
            System.out.println("Allocated RoomID : " + allocatedRoomId);
        } else {
            System.out.println("Reservation      : PENDING");
        }
        System.out.println("----------------------------------------");
    }
}