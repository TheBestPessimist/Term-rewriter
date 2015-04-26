package tbp.termrewriter.language;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import tbp.termrewriter.terms.FunctionSymbol;
import tbp.termrewriter.terms.VariableSymbol;

/**
 * Generate language based on input received from JSON file
 * 
 * @author alex
 *
 */
public class LanguageReader {

    private File languageFile;
    private Language language;

    public LanguageReader(File languageFile) {
        this.languageFile = languageFile;
        language = new Language();
        generateLanguage();
    }

    private void generateLanguage() {
        try {
            FileReader reader = new FileReader(languageFile);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray outerArray = (JSONArray) jsonObject.get("functionSymbols");

            Iterator<?> it = outerArray.iterator();
            while (it.hasNext()) {
                JSONObject innerObj = (JSONObject) it.next();
                String symbol = (String) innerObj.get("symbol");
                int arity = Integer.parseInt(innerObj.get("arity").toString());
                language.addTerm(new FunctionSymbol(symbol, arity));
            }

            outerArray = (JSONArray) jsonObject.get("constants");
            it = outerArray.iterator();
            while (it.hasNext()) {
                JSONObject innerObj = (JSONObject) it.next();
                language.addTerm(new FunctionSymbol((String) innerObj.get("symbol"), 0));
            }
            outerArray = (JSONArray) jsonObject.get("variables");
            it = outerArray.iterator();
            while (it.hasNext()) {
                JSONObject innerObj = (JSONObject) it.next();
                language.addTerm(new VariableSymbol((String) innerObj.get("symbol")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Language readLanguage() {
        return language;
    }
}
