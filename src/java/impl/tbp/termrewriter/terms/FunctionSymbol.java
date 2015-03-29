package tbp.termrewriter.terms;

import tbp.termrewriter.term.Term;

public class FunctionSymbol implements Term {

	private int arity;
	private String symbol;

	public FunctionSymbol(int arity, String symbol) {
		this.arity = arity;
		this.symbol = symbol;
	}

	@Override
	public int getArity() {
		return arity;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

}
