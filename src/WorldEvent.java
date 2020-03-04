public class WorldEvent implements IWorldEvent {
    private String event;

    public WorldEvent(String event){
        this.event = event;
    }

    @Override
    public String getEvent() {
        return event;
    }
}
