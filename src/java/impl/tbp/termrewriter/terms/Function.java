package tbp.termrewriter.terms;

import tbp.termrewriter.term.Term;

public class Function implements Term {

	private int arity;
	private String value;

	public Function() {
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
