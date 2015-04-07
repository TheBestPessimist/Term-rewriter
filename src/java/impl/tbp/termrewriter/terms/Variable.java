package tbp.termrewriter.terms;

import java.util.List;

import tbp.termrewriter.term.Term;

/**
 * This represents a variable symbol. There are no setters as we do not want to
 * change the symbol of this Variable. If such need arises, then a new Variable
 * should be created!
 */

public class Variable implements Term {

	private String symbol;

	public Variable(String symbol) {
		this.symbol = symbol;
	}


	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public boolean equals(String symbol) {
		// TODO Auto-generated method stub
		return false;
	}

}
