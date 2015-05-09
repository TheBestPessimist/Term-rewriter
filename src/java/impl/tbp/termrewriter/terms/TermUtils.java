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

    private void parseStringToTerm(String s, Term root) throws TermException {

        // the "parantheses"
        String[] functionParts = s.split("\\(", 2);

        // get rid of the last bracket
        if (functionParts.length > 1) {
            functionParts[1] = functionParts[1].substring(0, functionParts[1].length() - 1);
        }

        Term currentTerm = language.getTermBySymbol(functionParts[0]);

        if (currentTerm instanceof FunctionSymbol) {
            // handle FunctionSymbols
            FunctionSymbol currentFunctionSymbol = createFunctionSymbolFromTerm(currentTerm);
            // add this new function to the tree
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
                    throw new TermException("bad input string: different number of parameters in the inputString: \"" + functionParts[1] + "\" for "
                            + currentFunctionSymbol);
                }
            } else if (currentFunctionSymbol.getArity() > 0) {
                throw new TermException("bad input string: too many parameters in the inputString for functionSymbol " + currentFunctionSymbol);
            }
        } else {
            // handle variables
            VariableSymbol currentVariable;
            if (functionParts.length > 1) {
                // check good arity with regard to the remaining input string (a variable MUST NOT have any subterms)
                throw new TermException("bad input string: a variable should have no parameters in the inputString (variable name is: " + functionParts[0]
                        + ")");
            } else if (currentTerm instanceof VariableSymbol) {
                // handle Variables already added to the language
                currentVariable = createVariableFromTerm(currentTerm);
                // create a new instance of this variable
            } else {
                // handle variables not added to the language yet
                currentVariable = createVariable(functionParts[0]);
                getLanguage().addTerm(currentVariable);
            }
            ((FunctionSymbol) root).getSubterms().add(currentVariable);
            currentVariable.setParent(root);
        }
    }

    public VariableSymbol createVariable(String symbol) {
        return new VariableSymbol(symbol);
    }

    private String[] getCurrentLevelVariablesFromInputString(String inputString) throws TermException {
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
        if (len > 0) {
            // i can have a constant like const(), which is correct but there is nothing to add
            variables.add(inputString.substring(lastUsedPositionForSplitting, len));
        }
        return variables.toArray(new String[0]);
    }

    private void checkIfStringIsWellFormed(String inputString) throws TermException {
        int openParantheses = 0;
        boolean haveFoundPharantheses = false;
        int len = inputString.length();
        for (int i = 0; i < len; ++i) {
            if (inputString.startsWith("(", i)) {
                haveFoundPharantheses = true;
                openParantheses++;
                continue;
            }
            if (inputString.startsWith(")", i)) {
                openParantheses--;
                continue;
            }

            try {
                if (inputString.startsWith(",", i)) {
                    if (inputString.startsWith(",", i + 1)) {
                        throw new TermException("bad input string: the input string \"" + inputString
                                + "\" is bad formed (the term delimiter is at a wrong position)");
                    }
                }
            } catch (TermException e) {
                throw e;
            } catch (Exception e) {
                // do nothing, i am accessing outside the string
            }
        }
        if (openParantheses != 0) {
            // this is a bad input string because the number of parantheses is wrong
            throw new TermException("bad input string: the input string \"" + inputString + "\" is bad formed (the number of parantheses is wrong)");
        }

        // this is used for input strings like this: "aaaa, av"
        if (!haveFoundPharantheses && inputString.contains(",")) {
            throw new TermException("bad input string: the input string \"" + inputString + "\" is bad formed (there shouldn't be any term delimiters)");
        }
        if (inputString.startsWith(",")) {
            throw new TermException("bad input string: the input string \"" + inputString + "\" is bad formed (the term delimiter is at a wrong position)");
        }
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
        checkIfStringIsWellFormed(inputString);

        Term root = new FunctionSymbol(VIRTUAL_FUNCTION_SYMBOL_FOR_PARSING, 1);

        parseStringToTerm(inputString, root);

        // get rid of the virtual functionsymbol
        root = getSubterm(root, new int[] { 0 });
        if (root instanceof FunctionSymbol) {
            ((FunctionSymbol) root).setParent(null);
        } else {
            ((VariableSymbol) root).setParent(null);
        }
        return root;
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

    //
    // public Term substitute(Term root, Term[] initialTerms, Term[] substitutionTerms) throws TermException {
    // if (root == null) {
    // throw new TermException("root shouldn't be null");
    // }
    // if (initialTerms.length != substitutionTerms.length) {
    // // TODO what should i do here? exception or null?
    // throw new TermException("initialTerm and substitutionTerms don't have the same length!");
    // }
    //
    // return null;
    // }

    /**
     * Equals but taking into account the structure of the Term (eg.: if the tree of the 2 functions is the same (same function calls in the same order,
     * disregarding the variable names), then (probably?) the 2 terms are deepEquals.
     * 
     * @param term
     * @param anotherTerm
     * @return true if the terms are deepEquals false otherwise.
     * @throws TermException
     */
    public boolean deepEquals(Term term, Term anotherTerm) throws TermException {
        Term[] termSignature = getAllTerms(term);
        Term[] anotherTermSignature = getAllTerms(anotherTerm);

        if (termSignature.length != anotherTermSignature.length) {
            return false;
        } else {
            int len = termSignature.length;
            for (int i = 0; i < len; i++) {
                // variables are ignored, since a variable can have any name. the only thing that matters is to have a variable at the exact same position in
                // both terms
                if (termSignature[i] instanceof VariableSymbol && anotherTermSignature[i] instanceof VariableSymbol) {
                    continue;
                }
                // only functions really matter when doing deep equals
                if (!termSignature[i].equals(anotherTermSignature[i])) {
                    return false;
                }
            }
            // if im am here, the terms and their respective subterms are equal respectively
            return true;
        }
    }

    /**
     * Gives a list with all the terms and subterms in the tree, by visiting the tree elements in pre-order. It gives the same list of terms for multiple calls
     * on the same Term (it is consistent!).
     * 
     * @param root
     * @return list of terms, starting with the root
     * @throws TermException
     */
    public Term[] getAllTerms(Term root) throws TermException {
        return getAllTermsRecursively(new ArrayList<Term>(), root);
    }

    private Term[] getAllTermsRecursively(ArrayList<Term> terms, Term root) throws TermException {
        terms.add(root);

        if (root instanceof FunctionSymbol) {
            List<Term> subterms = ((FunctionSymbol) root).getSubterms();
            for (Term subterm : subterms) {
                getAllTermsRecursively(terms, subterm);
            }
        } else if (root instanceof VariableSymbol) {
            // this is already handled above
        } else {
            throw new TermException("" + root + " is not a term");
        }
        return terms.toArray(new Term[0]);
    }
}
