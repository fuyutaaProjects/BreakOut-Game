package org.openjfx.Game_ressources.Bonus_jeu;

import java.util.Random;

import org.openjfx.BreakOut;
import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Raquette;

import javafx.scene.paint.Color;

/**
 * Classe représentant un malus qui téléporte la balle à une position aléatoire.
 */
public class MalusBalleTeleport extends Bonus {
    private Color couleur; // Couleur initiale du bonus.
    private Random random; // Générateur de positions aléatoires.

    /**
     * Constructeur de la classe MalusBalleTeleport.
     * 
     * @param posX    Position X du bonus.
     * @param posY    Position Y du bonus.
     * @param vitesse Vitesse de déplacement du bonus.
     * @param taille  Taille du bonus.
     */
    public MalusBalleTeleport(double posX, double posY, double vitesse, double taille) {
        super(posX, posY, vitesse, taille);
        this.couleur = Color.RED;
        super.cercle.setFill(couleur);
        this.random = new Random();
    }

    /**
     * Active le malus sur la balle en la téléportant à une position aléatoire.
     * 
     * @param balle La balle sur laquelle activer le malus.
     */
    @Override
    public void activeBonus(Balle balle) {
        if (balle != null) {
            // Générer de nouvelles coordonnées aléatoires dans les limites du plateau
            double newX = random.nextDouble() * (BreakOut.getWidth() - balle.getRayon() * 2) + balle.getRayon();
            double newY = random.nextDouble() * (BreakOut.getHeight() / 2 - balle.getRayon() * 2) + balle.getRayon();

            // Mettre à jour la position de la balle
            balle.setPosX(newX);
            balle.setPosY(newY);
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
