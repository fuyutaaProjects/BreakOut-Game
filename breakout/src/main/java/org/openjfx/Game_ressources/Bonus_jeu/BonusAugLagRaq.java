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
 * Classe représentant un bonus qui augmente la largeur de la raquette.
 */
public class BonusAugLagRaq extends Bonus {
    // Augmentation de la largeur de la raquette
    private double augmentation;
    // Couleur du bonus
    private Color couleur;

    /**
     * Constructeur de la classe BonusAugLagRaq.
     * 
     * @param posX Position X du bonus.
     * @param posY Position Y du bonus.
     * @param vitesse Vitesse de déplacement du bonus.
     * @param taille Taille du bonus.
     * @param augmentation Augmentation de la largeur de la raquette.
     */
    public BonusAugLagRaq(double posX, double posY, double vitesse, double taille, double augmentation) {
        super(posX, posY, vitesse, taille);
        this.augmentation = augmentation;
        this.couleur = Color.AQUA;
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
        //Fonction permettant d'activer le bonus pendant 5secondes.
        if (raquette != null) {
            raquette.setLargeur(raquette.getLargeur()+augmentation);
            //Gestion des 5 secondes
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    raquette.setLargeur(raquette.getLargeur()-augmentation);
                }
            }, temps);    //5000ms = 5s
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