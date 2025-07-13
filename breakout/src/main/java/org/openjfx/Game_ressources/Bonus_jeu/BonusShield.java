package org.openjfx.Game_ressources.Bonus_jeu;

import java.util.Timer;
import java.util.TimerTask;

import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Raquette;

import javafx.scene.paint.Color;

/**
 * Bonus qui transforme la raquette en une raquette géante couvrant toute la largeur de l'écran.
 */
public class BonusShield extends Bonus {
    // Couleur du bonus
    private Color couleur;

    public BonusShield(double posX, double posY, double vitesse, double taille) {
        super(posX, posY, vitesse, taille);
        this.couleur = Color.AQUA;
        super.cercle.setFill(couleur);
    }

    @Override
    public void activeBonus(Raquette raquette, long temps) {
        if (raquette != null) {
            double largeurInitiale = raquette.getLargeur(); // Sauvegarde la largeur actuelle de la raquette
            double largeurEcran = 800; // Remplacer par la largeur de votre écran de jeu

            // Étend la raquette pour couvrir toute la largeur de l'écran
            raquette.setLargeur(largeurEcran);
            raquette.setCouleur(Color.MAGENTA); // Change la couleur pour indiquer l'effet

            // Réinitialise la raquette après la durée définie
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    raquette.setLargeur(largeurInitiale); // Restaure la largeur initiale
                    raquette.setCouleur(Color.WHITE); // Restaure la couleur originale
                }
            }, temps); // Durée en millisecondes
        }
    }

    @Override
    public void activeBonus(Balle balle) {
        // Ce bonus n'affecte pas la balle
    }
}
