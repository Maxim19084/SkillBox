
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

    public class DropBox {

        public static void main(String[] args) throws AWTException, IOException {

            System.out.println("Кофе-машина");


            int moneyAmount;
            try (Scanner rub = new Scanner(System.in)) {
                System.out.println("Что в карманцах?: ");

                moneyAmount = rub.nextInt();
            }
            int[] drinkPrices = {150, 80, 20, 50};
            String[] drinkNames = {"капучино", "американо", "вода", "молоко"};

            boolean canBuyAnything = false;

            for(int i = 0; i < 4; i++) {
                if (drinkPrices[i] <= moneyAmount) {
                    System.out.println("Вы можете купить " + drinkNames[i]);
                    canBuyAnything = true;
                }
                else
                    System.out.println("Докиньте " + (drinkPrices[i] - moneyAmount) + " на " + drinkNames[i]);
            }
            if(!canBuyAnything)
                System.out.println("Недостаточно средств :( поступайте в Skillbox на курс Java, сообщество поддержит!))");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Date date = new Date();
            System.out.println(formatter.format(date));

        Robot robot = new Robot();
        Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = robot.createScreenCapture(screen);
        System.out.println(image.getWidth() + "x" + image.getHeight());
        ImageIO.write(image, "png", new File("C:/1/" + formatter.format(date) + ".png"));
        }








}
