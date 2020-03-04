import java.util.LinkedList;

public class HaulerBed extends Bed  {

    protected LinkedList<Vehicle> cargo = new LinkedList<>();

    public void loadVehicle(Vehicle vehicle, DPoint Pos, int orientation) {

                vehicle.setPosition(Pos);
                vehicle.setOrientation(orientation);
                cargo.push(vehicle);
                System.out.println(vehicle.getModelName() + " Is Loaded.");
    }

    public void unloadVehicle(DPoint Pos, int orientation) {

            Vehicle vehicle = cargo.pop();
            System.out.println("\n" + vehicle.getModelName() + " is Unloaded");
            switch (orientation) {
                case 0:
                    vehicle.setPosition(new DPoint(Pos.getX() - 5, Pos.getY()));      //Right
                    vehicle.setOrientation(0);
                    break;

                case (90):
                case (-270):
                    vehicle.setPosition(new DPoint(Pos.getX(), Pos.getY() - 5));      //Up
                    vehicle.setOrientation(90);
                    break;

                case (180):
                case (-180):
                    vehicle.setPosition(new DPoint(Pos.getX() + 5, Pos.getY()));      //Left
                    vehicle.setOrientation(180);
                    break;

                case (270):
                case (-90):
                    vehicle.setPosition(new DPoint(Pos.getX(), Pos.getY() + 5));      //Down
                    vehicle.setOrientation(270);
                    break;
            }
    }

    public boolean isClose(Vehicle vehicle, DPoint Pos) {
        double deltaX = Math.abs(Pos.getX() - vehicle.getPosition().getX());
        double deltaY = Math.abs(Pos.getY() - vehicle.getPosition().getY());

        return deltaX <= 5 && deltaY <= 5;
    }
}
