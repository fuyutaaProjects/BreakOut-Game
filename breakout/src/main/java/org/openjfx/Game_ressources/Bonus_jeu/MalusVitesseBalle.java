package org.openjfx.Game_ressources.Bonus_jeu;

import java.util.Timer;
import java.util.TimerTask;

import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Raquette;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Classe représentant un bonus qui reduit la vitesse de la balle.
 */
public class MalusVitesseBalle extends Bonus {
    // Couleur du bonus
    private Color couleur;

    /**
     * Constructeur de la classe MalusVitesseBalle.
     * 
     * @param posX Position X du bonus.
     * @param posY Position Y du bonus.
     * @param vitesse Vitesse de déplacement du bonus.
     * @param taille Taille du bonus.
     */
    public MalusVitesseBalle(double posX, double posY, double vitesse, double taille) {
        super(posX, posY, vitesse, taille);
        this.couleur = Color.RED;
        super.cercle.setFill(couleur);
    }

    /**
     * Active le bonus sur la raquette.
     * 
     * @param raquette La raquette sur laquelle activer le bonus.
     */
    @Override
    public void activeBonus(Raquette raquette, long temps) {
        // Vide car inutile d'activer le bonus sur la raquette
    }

    /**
     * Active le bonus sur la balle.
     * 
     * @param balle La balle sur laquelle activer le bonus.
     */
    @Override
    public void activeBonus(Balle balle) {
        if (balle != null) {
            //Reduction de la vitesse de la balle
            balle.setVitesseX(balle.getVitesseX() * 0.5);
            balle.setVitesseY(balle.getVitesseY() * 0.5);
            
            //Gestion des 5 secondes
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    //remise a la vitesse normale
                    balle.setVitesseX(balle.getVitesseX() * 2);
                    balle.setVitesseY(balle.getVitesseY() * 2);
                }
            }, 10000);
        }
    }
}