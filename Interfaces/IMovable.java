public interface IMovable {

    void setPosition(DPoint position);
    void setDirection(int direction);
    void setCurrentSpeed(double speed);
    void iterate();

    DPoint getPosition();
    int getDirection();
    double getCurrentSpeed();
}
