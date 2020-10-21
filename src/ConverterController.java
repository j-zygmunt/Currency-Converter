public class ConverterController {

    private ConverterView theView;
    private ConverterModel theModel;

    public ConverterController(ConverterView theView, ConverterModel theModel) {

        this.theView = theView;
        this.theModel = theModel;
        String url = "https://www.nbp.pl/kursy/xml/lasta.xml";

        this.theView.addConvertListener(e -> {
            int from = theView.getFromIndex();
            int to = theView.getToIndex();
            try {
                double result = theModel.convert(from, to, theView.getAmount());
                theView.setAfterConversionTextArea(String.format("%.4f", result).replaceAll(",", "."));
                theView.setRateTextArea(String.format("%.4f", result / theView.getAmount()).replaceAll(",", "."));
            } catch (NumberFormatException error) {
                theView.setAfterConversionTextArea("");
                theView.setRateTextArea("");
            }
        });

        try {
            theModel.getData(url);
            if (!this.theModel.getCurrencyList().isEmpty()) {
                for (Currency currency : this.theModel.getCurrencyList()) {
                    theView.fillComboBox(currency.getCurrencyName());
                }
            }
        } catch (DownloadErrorException | SAXParserException e) {
            this.theView.showErrorPopup(e.getMessage());
        }
    }
}
