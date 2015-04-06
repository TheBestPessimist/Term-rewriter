package tbp.termrewriter.terms;

public class TermNotPartOfTheLanguageException extends TermException {

	public TermNotPartOfTheLanguageException(String exceptionMessage) {
		super("The term " + exceptionMessage + " is not part of the language.");
	}

}
