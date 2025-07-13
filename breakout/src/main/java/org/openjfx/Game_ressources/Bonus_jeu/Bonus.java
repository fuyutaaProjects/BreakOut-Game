package org.openjfx.Game_ressources.Bonus_jeu;

import java.util.ArrayList;

import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Raquette;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Classe abstraite représentant un bonus dans le jeu.
 */
public abstract class Bonus {
    // Position X du bonus
    private double posX;
    // Position Y du bonus
    private double posY;
    // Taille du bonus
    private double taille;
    // Cercle représentant le bonus
    protected Circle cercle;
    // Vitesse de déplacement du bonus
    private double vitesse;

    /**
     * Constructeur de la classe Bonus.
     * 
     * @param posX Position X du bonus.
     * @param posY Position Y du bonus.
     * @param vitesse Vitesse de déplacement du bonus.
     * @param taille Taille du bonus.
     */
    public Bonus(double posX, double posY, double vitesse, double taille) {
        this.posX = posX;
        this.posY = posY;
        this.taille = taille;
        this.vitesse = vitesse;
        this.cercle = new Circle(posX,posY,this.taille / 2);
    }

    /**
     * Met à jour la position du bonus lorsqu'il tombe de la brique.
     * Seule la hauteur change car un bonus ne se déplace pas horizontalement.
     */
    public void mettreAJour() {
        // Met à jour la position Y du bonus en ajoutant la vitesse
        this.posY += vitesse;
        // Met à jour la position du cercle représentant le bonus
        this.cercle.setCenterY(this.posY);
    }

    /**
     * Vérifie si le bonus touche la raquette.
     * 
     * @param raquette La raquette à vérifier.
     * @return true si le bonus touche la raquette, false sinon.
     */
    public boolean toucheRaquette(Raquette raquette) {
        // Vérifie si le cercle du bonus intersecte avec le rectangle de la raquette
        if(raquette == null){
            return false;
        }
        return Shape.intersect(raquette.getCorp(), this.cercle).getBoundsInLocal().getWidth() != -1;
    }

    /**
     * Vérifie si le bonus est sous la raquette.
     * 
     * @param raquette La raquette à vérifier.
     * @return true si le bonus est sous la raquette, false sinon.
     */
    public boolean sousRaquette(Raquette raquette) {
        if (raquette == null) {
            return false;
        }
        // Vérifie si la position Y du bonus est supérieure à la position Y de la raquette
        return (cercle.getCenterY() - getRayon() > raquette.getPosY() + raquette.getHauteur());
    }

    
    /** 
     * @param raquette
     * @param getPosX(
     */
    /**
     * Active le bonus sur la raquette.
     * 
     * @param raquette La raquette sur laquelle activer le bonus.
     * @param temps La durée pendant laquelle le bonus est actif.
     */
    public abstract void activeBonus(Raquette raquette, long temps);

    /**
     * Active le bonus sur la balle.
     * 
     * @param balle La balle sur laquelle activer le bonus.
     */
    public abstract void activeBonus(Balle balle);

    public char GestionBonus( Raquette raquette){
        this.mettreAJour();

        if(this.sousRaquette(raquette)){
            return 'S';
        }

        if(this.toucheRaquette(raquette)){
            return 'T';
        }

        return 'V';
    }
    

    // Getters et Setters

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getVitesse(){
        return vitesse;
    }

    public double getTaille(){
        return taille;
    }

    public double getRayon(){
        return taille/2;
    }    

    public Circle getCercle(){
        return cercle;
    }

    public void setPosX(double posX) {
        this.cercle.setCenterX(posX);
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.cercle.setCenterY(posY);
        this.posY = posY;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }


}
