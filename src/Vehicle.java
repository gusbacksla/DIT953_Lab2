import java.awt.*;

abstract class Vehicle implements IVehicle, IVehicleCustomization {


    private double enginePower;
    private int nrDoors;
    private double currentSpeed;
    private Color color;
    private String modelName;
    protected boolean engineIsRunning = false;

    private DPoint pos = new DPoint();
    private int orientation = 0;

    /**
     * Class constructor creates a car object then calls on the function stopEngine();
     *
     * @param nrDoors:     Amount of doors the car has
     * @param color:       Color of the car
     * @param enginePower: Strength of the engine
     * @param modelName:   Name of the model
     */
    public Vehicle(int nrDoors, Color color, double enginePower, String modelName) {

        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        stopEngine();
    }

    @Override
    public void gas(double amount) throws Exception {
        if(engineIsRunning) {
            if (amount <= 1 && amount >= 0) {
                incrementSpeed(amount);
            }
            else { throw new Exception("Value Has To Be Between 0 - 1"); }
        }
    }

    @Override
    public void brake(double amount) throws Exception {
        if (amount <= 1 && amount >= 0)
            decrementSpeed(amount);
        else
            throw new Exception("Value Has To Be Between 0 - 1");
    }

    @Override
    public void turnLeft() {
        orientation += 90;
        if (Math.abs(orientation) == 360) {
            orientation = 0;
        }
        System.out.print(getModelName() + " Is ");
    }

    @Override
    public void turnRight() {

    }

    @Override
    public void startEngine() {
        if(!engineIsRunning) {
            engineIsRunning = true;
            currentSpeed = 0.1;
        }
    }

    @Override
    public void stopEngine() {
        currentSpeed = 0;
        engineIsRunning = false;
    }

    @Override
    public void setPosition(DPoint position) {
        pos.setLocation(position.getX(), position.getY());
    }

    @Override
    public void setDirection(int direction) {
        orientation = direction;
    }

    @Override
    public void setCurrentSpeed(double speed) {
        if(speed >= 0 && speed <= enginePower) { currentSpeed = speed; }
    }

    @Override
    public DPoint getPosition() {
        return pos;
    }

    @Override
    public int getDirection() {
        return orientation;
    }

    @Override
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * Return the speedFactor of the car.
     *
     * @return The enginePower.
     */
    abstract double speedFactor();


    /**
     * Increases the speed of the car by 'amount' multiplied by the speed factor.
     * The speed cannot go above the engine power.
     *
     * @param amount: The amount of speed to be incremented by.
     */
    @Override
    public void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * Math.abs(amount), enginePower);
    }

    /**
     * Decreases the speed of the car by 'amount' multiplied by the speed factor.
     * The speed cannot go bellow 0.
     *
     * @param amount: The amount of speed to decrement by.
     */
    @Override
    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * Math.abs(amount), 0);
    }

    /**
     * Returns the number of doors in the car.
     *
     * @return The number of doors.
     */
    @Override
    public int getNrDoors() {
        return nrDoors;
    }

    /**
     * Returns the enginePower of the car.
     *
     * @return The enginePower.
     */
    @Override
    public double getEnginePower() {
        return enginePower;
    }

    /**
     * Returns the current color of the car.
     *
     * @return The current color.
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Return the car's model name.
     *
     * @return The model name.
     */
    @Override
    public String getModelName() {
        return modelName;
    }

    /**
     * @param clr: New car color.
     */
    @Override
    public void setColor(Color clr) {
        color = clr;
    }

    @Override
    public boolean isBig() {
        return false;
    }

    @Override
    public boolean isMoving() {
        return getCurrentSpeed() > 0.1;
    }

    @Override
    public void iterate() {
        double x = pos.getX();
        double y = pos.getY();
        if     (orientation == 0)   { x += currentSpeed ;}
        else if(orientation == 180) { x -= currentSpeed ;}
        else if(orientation == 90)  { y -= currentSpeed ;}
        else if(orientation == 270) { y += currentSpeed ;}
        pos.setLocation(x, y);
        //System.out.println("New position: (" + pos.getX() + "|" + pos.getY() + ")");
    }

    @Override
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
