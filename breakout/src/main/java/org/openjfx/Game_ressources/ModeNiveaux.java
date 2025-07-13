package org.openjfx.Game_ressources;

import java.util.ArrayList;
import java.util.Random;

import org.openjfx.BreakOut;
import org.openjfx.Credits;
import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Board;
import org.openjfx.Game_ressources.Objets.All_levels.*;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * La classe ModeNiveaux gère les différents niveaux du jeu.
 * Elle contient les méthodes pour passer au niveau supérieur et obtenir le niveau courant.
 */
public class ModeNiveaux  extends BreakOut{
    // Numéro du niveau courant
    private int numeroNiveau = 1;
    // Numéro du niveau final
    private int niveauFinal = 11;
    // Niveau courant
    private Niveaux niveauCourant;

    /**
     * Constructeur de la classe ModeNiveaux.
     * Initialise le niveau courant en appelant la méthode init_board().
     */
    public ModeNiveaux() {
        if (BreakOut.isEndless) {
            System.out.println("ENDLESS");
            this.niveauCourant = new Niveau_Endless();
        } else {
            this.niveauCourant = new Niveau_1();
        }System.out.println("Fait initboard");

        this.niveauCourant.init_board();
    }


    /**
     * Passe au niveau supérieur si le niveau courant est terminé.
     */
    public void level_sup() {
        try {
            // Vérifie si le plateau de jeu du niveau courant est vide
            if (niveauCourant.getTerrain().ConditionNivSup()) {
                // Vérifie si le niveau courant est le niveau final
                if (this.numeroNiveau == (this.niveauFinal +1 )) {
                    // Si c'est le niveau final, ne fait rien
                    currentPage = new Credits();
                } else {
                    // Passe au niveau suivant
                    numeroNiveau += 1;
                    // Construit le nom de la classe du niveau suivant
                    String nomClasseSuivant = "org.openjfx.Game_ressources.Objets.All_levels.Niveau_" + this.numeroNiveau;

                    // Essaye de charger la classe et de créer une nouvelle instance
                    Class<?> classeSuivante = Class.forName(nomClasseSuivant);
                    this.niveauCourant = (Niveaux) classeSuivante.getDeclaredConstructor().newInstance();
                    // Initialise le plateau de jeu du nouveau niveau
                    this.niveauCourant.init_board();
                }
            }
        } catch (Exception e) {
            // Affiche un message d'erreur en cas d'exception
            System.out.println("Erreur inattendue lors du chargement du niveau suivant.");
            e.printStackTrace();
        }
    }

    /**
     * Passe au niveau supérieur en mode Endless.
     * 
     * @param p Le panneau principal du jeu.
     */
    public void Niv_sup_Endless(Pane p) {
            System.out.println("Niveau sup");
            passe_nivsup(p);
            // Ajoute le panneau des briques au panneau principal
        }


    /**
     * Réinitialise les balles en changeant leur position et leur couleur.
     */
    public void reset_balles(Pane p) {
        ArrayList<Balle> liste_balle = this.niveauCourant.getTerrain().getListe_balle();
        for (Balle balle : liste_balle) {
            // Crée un générateur de nombres aléatoires
            Random random = new Random();
            // Génère une nouvelle couleur aléatoire pour la balle
            Color nouvelleCouleur = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());

            // Définit la nouvelle position de la balle au centre de l'écran
            double nouveauX = BreakOut.getWidth() / 2;
            double nouveauY = BreakOut.getHeight() / 2;

            // Crée des KeyValues pour animer la position et la couleur de la balle
            KeyValue moveX = new KeyValue(balle.oval.translateXProperty(), nouveauX - balle.getPosX());
            KeyValue moveY = new KeyValue(balle.oval.translateYProperty(), nouveauY - balle.getPosY());
            KeyValue colorChange = new KeyValue(balle.oval.fillProperty(), nouvelleCouleur);

            // Crée une Timeline pour animer la réinitialisation de la balle
            Timeline resetl = new Timeline();
            resetl.setCycleCount(1);

            // Crée un KeyFrame pour définir la durée de l'animation
            KeyFrame reset = new KeyFrame(Duration.seconds(3), moveX, moveY, colorChange);

            // Ajoute le KeyFrame à la Timeline
            resetl.getKeyFrames().add(reset);
            // Définit l'action à exécuter à la fin de l'animation
            resetl.setOnFinished(e -> {
                // Passe au niveau supérieur
                p.getChildren().remove(this.niveauCourant.getTerrain().getp());
                this.level_sup();
                p.getChildren().add(this.niveauCourant.getTerrain().getp());
                // Crée une nouvelle balle avec la nouvelle position et couleur
                // if (liste_balle.size() == 0) {
                //     Balle nouvelleBalle = new Balle(nouveauX, nouveauY, 3, 3, 20, nouvelleCouleur);
                //     // Ajoute la nouvelle balle à la liste des balles
                //     liste_balle.add(nouvelleBalle);
                //     // Ajoute la nouvelle balle au panneau
                //     p.getChildren().add(nouvelleBalle.oval);
                //     // Ajoute le panneau des briques au panneau principal
                //     p.getChildren().add(modeDeJeu.getBoard().getp());
                // }
            });
            // Démarre l'animation
            resetl.play();
        }
    }


    /**
     * Réinitialise les balles en mode Endless en changeant leur position et leur couleur.
     * 
     * @param p Le panneau principal du jeu.
     */
    public void reset_balles_endless(Pane p) {
        ArrayList<Balle> liste_balle = this.niveauCourant.getTerrain().getListe_balle();
        for (Balle balle : liste_balle) {
            // Crée un générateur de nombres aléatoires
            Random random = new Random();
            // Génère une nouvelle couleur aléatoire pour la balle
            Color nouvelleCouleur = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());

            // Définit la nouvelle position de la balle au centre de l'écran
            double nouveauX = BreakOut.getWidth() / 2;
            double nouveauY = BreakOut.getHeight() / 2;

            // Crée des KeyValues pour animer la position et la couleur de la balle
            KeyValue moveX = new KeyValue(balle.oval.translateXProperty(), nouveauX - balle.getPosX());
            KeyValue moveY = new KeyValue(balle.oval.translateYProperty(), nouveauY - balle.getPosY());
            KeyValue colorChange = new KeyValue(balle.oval.fillProperty(), nouvelleCouleur);

            // Crée une Timeline pour animer la réinitialisation de la balle
            Timeline resetl = new Timeline();
            resetl.setCycleCount(1);

            // Crée un KeyFrame pour définir la durée de l'animation
            KeyFrame reset = new KeyFrame(Duration.seconds(3), moveX, moveY, colorChange);

            // Ajoute le KeyFrame à la Timeline
            resetl.getKeyFrames().add(reset);
            // Définit l'action à exécuter à la fin de l'animation
            resetl.setOnFinished(e -> {
                // Réinitialise les balles pour le mode Endless
                p.getChildren().remove(this.niveauCourant.getTerrain().getp());
                this.niveauCourant = new Niveau_Endless();
                niveauCourant.init_board();
                p.getChildren().add(this.niveauCourant.getTerrain().getp());
            });

            // Démarre l'animation
            resetl.play();
        }
    }


    /**
     * Anime la raquette pendant le passage au niveau supérieur.
     */
    private void anim_barre() {
        // Crée une Timeline pour animer la raquette
        Timeline fin = new Timeline();
        fin.setCycleCount(400);

        // Crée un KeyFrame pour définir la durée de l'animation
        KeyFrame barre = new KeyFrame(Duration.millis(10), e -> {
            // Déplace la raquette en fonction de la position de la souris
            niveauCourant.getTerrain().deplace_raquette();
        });
        // Ajoute le KeyFrame à la Timeline
        fin.getKeyFrames().add(barre);

        // Définit l'action à exécuter à la fin de l'animation
        fin.setOnFinished(e -> BreakOut.continue_tl());

        // Démarre l'animation
        fin.play();
    }





    /**
     * Passe au niveau supérieur en animant la raquette et en réinitialisant les balles.
     * 
     * @param p Le panneau principal du jeu.
     */
    public void passe_nivsup(Pane p) {
        // Arrête la Timeline
        BreakOut.stop_tl();
        
        // Anime la raquette
        anim_barre();
        if(BreakOut.isEndless){
            reset_balles_endless(p);
        }else {
            reset_balles(p);
        }
        
    }



    /**
     * Obtient le plateau de jeu du niveau courant.
     * 
     * @return Le plateau de jeu du niveau courant.
     */
    public Board getBoard() {
            return this.niveauCourant.getTerrain();
        
    }
    
    
    /** 
     * @return Niveaux
     */
    public Niveaux getNiveauCourant() {
        return niveauCourant;
    }
    public int getNiveauFinal() {
        return niveauFinal;
    }
    public  int getNumeroNiveau() {
        return numeroNiveau;
    }
   
}





