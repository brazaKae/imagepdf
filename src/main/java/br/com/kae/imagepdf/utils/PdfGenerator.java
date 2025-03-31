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


        byte[] imageBytes = file.getBytes();
        if (imageBytes.length == 0) {
            throw new RuntimeException("Erro: O arquivo de imagem está vazio.");
        }
        Image image = Image.getInstance(file.getBytes());
        document.setPageSize(new com.itextpdf.text.Rectangle(image.getWidth(), image.getHeight()));
        image.setAbsolutePosition(0, 0);
        document.open();
        document.add(image);
        document.close();

        return outputStream.toByteArray();
    }
}
