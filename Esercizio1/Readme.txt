###Pulizia#####

del *.o su Windows
rm *.o su Linux


###Compilazione#####

--Compilazione implementazione del merge_binary_insertion_sort
gcc -c -Wall -Wextra -Wpedantic -Wconversion merge_binary_insertion_sort.c merge_binary_insertion_sort_main.c

--Compilazione unit test del merge_binary_insertion_sort
gcc -c -Wall -Wextra -Wpedantic -Wconversion merge_binary_insertion_sort.c merge_binary_insertion_sort_tests.c unity.c

###Linking#####

--Linking implementazione del merge_binary_insertion_sort
gcc -o usage merge_binary_insertion_sort.o merge_binary_insertion_sort_main.c

--Linking unit test del merge_binary_insertion_sort
gcc -o test merge_binary_insertion_sort.o merge_binary_insertion_sort_tests.o unity.o


###Esecuzione#####


--Esecuzione implementazione del merge_binary_insertion_sort
Il secondo parametro è  k mentre il terzo è il numero di valori letti da records
linux: ./usage "records.csv" 3 20000000
windows: usage.exe "records.csv" 3 20000000

--Linking unit test del merge_binary_insertion_sort
linux: ./test 
windows: test.exe
