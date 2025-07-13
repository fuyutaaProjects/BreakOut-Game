package org.openjfx.Game_ressources.Bonus_jeu;

import java.util.Timer;
import java.util.TimerTask;

import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Raquette;

import javafx.scene.paint.Color;

/**
 * Bonus qui augmente la taille de la balle.
 */
public class BonusBigBall extends Bonus {
    // Couleur du bonus
    private Color couleur;

    public BonusBigBall(double posX, double posY, double vitesse, double taille) {
        super(posX, posY, vitesse, taille);
        this.couleur = Color.AQUA;
        super.cercle.setFill(couleur);
    }

    @Override
    public void activeBonus(Balle balle) {
        if (balle != null) {
            double tailleInitiale = balle.getTaille();
            balle.setTaille(tailleInitiale * 1.5);

            // Remettre la taille initiale après une durée définie
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    balle.setTaille(tailleInitiale);
                }
            }, 6969);
        }
    }

    @Override
    public void activeBonus(Raquette raquette, long temps) {
        // Ce bonus n'affecte pas les raquettes
    }
}
