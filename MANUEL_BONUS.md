#Ce fichier permet d'apprendre comment rajouter un bonus quelconque au projet.

Pour rajouter un bonus cela ce fait en 2 étapes:

1. Créer une classe appartenant au bonus qu'on souhaite rajouter, cette classe doit être concrète et hériter de la classe abstraite Bonus.java. Le bonus doit avoir une couleur si possible différente de celle déjà existante dans les autres bonus. La classe abstraite contient 2 fonctions activebonus avec des paramètres différents cela permet de différencier quand un bonus doit changer la balle ou la raquette par exemple BonusAugLagRaq.java est un bonus qui change la largeur de la raquette donc seulement la fonction prenant en paramètre une raquette est écrite, l'autre reste vide. La fonction dessiner doit être réécrite à chaque fois en fonction de si on souhaite faire quelque chose de différent qu'un simple cercle.

2. Une fois la classe du bonus écrite on doit modifier la fonction determineBonus dans la classe BriqueBonus.java afin de rajouter un nouveau cas au switch. En plus de cette modification le constructeur de la classe brique doit être changé c'est à dire que la ligne this.bonus = determineBonus(int) doit avoir en paramètre un entier obtenu aléatoirement entre 0 et le nombre de bonus existant.
