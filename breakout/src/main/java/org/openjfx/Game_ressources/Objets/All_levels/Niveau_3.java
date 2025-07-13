package org.openjfx.Game_ressources.Objets.All_levels;

import org.openjfx.Game_ressources.Bonus_jeu.Bonus;
import org.openjfx.Game_ressources.Bonus_jeu.BonusAugLagRaq;
import org.openjfx.Game_ressources.Objets.Briques.*;

import java.util.Random;

import org.openjfx.BreakOut;

/**
 * Classe représentant le niveau 2 du jeu.
 */
public class Niveau_3 extends Niveaux {
    /**
     * Initialise le plateau de jeu pour le niveau 2.
     */
    @Override
    public void init_board() {
        double x = 0;
        double y = 0;
        // Boucle pour créer les briques du niveau 2
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                // Ajoute une brique cassable au plateau de jeu
                super.terrain.add(new Briques_rectangle(x, y, 500, 20,10,5,genereBonus(x, y)));
                // Met à jour la position X pour la prochaine brique
                x += 525;
            }
            // Réinitialise la position X et met à jour la position Y pour la prochaine rangée de briques
            x = 0;
            y += 60;
        }
    }

    /**
     * Fonctione permettant de génerer un bonus avec certains poucentage de chance et en le plaçant aux coordonnées données.
     * 
     * @param posX Position x du bonus.
     * @param posY Position y du bonus.
     * 
     * @return null si un nombre est entre 0 et 45 ou sinon un nombre dans un intervalle non défini pour un bonus sinon retourne un bonus choisit.
     */
    public Bonus genereBonus(double posX, double posY){
        //Cas permettant de génerer des coordonnées fausses et en dehors de l'écran.
        if (posX < 0 || posX > BreakOut.getWidth() || posY < 0 || posY > BreakOut.getHeight()) {
            throw new IllegalArgumentException("Coordonnées en dehors de l'écran");
        }else{
            Random random = new Random();
            int numBonus = random.nextInt(101); // Nombre entre 0 (inclus) et 101 (exclus).
            // 45% de chance de rien avoir
            if (numBonus >= 0 && numBonus <= 45) {
                return null;
            }
            // 15% de chance d'avoir un bonus qui augmente la largeur de la raquette.
            else if (numBonus > 45 && numBonus < 60 ) {
                return new BonusAugLagRaq(posX,posY,1,20,50);
            }
            // Cas quand rien ne remplis une condition et du coup renvoit aucun bonus.
            return null;
        }
    }
}