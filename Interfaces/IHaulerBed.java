public interface IHaulerBed {
    void loadVehicle(Vehicle vehicle);
    void unloadVehicle();
    boolean isClose(Vehicle vehicle);
}
