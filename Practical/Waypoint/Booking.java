class Booking {

    private final BookingId bookingId;
    private final CustomerId customerId;
    private final ParkingSpotId spotId;
    private final String vehicleNumber;
    private final Money fare;

    private BookingStatus status;
    private Payment payment;

    public Booking(
            BookingId bookingId,
            CustomerId customerId,
            ParkingSpotId spotId,
            String vehicleNumber,
            Money fare) {

        this.bookingId = bookingId;
        this.customerId = customerId;
        this.spotId = spotId;
        this.vehicleNumber = vehicleNumber;
        this.fare = fare;

        status = BookingStatus.PENDING;
    }

    public void attachPayment(Payment payment) {

        if (payment == null || !payment.successful())
            throw new IllegalArgumentException("Payment failed");

        this.payment = payment;
        status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        status = BookingStatus.CANCELLED;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public ParkingSpotId getSpotId() {
        return spotId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Money getFare() {
        return fare;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Payment getPayment() {
        return payment;
    }
}