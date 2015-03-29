package tbp.termrewriter.terms;

import tbp.termrewriter.term.Term;

/**
 * This represents a variable symbol. There are no setters as we do not want to
 * change the symbol of this Variable. If such need arises, then a new Variable
 * should be created!
 */

public class Variable implements Term {

	private int arity;
	private String symbol;

	public Variable(int arity, String symbol) {
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
