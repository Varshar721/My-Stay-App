public class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private String allocatedRoomId;
    private boolean confirmed;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.allocatedRoomId = null;
        this.confirmed = false;
    }

    public String getReservationId() {
        return reservationId;
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
        System.out.println("Reservation ID   : " + reservationId);
        System.out.println("Guest Name       : " + guestName);
        System.out.println("Room Type        : " + roomType);

        if (confirmed) {
            System.out.println("Reservation      : CONFIRMED");
            System.out.println("Allocated RoomID : " + allocatedRoomId);
        } else {
            System.out.println("Reservation      : PENDING");
        }

        System.out.println("----------------------------------------");
    }
}