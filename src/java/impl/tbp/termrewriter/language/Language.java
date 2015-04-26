package tbp.termrewriter.language;

import java.util.ArrayList;
import java.util.List;

import tbp.termrewriter.term.Term;

public class Language {
    private List<Term> terms;

    public Language() {
        terms = new ArrayList<Term>();
    }

    public Language(List<Term> terms) {
        this.terms = (ArrayList<Term>) terms;
    }

    public void addTerm(Term t) {
        terms.add(t);
    }

    public List<Term> getTerms() {
        return terms;
    }

    public Term getTermBySymbol(String symbol) {
        for (Term t : terms) {
            if (t.equals(symbol)) {
                return t;
            }
        }
        return null;
    }
}
