sealed class ParkingSpot
        permits CarParkingSpot, BikeParkingSpot, ElectricParkingSpot {
    public void display() {
        System.out.println("Parking spot");
    }
}

final class CarParkingSpot extends ParkingSpot {
    public void display() {
        System.out.println("Car Parking Spot");
    }
}

final class BikeParkingSpot extends ParkingSpot {
    public void display() {
        System.out.println("Bike Parking Spot");
    }
}

final class ElectricParkingSpot extends ParkingSpot {
    public void display() {
        System.out.println("Electric Vehicle Parking Spot");
    }
}

public class SealedDemo {
    public static void main(String[] args) {
        ParkingSpot car = new CarParkingSpot();
        ParkingSpot bike = new BikeParkingSpot();
        ParkingSpot electric = new ElectricParkingSpot();

        System.out.println("=== Parking Spot Details ===");
        car.display();
        bike.display();
        electric.display();
    }
}