import java.io.File;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

class QRGenerator {

    public static void generateQR(String data) {

        try {
            BitMatrix matrix = new MultiFormatWriter()
                    .encode(data, BarcodeFormat.QR_CODE, 300, 300);

            File file = new File("parkingQR.png");
            MatrixToImageWriter.writeToFile(matrix, "png", file);

            System.out.println("QR Code Generated!");

        } catch (Exception e) {
            System.out.println("QR Error: " + e.getMessage());
        }
    }
}
//
C:\Users\apoor\OneDrive\Documents\project>java -cp ".;core-3.5.1.jar;javase-3.5.1.jar" Main