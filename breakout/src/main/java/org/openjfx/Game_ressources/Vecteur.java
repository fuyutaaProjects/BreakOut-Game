package org.openjfx.Game_ressources;

/**
 * La classe Vecteur représente un vecteur 2D avec des composantes X et Y.
 * Elle contient des méthodes pour manipuler et réfléchir le vecteur.
 */
public class Vecteur {
    // Composante X du vecteur
    private double x;
    // Composante Y du vecteur
    private double y;

    /**
     * Constructeur de la classe Vecteur.
     * 
     * @param x Composante X du vecteur.
     * @param y Composante Y du vecteur.
     */
    public Vecteur(double x, double y) {
        // Initialise les composantes X et Y du vecteur
        this.x = x;
        this.y = y;
    }

    // Getters et Setters

    /**
     * Réfléchit le vecteur par rapport à une normale donnée.
     * 
     * @param normal La normale par rapport à laquelle réfléchir le vecteur.
     * @return Un nouveau vecteur représentant la réflexion du vecteur initial.
     */
    public Vecteur reflect(Vecteur normal) {
        // Obtient les composantes X et Y de la normale
        double normalX = normal.getX();
        double normalY = normal.getY();
        // Obtient les composantes X et Y du vecteur de vitesse
        double velocityX = this.x;
        double velocityY = this.y;

        // Calcule le produit scalaire entre la vitesse de la balle et la normale
        double dotProduct = velocityX * normalX + velocityY * normalY;

        // Réflexion : v' = v - 2 * (v . n) * n
        return new Vecteur(velocityX - 2 * dotProduct * normalX,
                           velocityY - 2 * dotProduct * normalY);
    }


    
    /** 
     * @return double
     */
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}