# C'est quoi ce fichier?
Les Special attacks actuellement équipées ne peuvent pas être un objet qu'on stockerait dans SpecialAttacksHandler. 
SpecialAttacksHandler crée l'objet de l'attaque en question (exemple: Laser.java qui est un projectile comme Balle) en fonction de la variable attackOnDIGIT2 ou attackOnDIGIT3.
On a donc le choix de comment stocker un "identifiant" de l'attaque en question: par exemple une string qui contient son nom (ex: "Laser"), ou bien une INT qu'on dira comme "veut dire Laser"

Etant donné qu'une int prendra clairement moins de temps à lire et c'est plus optimal, on va partir sur un dictionnaire de ints = "telle attaque spéciale".

# Le dictionnaire de correspondances (ID - attaque spéciale)
0 = Laser.java
Aucune autre attaque spéciale pour le moment...
