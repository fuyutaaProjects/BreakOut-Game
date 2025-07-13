Manuel de création de niveaux dans BreakOut

Structure et convention de fichiers
Les niveaux doivent suivre la nomenclature : Niveau_X.java, où X est le numéro du niveau (exemple : Niveau_2.java pour le deuxième niveau).
Les fichiers sont placés dans le package : org.openjfx.Game_ressources.Objets.All_levels.


Classe de base
    Chaque niveau hérite de la classe Niveaux et doit implémenter la méthode init_board() pour définir le contenu du niveau (briques, bonus, etc.).

Exemple d’un niveau : Niveau_1
    Voici comment structurer et personnaliser un niveau basé sur l''exemple fourni :
        Structure principale

        public class Niveau_X extends Niveaux {
            @Override
            public void init_board() {
                // Configuration des briques et des bonus ici
            }
        }


Ajout de briques personnalisées
Pour ajouter des briques :

Briques rectangulaires :

    super.terrain.add(new Briques_rectangle(posX, posY, largeur, hauteur, resistance, score, bonus));

        posX, posY : Position de la brique.
        largeur, hauteur : Taille de la brique.
        resistance : Nombre de coups nécessaires pour détruire la brique.
        score : Points ajoutés lorsque la brique est détruite.
        bonus : Bonus qui tombe quand la brique est détruite (utilisez genereBonus pour les générer).

Briques personnalisées :
    double[][] points = {{x1, y1}, {x2, y2}, ...};
    super.terrain.add(new Briques_personnalise(points, resistance, score, bonus));
    points : Tableau des coordonnées du polygone qui forme la brique.
    resistance, score, bonus : Identiques à ceux des briques rectangulaires.