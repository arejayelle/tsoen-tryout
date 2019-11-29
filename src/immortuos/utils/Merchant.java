package immortuos.utils;

public class Merchant implements Survivor{

    private String type = "merchant";
    private Point locaiton;

    @Override
    public void notify(Event event) {

    }

    @Override
    public Point getLocation() {
        return locaiton;
    }

    @Override
    public String getType() {
        return type;
    }
}
