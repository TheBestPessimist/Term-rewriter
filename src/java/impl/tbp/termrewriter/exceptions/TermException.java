package tbp.termrewriter.exceptions;

/**
 * This is the base exception of the termParser.
 */
@SuppressWarnings("serial")
public class TermException extends Exception {

	public TermException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
