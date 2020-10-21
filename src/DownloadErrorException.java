public class DownloadErrorException extends Exception {
    public DownloadErrorException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
