import java.util.*;

public class ReservationSystem {
    private List<Reservation> reservations;

    public ReservationSystem() {
        reservations = new ArrayList<>();
    }

    // Book a reservation
    public Reservation bookReservation(Passenger passenger, FlightRoute flightRoute) {
        if (flightRoute.bookSeat()) {
            Reservation reservation = new Reservation(passenger, flightRoute);
            reservations.add(reservation);
            return reservation;
        }
        return null; // Indicates flight is full
    }

    // Cancel a reservation
    public boolean cancelReservation(String reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservationId) && reservation.isActive()) {
                reservation.getFlightRoute().cancelSeat();
                reservation.cancel();
                return true;
            }
        }
        return false;
    }

    // Method to modify an existing reservation
    public boolean modifyReservation(String reservationId, FlightRoute newRoute) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservationId) && reservation.isActive()) {
                reservation.setFlightRoute(newRoute);
                return true;
            }
        }
        return false;
    }

}
