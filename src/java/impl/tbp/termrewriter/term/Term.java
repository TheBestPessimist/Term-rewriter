package tbp.termrewriter.term;

public interface Term {

	public String getSymbol();

	@Override
	public boolean equals(Object obj);

	// public boolean equals(String symbol);
	// public boolean equals(Term term);
}
