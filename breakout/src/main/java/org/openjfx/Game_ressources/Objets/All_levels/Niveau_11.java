package org.openjfx.Game_ressources.Objets.All_levels;

import org.openjfx.Game_ressources.Bonus_jeu.Bonus;
import org.openjfx.Game_ressources.Objets.Briques.Briques_personnalise;

public class Niveau_11 extends Niveaux {

    @Override
    public void init_board() {
        double[][] points = {
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
        };

        // Ajouter le polygone avec une résistance de 15
        super.terrain.add(new Briques_personnalise(points, 15, 10, genereBonus(points[0][0], points[0][1])));
    }

    /**
     * Génère un bonus ou un malus basé sur un pourcentage.
     * 
     * @param posX Position x du bonus/malus.
     * @param posY Position y du bonus/malus.
     * @return Un objet de type Bonus ou null si aucun n'est généré.
     */
    public Bonus genereBonus(double posX, double posY) {
        return null; // Aucun bonus généré pour ce niveau
    }
}