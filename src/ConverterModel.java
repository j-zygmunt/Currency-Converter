import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConverterModel implements ModelInterface{

    private ArrayList<Currency> currencyList = null;

    public double convert(int from, int to, double amount) {
        return (currencyList.get(from).getCurrencyExchangeRate() * amount * currencyList.get(to).getCurrencyRatio()) /
                (currencyList.get(from).getCurrencyRatio() * currencyList.get(to).getCurrencyExchangeRate());
    }

    public void getData(String url) throws DownloadErrorException, SAXParserException {

        Downloader downloader = new Downloader();
        String path = "./resources/currencies.xml";
        downloader.download(url, path);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SAXHandler saxHandler = new SAXHandler();
            saxParser.parse(new File(path), saxHandler);
            setCurrencyList(saxHandler.getCurrencyList());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new SAXParserException("An error occurred during the parsing", e);
        }
        Currency pln = new Currency();
        pln.setCurrencyName("złoty polski");
        pln.setCurrencyCode("PLN");
        pln.setCurrencyExchangeRate(1);
        pln.setCurrencyRatio(1);
        this.currencyList.add(pln);

    }

    public void setCurrencyList(ArrayList<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public ArrayList<Currency> getCurrencyList() {
        return currencyList;
    }

}
