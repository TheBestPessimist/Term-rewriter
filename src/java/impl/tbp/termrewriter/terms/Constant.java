package tbp.termrewriter.terms;

import tbp.termrewriter.term.Term;

/**
 * This represents the identity function symbol, with arity 0. There are no
 * setters as we do not want to change the symbol of this constant. If such need
 * arises, then a new constant should be created!
 *
 */
public class Constant implements Term {

	private int arity = 0;
	private String symbol;

	public Constant(String symbol) {
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

	@Override
	public String toString() {
		return "Constant [arity=" + arity + ", symbol=" + symbol + "]";
	}

}
