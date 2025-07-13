# Manuel de Création de Niveau

## Introduction
Ce manuel explique comment créer un niveau personnalisé pour le jeu casse-brique. Vous apprendrez à définir les briques, les bonus, et les configurations spécifiques pour chaque niveau.

## Étapes pour créer un niveau

### 1. Créer une nouvelle classe de niveau
Créez une nouvelle classe Java dans le package `org.openjfx.Game_ressources.Objets.All_levels`. Cette classe doit hériter de la classe `Niveaux`.

```java
package org.openjfx.Game_ressources.Objets.All_levels;

public class Niveau_X extends Niveaux {
    @Override
    public void init_board() {
        // Initialisation du plateau de jeu
    }
}
```

### 2. Initialiser le plateau de jeu
Dans la méthode `init_board()`, définissez les briques et leur position sur le plateau de jeu.

```java
@Override
public void init_board() {
    double largeur = BreakOut.getWidth() / 10;
    ligne(5, 0, 0, largeur);
    ligne(5, 0, 150, largeur);
    // Ajoutez d'autres lignes ou configurations de briques
}
```

### 3. Ajouter des lignes de briques
Utilisez une méthode pour ajouter des lignes de briques. Vous pouvez personnaliser le nombre de briques, leur position, leur taille, etc.

```java
public void ligne(int nb, double x, double y, double width) {
    for (int i = 0; i < nb; i++) {
        super.terrain.add(new Briques_rectangle(x, y, width, 20, 20, 2, genereBonus(x, y)));
        x += width + 2;
    }
}
```

### 4. Générer des bonus
Définissez une méthode pour générer des bonus à certaines positions.

```java
public Bonus genereBonus(double posX, double posY) {
    Random random = new Random();
    int numBonus = random.nextInt(101);
    if (numBonus >= 0 && numBonus <= 20) {
        return null;
    } else if (numBonus > 20 && numBonus < 50) {
        return new BonusAugLagRaq(posX, posY, 1, 20, 50);
    } else if (numBonus >= 50 && numBonus <= 80) {
        return new BonusPlusBalles(posX, posY, 1, 20);
    }
    return null;
}
```

### 5. Ajouter le niveau au jeu
Assurez-vous que votre nouveau niveau est pris en compte dans la classe `ModeNiveaux`. Modifiez la méthode `level_sup()` pour inclure votre nouveau niveau.

```java
public void level_sup() {
    try {
        if (niveauCourant.getTerrain().ConditionNivSup()) {
            if (this.numeroNiveau == this.niveauFinal) {
                return;
            } else {
                numeroNiveau += 1;
                String nomClasseSuivant = "org.openjfx.Game_ressources.Objets.All_levels.Niveau_" + this.numeroNiveau;
                Class<?> classeSuivante = Class.forName(nomClasseSuivant);
                this.niveauCourant = (Niveaux) classeSuivante.getDeclaredConstructor().newInstance();
                this.niveauCourant.init_board();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

## Conclusion
En suivant ces étapes, vous pouvez créer et ajouter de nouveaux niveaux personnalisés au jeu casse-brique. N'hésitez pas à expérimenter avec différentes configurations de briques et de bonus pour créer des niveaux uniques et intéressants.