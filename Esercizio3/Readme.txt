
####COMPILAZIONE####

----PER COMPILARE LE CLASSI PER LA STRUTTURA DATI UnionFindSet NEL PACKAGE unionfindset---
1) posizionarsi in .../Esercizio3/src
2) javac -d ../classes unionfindset/UnionFindSet.java

---PER COMPILARE LE CLASSI PER GLI UNIT TEST NEL PACKAGE unionfindset---
1) posizionarsi in .../Esercizio3/src
2) javac -d ../classes -cp '.:../junit-4.12.jar:../hamcrest-core-1.3.jar' unionfindset/*.java 

####ESECUZIONE####

---PER ESEGUIRE unionfindset/UnionFindSetTestsRunner---
1) posizionarsi in .../Esercizio3/classes 
2) java -cp '.:../junit-4.12.jar:../hamcrest-core-1.3.jar'  unionfindset/UnionFindSetTestsRunner
