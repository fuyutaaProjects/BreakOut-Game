package org.openjfx;

import java.util.prefs.Preferences;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.Score.Score;

public class TestScore {

    private Score score;

    private Preferences prefs;

    @BeforeEach
    public void setup() {
        // Réinitialise une instance de Score avant chaque test
        score = new Score();
        
        // Réinitialise les préférences
        prefs = Preferences.userNodeForPackage(Score.class);
        resetPreferences();

        // Réinitialise les scores en mémoire
        score.resetScore();
    }

    private void resetPreferences() {
        prefs.putInt("highScore1", 0);
        prefs.putInt("highScore2", 0);
        prefs.putInt("highScore3", 0);
    }

    @Test
    public void testAjoutScore() {
        // Ajout de 10 points
        score.addScore(10);
        assertEquals(10, score.getScore(), "Le score devrait être égal à 10 après l'ajout.");

        // Ajout de 20 points supplémentaires
        score.addScore(20);
        assertEquals(30, score.getScore(), "Le score devrait être égal à 30 après l'ajout.");
    }

    @Test
    public void testMeilleurScore() {
        // Définir un score élevé
        score.addScore(100);
        score.updateHighScores();

        assertEquals(100, score.getHighScore1(), "Le meilleur score devrait être égal à 100.");

        // Ajout d'un nouveau score plus élevé
        score.resetScore(); // Réinitialise le score pour simuler une nouvelle partie
        score.addScore(150);
        score.updateHighScores();

        assertEquals(150, score.getHighScore1(), "Le meilleur score devrait être mis à jour à 150.");
        assertEquals(100, score.getHighScore2(), "Le deuxième meilleur score devrait être 100.");

        // Ajout d'un troisième score élevé
        score.resetScore();
        score.addScore(120);
        score.updateHighScores();

        assertEquals(150, score.getHighScore1(), "Le premier meilleur score devrait rester 150.");
        assertEquals(120, score.getHighScore2(), "Le deuxième meilleur score devrait être mis à jour à 120.");
        assertEquals(100, score.getHighScore3(), "Le troisième meilleur score devrait être mis à jour à 100.");
    }

    @Test
    public void testResetScore() {
        score.addScore(20);
        score.resetScore();
        assertEquals(0, score.getScore(), "Le score doit être réinitialisé à 0.");
    }

    @Test
    public void testConsecutiveHitsEtMultiplier() {
        score.registerHit();
        score.registerHit(); // Deuxième coup consécutif pour activer le multiplicateur
        assertEquals(2, score.getMultiplier(), "Le multiplicateur doit être activé et égal à 2.");
    }

    @Test
    public void testResetHits() {
        score.registerHit();
        score.registerHit();
        score.resetHits(); // Réinitialise les coups consécutifs
        assertEquals(1, score.getMultiplier(), "Le multiplicateur doit être réinitialisé à 1.");
    }
}
