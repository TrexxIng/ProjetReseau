
SRC = src
BIN = bin

EXECUTABLE = analyseur/Analyseur
NOM_EXECUTABLE = analyseur
EXT = .class


JCC = javac

CLASS = $(SRC)/**/*.java

FLAGS = -sourcepath $(SRC) -d $(BIN)


all: compilation

compilation:
	$(JCC) $(FLAGS) $(CLASS)
	@echo " "
	@echo " Compilation reussi \n Execution:java -cp bin analyseur.Analyseur <filenameEntry> <multifile> <filenameExit>"
	@echo " information sur l'execution: make help"
	@echo " "	
clean:
	rm -rf $(BIN)/*

help:
	@echo " "
	@echo " Execution:java -cp bin analyseur.Analyseur <filenameEntry> <multifile> <filenameExit>"
	@echo "\t filenameEntry: nom du fichier d'entrée (chemin relatif)"
	@echo "\t multifile: 0 si on ecrit toute le trame sur le même fichier, 1 si on ecrit une trame par fichier"
	@echo "\t filenameExit: nom du fichier en sortie (sera ecrit dans le dossier trames)"
	@echo " "
	@echo " 1) Conseil: placer le fichier d'entrée dans le dossier data, fileNameEntry sera alors data/[nom du fichier]"
	@echo " 2) Valeur acceptée par multifile: 0 | F | False | Faux | f | faux | false"
	@echo "                                   1 | V | Vrai | v | vrai | T | True | t | True"
	@echo " 3) le nom du fichier de sortie prendra en compte l'extension"
	@echo "          Si [nom].txt alors en sortie, on aura trames/[nom].txt"
	@echo "          Si le fichier n'a pas d'extension, il mettra '.txt' par défaut"
	@echo "          Si on crée plusieurs fichiers en sortie, alors ils seront numérotés selon l'ordre des trames du fichier d'entrée" 
	@echo " "	

