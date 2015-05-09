### TODO

- trebuie peste tot checkuri daca face parte din language
- sa implementez substitution of a term with another.
- ca sa implementez acel function match, eu tre sa ma plimb prin tot treeul,si sa caut functii, care se aplica la functii, si variabile.. variabilele pot sa fie diferite! practic e shittons of recursive calls.

### Questions
la substitution: ce se intampla daca am 2 variablie care vreau sa le substitui, si una e in interiorul celeilalte?
eg: root = f(g(c,1,y),e(f(e(g(c,1,y)),g(c,1,y))))
subst 1: f(g(c,1,y),e(f(e(g(c,1,y)),g(c,1,y)))) -> 1
subst 2: 1 -> 555555555

	
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
- collect variable names during parsing
- substitution of a variable with a term;
- rename getSubterm to getSubtermByPosition
- rename variable symbol to variable



### whatever
- reduction se afla in capitolul 2
- terms se afla la capitolul 3, pg 46
- eu propun ca toate chestiile care le creem, sa se creeze cu un factory, de aceea am creat `TermFactory`.
	- facem ceva de genul; 
		1. instantiem factory 
		2. i dam la factory limbajul cu care lucreaza
	