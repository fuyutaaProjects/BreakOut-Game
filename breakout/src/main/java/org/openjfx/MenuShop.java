package org.openjfx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;


/*
Structure de la classe MenuShop:
C'est une vbox, une boite verticale, qui contient les objets suivants:
    - le titre "Shop!" en text
    - un texte pour afficher le nombre de pièces possédées
    - une hbox, une boite horizontale, qui contient les boutons pour acheter les items
    - un bouton return pour retourner au menu principal

    Les boutons sont simples à comprendre, et ce script est basé sur la même structure utilisée dans TiltleScreen.java
*/

public class MenuShop extends BreakOut {

    private Amelioration ameliorationManager = new Amelioration();
    private Piece pieceManager = new Piece();

    private Button largeurButton;
    private Button laserButton;
    private Button retourButton;

    public MenuShop() {
        init_scene();
    }

    public void init_scene() {
        // boite verticale pour empiler les éléments
        VBox root = new VBox(50);
        root.setStyle("-fx-alignment: center;");

        // image de fond
        String backgroundImagePath = "file:src/main/java/org/openjfx/Game_ressources/Images/stars_bg.png";
        BackgroundImage backgroundImage = new BackgroundImage(
            new javafx.scene.image.Image(backgroundImagePath),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(backgroundImage));

        Text title = new Text("Shop!");
        title.setFill(Color.WHITE);
        title.setFont(Font.font(50));

        // Compteur de pièces
        Text piecesText = new Text("Pièces: " + pieceManager.getPieces());  // Initialiser avec la valeur actuelle
        piecesText.setFill(Color.WHITE);
        piecesText.setFont(Font.font(30));

        // Buttons d'items à acheter
        HBox buttonBox = new HBox(10);
        largeurButton = createButton("Amélioration : Largeur de raquette\n\nPrix : 200 pièces\n\nAugmente la largeur de la raquette de +35");
        laserButton = createButton("Attaque spéciale : Laser\n\nPrix : 1000 pièces\n\nDébloque l'attaque spéciale Laser");        

        largeurButton.setOnAction(e -> {
            if (ameliorationManager.getNombreAmeliorationLargeur() >= 5) {
                afficherMessage("Achat interdit : Limite atteinte");
            } else if (pieceManager.getPieces() >= 200) {
                pieceManager.retirerPiece(200);
                ameliorationManager.ajoutLargeur(35);
                ameliorationManager.ajoutNombreAmeliorationLargeur(1);
                afficherMessage("Achat effectué : Largeur améliorée");
            } else {
                afficherMessage("Manque d'argent : 200 pièces nécessaires");
            }
        });

        laserButton.setOnAction(e -> {
            if (ameliorationManager.getLaser()) {
                afficherMessage("Achat interdit : Laser déjà acheté");
            } else if (pieceManager.getPieces() >= 1000) {
                pieceManager.retirerPiece(1000);
                ameliorationManager.setLaser(true);
                afficherMessage("Achat effectué : Laser débloqué");
            } else {
                afficherMessage("Manque d'argent : 1000 pièces nécessaires");
            }
        });

        // ajout des buttons dans la HBox pour les aligner horizontalement
        buttonBox.getChildren().addAll(largeurButton, laserButton);
        buttonBox.setStyle("-fx-alignment: center; -fx-spacing: 10;");

        retourButton = new Button("Retour");
        retourButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 18px; -fx-text-alignment: center;");
        retourButton.setPrefWidth(200);
        retourButton.setMinHeight(50);
        // effet hover gris
        retourButton.setOnMouseEntered(e -> retourButton.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 18px; -fx-text-alignment: center;"));
        retourButton.setOnMouseExited(e -> retourButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 18px; -fx-text-alignment: center;"));

        retourButton.setOnAction(e -> currentPage = new TitleScreen());

        // Ajout des 3 items pour les empiler verticalement
        root.getChildren().addAll(title, piecesText, buttonBox, retourButton);

        Scene scene = new Scene(root, width, height);
        Tstage.setScene(scene);
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 18px; -fx-wrap-text: true; -fx-text-alignment: center;");
        button.setPrefWidth(200);
        button.setMinHeight(50);

        // Permet d'étirer les boutons verticalement (normalement, j'ai pas tout compris mais ça marche)
        // en gros, hgrow autorise à étirer horizontalement mais on peut pas faire hbox.setvgrow. donc pour autoriser un etirement vertical on fait vbox.vgrow
        // en soit c'est pas obligatoire ça vu que les boutons sont déjà étirés verticalement par défaut, mais on ensure que ça le fait au cas ou.
        HBox.setHgrow(button, Priority.ALWAYS);
        VBox.setVgrow(button, Priority.ALWAYS);

        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 18px; -fx-wrap-text: true; -fx-text-alignment: center;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 18px; -fx-wrap-text: true; -fx-text-alignment: center;"));

        return button;
    }

    private void afficherMessage(String message) {
        VBox messageBox = new VBox(10);
        messageBox.setStyle("-fx-background-color: black; -fx-alignment: center;");

        Text messageText = new Text(message);
        messageText.setFill(Color.YELLOW);
        messageText.setFont(Font.font(20));

        messageBox.getChildren().add(messageText);
        Tstage.setScene(new Scene(messageBox, width, height));

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            javafx.application.Platform.runLater(this::init_scene);
        }).start();
    }
}
