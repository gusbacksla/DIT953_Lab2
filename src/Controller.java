import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements IUIListener, IWorldListener {
    private UI userInterface;
    private IWorld world;

    private int xMax = 900;
    private int yMax = 840;

    public Controller(ArrayList<IVehicle> vehicles) throws IOException, AWTException {
        world = new World(vehicles, xMax, yMax-240);
        world.addWorldListener(this);
        userInterface = new GUI(world.getVehicles(), xMax, yMax, "CarSim 1.1");
        userInterface.update();
        userInterface.addUIListener(this);
        world.start();
    }

    @Override
    public void event(IUIEvent event) throws Exception {
        switch (event.getEvent()) {
            case ("gas") : {
                world.gas(event.getValue());
                break;
            }
            case ("brake") : {
                world.brake(event.getValue());
                break;
            }
            case ("turboOn") : {
                world.turboOn();
                break;
            }
            case ("turboOff") : {
                world.turboOff();
                break;
            }
            case ("liftBed") : {
                world.liftBed();
                break;
            }
            case ("lowerBed") : {
                world.lowerBed();
                break;
            }
            case ("startCars") : {
                world.startAllCars();
                break;
            }
            case ("stopCars") : {
                world.stopAllCars();
                break;
            }
            case ("addCar") : {

                world.stopAllCars();
                world.addCar();
                userInterface.rebuildVehicleList(world.getVehicles());

                break;
            }
            case ("deleteCar") : {

                world.stopAllCars();
                world.removeCar();
                userInterface.rebuildVehicleList(world.getVehicles());
                break;
            }
            case ("newUI") : {
                userInterface.dispose();
                userInterface = new GUI(world.getVehicles(), xMax, yMax, "UI has been changed");
                userInterface.update();
                userInterface.addUIListener(this);
            }
        }
        //System.out.println("Event '" + event.getEvent() + "' with value: " + event.getValue());
    }

    @Override
    public void event(IWorldEvent event) {
        switch (event.getEvent()){
            case ("updated") : {
                userInterface.update();
                break;
            }
        }
    }
}
