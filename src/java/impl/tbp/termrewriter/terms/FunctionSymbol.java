package tbp.termrewriter.terms;

import java.util.ArrayList;
import java.util.List;

import tbp.termrewriter.term.Term;

/**
 * This represents a function symbol. There are no setters as we do not want to
 * change the symbol of this function. If such need arises, then a new
 * FunctionSymbol should be created!
 */

public class FunctionSymbol implements Term {

	private int arity;
	private String symbol;
	private List<Term> subTerms;
	private Term parent;

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
	public boolean equals(Object obj) {
		return getSymbol().equals(obj);
	}

	@Override
	public int hashCode() {
		return getSymbol().hashCode();
	}

	@Override
	public String toString() {
		return "FunctionSymbol [arity=" + arity + ", symbol=" + symbol + "]";
	}

	/**
	 * @return the parent
	 */
	public Term getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Term parent) {
		this.parent = parent;
	}

}
