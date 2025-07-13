package org.openjfx.Game_ressources.Objets.All_levels;

import java.util.Random;

import org.openjfx.BreakOut;
import org.openjfx.Game_ressources.Bonus_jeu.*;
import org.openjfx.Game_ressources.Objets.Briques.*;

import javafx.scene.paint.Color;

/**
 * Classe représentant le niveau 10 du jeu.
 */
public class Niveau_10 extends Niveaux {

    public Niveau_10() {
        super.terrain.addLabel("Merci d'avoir joué au Jeu", 150, 60, Color.WHITE);
    }

    @Override
    public void init_board() {
        double largeurBrique = BreakOut.getWidth() / 10; // Largeur de la brique
        double hauteurBrique = 20; // Hauteur de la brique
        double xPosition = BreakOut.getWidth() / 2 - largeurBrique / 2; // Position centrale pour la brique cassable


        super.terrain.add(new Briques_rectangle(xPosition, 50, largeurBrique, hauteurBrique, 1, 1, genereBonus(xPosition, 50)));

       
    }

    /**
     * Génère un bonus ou malus aléatoire pour ce niveau.
     *
     * @param posX Position x du bonus.
     * @param posY Position y du bonus.
     * @return Un bonus ou null.
     */
    public Bonus genereBonus(double posX, double posY) {
        Random random = new Random();
        int numBonus = random.nextInt(101); // Probabilité aléatoire

        if (numBonus >= 0 && numBonus <= 30) {
            return null; // 30% de chance de ne rien générer
        } else if (numBonus > 30 && numBonus <= 60) {
            return new BonusAugLagRaq(posX, posY, 1, 20, 50); // 30% chance d'un bonus
        } else {
            return new BonusPlusBalles(posX, posY, 1, 20); // 40% chance d'un autre bonus
        }
    }
}

