import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread
{
    private final DbxClientV2 client;
    private final BufferedImage image;
    public MyThread(DbxClientV2 client, BufferedImage image)
    {
        this.client = client;
        this.image = image;
    }
    @Override
    public void run()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Upload to Dropbox
        try (InputStream in = new ByteArrayInputStream(os.toByteArray())) {
            client.files().uploadBuilder("/"+formatter.format(date)+".png")
                    .uploadAndFinish(in);
        }        catch (Exception ex) {ex.printStackTrace();}
    }
}
