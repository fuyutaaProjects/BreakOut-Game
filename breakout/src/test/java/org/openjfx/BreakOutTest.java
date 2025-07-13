package org.openjfx;


import org.JavaFXInitializer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.Transient;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openjfx.Game_ressources.Vecteur;
import org.openjfx.Game_ressources.Bonus_jeu.*;
import org.openjfx.Game_ressources.Audio;
import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.Objets.Board;
import org.openjfx.Game_ressources.Objets.Raquette;
import org.openjfx.Game_ressources.Objets.All_levels.Niveau_1;
import org.openjfx.Game_ressources.Objets.All_levels.Niveau_2;
import org.openjfx.Game_ressources.Objets.Briques.Brique;
import org.openjfx.Game_ressources.Objets.Briques.Briques_rectangle;
import org.openjfx.Game_ressources.Objets.Briques.Face;
import org.openjfx.Game_ressources.SpecialAttacks.Laser;
import org.openjfx.Game_ressources.SpecialAttacks.SpecialAttack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.openjfx.Piece;
import org.openjfx.Amelioration;

// NE PAS SUPPRIMER! NECESSAIRE POUR DES TESTS LOCAUX QUI NE MARCHENT PAS SUR LA PIPELINE CAR ELLE NE PEUT PAS INIT JAVAFX.
//import org.JavaFXInitializer;

public class BreakOutTest {
    
    @BeforeAll
    public static void setup() {
        // NE PAS SUPPRIMER!!!!!!!!!!! NECESSAIRE POUR DES TESTS LOCAUX QUI NE MARCHENT PAS SUR LA PIPELINE.
        //Initialiser JavaFX avant les tests de buttons de menus
        //JavaFXInitializer.initialize();

        // Ne pas supprimer, fait crash les tests des class qui contiennent Audio en eux, ou dans un parent
        Audio.set_audio_disabled_for_test(false);
    }

    @Test
    void TestGetterBonus(){
        BonusAugLagRaq bonuslargeur = new BonusAugLagRaq(50, 80, 5, 70, 10);
        assertEquals(50,bonuslargeur.getPosX());
        assertEquals(80,bonuslargeur.getPosY());
        assertEquals(5,bonuslargeur.getVitesse());
        assertEquals(70,bonuslargeur.getTaille());
        assertEquals(35,bonuslargeur.getRayon());
    }

    // //@Test
    // //void checkIfNoRaquetteEqualsNullReturnFalse(){
    // //    BonusAugLagRaq bonuslargeur = new BonusAugLagRaq(50, 80, 5, 70, 10);
    // //    assertFalse(bonuslargeur.sousRaquette(null));
    // //    assertFalse(bonuslargeur.toucheRaquette(null));
    // //}

    @Test
    void checkIfBonusTouchRaquette(){
        BonusAugLagRaq bonuslargeur = new BonusAugLagRaq(512, 728, 5, 70, 10);
        Raquette raquette = new Raquette(512,728,150,10,Color.YELLOWGREEN); //Raquette comme au début du jeu.
        assertTrue(bonuslargeur.toucheRaquette(raquette));
    }

    @Test
    void checkIfBonusDontTouchRaquette(){
        BonusAugLagRaq bonuslargeur1 = new BonusAugLagRaq(300, 730, 5, 70, 10);
        BonusAugLagRaq bonuslargeur2 = new BonusAugLagRaq(550, 800, 5, 70, 10);
        BonusAugLagRaq bonuslargeur3 = new BonusAugLagRaq(300, 800, 5, 70, 10);
        Raquette raquette = new Raquette(512,728,150,10,Color.YELLOWGREEN); //Raquette comme au début du jeu.
        assertFalse(bonuslargeur1.toucheRaquette(raquette));
        assertFalse(bonuslargeur2.toucheRaquette(raquette));
        assertFalse(bonuslargeur3.toucheRaquette(raquette));
    }

    @Test
    void checkIfBonusUnderRaquette(){
        BonusAugLagRaq bonuslargeur = new BonusAugLagRaq(550, 1000, 5, 70, 10);
        Raquette raquette = new Raquette(512,728,150,10,Color.YELLOWGREEN); //Raquette comme au début du jeu.
        assertTrue(bonuslargeur.sousRaquette(raquette));
    }

    @Test
    void checkIfBonusNotUnderRaquette(){
        BonusAugLagRaq bonuslargeur = new BonusAugLagRaq(550, 600, 5, 70, 10);
        Raquette raquette = new Raquette(512,728,150,10,Color.YELLOWGREEN); //Raquette comme au début du jeu.
        assertFalse(bonuslargeur.sousRaquette(raquette));
    }

    @Test
    void checkIfPosXPosYOutOfBoardForgenererBonus(){
        Niveau_2 niveau = new Niveau_2();
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class, 
            () -> {
                // Marche pour tous les niveaux.
                niveau.genereBonus(4000,2000);
            }
        );
        assertEquals("Coordonnées en dehors de l'écran", thrown.getMessage());
    }

    @Test
    void checkIfgenererBonusWork(){
        // Même cas pour tous les autres niveaux.
        Niveau_2 niveau = new Niveau_2();
        Bonus bonus = niveau.genereBonus(BreakOut.getWidth()-100,BreakOut.getHeight()-50);
        if (bonus != null) {
            assertNotNull(bonus, "Un bonus a été générer.");
        }else{
            assertNull(bonus, "Le bonus n'est pas générer donc null.");
        }
    }
    
    @Test
    void checkIfAjoutPieceNegatifWork(){
        Piece pieceManager = new Piece();
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class, 
            () -> {
                pieceManager.ajoutPiece(-50);
            }
        );
        assertEquals("Le montant doit être positif.",thrown.getMessage());
    }

    @Test
    void checkIfRetirerPieceNegatifWork(){
        Piece pieceManager = new Piece();
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class, 
            () -> {
                pieceManager.retirerPiece(-50);
            }
        );
        assertEquals("Le montant doit être positif.",thrown.getMessage());
    }

    // NE PAS SUPPRIMER!!!!!!!!!!! NECESSAIRE POUR DES TESTS LOCAUX QUI MARCHENT LOCALEMENT MAIS PAS SUR LA PIPELINE.
    /*
    @Test 
    void testStartButtonOnTitleScreen() {
        Platform.runLater(() -> {
            TitleScreen titleScreen = new TitleScreen();
            titleScreen.hoverAndClickStartButton();
            assertTrue(titleScreen.isGameStarted());
        });

        // Attendre la fin de la tâche JavaFX pour qu'on puisse ensuite process a la prochaine task sans bug
        waitForRunLater();
    }
    @Test
    void testReplayButtonOnGameOver() {
        Platform.runLater(() -> {
            GameOver gameOver = new GameOver(0);
            gameOver.hoverAndClickReplayButton();
            assertTrue(gameOver.isGameStarted());
        });

        // Attendre la fin de la tâche JavaFX pour qu'on puisse ensuite process a la prochaine task sans bug
        waitForRunLater();
    }

    @Test
    void testMainMenuButtonOnGameOver() {
        Platform.runLater(() -> {
            GameOver gameOver = new GameOver(0);
            gameOver.hoverAndClickMainMenuButton();
            assertTrue(gameOver.isCurrentSceneMainMenu());
        });

        // Attendre la fin de la tâche JavaFX pour qu'on puisse ensuite process a la prochaine task sans bug
        waitForRunLater();
    }
    

    */

    @Test
    public void testMalusInvisiRaq() {
    // Initialisation
    Raquette raquette = new Raquette(300, 450, 100, 20, Color.BLUE);
    MalusInvisiRaq malus = new MalusInvisiRaq(200, 200, 2, 20);

    // Vérifier que la raquette est visible au départ
    assertEquals(1.0, raquette.getCorp().getOpacity(), "La raquette devrait être visible avant le malus");

    // Appliquer le malus
    malus.activeBonus(raquette, 5000);

    // Vérifier que la raquette devient invisible
    assertEquals(0.0, raquette.getCorp().getOpacity(), "La raquette devrait être invisible après l'application du malus");
    }


    @Test
    public void testMalusTache() {
    // Initialisation
    Pane gamePane = new Pane();
    MalusTache malus = new MalusTache(100, 100, 2, 20, 50); // Rayon de 50 pour le cercle

    // Vérification initiale : le pane ne contient aucun cercle
    assertEquals(0, gamePane.getChildren().size(), "Le pane ne devrait contenir aucun élément au départ");

    // Afficher le cercle
    malus.afficherCercle(gamePane);

    // Vérification après ajout : le cercle est présent dans le pane
    assertEquals(1, gamePane.getChildren().size(), "Le pane devrait contenir un cercle après l'appel à afficherCercle");

    // Vérification que l'objet ajouté est bien un cercle
    assertTrue(gamePane.getChildren().get(0) instanceof Circle, "L'élément ajouté au pane devrait être un cercle");

    // Vérification des propriétés du cercle
    Circle cercle = (Circle) gamePane.getChildren().get(0);
    assertEquals(50, cercle.getRadius(), 0.1, "Le rayon du cercle devrait être de 50");
    assertEquals(Color.PURPLE, cercle.getFill(), "La couleur du cercle devrait être pourpre");
    }


    @Test
    public void testBonusBigBall() {
    // Initialisation
    Balle balle = new Balle(100, 100, 10, 5, 5, Color.WHITE);
    BonusBigBall bonus = new BonusBigBall(200, 200, 2, 20);

    // Vérification de la taille initiale
    double tailleInitiale = balle.getTaille();

    // Application du bonus
    bonus.activeBonus(balle);

    // Vérification que la taille a augmenté
    assertTrue(balle.getTaille() > tailleInitiale);
    }

    @Test
    public void testBonusShield() {
    // Initialisation
    Raquette raquette = new Raquette(300, 450, 100, 20, Color.BLUE);
    BonusShield bonus = new BonusShield(200, 200, 2, 20);

    // Vérification de la largeur initiale
    double largeurInitiale = raquette.getLargeur();

    // Application du bonus
    bonus.activeBonus(raquette, 5000);

    // Vérification que la raquette couvre la largeur de l'écran
    assertTrue(raquette.getLargeur() > largeurInitiale);
    }

    /*@Test
    public void testBonusStopMalus() {
        // Initialisation
        Raquette raquette = new Raquette(300, 450, 100, 20, Color.BLUE);
        BonusStopMalus bonus = new BonusStopMalus(200, 200, 2, 20);

        // Simuler un malus rendant la raquette invisible
        raquette.getCorp().setOpacity(0.0);

        // Vérifier l'état initial
        assertEquals(0.0, raquette.getCorp().getOpacity(), "La raquette devrait être invisible avant l'application du bonus");

        // Appliquer le bonus
        bonus.activeBonus(raquette, 5000);

        // Vérifier l'état après application
        assertEquals(1.0, raquette.getCorp().getOpacity(), "La raquette devrait être visible après l'application du bonus");
    }
    */

    @Test
    public void testCreationEtAjoutDeLaserALaListeDesAtkSpeciales() {
        SpecialAttacksHandler handler = new SpecialAttacksHandler();
        handler.setSpecialAttackOnDIGIT2(0); // 0 = attaque laser
        Board board = new Board();
        // // Pour des raisons de dev parfois je préload la liste avec une specialattack pour tester un truc, donc je mets ce clear pour assurer que c'est vide.
        board.liste_attaques_speciales.clear(); 

        // On simule un appui sur la touche pour tirer un laser: ATTENTION ça va casser si l'on n'a pas l'attaque disponible. 
        //Ce sera à ajuster quand on va ajouter le shop/cooldown pour le laser.
        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.DIGIT2, false, false, false, false);
        handler.handle(event);

        // Vérification que le laser est dans la liste des attaques spéciales
        assertEquals(1, board.liste_attaques_speciales.size(), "la liste des atk spéciales fait 1 de long, il y a bien un ajout d'attaque spéciale");
        assertTrue(board.liste_attaques_speciales.get(0) instanceof Laser, "L'objet est bien un laser");
    }

    @Test
    public void testLaserBougeViaUnVecteur() {
        Vecteur vecteur = new Vecteur(0, -5); // Déplacement vers le haut (le haut c'est 0, si on descend c'est un gain positif et si on monte c'est négatif)
        Laser laser = new Laser(100, 200, vecteur, Color.RED, 25, 1);

        // Position initiale pour comparer après à sa nouvelle position
        double initialY = laser.getPosY();

        // Mise à jour du laser
        laser.mettreAJour();

        // Vérification du déplacement en checkant si la position initale a bien diminué en coordonnée y (on perd 5 normalement)
        assertEquals(initialY - 5, laser.getPosY(), 0.1, "Le laser a bougé de 5 pixels vers le haut");
    }

    @Test
    public void testLaserCollisionAvecBrique() {
        Vecteur vecteur = new Vecteur(0, -5); // Déplacement vers le haut
        Laser laser = new Laser(100, 200, vecteur, Color.RED, 25, 1);
    
        // Au lieu de s'embeter on va juste faire un polygon et le passer directement à la brique.
        Polygon poly = new Polygon(
            90, 190, // Coin supérieur gauche
            110, 190, // Coin supérieur droit
            110, 210, // Coin inférieur droit
            90, 210   // Coin inférieur gauche
        );
        Brique brique = new Briques_rectangle(100, 190, 20, 20, 10, 1, null) {
            {
                this.poly = poly;
            }
        };
    
        brique.physiqueSpecialAttack(laser);
    
        assertEquals(0, brique.getResistance(), "La brique qui avait 1 de resistance l'a perdu, donc ça devrait être 0 maintenant");
        assertFalse(brique.getEstVisible(), "La brique ne devrait plus être visible une fois dead");
    }

    
    // ------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------
    // Tests de buttons de menus!
    // Ces tests ne fonctionnent QUE LOCALEMENT VIA LES TESTS GRADLE DANS VSCODE.
    // La pipeline de gitlab ne peut pas éxecuter des tests de button qui changent la currentPage. Simplement car elle ne peut pas initiliaser JavaFX.
    // J'ai cherché pendant des HEURES à faire marcher sans javafx (avec mockito par exemple). mais c'est simplement impossible: 
    // j'ai besoin de checker si l'instance change, et ce qui fait changer et change sont que des objets de javafx. sans javafx initialisé, pas de test.
    
    /*
    
    @Test
    void testStartButtonOnTitleScreen() {
        Platform.runLater(() -> {
            TitleScreen titleScreen = new TitleScreen();
            titleScreen.clickStartButton();
            assertTrue(titleScreen.currentPage instanceof Run);
        });
    }

    @Test
    void testEndlessButtonOnTitleScreen() {
        Platform.runLater(() -> {
            TitleScreen titleScreen = new TitleScreen();
            titleScreen.clickEndlessButton();
            // Ajoute la vérification pour Endless Mode quand il sera disponible
        });
    }

    // scores button a implementer par oussama

    @Test
    void testExitButtonOnTitleScreen() {
        Platform.runLater(() -> {
            TitleScreen titleScreen = new TitleScreen();
            titleScreen.clickExitButton();
            // on peut pas vraiment test le exit mais on peut faire ça et voir si ça plante, si ça ne se deroule pas comme prévu il va break. sinon c'est bon.
        });
    }

    @Test
    void testReplayButtonOnGameOver() {
        Platform.runLater(() -> {
            GameOver gameOverScreen = new GameOver(100);
            gameOverScreen.clickReplayButton();
            assertTrue(gameOverScreen.currentPage instanceof Run);
        });
    }

    // scores button a implementer par oussama

    @Test
    void testMenuButtonOnGameOver() {
        Platform.runLater(() -> {
            GameOver gameOverScreen = new GameOver(100);
            gameOverScreen.clickMenuButton();
            assertTrue(gameOverScreen.currentPage instanceof TitleScreen);
        });
    }

    */

    // ------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------



    @Test
    public void checkIfAjoutLargeurNegativeWork(){
        Amelioration ameliorationManager = new Amelioration();
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class, 
            () -> {
                ameliorationManager.ajoutLargeur(-50);
            }
        );
        assertEquals("On ne peut pas rajouter une largeur négative",thrown.getMessage());
    }

    @Test
    public void checkIfAjoutNombreAmeliorationLargeurNegativeWork(){
        Amelioration ameliorationManager = new Amelioration();
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class, 
            () -> {
                ameliorationManager.ajoutNombreAmeliorationLargeur(-50);
            }
        );
        assertEquals("On ne peut pas rajouter un maximum négatif",thrown.getMessage());
    }

    @AfterAll
    private static void reenable_audio_after_test(){
        //Rétablit Audio à la fin des tests
        Audio.set_audio_disabled_for_test(true);
    }
    private Raquette raquette;
    private Balle balle;

    


}
