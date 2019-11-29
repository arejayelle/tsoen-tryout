package immortuos.solution;

import immortuos.utils.Point;
import immortuos.utils.Survivor;
import immortuos.utils.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * The main application for the solution. Write your code here.
 */
public class Application {
    private List<Survivor> survivorList = new ArrayList<Survivor>();
    private final int WATER_DISTANCE = 5;
    private final int TRADE_CITIZEN_DISTANCE = 3;
    private final int TRADE_MERCHANT_DISTANCE = 6;
    private final int ZOMBIE_CITIZEN_MERCHANT_DISTANCE = 4;
    private final int ZOMBIE_SOLDIER_DISTANCE = 7;

    /**
     * Create a new application. You must not change this constructor's
     * signature.
     */
    public Application() {
        // You may write code here.
        
    }

    /**
     * Called when a new survivor must be added to the system. You must not
     * change this method's signature.
     *
     * @param survivor The survivor to be added.
     * @param type The type of this survivor.
     */
    public void registerSurvivor(Survivor survivor) {
        // Write your code here.
        this.survivorList.add(survivor);
        survivor.notify(new Event("registered", survivor.getLocation()));
    }

    /**
     * Called when an event occurs in the area tracked by the system. You must
     * not change this method's signature.
     *
     * @param eventType The type of the event.
     * @param eventLocation The location at which the event occurred.
     */
    public void onEvent(Event event) {
        // Write your code here.
        switch(event.getType()){
            case "water":
                this.waterEvent(event);
                break;
            case "trade":
                this.tradeEvent(event);
                break;
            case "zombie":
                this.zombieEvent(event);
                break;
        }
    }

    public void waterEvent(Event event){
        for(Survivor survivor: this.survivorList){
            int distance = this.distance(event.getLocation(), survivor.getLocation());
            if(distance <= WATER_DISTANCE){
                survivor.notify(event);
            }
        }
    }

    public void tradeEvent(Event event){
        for(Survivor survivor: this.survivorList) {
            int distance = this.distance(event.getLocation(), survivor.getLocation());
            switch (survivor.getType()) {
                case "citizen":
                    if (distance <= TRADE_CITIZEN_DISTANCE) {
                        survivor.notify(event);
                    }
                    break;
                case "merchant":
                    if (distance <= TRADE_MERCHANT_DISTANCE) {
                        survivor.notify(event);
                    }
            }
        }
    }

    public void zombieEvent(Event event){
        for(Survivor survivor: this.survivorList){
            int distance = this.distance(event.getLocation(), survivor.getLocation());
            switch (survivor.getType()) {
                case "citizen":
                case "merchant":
                    if (distance <= ZOMBIE_CITIZEN_MERCHANT_DISTANCE) {
                        Point runLocation = this.findRunLocation(survivor.getLocation(), event.getLocation());
                        survivor.notify(new Event("run", runLocation));
                    }
                    break;
                case "soldier":
                    if (distance <= ZOMBIE_SOLDIER_DISTANCE) {
                        survivor.notify(event);
                    }
            }
        }
    }

    private int distance(Point p1, Point p2){
        return (int)Math.sqrt(this.square((p2.getX()-p1.getX())) + square(p2.getY()-p1.getY()));
    }

    private double square(double num1){
        return num1*num1;
    }

    private Point findRunLocation(Point survivor, Point zombie){
        float angle = this.getAngle(survivor, zombie);
        double x = survivor.getX() + (Math.cos(angle));
        double y = (survivor.getY() + Math.sin(angle));
        return new Point(x, y);
    }

    private float getAngle(Point survivor, Point zombie){
        float angle = (float) Math.toDegrees(Math.atan2(survivor.getY() - zombie.getY(), survivor.getX() - zombie.getX()));

        if(angle < 0){
            angle += 360;
        }
        return angle;
    }
}
