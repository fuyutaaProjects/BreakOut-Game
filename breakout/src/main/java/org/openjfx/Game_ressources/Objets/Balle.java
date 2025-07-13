package org.openjfx.Game_ressources.Objets;

import org.openjfx.Game_ressources.Audio;
import org.openjfx.Game_ressources.Objets.Briques.Face;
import org.openjfx.Game_ressources.Vecteur;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * La classe Balle représente la balle dans le jeu Breakout.
 * Elle contient les propriétés et les méthodes pour gérer les mouvements, les collisions et le sfx de la balle.
 */
public class Balle extends Projectile {
    // Taille de la balle
    private double taille;
    // Cercle représentant la balle
    public Circle oval;
    // Audio pour l'audio de rebond
    private Audio bounceSFX;

    /**
     * 
     * @param taille Taille de la balle.
     */
    public Balle(double posX, double posY, double vitesseX, double vitesseY, double taille, Color couleur) {
        super(posX, posY, new Vecteur(vitesseX, vitesseY), couleur);
        this.taille = taille;
        // Initialise la couleur de la balle
        this.couleur = couleur;
        // Crée un cercle représentant la balle
        this.oval = new Circle(posX, posY, this.taille / 2, this.couleur);
        // Initialise le SFX de rebond
        System.out.println("Testing loading audio");
        bounceSFX = new Audio();
        bounceSFX.load("Bounce_Ball_sfx_G.wav");
        System.out.println(bounceSFX.audio());
        System.out.println("Testing completed");
    }

    // Getters et Setters

    /**
     * Méthode pour dessiner la balle.
     * Met à jour la position du cercle représentant la balle en fonction de sa position actuelle.
     */
    @Override
    public void dessiner() {
        this.oval.setCenterX(this.getPosX());
        this.oval.setCenterY(this.getPosY());
    }

    @Override
    public void mettreAJour() {
        // Met à jour les positions internes
        this.posX += this.vecteur.getX();
        this.posY += this.vecteur.getY();

        // Met à jour la position du cercle visuel
        this.oval.setCenterX(this.posX);
        this.oval.setCenterY(this.posY);
    }


    /**
     * Méthode pour gérer le rebond de la balle sur une face (s'occupe aussi de lancer le son du rebond pour des raisons de facilités).
     * 
     * @param face La face sur laquelle la balle rebondit.
     */
    public void rebond(Face face) {
        // Calcule le nouveau vecteur de déplacement de la balle en fonction de la normale de la face
        this.vecteur = this.vecteur.reflect(face.getNormal());
        // Joue le SFX de rebond 
        bounceSFX.play();
    }

    /**
     * Méthode pour changer la couleur de la balle.
     * 
     * @param couleur La nouvelle couleur de la balle.
     */
    @Override
    public void changeCouleur(Color couleur) {
        // Met à jour la couleur de la balle
        this.couleur = couleur;
        // Met à jour la couleur du cercle représentant la balle
        this.oval.setFill(couleur);
    }

    /**
     * Méthode pour obtenir la prochaine position de la balle.
     * 
     * @return Un cercle représentant la prochaine position de la balle.
     */
    public Circle nextPos() {
        // Retourne un nouveau cercle représentant la prochaine position de la balle
        return new Circle(this.oval.getCenterX() + this.vecteur.getX(), this.oval.getCenterY() + this.vecteur.getY(), this.getRayon());
    }

    
    /** 
     * @return double
     */
    // Getters et Setters
    @Override
    public double getPosX() {
        return this.oval.getCenterX();
    }
    
    @Override
    public void setPosX(double posX) {
        this.oval.setCenterX(posX); 
    }
    
    @Override
    public double getPosY() {
        return this.oval.getCenterY();
    }
    
    @Override
    public void setPosY(double posY) {
        this.oval.setCenterY(posY);
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
        this.oval.setRadius(taille / 2);
    }

    public Circle getOval(){
        return this.oval;
    }

    public double getRayon(){
        return this.taille/2;
    }

    public void setVitesseX(double x){
        this.vecteur.setX(x);
    }
    public void setVitesseY(double y){
        this.vecteur.setY(y);
    }
    public double getVitesseX(){
        return this.vecteur.getX();
    }
    public double getVitesseY(){
        return this.vecteur.getY();
    }
}

