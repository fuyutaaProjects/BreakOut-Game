package org.openjfx.Game_ressources.Objets.Briques;

import org.openjfx.Game_ressources.Bonus_jeu.Bonus;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Briques_personnalise extends Brique{
    
    double [][] points;

    public Briques_personnalise( double [][] points, int points_brique, int resistance,Bonus bonus) {
        super(points_brique, iniFaces(points),resistance,bonus);
        super.poly = iniPoly(points);
        super.poly.setFill(couleur);
        this.points=points;
        
    }

    /**
     * Initialise les faces de la brique.
     * 
     * @param points tout les points qui contstitus les sommets de la brique
     * @return Un tableau de faces représentant les faces de la brique avec des collision.
     */
    public static Face[] iniFaces(double [][] points) {
        Face[] faces = new Face[points.length];

        for (int i= 0; i<points.length;i++){
            double finX;
            double finY;
            double debutX=points[i][0];
            double debutY=points[i][1];

            if (i+1>=points.length){
                finX =points[0][0];
                finY =points[0][1];
            }
            else{
                finX = points[i+1][0];
                finY = points[i+1][1];
            }

            faces[i] = new Face(debutX,debutY,finX,finY );
        }

        return faces;
    }

    public static Polygon iniPoly(double [][] points) {
        Polygon poly = new Polygon();

        for (double[] point : points) {
            double posX = point[0];
            double posY = point[1];
            poly.getPoints().addAll(posX, posY);
        }

        return poly;
    }


}
