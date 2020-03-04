import java.awt.*;

public interface IVehicleCustomization {
    /**
     * Returns the number of doors in the car.
     *
     * @return The number of doors.
     */
    public int getNrDoors();

    /**
     * Returns the enginePower of the car.
     *
     * @return The enginePower.
     */
    public double getEnginePower();

    /**
     * Returns the current color of the car.
     *
     * @return The current color.
     */
    public Color getColor();

    /**
     * Return the car's model name.
     *
     * @return The model name.
     */
    public String getModelName();

    /**
     * @param clr: New car color.
     */
    public void setColor(Color clr);

    public boolean isBig();

    public boolean isMoving();
}
