import java.util.*;

class Waypoint {

    private final Map<CustomerId, Customer> customers =
            new HashMap<>();

    private final Map<ParkingSpotId, ParkingSpot> spots =
            new HashMap<>();

    private final Map<BookingId, Booking> bookings =
            new HashMap<>();

    private final Set<String> registeredVehicles =
            new HashSet<>();

    private int bookingCounter = 1;
    private int paymentCounter = 1;

    public void registerCustomer(Customer customer) {

        customers.put(
                customer.getCustomerId(),
                customer
        );
    }

    public void addParkingSpot(ParkingSpot spot) {

        spots.put(
                spot.getSnapshot().spotId(),
                spot
        );
    }

    public void registerVehicle(String vehicleNumber) {

        registeredVehicles.add(
                vehicleNumber.toUpperCase()
        );
    }

    public List<ParkingSpotView> getParkingSpots() {

        List<ParkingSpotView> list =
                new ArrayList<>();

        for (ParkingSpot spot : spots.values()) {
            list.add(
                    spot.getSnapshot()
            );
        }

        return list;
    }

    public Booking reserve(
            CustomerId customerId,
            ParkingSpotId spotId,
            String vehicleNumber,
            int hours,
            Money hourlyRate) {

        ParkingSpot spot = spots.get(spotId);

        if (spot == null)
            throw new IllegalArgumentException(
                    "Spot not found");

        if (!spot.isAvailable())
            throw new IllegalStateException(
                    "Parking spot unavailable");

        BookingId bookingId =
                new BookingId(bookingCounter++);

        PaymentId paymentId =
                new PaymentId(paymentCounter++);

        Money totalFare =
                hourlyRate.times(hours);

        spot.parkVehicle(
                vehicleNumber,
                bookingId
        );

        Booking booking =
                new Booking(
                        bookingId,
                        customerId,
                        spotId,
                        vehicleNumber,
                        totalFare
                );

        Payment payment =
                new Payment(
                        paymentId,
                        bookingId,
                        totalFare,
                        true
                );

        booking.attachPayment(payment);

        bookings.put(
                bookingId,
                booking
        );

        return booking;
    }

    public void cancelBooking(
            BookingId bookingId) {

        Booking booking =
                bookings.get(bookingId);

        booking.cancel();

        ParkingSpot spot =
                spots.get(
                        booking.getSpotId()
                );

        spot.releaseSpot(bookingId);
    }

    public void displayBookings() {

        System.out.println(
                "\n----- BOOKING DETAILS -----"
        );

        for (Booking booking :
                bookings.values()) {

            System.out.println(
                    "Booking ID: "
                            + booking.getBookingId().value()
            );

            System.out.println(
                    "Customer ID: "
                            + booking.getCustomerId().value()
            );

            System.out.println(
                    "Spot ID: "
                            + booking.getSpotId().value()
            );

            System.out.println(
                    "Vehicle: "
                            + booking.getVehicleNumber()
            );

            System.out.println(
                    "Fare: "
                            + booking.getFare()
            );

            System.out.println(
                    "Status: "
                            + booking.getStatus()
            );

            System.out.println(
                    "---------------------"
            );
        }
    }
}