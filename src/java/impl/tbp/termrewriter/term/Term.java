package tbp.termrewriter.term;

import java.util.List;

public interface Term {

	public int getArity();

	public String getSymbol();

	public List<Term> getSubterms();

	public boolean equals(String symbol);
}
