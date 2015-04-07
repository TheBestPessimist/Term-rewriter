package tbp.termrewriter.terms;

import java.util.ArrayList;
import java.util.List;

import tbp.termrewriter.term.Term;

public class FunctionSymbol implements Term {

	private int arity;
	private String symbol;
	private List<Term> subTerms;

	public FunctionSymbol(String symbol, int arity) {
		this.arity = arity;
		this.symbol = symbol;
		subTerms = new ArrayList<Term>();
	}

	public int getArity() {
		return arity;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	public List<Term> getSubterms() {
		return subTerms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(String symbol) {
		if (symbol == null)
			return false;

		return this.symbol.equals(symbol);
	}

	@Override
	public String toString() {
		return "FunctionSymbol [arity=" + arity + ", symbol=" + symbol + "]";
	}

}
