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

        // inputString = "()aaaa av";
        inputString = "f(g(c,1,y),e(f(e(g(c,1,y)),g(c,1,y))))";
        // inputString = "1()";
        // inputString = "f(aaaa, babahsjdbsajhdbajh-sda)";
        Term root = utils.parseStringToTerm(inputString);

        // Term subterm = utils.getSubterm(root, new int[] { 0, });
        // inputString = "g(c,1,y)";
        //
        Term root2 = utils.deepCopyTerm(root);

        System.out.println(utils.deepToString(root));
        System.out.println(utils.deepToString(root2));
        System.out.println(utils.deepEquals(root, root2));
        // System.out.println(utils.deepToString(subterm));
        // Term[] allTerms = utils.getAllTerms(root);
        // for (Term t : allTerms) {
        // System.out.println(t);
        // }
        // for (Term t : utils.getAllTerms(allTerms[0])) {
        // System.out.println(t);
        // }
        //
        // for (Term t : utils.deepFindAllMatches(root, root2)) {
        // System.out.println(utils.deepToString(t));
        // }

        // inputString = "f(g(c,1,y),e(f(e(g(c,1,y)),g(c,1,0))))";
        // Term root = utils.parseStringToTerm(inputString);
        // Term subterm1 = utils.parseStringToTerm("g(c,1,y)");
        // Term subterm2 = utils.parseStringToTerm("e(f(e(g(c,1,y)),g(c,1,y)))");
        // Term substitute1 = utils.parseStringToTerm("ee");
        // Term substitute2 = utils.parseStringToTerm("1");
        //
        // utils.substitute(root, new Term[] { subterm1, subterm2, }, new Term[] { substitute1, substitute2, });
    }
}
