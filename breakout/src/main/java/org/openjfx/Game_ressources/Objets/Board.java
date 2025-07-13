package org.openjfx.Game_ressources.Objets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openjfx.Amelioration;
import org.openjfx.BreakOut;
import org.openjfx.GameOver;
import org.openjfx.Game_ressources.Audio;
import org.openjfx.Game_ressources.Bonus_jeu.*;

import org.openjfx.Game_ressources.Objets.Briques.Brique;
import org.openjfx.Game_ressources.Objets.Briques.Face;
import org.openjfx.Game_ressources.SpecialAttacks.SpecialAttack;
import org.openjfx.Score.Score;
import org.openjfx.Piece;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 * La classe Board représente le plateau de jeu dans le jeu Breakout.
 * Elle contient les briques et gère les interactions entre la balle et les briques.
 */
public class Board extends BreakOut {

    protected Amelioration ameliorationManager = new Amelioration();
    protected Score scoreManager = new Score();
    protected Piece pieceManager = new Piece();
    private Raquette raquette = new Raquette(200, height / 2 + height / 2.7, 200 + ameliorationManager.getAmeliorationLargeur(), 20, Color.YELLOWGREEN);

    // Liste des briques sur le plateau de jeu
    protected List<Brique> briques = new ArrayList<>();
    protected List<Bonus> bonuss = new ArrayList<>();
    public ArrayList<Balle> liste_balle = new ArrayList<>();
    public ArrayList<SpecialAttack> liste_attaques_speciales = new ArrayList<>();
    // Pane représentant le panneau contenant les briques
    protected Pane pBalle = new Pane();
    protected Pane pBonus= new Pane();
    protected Pane pBriques= new Pane();
    private static Board instance;
    protected Pane p = new Pane(pBonus,pBriques,pBalle,raquette.getCorp());
    // Audio pour l'audio de rebond
    private Audio bounceSFX;

    public Board() {
        liste_balle.add(new Balle(width / 2, height / 2, 3, 3, 20, Color.BLUE));
        pBalle.getChildren().add(liste_balle.get(0).getOval());
        instance = this;
        bounceSFX = new Audio();
        bounceSFX.load("Bounce_Ball_sfx_G.wav");
}

 public Board(Balle balle) {
        liste_balle.add(balle);
        pBalle.getChildren().add(balle.getOval());
        instance = this;
    }



    /**
     * Ajoute une brique au plateau de jeu.
     * 
     * @param brique La brique à ajouter.
     */
    public void add(Brique brique) {
        // Ajoute la brique à la liste des briques
        briques.add(brique);
        // Ajoute le polygone représentant la brique au panneau
        pBriques.getChildren().add(brique.getPoly());
    }

    /**
     * Vérifie si le plateau de jeu est vide.
     * 
     * @return true si le plateau de jeu est vide, false sinon.
     */
    public boolean estVide() {
        // Retourne true si la liste des briques est vide, false sinon
        return briques.isEmpty();
    }

    public boolean ConditionNivSup() {
        if(estVide()) {
            return true;
        }
        for (Brique brique : briques) {
            if (brique.getResistance() > 0) {
                return false;
            }
        }

        return true;
    }
    /**
     * Ajoute un label au plateau de jeu pour afficher du texte.
     * 
     * @param texte Le texte à afficher.
     * @param x La position x du label.
     * @param y La position y du label.
     * @param color La couleur du texte.
     */
    public void addLabel(String texte, double x, double y, Color color) {
        javafx.scene.control.Label label = new javafx.scene.control.Label(texte);
        label.setTextFill(color);
        label.setLayoutX(x);
        label.setLayoutY(y);
        p.getChildren().add(label);
        label.setStyle("-fx-font-size: 60px;");
    }
    /**
     * Gère les interactions physiques entre la balle et les briques.
     * 
     * @param balle La balle à vérifier.
     */
    public void physiques(Balle balle, Raquette raquette) {
        // Parcourt la liste des briques
        for (int i = 0; i < Math.max(briques.size(),bonuss.size()); i++) {
            ActualiserBonuss(i, raquette, balle);
            ActualiserBriques(i, balle);
        }     
    }

    /**
     * applique la physique du board
     * @param balle balle sur laquelle on test la physique et l'applique si une interaction est detecter
     */

    public void physiques2(Balle balle,Raquette raquette){

        List<Brique> brique_sel= new ArrayList<>();
        List<Face> faces = new ArrayList<>();
        List<double[]> intersections=  new ArrayList<>();

        for(int i=0;i<bonuss.size();i++){
            ActualiserBonuss(i, raquette, balle); //gere la physique des bonus
        }

        for (int i = 0; i < briques.size(); i++) {  //cette physique doit, pour fonctionner tester toute les briques pour chaque balle une a une. Désolé le H
            Brique brique = briques.get(i);
            Map.Entry<List <Face>, List<double []>> entry = brique.checkColB2(balle);//recuperation des face et point d'intersection avce la balle pour chaque brique
            faces.addAll(entry.getKey());
            intersections.addAll(entry.getValue());

            if(((List<Face>)entry.getKey()).size()!=0){
                for(int j=0;j<((List<Face>)entry.getKey()).size();j++){
                    brique_sel.add(brique);
                }
                
            }
        }
        double distance_carre_min=1000;
        double distance_carre_actuelle;
        Face face_selec=null;
        Brique brique_modif=null;

        for (int i = 0; i < faces.size(); i++){
            double []centre_balle= {balle.getPosX(),balle.getPosY()};
            distance_carre_actuelle= dpoint_carre(intersections.get(i), centre_balle);

            if (distance_carre_actuelle<distance_carre_min){
                face_selec=faces.get(i);
                distance_carre_min= distance_carre_actuelle; //détermine la face que la balle est sensé avoire touché en premier
                brique_modif=brique_sel.get(i);
            }
        }

        if (face_selec!=null){
             balle.rebond(face_selec);//applique le nouveau vecteur determiner par le vecteur normal de la face
             brique_modif.physique2();
             if (!brique_modif.getEstVisible()) {
                scoreManager.registerHit();                        // Gère les coups consécutifs
                scoreManager.addScore(brique_modif.getPoint_brique()); // Mise à jour du score
                // Supprime la brique de la liste des briques
                briques.remove(brique_modif);
                // Supprime le polygone représentant la brique du panneau
                pBriques.getChildren().remove(brique_modif.getPoly());
                //ajout du bonus
                this.addBonus(brique_modif);
            }
        
        }
    }

    public double dpoint_carre(double [] p1,double [] p2){
        return Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2);
    }

    /**
     * Gère les interactions physiques entre l'attaque spéciale et les briques.
     * 
     * @param balle La balle à vérifier.
     */
    public void physiquesSpecialAttack(SpecialAttack specialAttack) {
        // Parcourt la liste des briques
        for (int i = 0; i < Math.max(briques.size(),bonuss.size()); i++) {
            ActualiserBriquesSpecialAttack(i, specialAttack);
        }     
    }

    private void addBonus(Brique brique){
        if (brique.getBonus()!=null){
            Bonus bonus = brique.getBonus();
            pBonus.getChildren().add(bonus.getCercle());
            bonuss.add(bonus);
        }
    }

    private void ActualiserBriques(int i, Balle balle){
        try {
            // Obtient la brique à l'index i
            Brique brique = briques.get(i);
            
            // Gère l'interaction physique entre la brique et la balle
            brique.physique(balle);
            // Vérifie si la brique n'est plus visible
            if (!brique.getEstVisible()) {
                scoreManager.registerHit();                        // Gère les coups consécutifs
                scoreManager.addScore(brique.getPoint_brique()); // Mise à jour du score
                // Supprime la brique de la liste des briques
                briques.remove(i);
                // Supprime le polygone représentant la brique du panneau
                pBriques.getChildren().remove(brique.getPoly());
                //ajout du bonus
                this.addBonus(brique);
            }
        }
        catch (Exception e){

        }
    }



    private void ActualiserBriquesSpecialAttack(int i, SpecialAttack specialAttack){
        try {
            // Obtient la brique à l'index i
            Brique brique = briques.get(i);
            
            // Gère l'interaction physique entre la brique et la balle
            brique.physiqueSpecialAttack(specialAttack);
            // Vérifie si la brique n'est plus visible
            if (!brique.getEstVisible()) {
                scoreManager.registerHit();                        // Gère les coups consécutifs
                scoreManager.addScore(brique.getPoint_brique()); // Mise à jour du score
                // Supprime la brique de la liste des briques
                briques.remove(i);
                // Retire 1 de resitance à la special attack
                specialAttack.takeResistanceDamage(1);
                // Supprime le polygone représentant la brique du panneau
                pBriques.getChildren().remove(brique.getPoly());
                //ajout du bonus
                this.addBonus(brique);
            }
        }
        catch (Exception e){

        }
    }

    private void ActualiserBonuss(int i,Raquette raquette, Balle balle){
        try {
            Bonus bonus = bonuss.get(i);
            char Statut= bonus.GestionBonus(raquette);

            if (Statut == 'T' ){
                System.out.println("Ajout de la balle");
                bonus.activeBonus(raquette, 5000);
                bonus.activeBonus(balle);
                
                pBonus.getChildren().remove(bonus.getCercle());
                bonuss.remove(i);
            }
            if (Statut == 'S' ){
                pBonus.getChildren().remove(bonus.getCercle());
                bonuss.remove(i);
            } 
        }
        catch (Exception e){

        }
    }


     /**
     * Vérifie si les balles touchent les bordures du jeu.
     * Si une balle touche un mur, sa direction est inversée.
     * Si une balle touche le sol, le jeu se termine.
     */
    public void border() {
        ArrayList<Balle> ballesPerdues = new ArrayList<>();
        for (Balle balle : liste_balle) {
            // Vérifie si la balle touche les murs gauche ou droit
            if (balle.getPosX() + balle.getRayon() > BreakOut.width || balle.getPosX() - balle.getRayon() < 0) {
                // Inverse la vitesse en X de la balle
                balle.setVitesseX(-balle.getVitesseX());
                // Joue le SFX de rebond
                bounceSFX.play();
            }
            // Vérifie si la balle touche le plafond
            if (balle.getPosY() < 0) {
                // Inverse la vitesse en Y de la balle
                balle.setVitesseY(-balle.getVitesseY());
                // Joue le SFX de rebond
                bounceSFX.play();
            }
            // Vérifie si la balle touche le sol
            if (balle.getPosY() > height) {
                // Ajoute la balle à la liste des balles perdues
                
                ballesPerdues.add(balle);
            }
        }
        // Supprime les balles perdues de la liste des balles et du panneau principal
        for (Balle balle : ballesPerdues) {
            p.getChildren().remove(balle.oval);
            liste_balle.remove(balle);
        }
        // Vérifie si toutes les balles ont été perdues
        if (liste_balle.isEmpty()) {
            // Change la page courante pour l'écran de fin de jeu
            scoreManager.updateHighScores(); // Vérifie et sauvegarde le meilleur score
            pieceManager.ajoutPiece(scoreManager.getScore() * 0.2);   // Calcule le nombre de piece obtenu en fonction du score.
            currentPage = new GameOver(scoreManager.getScore());
        }
    }


     /**
     * Déplace la raquette en fonction de la position de la souris.
     */
    public void deplace_raquette() {
        // Définit un événement de déplacement de la souris pour déplacer la raquette
        p.setOnMouseMoved(e -> raquette.deplacer(e.getX()));
        // Dessine la raquette
        raquette.dessiner_raquette();
    }


    public void FrameBoard10ms() {

        for (Balle balle : liste_balle) {
            raquette.rebond(balle);
            // Met à jour la position de la balle
            balle.mettreAJour();
            // Dessine la balle
            balle.dessiner();
        }

        for (Balle balle : liste_balle) {
                physiques2(balle,raquette);
        }

        // Les SpecialAttacks sont des objets permettant de détruire des briques comme la balle: par exemple, on a le Laser. 
        //La balle n'est pas une specialattack évidemment.
        for (SpecialAttack specialAttack : liste_attaques_speciales) {
            specialAttack.mettreAJour();
            specialAttack.dessiner();
            physiquesSpecialAttack(specialAttack);
        }

        border();

     // Déplace la raquette en fonction de la position de la souris
        deplace_raquette();
    }


public ArrayList<Balle> getListe_balle() {
    return liste_balle;
    
}

    /**
     * Obtient le panneau contenant les briques.
     * 
     * @return Le panneau contenant les briques.
     */
    public Pane getp() {
        return p;
    }

    /**
     * Obtient la liste des briques sur le plateau de jeu.
     * 
     * @return La liste des briques sur le plateau de jeu.
     */
    public List<Brique> getBriques() {
        return briques;
    }

public static Board getInstance() {
    if (instance == null) {
        instance = new Board();
    }
    return instance;
}


/**
 * Ajoute une balle à la liste des balles et au panneau.
 * 
 * @param nouvelleBalle La balle à ajouter.
 */
public void ajoute_balle(Balle nouvelleBalle) {
    liste_balle.add(nouvelleBalle);
    pBalle.getChildren().add(nouvelleBalle.getOval());
}

/**
 * Getter de raquette utiliser dans Run pour gérer la rotation
 */
public Raquette raquette(){return raquette;}
}