import java.awt.event.ActionListener;

public interface ViewInterface {

    public void fillComboBox(String Item);

    public int getFromIndex();

    public int getToIndex();

    public double getAmount();

    public void addConvertListener(ActionListener click);

    public void setRateTextArea(String rate);

    public void setAfterConversionTextArea(String result);
}
