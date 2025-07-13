package org.openjfx.Game_ressources.Bonus_jeu;

import java.util.Timer;
import java.util.TimerTask;

import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Raquette;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Classe représentant un malus qui réduit la taille de la balle.
 */
public class MalusTailleBalle extends Bonus {

    // Couleur du bonus
    private Color couleur;

    /**
     * Constructeur de la classe MalusTailleBalle.
     * 
     * @param posX Position X du bonus.
     * @param posY Position Y du bonus.
     * @param vitesse Vitesse de déplacement du bonus.
     * @param taille Taille du bonus.
     */
    public MalusTailleBalle(double posX, double posY, double vitesse, double taille) {
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
            //Reduction de la taille de la balle
            balle.setTaille(10);

            //Gestion des 5 secondes
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    //remise a la taille normale
                    balle.setTaille(20);
                }
            }, 10000);    //5000ms = 5s
        }
    }
}
