Pour créer un menu, vous pouvez vous baser sur l'un des menus déjà existants: TitleScreen, MenuShop, PageScore. 
Je recommande MenuShop pour un menu de complexité moyenne, ou PageScore pour un menu simple. Pour un menus avec beaucoup de buttons, essayer TitleScreen.

La structure d'un menu:

Structure de la classe MenuShop:
Une VBox principale est déclarée comme "root". Il s'agit d'une boite verticale dans laquelle on va passer des objets pour qu'ils soient empilés automatiquement.

Exemple avec MenuShop:

VBox root contient:
    - le titre "Shop!" en text
    - un texte pour afficher le nombre de pièces possédées
    - une hbox, une boite horizontale, qui contient les boutons pour acheter les items
    - un bouton return pour retourner au menu principal

Les boutons sont simples à comprendre, pas besoin d'explication.