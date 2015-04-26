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
}
