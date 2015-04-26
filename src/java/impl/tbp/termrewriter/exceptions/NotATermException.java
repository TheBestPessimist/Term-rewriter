package tbp.termrewriter.exceptions;

@SuppressWarnings("serial")
public class NotATermException extends TermException {

	/**
	 * @param exceptionMessage
	 */
	public NotATermException(String exceptionMessage) {
		super(exceptionMessage + "is not a term.");
	}

}
