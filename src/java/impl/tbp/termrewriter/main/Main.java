package tbp.termrewriter.main;

import java.io.File;

import tbp.termrewriter.exceptions.TermException;
import tbp.termrewriter.language.LanguageReader;
import tbp.termrewriter.terms.TermFactory;

public class Main {

	public static void main(String[] args) throws TermException {
		// Main m = new Main();
		// TermFactory factory = m.createFactory();

		TermFactory factory = new TermFactory();
		LanguageReader languageReader = new LanguageReader(new File(
				"resources/language.json"));
		factory.setLanguage(languageReader.readLanguage());

		// System.out.println(Arrays.toString(factory.getLanguage().getLanguage().toArray()));

		String inputString = "f(c,e(f(e(c),g(c,1,y))))";
		// String inputString = "g(c,1,y)";
		factory.parseStringToTerm(inputString);
		//
		// System.out.println(factory.deepToString(ouput));
	}
	// /**
	// * Create a simple language.
	// *
	// * @return the language
	// */
	// private TermFactory createFactory() {
	// TermFactory factory = new TermFactory();
	// ArrayList<Term> language = new ArrayList<Term>();
	//
	// language.add(new FunctionSymbol("c", 0));
	// language.add(new FunctionSymbol("e", 1));
	// language.add(new FunctionSymbol("f", 2));
	// Language l = new Language();
	// l.setLanguage(language);
	// factory.setLanguage(l);
	//
	// return factory;
	// }
}
