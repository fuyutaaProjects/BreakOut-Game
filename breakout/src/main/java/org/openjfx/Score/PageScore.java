package org.openjfx.Score;

import org.openjfx.BreakOut;
import org.openjfx.TitleScreen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PageScore extends BreakOut {

    private Score scoreManager;
    private Button retourButton;

    public PageScore(Score scoreManager) {
        this.scoreManager = scoreManager;
        init_scene();
    }

    public void init_scene() {
        // Boîte verticale vbox pour empiler les éléments
        VBox root = new VBox(50);
        root.setStyle("-fx-alignment: center;");

        // Image de fond
        String backgroundImagePath = "file:src/main/java/org/openjfx/Game_ressources/Images/stars_bg.png";
        BackgroundImage backgroundImage = new BackgroundImage(
            new javafx.scene.image.Image(backgroundImagePath),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(backgroundImage));

        // Titre
        Text title = new Text("Top Scores");
        title.setFill(Color.YELLOW);
        title.setFont(Font.font(50));

        // Texts qui affichent le score
        Text score1 = new Text("1. " + scoreManager.getHighScore1());
        Text score2 = new Text("2. " + scoreManager.getHighScore2());
        Text score3 = new Text("3. " + scoreManager.getHighScore3());

        score1.setFill(Color.WHITE);
        score1.setFont(Font.font(30));

        score2.setFill(Color.WHITE);
        score2.setFont(Font.font(30));

        score3.setFill(Color.WHITE);
        score3.setFont(Font.font(30));

        // return button vers le menu principal
        retourButton = new Button("Retour");
        retourButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 18px; -fx-text-alignment: center;");
        retourButton.setPrefWidth(200);
        retourButton.setMinHeight(50);

        // Effet hover gris
        retourButton.setOnMouseEntered(e -> retourButton.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 18px; -fx-text-alignment: center;"));
        retourButton.setOnMouseExited(e -> retourButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 18px; -fx-text-alignment: center;"));

        retourButton.setOnAction(e -> {
            currentPage = new TitleScreen();
        });

        // Ajout des items dans la boite verticale vbox
        root.getChildren().addAll(title, score1, score2, score3, retourButton);

        Scene scene = new Scene(root, width, height);
        Tstage.setScene(scene);
    }
}
