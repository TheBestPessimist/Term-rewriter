package tbp.termrewriter.main;

import java.util.ArrayList;
import java.util.Arrays;

import tbp.termrewriter.language.Language;
import tbp.termrewriter.term.Term;
import tbp.termrewriter.terms.FunctionSymbol;
import tbp.termrewriter.terms.TermException;
import tbp.termrewriter.terms.TermFactory;

public class Main {

	public static void main(String[] args) throws TermException {
		Main m = new Main();
		TermFactory factory = m.createFactory();
		System.out.println(Arrays.toString(factory.getLanguage().getLanguage()
				.toArray()));
		String inputTerm = "f(c,e(f(e(c),e(c))))";

		Term ouput = factory.createFunctionSymbol("a", 1);

		factory.parseStringToTerm(inputTerm, ouput);
		System.out.println(factory.deepToString(ouput));
	}

	/**
	 * Create a simple language.
	 * 
	 * @return the language
	 */
	private TermFactory createFactory() {
		TermFactory factory = new TermFactory();
		ArrayList<Term> language = new ArrayList<Term>();

		language.add(new FunctionSymbol("c", 0));
		language.add(new FunctionSymbol("e", 1));
		language.add(new FunctionSymbol("f", 2));
		Language l = new Language();
		l.setLanguage(language);
		factory.setLanguage(l);

		return factory;
	}
}
