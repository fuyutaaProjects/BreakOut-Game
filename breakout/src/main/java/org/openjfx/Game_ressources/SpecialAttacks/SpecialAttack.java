package org.openjfx.Game_ressources.SpecialAttacks;

import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

public interface SpecialAttack {
    // getHitbox car on va générer une hitbox selon la forme de la specialAttack (elles ne sont pas toutes de la même forme). On récupèrera sa hitbox pour ensuite
    // récupérer la face, et checker la collision avec la brique.
    Shape getHitbox(); 
    Color getCouleur();

    void mettreAJour();
    void dessiner();
    void takeResistanceDamage(Integer damage);
}
