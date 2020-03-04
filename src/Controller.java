import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements IUIListener {
    private UI userInterface;
    private final IVehicleGenerator vehicleGenerator = new VehicleGenerator();

    private final int maxCars = 10;

    private ArrayList<IVehicle> vehicles;
    private ArrayList<IBed> beds = new ArrayList();
    private ArrayList<IHaulerBed> haulerBeds= new ArrayList();
    private ArrayList<ILiftBed> liftBeds= new ArrayList();
    private ArrayList<ITurbo> turbos = new ArrayList();

    private final int delay = 17;
    private final Timer timer = new Timer(delay, new TimerListener());
    private final Robot robot = new Robot();

    public Controller(ArrayList<IVehicle> vehicles) throws IOException, AWTException {
        this.vehicles = vehicles;
        sortVehicles(vehicles);
        userInterface = new GUI(vehicles, "CarSim 1.1");
        setCarPosition();
        userInterface.update();
        userInterface.addUIListener(this);
    }

    public void setCarPosition(){
        double y = 0;
        double step = userInterface.getYMax()/vehicles.size();

        for (IVehicle v: vehicles) {
            v.setPosition(new DPoint(0.0, y));
            y += step;
        }
    }

    public void start(){
        timer.start();
    }

    @Override
    public void event(IUIEvent event) throws Exception {
        switch (event.getEvent()) {
            case ("gas") : {
                gas(event.getValue());
                break;
            }
            case ("brake") : {
                brake(event.getValue());
                break;
            }
            case ("turboOn") : {
                turboOn();
                break;
            }
            case ("turboOff") : {
                turboOff();
                break;
            }
            case ("liftBed") : {
                liftBed();
                break;
            }
            case ("lowerBed") : {
                lowerBed();
                break;
            }
            case ("startCars") : {
                startAllCars();
                break;
            }
            case ("stopCars") : {
                stopAllCars();
                break;
            }
            case ("addCar") : {
                if(vehicles.size() < maxCars) {
                    stopAllCars();
                    addCar();
                    userInterface.rebuildVehicleList(vehicles);
                }
                break;
            }
            case ("deleteCar") : {
                if(!vehicles.isEmpty()) {
                    stopAllCars();
                    removeCar();
                    userInterface.rebuildVehicleList(vehicles);
                }
                break;
            }
            case ("newUI") : {
                userInterface.dispose();
                userInterface = new GUI(vehicles, "UI has been changed");
                userInterface.update();
                userInterface.addUIListener(this);
            }
        }
        //System.out.println("Event '" + event.getEvent() + "' with value: " + event.getValue());
    }


    private void gas(double amount) throws Exception {
        //System.out.println("gas()");
        for(IVehicle v : vehicles) { v.gas(amount/100); }
    }

    private void brake(double amount) throws Exception {
        //System.out.println("brake()");
        for(IVehicle v : vehicles) { v.brake(amount/100); }
    }

    private void turboOn() throws Exception {
        //System.out.println("turboOn()");
        for(ITurbo t : turbos) { t.setTurboOn();}
    }

    private void turboOff() throws Exception {
        //System.out.println("turboOff");
        for(ITurbo t : turbos) { t.setTurboOff(); }
    }

    private void liftBed() {
        //System.out.println("liftBed()");
        for(IBed b : beds) { b.liftRamp(); }
    }

    private void lowerBed(){
        //System.out.println("lowerBed()");
        for(IBed b : beds) { b.lowerRamp(); }
    }

    private void stopAllCars() {
        //System.out.println("stopAllCars()");
        for(IVehicle v : vehicles) { v.stopEngine(); }
    }

    private void startAllCars() {
        //System.out.println("startAllCars()");
        for(IVehicle v : vehicles) { v.startEngine(); }
    }

    private void addCar(){
        IVehicle vehicle = vehicleGenerator.getRandomVehicle();
        vehicles.add(vehicle);
        addVehicleToLists(vehicle);
        setCarPosition();
    }

    private void removeCar(){
        removeVehicleFromLists(vehicles.get(vehicles.size()-1));
        setCarPosition();
    }

    private void sortVehicles(ArrayList<IVehicle> vehicles){
        for(IVehicle v : vehicles){
            addVehicleToLists(v);
        }
    }

    private void addVehicleToLists(IVehicle vehicle){

        if(vehicle instanceof IBed){ beds.add((IBed) vehicle); }
        if(vehicle instanceof IHaulerBed){ haulerBeds.add((IHaulerBed) vehicle); }
        if(vehicle instanceof ILiftBed){ liftBeds.add((ILiftBed) vehicle); }
        if(vehicle instanceof ITurbo){ turbos.add((ITurbo) vehicle); }
    }

    private void removeVehicleFromLists(IVehicle vehicle){
        vehicles.remove(vehicle);
        beds.remove(vehicle);
        haulerBeds.remove(vehicle);
        liftBeds.remove(vehicle);
        turbos.remove(vehicle);
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for(IVehicle v : vehicles ) {
                if(v.getPosition().getX() >= userInterface.getXMax() -100){
                    v.setDirection(180);
                }
                else if(v.getPosition().getX() < 0){
                    v.setDirection(0);
                }
                v.iterate();
            }
            userInterface.update();
            robot.keyPress(62);
        }
    }
}
