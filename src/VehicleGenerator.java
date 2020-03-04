import java.awt.*;
import java.util.Random;

public class VehicleGenerator implements IVehicleGenerator{
    private final static Random rand = new Random();
    @Override
    public IVehicle getRandomVehicle() {
        switch (rand.nextInt(3)){
            case (0) : {
                return getSaab95();
            }
            case (1) : {
                return getVolvo240();
            }
            case (2) : {
                return getScania();
            }
        }
        return null;
    }

    private Saab95 getSaab95(){
        return new Saab95(5, Color.RED, rand.nextInt(125) + 125, "Saab95");
    }

    private Volvo240 getVolvo240(){
        return new Volvo240(5, Color.BLUE, rand.nextInt(250) + 75, "Volvo240");
    }

    private Scania getScania(){
        return new Scania(2,Color.BLACK, rand.nextInt(650) + 50,"Scania");
    }
}
