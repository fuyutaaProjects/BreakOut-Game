package org.openjfx.Game_ressources.Bonus_jeu;

import java.util.ArrayList;
import java.util.Random;

import org.openjfx.BreakOut;
import org.openjfx.Run;
import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Board;
import org.openjfx.Game_ressources.Objets.Raquette;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BonusPlusBalles extends Bonus {
    /**
     * Constructeur de la classe BonusPlusBalles.
     * 
     * @param posX Position X du bonus.
     * @param posY Position Y du bonus.
     * @param largeur Largeur du bonus.
     * @param hauteur Hauteur du bonus.
     */
     public Color couleur;

    public BonusPlusBalles(double posX, double posY, double largeur, double hauteur) {
        super(posX, posY, largeur, hauteur);
        this.couleur = Color.YELLOW;
        super.cercle.setFill(couleur);
    }

    @Override
    public  void activeBonus(Raquette raquette, long temps) {
        // Pas utile dans le cas d'un bonus qui ajoute des balles
    }

    /**
     * Active le bonus sur la balle.
     * 
     * @param balle La balle sur laquelle activer le bonus.
     */

     @Override
     public void activeBonus(Balle balle) {
    // Crée un générateur de nombres aléatoires
    Random random = new Random();
    // Génère une nouvelle couleur aléatoire pour la balle
    Color nouvelleCouleur = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());

    Board Board_instance = Board.getInstance();

    // Crée une nouvelle balle basée sur celle existante
    Balle nouvelleBalle = new Balle(BreakOut.getWidth()/2, BreakOut.getHeight()/2, balle.getVitesseX(), balle.getVitesseY(), balle.getTaille(), nouvelleCouleur);

    // Ajout des logs pour vérifier le processus d'ajout de balle
    
    // Ajoute la nouvelle balle à la liste et au Pane
    Board_instance.ajoute_balle(nouvelleBalle);

    Board_instance.getp().getChildren().add(nouvelleBalle.getOval());

          
        
        
      }
   
    /**
     * Applique le bonus.
     */
}


