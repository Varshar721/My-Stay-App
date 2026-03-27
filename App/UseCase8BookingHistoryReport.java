public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {

        // Step 1: Create confirmed reservations
        Reservation reservation1 = new Reservation("RES201", "Varsha", "Single");
        reservation1.confirmReservation("S101");

        Reservation reservation2 = new Reservation("RES202", "Rahul", "Double");
        reservation2.confirmReservation("D205");

        Reservation reservation3 = new Reservation("RES203", "Priya", "Suite");
        reservation3.confirmReservation("SU301");

        // Step 2: Create booking history
        BookingHistory bookingHistory = new BookingHistory();

        // Step 3: Add confirmed reservations to history in insertion order
        bookingHistory.addConfirmedReservation(reservation1);
        bookingHistory.addConfirmedReservation(reservation2);
        bookingHistory.addConfirmedReservation(reservation3);

        // Step 4: Admin reviews booking history
        bookingHistory.displayBookingHistory();

        // Step 5: Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateSummaryReport(bookingHistory);
    }
}