package br.com.kae.imagepdf.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;

public class PdfGenerator {
    public static byte[] convertImageToPdf(MultipartFile file) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Image image = Image.getInstance(file.getBytes());
        image.scaleToFit(PageSize.A4.getWidth() - 50, PageSize.A4.getHeight() - 50);
        document.add(image);
        document.close();

        return outputStream.toByteArray();
    }
}
