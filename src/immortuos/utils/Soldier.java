package immortuos.utils;

public class Soldier implements Survivor {

    private String type = "soldier";
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
