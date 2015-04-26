package tbp.termrewriter.terms;

import tbp.termrewriter.term.Term;

/**
 * This represents a variable symbol. There are no setters as we do not want to
 * change the symbol of this VariableSymbol. If such need arises, then a new VariableSymbol
 * should be created!
 */

public class VariableSymbol implements Term {

	private String symbol;
	private Term parent;

	public VariableSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String getSymbol() {
		return symbol;
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

	@Override
	public boolean equals(Object obj) {
		return getSymbol().equals(obj);
	};

	@Override
	public int hashCode() {
		return getSymbol().hashCode();
	}

	@Override
	public String toString() {
		return "VariableSymbol [symbol=" + symbol + "]";
	}

}
