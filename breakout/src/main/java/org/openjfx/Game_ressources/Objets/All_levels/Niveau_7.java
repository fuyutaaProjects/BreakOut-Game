package org.openjfx.Game_ressources.Objets.All_levels;

import java.util.Random;

import org.openjfx.BreakOut;
import org.openjfx.Game_ressources.Bonus_jeu.*;
import org.openjfx.Game_ressources.Objets.Briques.*;

/**
 * Classe représentant le niveau 7 du jeu.
 */
public class Niveau_7 extends Niveaux {

    @Override
    public void init_board() {
        double hexagonSize = 50; // Taille moyenne des côtés de chaque hexagone
        double spacing = 2; // Espacement minimal entre les hexagones

        double width = BreakOut.getWidth();
        double height = BreakOut.getHeight();

        double hexagonHeight = Math.sqrt(3) * hexagonSize; // Hauteur d'un hexagone
        double hexagonWidth = 2 * hexagonSize; // Largeur d'un hexagone
        int rows = (int) (height / (hexagonHeight + spacing));
        int cols = (int) (width / (hexagonWidth * 0.75 + spacing));

        // Générer une grille d'hexagones couvrant tout le plateau
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double xOffset = col * (hexagonWidth * 0.75 + spacing);
                double yOffset = row * (hexagonHeight + spacing);

                // Décalage horizontal pour les lignes impaires (grille en quinconce)
                if (row % 2 != 0) {
                    xOffset += hexagonWidth * 0.375;
                }

                // Génération des coordonnées des sommets de l'hexagone
                double[][] hexagon = generateHexagon(xOffset, yOffset, hexagonSize);

                // Ajouter l'hexagone à la liste des briques
                super.terrain.add(new Briques_personnalise(hexagon, 100, 1, genereBonus(xOffset, yOffset)));
            }
        }
    }

    /**
     * Génère les coordonnées d'un hexagone régulier.
     * 
     * @param centerX Centre en X de l'hexagone.
     * @param centerY Centre en Y de l'hexagone.
     * @param size Taille des côtés de l'hexagone.
     * @return Tableau 2D des coordonnées des sommets de l'hexagone.
     */
    private double[][] generateHexagon(double centerX, double centerY, double size) {
        double[][] hexagon = new double[6][2];
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i); // Diviser le cercle en 6 angles
            hexagon[i][0] = centerX + size * Math.cos(angle);
            hexagon[i][1] = centerY + size * Math.sin(angle);
        }
        return hexagon;
    }

    /**
     * Génère un bonus ou malus basé sur des probabilités.
     * 
     * @param posX Position X du bonus.
     * @param posY Position Y du bonus.
     * @return Bonus ou Malus selon des probabilités prédéfinies.
     */
    public Bonus genereBonus(double posX, double posY) {
        if (posX < 0 || posX > BreakOut.getWidth() || posY < 0 || posY > BreakOut.getHeight()) {
            throw new IllegalArgumentException("Coordonnées en dehors de l'écran");
        } else {
            Random random = new Random();
            int numBonus = random.nextInt(101);

            if (numBonus >= 0 && numBonus <= 5) {
                return null;
            } else if (numBonus > 5 && numBonus <= 15) {
                return new BonusPlusBalles(posX, posY, 1, 20);
            } else if (numBonus > 15 && numBonus <= 30) {
                return null;
            } else if (numBonus > 30 && numBonus <= 70) {
                return null;
            } else if (numBonus > 70 && numBonus <= 100) {
                return null;
            }
            return null;
        }
    }
}
