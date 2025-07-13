package org.openjfx.Game_ressources.Objets.All_levels;
import java.util.Random;

import org.openjfx.BreakOut;

import org.openjfx.Game_ressources.Bonus_jeu.*;
import org.openjfx.Game_ressources.Objets.Briques.*;

import javafx.scene.paint.Color;



/**
 * Classe représentant le niveau 1 du jeu.
 */
public class Niveau_2 extends Niveaux {
    /**
     * Initialise le plateau de jeu pour le niveau 1.
     */
    @Override
    public void init_board() {
        double largeur=BreakOut.getWidth()/9;
        ligne(4,0,0,largeur);
        ligne(4,0,150,largeur);
        ligne(4,(largeur+2)*+4+92,0,largeur);
        ligne(4,(largeur+2)*4+92,150,largeur);

        super.terrain.add(new Briques_rectangle(46, 50, largeur, 20, 10,1,genereBonus(46, 50)));
        super.terrain.add(new Briques_rectangle(46, 100, largeur, 20, 10,1,genereBonus(46, 100)));
        
        
        super.terrain.add(new Briques_rectangle((largeur+2)*3+46, 50, largeur, 20, 10,1,genereBonus((largeur+2)*3+46, 50)));
        super.terrain.add(new Briques_rectangle((largeur+2)*3+46, 100, largeur, 20, 10,1,genereBonus((largeur+2)*3+46, 100)));
        super.terrain.add(new Briques_rectangle((largeur+2)*4+46, 50, largeur, 20, 10,1,genereBonus((largeur+2)*4+46, 50)));
        super.terrain.add(new Briques_rectangle((largeur+2)*4+46, 100, largeur, 20, 10,1,genereBonus((largeur+2)*4+46, 100)));

        super.terrain.add(new Briques_rectangle((largeur+2)*7+46, 50, largeur, 20, 10,1,genereBonus((largeur+2)*7+46, 50)));
        super.terrain.add(new Briques_rectangle((largeur+2)*7+46, 100, largeur, 20, 10,-1,genereBonus((largeur+2)*7+46, 100)));

        double [][] p1= {{170,30},{190,50},{210,90},{300,90},{360,120}};
        double [][] p2= {{679,30},{699,50},{719,90},{809,90},{869,120}};
        super.terrain.add(new Briques_personnalise(p1,40,3,genereBonus(p1[1][0], p1[1][1])));
        super.terrain.add(new Briques_personnalise(p2,40,3,genereBonus(p2[1][0], p2[1][1])));

    }

    public void ligne(int nb,double x,double y,double width){
        for (int i=0;i<nb;i++){
            super.terrain.add(new Briques_rectangle(x, y, width, 20, 10,1,genereBonus(x, y)));
            x+=width+2;
        }
    }

    /**
     * Fonctione permettant de génerer un bonus avec certains poucentage de chance et en le plaçant aux coordonnées données.
     * 
     * @param posX Position x du bonus.
     * @param posY Position y du bonus.
     * 
     * @return null si un nombre est entre 0 et 30 ou sinon un nombre dans un intervalle non défini pour un bonus sinon retourne un bonus choisit.
     */
    public Bonus genereBonus(double posX, double posY){
        //Cas permettant de génerer des coordonnées fausses et en dehors de l'écran.
        if (posX < 0 || posX > BreakOut.getWidth() || posY < 0 || posY > BreakOut.getHeight()) {
            throw new IllegalArgumentException("Coordonnées en dehors de l'écran");
        }else{
            Random random = new Random();
            int numBonus = random.nextInt(101); // Nombre entre 0 (inclus) et 101 (exclus).
            // 30% de chance de rien avoir.
            if (numBonus >= 0 && numBonus <= 30) {
                return new BonusAugLagRaq(posX,posY,1,20,30);
            }
            // 30% de chance d'avoir un bonus qui augmente la largeur de la raquette.
            else if (numBonus > 30 && numBonus < 60 ) {
                return new BonusAugLagRaq(posX,posY,1,20,50);
            }
            else if (numBonus > 60 && numBonus < 90 ) {
                return new BonusPlusBalles(posX,posY,1,20);
            }
            // Cas quand rien ne remplis une condition et du coup renvoit aucun bonus.
            return null;
        }
    }
}