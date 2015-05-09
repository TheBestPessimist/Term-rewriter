package tbp.termrewriter.main;

import java.io.File;

import tbp.termrewriter.exceptions.TermException;
import tbp.termrewriter.language.LanguageParser;
import tbp.termrewriter.term.Term;
import tbp.termrewriter.terms.TermUtils;

public class Main {

    public static void main(String[] args) throws TermException {
        TermUtils utils = new TermUtils();
        LanguageParser languageParser = new LanguageParser(new File("resources/language.json"));
        utils.setLanguage(languageParser.readLanguage());

        String inputString = "";

        inputString = "()aaaa av";
        inputString = "f(hghghghghghg,e(f(e(c),g(c,1,y))))";
        // inputString = "1()";
        // inputString = "f(aaaa, babahsjdbsajhdbajh-sda)";
        Term root = utils.parseStringToTerm(inputString);

        Term subterm = utils.getSubterm(root, new int[] { 0, });
        inputString = "f(ew,e(f(e(c),g(c,1,y))))";

        Term root2 = utils.parseStringToTerm(inputString);

        // Term subterm2 = utils.getSubterm(root2, new int[] { 2, });

        //
        // System.out.println(utils.deepToString(root));
        // System.out.println(utils.deepToString(subterm));
        // Term[] allTerms = utils.getAllTerms(root);
        // for (Term t : allTerms) {
        // System.out.println(t);
        // }
        // for (Term t : utils.getAllTerms(allTerms[0])) {
        // System.out.println(t);
        // }

        System.out.println(utils.deepEquals(root, root2));
    }
}
