package org.openjfx.Game_ressources.Objets.All_levels;

import org.openjfx.BreakOut;
import org.openjfx.Game_ressources.Bonus_jeu.*;
import org.openjfx.Game_ressources.Objets.Briques.Briques_personnalise;
import org.openjfx.Game_ressources.Objets.Briques.Briques_rectangle;

import java.util.Random;

public class Niveau_Endless extends Niveaux {

    @Override
    public void init_board() {
        double largeur = BreakOut.getWidth() / 10;
        double hauteur = 20;
        Random random = new Random();

        // Générer des lignes de briques rectangulaires avec une résistance aléatoire
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                int resistance = random.nextInt(5) + 1; // Résistance aléatoire entre 1 et 5
                super.terrain.add(new Briques_rectangle(j * (largeur + 2), i * (hauteur + 2), largeur, hauteur, 10, resistance, genereBonus(j * (largeur + 2), i * (hauteur + 2))));
            }
        }

        // Ajouter des briques personnalisées (polygones) à des positions aléatoires
        double[][][] predefinedPolygons = getPredefinedPolygons();
        for (double[][] points : predefinedPolygons) {
            int resistance = random.nextInt(10) + 5; // Résistance aléatoire entre 5 et 15
            double posX = random.nextDouble() * (BreakOut.getWidth() - 100) + 50; // Position X aléatoire
            double posY = random.nextDouble() * (BreakOut.getHeight() - 100) + 50; // Position Y aléatoire
            super.terrain.add(new Briques_personnalise(points, resistance, 10, genereBonus(posX, posY)));
        }
    }

    /**
     * Génère un ensemble de polygones prédéfinis.
     * 
     * @return Tableau 3D des coordonnées des sommets des polygones.
     */
    private double[][][] getPredefinedPolygons() {
        return new double[][][] {
            {
                {101.0, 135.0},
                {48.0, 102.0},
                {100.0, 225.0},
                {170.0, 84.0},
                {213.0, 189.0},
                {267.0, 104.0},
                {336.0, 189.0},
                {426.0, 83.0},
                {483.0, 201.0},
                {564.0, 87.0},
                {643.0, 192.0},
                {733.0, 86.0},
                {786.0, 191.0},
                {845.0, 89.0},
                {900.0, 173.0},
                {922.0, 103.0},
                {961.0, 156.0},
                {985.0, 98.0}
            },
            {
                {300.0, 260.0},
                {320.0, 280.0},
                {340.0, 300.0},
                {360.0, 280.0},
                {340.0, 260.0}
            },
            {
                {400.0, 360.0},
                {420.0, 380.0},
                {440.0, 400.0},
                {460.0, 380.0},
                {440.0, 360.0}
            },
            {
                {500.0, 460.0},
                {520.0, 480.0},
                {540.0, 500.0},
                {560.0, 480.0},
                {540.0, 460.0}
            }
        };
    }

    /**
     * Génère un bonus ou un malus de manière aléatoire avec une probabilité égale pour chaque type.
     * 
     * @param posX Position x du bonus.
     * @param posY Position y du bonus.
     * @return Un objet Bonus ou null.
     */
    public Bonus genereBonus(double posX, double posY) {
        Random random = new Random();
        int numBonus = random.nextInt(12); // Nombre entre 0 (inclus) et 12 (exclus)

        switch (numBonus) {
            case 0:
                return new BonusAugLagRaq(posX, posY, 1, 20, 50);
            case 1:
                return new BonusPlusBalles(posX, posY, 1, 20);
            case 2:
                return new BonusBigBall(posX, posY, 1, 20);
            case 3:
                return new BonusShield(posX, posY, 1, 20);
            case 4:
                return new BonusStopMalus(posX, posY, 1, 20);
            case 5:
                return new MalusInvRaq(posX, posY, 1, 20);
            case 6:
                return new MalusTailleBalle(posX, posY, 1, 20);
            case 7:
                return new MalusVitesseBalle(posX, posY, 1, 20);
            case 8:
                return new BonusAugLagRaq(posX, posY, 1, 20, 50); // Duplicate for demonstration
            case 9:
                return new BonusPlusBalles(posX, posY, 1, 20); // Duplicate for demonstration
            case 10:
                return new BonusBigBall(posX, posY, 1, 20); // Duplicate for demonstration
            case 11:
                return new BonusShield(posX, posY, 1, 20); // Duplicate for demonstration
            default:
                return null;
        }
    }
}