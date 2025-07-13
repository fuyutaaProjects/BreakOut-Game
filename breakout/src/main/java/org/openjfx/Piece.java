package org.openjfx;
import java.util.prefs.Preferences;

/**
 * Classe Piece permettant de créer un système d'argent dans le jeu.
 */
public class Piece {
    // Noeud permettant de sauvegarder l'argent du joueur.
    private Preferences prefsPiece;

    /**
     * Constructeur de la classe Piece.
     */
    public Piece(){
        prefsPiece = Preferences.userNodeForPackage(Piece.class);
    }

    /**
     * Récupère le nombre de pièce du joueur.
     * 
     * @return Le nombre de pièce du joueur.
     */
    public double getPieces(){
        return prefsPiece.getDouble("nombrePieces", 0);
    }

    /**
     * Permet de rajouter un montant d'argent à la somme actuelle du joueur.
     * 
     * @param montant
     */
    public void ajoutPiece(double montant){
        // Cas ou le montant est négatif.
        if (montant < 0) {
            throw new IllegalArgumentException("Le montant doit être positif.");
        }
        prefsPiece.putDouble("nombrePieces", getPieces()+montant);
    }

    /**
     * Permet de retirer un montant d'argent à la somme actuelle du joueur.
     * 
     * @param montant
     */
    public void retirerPiece(double montant){
        // Cas ou le montant
        if (montant < 0) {
            throw new IllegalArgumentException("Le montant doit être positif.");
        }
        if (getPieces() < montant) {
            throw new IllegalArgumentException("Vous n'avez pas l'argent nécessaire.");
        }
        prefsPiece.putDouble("nombrePieces", getPieces()-montant);
    }
}
