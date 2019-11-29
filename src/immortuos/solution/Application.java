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
                for(Survivor survivor: this.survivorList) {
                    switch (survivor.getType()) {
                        case "citizen":
                            if (this.distance(event.getLocation(), survivor.getLocation()) <= 3) {
                                survivor.notify(event);
                            }
                            break;
                        case "merchant":
                            if (this.distance(event.getLocation(), survivor.getLocation()) <= 6) {
                                survivor.notify(event);
                            }
                    }
                }
                break;
            case "zombie":
                for(Survivor survivor: this.survivorList){
                    switch (survivor.getType()) {
                        case "citizen":
                        case "merchant":
                            if (this.distance(event.getLocation(), survivor.getLocation()) <= 4) {
                                Point runLocation = this.findRunLocation(survivor.getLocation(), event.getLocation());
                                survivor.notify(new Event("run", runLocation));
                            }
                            break;
                        case "soldier":
                            if (this.distance(event.getLocation(), survivor.getLocation()) <= 7) {
                                survivor.notify(event);
                            }
                    }
                }
                break;
        }
    }

    public void waterEvent(Event event){
        for(Survivor survivor: this.survivorList){
            int distance = this.distance(event.getLocation(), survivor.getLocation());
            if(distance <= 5){
                survivor.notify(event);
            }
        }
    }

    public int distance(Point p1, Point p2){
        return (int)Math.sqrt((p2.getX()-p1.getX())*(p2.getX()-p1.getX()) + (p2.getY()-p1.getY())*(p2.getY()-p1.getY()));
    }

    public Point findRunLocation(Point survivor, Point zombie){
        float angle = this.getAngle(survivor, zombie);
        double x = survivor.getX() + (Math.cos(angle));
        double y = (survivor.getY() + Math.sin(angle));
        System.out.println("X: " + x + " Y: " + y);
        return new Point(x, y);
    }

    public float getAngle(Point survivor, Point zombie){
        float angle = (float) Math.toDegrees(Math.atan2(survivor.getY() - zombie.getY(), survivor.getX() - zombie.getX()));

        if(angle < 0){
            angle += 360;
        }
        return angle;
    }
}
