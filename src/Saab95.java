
import java.awt.*;

public class Saab95 extends Vehicle implements ITurbo {

    /** to be multiplied with the speedFactor
     */
    private final Turbo turbo = new Turbo(1.5);

    /**
     * public Saab95()
     * X and y coordinates added and set to 0 as starting point.
     * Direction set to 0. Direction is in degrees so 0 corresponds to moving right on the screen
     */
    public Saab95(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    public void setTurboOn() {
        if(!turbo.isOn()) {
            turbo.setTurboOn();
            setCurrentSpeed(getCurrentSpeed() * turbo.getTurbo());
        }
    }

    public void setTurboOff() {
        if(turbo.isOn()){
            setCurrentSpeed(getCurrentSpeed()/turbo.getTurbo());
            turbo.setTurboOff();
        }
    }

    /**  speedFactor()
     * Returns the enginePower*0.1 multiplied by the turbo of the car.
     * @return enginePower*0.1 multiplied by turbo.
     */
    @Override
    public double speedFactor() {
        return getEnginePower()* 0.01 * turbo.getTurbo();
    }
}