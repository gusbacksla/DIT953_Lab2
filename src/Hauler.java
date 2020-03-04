import java.awt.*;

public class Hauler extends BedVehicles implements IHaulerBed {

    HaulerBed hb = new HaulerBed();

    /**
     * Class constructor creates a car object then calls on the function stopEngine();
     *
     * @param nrDoors     :     Amount of doors the car has
     * @param color       :       Color of the car
     * @param enginePower : Strength of the engine
     * @param modelName   :   Name of the model
     */
    public Hauler(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    @Override
    public void loadVehicle(Vehicle vehicle) {
        if (isRampDown() && isClose(vehicle)) {
            if (!vehicle.isBig()) {

        hb.loadVehicle(vehicle, getPosition(), getDirection());

            } else System.out.println(vehicle.getModelName() + " Is To Big To Be Loaded.");
        } else System.out.println("Can't Load Vehicle.");
    }

    @Override
    public void unloadVehicle() {
        if (isRampDown()) {
        hb.unloadVehicle(getPosition(),getDirection());
        } else {
            System.out.println("Can't Unload Vehicle." + "\n");
        }
    }

    @Override
    public boolean isClose(Vehicle vehicle) {
        return hb.isClose(vehicle, getPosition());
    }
}
