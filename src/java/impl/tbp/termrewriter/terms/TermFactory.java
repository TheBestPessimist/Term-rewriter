package tbp.termrewriter.terms;

import java.util.ArrayList;
import java.util.List;

import tbp.termrewriter.exceptions.TermNotPartOfTheLanguageException;
import tbp.termrewriter.language.Language;
import tbp.termrewriter.term.Term;

public class TermFactory {

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
		return new FunctionSymbol(term.getSymbol(), term.getArity());
	}
	

	// /**
	// *
	// * @param s
	// * @return
	// *
	// * Rules to parse a string into terms:
	// */
	// public Term parseStringToTerm(String s) {
	// Term root = new FunctionSymbol
	//
	// // System.out.println(Arrays.toString(functionParts));
	// // System.out.println(functionParts.length);
	//
	// return null;
	// }

	
	
	
	public void parseStringToTerm(String s, Term root)
			throws TermNotPartOfTheLanguageException {

		String[] functionParts = s.split("\\(", 2); // the "parantheses"
		if (functionParts.length > 1) {
			functionParts[1] = functionParts[1].substring(0,
					functionParts[1].length() - 1); // get rid of the last
													// bracket
		}
		if (language.getTermBySymbol(functionParts[0]) != null) {

			FunctionSymbol f = createFunctionSymbolFromTerm(language
					.getFunctionSymbol(functionParts[0]));
			root.getSubterms().add(f); // add this new variable
			if (functionParts.length > 1) {
				String variables[] = getCurrentLevelVariables(functionParts[1]);
				if (variables.length == f.getArity()) { // check good arity
					for (String variable : variables) {
						parseStringToTerm(variable, f);
					}
				}
			}
		} else {
			throw new TermNotPartOfTheLanguageException(functionParts[0]);
		}
	}

	private String[] getCurrentLevelVariables(String functionVariables) {
		List<String> variables = new ArrayList<String>();
		int len = functionVariables.length();
		int openParantheses = 0;
		int lastUsedPositionForSplitting = 0;
		for (int i = 0; i < len; ++i) {
			if (openParantheses == 0 && functionVariables.startsWith(",", i)) {
				variables.add(functionVariables.substring(
						lastUsedPositionForSplitting, i));
				lastUsedPositionForSplitting = i + 1;
				continue;
			}
			if (functionVariables.startsWith("(", i)) {
				openParantheses++;
				continue;
			}
			if (functionVariables.startsWith(")", i)) {
				openParantheses--;
				continue;
			}
		}
		variables.add(functionVariables.substring(lastUsedPositionForSplitting,
				len));

		return variables.toArray(new String[0]);
	}

	private void deepToString(Term root, StringBuilder out, int indent) {
		out.append(root);
		out.append("\n");
		for (Term subterm : root.getSubterms()) {
			for (int i = 0; i <= indent; ++i) {
				out.append("---");
			}
			deepToString(subterm, out, indent + 1);
		}
	}

	public String deepToString(Term root) {
		StringBuilder out = new StringBuilder();
		deepToString(root, out, 0);
		return out.toString();
	}
}
