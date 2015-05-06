package tbp.termrewriter.terms;

import java.util.ArrayList;
import java.util.List;

import tbp.termrewriter.exceptions.TermException;
import tbp.termrewriter.language.Language;
import tbp.termrewriter.term.Term;

public class TermUtils {

    private static final String VIRTUAL_FUNCTION_SYMBOL_FOR_PARSING = "virtualFunctionSymbolForParsing";
    private Language language;

    public TermUtils() {
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

    /**
     * Creates a new function symbol which has the same symbol and arity as the term.
     * 
     * @param term
     * @return the newly created function symbol, or null if the term is not a function symbol
     */
    public FunctionSymbol createFunctionSymbolFromTerm(Term term) {
        if (term instanceof FunctionSymbol) {
            return new FunctionSymbol(term.getSymbol(), ((FunctionSymbol) term).getArity());
        }
        // not a functionSymbol Exception
        System.out.println("the term " + term + " is not a functionSymbol");
        return null;
    }

    /**
     * Creates a new variable which has the same symbol as the term.
     * 
     * @param term
     * @return the newly created variable, or null if the term is not a variable
     */
    private VariableSymbol createVariableFromTerm(Term term) {
        if (term instanceof VariableSymbol) {
            return new VariableSymbol(term.getSymbol());
        }
        // not a VariableException?
        System.out.println("the term " + term + " is not a variable");
        return null;
    }

    public void parseStringToTerm(String s, Term root) throws TermException {

        // the "parantheses"
        String[] functionParts = s.split("\\(", 2);

        // get rid of the last bracket
        if (functionParts.length > 1) {
            functionParts[1] = functionParts[1].substring(0, functionParts[1].length() - 1);
        }

        Term currentTerm = language.getTermBySymbol(functionParts[0]);

        if (currentTerm != null) {
            if (currentTerm instanceof FunctionSymbol) {
                // handle FunctionSymbols
                FunctionSymbol currentFunctionSymbol = createFunctionSymbolFromTerm(currentTerm);
                // add this new function
                ((FunctionSymbol) root).getSubterms().add(currentFunctionSymbol);
                currentFunctionSymbol.setParent(root);

                if (functionParts.length > 1) {
                    // check good arity with regard to the remaining input variables
                    String variables[] = getCurrentLevelVariablesFromInputString(functionParts[1]);
                    if (variables.length == currentFunctionSymbol.getArity()) {
                        for (String variable : variables) {
                            parseStringToTerm(variable, currentFunctionSymbol);
                        }
                    } else {
                        throw new TermException("bad input string: not enough parameters in the inputString for functionSymbol " + currentFunctionSymbol);
                    }
                } else if (currentFunctionSymbol.getArity() > 0) {
                    throw new TermException("bad input string: too many parameters in the inputString for functionSymbol " + currentFunctionSymbol);
                }
            } else if (currentTerm instanceof VariableSymbol) {
                // handle Variables
                VariableSymbol currentVariable = createVariableFromTerm(currentTerm);
                // add this new variable
                ((FunctionSymbol) root).getSubterms().add(currentVariable);
                currentVariable.setParent(root);

                // check good arity with regard to the remaining input variables
                if (functionParts.length > 1) {
                    throw new TermException("bad input string: a variable should have no parameters in the inputString " + currentVariable);
                }
            }
        } else {
            throw new TermException("term not part of the language: " + functionParts[0]);
        }
    }

    private String[] getCurrentLevelVariablesFromInputString(String inputString) {
        List<String> variables = new ArrayList<String>();
        int len = inputString.length();
        int openParantheses = 0;
        int lastUsedPositionForSplitting = 0;
        for (int i = 0; i < len; ++i) {
            if (openParantheses == 0 && inputString.startsWith(",", i)) {
                variables.add(inputString.substring(lastUsedPositionForSplitting, i));
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

    private void deepToString(Term root, StringBuilder out, int indent) throws TermException {
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
        } else if (root instanceof VariableSymbol) {
            // this is already handled above
            throw new TermException("i shouldn't be here!");
        } else {
            throw new TermException("" + root + " is not a term");
        }
    }

    public String deepToString(Term root) throws TermException {
        StringBuilder out = new StringBuilder();
        deepToString(root, out, 0);
        return out.toString();
    }

    /**
     * @param inputString
     * @throws TermException
     * @throws TermNotPartOfTheLanguageException
     */
    public Term parseStringToTerm(String inputString) throws TermException {
        Term root = new FunctionSymbol(VIRTUAL_FUNCTION_SYMBOL_FOR_PARSING, 1);

        parseStringToTerm(inputString, root);

        return getSubterm(root, new int[] { 0 });
    }

    /**
     * Get the subterm in the tree at the position specified in the array. The numbers in the array signify what subterm of the current term will be returned,
     * recursively.
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

    private Term getSubtermRecursively(Term root, int[] inputSubtermPositions, int currentPosition) {
        if (root instanceof FunctionSymbol) {
            FunctionSymbol currentFunctionSymbol = (FunctionSymbol) root;
            int subtermsSize = currentFunctionSymbol.getSubterms().size();
            if (subtermsSize == 0) {
                // if i want to access subterms when i can't
                if (currentPosition != inputSubtermPositions.length - 1) {
                    return null;
                }
                return currentFunctionSymbol;
            }
            // check if i have valid position and array size
            if (subtermsSize > inputSubtermPositions[currentPosition] && inputSubtermPositions[currentPosition] >= 0) {
                if (currentPosition == inputSubtermPositions.length - 1) {
                    return currentFunctionSymbol.getSubterms().get(inputSubtermPositions[currentPosition]);
                } else {
                    return getSubtermRecursively(currentFunctionSymbol.getSubterms().get(inputSubtermPositions[currentPosition]), inputSubtermPositions,
                            ++currentPosition);
                }
            } else {
                // TODO i can't address outside of any of the 2 arrays. exception?
                return null;
            }
        } else if (root instanceof VariableSymbol) {
            VariableSymbol currentVariable = (VariableSymbol) root;
            if (currentPosition == inputSubtermPositions.length - 1) {
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
