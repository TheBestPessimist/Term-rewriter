package tbp.termrewriter.language;

import java.util.List;

import tbp.termrewriter.term.Term;

/**
 * The language which will be used to create the Terms.
 * 
 * @author CristianViorel
 *
 */
public class Language {

	private List<Term> language;

	public List<Term> getLanguage() {
		return language;
	}

	public void setLanguage(List<Term> language) {
		this.language = language;
	}

	public Term getFunctionSymbol(String symbol) {
		for (Term t : language) {
			if (t.equals(symbol)) {
				return t;
			}
		}
		return null;
	}
}
