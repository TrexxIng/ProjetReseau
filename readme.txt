Projet Réseau :  analyseur d'une trame ethernet

  1) arborescence (uniquement les dossiers)
   
├── bin
├── data
├── src
│   ├── analyseur
│   ├── champs
│   │   ├── adresseEtPort
│   │   ├── informations
│   │   ├── longueur
│   │   ├── nbQuestRep
│   │   ├── options
│   │   ├── sectionDNS
│   │   └── trameSuiv
│   ├── exceptions
│   ├── flags
│   │   ├── flagsDNS
│   │   ├── flagsIP
│   │   └── flagsTCP
│   └── segment
│       └── subsegment
└── trames

bin: où se trouve les fichiers compilés (l'arborescence est la même que src après le make
data: où se trouve des fichiers pour tester l'analyseur
trames: où seront placer les fichiers en sortie (les résultats)
src: dossier où se trouve le code



  2) analyseur
  
analyseur/
├── Analyseur.java
├── Tests.java
├── TraitementFichier.java
└── Trame.java 

  Dans l'analyseur se trouve 4 fichiers:
    - Analyseur.java est le fichier qui permet de lancer l'analyseur (voir howto.txt)
    - Tests.java est un fichier permettant de tester des trames et ne donne pas de fichiers en sortie, juste un affichage sur le terminal, 
        il faut modifier directement le fichier afin de changer les trames testées 
    - TraitementFichier.java est une classe n'ayant que des methodes static, elle contient la lecture du fichier d'entrée qui nous permet d'obtenir
        des listes de listes d'octets (une liste d'octets pour chaque trame) ainsi qu'une methode pour ecrire le/les fichiers de sortie
    - Trame.java  permet de calculer une trame à partir d'une liste d'octets
  
  Si on veut augmenter le nombre de port,protocole,... que l'analyseur puisse identifier, le seul fichier à modifier sera Trame.java



  3) 

    
