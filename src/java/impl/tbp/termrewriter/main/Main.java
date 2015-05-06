package tbp.termrewriter.main;

import java.io.File;

import tbp.termrewriter.exceptions.TermException;
import tbp.termrewriter.language.LanguageReader;
import tbp.termrewriter.term.Term;
import tbp.termrewriter.terms.TermUtils;

public class Main {

    public static void main(String[] args) throws TermException {
        TermUtils utils = new TermUtils();
        LanguageReader languageReader = new LanguageReader(new File("resources/language.json"));
        utils.setLanguage(languageReader.readLanguage());

        String inputString = "f(c,e(f(e(c),g(c,1,y))))";
        // String inputString = "c";
        Term root = utils.parseStringToTerm(inputString);

        Term subterm = utils.getSubterm(root, new int[] { 2, 1, 0, 1 });

        System.out.println(utils.deepToString(root));
        System.out.println(utils.deepToString(subterm));
    }
}
