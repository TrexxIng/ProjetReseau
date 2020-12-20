Projet Réseau :  analyseur d'une trame ethernet

Sorbonne Université

Réalisé par 
  Boris Chan Yip Hon, 3706553
  Ingrid Theret, 3201608
Groupe 5


_____________________________________________________________________


Sommaire:
 1) arborescence
 2) analyseur
 3) segments
 4) champs
 5) flags
 6) exceptions
 7) Ajouter un nouveau protocole
 8) Format du fichier d'entrée
 9) liste des protocoles et options reconnu par l'analyseur
 10) Notes

_____________________________________________________________________


  1) arborescence (uniquement les dossiers)
  
Analyseur/  
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


_____________________________________________________________________


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
  

_____________________________________________________________________


  3) segments
  
segment/
├── ARP.java
├── DataDump.java
├── DNS.java
├── Ethernet.java
├── HTTP.java
├── ICMP.java
├── InternetProtocol.java
├── ITrame.java
├── subsegment
│   ├── AllOptions.java
│   ├── HeaderDatagramIP.java
│   ├── HeaderTCP.java
│   ├── Options.java
│   └── Padding.java
├── TCP.java
└── UDP.java

  Dans segment, se trouve toutes les couches de la trame Ethernet:
    - toutes les classes de ce dossier utilise l'interface ITrame
    - la plupart des segments (ou protocoles) se decomposent de la manière suivante: 
          --en entrée, on a une liste d'octets qui comprend le protocole + ses données (qui contient les protocoles suivant ou les data) 
          --une liste de champs (IChamps) est calculé grace à la liste d'octets, on dépile les octets permettant le calcul des champs
            et à la fin, on a une liste d'octets correspond aux données
          --un des IChamps permet de determiner le protocole suivant
          --en sortie, on renvoie la liste d'octets de données et le paramètre déterminant le protocole suivant
    - le dossier subsegment/ est particulier, on peut diviser IP et TCP en deux: 
          -- une entête de longueur fixe : HeaderTCP.java et HeaderDatagram.java dont on extraira la longueur des options
          -- des options de longueur variables, on utilisera AllOptions pour faire une liste d'Options afin de distinguer les différentes options
          -- TCP.java et InternetProtocol.java seront composés une classe Header_ et une classe AllOptions
    - DataDump.java permet de compresser les octets en un seul String pour l'affichage, on utilisera ceci lorsque le protocole suivant est inconnu
    
  Parmi les fonctions communes, les plus importantes sont:
    - nextSegment() qui nous donne un String correspondant au protocole suivant, il donnera "Rien" s'il n'y a rien et "Data" s'il est inconnu
    - formatDisplay(int tab) pour l'affichage avec tabulation
    - errorDetect() pour la detection d'erreur interrompant la traduction de la trame
      
    
_____________________________________________________________________


  4) champs
  
champs/
├── adresseEtPort
│   ├── AdresseIP.java
│   ├── AdresseMAC.java
│   ├── AdresseMask.java
│   └── Port.java
├── Checksum.java
├── Data.java
├── Flags.java
├── IChamps.java
├── informations
│   ├── AckSeqNumber.java
│   ├── CodeICMP.java
│   ├── Hardware.java
│   ├── Horodatage.java
│   ├── Identification.java
│   ├── NextHopMTU.java
│   ├── Operation.java
│   ├── TimeToLive.java
│   ├── TOS.java
│   ├── UrgPointeur.java
│   ├── VersionIP.java
│   └── Windows.java
├── longueur
│   ├── Length1Bytes.java
│   ├── LengthQuartet.java
│   ├── LengthUDP.java
│   └── TotalLength.java
├── nbQuestRep
│   ├── NbAdditional.java
│   ├── NbAuthority.java
│   ├── NbQuestions.java
│   └── NbReponsesRR.java
├── options
│   ├── Bourrage.java
│   ├── PointeurOption.java
│   └── TypeOptions.java
├── sectionDNS
│   ├── AllRequeteDNS.java
│   ├── OtherDNS.java
│   ├── Reponse.java
│   └── Requete.java
├── TraductionHTTP.java
├── trameSuiv
│   ├── Protocol.java
│   ├── TypeEther.java
│   └── TypeICMP.java
└── Vide.java

    On peut découper un protocole en plusieurs champs:
      - toutes les classes utilisent l'interface IChamp
      - certains champs auront une fonction permettant d'obtenir le protocole suivant (qui sont Port.java et les classes dans trameSuiv/)
      - les champs permettant d'obtenir les valeurs des longueurs (IHL, Total Length...) sont dans longueur/ 
      - les champs spécifiques aux options sont dans options/ , spécifiques à DNS dans sectionDNS/ et nbQuestRep/
      - informations sont les champs ne demandant qu'un affichage et n'influençant pas le reste de la section (TTL,TOS,...) 
      - adresseEtPort/ pour le port, les addresses IP, MAC et le masque
      - Data.java est inclassable car utilisable par beaucoup de protocoles
      - Checksum a été mis à part pour permettre le calcul du checksum et faire des vérifications (pas encore implémenté)
      
     Flags.java est particulier car il n'agit pas comme les autres:
      - il nécessite de regarder bit à bit au lieu de regarder les octets/quartets
      - il s'agit donc d'une classe contenant une liste de flags (IFlags)
      - il a une fonction permettant de convertir les octets en bits en faisant attention à la taille
            0x11 ---Integer.parseInt--> 0b10001 --ajoutZero--> 0b00010001
     
    Parmi les fonctions communes, les plus importantes sont:
      - formatDisplay(int tab) pour l'affichage avec tabulation
     
     
 _____________________________________________________________________


   5) Flags
 
flags/
├── flagsDNS
│   ├── AA.java
│   ├── Check.java
│   ├── Opcode.java
│   ├── QR.java
│   ├── RA.java
│   ├── RCode.java
│   ├── RD.java
│   ├── ReponseAuthent.java
│   └── TC.java
├── flagsIP
│   ├── DF.java
│   ├── FragOffset.java
│   └── MF.java
├── flagsTCP
│   ├── ACK.java
│   ├── FIN.java
│   ├── PSH.java
│   ├── RST.java
│   ├── SYN.java
│   ├── URG2.java
│   └── URG.java
├── IFlags.java
└── Reserved.java

 
   Comme dit en 4), les flags permettent de faire un traitement bit par bit:
     - toutes les classes utilisent l'interface IFlags
     - seul les protocoles TCP, IP et DNS ont un champs Flags
     - le seul flags en commun est Reserved dont les bits doivent être à zéro
     - toutes les autres classes sont spécifiques à un protocole et ont été séparés par dossier
  

_____________________________________________________________________


  6) Exceptions
  
exceptions/
├── ExceptionFormat.java
├── ExceptionIncoherence.java
├── ExceptionSupport.java
└── ExceptionTaille.java

  
   On utilise 4 exceptions afin de connaitre les erreurs dans le programme:
      - ExceptionFormat permet de detecter les erreurs dans le fichiers d'entrée
      - ExceptionIncohérence affichera les erreurs des octets (par exemple si IHL est égal à 4 alors qu'il doit être au minimum à 5)
      - ExceptionSupport permet d'afficher un message indiquant que l'analyseur ne connait pas le protocole suivant (par exemple si
          le type Ethernet n'est ni ARP, RARP ou Datagramme IP)
      - ExceptionTaille permet de detecter si le nombre d'octets est insuffisant pour analyser un protocole (par exemple le Datagramme IP doit
          avoir au minimum 20 octets)
          
    
_____________________________________________________________________


  7) Ajouter un nouveau protocole
  
  Si on désire ajouter un nouveau procole:
    - déterminer sur quelle couche ce procole est, determiner le protocole de la couche supérieur et les protocoles de sa couche interne
    - ajouter le protocole dans le dossier segment/ , ce protocole utilisera l'interface ITrame
    - determiner ses champs
    - s'ils n'existent pas déjà, ajouter les champs manquants dans le dossier champs utilisant l'interface IChamps
    - s'il y a des flags, utiliser la classe Flags.java, la modifier suivant le modèle de IP, TCP et DNS et ajouter les flags 
        manquant dans le dossier flags/ en utilisant l'interface IFlags
        
  Regarder ensuite la couche supérieure: 
    - il faut retrouver la fonction getNextSegment() du protocole de la couche supérieure
    - aller dans le champ determinant le protocole suivant afin que ce champ puisse le prendre en compte
  Par exemple si on ajoute un nouveau type ethernet 0x0110:
      Ethernet -> getNextSegment() = ((TypeEther)listEther.get(2)).getType() -> aller dans la classe TypeEther -> ajouter:
      		else if(n0 == 1 && n1 == 16) {
	      		type = "[nouveau protocole]";
              	}
        
  Par la suite, modifier Trame.java en suivant le modèle des autres protocoles afin d'inclure le nouveau segment:
  	
	/** ajout de [nouveau protocole] */
	else if(suite == "[nom du protocole]") {
		data = this.add[nom du protocole](data);
		suite =  this.getNextSegment();
	}
    
      	/** fonction d'ajout */
     	private List<String> add[nom du segement](List<String> data) throws [Exceptions necessaires]{
     		[Nom] a = new [Nom](data);
		listTrame.add(a);
		return a.getData();
	}	
    
    
_____________________________________________________________________
    
    
  8) Format du fichier d'entrée
    
  Sur wireshark, deux possibilités d'exportation de la trame:
    - click droit sur les octets -> Copy Bytes as Hex + ASCII Dump -> coller sur un fichier texte
    - click droit sur les octets -> Copy Bytes as Hex Dump -> coller sur un fichier texte
    
  Important:
    - les Offset et les octets seront normalement séparé par 3 espaces 
    - les octets et le ASCII Dump (s'il est là) seront normalement séparé par 3 espaces 
    - on peut coller plusieurs trames ensembles, elles doivent être séparé par l'offset 000
    
  Exemple avec 2 trames et les 2 exports différents de wireshark:
    
0000   3c 98 72 90 46 b8 bc 85 56 7b c7 b3 08 00 45 00   <.r.F...V{....E.
0010   00 8b 8f c6 40 00 40 06 6f fb c0 a8 01 20 23 de   ....@.@.o.... #.
0020   55 05 c5 9c 00 50 db 44 8d 58 ea 45 06 df 80 18   U....P.D.X.E....
0030   01 f6 1c 01 00 00 01 01 08 0a 0e 0e e9 c0 f5 20   ............... 
0040   4f d5 47 45 54 20 2f 20 48 54 54 50 2f 31 2e 31   O.GET / HTTP/1.1
0050   0d 0a 48 6f 73 74 3a 20 63 6f 6e 6e 65 63 74 69   ..Host: connecti
0060   76 69 74 79 2d 63 68 65 63 6b 2e 75 62 75 6e 74   vity-check.ubunt
0070   75 2e 63 6f 6d 0d 0a 41 63 63 65 70 74 3a 20 2a   u.com..Accept: *
0080   2f 2a 0d 0a 43 6f 6e 6e 65 63 74 69 6f 6e 3a 20   /*..Connection: 
0090   63 6c 6f 73 65 0d 0a 0d 0a                        close....
0000   ff ff ff ff ff ff 00 00 00 00 20 00 08 06 00 01
0010   08 00 06 04 00 01 00 00 00 00 20 00 0a 00 00 02
0020   00 00 00 00 00 00 0a 00 00 03
      
      
_____________________________________________________________________



  9) liste des protocoles et options reconnu par l'analyseur
     
  On ne peut analyser qu'une trace ethernet sans préambule ni champ FCS. Le champ Type identifie:
       - ARP
       - RARP
       - IP
  
  Pour ARP et RARP, il n'y a pas de protocole suivant analysable. Pour IP, le champ Protocol identifie:
       - UDP
       - TCP
       - ICMP
	
  Pour ICMP, on a une trame qui se divise en 2. Les 4 premiers octets sont commun, le champ Type permet de savoir comment est composé
  le reste de ICMP. Ce qui sont supportés/reconnu par l'analyseur (on donne les octets 'bruts' sinon):
       - Echo reply
       - Echo request
       - Timestamp
       - Timestamp reply
       - Destination Unreachable 
       - Source Quench 
       - Redirect
       - Address Mask Request
       - Address Mask Reply
	
  Pour UDP et TCP, des ports destinations et sources sont donnés. ceux identifiables sont:
       - HTTP (pour TCP)
       - DNS (pour UDP)
	
  Pour DNS, on a les requetes, les reponses, les 'authorative answer' et les 'additionnal answer'. Ceux qui sont entièrement développé sont:
       - requetes
       - reponses
	
  Pour les options de IP, l'analyseur est capable de reconnaitre:
       - Fin d'options EOOL 
       - Pas d’opération
       - Enregistrement de route (entièrement développé)
       - Enregistrement d’instant
       - Sécurité
       - Routage approximatif
       - Identificateur de stream
       - Routage impératif
 
  Pour les options de TCP, l'analyseur est capable de reconnaitre:
  	- Fin d'options EOOL 
        - Pas d’opération
	- Maximum Segment Size
	- Window scale factor
	- Echo
	- Echo reply
	- Timestamp
	- Alternate checksum request
	- Alternate checksum data
	- TCP Compression Filter
	- User Timeout
  

Source utilisé: http://www.networksorcery.com/enp/topic/ipsuite.htm

_____________________________________________________________________     
     
     
  10) Notes 
  
  - lorsqu'on lance le programme, il demandera 3 arguments. S'ils sont manquant, il affichera un message d'erreur avec les arguments demandés
  - les fichiers de sorties se retrouveront dans le dossier trames/
  - les fichiers se trouvant dans data/ ont été tirés de wireshark. Certains ont été modifié afin de tester les erreurs     
     
    Si on fait des ajout sur le programme:
      - NE PAS MODIFIER le Makefile
      - NE PAS MODIFIER les classes Analyseur.java, TraitementFichier.java
      - toujours placer les nouvelles classes dans le dossier src/
      
     
     
     
