package leanderk.izou.testactivator;

import intellimate.izou.activator.Activator;
import intellimate.izou.addon.PropertiesContainer;
import intellimate.izou.events.Event;
import intellimate.izou.events.LocalEventManager;
import intellimate.izou.system.Context;
import intellimate.izou.system.Identification;
import intellimate.izou.system.IdentificationManager;

import java.util.Optional;

/**
 * Created by LeanderK on 13/11/14.
 */
public class TestActivator extends Activator{
    public static final String ID = TestActivator.class.getCanonicalName();
    private final PropertiesContainer propertiesContainer;
    private Context context;

    TestActivator(PropertiesContainer propertiesContainer, Context context) {
        super(context);
        this.context = context;
        this.propertiesContainer = propertiesContainer;
    }
    /**
     * Starting an Activator causes this method to be called.
     *
     * @throws InterruptedException will be caught by the Activator implementation, used to stop the Activator Thread
     */
    @Override
    public void activatorStarts() throws InterruptedException {
        System.out.println("TestActivator started");
        String eventDescriptor = propertiesContainer.getProperties().getProperty("event");
        if(eventDescriptor != null) switch (eventDescriptor) {
            case "FullWelcome" : eventDescriptor = Event.FULL_WELCOME_EVENT;
                break;
            case "MajorWelcome" : eventDescriptor = Event.MAJOR_WELCOME_EVENT;
                break;
            case "MinorWelcome" : eventDescriptor = Event.MINOR_WELCOME_EVENT;
                break;
            default: break;
        }
        if(eventDescriptor == null || eventDescriptor.isEmpty()) eventDescriptor = Event.FULL_WELCOME_EVENT;
        Optional<Identification> id = IdentificationManager.getInstance().getIdentification(this);
        if(!id.isPresent()) {
            context.logger.getLogger().error(new Exception("Unable to obtain ID"));
            return;
        }
        Optional<Event> event = Event.createEvent(Event.RESPONSE, id.get());
        if(!event.isPresent()) {
            context.logger.getLogger().error(new Exception("Unable to create Event"));
            return;
        }
        try {
            fireEvent(event.get());
            System.out.println("TestActivator fired Event");
        } catch (LocalEventManager.MultipleEventsException e) {
            context.logger.getLogger().debug(e);
            e.printStackTrace();
        }
    }

    /**
     * This method gets called when the Activator Thread got exceptionThrown.
     * <p/>
     * This is an unusual way of ending a thread. The main reason for this should be, that the activator was interrupted
     * by an uncaught exception.
     *
     * @param e if not null, the exception, which caused the termination
     * @return true if the Thread should be restarted
     */
    @Override
    public boolean terminated(Exception e) {
        e.printStackTrace();
        return false;
    }

    /**
     * An ID must always be unique.
     * A Class like Activator or OutputPlugin can just provide their .class.getCanonicalName()
     * If you have to implement this interface multiple times, just concatenate unique Strings to
     * .class.getCanonicalName()
     *
     * @return A String containing an ID
     */
    @Override
    public String getID() {
        return ID;
    }
}
