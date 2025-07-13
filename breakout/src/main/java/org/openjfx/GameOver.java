
package org.openjfx;

import org.openjfx.Score.PageScore;
import org.openjfx.Score.Score;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameOver extends BreakOut {

    private int scoreFromLastGame;
    private Score scoreManager = new Score();
    // on a besoin des buttons dans les fonctions de tests, alors ils sont déclarés comme variables du script directement (et pas juste dans init_scene)
    private Button replayButton;
    private Button scoresButton;
    private Button menuButton;

    public GameOver(int score) {
        this.scoreFromLastGame = score;
        init_scene();
    }

    public void init_scene() {
        VBox root = new VBox(20); // la vbox c'est le conteneur dans lequel on mets les buttons. Ils sont empilés automatiquement, et on espace les buttons de 20 pixels avec ce paramètre.
        root.setStyle("-fx-background-color: black; -fx-alignment: center;"); // background color black pour le moment, oui le fond est du jeu est déjà black mais je vais en avoir besoin quand je mettrais une image de fond.

        // texte après game over
        Text gameOverText = new Text("Game Over!");
        gameOverText.setFill(Color.RED);
        gameOverText.setFont(Font.font(60));

        // score
        Text scoreText = new Text("Your Score: " + scoreFromLastGame);
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(Font.font(40));

        // les buttons
        replayButton = createButton("Replay");
        scoresButton = createButton("Scores");
        menuButton = createButton("Main Menu");

          // Gestion des actions des boutons
          replayButton.setOnAction(e -> 
            {if(BreakOut.isEndless) {
                currentPage = new Run();
            }
            else {
                currentPage = new RunEndless();
             }
        });;
          scoresButton.setOnAction(e -> {
              currentPage = new PageScore(scoreManager);
          });
          menuButton.setOnAction(e -> currentPage = new TitleScreen());
  
          root.getChildren().addAll(gameOverText, scoreText, replayButton, scoresButton, menuButton);
  
          Scene scene = new Scene(root, width, height);
          Tstage.setScene(scene);
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

    // Fonctions de test ----------------------------
    public void clickReplayButton() {
        if (replayButton != null) {
            replayButton.fire();
        }
    }

    public void clickScoresButton() {
        if (scoresButton != null) {
            scoresButton.fire();
        }
    }

    public void clickMenuButton() {
        if (menuButton != null) {
            menuButton.fire();
        }
    }
}
