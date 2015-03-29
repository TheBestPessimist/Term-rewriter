package tbp.termrewriter.language;

import java.util.List;
import java.util.Set;

import tbp.termrewriter.terms.FunctionSymbol;
import tbp.termrewriter.terms.Variable;

/**
 * The language which will be used to create the Terms.
 * 
 * @author CristianViorel
 *
 */
public class Language {

	private List<FunctionSymbol> functionSymbols;
	private List<Variable> variables;

	public Language(List<FunctionSymbol> functionSymbols,
			List<Variable> variables) {
		this.functionSymbols = functionSymbols;
		this.variables = variables;
	}

	public List<FunctionSymbol> getFunctionSymbols() {
		return functionSymbols;
	}

	public void setFunctionSymbols(List<FunctionSymbol> functionSymbols) {
		this.functionSymbols = functionSymbols;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}
}
