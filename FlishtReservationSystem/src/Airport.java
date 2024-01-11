public class Airport {
    private String airportCode;
    private String airportName;
    private String location; // City, Country, etc.
    // Other relevant fields like terminals, services, etc.
    
    // Constructor
    public Airport(String airportCode, String airportName, String location) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.location = location;
    }

    // Getters and Setters
    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Additional methods as required
}
