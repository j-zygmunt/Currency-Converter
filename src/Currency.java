public class Currency {
    private String currencyName;
    private String currencyCode;
    private double currencyExchangeRate;
    private int currencyRatio;

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getCurrencyExchangeRate() {
        return currencyExchangeRate;
    }

    public int getCurrencyRatio() {
        return currencyRatio;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setCurrencyExchangeRate(double currencyExchangeRate) {
        this.currencyExchangeRate = currencyExchangeRate;
    }

    public void setCurrencyRatio(int currencyRatio) {
        this.currencyRatio = currencyRatio;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyName='" + currencyName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", currencyExchangeRate=" + currencyExchangeRate +
                ", currencyRatio=" + currencyRatio +
                '}';
    }
}
