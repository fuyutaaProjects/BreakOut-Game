package org;

import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXInitializer extends Application {
    private static boolean initialized = false;

    @Override
    public void start(Stage primaryStage) {
        synchronized (JavaFXInitializer.class) {
            initialized = true;
            JavaFXInitializer.class.notifyAll(); // Réveiller les threads en attente
        }
    }

    public static void initialize() {
        if (!initialized) {
            new Thread(() -> Application.launch(JavaFXInitializer.class)).start();
            synchronized (JavaFXInitializer.class) {
                while (!initialized) {
                    try {
                        JavaFXInitializer.class.wait(); // Attendre l'initialisation
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}


