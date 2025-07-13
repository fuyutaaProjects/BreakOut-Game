package org.openjfx.Game_ressources.Objets.Briques;

import org.openjfx.Game_ressources.Vecteur;
import org.openjfx.Game_ressources.Objets.Balle;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * La classe Face représente une face d'une brique dans le jeu Breakout.
 * Elle contient les méthodes pour calculer la normale de la face et vérifier les collisions avec la balle, ou une SpecialAttack.
 */
public class Face {
    // Position X de départ de la face
    private double startX;
    // Position Y de départ de la face
    private double startY;
    // Position X de fin de la face
    private double endX;
    // Position Y de fin de la face
    private double endY;
    // Ligne représentant la face
    private Line ligne;
    // Normale de la face pour calculer le rebond
    private Vecteur normale;


    /**
     * Constructeur de la classe Face.
     * 
     * @param startX Position X de départ de la face.
     * @param startY Position Y de départ de la face.
     * @param endX Position X de fin de la face.
     * @param endY Position Y de fin de la face.
     */
    public Face(double startX, double startY, double endX, double endY) {

        // Initialise les positions de départ et de fin de la face
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        // Crée une ligne représentant la face
        this.ligne = new Line(startX, startY, endX, endY);
        // Calcule la normale de la face
        this.normale = calculerNormale();
    }

    /**
     * Calcule la normale pour chaque face en fonction de l'angle entre start et end.
     * 
     * @return La normale de la face.
     */
    private Vecteur calculerNormale() {
        // Calcule le vecteur directeur de la face
        double dx = this.endX - this.startX;
        double dy = this.endY - this.startY;
        // Calcule la norme du vecteur directeur
        double norme = Math.sqrt(dx * dx + dy * dy);
        
        // Choisit une orientation pour la normale, par exemple vers l'extérieur de la brique
        double normalX = -dy / norme;
        double normalY = dx / norme;

        // Retourne la normale sous forme de vecteur
        return new Vecteur(normalX, normalY);
    }

    //Vérifie si la balle touche la face.
    public boolean checkCol(Circle cercle) {
        // Vérifie si le cercle représentant la balle intersecte avec la ligne représentant la face
        return Shape.intersect(this.ligne, cercle).getBoundsInLocal().getWidth() != -1;
    }
    
    //Vérifie si l'attaque spéciale touche la face.
    public boolean checkColWithSpecialAttack(Rectangle laser) {
    // Vérifie si le rectangle représentant le laser intersecte la ligne représentant la face
    return Shape.intersect(this.ligne, laser).getBoundsInLocal().getWidth() != -1;
    }

    
    /** 
     * @return double
     */
    // Getters pour les attributs de la classe
    public double getStartX() {
        return this.startX;
    }

    public double getStartY() {
        return this.startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public Vecteur getNormal() {
        return normale;
    }

    public Line getLigne() {
        return ligne;
    }
}