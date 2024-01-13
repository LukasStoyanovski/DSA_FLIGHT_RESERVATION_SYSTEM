import java.util.*;

public class FlightRoutesGraph {
    private Map<String, List<FlightRoute>> routes;
    private Map<String, Airport> airports;

    public FlightRoutesGraph() {
        routes = new HashMap<>();;
        airports = new HashMap<>();
    }

    // Method to delete a flight route
    public void deleteRoute(FlightRoute route) {
        List<FlightRoute> sourceRoutes = routes.get(route.getSourceAirport());
        if (sourceRoutes != null) {
            sourceRoutes.removeIf(r -> r.getDestinationAirport().equals(route.getDestinationAirport()));
        }
    }

    // Method to update route details
    public void updateRoute(FlightRoute oldRoute, FlightRoute newRoute) {
        deleteRoute(oldRoute);
        addRoute(newRoute);
    }

    // Method to check for duplicate routes
    public boolean isDuplicateRoute(FlightRoute route) {
        List<FlightRoute> sourceRoutes = routes.get(route.getSourceAirport());
        if (sourceRoutes != null) {
            return sourceRoutes.stream().anyMatch(r -> r.getDestinationAirport().equals(route.getDestinationAirport()));
        }
        return false;
    }

    // Method to add an airport
    public void addAirport(Airport airport) {
        if (!airports.containsKey(airport.getAirportCode())) {
            airports.put(airport.getAirportCode(), airport);
        }
    }

    // Method to remove an airport
    public void removeAirport(String airportCode) {
        if (airports.containsKey(airportCode)) {
            airports.remove(airportCode);
            routes.remove(airportCode); // Remove associated routes
        }
    }

    // Method to add a flight route
    public void addRoute(FlightRoute route) {
        if (airports.containsKey(route.getSourceAirport()) && airports.containsKey(route.getDestinationAirport())) {
            routes.putIfAbsent(route.getSourceAirport(), new ArrayList<>());
            routes.get(route.getSourceAirport()).add(route);
        }
    }
    
    // Method to get all routes from a specific airport
    public List<FlightRoute> getRoutesFrom(String airportCode) {
        return routes.getOrDefault(airportCode, new ArrayList<>());
    }
    
    //getNumberOfBookings method simply retrieves info from FlightRoute Object
    public static int getNumberOfBookings(FlightRoute route) {
        return route.getNumberOfBookings();
    }
    
    public List<FlightRoute> getAllRoutes() {
        List<FlightRoute> allRoutes = new ArrayList<>();
        for (List<FlightRoute> routeList : this.routes.values()) {
            allRoutes.addAll(routeList);
        }
        return allRoutes;
    }
    
    
    public static void searchForFlight(FlightRoutesGraph flightRoutesGraph, String source, String destination) {
        List<FlightRoute> routes = flightRoutesGraph.getRoutesFrom(source);
        boolean flightFound = false;

        for (FlightRoute route : routes) {
            if (route.getDestinationAirport().equals(destination)) {
                // Assuming a method getNumberOfBookings() returns the number of bookings for this route
                int bookings = getNumberOfBookings(route); 
                if (bookings < 10) {
                    System.out.println("Flight found from " + source + " to " + destination + " with available seats.");
                    flightFound = true;
                    break;
                }
            }
        }

        if (!flightFound) {
            System.out.println("No available direct flight found from " + source + " to " + destination + ".");
        }
    }
    
    public static void searchForConnectingFlight(FlightRoutesGraph flightRoutesGraph, String source, String destination) {
        // Get all routes from the source
        List<FlightRoute> sourceRoutes = flightRoutesGraph.getRoutesFrom(source);
        boolean connectingFlightFound = false;

        for (FlightRoute sourceRoute : sourceRoutes) {
            String midPoint = sourceRoute.getDestinationAirport();
            // Check if there's a route from the midpoint to the final destination
            List<FlightRoute> midPointRoutes = flightRoutesGraph.getRoutesFrom(midPoint);
            for (FlightRoute midPointRoute : midPointRoutes) {
                if (midPointRoute.getDestinationAirport().equals(destination)) {
                    int bookings1 = getNumberOfBookings(sourceRoute); 
                    int bookings2 = getNumberOfBookings(midPointRoute); 
                    if (bookings1 < 10 && bookings2 < 10) {
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

    

}
