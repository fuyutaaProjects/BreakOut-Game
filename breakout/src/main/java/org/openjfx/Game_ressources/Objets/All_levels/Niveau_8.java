package org.openjfx.Game_ressources.Objets.All_levels;

import java.util.Random;

import org.openjfx.BreakOut;
import org.openjfx.Game_ressources.Bonus_jeu.*;
import org.openjfx.Game_ressources.Objets.Briques.*;

/**
 * Classe représentant le niveau 1 du jeu.
 */
public class Niveau_8 extends Niveaux {
    @Override
    public void init_board() {
         // Premier polygone 
 double centerX = BreakOut.getWidth() / 2;
 double centerY = BreakOut.getHeight() / 2;
       // Premier polygone - Cage extérieure
 double[][] points1 = {
    {centerX - 150, centerY - 150},  // Haut gauche
    {centerX + 150, centerY - 150},  // Haut droite
    {centerX + 150, centerY + 150},  // Bas droite  
    {centerX + 100, centerY + 150},  // Bas droite intérieur
    {centerX + 100, centerY - 100},  // Intérieur haut droite
    {centerX - 100, centerY - 100},  // Intérieur haut gauche
    {centerX - 100, centerY + 150},  // Bas gauche intérieur
    {centerX - 150, centerY + 150}   // Bas gauche
};

// Deuxième polygone - Cage intérieure
double[][] points2 = {
    {centerX - 80, centerY - 80},    // Haut gauche interne
    {centerX + 80, centerY - 80},    // Haut droite interne
    {centerX + 80, centerY + 80},    // Bas droite interne
    {centerX + 50, centerY + 80},    // Bas droite interne petit
    {centerX + 50, centerY - 50},    // Milieu droite interne
    {centerX - 50, centerY - 50},    // Milieu gauche interne
    {centerX - 50, centerY + 80},    // Bas gauche interne petit
    {centerX - 80, centerY + 80}     // Bas gauche interne
};

super.terrain.add(new Briques_personnalise(points1, 40, 20, genereBonus(centerX, centerY)));
super.terrain.add(new Briques_personnalise(points2, 40, 20, genereBonus(centerX, centerY)));

    }

   
    

    public Bonus genereBonus(double posX, double posY) {
        if (posX < 0 || posX > BreakOut.getWidth() || posY < 0 || posY > BreakOut.getHeight()) {
            throw new IllegalArgumentException("Coordonnées en dehors de l'écran");
        } else {
            Random random = new Random();
            int numBonus = random.nextInt(101);
            if (numBonus >= 0 && numBonus <= 1) {
                return null;
            } 
            return new BonusPlusBalles(posX, posY, 1, 20);
        }
    }
}
