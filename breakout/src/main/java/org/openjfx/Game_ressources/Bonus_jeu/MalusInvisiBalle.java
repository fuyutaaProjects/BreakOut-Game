package org.openjfx.Game_ressources.Bonus_jeu;

import java.util.Timer;
import java.util.TimerTask;

import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Raquette;

import javafx.scene.paint.Color;

/**
 * Classe représentant un malus qui rend la balle invisible temporairement.
 */
public class MalusInvisiBalle extends Bonus {
    private Color couleur; // Couleur initiale du bonus.

    /**
     * Constructeur de la classe MalusInvisiBalle.
     * 
     * @param posX    Position X du bonus.
     * @param posY    Position Y du bonus.
     * @param vitesse Vitesse de déplacement du bonus.
     * @param taille  Taille du bonus.
     */
    public MalusInvisiBalle(double posX, double posY, double vitesse, double taille) {
        super(posX, posY, vitesse, taille);
        this.couleur = Color.RED;
        super.cercle.setFill(couleur);
    }

    /**
     * Active le malus sur la balle en la rendant invisible temporairement.
     * 
     * @param balle La balle sur laquelle activer le malus.
     */
    @Override
    public void activeBonus(Balle balle) {
        if (balle != null) {
            // Rendre la balle invisible
            balle.getOval().setOpacity(0.0);

            // Restaurer la visibilité après un délai
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    balle.getOval().setOpacity(1.0);
                }
            }, 2000); // Durée d'invisibilité en millisecondes
        }
    }

    /**
     * Active le malus sur la raquette (non utilisé ici).
     * 
     * @param raquette La raquette sur laquelle activer le malus.
     * @param temps    La durée pendant laquelle le malus est actif.
     */
    @Override
    public void activeBonus(Raquette raquette, long temps) {
        // Aucun effet sur la raquette pour ce malus.
    }
}
