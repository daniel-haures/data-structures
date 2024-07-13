Posizionarsi su cartella Esercizio 2

Per l'implementazione:
-pulire da precedenti compilazioni: del *.o oppure rm *.o in caso di linux
-compilazione: gcc -c -Wall -Wextra -Wpedantic -Wconversion edit_distance.c edit_distance_main.c 
-linkig: gcc -o editdist edit_distance.o edit_distance_main.o
-esecuzione su windows: editdist.exe correctme.txt dictionary.txt
-esecuzione su linux: ./editdist correctme.txt dictionary.txt

Per il test unit:
-pulire da precedenti compilazioni: del *.o oppure rm *.o in caso di linux
-compilazione: gcc -c edit_distance.c edit_distance_test.c unity.c
-linkig: gcc -o test edit_distance.o edit_distance_test.o unity.o
-esecuzione su windows: test.exe
-esecuzione su linux: ./test
