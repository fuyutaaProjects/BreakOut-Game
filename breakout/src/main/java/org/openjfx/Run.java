package org.openjfx;

import org.openjfx.Game_ressources.ModeNiveaux;
import org.openjfx.Score.Score;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * La classe Run représente la boucle principale du jeu Breakout.
 * Elle gère l'initialisation de la scène, les mouvements de la raquette, les collisions et le passage au niveau supérieur.
 */
public class Run extends BreakOut {

    // Pane représentant le panneau contenant les éléments du jeu
    private Pane p = new Pane();

    // Amélioration du joueur
    private Amelioration ameliorationManager = new Amelioration();

    // Mode de jeu contenant les niveaux
    private ModeNiveaux modeDeJeu = new ModeNiveaux();
    // Score du joueur
    private Score scoreManager = new Score();

    // Canvas pour dessiner les éléments du jeu
    private Canvas canvas = new Canvas(width, height);
    // Contexte graphique pour dessiner sur le canvas
    private GraphicsContext gc = canvas.getGraphicsContext2D();

    // L'utilitaire qui gère les attaques spéciales (attaque sur é et sur " (l'attaque sur " c'est l'ultimate"))
    private SpecialAttacksHandler specialAttacksHandler = new SpecialAttacksHandler();

    // Image de fond
    private Image backgroundImage;


    /**
     * Initialise la scène du jeu.
     */
     public void init_scene() {
        // Ajoute le canvas, la raquette et le panneau des briques au panneau principal
        p.getChildren().add(canvas);
        p.getChildren().add(modeDeJeu.getBoard().getp());
        // p.getChildren().add(liste_balle.get(0).oval);


        // Définit la scène avec le panneau principal
        Scene scene = new Scene(p, width, height);
        Tstage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/org/openjfx/styles.css").toExternalForm());

        // Special Attacks: une grande documentation qui explique tout est disponible dans le dossier des SpecialAttacks.
        scene.setOnKeyPressed(event -> specialAttacksHandler.handle(event));
        // Temporaire: on équipe le laser (son id est 0, on l'indentifie en tant que 0)
        if (ameliorationManager.getLaser()) {
            // Permet d'activer le sort si le joueur le possède.
            specialAttacksHandler.setSpecialAttackOnDIGIT2(0);
        }

        // Envoie les entrées clavier dans raquette pour la rotation
        scene.setOnKeyPressed(event -> modeDeJeu.getBoard().raquette().keypress_rotation(event));
        scene.setOnKeyReleased(event -> modeDeJeu.getBoard().raquette().keyrelease_rotation(event));
        

        // Initialise le contexte graphique
        init_gc(gc);
    }


    /**
     * Constructeur de la classe Run.
     * Initialise la scène du jeu.
     */
    public Run() {
        // image de fond
        backgroundImage = new Image("file:src/main/java/org/openjfx/Game_ressources/Images/stars_bg.png");

        // Initialise la scène du jeu
        init_scene();
        scoreManager.resetScore(); // Réinitialise le score et le multiplicateur à la fin de la partie
        scoreManager.resetHits(); //reset le multiplicateur

    }


    /**
     * Boucle principale du jeu.
     * Met à jour et dessine les éléments du jeu, gère les collisions et vérifie les conditions de fin de partie.
     */
    public void loop() {
        // dessiner image de fond
        gc.drawImage(backgroundImage, 0, 0, width, height);

        // Gère le rebond des balles sur la raquette

        // Vérifie si les balles touchent les bordures du jeu
        modeDeJeu.getBoard().FrameBoard10ms();


        // Affiche le score
        gc.setFont(new Font( "Arial",24)); // Police et taille du texte
        scoreManager.drawBestScore(gc, 10, height * 0.45);//dessine le meuilleur Score
        scoreManager.drawScore(gc, 10, height * 0.5);//dessine le Score
        scoreManager.drawMultiplier(gc, width , height );// Affichage du multiplicateur s'il est actif    

        if (modeDeJeu.getBoard().ConditionNivSup()) {
            modeDeJeu.passe_nivsup(p);
            // Ajoute le panneau des briques au panneau principal
            p.getChildren().add(modeDeJeu.getBoard().getp());
        }

        // Gère la rotation de la raquette
        modeDeJeu.getBoard().raquette().rotation();
    }

}
