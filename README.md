### info
- reduction se afla in capitolul 2
- terms se afla la capitolul 3, pg 46
- eu propun ca toate chestiile care le creem, sa se creeze cu un factory, de aceea am creat `TermFactory`.
	- facem ceva de genul; 
		1. instantiem factory 
		2. i dam la factory limbajul cu care lucreaza
	
	
### TODO
- trebuie peste tot checkuri daca face parte din language


### Questions
		
	
### Done
- ar trebui sa fac si constants si function symbols, sau doar function symbols? (tinand cont ca constants au arity 0)
- nu prea stiu cum sa fac cu variabiele.. ceva nu se leaga.
	- ce sunt variabilele
	- ce tinem in ele
	- cum tinem in ele
	- ????
- lista de variabile
- lista de constante
- lista de function symbols
- lista de cum se sparg termenii? (the language)
- trebuie sa scapam de termenul dummy cand facem parsarea sirului
- sa adresam termenii (incepand de la root si pana la termenul care il vrem noi)
- backreference to el padre.
- la parsarea termenilor din string, sa se implementeze cazul pentru Variable
- trebuie override la equals de la clasa object!
