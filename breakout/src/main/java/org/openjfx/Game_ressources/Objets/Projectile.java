package org.openjfx.Game_ressources.Objets;

import javafx.scene.paint.Color;
import org.openjfx.Game_ressources.Vecteur;

// Utilisé pour des classes comme Balle ou Laser

public abstract class Projectile {
    protected double posX;
    protected double posY;
    protected Vecteur vecteur;
    protected Color couleur;

    // On aurait pu ajouter vitesseX et vitesseY dans le constructeur, ça marche avec Balle et Laser mais je ne sais pas ce que les futurs projectiles qu'on va coder seront,
    // Et peut être qu'ils fonctionneront différent qu'avec un simple vitesseX et vitesseY (j'ai pas trop creusé, mais je veux garder simple et pas trop précis.)
    public Projectile(double posX, double posY, Vecteur vecteur, Color couleur) {
        this.posX = posX;
        this.posY = posY;
        this.vecteur = vecteur;
        this.couleur = couleur;
    }

    /**
     * Met à jour la position du projectile en fonction de son vecteur.
     */
    public abstract void mettreAJour();

    /**
     * Change la couleur du projectile.
     * 
     * @param couleur Nouvelle couleur.
     */
    public void changeCouleur(Color couleur) {
        this.couleur = couleur;
    }

    // Getters et Setters
    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public Vecteur getVecteur() {
        return vecteur;
    }

    public void setVecteur(Vecteur vecteur) {
        this.vecteur = vecteur;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public abstract void dessiner();
}
