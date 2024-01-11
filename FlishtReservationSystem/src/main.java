import java.util.List;

public class main {
    public static void main(String[] args) {
        // Create instances of the system classes
        FlightRoutesGraph flightRoutesGraph = new FlightRoutesGraph();
        ReservationSystem reservationSystem = new ReservationSystem();

     // Adding a large number of airports
        int numberOfAirports = 10000; // for example, 100 airports
        for (int i = 1; i <= numberOfAirports; i++) {
            String airportCode = "AIRPORT" + i;
            String airportName = "Airport " + i;
            String location = "City " + i;
            Airport airport = new Airport(airportCode, airportName, location);
            flightRoutesGraph.addAirport(airport);
        }

        // Adding flight routes between these airports
        int numberOfRoutes = 50000; // for example, 500 routes
        for (int i = 1; i <= numberOfRoutes; i++) {
            String source = "AIRPORT" + (i % numberOfAirports + 1); // To cycle through airports
            String destination = "AIRPORT" + ((i + 1) % numberOfAirports + 1); // To ensure different destination
            String timing = "08:00";
            double duration = 2.0;
            FlightRoute route = new FlightRoute(source, destination, timing, duration);
            flightRoutesGraph.addRoute(route);
        }

        // Making some reservations
        int numberOfReservations = 10000; // for example, 100 reservations
        for (int i = 1; i <= numberOfReservations; i++) {
            Passenger passenger = new Passenger("Passenger " + i, "P123" + i);
            // Assuming routes are stored in a list or similar collection
            List<FlightRoute> allRoutes = flightRoutesGraph.getAllRoutes();
            if (!allRoutes.isEmpty()) {
                FlightRoute route = allRoutes.get(i % allRoutes.size()); // Cycle through available routes
                reservationSystem.bookReservation(passenger, route);
            }
        }
        
        // Testing scenarios
        // 1. Search for a direct flight between AAA and BBB
        searchForFlight(flightRoutesGraph, "AIRPORT450", "AIRPORT451");

        // 2. Search for a connecting flight from AAA to CCC via BBB
        searchForConnectingFlight(flightRoutesGraph, "AIRPORT998", "AIRPORT1000");

        // 3. Search for a non-existent flight or a flight without available seats
        searchForFlight(flightRoutesGraph, "AIRPORT455", "AIRPORT15");

        // Word Recommendation - implement a method for this as needed

        // Performance Testing - use larger data sets and measure execution time
        long startTime = System.nanoTime();
	    // Call the method you want to test
	    long endTime = System.nanoTime();
	    System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");

        
    }
        
    // Methods for searchForFlight, searchForConnectingFlight, etc.
    public static void searchForFlight(FlightRoutesGraph flightRoutesGraph, String source, String destination) {
        List<FlightRoute> routes = flightRoutesGraph.getRoutesFrom(source);
        boolean flightFound = false;

        for (FlightRoute route : routes) {
            if (route.getDestinationAirport().equals(destination) && route.getNumberOfBookings() < 10) {
                System.out.println("Direct flight found from " + source + " to " + destination + " with available seats.");
                flightFound = true;
                break;
            }
        }

        if (!flightFound) {
            System.out.println("No available direct flight found from " + source + " to " + destination + ".");
        }
    }

    
    
    public static void searchForConnectingFlight(FlightRoutesGraph flightRoutesGraph, String source, String destination) {
        List<FlightRoute> sourceRoutes = flightRoutesGraph.getRoutesFrom(source);
        boolean connectingFlightFound = false;

        for (FlightRoute sourceRoute : sourceRoutes) {
            String midPoint = sourceRoute.getDestinationAirport();
            List<FlightRoute> midPointRoutes = flightRoutesGraph.getRoutesFrom(midPoint);

            for (FlightRoute midPointRoute : midPointRoutes) {
                if (midPointRoute.getDestinationAirport().equals(destination)) {
                    if (sourceRoute.getNumberOfBookings() < 10 && midPointRoute.getNumberOfBookings() < 10) {
                        System.out.println("Connecting flight found from " + source + " via " + midPoint + " to " + destination + " with available seats.");
                        connectingFlightFound = true;
                        break;
                    }
                }
            }
            if (connectingFlightFound) {
                break;
            }
        }

        if (!connectingFlightFound) {
            System.out.println("No available connecting flight found from " + source + " to " + destination + ".");
        }
    }

    // Implementation depends on requirements
}
