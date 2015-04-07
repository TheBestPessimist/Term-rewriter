package tbp.termrewriter.term;

public interface Term {

	public String getSymbol();

	public boolean equals(String symbol);

	public boolean equals(Term term);
}
