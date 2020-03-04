public class Turbo {

    /** a constant that is supposed to be multiplied with the speedFactor of a vehicle
     */
    final double turbo;

    private boolean turboOn = false;

    public Turbo(double turbo){
        this.turbo = turbo;
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    public double getTurbo(){
        if(turboOn) { return turbo; }
        return 1;
    }

    public boolean isOn(){
        return turboOn;
    }
}