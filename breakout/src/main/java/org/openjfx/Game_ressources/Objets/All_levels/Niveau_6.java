package org.openjfx.Game_ressources.Objets.All_levels;

import java.util.Random;

import org.openjfx.BreakOut;
import org.openjfx.Game_ressources.Bonus_jeu.*;
import org.openjfx.Game_ressources.Objets.Briques.*;

import javafx.scene.paint.Color;

/**
 * Classe représentant le niveau 6 du jeu.
 */
public class Niveau_6 extends Niveaux {

    @Override
    public void init_board() {
        // double largeur = BreakOut.getWidth() / 14; // Briques légèrement plus longues
        // double hauteur = 20;
        super.terrain.addLabel("Appréciez le spectacle !", 150, 10, Color.WHITE);
        
        double[][] points = {
            {294.4, 335.2},  // 235.2 + 100
            {651.2, 337.6},  // 237.6 + 100
            {450.4, 583.2},  // 483.2 + 100
            {451.2, 548.0},  // 448.0 + 100
            {607.2, 359.2},  // 259.2 + 100
            {318.4, 359.2},  // 259.2 + 100
            {452.0, 550.4},  // 450.4 + 100
            {450.4, 580.0},  // 480.0 + 100
            {295.2, 337.6},  // 237.6 + 100
            {288.8, 337.6}   // 237.6 + 100
        };      
       // Premier polygone 
// Premier polygone 
// Premier polygone 
// Premier polygone 
double[][] points1 = {
    {411.8, 444.0},  // 316.8 + 95
    {555.0, 205.6},  // 460.0 + 95
    {660.6, 444.0},  // 565.6 + 95
    {631.8, 443.2},  // 536.8 + 95
    {547.8, 258.4},  // 452.8 + 95
    {446.2, 414.4},  // 351.2 + 95
    {625.4, 416.8},  // 530.4 + 95
    {658.2, 443.2}   // 563.2 + 95
};

// Deuxième polygone
double[][] points2 = {
    {397.4, 251.2},  // 302.4 + 95
    {680.6, 248.8},  // 585.6 + 95
    {522.2, 525.6},  // 427.2 + 95
    {522.2, 480.0},  // 427.2 + 95
    {635.8, 276.0},  // 540.8 + 95
    {433.4, 271.2},  // 338.4 + 95
    {526.2, 487.2},  // 431.2 + 95
    {522.2, 524.0}   // 427.2 + 95
};
    super.terrain.add(new Briques_personnalise(points1, 40, 1000, null));
    super.terrain.add(new Briques_personnalise(points2, 40, 1000, null));
        // Ajout de l'étoile géante
        // double[][] triangle1 = { 
        //     {300, 100}, // Haut du triangle
        //     {200, 300}, // Bas gauche
        //     {400, 300}  // Bas droit
        // };

        // double[][] triangle2 = { 
        //     {300, 400}, // Bas du triangle inversé
        //     {200, 200}, // Haut gauche
        //     {400, 200}  // Haut droit
        // };

        // Ajouter les deux triangles pour former l'étoile
        // super.terrain.add(new Briques_personnalise(points, 100, 75, genereBonus(700, 400)));

        
    }

    // Méthode pour ajouter une ligne de briques rectangulaires
    public void ligne(int nb, double x, double y, double width, double height, int resist, int scoreVal) {
        for (int i = 0; i < nb; i++) {
            super.terrain.add(new Briques_rectangle(x, y, width, height, scoreVal, resist, genereBonus(x, y)));
            x += width + 2; // Espacement entre les briques
        }
    }

    // Méthode pour générer des bonus ou malus
    public Bonus genereBonus(double posX, double posY) {
        if (posX < 0 || posX > BreakOut.getWidth() || posY < 0 || posY > BreakOut.getHeight()) {
            throw new IllegalArgumentException("Coordonnées en dehors de l'écran");
        } else {
            Random random = new Random();
            int numBonus = random.nextInt(101);

            if (numBonus >= 0 && numBonus <= 5) {
                return null;
            } else if (numBonus > 5 && numBonus <= 20) {
                return new BonusAugLagRaq(posX, posY, 1, 20, 50);
            } else if (numBonus > 20 && numBonus <= 40) {
                return new BonusPlusBalles(posX, posY, 1, 20);
            } else if (numBonus > 40 && numBonus <= 70) {
                return new MalusTailleBalle(posX, posY, 1, 15);
            } else if (numBonus > 70 && numBonus <= 100) {
                return new MalusVitesseBalle(posX, posY, 1, 10);
            }
            
            return null;
        }
    }
}
