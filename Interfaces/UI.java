import java.io.IOException;
import java.util.ArrayList;

public interface UI {
    void update();
    void addUIListener(IUIListener listener);
    int getXMax();
    int getYMax();
    void rebuildVehicleList(ArrayList<IVehicle> vehicles) throws IOException;
    void dispose();
}
