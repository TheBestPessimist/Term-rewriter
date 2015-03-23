package tbp.termrewriter.terms;

import tbp.termrewriter.term.Term;

/**
 * This represents the identity function symbol, with arity 0.
 *
 */
public class Constant implements Term {

	private int arity = 0;

	@Override
	public int getArity() {
		return arity;
	}

}
