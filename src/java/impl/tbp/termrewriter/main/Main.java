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

		Constant a = factory.createConstant("A");
		Constant b = factory.createConstant("B");

		System.out.println(i + " " + j);
	}
}
