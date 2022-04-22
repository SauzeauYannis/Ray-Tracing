Pour compiler les sources, vous pouvez utiliser le makefile et exécuter la commande 'make'.

Pour lancer le programme, vous pouvez lancer la commande 'java raytracing.Main'.

Plusieurs arguments peuvent être ajoutés 'java raytracing.Main <sceneNumber> <maxDepth> <width> <height>' :

    - <sceneNumber> (par défaut 1) est le numéro de la scène, trois numéro sont disponible et représentent 3 scènes différentes :
        - la scène numéro 1 reprend la disposition de la scène faite avec OpenGL
        - la scène numéro 2 montre l'effet de la réflexion
        - la scène numéro 3 montre l'effet de la réfraction

    - <maxDepth> (par défaut 5) est la profondeur de la récursivité de l'algorithme

    - <width> (par défaut 1280) et <height> (par défaut 720) sont les dimensions de l'image obtenue

Une fois le programme terminé, vous pouvez voir l'image obtenue dans le dossier 'output'
