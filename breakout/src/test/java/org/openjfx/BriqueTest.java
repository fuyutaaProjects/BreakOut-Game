/*package org.openjfx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openjfx.Game_ressources.Bonus_jeu.Bonus;
import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.SpecialAttacks.SpecialAttack;

import static org.junit.jupiter.api.Assertions.*;

class BriqueTest {

    private Brique brique;
    private Face[] faces;
    private Bonus bonusMock;

    @BeforeEach
    void setUp() {
        // Mock des faces de la brique
        faces = new Face[]{
            Mockito.mock(Face.class),
            Mockito.mock(Face.class)
        };

        // Mock du bonus
        bonusMock = Mockito.mock(Bonus.class);

        // Instanciation d'une classe anonyme pour la classe abstraite Brique
        brique = new Brique(100, faces, 3, bonusMock) {
            {
                this.poly = new Polygon();
            }
        };
    }

    @Test
    void testConstructeurAvecCouleur() {
        brique = new Brique(200, faces, 2, bonusMock, Color.BLUE) {
            {
                this.poly = new Polygon();
            }
        };
        assertEquals(Color.BLUE, brique.getCouleur());
        assertEquals(200, brique.getPoint_brique());
        assertEquals(2, brique.getResistance());
        assertTrue(brique.getEstVisible());
    }

    @Test
    void testIncrementerFissuresEtResistance() {
        assertEquals(3, brique.getResistance());

        // Incrémentation des fissures
        brique.incrementerFissures();
        assertEquals(2, brique.getResistance());
        assertEquals(1, brique.poly.getStyleClass().size());
        assertTrue(brique.poly.getStyleClass().contains("fissure-1"));

        // Incrémentation jusqu'à destruction
        brique.incrementerFissures();
        brique.incrementerFissures();
        assertEquals(0, brique.getResistance());
        assertFalse(brique.getEstVisible());
    }

    @Test
    void testCheckColB() {
        // Mock de la balle et des collisions
        Balle balle = Mockito.mock(Balle.class);
        Circle nextPos = new Circle(10, 10, 5);
        Mockito.when(balle.nextPos()).thenReturn(nextPos);

        Mockito.when(faces[0].checkCol(nextPos)).thenReturn(false);
        Mockito.when(faces[1].checkCol(nextPos)).thenReturn(true);

        Face touchedFace = brique.checkColB(balle);
        assertNotNull(touchedFace);
        assertEquals(faces[1], touchedFace);
    }

    @Test
    void testCheckColSpecialAttack() {
        SpecialAttack specialAttack = Mockito.mock(SpecialAttack.class);
        Mockito.when(specialAttack.getHitbox()).thenReturn(new Circle(15, 15, 5));

        // Mock de l'intersection
        Mockito.when(faces[0].getLigne()).thenReturn(new Line(10, 10, 20, 20));
        Mockito.when(Shape.intersect(faces[0].getLigne(), specialAttack.getHitbox()).getBoundsInLocal().getWidth()).thenReturn(5.0);

        Face touchedFace = brique.checkColSpecialAttack(specialAttack);
        assertNotNull(touchedFace);
        assertEquals(faces[0], touchedFace);
    }

    @Test
    void testHitBox() {
        Circle circle1 = new Circle(10, 10, 5);
        Circle circle2 = new Circle(20, 20, 5);

        Line[] lines = brique.hitBox(circle1, circle2, 4);

        assertEquals(4, lines.length);
        for (Line line : lines) {
            assertNotNull(line);
        }
    }

    @Test
    void testIntersection() {
        Line line1 = new Line(0, 0, 10, 10);
        Line line2 = new Line(0, 10, 10, 0);

        double[] intersection = Brique.Intersection(line1, line2);
        assertNotNull(intersection);
        assertEquals(5.0, intersection[0], 0.001);
        assertEquals(5.0, intersection[1], 0.001);
    }

    @Test
    void testDetermineColor() {
        assertEquals(Color.YELLOW, Brique.determineColor(1));
        assertEquals(Color.RED, Brique.determineColor(5));
        assertEquals(Color.GREY, Brique.determineColor(-1));
    }
}
*/
