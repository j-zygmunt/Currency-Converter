import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class SAXHandler extends DefaultHandler {

    private ArrayList<Currency> currencyList = null;
    private Currency currency = null;
    private boolean bName = false, bCode = false, bExchangeRate = false, bRatio = false;

    public ArrayList<Currency> getCurrencyList() {
        return currencyList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        switch (qName) {
            case "pozycja":
                currency = new Currency();
                if (currencyList == null)
                    currencyList = new ArrayList<>();
                break;
            case "nazwa_waluty":
                bName = true;
                break;
            case "przelicznik":
                bRatio = true;
                break;
            case "kod_waluty":
                bCode = true;
                break;
            case "kurs_sredni":
                bExchangeRate = true;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if (qName.equals("pozycja")) {
            currencyList.add(currency);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length){

        if (bName) {
            currency.setCurrencyName(new String(ch, start, length));
            bName = false;
        }
        if (bRatio) {
            currency.setCurrencyRatio(Integer.parseInt(new String(ch, start, length)));
            bRatio = false;
        }
        if (bCode) {
            currency.setCurrencyCode(new String(ch, start, length));
            bCode = false;
        }
        if (bExchangeRate) {
            currency.setCurrencyExchangeRate(Double.parseDouble((new String(ch, start, length).replaceAll(",","."))));
            bExchangeRate = false;
        }
    }
}