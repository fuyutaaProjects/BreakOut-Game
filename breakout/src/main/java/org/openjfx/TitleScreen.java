package org.openjfx;

import org.openjfx.Score.PageScore;
import org.openjfx.Score.Score;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TitleScreen extends BreakOut {

    private Score scoreManager = new Score();

    // on a besoin des buttons dans les fonctions de tests, alors ils sont déclarés comme variables du script directement (et pas juste dans init_scene)
    private Button startButton;
    private Button endlessButton;
    private Button scoresButton;
    private Button shopButton;
    private Button exitButton;
    private Button creditsButton;

    public TitleScreen() {
        init_scene();
    }

    public void init_scene() {
        VBox root = new VBox(20); // la vbox c'est le conteneur dans lequel on mets les buttons. Ils sont empilés automatiquement, et on espace les buttons de 20 pixels avec ce paramètre.
        root.setStyle("-fx-alignment: top-left; -fx-padding: 100px 0px 0px 50px;");

        // image de fond
        String backgroundImagePath = "file:src/main/java/org/openjfx/Game_ressources/Images/titlescreen_bg.png";
        BackgroundImage backgroundImage = new BackgroundImage(
            new javafx.scene.image.Image(backgroundImagePath),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(backgroundImage));

        // titre du jeu
        Text title = new Text("SPACEBREAKER");
        title.setFill(Color.WHITE);
        title.setFont(Font.font(50));
        
        // les buttons
        startButton = createButton("Start Game");
        endlessButton = createButton("Endless Mode");
        scoresButton = createButton("Scores");
        shopButton = createButton("Shop");
        exitButton = createButton("Exit");
        creditsButton = createButton("Crédits");
        

        startButton.setOnAction(e -> {
            BreakOut.isEndless = false;
            currentPage = new Run();
        });
        scoresButton.setOnAction(e -> {
            
            currentPage = new PageScore(scoreManager);
        });
        endlessButton.setOnAction(e -> {
            BreakOut.isEndless = true;
            currentPage = new RunEndless();
        });
        shopButton.setOnAction(e -> {
            currentPage = new MenuShop();
        });
        exitButton.setOnAction(e -> System.exit(0));

        creditsButton.setOnAction(e -> {
            currentPage = new Credits();
        });


        root.getChildren().addAll(title, startButton, endlessButton, scoresButton, shopButton, exitButton, creditsButton);

        Scene scene = new Scene(root, width, height);
        Tstage.setScene(scene);

        // gestion des shortcuts cachés pour les développeurs
        scene.setOnKeyPressed(this::handleDevShortcut);
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        // setPrefWidth et setPrefHeight déterminent une taille préférée à donner aux buttons quand c'est possible. par exemple si le texte rentre bien dans un button d'une taille
        // inférieure à 200x40 alors il va préférer faire un button de taille 200x40, il respecte la règle de préférance. mais si le texte sors de la "boite" du button car il est
        // trop grand, alors javafx va ajuster la taille pour éviter de casser l'affichage, le layout. On peut aussi set un mixheight et maxheight par exemple.
        button.setPrefSize(200, 40);
        button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20px;");
        
        // la couleur change quand on hover le button (on passe la souris dessus)
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 20px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20px;"));
        
        return button;
    }

    private void handleDevShortcut(KeyEvent event) {
        if (event.isAltDown() && "N".equals(event.getCode().toString())) {
            currentPage = new Dev_niveau();
        }
    }


    // Fonctions de test -------------------------------------------------
    public void clickStartButton() {
        startButton.fire();
    }

    public void clickEndlessButton() {
        endlessButton.fire();
    }

    public void clickScoresButton() {
        scoresButton.fire();
    }

    public void clickShopButton() {
        shopButton.fire();
    }

    public void clickExitButton() {
        exitButton.fire();
    }
}
