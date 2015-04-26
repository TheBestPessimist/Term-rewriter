package tbp.termrewriter.main;

import java.io.File;

import tbp.termrewriter.exceptions.TermException;
import tbp.termrewriter.language.LanguageReader;
import tbp.termrewriter.term.Term;
import tbp.termrewriter.terms.TermUtils;

public class Main {

    public static void main(String[] args) throws TermException {
        TermUtils factory = new TermUtils();
        LanguageReader languageReader = new LanguageReader(new File("resources/language.json"));
        factory.setLanguage(languageReader.readLanguage());

        String inputString = "f(c,e(f(e(c),g(c,1,y))))";
        // String inputString = "c";
        Term root = factory.parseStringToTerm(inputString);

        System.out.println(factory.deepToString(root));
    }
}
