package tbp.termrewriter.exceptions;

@SuppressWarnings("serial")
public class TermNotPartOfTheLanguageException extends TermException {

	public TermNotPartOfTheLanguageException(String exceptionMessage) {
		super("The term " + exceptionMessage + " is not part of the language.");
	}

}
