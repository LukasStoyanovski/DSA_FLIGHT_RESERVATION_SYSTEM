import java.util.List;

public class main {
    public static void main(String[] args) {
        // Create instances of the system classes
        FlightRoutesGraph flightRoutesGraph = new FlightRoutesGraph();
        ReservationSystem reservationSystem = new ReservationSystem();

        // Adding a large number of airports
        int numberOfAirports = 100;
        for (int i = 1; i <= numberOfAirports; i++) {
            String airportCode = "AIRPORT" + i;
            String airportName = "Airport " + i;
            String location = "City " + i;
            Airport airport = new Airport(airportCode, airportName, location);
            flightRoutesGraph.addAirport(airport);
        }

        // Adding flight routes between these airports
        int numberOfRoutes = 500; 
        for (int i = 1; i <= numberOfRoutes; i++) {
            String source = "AIRPORT" + (i % numberOfAirports + 1); // To cycle through airports
            String destination = "AIRPORT" + ((i + 1) % numberOfAirports + 1); // To ensure different destination
            String timing = "08:00";
            double duration = 2.0;
            FlightRoute route = new FlightRoute(source, destination, timing, duration);
            flightRoutesGraph.addRoute(route);
        }

        // Making some reservations
        int numberOfReservations = 100; 
        for (int i = 1; i <= numberOfReservations; i++) {
            Passenger passenger = new Passenger("Passenger " + i, "P123" + i);
            
            List<FlightRoute> allRoutes = flightRoutesGraph.getAllRoutes();
            if (!allRoutes.isEmpty()) {
                FlightRoute route = allRoutes.get(i % allRoutes.size()); // Cycle through available routes
                reservationSystem.bookReservation(passenger, route);
            }
        }
        
        long startTime = System.nanoTime();
        
        // Testing scenarios
        // 1. Search for a direct flight between AAA and BBB
        FlightRoutesGraph.searchForFlight(flightRoutesGraph, "AIRPORT450", "AIRPORT451");

        // 2. Search for a connecting flight from AAA to CCC via BBB
        FlightRoutesGraph.searchForConnectingFlight(flightRoutesGraph, "AIRPORT998", "AIRPORT1000");

        // 3. Search for a non-existent flight or a flight without available seats
        FlightRoutesGraph.searchForFlight(flightRoutesGraph, "AIRPORT455", "AIRPORT15");

        // Performance Testing - tested in nanoseconds results are in the presentation and report.
        
	    // Call the method you want to test
	    long endTime = System.nanoTime();
	    System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        
    }

}
