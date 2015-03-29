package tbp.termrewriter.terms;

import tbp.termrewriter.language.Language;

public class TermFactory {

	private Language language;

	public TermFactory() {
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Constant createConstant(String constantSymbol) {
		return new Constant(constantSymbol);
	}

	public FunctionSymbol createfunctionSymbol(int arity, String symbol) {
		return new FunctionSymbol(arity, symbol);
	}
}
