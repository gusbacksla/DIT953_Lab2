public class UIEvent implements IUIEvent {
    private String event;
    private double value;

    public UIEvent(String event, double value) {
        this.event = event;
        this.value = value;
    }

    @Override
    public String getEvent() {
        return event;
    }

    @Override
    public double getValue(){
        return value;
    }
}
