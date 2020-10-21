import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Downloader {

    public void download(String urlAddress, String path) throws DownloadErrorException {

        try {
            URL url = new URL(urlAddress);
            ReadableByteChannel channel = Channels.newChannel(url.openStream());
            FileOutputStream output = new FileOutputStream(path);
            output.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
            output.close();
            channel.close();
        } catch (IOException e) {
            throw new DownloadErrorException("An error occurred during download", e);
        }
    }
}
