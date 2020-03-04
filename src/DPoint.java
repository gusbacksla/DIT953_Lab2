public class DPoint implements IPoint {
    private double x;
    private double y;

    public DPoint(){
        this(0,0);
    }

    public DPoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
