enum SpotStatus { AVAILABLE, OCCUPIED }

class ParkingSpot {
    private SpotStatus status = SpotStatus.AVAILABLE;
    private String vehicleNumber = null;

    public void parkVehicle(String vehicle) {
        if (status == SpotStatus.OCCUPIED) {
            throw new IllegalStateException("Spot already occupied");
        }
        if (vehicle == null || vehicle.isBlank()) {
            throw new IllegalArgumentException("Invalid vehicle");
        }
        vehicleNumber = vehicle;
        status = SpotStatus.OCCUPIED;
    }

    public void show() {
        System.out.println("Status: " + status + ", Vehicle: " + vehicleNumber);
    }
}

public class Invariants {
    public static void main(String[] args) {
        ParkingSpot spot = new ParkingSpot();
        spot.show();
        spot.parkVehicle("TS09AB1234");
        spot.show();
        spot.parkVehicle("AP01XY9999");
    }
}