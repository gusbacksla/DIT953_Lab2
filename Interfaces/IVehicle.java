public interface IVehicle extends IMovable{
    String getModelName();

    void setPosition(DPoint position);
    void setOrientation(int orientation);

    void gas(double amount) throws Exception;
    void brake(double amount) throws Exception;
    void incrementSpeed(double amount);
    void decrementSpeed(double amount);

    void turnLeft();
    void turnRight();
    void startEngine();
    void stopEngine();
}
