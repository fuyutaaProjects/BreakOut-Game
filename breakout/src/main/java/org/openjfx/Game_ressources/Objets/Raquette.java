package org.openjfx.Game_ressources.Objets;

import org.openjfx.BreakOut;
import org.openjfx.Game_ressources.Audio;
import org.openjfx.Score.Score;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * La classe Raquette représente la raquette contrôlée par le joueur.
 * Elle gère les mouvements de la raquette et les collisions avec la balle.
 */
public class Raquette {

    private Score scoreManager = new Score();
    // Position X de la raquette
    private double PosX;
    // Position Y de la raquette (constante)
    private double PosY;
    // Largeur de la raquette
    private double largeur;
    // Hauteur de la raquette
    private double hauteur;
    // Couleur de la raquette
    private Color couleur;
    // Rectangle représentant la raquette
    public Rectangle corp;
    // SFX de rebond
    private Audio bounceSFX;
    // Booléen pour gérer le malus de raquette inversé
    private boolean inverted = false;
    // Double représentant l'angle de la raquette
    private double rotation;
    // 2 Boolean pour représenter si la raquette tourner vers la gauche ou la droite
    private boolean rotaLeft = false;
    private boolean rotaRigh = false;

    /**
     * Constructeur de la classe Raquette.
     * 
     * @param PosX Position X de la raquette.
     * @param PosY Position Y de la raquette.
     * @param largeur Largeur de la raquette.
     * @param hauteur Hauteur de la raquette.
     * @param couleur Couleur de la raquette.
     */
    public Raquette(double PosX, double PosY, double largeur, double hauteur, Color couleur) {
        // Initialise la position, la taille et la couleur de la raquette
        this.PosX = PosX;
        this.PosY = PosY;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.couleur = couleur;

        // Crée un rectangle représentant la raquette
        this.corp = new Rectangle(PosX, PosY, largeur, hauteur);
        // Définit la couleur de remplissage du rectangle
        this.corp.setFill(this.couleur);

        // Initialise le SFX de rebond
        bounceSFX = new Audio();
        bounceSFX.load("Bounce_Ball_sfx_G.wav");

        // Initialise l'angle pour la rotation de la raquette
        rotation = 0;
    }

    /**
     * Gère le rebond de la balle sur la raquette.
     * 
     * @param balle La balle à vérifier.
     */
    public void rebond(Balle balle) {
        // Vérifie si la balle intersecte avec la raquette
        if (Shape.intersect(this.corp, balle.getOval()).getBoundsInLocal().getWidth() != -1) {
            // Si la balle est déjà au-dessus de la raquette et rebondit sur un côté
            if (balle.getPosY() + balle.getVitesseY() > this.PosY) {
                // Détection de collision sur les côtés gauche et droit
                if (balle.getPosX() < this.PosX) {
                    balle.setPosX(this.PosX - this.largeur / 2 - balle.getRayon());
                } else {
                    balle.setPosX(this.PosX + this.largeur / 2 + balle.getRayon());
                }
                // Inverse la vitesse en X de la balle
                balle.setVitesseX(-balle.getVitesseX());
            
            } else {
                // Inverse la vitesse en Y de la balle pour le rebond vertical
                balle.setVitesseY(-balle.getVitesseY());
    
                // Calcul de la position d'impact de la balle sur la raquette
                double impactPosition = balle.getPosX() - this.PosX+largeur/2;
    
                // Calcul du ratio d'impact entre -1 (gauche) et 1 (droite)
                double impactRatio = ((impactPosition / this.largeur) - 0.5) * 2;
    
                // Ajuste la vitesse en X en fonction du ratio d'impact
                double nouvelleVitesseX = impactRatio *2+ balle.getVitesseX(); // 5 = vitesse horizontale ajustable
                balle.setVitesseX(nouvelleVitesseX);
            }
            scoreManager.resetHits();                     // Réinitialise les coups consécutifs et le multiplicateur
            bounceSFX.play();
            //Joue le SFX de rebond
        }
    }

    /**
     * Dessine la raquette en mettant à jour la position du rectangle représentant la raquette.
     */
    public void dessiner_raquette() {
        // Met à jour la position X du rectangle représentant la raquette
        this.corp.setX(this.PosX - this.largeur / 2);
    }

    /**
     * Déplace la raquette en fonction de la position de la souris.
     * 
     * @param mouseX Position X de la souris.
     */
    public void deplacer(double mouseX) {
        double targetX = inverted ? BreakOut.getWidth() - mouseX : mouseX;

        // Si la raquette dépasse à droite
        if (mouseX + largeur / 2 > BreakOut.getWidth()) {
            this.PosX = BreakOut.getWidth() - largeur / 2;
            return;
        }
        // Si la raquette dépasse à gauche
        if (mouseX - largeur / 2 < 0) {
            this.PosX = largeur / 2;
            return;
        }
        // Centre la raquette sur la position de la souris
        this.PosX = mouseX;
    }

    /**
     * Tourner la raquette en fonction de l'angle reçu
     * 
     * @param angle Valeur de l'angle de rotation
     */
    public void rotation_calcul(double angle) {
        rotation += angle;

        // Limite la rotation à un angle de +/- 25 degré
        if (rotation > 25) rotation = 25;
        if (rotation < -25) rotation = -25;

        // Applique la rotation à la raquette
        this.corp.setRotate(rotation);
    }


    /**
     * Fonction appeler continuellement qui met à jour la rotation de la raquette
     * Elle applique la rotation selon si les touches Q ou D sont appuyés
     */
    public void rotation() {
        System.out.println("test rotation print");
        if (rotaLeft) {
            rotation_calcul(-1); // Rotate left
        }
        if (rotaRigh) {
            rotation_calcul(1); // Rotate right
        }
    }

    /**
     * Tourner la raquette à l'angle reçu indépendamment de sa rotation précédente
     * 
     * @param angle Valeur de l'angle de position à la fin
     */
    public void rotation_strict(double angle) {
        rotation = angle;

        // Limite la rotation à un angle de +/- 25 degré
        if (rotation > 25) rotation = 25;
        if (rotation < -25) rotation = -25;

        // Applique la rotation à la raquette
        this.corp.setRotate(rotation);
    }

    /**
     * Transforme les entrées clavier en une rotation de la raquette
     * 
     * @param event Valeur event dans Run
     */
    public void keypress_rotation(KeyEvent event) {
        switch (event.getCode()){
            case Q:
                rotaLeft = true;
                // Active la rotation vers la gauche
                break;
            case D:
                rotaRigh = true;
                // Active la rotation vers la droite
                break;
            default:
                break;
        }
    }

    /**
     * Transforme les entrées clavier en une rotation de la raquette
     * 
     * @param event Valeur event dans Run
     */
    public void keyrelease_rotation(KeyEvent event) {
        switch (event.getCode()){
            case Q:
                rotaLeft = false;
                break;
            case D:
                rotaRigh = false;
                break;
            default:
                break;
        }
    }

    // Getters et Setters

    public double getPosX() {
        return PosX;
    }

    public double getPosY() {
        return PosY;
    }

    public double getLargeur() {
        return corp.getWidth();
    }

    public double getHauteur() {
        return hauteur;
    }

    public Color getCouleur() {
        return couleur;
    }

    public Rectangle getCorp() {
        return corp;
    }

    public void setPosX(double posX) {
        PosX = posX;
    }

    public void setPosY(double posY) {
        PosY = posY;
    }

    public void setLargeur(double largeur) {
        this.largeur=largeur;
        this.corp.setWidth(largeur);
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
        this.corp.setHeight(hauteur);
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setInverted(Boolean state){
        inverted = state;
    }
}