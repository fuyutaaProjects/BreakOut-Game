package org.openjfx;

import java.util.Random;

import org.openjfx.Game_ressources.ModeNiveaux;
import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Raquette;
import org.openjfx.Game_ressources.Objets.All_levels.Niveau_Endless;
import org.openjfx.Game_ressources.SpecialAttacks.Laser;
import org.openjfx.Score.Score;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;

/**
 * La classe RunEndless représente la boucle principale du jeu Breakout en mode "Endless".
 * Elle gère l'initialisation de la scène, les mouvements de la raquette, les collisions et la génération continue de nouvelles briques.
 */
public class RunEndless extends BreakOut {

    // Pane représentant le panneau contenant les éléments du jeu
    private Pane p = new Pane();

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

    /**
     * Initialise la scène du jeu.
     */
    public void init_scene() {
        // Ajoute le canvas, la raquette et le panneau des briques au panneau principal
        p.getChildren().add(canvas);
        p.getChildren().add(modeDeJeu.getBoard().getp());

        // Définit la scène avec le panneau principal
        Scene scene = new Scene(p, width, height);
        Tstage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/org/openjfx/styles.css").toExternalForm());

        // Special Attacks: une grande documentation qui explique tout est disponible dans le dossier des SpecialAttacks.
        scene.setOnKeyPressed(event -> specialAttacksHandler.handle(event));
        // Temporaire: on équipe le laser (son id est 0, on l'indentifie en tant que 0)
        specialAttacksHandler.setSpecialAttackOnDIGIT2(0);

        // Initialise le contexte graphique
        init_gc(gc);
    }

    /**
     * Constructeur de la classe RunEndless.
     * Initialise la scène du jeu.
     */
    public RunEndless() {
        init_scene();
        scoreManager.resetScore(); // Réinitialise le score et le multiplicateur à la fin de la partie
        scoreManager.resetHits(); //reset le multiplicateur
    }

    /**
     * Boucle principale du jeu.
     * Met à jour et dessine les éléments du jeu, gère les collisions et vérifie les conditions de fin de partie.
     */
    public void loop() {
        gc.setFill(Color.BLACK); //important !!!! 
        //important !!!! à laisser car sa permet d'actualiser le fond noir (ce qui remet a jour l'image et supprime les superposition)
        gc.fillRect(0, 0, width, height);

        // Gère le rebond des balles sur la raquette

        // Vérifie si les balles touchent les bordures du jeu
        modeDeJeu.getBoard().FrameBoard10ms();

        // Affiche le score
        gc.setFont(new Font("Arial", 24)); // Police et taille du texte
        scoreManager.drawBestScore(gc, 10, height * 0.45); //dessine le meilleur Score
        scoreManager.drawScore(gc, 10, height * 0.5); //dessine le Score
        scoreManager.drawMultiplier(gc, width, height); // Affichage du multiplicateur s'il est actif 
           
        if (modeDeJeu.getBoard().ConditionNivSup()) {
            System.out.println("fait initloopboard");
            modeDeJeu.Niv_sup_Endless(p);
            p.getChildren().add(modeDeJeu.getBoard().getp());
        }
    }
}