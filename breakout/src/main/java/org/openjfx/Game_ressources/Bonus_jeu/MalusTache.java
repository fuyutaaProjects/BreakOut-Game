package org.openjfx.Game_ressources.Bonus_jeu;

import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Raquette;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import java.util.Random;

public class MalusTache extends Bonus {

    private double rayon; // Rayon du cercle qui va couvrir une partie de l'écran
    // Couleur du bonus
    private Color couleur;

    /**
     * Constructeur de la classe MalusTache.
     * 
     * @param posX     Position X du bonus.
     * @param posY     Position Y du bonus.
     * @param vitesse  Vitesse de déplacement du bonus.
     * @param taille   Taille du bonus.
     * @param rayon    Rayon du cercle à afficher qui couvrira une petite portion de l'écran.
     */
    public MalusTache(double posX, double posY, double vitesse, double taille, double rayon) {
        super(posX, posY, vitesse, taille);
        this.rayon = rayon;
        this.couleur = Color.RED;
        super.cercle.setFill(couleur);
    }

    /**
     * Active le bonus sur la raquette (cette méthode est vide pour ce malus).
     * 
     * @param raquette La raquette sur laquelle activer le bonus.
     * @param temps    La durée pendant laquelle le bonus est actif.
     */
    @Override
    public void activeBonus(Raquette raquette, long temps) {
        // Ce malus n'affecte pas la raquette donc la méthode est vide.
    }

    /**
     * Active le bonus sur la balle (cette méthode ne fait rien pour ce malus).
     * 
     * @param balle La balle sur laquelle activer le bonus.
     */
    @Override
    public void activeBonus(Balle balle) {
        // Ce malus n'affecte pas directement la balle.
    }

    /**
     * Affiche un cercle à une position aléatoire sur l'écran pour couvrir une partie de celui-ci.
     * 
     * @param pane Le conteneur (par exemple, un `Pane`) dans lequel afficher le cercle.
     */
    public void afficherCercle(Pane pane) {
        Random rand = new Random();
        // Position aléatoire du cercle
        double posX = rand.nextDouble() * (pane.getWidth() - rayon * 2);
        double posY = rand.nextDouble() * (pane.getHeight() - rayon * 2);
        
        // Crée un cercle avec le rayon spécifié et la position aléatoire
        Circle cercle = new Circle(posX + rayon, posY + rayon, rayon);
        cercle.setFill(Color.PURPLE); // Le cercle peut être d'une couleur sombre pour "couvrir" une partie de l'écran
        
        // Ajouter le cercle au Pane
        pane.getChildren().add(cercle);
    }
}
