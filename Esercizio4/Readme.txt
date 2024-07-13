
****COMPILAZIONE***

----PER COMPILARE LE CLASSI PER LA STRUTTURA DATI Graph NEL PACKAGE graph---
1) posizionarsi in .../Esercizio4/src
2) javac -d ../classes graph/Graph.java

----PER COMPILARE LE CLASSI PER LA STRUTTURA DATI UnionFindSet NEL PACKAGE unionfindset---
1) posizionarsi in .../Esercizio4/src
2) javac -d ../classes unionfindset/UnionFindSet.java

----PER COMPILARE LE CLASSI PER L'ALGORITMO DI KRUSKAL NEL PACKAGE kruskal---
1) posizionarsi in .../Esercizio4/src
2) javac -d ../classes kruskal/Kruskal.java

---PER COMPILARE IL PACKAGE graphmfsetusage---
1) posizionarsi in .../Esercizio4/src
2) javac -d ../classes graphmfsetusage/GraphMfsetUsage.java 

---PER COMPILARE LE CLASSI PER GLI UNIT TEST NEL PACKAGE graph---
1) posizionarsi in .../Esercizio4/src
2) javac -d ../classes -cp '.:../junit-4.12.jar:../hamcrest-core-1.3.jar' graph/*.java


***ESECUZIONE***

---PER ESEGUIRE graph/GraphTestsRunner---
1) posizionarsi in .../OrderedArray_Java/classes 
2) java -cp '.:../junit-4.12.jar:../hamcrest-core-1.3.jar'  graph/GraphTestsRunner

---PER ESEGUIRE graph/GraphMfsetUsage---
1) posizionarsi in .../Esercizio4/classes
2) java graphmfsetusage/GraphMfsetUsage "../italian_dist_graph.csv"



