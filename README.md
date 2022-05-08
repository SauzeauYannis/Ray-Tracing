##### Table of Contents
* [Français](#fr)
  * [Présentation](#fr_pr)
  * [Utilisation](#fr_ut)
  * [Compétences acquises](#fr_cp)
  * [Résultat](#fr_rs)
* [English](#en)
  * [Presentation](#en_pr)
  * [Use](#en_u)
  * [Skills acquired](#en_sk)
  * [Result](#en_rs)

<a name="fr"/>

## Français

<a name="fr_pr"/>

### Présentation

Ce projet a été effectué en quatrième année du [CMI Informatique](http://formations.univ-poitiers.fr/fr/index/autre-diplome-niveau-master-AM/autre-diplome-niveau-master-AM/cmi-informatique-JD2XQGVY.html) à l'[UFR SFA Université de Poitiers](https://sfa.univ-poitiers.fr/) dans le cadre de l'enseignement [Algorithmique 3D](https://formations.univ-poitiers.fr/fr/index/autre-diplome-niveau-master-AM/autre-diplome-niveau-master-AM/cmi-informatique-JD2XQGVY/cmi-parcours-conception-logicielle-K56KZ5KL/specialite-s8-K5C80CME/algorithmique-3d-1-KZQUZCJD.html).

Ce projet a été développé en Java avec [Visual Studio Code](https://code.visualstudio.com/).

<a name="fr_ut"/>

### Utilisation

Pour lancer le projet, il faut d'abord compiler les sources grâce au [makefile](https://github.com/SauzeauYannis/Ray-Tracing/blob/main/src/makefile).

Pour lancer le programme, vous pouvez lancer la commande `java raytracing.Main`.

Plusieurs arguments peuvent être ajoutés `java raytracing.Main <sceneNumber> <maxDepth> <width> <height>` :

    - <sceneNumber> (par défaut 1) est le numéro de la scène, trois numéro sont disponible et représentent 3 scènes différentes :
        - la scène numéro 1 reprend la disposition de la scène faite avec OpenGL
        - la scène numéro 2 montre l'effet de la réflexion
        - la scène numéro 3 montre l'effet de la réfraction

    - <maxDepth> (par défaut 5) est la profondeur de la récursivité de l'algorithme

    - <width> (par défaut 1280) et <height> (par défaut 720) sont les dimensions de l'image obtenue

Une fois le programme terminé, vous pouvez voir l'image obtenue dans le dossier 'output'

<a name="fr_cp"/>

### Compétences acquises

* Sélectionner des modèles de représentation appropriés et les organiser afin de modéliser un objet virtuel
* Mettre en œuvre des modèles permettant de doter les objets d'une apparence visuelle
* Afficher une scène (projection à l'écran, gestion des parties cachées)
* Effectuer des calculs d'éclairement dans la scène, à partir des lois physiques de la lumière
* Gérer les phénomènes de réflexion et de réfraction

<a name="fr_rs"/>

### Résultat

J'ai obtenu la note de ?/20. (Résultat en juin)

<a name="en"/>

## English

<a name="en_pr"/>

### Presentation

This project was carried out in the fourth year of the [CMI Informatique](http://formations.univ-poitiers.fr/fr/index/autre-diplome-niveau-master-AM/autre-diplome-niveau-master-AM/cmi-informatique-JD2XQGVY.html) at the [University of Poitiers](https://www.univ-poitiers.fr/en/) as part of the [3D Algorithms](http://formations.univ-poitiers.fr/fr/index/autre-diplome-niveau-master-AM/autre-diplome-niveau-master-AM/cmi-informatique-JD2XQGVY/specialite-s5-JD2XSMB7/algorithmique-et-programmation-3-JB1YGKR9.html) teaching programme.

This project was developed in Java with [Visual Studio Code](https://code.visualstudio.com/).

<a name="en_u"/>

### Use

To launch the project, you must first compile the sources with the [makefile](https://github.com/SauzeauYannis/Ray-Tracing/blob/main/src/makefile).

To launch the program, you can run the command `java raytracing.Main`.

Several arguments can be added `java raytracing.Main <sceneNumber> <maxDepth> <width> <height>`:

    - <sceneNumber> (default 1) is the scene number, three numbers are available and represent 3 different scenes:
        - scene number 1 shows the layout of the scene made with OpenGL
        - scene number 2 shows the effect of the reflection
        - scene number 3 shows the effect of refraction

    - <maxDepth> (default 5) is the depth of the recursion of the algorithm

    - <width> (default 1280) and <height> (default 720) are the dimensions of the resulting image

Once the program is finished, you can see the obtained image in the 'output' folder

<a name="en_sk"/>

### Skills acquired

* Select appropriate representation models and organize them to model a virtual object
* Implement models to give objects a visual appearance
* Display a scene (screen projection, management of hidden parts)
* Perform illumination calculations in the scene, based on the physical laws of light
* Manage the phenomena of reflection and refraction
  
<a name="en_rs"/>

### Result

I obtained a grade of ?/20. (Result in June)
