public class Reservation {
    private Passenger passenger;
    private FlightRoute flightRoute;
    private String reservationId;
    private boolean isActive;

    // Constructor
    public Reservation(Passenger passenger, FlightRoute flightRoute) {
        this.passenger = passenger;
        this.flightRoute = flightRoute;
        this.isActive = true;
    }  

    public boolean isActive() {
		return isActive;
	}

	public void setFlightRoute(FlightRoute flightRoute) {
		this.flightRoute = flightRoute;
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	
    // Method to get the flight route associated with this reservation
    public FlightRoute getFlightRoute() {
        return flightRoute;
    }
    
	// Method to cancel the reservation
    public void cancel() {
        this.isActive = false;
    }
}
