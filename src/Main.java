import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, AWTException {
        ArrayList<IVehicle> vehicles = new ArrayList<>();

        Volvo240 volvo = new Volvo240(5, Color.BLUE, 100, "Volvo240");
        Saab95 saab = new Saab95(5, Color.RED, 125, "Saab95");
        Scania scania = new Scania(2,Color.BLACK, 90,"Scania");

        vehicles.add(volvo);
        vehicles.add(saab);
        vehicles.add(scania);


        final Controller controller = new Controller(vehicles);
        controller.start();
    }
}
