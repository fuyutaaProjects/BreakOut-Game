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
 * Classe représentant le malus qui inverse la raquette
 */
public class MalusInvRaq extends Bonus {
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
    public MalusInvRaq(double posX, double posY, double vitesse, double taille) {
        super(posX, posY, vitesse, taille);
        this.couleur = Color.RED;
        super.cercle.setFill(couleur);
    }

    /**
     * Active le bonus sur la raquette.
     * 
     * @param raquette La raquette sur laquelle activer le bonus.
     * @param temps La durée pendant laquelle le bonus est actif.
     */
    @Override
    public void activeBonus(Raquette raquette, long temps) {
        //Fonction permettant d'activer le malus pendant 5secondes.
        raquette.setInverted(true);
        if (raquette != null) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    raquette.setInverted(false);
                }
            }, temps);
        }
    }

    /**
     * Active le bonus sur la balle.
     * 
     * @param balle La balle sur laquelle activer le bonus.
     */
    @Override
    public void activeBonus(Balle balle) {
        // On laisse vide car on ne souhaite pas activer un bonus sur la balle.
    }
}
