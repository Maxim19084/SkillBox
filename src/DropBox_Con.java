import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Scanner;

public class DropBox_Con {
    private static final String ACCESS_TOKEN = "zCABGQX_5PUAAAAAAAAAAbu-wjvmFjoSx50oySL9d6NwliVu2JIGKNhQADBjDRUl";

    public static void main(String args[]) throws DbxException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
//        FullAccount account = client.users().getCurrentAccount();
//        System.out.println(account.getName().getDisplayName());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();
//        System.out.println(formatter.format(date));
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = robot.createScreenCapture(screen);
//        System.out.println(image.getWidth() + "x" + image.getHeight());
        //ImageIO.write(image, "png", new File("C:/1/" + formatter.format(date) + ".png"));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        // Upload to Dropbox
        try (InputStream in = new ByteArrayInputStream(os.toByteArray())) {
            FileMetadata metadata = client.files().uploadBuilder("/"+formatter.format(date)+".png")
                    .uploadAndFinish(in);
        }        catch (Exception ex) {ex.printStackTrace();}

    }
}