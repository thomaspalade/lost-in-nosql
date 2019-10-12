In implementarea rezolvarii am folosit urmatoarele clase:
Tema2 
	- clasa ce contine main-ul in care imi creez baza de date si fac citirea din fisierul de intrare
	- tot de aici se apeleaza operatiile UPDATE, INSERT, GET, etc.

DataBase - clasa ce contine 2 atribute: nr_of_nodes si max_capacity.
	 - avem un atribut de tip ArrayList <Nod> ce va contine toate nodurile bazei de date
	 - clasa contine 2 metode: CREATEDB si SNAPSHOTDB

Nod	 - clasa ce contine 2 atribute diferite de tip ArrayList
	 - un ArrayList ce memoreaza toate Entitatile salvate pe acel Nod
	 - un ArrayList ce memoreaza Atributele si Instantele de pe acel nod, ce urmeaza sa fie printate in eventualitate unui apel al functiei SNAPSHOTDB


Atribut	 - contine numele unui atribut si tipul acestuia
	 - un atribut de tip int va avea type-ul codificat ca 0
	 - un atribut de tip String va avea type-ul codificat ca 1
	 - un atribut de tip Float va avea type-ul codificat ca 2

Val_Atribut - o clasa ce contine type-ul atributului si valoarea propriu zisa
	    - avem 3 atribute (int, String, Float)
	    - in functie de type, un anumit atribut este completat

Instanta    - retine practic un ArrayList de Val_Atribut

Entitate    - contine nume, RF si numarul de atribute
	    - contine 2 ArrayList-uri (de Atribute si de Instante)
	    - metoda Creez_Entitate
	    - metoda Inserare_Instanta
	    - metoda Sterge_Instanta
	    - metoda Update_Instanta