package org.openjfx.Game_ressources.Objets.Briques;

import org.openjfx.Game_ressources.Bonus_jeu.Bonus;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Classe abstraite représentant une brique en forme de rectangle.
 */
public class Briques_rectangle extends Brique {
    // Largeur de la brique
    private double largeur;
    // Hauteur de la brique
    private double hauteur;
    // Position X de la brique
    private double posX;
    // Position Y de la brique
    private double posY;

    /**
     * Constructeur de la classe Briques_rectangle.
     * 
     * @param posX Position X de la brique.
     * @param posY Position Y de la brique.
     * @param largeur Largeur de la brique.
     * @param hauteur Hauteur de la brique.
     * @param points_brique Points que rapporte la brique.
     */
    public Briques_rectangle(double posX, double posY, double largeur, double hauteur,int points_brique, int resistance,Bonus bonus) {
        super(points_brique, iniFaces(posX, posY, largeur, hauteur),resistance,bonus);
        super.poly = new Polygon();
        super.poly.getPoints().addAll(
            posX, posY, // en haut à gauche
            posX + largeur, posY, // en haut à droite
            posX + largeur, posY + hauteur, // en bas à droite
            posX, posY + hauteur // en bas à gauche
        );

        super.poly.setFill(couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Initialise les faces de la brique.
     * 
     * @param PosX Position X de la brique.
     * @param PosY Position Y de la brique.
     * @param largeur Largeur de la brique.
     * @param hauteur Hauteur de la brique.
     * @return Un tableau de faces représentant les faces de la brique.
     */
    public static Face[] iniFaces(double PosX, double PosY, double largeur, double hauteur) {
        Face[] faces = {
            new Face(PosX, PosY, PosX + largeur, PosY), // face du haut
            new Face(PosX + largeur, PosY, PosX + largeur, PosY + hauteur), // face de droite
            new Face(PosX + largeur, PosY + hauteur, PosX, PosY + hauteur), // face du bas
            new Face(PosX, PosY + hauteur, PosX, PosY) // face de gauche
        };
        return faces;
    }


 
    public double getLargeur() {
        return largeur;
    }

    public double getHauteur() {
        return hauteur;
    }
    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }
}
