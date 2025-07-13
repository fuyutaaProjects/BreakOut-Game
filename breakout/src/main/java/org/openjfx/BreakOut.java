package org.openjfx;

import org.openjfx.Game_ressources.Audio;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration; 

//--------------------------------
// Zone de tests de push pour la 1ere séance: écrire vos trucs ici
// fuyutaaProjects here!
// Mohamed Here, test push avec ce commentaire en ajout(PS : Je suis en CM de Maths), super
// Ethan here, test push.
// osama test push.
// wassime test push.oui vraiment là
// TEST
//-------------------------------

/**
 * La classe BreakOut est la classe principale de l'application.
 * Elle initialise la fenêtre du jeu et gère la boucle principale.
 */
public class BreakOut extends Application { // oui BreakOut ne peut pas etre abstraite sinon ca bug dommage voila

    // Largeur de la fenêtre du jeu
    protected static final int width = 1024;
    // Hauteur de la fenêtre du jeu
    protected static final int height = 768;
    // Score du joueur
    protected int score = 0;
    //isENdless
    public static boolean isEndless = false;

    // Page courante du jeu
    protected static BreakOut currentPage;
    // Fenêtre principale du jeu
    protected static Stage Tstage;
    // Timeline pour gérer l'animation du jeu
    protected static Timeline tl;
    // KeyFrame pour définir la boucle principale du jeu
    protected static KeyFrame kf;

    //Audio de la musique du niveau
    private Audio bg_music = new Audio();

    /**
     * Méthode à redéfinir pour la boucle principale du jeu.
     */
    public void loop() {
        // Cette méthode sera redéfinie dans les sous-classes pour implémenter la boucle principale du jeu
    }

    /**
     * Démarre l'application JavaFX.
     * 
     * @param stage La scène principale de l'application.
     * @throws Exception Si une erreur se produit lors du démarrage de l'application.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Initialise la fenêtre principale
        Tstage = stage;

        // Définit la largeur de la fenêtre
        Tstage.setWidth(width);
        // Définit la hauteur de la fenêtre
        Tstage.setHeight(height);
        // Définit le titre de la fenêtre
        Tstage.setTitle("BreakOut!");

        // Crée la première page "Title Screen"
        currentPage = new TitleScreen();
        // Définit le KeyFrame pour la boucle principale du jeu
        kf = new KeyFrame(Duration.millis(10), e -> currentPage.loop());
        // Crée la Timeline pour gérer l'animation du jeu
        tl = new Timeline(kf);
        // Définit la Timeline pour s'exécuter indéfiniment
        tl.setCycleCount(Timeline.INDEFINITE);

        // Force la fenêtre à garder une taille fixe
        Tstage.setResizable(false);
        // Centre la fenêtre sur l'écran
        Tstage.centerOnScreen();

        // Démarre la musique de fond
        bg_music = new Audio();
        bg_music.load("Power_Punch_2050_Powerful.wav");
        if (!bg_music.isplaying()) {
            bg_music.play();
            bg_music.loop();
        }

        // Affiche la fenêtre principale
        Tstage.show();

        // Démarre la Timeline
        tl.play();
    }

    /**
     * Méthode principale pour lancer l'application.
     * 
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        // Lance l'application JavaFX
        launch(args);
    }

    /**
     * Initialise le contexte graphique pour les utilisations usuelles.
     * 
     * @param gc Le contexte graphique à initialiser.
     */
    protected static void init_gc(GraphicsContext gc) {
        // Définit la couleur de remplissage à noir
        gc.setFill(Color.BLACK);
        // Remplit le rectangle avec la couleur noire
        gc.fillRect(0, 0, width, height);
        // Définit la couleur de remplissage à blanc
        gc.setFill(Color.WHITE);
        // Définit la police de texte
        gc.setFont(Font.font(25));
        // Définit la couleur de remplissage à blanc (répétition)
        gc.setFill(Color.WHITE);
    }

    /**
     * Arrête la Timeline.
     */
    public static void stop_tl() {
        // Affiche un message indiquant le début de l'arrêt
        System.out.println("debut de l'arret");
        // Met en pause la Timeline
        tl.pause();
    }

    /**
     * Reprend la Timeline.
     */
    public static void continue_tl() {
        // Reprend la Timeline
        tl.play();
        // Affiche un message indiquant la fin de l'arrêt
        System.out.println("fin de l'arret");
    }

    /**
     * Met en pause la Timeline pendant une durée spécifiée.
     * 
     * @param duration La durée de la pause en secondes.
     */
    protected static void pause_tl(float duration) {
        // Crée une transition de pause avec la durée spécifiée
        PauseTransition pause = new PauseTransition(Duration.seconds(duration));

        // Définit l'action à exécuter à la fin de la pause
        pause.setOnFinished(event -> {
            // Reprend la Timeline
            tl.play();
            // Affiche un message indiquant la fin de la pause
            System.out.println("Fin de la pause de" + duration + " seconde");
        });

        // Affiche un message indiquant le début de la pause
        System.out.println("debut de la pause de" + duration + " seconde");
        // Met en pause la Timeline
        tl.pause();
        // Démarre la transition de pause
        pause.play();
    }

    public int[][] CreationTabAleatoire(int brickRows, int brickCols) {
        int [][] tab_alea = new int[brickRows][brickCols];
        for (int i = 0; i<brickRows; i++) {
            for (int j = 0; j<brickCols; j++) {
                tab_alea[i][j] = (int)(Math.random()*2);
            }
        }   
        return tab_alea;
   }


   public static void setTstage(Stage tstage) {
       Tstage = tstage;
   }
   

   public static int getHeight() {
       return height;
   }
   public static int getWidth() {
       return width;
   }
}
