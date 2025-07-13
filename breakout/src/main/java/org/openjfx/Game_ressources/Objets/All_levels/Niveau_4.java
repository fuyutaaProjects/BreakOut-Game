package org.openjfx.Game_ressources.Objets.All_levels;

import java.util.Random;

import org.openjfx.BreakOut;
import org.openjfx.Game_ressources.Bonus_jeu.*;
import org.openjfx.Game_ressources.Objets.Briques.*;

/**
 * Classe représentant le niveau 4 du jeu.
 */
public class Niveau_4 extends Niveaux {
    @Override
    public void init_board() {
        double largeur = BreakOut.getWidth() / 14;
        ligne(7, 0, 0, largeur);
        ligne(7, 0, 120, largeur);
        ligne(7, (largeur + 2) * 7, 0, largeur);
        ligne(7, (largeur + 2) * 7, 120, largeur);
    }
    //     super.terrain.add(new Briques_rectangle(80, 80, largeur, 20, 40, 4, genereBonus(80, 80)));
    //     super.terrain.add(new Briques_rectangle(80, 130, largeur, 20, 40, 4, genereBonus(80, 130)));
    // }

    public void ligne(int nb, double x, double y, double width) {
        for (int i = 0; i < nb; i++) {
            super.terrain.add(new Briques_rectangle(x, y, width, 20, 40, 4, genereBonus(x, y)));
            x += width + 2;
        }
    }

    public Bonus genereBonus(double posX, double posY) {
        if (posX < 0 || posX > BreakOut.getWidth() || posY < 0 || posY > BreakOut.getHeight()) {
            throw new IllegalArgumentException("Coordonnées en dehors de l'écran");
        } else {
            Random random = new Random();
            int numBonus = random.nextInt(101);
            if (numBonus >= 0 && numBonus <= 15) {
                return null;
            } else if (numBonus > 15 && numBonus <= 40) {
                return new BonusAugLagRaq(posX, posY, 1, 20, 50);
            } else if (numBonus > 40 && numBonus <= 70) {
                return new BonusPlusBalles(posX, posY, 1, 20);
            }
            return null;
        }
    }
}
