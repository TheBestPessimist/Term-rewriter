package tbp.termrewriter.terms;

import tbp.termrewriter.term.Term;

public class Variable implements Term {

	private int arity;
	private String value;

	public Variable() {
		arity = -1;
		value = "";
	}

	@Override
	public int getArity() {
		return arity;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setArity(int arity) {
		this.arity = arity;
	}

}
