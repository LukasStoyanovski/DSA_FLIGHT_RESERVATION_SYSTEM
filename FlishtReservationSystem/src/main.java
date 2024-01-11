import java.util.List;

public class main {
    public static void main(String[] args) {
        // Create instances of the system classes
        FlightRoutesGraph flightRoutesGraph = new FlightRoutesGraph();
        ReservationSystem reservationSystem = new ReservationSystem();

        // Add airports
        Airport airportA = new Airport("SKG", "Thessaloniki International Airport Macedonia", "Thessaloniki");
        Airport airportB = new Airport("BER", "Berlin Brandenburg Airport", "Berlin");
        Airport airportC = new Airport("SKP", "Skopje International Airport", "Skopje");
        Airport airportD = new Airport("LHR", "Heathrow Airport", "London");
        Airport airportE = new Airport("FCO", "Leonardo da Vinci–Fiumicino Airport", "Rome");
        flightRoutesGraph.addAirport(airportA);
        flightRoutesGraph.addAirport(airportB);
        flightRoutesGraph.addAirport(airportC);
        flightRoutesGraph.addAirport(airportD);
        flightRoutesGraph.addAirport(airportE);

        // Add flight routes
        FlightRoute routeSKP_FCO = new FlightRoute("SKP", "FCO", "08:00", 1.0); // 1 hours duration
        FlightRoute routeFCO_SKG = new FlightRoute("FCO", "SKG", "10:00", 1.5); 
        FlightRoute routeSKG_LHR = new FlightRoute("SKG", "LHR", "09:00", 3.0); 
        FlightRoute routeLHR_BER = new FlightRoute("LHR", "BER", "15:00", 1.0);	
        flightRoutesGraph.addRoute(routeSKP_FCO);
        flightRoutesGraph.addRoute(routeFCO_SKG);
        flightRoutesGraph.addRoute(routeSKG_LHR);
        flightRoutesGraph.addRoute(routeLHR_BER);

        // Add reservations
        Passenger passenger1 = new Passenger("John Doe", "P123456");
        Passenger passenger2 = new Passenger("Jane Doe", "P654321");
        Passenger passenger3 = new Passenger("Jane Doae", "P654321");
        Passenger passenger4 = new Passenger("Jane Doe", "P6543d21");
        Passenger passenger5 = new Passenger("Jane Doae", "P65421a");
        Passenger passenger6 = new Passenger("Jane Doe", "P654321");
        Passenger passenger7 = new Passenger("Jane Doae", "P654321");
        Passenger passenger8 = new Passenger("Jane Doe", "P654321");
        Passenger passenger9 = new Passenger("Jane Doae", "P654321");
        Passenger passenger10 = new Passenger("Jane Doe", "P6543d21");
        Passenger passenger11 = new Passenger("Jane Doe", "P654321");
        Passenger passenger12 = new Passenger("Jane Doe", "P654322");
        reservationSystem.bookReservation(passenger1, routeSKP_FCO); 
        reservationSystem.bookReservation(passenger2, routeFCO_SKG); 
        reservationSystem.bookReservation(passenger3, routeFCO_SKG);
        reservationSystem.bookReservation(passenger4, routeFCO_SKG);
        reservationSystem.bookReservation(passenger5, routeFCO_SKG);
        reservationSystem.bookReservation(passenger6, routeFCO_SKG);
        reservationSystem.bookReservation(passenger7, routeFCO_SKG);
        reservationSystem.bookReservation(passenger8, routeFCO_SKG);
        reservationSystem.bookReservation(passenger9, routeFCO_SKG);
        reservationSystem.bookReservation(passenger10, routeFCO_SKG);
        reservationSystem.bookReservation(passenger11, routeFCO_SKG);
        reservationSystem.bookReservation(passenger12, routeFCO_SKG);

        // Testing scenarios
        // 1. Search for a direct flight between AAA and BBB
        searchForFlight(flightRoutesGraph, "SKG", "LHR");

        // 2. Search for a connecting flight from AAA to CCC via BBB
        searchForConnectingFlight(flightRoutesGraph, "SKP", "SKG");

        // 3. Search for a non-existent flight or a flight without available seats
        searchForFlight(flightRoutesGraph, "FCO", "SKG");

        // Word Recommendation - implement a method for this as needed

        // Performance Testing - use larger data sets and measure execution time
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
