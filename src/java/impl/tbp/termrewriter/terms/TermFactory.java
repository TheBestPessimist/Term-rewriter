package tbp.termrewriter.terms;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import tbp.termrewriter.exceptions.NotATermException;
import tbp.termrewriter.exceptions.TermNotPartOfTheLanguageException;
import tbp.termrewriter.language.Language;
import tbp.termrewriter.term.Term;

public class TermFactory {

	private static final String VIRTUAL_FUNCTION_SYMBOL_FOR_PRINTING = "virtualFunctionSymbolForPrinting";
	private Language language;

	public TermFactory() {
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public FunctionSymbol createFunctionSymbol(String symbol, int arity) {
		return new FunctionSymbol(symbol, arity);
	}

	public FunctionSymbol createFunctionSymbolFromTerm(Term term) {
		if (term instanceof FunctionSymbol) {
			return new FunctionSymbol(term.getSymbol(),
					((FunctionSymbol) term).getArity());
		} else {

			// TODO not a functionSymbolException?
			try {
				throw new OperationNotSupportedException();
			} catch (OperationNotSupportedException e) {
				System.out
						.println("Unexpected Exception ( not a functionSymbol: "
								+ e);
				return null;
			}
		}
	}

	private Variable createVariableFromTerm(Term term) {
		if (term instanceof Variable) {
			return new Variable(term.getSymbol());
		} else {

			// TODO not a VariableException?
			try {
				throw new OperationNotSupportedException();
			} catch (OperationNotSupportedException e) {
				System.out
						.println("Unexpected Exception (not a variable exception: "
								+ e);
				return null;
			}
		}
	}

	public void parseStringToTerm(String s, Term root)
			throws TermNotPartOfTheLanguageException {

		// the "parantheses"
		String[] functionParts = s.split("\\(", 2);

		// get rid of the last bracket
		if (functionParts.length > 1) {
			functionParts[1] = functionParts[1].substring(0,
					functionParts[1].length() - 1);
		}

		Term currentTerm = language.getTermBySymbol(functionParts[0]);

		if (currentTerm != null) {

			// handle FunctionSymbols
			if (currentTerm instanceof FunctionSymbol) {
				FunctionSymbol currentFunctionSymbol = createFunctionSymbolFromTerm(currentTerm);
				// add this new variable
				((FunctionSymbol) root).getSubterms()
						.add(currentFunctionSymbol);
				currentFunctionSymbol.setParent(root);

				if (functionParts.length > 1) {
					// check good arity with regard to the remaining input
					// variables
					String variables[] = getCurrentLevelVariablesFromInputString(functionParts[1]);
					if (variables.length == currentFunctionSymbol.getArity()) {
						for (String variable : variables) {
							parseStringToTerm(variable, currentFunctionSymbol);
						}
					}
				}
				// handle Variables
			} else if (currentTerm instanceof Variable) {
				Variable currentVariable = createVariableFromTerm(currentTerm);
				// add this new variable
				((FunctionSymbol) root).getSubterms().add(currentVariable);
				currentVariable.setParent(root);

				// check good arity with regard to the remaining input variables
				if (functionParts.length > 1) {
					// TODO throw new badInputStringException
					try {
						throw new OperationNotSupportedException();
					} catch (OperationNotSupportedException e) {
						System.out
								.println("Unexpected Exception (badInputStringException @ variable): "
										+ e);
					}
				}
			}
		} else {
			throw new TermNotPartOfTheLanguageException(functionParts[0]);
		}
	}

	private String[] getCurrentLevelVariablesFromInputString(String inputString) {
		List<String> variables = new ArrayList<String>();
		int len = inputString.length();
		int openParantheses = 0;
		int lastUsedPositionForSplitting = 0;
		for (int i = 0; i < len; ++i) {
			if (openParantheses == 0 && inputString.startsWith(",", i)) {
				variables.add(inputString.substring(
						lastUsedPositionForSplitting, i));
				lastUsedPositionForSplitting = i + 1;
				continue;
			}
			if (inputString.startsWith("(", i)) {
				openParantheses++;
				continue;
			}
			if (inputString.startsWith(")", i)) {
				openParantheses--;
				continue;
			}
		}
		variables.add(inputString.substring(lastUsedPositionForSplitting, len));

		return variables.toArray(new String[0]);
	}

	private void deepToString(Term root, StringBuilder out, int indent)
			throws NotATermException {
		out.append(root);
		out.append("\n");
		if (root instanceof FunctionSymbol) {
			List<Term> subterms = ((FunctionSymbol) root).getSubterms();
			for (Term subterm : subterms) {
				for (int i = 0; i <= indent; ++i) {
					out.append("---");
				}
				deepToString(subterm, out, indent + 1);
			}
		} else if (root instanceof Variable) {
			// this is already handled above
		} else {
			throw new NotATermException(root.toString());
		}
	}

	public String deepToString(Term root) throws NotATermException {
		StringBuilder out = new StringBuilder();
		deepToString(root, out, 0);
		return out.toString();
	}

	/**
	 * @param inputString
	 * @throws TermNotPartOfTheLanguageException
	 */
	public Term parseStringToTerm(String inputString)
			throws TermNotPartOfTheLanguageException {
		Term root = new FunctionSymbol(VIRTUAL_FUNCTION_SYMBOL_FOR_PRINTING, 1);

		parseStringToTerm(inputString, root);

		return getSubterm(root, new int[] { 0, 0 });

	}

	/**
	 * Get the subterm in the tree at the position specified in the array. The
	 * numbers in the array signify what subterm of the current term will be
	 * returned, recursively.
	 * 
	 * Position counting starts at 0.
	 * 
	 * @param subterms
	 *            the array containing the positions
	 * @return
	 */
	// TODO should throw subterm does not exist exception?

	public Term getSubterm(Term root, int[] subtermPositions) {
		return getSubtermRecursively(root, subtermPositions, 0);
	}

	private Term getSubtermRecursively(Term root, int[] subtermPositions,
			int currentPosition) {
		if (root instanceof FunctionSymbol) {
			FunctionSymbol currentFunctionSymbol = (FunctionSymbol) root;
			int subtermsSize = currentFunctionSymbol.getSubterms().size();
			if (subtermsSize > subtermPositions[currentPosition]
					&& subtermPositions[currentPosition] >= 0) {
				if (currentPosition == subtermPositions.length - 1) {
					return currentFunctionSymbol;
				} else {
					return getSubtermRecursively(
							currentFunctionSymbol.getSubterms().get(
									subtermPositions[currentPosition]),
							subtermPositions, ++currentPosition);
				}
			} else {
				// TODO i can't address outside of any of the 2 arrays.
				// exception?
				return null;
			}
		} else if (root instanceof Variable) {
			Variable currentVariable = (Variable) root;
			if (currentPosition == subtermPositions.length - 1) {
				return currentVariable;
			} else {
				// TODO here i can't address anything else. exception???
				return null;
			}
		}
		// TODO nothing should be here. EXCEPTION!!!
		return null;

	}
}
