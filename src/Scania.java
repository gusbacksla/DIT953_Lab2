import java.awt.*;

public class Scania extends BedVehicles implements ILiftBed {

    LiftBed liftBed = new LiftBed();
    /**
     * Class constructor creates a car object then calls on the function stopEngine();
     *
     * @param nrDoors     :     Amount of doors the car has
     * @param color       :       Color of the car
     * @param enginePower : Strength of the engine
     * @param modelName   :   Name of the model
     */
    public Scania(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
        lowerRamp();
    }

    @Override
    public void setAngle(int angle) {
        if(!engineIsRunning) {
            liftBed.setAngle(angle);
            if (angle > 0) {
                liftRamp();
            }
            else {
                lowerRamp();
            }
        }
    }

    @Override
    public void setCargoHeight(int cargoHeight) {
        liftBed.setCargoHeight(cargoHeight);
    }

    @Override
    public int getAngle() {
        return liftBed.getAngle();
    }

    @Override
    public int getCargoHeight() {
        return liftBed.getCargoHeight();
    }

    @Override
    public void iterate() {
        if (isRampDown()) { super.iterate(); }
        //if (isMoving() && isRampUp()) System.out.println("Can't move while cargo is tilted.");
    }

    @Override
    public void startEngine() {
        if(liftBed.isRampUp()) { super.startEngine();}
    }
}
