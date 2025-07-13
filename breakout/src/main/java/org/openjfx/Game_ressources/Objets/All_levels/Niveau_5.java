package org.openjfx.Game_ressources.Objets.All_levels;

import java.util.Random;

import org.openjfx.BreakOut;
import org.openjfx.Game_ressources.Bonus_jeu.*;
import org.openjfx.Game_ressources.Objets.Briques.*;

/**
 * Classe représentant le niveau 5 du jeu.
 */
public class Niveau_5 extends Niveaux {

    @Override
    public void init_board() {
        double largeur = BreakOut.getWidth() / 8; // Briques légèrement plus longues
        double hauteur = 20;

        // Générer deux lignes de briques
        ligne(8, 0, 0, largeur, hauteur, 4, 5);
        ligne(8, 0, 100, largeur, hauteur, 4, 5);

        // Ajout de briques personnalisées (polygones) entre les deux lignes
        double[][] poly1 = {{100, 60}, {120, 80}, {140, 100}, {160, 80}, {140, 60}};
        double[][] poly2 = {{300, 60}, {320, 80}, {340, 100}, {360, 80}, {340, 60}};
        double[][] poly3 = {{500, 60}, {520, 80}, {540, 100}, {560, 80}, {540, 60}};
        double[][] poly4 = {{700, 60}, {720, 80}, {740, 100}, {760, 80}, {740, 60}};
        double[][] poly5 = {{900, 60}, {920, 80}, {940, 100}, {960, 80}, {940, 60}};
        super.terrain.add(new Briques_personnalise(poly1, 60, 4, genereBonus(120, 70)));
        super.terrain.add(new Briques_personnalise(poly2, 60, 4, genereBonus(320, 70)));
        super.terrain.add(new Briques_personnalise(poly3, 60, 4, genereBonus(520, 70)));
        super.terrain.add(new Briques_personnalise(poly4, 60, 4, genereBonus(720, 70)));
        super.terrain.add(new Briques_personnalise(poly5, 60, 4, genereBonus(920, 70)));

        
    }

    /**
     * Ajoute une ligne de briques rectangulaires.
     * 
     * @param nb       Nombre de briques dans la ligne.
     * @param x        Position x initiale de la première brique.
     * @param y        Position y de la ligne.
     * @param width    Largeur des briques.
     * @param height   Hauteur des briques.
     * @param resist   Résistance des briques.
     * @param scoreVal Valeur en points des briques.
     */
    public void ligne(int nb, double x, double y, double width, double height, int resist, int scoreVal) {
        for (int i = 0; i < nb; i++) {
            super.terrain.add(new Briques_rectangle(x, y, width, height, scoreVal, resist, genereBonus(x, y)));
            x += width + 2; // Espacement entre les briques
        }
    }

    /**
     * Génère un bonus ou un malus basé sur un pourcentage.
     * 
     * @param posX Position x du bonus/malus.
     * @param posY Position y du bonus/malus.
     * @return Un objet de type Bonus ou null si aucun n'est généré.
     */
    public Bonus genereBonus(double posX, double posY) {
        if (posX < 0 || posX > BreakOut.getWidth() || posY < 0 || posY > BreakOut.getHeight()) {
            throw new IllegalArgumentException("Coordonnées en dehors de l'écran");
        } else {
            Random random = new Random();
            int numBonus = random.nextInt(101); // Nombre aléatoire entre 0 et 100 inclus

            if (numBonus >= 0 && numBonus <= 10) { // 10% de chance de ne rien avoir
                return null;
            } else if (numBonus > 10 && numBonus <= 30) { // 20% de chance pour BonusAugLagRaq
                return new BonusAugLagRaq(posX, posY, 1, 20, 50);
            } else if (numBonus > 30 && numBonus <= 50) { // 20% de chance pour BonusPlusBalles
                return new BonusPlusBalles(posX, posY, 1, 20);
            } else if (numBonus > 50 && numBonus <= 70) { // 20% de chance pour MalusTailleBalle
                return new MalusTailleBalle(posX, posY, 1, 15);
            } else if (numBonus > 70 && numBonus <= 90) { // 20% de chance pour MalusVitesseBalle
                return new MalusVitesseBalle(posX, posY, 1, 10);
            }
            return null;
        }
    }
}
