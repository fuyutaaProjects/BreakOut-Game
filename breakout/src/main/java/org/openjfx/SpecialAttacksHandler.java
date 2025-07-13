package org.openjfx;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import org.openjfx.Game_ressources.Vecteur;
import org.openjfx.Game_ressources.Objets.Balle;
import org.openjfx.Game_ressources.SpecialAttacks.Laser;
import org.openjfx.Game_ressources.SpecialAttacks.SpecialAttack;
import org.openjfx.Game_ressources.Objets.Board;


// Pour comprendre le fonctionnement de ce script et du reste des morceaux de codes concernant les special attacks, vous pouvez lire le manuel que j'ai rédigé.

public class SpecialAttacksHandler {

    private Integer attackOnDIGIT2; // L'index de l'attaque spéciale classique en question (exemple: Laser.java c'est 0. voir la liste de correspondances dans ID_For_Each_Attack.MD)
    private Integer attackOnDIGIT3; // L'index de l'attaque ultime en question

    public SpecialAttacksHandler() {
        this.attackOnDIGIT2 = null;
    }

    public void setSpecialAttackOnDIGIT2(Integer attackID) {
        this.attackOnDIGIT2 = attackID;
    }

    public void setSpecialAttackOnDIGIT3(Integer attackID) {
        this.attackOnDIGIT3 = attackID;
    }

    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.DIGIT2 && attackOnDIGIT2 != null) {
            // Attaques de type "attaque spéciale classique"

            if (attackOnDIGIT2 == 0) { // 0 est un laser
                //System.out.println("Current attack is a laser");
                // recup la position de la première balle dans la liste des balles à l'écran
                Balle balle = Board.getInstance().getListe_balle().get(0);
                Color color = Color.RED;
                
                Laser laser = new Laser(
                    balle.getPosX(),
                    balle.getPosY(),
                    new Vecteur(0, -5), // déplacement vers le haut
                    color,
                    25,
                    1
                );
    
                // Ajoute le laser à la liste des attaques spéciales dans Board
                //System.out.println("adding laser to liste_atk_speciales");
                Board.getInstance().liste_attaques_speciales.add(laser);
            }
        }

        if (event.getCode() == KeyCode.DIGIT3 && attackOnDIGIT3 != null) {
            // Attaques de type Ultime (des attaques puissantes)


        }
    }
}
