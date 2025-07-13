La page Dev_niveau permet de créer des polygones personnalisés en cliquant sur la scène et d'exporter leurs coordonnées pour les intégrer à votre jeu BreakOut.

Comment y accéder ?
Depuis TitleScreen, appuyez simultanément sur Alt + N.

Fonctionnalités principales

Créer un polygone :

    Cliquez sur l'écran pour ajouter des points au polygone actuelle.
    Chaque clic ajoute un nouveau point (X, Y), et le polygone est mis à jour en temps réel.

Exporter les coordonnées :

    Les points des polygones sont affichés dans la console, accompagnés d’une ligne préformatée pour les intégrer au jeu :
    super.terrain.add(new Briques_personnalise(points, 40, 3, null));
    vous n'avez plus cas copié coller dans le code de votre fichier Niveau.

Comment utiliser ?

    Lancez la page via Alt + N.
    Cliquez pour définir les points de votre polygone actuelle .
    Copiez le code généré dans la console pour l'utiliser dans votre configuration de niveaux.

Differantes commandes a connaitre :

    appuyer sur N crée un nouveau polygone.
    appuyer sur B retourne 1 action en arrière.
    appuyer sur R supprime le dernier polygone ajouter.
