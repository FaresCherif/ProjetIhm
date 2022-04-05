Fares Cherif
Fonteneau Thomas

Ce projet est un test de personalite de personalite afin de savoir quelle genre d'amoureu vous etes.
Il est compose de 10 layout ( +2 landscape) avec plusieurs type de widget comme des radio button, des sliders, des switch, des champs de text et des images bouttons.
Les informations sont transmise d'une activité a l'autre a l'aide de Bundle que des Intent envoie d'une activité a l'autre.

Les informations transmise ont été synthétisé dans une classe User composant de plusieurs champs dont notamment 2 compteurs qui vont s'incrémenter selon les reponses. 
Il possede egalement un arrayListe qui sert à capter les evenement speciaux ( pour notre code "rich" si le somme maximum en banque est selectionnée)

Les informations sont enregistré dans plusieurs fichiers :
	- "success.txt" qui va enregistrer la liste des succes débloquer avec les nom et prenom du detenteur ( cela sera appeler dans l'activitée success)
	- nom-prenom.txt qui va enregistrer toute les infos de l'utilisateur 
	- sharedPreferences qui va enregistrer le nombre de succès validés

On utilise onSavedInstance et onRestoreInstance pour pouvoir garder le fais que le des a ete lancer meme lorsque l'écran est tourné.

Il y a des musiques qui sont lancés au choix du personnage à l'activité 4