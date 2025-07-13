package org.openjfx;
import java.util.prefs.Preferences;

/**
 * Classe Amelioration permettant de gérer les améliorations acheté dans le shop.
 */
public class Amelioration {
    private Preferences prefAmelioration;

    /**
     * Constructeur d'Amelioration
     */
    public Amelioration(){
        prefAmelioration = Preferences.userNodeForPackage(Amelioration.class);
    }

    /**
     * Fonction permettant de récupérer la largeur bonus que le joueur possède.
     * 
     * @return Largeur bonus
     */
    public double getAmeliorationLargeur(){
        return prefAmelioration.getDouble("AmeliorationLargeur", 0);
    }

    /**
     * Fonction permettant de rajouter de la largeur dans l'amélioration après un achat.
     * 
     * @param largeurRajout Largeur à rajouter
     */
    public void ajoutLargeur(double largeurRajout){
        if (largeurRajout < 0) {
            throw new IllegalArgumentException("On ne peut pas rajouter une largeur négative");
        }
        prefAmelioration.putDouble("AmeliorationLargeur", getAmeliorationLargeur()+largeurRajout);
    }

    /**
     * Fonction permettant de récupérer le nombre d'amélioration effectué pour la largeur afin de créer une limite d'amélioration.
     * 
     * @return Nombre amélioration
     */
    public int getNombreAmeliorationLargeur(){
        return prefAmelioration.getInt("NombreAmeliorationLargeur", 0);
    }

    /**
     * Fonction permettant d'ajouter un nombre au nombre d'amélioration effectué.
     * 
     * @param max Nombre d'amélioration à rajouter
     */
    public void ajoutNombreAmeliorationLargeur(int nbAmeliorationRAjout){
        if (nbAmeliorationRAjout < 0) {
            throw new IllegalArgumentException("On ne peut pas rajouter un maximum négatif");
        }
        prefAmelioration.putInt("NombreAmeliorationLargeur",getNombreAmeliorationLargeur()+ nbAmeliorationRAjout);
    }

    /**
     * Permet de vider les sauvegardes par rapport à l'amélioration de largeur.
     */
    public void clearAmeliorationLargeur(){
        prefAmelioration.putDouble("AmeliorationLargeur", 0);
        prefAmelioration.putInt("NombreAmeliorationLargeur",0);
    }

    /**
     * Fonctione permettant de récupérer si oui ou non le laser est acheté pour le joueur.
     * 
     * @return Boolean
     */
    public boolean getLaser(){
        return prefAmelioration.getBoolean("Laser", false);
    }

    /**
     * Fonction permettant de définir si le laser est acheté ou non par le joueur.
     * 
     * @param actif Boolean
     */
    public void setLaser(Boolean actif){
        prefAmelioration.putBoolean("Laser", actif);
    }

}
