package org.openjfx;

import javafx.application.Platform;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.JavaFXInitializer;

public class Dev_niveauTest {

    private static Dev_niveau devNiveau;

    @BeforeAll
    public static void setupJavaFX() throws Exception {
        // Initialiser JavaFX
        JavaFXInitializer.initialize();

        // Attendre que JavaFX soit prêt
        Platform.runLater(() -> {
            BreakOut.setTstage(new Stage());
            devNiveau = new Dev_niveau(); // Initialiser Dev_niveau
        });

        // Attendre que JavaFX soit complètement initialisé
        Thread.sleep(500);
    }
    /* 
    @Test
    public void testAddPoly() throws Exception {
        Platform.runLater(() -> {
            devNiveau.addPoly();
            assertEquals(2, devNiveau.getPolys().size(), "Il devrait y avoir 2 polygones après ajout.");
        });

        Thread.sleep(500); // Attendre la fin des actions JavaFX
    }

    @Test
    public void testRemovePoly() throws Exception {
        Platform.runLater(() -> {
            // Ajouter deux polygones pour les tests
            devNiveau.addPoly();
            devNiveau.addPoly();

            // Supprimer un polygone
            devNiveau.removePoly();
            assertEquals(1, devNiveau.getPolys().size(), "Il devrait rester 1 polygone après suppression.");

            // Supprimer le dernier polygone
            devNiveau.removePoly();
            assertEquals(1, devNiveau.getPolys().size(), "Un polygone vide devrait être ajouté automatiquement.");
        });

        Thread.sleep(500);
    }

    @Test
    public void testAddToPoly() throws Exception {
        Platform.runLater(() -> {
            // Ajouter des points à un polygone
            devNiveau.addToPoly(10.0, 20.0);
            devNiveau.addToPoly(30.0, 40.0);

            // Vérifier les coordonnées ajoutées
            Polygon poly = devNiveau.getPolys().get(0);
            assertEquals(4, poly.getPoints().size(), "Il devrait y avoir 4 coordonnées (2 points).");
            assertEquals(10.0, poly.getPoints().get(0), "Première coordonnée X incorrecte.");
            assertEquals(20.0, poly.getPoints().get(1), "Première coordonnée Y incorrecte.");
            assertEquals(30.0, poly.getPoints().get(2), "Deuxième coordonnée X incorrecte.");
            assertEquals(40.0, poly.getPoints().get(3), "Deuxième coordonnée Y incorrecte.");
        });

        Thread.sleep(500);
    }

    @Test
    public void testKeyPressed() throws Exception {
        Platform.runLater(() -> {
            // Ajouter un polygone
            devNiveau.keyPressed("N");
            assertEquals(2, devNiveau.getPolys().size(), "Il devrait y avoir 2 polygones après avoir pressé 'N'.");

            // Ajouter des points
            devNiveau.addToPoly(10.0, 20.0);

            // Supprimer les derniers points
            devNiveau.keyPressed("B");
            assertEquals(0, devNiveau.getPolys().get(1).getPoints().size(), "Tous les points du dernier polygone devraient être supprimés après 'B'.");

            // Supprimer un polygone
            devNiveau.keyPressed("R");
            assertEquals(1, devNiveau.getPolys().size(), "Il devrait rester 1 polygone après avoir pressé 'R'.");
        });

        Thread.sleep(500);
    }
    */
}
