package com.jayklef.qrgenerator.utility;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jayklef.qrgenerator.model.Student;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {

    public static void generateQrCode(Student student) throws WriterException, IOException {
        String qrCodePath = "/Users/jerrycodes/Desktop/qrGenerator/QRCode";
        String qrCodeName = qrCodePath + student.getFirstName() + student.getId()+ "-QRCODE.png";

        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "ID: "+ student.getId() + "\n"+
                        "Firstname: " + student.getFirstName() + "\n"+
                        "Lastname: " + student.getLastName() + "\n" +
                        "Email: " + student.getEmail() + "\n" +
                        "PhoneNo: " + student.getPhoneNo(), BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
