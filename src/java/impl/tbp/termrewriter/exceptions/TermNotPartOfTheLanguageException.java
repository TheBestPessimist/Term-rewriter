package tbp.termrewriter.exceptions;

import tbp.termrewriter.terms.TermException;

public class TermNotPartOfTheLanguageException extends TermException {

	public TermNotPartOfTheLanguageException(String exceptionMessage) {
		super("The term " + exceptionMessage + " is not part of the language.");
	}

}
