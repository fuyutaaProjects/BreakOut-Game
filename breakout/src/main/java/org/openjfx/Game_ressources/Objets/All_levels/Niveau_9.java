package org.openjfx.Game_ressources.Objets.All_levels;

import java.util.Random;

import org.openjfx.BreakOut;
import org.openjfx.Game_ressources.Bonus_jeu.*;
import org.openjfx.Game_ressources.Objets.Briques.*;

/**
 * Classe représentant le niveau 9 du jeu.
 */
public class Niveau_9 extends Niveaux {

    @Override
    public void init_board() {
        double largeurFenetre = BreakOut.getWidth();
        double largeurBrique = BreakOut.getWidth() / 12; // Largeur des briques incassables
        double hauteurBrique = 20;

        // Première ligne d'étoiles (remplie sur toute la largeur de la fenêtre)
        double y1 = 50;
        ajouterLigneEtoiles(y1, largeurFenetre);

        // Ligne de briques incassables (plus espacées)
        double y2 = 200;
        ajouterBriquesIncassables(y2, largeurFenetre, largeurBrique, hauteurBrique);

        // Deuxième ligne d'étoiles (descendue davantage)
        double y3 = 450;
        ajouterLigneEtoiles(y3, largeurFenetre);
    }

    /**
     * Ajoute une ligne d'étoiles couvrant toute la largeur de la fenêtre.
     *
     * @param y Position y de la ligne.
     * @param largeurFenetre Largeur totale de la fenêtre.
     */
    private void ajouterLigneEtoiles(double y, double largeurFenetre) {
        double tailleEtoile = 50; // Taille de chaque étoile
        double espacement = 20; // Espacement entre les étoiles
        double largeurTotaleEtoile = tailleEtoile * 2 + espacement; // Largeur d'une étoile avec espacement

        int nombreEtoiles = (int) (largeurFenetre / largeurTotaleEtoile);
        double offsetX = (largeurFenetre - (nombreEtoiles * largeurTotaleEtoile)) / 2; // Centrer les étoiles

        for (int i = 0; i < nombreEtoiles; i++) {
            double x = offsetX + i * largeurTotaleEtoile;
            ajouterEtoile(x, y, tailleEtoile);
        }
    }

    /**
     * Ajoute des briques incassables espacées uniformément.
     *
     * @param y Position y de la ligne de briques.
     * @param largeurFenetre Largeur totale de la fenêtre.
     * @param largeurBrique Largeur d'une brique.
     * @param hauteurBrique Hauteur d'une brique.
     */
    private void ajouterBriquesIncassables(double y, double largeurFenetre, double largeurBrique, double hauteurBrique) {
        int nombreBriques = 4; // Nombre de briques incassables
        double espacement = (largeurFenetre - (nombreBriques * largeurBrique)) / (nombreBriques + 1);

        for (int i = 0; i < nombreBriques; i++) {
            double x = espacement + i * (largeurBrique + espacement);
            super.terrain.add(new Briques_rectangle(x, y, largeurBrique, hauteurBrique, 0, -1, null));
        }
    }

    /**
     * Ajoute une étoile (polygone) au terrain.
     *
     * @param centerX Coordonnée x du centre de l'étoile.
     * @param centerY Coordonnée y du centre de l'étoile.
     * @param size Taille de l'étoile (distance du centre aux pointes).
     */
    private void ajouterEtoile(double centerX, double centerY, double size) {
        double innerSize = size * 0.5; // Taille des branches internes
        int branches = 5; // Nombre de branches pour une étoile normale
        double[][] points = new double[branches * 2][2]; // Sommet des points de l'étoile

        for (int i = 0; i < branches * 2; i++) {
            double angle = Math.PI / branches * i; // Alternance entre les sommets
            double radius = (i % 2 == 0) ? size : innerSize;
            points[i][0] = centerX + Math.cos(angle - Math.PI / 2) * radius;
            points[i][1] = centerY + Math.sin(angle - Math.PI / 2) * radius;
        }

        super.terrain.add(new Briques_personnalise(points, 100, 6, genereBonus(centerX, centerY)));
    }

    /**
     * Génère des bonus ou des malus selon les probabilités.
     *
     * @param posX Position x du bonus.
     * @param posY Position y du bonus.
     * @return Un objet Bonus ou null.
     */
    public Bonus genereBonus(double posX, double posY) {
        if (posX < 0 || posX > BreakOut.getWidth() || posY < 0 || posY > BreakOut.getHeight()) {
            throw new IllegalArgumentException("Coordonnées en dehors de l'écran");
        } else {
            Random random = new Random();
            int numBonus = random.nextInt(101);

            if (numBonus >= 0 && numBonus <= 10) {
                return null;
            } else if (numBonus > 10 && numBonus <= 30) {
                return new BonusAugLagRaq(posX, posY, 1, 20, 50);
            } else if (numBonus > 30 && numBonus <= 50) {
                return new BonusPlusBalles(posX, posY, 1, 20);
            } else if (numBonus > 50 && numBonus <= 70) {
                return new MalusTailleBalle(posX, posY, 1, 15);
            } else if (numBonus > 70 && numBonus <= 90) {
                return new MalusVitesseBalle(posX, posY, 1, 10);
            }
            return null;
        }
    }
}
