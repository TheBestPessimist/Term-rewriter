### TODO and info
- lista de variabile
- lista de constante
- lista de function symbols
- lista de cum se sparg termenii? (the language)
- reduction se afla in capitolul 2
- terms se afla la capitolul 3, pg 46
- eu propun ca toate chestiile care le creem, sa se creeze cu un factory, de aceea am creat `TermFactory`.
	- facem ceva de genul; 
		1. instantiem factory 
		2. i dam la factory limbajul cu care lucreaza
		3. cand creem ceva, vedem daca exista symbolul in language, altfel ar trebui sa nu dam nimic inapoi?
	- punctul 3 poate fi verificat mai in colo, presupun eu, mai important este sa facm parsarea.
	


### Questions
- ar trebui sa fac si constants si function symbols, sau doar function symbols? (tinand cont ca constants au arity 0)
- nu prea stiu cum sa fac cu variabiele.. ceva nu se leaga.
	- ce sunt variabilele
	- ce tinem in ele
	- cum tinem in ele
	- ????


