package br.com.kae.imagepdf.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfGenerator {
    public static byte[] convertImageToPdf(MultipartFile file) throws IOException, DocumentException {
        // Validação básica do arquivo
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("O arquivo de imagem não pode ser nulo ou vazio");
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Carrega a imagem
            Image image = Image.getInstance(file.getBytes());

            // Ajusta o tamanho do documento para o tamanho da imagem
            Rectangle documentSize = new Rectangle(image.getWidth(), image.getHeight());
            Document document = new Document(documentSize, 0, 0, 0, 0);

            // Configura o PDF writer
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            // Abre o documento
            document.open();

            // Adiciona a imagem
            document.add(image);

            // Fecha o documento (isso finaliza a escrita do PDF)
            document.close();

            // Fecha o writer
            writer.close();

            return outputStream.toByteArray();
        }
    }
}