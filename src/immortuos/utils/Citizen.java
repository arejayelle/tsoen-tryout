package immortuos.utils;

public class Citizen implements Survivor {

    private String type = "citizen";
    private Point location;

    @Override
    public void notify(Event event) {

    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public String getType() {
        return type;
    }
}
