public class CurrencyConverter {

    public static void main(String[] args) {
        ConverterView view = new ConverterView();
        ConverterModel model = new ConverterModel();
        ConverterController controller = new ConverterController(view, model);
    }
}
