package org.openjfx;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Credits extends BreakOut {

    protected static Canvas canvas = new Canvas(width, height);
    // Contexte graphique pour dessiner sur le canvas
    protected static GraphicsContext gc = canvas.getGraphicsContext2D();

    public Credits() {
        init_scene();
    }

    /**
     * Initialise la scène des crédits.
     */
    public void init_scene() {
        // Charge le GIF
        Image gifImage = new Image(new File("src/main/java/org/openjfx/steamuserimages-a.akamaihd.gif").toURI().toString());
        ImageView imageView = new ImageView(gifImage);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        // Définit la scène avec un StackPane contenant l'ImageView et le canvas
        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, canvas);
        Scene scene = new Scene(root, width, height);
        BreakOut.Tstage.setScene(scene);
        // Initialise le contexte graphique
        init_gc(gc);

        // Affiche les crédits
        showCredits();

        // Gère les clics de la souris pour revenir à l'écran de titre
        scene.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                TitleScreen titleScreen = new TitleScreen();
                currentPage = titleScreen;
            }
        });
    }

    /**
     * Affiche les crédits.
     */
    private void showCredits() {
        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        gc.fillText("Jeu construit par \n Wassime, Mohamed \n Oussama, Ethan \n Daniel, fuyutaaProjects", width / 2 - 10, height / 2 + 70);

        // Animation pour faire clignoter le texte
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), canvas);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.3);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }
}