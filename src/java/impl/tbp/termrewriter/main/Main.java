package tbp.termrewriter.main;

import tbp.termrewriter.term.Term;
import tbp.termrewriter.terms.Constant;

public class Main {

	// TODO lista de variabile
	// TODO lista de constante
	// TODO lista de function symbols
	// TODO lista de cum se sparg termenii? (the rules)
	// XXX reduction se afla in capitolul 2
	// XXX terms se afla la capitolul 3, pg 34

	public static void main(String[] args) {
		Term i = new Constant();
		System.out.println(i.getArity());
	}
}
