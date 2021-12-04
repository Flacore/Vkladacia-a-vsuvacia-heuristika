/*
 * TSPApp.java
 */

package tsp;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class TSPApp extends SingleFrameApplication {
    
    TSPView View = null;

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        View = new TSPView(this); 
        show(View);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     * @param root
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of TSPApp
     */
    public static TSPApp getApplication() {
        return Application.getInstance(TSPApp.class);
    }

    /**
     * Main method launching the application.
     * @param args
     */
    public static void main(String[] args) {
        launch(TSPApp.class, args);

    }
}
