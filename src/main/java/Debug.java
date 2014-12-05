import intellimate.izou.addon.AddOn;
import intellimate.izou.main.Main;
import leanderk.izou.testactivator.TestActivatorAddOn;

import java.util.LinkedList;

/**
 * Use this class to debug
 */
public class Debug {
    public static void main(String[] args) {
        LinkedList<AddOn> addOns = new LinkedList<>();
        addOns.add(new TestActivatorAddOn());
        Main main = new Main(addOns, true);
    }
}
