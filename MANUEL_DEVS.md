# Fonctionnement de BreakOut.java
Le fonctionnement expliqué: les nouvelles classes, les méthodes du script.

# Les Classes utilisées

## A. La partie visuelle

1. Le stage
C'est la fenetre du jeu, de l'application JavaFX, et javaFX c'est grossièrement un outil permettant de faire un affichage java. Dans le code, on initialise notre fenêtre dans la méthode start:
```
stage.setTitle("Breakout");
stage.setScene(new Scene(new StackPane(canvas)));
stage.show();
```

2. La scene, c'est le conteneur de toutes les choses qu'on va afficher à l'écran. On la donnera à un stage pour l'afficher.
```
stage.setScene(new Scene(new StackPane(canvas)));
```
Vous comprendrez donc que l'on peut donner d'une seule scène à la fois à un stage. On ne peut pas empiler des scenes.

3. StackPane
En parlant d'empiler, c'est le role du stackpane! Il prend un nombre illimité d'arguments, et superpose ces arguments ensembles.
Pour ce qui est de la position d'objets empilés, c'est pas le stackpane qui gère ça. on va utiliser des variables.

4. Canvas
Le canvas est une zone de dessin, sur laquelle on peut dessiner n'importe quoi. Il porte son nom, c'est comme un tableau. On va dessienr dessus des formes (raquette, balle, briques), du texte (Click To Start)... 
Par contre le Canvas ne peut pas contenir des éléments d'interface comme des boutons, ce sont des éléments qui ne se dessinent pas.
Création du canvas dans le code:
```
Canvas canvas = new Canvas(width, height);
```

5. GraphicsContext
Pour dessiner sur un canvas, on utilise un graphicscontext. ici vu qu'on est en 2d, c'est ```GraphicsContext2D```
```
GraphicsContext gc = canvas.getGraphicsContext2D();
```
Exemples de dessin:
La balle:
```
gc.fillOval(ballXPos, ballYPos, ballSize, ballSize);
```
Les briques:
```
for (int i = 0; i < brickRows; i++) {
  for (int j = 0; j < brickCols; j++) {
      if (bricks[i][j]) {
          double brickX = j * (brickWidth + 5);
          double brickY = i * (brickHeight + 5);
          gc.setFill(Color.BLUE);
          gc.fillRect(brickX, brickY, brickWidth, brickHeight);
      }
  }
}
```

## B. La partie exécution

1. KeyFrame
Définit une période de temps.

2. La timeline
La timeline, c'est un peu le "temps" dans notre jeu. C'est une classe à qui on passe une durée en KeyFrame.
```
Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
tl.setCycleCount(Timeline.INDEFINITE);
```
Notre jeu va alors toutes les 10 millisecondes appeler run(gc), qui va réactualiser l'écran (position des objets, si une brique a été détruite elle disparait...)


On en résumé démonstratif, dans l'état actuel de notre projet la création du stage se fait de cette manière:
1. Créer un canvas
```
Canvas canvas = new Canvas(width, height);
```

2.  Initialiser un GraphicsContext2D:
```
GraphicsContext gc = canvas.getGraphicsContext2D();
```

3. Assembler le tout dans un StackPane, Donner le StackPane à une nouvelle Scène et donner cette Scene à notre stage
```
stage.setScene(new Scene(new StackPane(canvas)));
```

4. Afficher la stage:
```
stage.show();
```

5. Définir une timeline pour le jeu
```
Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
tl.setCycleCount(Timeline.INDEFINITE);
```


5. Lancer le jeu:
```
tl.play();
```

# Les méthodes du projet
1. start(Stage stage)
Crée le canvas, graphicscontext, timeline.
Surveille les clicks de souris pour lancer le jeu quand on a pas lancé / déplacer la raquette
Config le stage, et l'affiche puis active la timeline (lancement du jeu)

2. run(...)
met à jour l'affichage, les variables du jeu, handle les "events" en fonction de ce qui se passe dans le jeu.

3. main
méthode d'entrée, elle lance le jeu (call launch(args))

4. launch
la méthode qui lance l'app JavaFX (ouvre l'affichage quoi)