package org.openjfx.Game_ressources.Objets.All_levels;

import java.util.Random;

import org.openjfx.BreakOut;
import org.openjfx.Game_ressources.Bonus_jeu.*;
import org.openjfx.Game_ressources.Objets.Briques.*;

/**
 * Classe représentant le niveau 1 du jeu.
 */
public class Niveau_1 extends Niveaux {
    @Override
    public void init_board() {
        double largeur = BreakOut.getWidth() / 10;
        ligne(5, 0, 0, largeur);
        ligne(5, 0, 150, largeur);
        ligne(5, (largeur + 2) * 5 + 50, 0, largeur);
        ligne(5, (largeur + 2) * 5 + 50, 150, largeur);

    }

    public void ligne(int nb, double x, double y, double width) {
        for (int i = 0; i < nb; i++) {
            super.terrain.add(new Briques_rectangle(x, y, width, 20, 20, 2, genereBonus(x, y)));
            x += width + 2;
        }
    }

    public Bonus genereBonus(double posX, double posY) {
        if (posX < 0 || posX > BreakOut.getWidth() || posY < 0 || posY > BreakOut.getHeight()) {
            throw new IllegalArgumentException("Coordonnées en dehors de l'écran");
        } else {
            Random random = new Random();
            int numBonus = random.nextInt(101);
            if (numBonus >= 0 && numBonus <= 20) {
                return null;
            } else if (numBonus > 20 && numBonus < 50) {
                return new BonusAugLagRaq(posX, posY, 1, 20, 50);
            } else if (numBonus >= 50 && numBonus <= 80) {
                return new BonusPlusBalles(posX, posY, 1, 20);
            }
            return null;
        }
    }
}
