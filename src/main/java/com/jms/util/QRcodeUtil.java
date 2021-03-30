package com.jms.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

/**
 *
 * @author Jerry Mouse Software.
 */
public class QRcodeUtil {
    /**
     * generate QRcode
     *
     * @param text     code generation rules
     * @param width    
     * @param height   
     */
    public static String getQRCodeImage(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //generate QR code
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        //transfer type of QR code to Base64
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
        byte[] bytes = baos.toByteArray();
        String encode = Base64.getEncoder().encodeToString(bytes);
        return encode;
    }



//    public static void main(String[] args) {
//        try {
//            QRcodeUtil qRcodeUtil = new QRcodeUtil();
//            String dd = qRcodeUtil.getQRCodeImage("Jerry Mouse est le meilleur");
//            System.out.println(dd);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }



    /**
     * Remove the underscore from the UUID that comes with String and return
     *
     * @return String UUID
     */
    public String getUUID() {
        String s = UUID.randomUUID().toString();
        //remove “-”
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

}
