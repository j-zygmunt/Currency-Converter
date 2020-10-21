public interface ModelInterface {
    public void getData(String url) throws DownloadErrorException, SAXParserException;

    public double convert(int from, int to, double amount);
}
