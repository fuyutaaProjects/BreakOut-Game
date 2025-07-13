// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.openjfx.Game_ressources.ModeNiveaux;
// import org.openjfx.Game_ressources.Objets.All_levels.Niveau_Endless;
// import org.openjfx.Game_ressources.Objets.Board;
// import org.openjfx.Game_ressources.Bonus_jeu.Bonus;
// import org.openjfx.Game_ressources.Objets.Briques.Briques_rectangle;

// import static org.junit.jupiter.api.Assertions.*;

// public class EndlessTest {

//     private ModeNiveaux modeNiveaux;
//     private Niveau_Endless niveauEndless;

//     @BeforeEach
//     public void setUp() {
//         modeNiveaux = new ModeNiveaux();
//         niveauEndless = new Niveau_Endless();
//         niveauEndless.init_board();
//     }

//     @Test
//     public void testInitialisationNiveauEndless() {
//         Board board = niveauEndless.getTerrain();
//         assertNotNull(board, "Le plateau de jeu ne doit pas être null après l'initialisation.");
//         assertFalse(board.getBriques().isEmpty(), "Le plateau de jeu doit contenir des briques après l'initialisation.");
//     }

//     @Test
//     public void testPassageNiveauSuperieur() {
//         modeNiveaux.Niv_sup_Endless(new Pane());
//         Board board = modeNiveaux.getBoard();
//         assertNotNull(board, "Le plateau de jeu ne doit pas être null après le passage au niveau supérieur.");
//         assertFalse(board.getBriques().isEmpty(), "Le plateau de jeu doit contenir des briques après le passage au niveau supérieur.");
//     }

//     @Test
//     public void testGenerationBonus() {
//         double posX = 100;
//         double posY = 100;
//         Bonus bonus = niveauEndless.genereBonus(posX, posY);
//         assertNotNull(bonus, "Un bonus doit être généré.");
//         assertTrue(bonus.getPosX() == posX && bonus.getPosY() == posY, "Le bonus doit être généré aux coordonnées spécifiées.");
//     }

//     @Test
//     public void testGenerationBriquesRectangulaires() {
//         Board board = niveauEndless.getTerrain();
//         boolean containsRectangularBricks = board.getBriques().stream().anyMatch(brique -> brique instanceof Briques_rectangle);
//         assertTrue(containsRectangularBricks, "Le plateau de jeu doit contenir des briques rectangulaires.");
//     }

//     @Test
//     public void testGenerationBriquesPersonnalisees() {
//         Board board = niveauEndless.getTerrain();
//         boolean containsCustomBricks = board.getBriques().stream().anyMatch(brique -> brique instanceof Briques_personnalise);
//         assertTrue(containsCustomBricks, "Le plateau de jeu doit contenir des briques personnalisées.");
//     }
// }