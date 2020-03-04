import java.awt.*;

public abstract class BedVehicles extends Vehicle implements IBed{
    Bed bed = new Bed();

    /**
     * Class constructor creates a car object then calls on the function stopEngine();
     *
     * @param nrDoors     :     Amount of doors the car has
     * @param color       :       Color of the car
     * @param enginePower : Strength of the engine
     * @param modelName   :   Name of the model
     */
    public BedVehicles(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    @Override
    public boolean isRampUp() { return bed.isRampUp(); }

    @Override
    public boolean isRampDown() { return bed.isRampDown(); }

    @Override
    public void lowerRamp() {
        if(!engineIsRunning) {
           bed.lowerRamp();
        }
        else { System.out.println("Can't Lower Ramp While The Vehicle Is In Motion."); }
    }

    @Override
    public void liftRamp() {
        if(!engineIsRunning) {
            bed.liftRamp();
        }
        else { System.out.println("Can't Lift Ramp While The Vehicle Is In Motion."); }
    }

    @Override
    double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    public void startEngine() {
        if(isRampDown()) { super.startEngine(); }
    }
}
