package tbp.termrewriter.terms;

import java.util.List;

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

	/**
	 * What should a Variable return?
	 */
	@Override
	public List<Term> getSubterms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(String symbol) {
		// TODO Auto-generated method stub
		return false;
	}

}
