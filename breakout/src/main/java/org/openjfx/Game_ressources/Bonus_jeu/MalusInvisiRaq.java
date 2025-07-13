package org.openjfx.Game_ressources.Bonus_jeu;

import java.util.Timer;
import java.util.TimerTask;

import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Raquette;

import javafx.scene.paint.Color;

/**
 * Classe représentant un malus qui rend la raquette invisible temporairement.
 */
public class MalusInvisiRaq extends Bonus {
    private Color couleur; // Couleur initiale du bonus.

    /**
     * Constructeur de la classe MalusRaqInvisible.
     * 
     * @param posX    Position X du bonus.
     * @param posY    Position Y du bonus.
     * @param vitesse Vitesse de déplacement du bonus.
     * @param taille  Taille du bonus.
     */
    public MalusInvisiRaq(double posX, double posY, double vitesse, double taille) {
        super(posX, posY, vitesse, taille);
        this.couleur = Color.RED;
        super.cercle.setFill(couleur);
    }

    /**
     * Active le malus en rendant la raquette invisible temporairement.
     * 
     * @param raquette La raquette sur laquelle activer le malus.
     * @param temps    La durée pendant laquelle le malus est actif.
     */
    @Override
    public void activeBonus(Raquette raquette, long temps) {
        if (raquette != null) {
            // Rendre la raquette invisible
            raquette.getCorp().setOpacity(0.0);

            // Revenir à la visibilité normale après un délai
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    raquette.getCorp().setOpacity(1.0);
                }
            }, temps); // Le temps est en millisecondes
        }
    }

    /**
     * Active le malus sur la balle (non utilisé ici).
     * 
     * @param balle La balle sur laquelle activer le malus.
     */
    @Override
    public void activeBonus(Balle balle) {
        // Aucun effet sur la balle pour ce malus.
    }
}
