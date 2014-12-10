package leanderk.izou.testactivator;

import intellimate.izou.activator.Activator;
import intellimate.izou.addon.AddOn;
import intellimate.izou.contentgenerator.ContentGenerator;
import intellimate.izou.events.EventsController;
import intellimate.izou.output.OutputExtension;
import intellimate.izou.output.OutputPlugin;
import ro.fortsoft.pf4j.Extension;

/**
 * Created by LeanderK on 13/11/14.
 */
@Extension
public class TestActivatorAddOn extends AddOn{
    public final static String ID = TestActivatorAddOn.class.getCanonicalName();
    /**
     * the default constructor for AddOns
     *
     */
    public TestActivatorAddOn() {
        super(ID);
    }

    /**
     * use this method to build your instances etc.
     */
    @Override
    public void prepare() {

    }

    /**
     * use this method to register (if needed) your Activators.
     *
     * @return Array containing Instances of Activators
     */
    @Override
    public Activator[] registerActivator() {
        Activator[] activators = new Activator[1];
        activators[0] = new TestActivator(getPropertiesContainer(), getContext());
        return activators;
    }

    /**
     * use this method to register (if needed) your ContentGenerators.
     *
     * @return Array containing Instances of ContentGenerators
     */
    @Override
    public ContentGenerator[] registerContentGenerator() {
        return new ContentGenerator[0];
    }

    /**
     * use this method to register (if needed) your EventControllers.
     *
     * @return Array containing Instances of EventControllers
     */
    @Override
    public EventsController[] registerEventController() {
        return new EventsController[0];
    }

    /**
     * use this method to register (if needed) your OutputPlugins.
     *
     * @return Array containing Instances of OutputPlugins
     */
    @Override
    public OutputPlugin[] registerOutputPlugin() {
        return new OutputPlugin[0];
    }

    /**
     * use this method to register (if needed) your Output.
     *
     * @return Array containing Instances of OutputExtensions
     */
    @Override
    public OutputExtension[] registerOutputExtension() {
        return new OutputExtension[0];
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
