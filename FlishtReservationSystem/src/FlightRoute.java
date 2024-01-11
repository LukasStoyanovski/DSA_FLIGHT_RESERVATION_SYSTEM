public class FlightRoute {
    private String sourceAirport;
    private String destinationAirport;
    private String timing;
    private double duration;
    private int numberOfBookings;

    // Constructor
    public FlightRoute(String source, String destination, String timing, double duration) {
        this.sourceAirport = source;
        this.destinationAirport = destination;
        this.timing = timing;
        this.duration = duration;
        this.numberOfBookings = 0;
    }

    // Getters and Setters for each field
    public String getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(String sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    // Book a seat on this flight
    public boolean bookSeat() {
        if (numberOfBookings < 10) { // Assuming each flight has 10 seats
            numberOfBookings++;
            return true;
        }
        return false;
    }
    
    // Cancel a seat booking
    public void cancelSeat() {
        if (numberOfBookings > 0) {
            numberOfBookings--;
        }
    }

    // Getters
    public int getNumberOfBookings() {
        return numberOfBookings;
    }
}
