package org.openjfx.Game_ressources.Objets.All_levels;

import org.openjfx.Game_ressources.Bonus_jeu.Bonus;
import org.openjfx.Game_ressources.Objets.Board;

/**
 * Classe abstraite représentant un niveau du jeu.
 */
public abstract class Niveaux {
    // Plateau de jeu
    protected Board terrain = new Board();

    
    /** 
     * @param getTerrain(
     */
    /**
     * Initialise le plateau de jeu pour le niveau.
     */
    public abstract void init_board();

    /**
     * Obtient le plateau de jeu.
     * 
     * @return Le plateau de jeu.
     */
    public Board getTerrain() {
        return terrain;
    }

    public abstract Bonus genereBonus(double x, double y);
}