# C'est quoi les Special Attacks?
Ce sont des attaques qui peuvent être enclenchées par un appui de touche. 
On considère la balle comme une attaque "normale", les special attacks sont tout moyen différent de détruire une brique: un laser par exemple.

# Scripts utilisés par les Special Attacks
- Board.java : ArrayList<SpecialAttack> liste_attaques_speciales.
- Run.java: scene.setOnKeyPressed(event -> specialAttacksHandler.handle(event));

- SpecialAttacksHandler.java : gère les entrées de touche (enclenchement d'une attaque), et permets d'équiper une attaque.
- SpecialAttack.java : c'est une interface, qui définit les fonctions qu'une special attack doit inclure, et permet de créer le paramètre de type "SpecialAttack" pour les fonctions.
- Projectile.java : la classe fondamentale utilisée par une attaque spéciale. Elles sont en général un extends de Projectile, et implements SpecialAttack.

Exemple de script special attack:
- Laser.java

# Fonctionnement des Special Attacks.
1. Détection et process d'inputs
Une Special Attack est enclenchée via une touche de clavier (é ou "). Ces touches sont DIGIT2 et DIGIT3 dans le code.
Pour détecter et process ces inputs, on déclare un SpecialAttacksHandler dans Run.java. 
```java
private SpecialAttacksHandler specialAttacksHandler = new SpecialAttacksHandler();
```
Dans init_scene toujours dans Run.java, on va alors déclarer que pour chaque touche pressée, on demande à specialAttacksHandler de la process:
```java
scene.setOnKeyPressed(event -> specialAttacksHandler.handle(event));
```
Alors, chaque touche sera lue par specialAttacksHandler et il vérifiera si cette touche est l'une des touches DIGIT2 ou DIGIT3 (é ou ").


2. Traitement et enclenchement de l'attaque
Je vais expliquer le handle de SpecialAttacksHandler.java. Le voici pour que vous faciliter la compréhension:
```java
public void handle(KeyEvent event) {
    if (event.getCode() == KeyCode.DIGIT2 && attackOnDIGIT2 != null) {
        if (attackOnDIGIT2 == 0) {
            //System.out.println("Current special attack on digit 1 is a laser");
            // ...
            
            Laser laser = new Laser(
                balle.getPosX(),
                balle.getPosY(),
                new Vecteur(0, -5), // déplacement vers le haut
                color,
                25
            );

            // Ajoute le laser à la liste des attaques spéciales dans Board
            Board.getInstance().liste_attaques_speciales.add(laser);
        }
    }
    if (event.getCode() == KeyCode.DIGIT3 && attackOnDIGIT3 != null) {
        // ...
    }
}
```
La variable attackOnDIGIT2, c'est la variable de l'attaque spéciale actuellement équipée. Et attackOnDIGIT3, celle de l'attaque ultime, l'ultimate.
Quand on équipe une attaque, il suffit de faire:
```java
SpecialAttacksHandler.setSpecialAttackOnDIGIT2(ID_de_l_attaque)
```
Dans notre handle on va donc checker la valeur de la variable ID_de_l_attaque (de type int) et voir si elle correspond à une attaque qu'on a exprimé dans le fichier ID_For_Each_Attack.MD 
(il y a des relations de correspondance ou "telle int veut dire telle attaque"). Des notes additionnelles a ce sujet dans dans le fichier des IDs.

Si cette int correspond à une attaque existante (ce qui est censé être le cas normalement), on execute l'attaque en question.
On initialise alors un nouvel objet correspondant a cette attaque (dans notre cas Laser), et on l'ajoute à la liste_des_attaques_speciales dans la board.

Cette attaque spéciale qui est donc un projectile, sera alors traitée depuis la fonction FrameBoard10ms dans Board:
```java
    public void FrameBoard10ms() {
        // ...

        // Les SpecialAttacks sont des objets permettant de détruire des briques comme la balle: par exemple, on a le Laser. 
        //La balle n'est pas une specialattack évidemment.
        for (SpecialAttack specialAttack : liste_attaques_speciales) {
            specialAttack.mettreAJour();
            specialAttack.dessiner();
            physiquesSpecialAttack(specialAttack);
        }

        // ...
    }
```
Vous pouvez alors creuser à partir d'ici et suivre les calls de fonction pour comprendre comment cela fonctionne. Je vous ai fait une image qui contient visuellement toutes les fonctions utilisées pour le 
fonctionnement des attaques spéciales, que vous pouvez trouver dans ce fichier.
Il y a juste un edit pas présent sur l'image: la résistance, qui est juste une variable dans notre attaque spéciale. la diminution de la resistance et la suppression du laser par conséquent, sont faits dans la fonction takeResistanceDamage.

### Attention: pour le moment, on utilise liste_attaques_speciales, une liste séparée de celle des balles. mais il se peut que dans le futur on utilise simplement un for "projectile". J'attends de pouvoir ça avec le reste de l'équipe quand ils auront fini de revoir la physique et qu'on pourra fusionner certaines parties de code. Pour le moment j'utilise comme "une copie du système de physiques qui prend de base Balle mais cette fois prend SpecialAttack en paramètre", car Wassime veut revoir la physique et donc je ne peux pas vraiment y toucher.
