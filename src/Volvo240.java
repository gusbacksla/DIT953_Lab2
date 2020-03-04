import java.awt.*;

public class Volvo240 extends Vehicle {

    /** to be multiplied with speedFactor
     */
    double trimFactor = 1.25;


    public Volvo240(int nrDoors, Color color, double enginePower, String modelName){
        super(nrDoors, color, enginePower, modelName);
    }

    public double getTrimFactor(){
        return trimFactor;
    }

    public void setTrimFactor(double trimFactor){
        this.trimFactor = trimFactor;
    }



    /**  speedFactor()
     * Returns the enginePower*0.1 multiplied by the trimFactor of the car.
     * @return the enginePower*0.1 multiplied by the trimFactor.
     */
    @Override
    public double speedFactor() {
        return getEnginePower()* 0.01 * trimFactor;
    }

}