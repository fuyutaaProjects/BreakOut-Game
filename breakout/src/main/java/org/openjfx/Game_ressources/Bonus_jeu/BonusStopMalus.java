package org.openjfx.Game_ressources.Bonus_jeu;

import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Raquette;

import javafx.scene.paint.Color;

/**
 * Bonus qui arrête tous les malus en cours.
 */
public class BonusStopMalus extends Bonus {
    // Couleur du bonus
    private Color couleur;

    public BonusStopMalus(double posX, double posY, double vitesse, double taille) {
        super(posX, posY, vitesse, taille);
        this.couleur = Color.AQUA;
        super.cercle.setFill(couleur);
    }

    @Override
    public void activeBonus(Raquette raquette, long temps) {
        // Arrêter tous les malus liés à la raquette
        arreterMalusRaquette(raquette);
    }

    @Override
    public void activeBonus(Balle balle) {
        // Arrêter tous les malus liés à la balle
        arreterMalusBalle(balle);
    }

    /**
     * Arrête les malus actifs sur la raquette.
     * 
     * @param raquette La raquette à réinitialiser.
     */
    private void arreterMalusRaquette(Raquette raquette) {
        if (raquette != null) {
            // Réinitialise la taille de la raquette si elle est modifiée
            raquette.setLargeur(100); // Taille normale par défaut

            // Rendre la raquette visible si elle était invisible
            raquette.getCorp().setVisible(true);

            // Réinitialise tout autre état malus
            raquette.setInverted(false); // La raquette n'est plus inversée
        }
    }

    /**
     * Arrête les malus actifs sur la balle.
     * 
     * @param balle La balle à réinitialiser.
     */
    private void arreterMalusBalle(Balle balle) {
        if (balle != null) {
            // Réinitialise la taille de la balle si elle était réduite
            balle.setTaille(10); // Rayon normal par défaut

            // Rendre la balle visible si elle était invisible
            balle.getOval().setVisible(true);
        }
    }
}

