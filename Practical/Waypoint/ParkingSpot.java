import java.util.Objects;

class ParkingSpot {

    private final ParkingSpotId spotId;
    private final String location;

    private SpotStatus status;
    private String vehicleNumber;
    private BookingId currentBookingId;

    public ParkingSpot(ParkingSpotId spotId, String location) {
        if (spotId == null || location == null || location.isBlank())
            throw new IllegalArgumentException("Invalid parking spot");

        this.spotId = spotId;
        this.location = location;
        this.status = SpotStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return status == SpotStatus.AVAILABLE;
    }

    public void parkVehicle(String vehicleNumber, BookingId bookingId) {
        if (!isAvailable())
            throw new IllegalStateException("Spot already occupied");

        this.vehicleNumber = vehicleNumber;
        this.currentBookingId = bookingId;
        this.status = SpotStatus.OCCUPIED;
    }

    public void releaseSpot(BookingId bookingId) {
        if (isAvailable() || !Objects.equals(currentBookingId, bookingId))
            throw new IllegalStateException("Booking does not own this spot");

        vehicleNumber = null;
        currentBookingId = null;
        status = SpotStatus.AVAILABLE;
    }

    public ParkingSpotView getSnapshot() {
        return new ParkingSpotView(
                spotId,
                location,
                status,
                vehicleNumber
        );
    }
}