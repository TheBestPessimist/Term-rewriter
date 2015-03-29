package tbp.termrewriter.main;

import tbp.termrewriter.term.Term;
import tbp.termrewriter.terms.Constant;
import tbp.termrewriter.terms.TermFactory;

public class Main {

	public static void main(String[] args) {
		createLanguage();
	}

	private static void createLanguage() {
		TermFactory factory = new TermFactory();

		Term i = factory.createConstant("A");
		Term j = factory.createConstant("A");
		System.out.println(i + " " + j);
	}
}
