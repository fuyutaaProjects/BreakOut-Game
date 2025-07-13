package org.openjfx.Score;

import java.util.prefs.Preferences;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Score {
    private static int score = 0;             // Score actuel du joueur
    private static int highScore1 = 0;        // Meilleur score
    private static int highScore2 = 0;        // Deuxième meilleur score
    private static int highScore3 = 0;        // Troisième meilleur score
    private static int consecutiveHits = 0;  // Coups consécutifs sans rater
    private static int multiplier = 1;       // Valeur actuelle du multiplicateur
    private static boolean multiplierShow = false; // État d'affichage du multiplicateur
    private static double sizeMultiplier = 1;      // Taille d'affichage du multiplicateur
    private static int AddMultiplier = 10;         // Points additionnels avec le multiplicateur
    private Preferences prefs;                // Préférences pour stocker les meilleurs scores

    // Constructeur : initialise les préférences et charge les meilleurs scores
    public Score() {
        prefs = Preferences.userNodeForPackage(Score.class); // Initialisation des préférences
        loadHighScores(); // Charger les meilleurs scores au démarrage
    }

    // Charge les meilleurs scores depuis les préférences
    private void loadHighScores() {
        highScore1 = prefs.getInt("highScore1", 0); // 0 par défaut si aucune valeur n'est enregistrée
        highScore2 = prefs.getInt("highScore2", 0);
        highScore3 = prefs.getInt("highScore3", 0);
    }

    // Sauvegarde les meilleurs scores dans les préférences
    private void saveHighScores() {
        prefs.putInt("highScore1", highScore1);
        prefs.putInt("highScore2", highScore2);
        prefs.putInt("highScore3", highScore3);
    }

    // Met à jour les meilleurs scores si le score actuel est supérieur
    public void updateHighScores() {
        if (score > highScore1) {
            // Décale les scores existants
            highScore3 = highScore2;
            highScore2 = highScore1;
            highScore1 = score; // Nouveau meilleur score
        } else if (score > highScore2) {
            highScore3 = highScore2;
            highScore2 = score; // Nouveau deuxième meilleur score
        } else if (score > highScore3) {
            highScore3 = score; // Nouveau troisième meilleur score
        }
        saveHighScores(); // Sauvegarde les nouveaux scores
    }

    
    /** 
     * @param points
     */
    // Ajoute des points au score en tenant compte du multiplicateur
    public void addScore(int points) {
        score += points * multiplier;
    }

    // Enregistre un coup réussi et active le multiplicateur au second coup
    public void registerHit() {
        consecutiveHits++;
        if (consecutiveHits >= 2) {
            activateMultiplier();
        }
    }

    // Réinitialise les coups consécutifs et désactive le multiplicateur
    public void resetHits() {
        consecutiveHits = 0;
        resetMultiplier();
    }

    // Active le multiplicateur et augmente sa taille d'affichage
    public void activateMultiplier() {
        multiplier++;
        AddMultiplier += (10 * multiplier); // Augmente les points bonus avec le multiplicateur
        sizeMultiplier += 0.5;
        multiplierShow = true;
    }

    // Réinitialise le multiplicateur et sa taille d'affichage
    public void resetMultiplier() {
        multiplier = 1;
        AddMultiplier = 10;
        sizeMultiplier = 1;
        multiplierShow = false;
    }

    // Réinitialise le score pour une nouvelle partie
    public void resetScore() {
        score = 0;
    }

    
    /** 
     * @param gc
     * @param width
     * @param height
     */
    // Affiche le score actuel
    public void drawScore(GraphicsContext gc, double width, double height) {
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("Score: " + score, width , height );
    }

    
    /** 
     * @param gc
     * @param width
     * @param height
     */
    // Affiche uniquement le meilleur score
    public void drawBestScore(GraphicsContext gc, double width, double height) {
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("Meilleur score: " + highScore1, width , height );
    }

    
    /** 
     * @param gc
     * @param width
     * @param height
     */
    // Affiche le multiplicateur actif au centre
    public void drawMultiplier(GraphicsContext gc, double width, double height) {
        if (multiplierShow) {
            gc.setFont(Font.font((width * 0.02) * sizeMultiplier));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("x" + multiplier + "  +" + AddMultiplier, width * 0.5, height * 0.5);
        }
    }

    
    
    // Accesseurs pour obtenir les valeurs des scores
    public int getScore() {
        return score;
    }

    public int getHighScore1() {
        return highScore1;
    }

    public int getHighScore2() {
        return highScore2;
    }

    public int getHighScore3() {
        return highScore3;
    }
    public int getMultiplier(){
        return multiplier;
    }
    // methode qui va nous servire seulement dans la class TestPageScore
    public void setHighScores(int i, int j, int k) {
        highScore1 = i;
        highScore2 = j;
        highScore3 = k;

    }
}
