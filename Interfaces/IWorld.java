import java.util.ArrayList;

public interface IWorld {
    void gas(double amount) throws Exception;

    void brake(double amount) throws Exception;

    void turboOn() throws Exception;

    void turboOff() throws Exception;

    void liftBed();

    void lowerBed();

    void stopAllCars();

    void startAllCars();

    void addCar();

    void removeCar();

    void addWorldListener(IWorldListener listener);

    void start();

    ArrayList<IVehicle> getVehicles();
}
