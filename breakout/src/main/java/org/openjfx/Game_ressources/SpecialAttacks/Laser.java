package org.openjfx.Game_ressources.SpecialAttacks;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import org.openjfx.Game_ressources.Vecteur;
import org.openjfx.Game_ressources.Objets.Board;
import org.openjfx.Game_ressources.Objets.Projectile;
import org.openjfx.Game_ressources.Objets.Briques.Brique;

public class Laser extends Projectile implements SpecialAttack {
    private Rectangle laserRectangle; // nécessaire pour obtenir la hitbox

    private double taille; // longueur du rectangle du laser
    private Color couleur;
    private Integer resistance;

    /**
     * Méthode pour mettre à jour la position de la balle.
     * Met à jour la position de la balle en ajoutant les composantes du vecteur de déplacement à la position actuelle.
     */
    public void mettreAJour() {
        // Met à jour la position X de la balle
        this.posX += this.vecteur.getX();
        // Met à jour la position Y de la balle
        this.posY += this.vecteur.getY();
    }

    @Override
    public void dessiner() {
        // Met à jour la position visuelle du rectangle en fonction des coordonnées actuelles
        this.laserRectangle.setX(this.getPosX() - 2); // Centré autour de X
        this.laserRectangle.setY(this.getPosY());
    }

    /**
     * Constructeur du Laser.
     *
     * @param posX   Position X initiale du laser.
     * @param posY   Position Y initiale du laser.
     * @param vecteur Vecteur de déplacement du laser.
     * @param couleur Couleur du laser.
     * @param taille Taille (longueur) du laser.
     */
    public Laser(double posX, double posY, Vecteur vecteur, Color couleur, double taille, Integer hp) {
        super(posX, posY, vecteur, couleur);
        this.laserRectangle = new Rectangle(posX - 2, posY - taille, 6, taille);
        this.taille = taille;
        this.laserRectangle.setFill(couleur);
        this.resistance = hp;

        Board.getInstance().getp().getChildren().add(this.laserRectangle);
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public Shape getHitbox() {
        return this.laserRectangle; // Retourne le rectangle représentant le laser
    }

    public void takeResistanceDamage(Integer damage) {
        resistance -= damage;

        if (resistance <= 0) {
            // Retirer le laser de la liste des attaques spéciales à process et qui sont sur la scene
            Board.getInstance().liste_attaques_speciales.remove(this);
            // Supprime le polygone, l'affichage du laser
            Board.getInstance().getp().getChildren().remove(this.laserRectangle);
        }
    }
}

