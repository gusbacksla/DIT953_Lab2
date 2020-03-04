import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class World implements IWorld {
    private final IVehicleGenerator vehicleGenerator = new VehicleGenerator();

    private ArrayList<IWorldListener> listeners = new ArrayList();

    private final int maxCars = 10;

    private final int xMax;
    private final int yMax;

    private ArrayList<IVehicle> vehicles;
    private ArrayList<IBed> beds = new ArrayList();
    private ArrayList<IHaulerBed> haulerBeds= new ArrayList();
    private ArrayList<ILiftBed> liftBeds= new ArrayList();
    private ArrayList<ITurbo> turbos = new ArrayList();

    private final int delay = 17;
    private final Timer timer = new Timer(delay, new TimerListener());
    private final Robot robot = new Robot();

    public World(ArrayList<IVehicle> vehicles, int xMax, int yMax) throws IOException, AWTException {
        this.xMax = xMax;
        this.yMax = yMax;
        this.vehicles = vehicles;
        sortVehicles(vehicles);
        setCarPosition();
    }

    public void gas(double amount) throws Exception {
        //System.out.println("gas()");
        for(IVehicle v : vehicles) { v.gas(amount/100); }
    }

    public void brake(double amount) throws Exception {
        //System.out.println("brake()");
        for(IVehicle v : vehicles) { v.brake(amount/100); }
    }

    public void turboOn() throws Exception {
        //System.out.println("turboOn()");
        for(ITurbo t : turbos) { t.setTurboOn();}
    }

    public void turboOff() throws Exception {
        //System.out.println("turboOff");
        for(ITurbo t : turbos) { t.setTurboOff(); }
    }

    public void liftBed() {
        //System.out.println("liftBed()");
        for(IBed b : beds) { b.liftRamp(); }
    }

    public void lowerBed(){
        //System.out.println("lowerBed()");
        for(IBed b : beds) { b.lowerRamp(); }
    }

    public void stopAllCars() {
        //System.out.println("stopAllCars()");
        for(IVehicle v : vehicles) { v.stopEngine(); }
    }

    public void startAllCars() {
        //System.out.println("startAllCars()");
        for(IVehicle v : vehicles) { v.startEngine(); }
    }

    public void addCar(){
        IVehicle vehicle = vehicleGenerator.getRandomVehicle();
        vehicles.add(vehicle);
        addVehicleToLists(vehicle);
        setCarPosition();
    }

    public void removeCar(){
        removeVehicleFromLists(vehicles.get(vehicles.size()-1));
        setCarPosition();
    }

    @Override
    public void addWorldListener(IWorldListener listener) {
        listeners.add(listener);
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
                if(v.getPosition().getX() >= xMax -100){
                    v.setDirection(180);
                }
                else if(v.getPosition().getX() < 0){
                    v.setDirection(0);
                }
                v.iterate();
            }
            sendEvent("updated");
            //robot.keyPress(62);
            //robot.keyPress(KeyEvent.VK_COPY);
        }
    }

    public void setCarPosition(){
        double y = 0;
        double step = 0;
        if(!vehicles.isEmpty()) { step = yMax/vehicles.size(); }

        for (IVehicle v: vehicles) {
            v.setPosition(new DPoint(0.0, y));
            y += step;
        }
    }

    private void sendEvent(String eventString){
        for(IWorldListener l : listeners) { l.event(new WorldEvent(eventString)); }
    }

    @Override
    public void start(){
        timer.start();
    }

    @Override
    public ArrayList<IVehicle> getVehicles() {
        return vehicles;
    }
}
