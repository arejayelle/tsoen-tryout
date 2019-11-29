package immortuos.solution;

import immortuos.utils.Survivor;
import immortuos.utils.Event;

/**
 * The main application for the solution. Write your code here.
 */
public class Application {
    /**
     * Create a new application. You must not change this constructor's
     * signature.
     */
    private List<Survivor> survivorList = new ArrayList<>();

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
        this.event = event;

        for (Survivor survivor: this.survivorList) {
            survivor.update(this.event);
        }

    }
}
