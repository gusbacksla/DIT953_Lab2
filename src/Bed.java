public class Bed {

    private boolean isRampUp = true;
    private boolean isRampDown = false;

    public boolean isRampUp() {
        return isRampUp;
    }

    public boolean isRampDown(){
        return isRampDown;
    }

    public void lowerRamp() {
            isRampDown = true;
            isRampUp = false;
    }

    public void liftRamp() {
        isRampUp = true;
        isRampDown = false;
    }
}
