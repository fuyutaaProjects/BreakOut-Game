package org.openjfx.Game_ressources.Objets.Briques;

import org.openjfx.Game_ressources.Bonus_jeu.Bonus;
import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.SpecialAttacks.SpecialAttack;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.openjfx.Score.Score;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Classe abstraite représentant une brique dans le jeu.
 */
public abstract class Brique {
    // Couleur de la brique
    protected Color couleur;
    // Points que rapporte la brique
    protected int points_brique;
    protected Bonus bonus;
    protected int resistance;
    private int fissures = 0;
    // Visibilité de la brique
    protected boolean estVisible = true;
    // Polygone représentant la brique
    protected Polygon poly;
    // Faces de la brique
    protected Face[] faces;

    protected Score scoreManager = new Score();

    /**
     * Constructeur de la classe Brique.
     * 
     * @param points_brique Points que rapporte la brique.
     * @param faces Faces de la brique.
     */
    public Brique(int points_brique, Face[] faces, int resistance, Bonus bonus) {
        this.couleur = determineColor(resistance);
        this.points_brique = points_brique;
        this.faces = faces;
        this.bonus=bonus;
        this.resistance=resistance;
    }

    /**
     * Constructeur de la classe Brique.
     * 
     * @param points_brique Points que rapporte la brique.
     * @param faces Faces de la brique.
     * @param color Couleur de la brique.
     */
    public Brique(int points_brique, Face[] faces, int resistance, Bonus bonus, Color color) {
        this.couleur = color;
        this.points_brique = points_brique;
        this.faces = faces;
        this.bonus=bonus;
        this.resistance=resistance;
    }

    /**
     * Gère la physique de la brique lorsqu'elle est touchée par une balle.
     * 
     * @param balle La balle qui touche la brique.
     */
    public void physique(Balle balle) {
        // Vérifie si la balle touche la brique
        Face touche = this.checkColB(balle);
        if (touche != null) {
            // Fait rebondir la balle
            balle.rebond(touche);

            if (this.resistance>0){ //si la brique est cassable
                // Incrémente les fissures de la brique
                this.incrementerFissures();
                this.setColor(determineColor(resistance));
                // Si la brique est détruite, la supprime du Board
                if (resistance==0) {
                    this.estVisible = false;
                }
            }
        }
    }

    public void physique2() {
        if (this.resistance>0){ //si la brique est cassable
            // Incrémente les fissures de la brique
            this.incrementerFissures();
            this.setColor(determineColor(resistance));
            // Si la brique est détruite, la supprime du Board
            if (resistance==0) {
                this.estVisible = false;
            }
        }
    }


    /**
     * Gère la physique de la brique lorsqu'elle est touchée par une attaque spéciale (exemple: Laser.java)
     * 
     * @param specialAttack L'attaque spéciale qui touche la brique.
     */
    public void physiqueSpecialAttack(SpecialAttack specialAttack) {

        // Vérifie si la hitbox touche la brique
        Face touche = this.checkColSpecialAttack(specialAttack);
        if (touche != null) {
            if (this.resistance > 0) { // Si la brique est cassable
                this.incrementerFissures();
                this.setColor(determineColor(resistance));

                // Si la brique est détruite
                if (resistance <= 0) {
                    // on la rend invisible, et on va ensuite checker dans le call précedent si la brique est invisible, si oui on a la supprime
                    // on fait ça vu qu'on ne peut pas la delete elle même, elle doit être delete depuis un autre script.
                    this.estVisible = false; 
                }
            }
        }
    }


    /**
     * Vérifie si la balle touche une des faces de la brique.
     * 
     * @param balle La balle à vérifier.
     * @return La face touchée par la balle, ou null si aucune face n'est touchée.
     */
    public Face checkColB(Balle balle) {
        Face face_sel= null;
        Circle nextPos= balle.nextPos();
        for (Face face : faces) {
            if (face.checkCol(nextPos)) {
                face_sel=face;
            }
        }
        return face_sel;
    }


    /**
     * renvoie tout les points d'interections entre une brique et une balle
     * 
     * @param balle la balle tester.
     * @return la map contenant tout les points d'intersection, et les faces correspondante, renvoie map vide s'il n'y en a aucun.
     */
    public Map.Entry<List<Face>, List<double[]>> checkColB2(Balle balle){
        List<double[]> intersections = new ArrayList<>();
        List<Face> facess = new ArrayList<>();
        
        Circle nextPos= balle.nextPos();

        Line [] lignes= hitBox(balle.oval, nextPos, 20); 
        
        for (Face face : faces) {
            for (Line ligne : lignes){
                Line lignef=face.getLigne();
                double [] intersection = Intersection(ligne,lignef);

                if (intersection != null){
                    
                    intersections.add(intersection);
                    facess.add(face);
                }
            }
        }

        return new AbstractMap.SimpleEntry<>(facess,intersections);
    }
    /**
     * cree les ligne decrite dans le manuel de physique
     *
     * @param circle1 position initiale
     * @param circle2   position suivante
     * @param numberOfLines nombre de lignes a cree
     * @return les lignes decrite dans le manuel de la physique
     */
    public Line[] hitBox(Circle circle1, Circle circle2, int numberOfLines) {
        Line[] lines = new Line[numberOfLines];

        double circle1X = circle1.getCenterX();
        double circle1Y = circle1.getCenterY();
        double radius1 = circle1.getRadius();

        double circle2X = circle2.getCenterX();
        double circle2Y = circle2.getCenterY();
        double radius2 = circle2.getRadius();

        for (int i = 0; i < numberOfLines; i++) {
            // Calcul de l'angle pour chaque ligne
            double angle = 2 * Math.PI * i / numberOfLines;

            // Points sur le bord du cercle initial
            double startX = circle1X + radius1 * Math.cos(angle);
            double startY = circle1Y + radius1 * Math.sin(angle);

            // Points sur le bord du cercle final
            double endX = circle2X + radius2 * Math.cos(angle);
            double endY = circle2Y + radius2 * Math.sin(angle);

            // Créer une ligne entre les deux points
            lines[i] = new Line(startX, startY, endX, endY);
        }

        return lines;
    }


    // Vérifie si l'attaque spéciale touche la brique.
    public Face checkColSpecialAttack(SpecialAttack specialAttack) {
        Shape hitbox = specialAttack.getHitbox();

        // check chaque face si elle intersect avec la hitbox de l'attaque spéciale
        for (Face face : faces) {
            System.out.println("processing all faces of the brick in checkColSpecialAttack");
            if (Shape.intersect(face.getLigne(), hitbox).getBoundsInLocal().getWidth() != -1) {
                return face;
            }
        }

        return null; // aucune face ne touche l'attaque spéciale
    }

    
    
    /**
     * Incrémente les fissures de la brique.
     * Et diminue la résistance de la brique.
     */    
    public void incrementerFissures() {
        resistance--;
        fissures++;

        if (fissures == 1) {
            poly.getStyleClass().add("fissure-1");
        } else if (fissures == 2) {
            poly.getStyleClass().add("fissure-2");
        } else if (fissures == 3) {
            poly.getStyleClass().add("fissure-3");
        }
    }

    public static double[] Intersection(Line line1, Line line2) {
        double x1 = line1.getStartX(), y1 = line1.getStartY();
        double x2 = line1.getEndX(), y2 = line1.getEndY();
        double x3 = line2.getStartX(), y3 = line2.getStartY();
        double x4 = line2.getEndX(), y4 = line2.getEndY();

        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        // Vérification si les lignes sont parallèles
        if (denominator == 0) {
            return null; // Pas d'intersection (lignes parallèles ou colinéaires)
        }

        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / denominator;
        double u = ((x1 - x3) * (y1 - y2) - (y1 - y3) * (x1 - x2)) / denominator;

        // Vérifier si l'intersection se trouve sur les segments
        if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            double intersectionX = x1 + t * (x2 - x1);
            double intersectionY = y1 + t * (y2 - y1);
            return new double[]{intersectionX, intersectionY};
        }

        return null; // Pas d'intersection sur les segments
    }



    // Getters
    public Face[] getFaces() {
        return faces;
    }

    public int getPoint_brique() {
        return this.points_brique;
    }

    public Color getCouleur() {
        return couleur;
    }
    public Polygon getPoly() {
        return poly;
    }

    public boolean getEstVisible(){
        return estVisible;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void setColor(Color color){
        this.couleur = color;
    }

    public static Color determineColor(int couleur) {
        switch (couleur) {
            case 1:
                return Color.YELLOW;
            case 2:
                return new Color(1.0, 0.8, 0.0, 1.0); // orange clair
            case 3:
                return Color.ORANGE;
            case 4:
                return new Color(1.0, 0.4, 0.0, 1.0); // orange rouge
            case 5:
                return Color.RED;
            case 6:
                return Color.RED.darker();
            case -1:    
                return Color.GREY;
            default:
                return Color.YELLOW;
        }
    }    
}
