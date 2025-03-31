package br.com.kae.imagepdf.service;

import br.com.kae.imagepdf.utils.PdfGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageToPdfService {
    public byte[] convertImageToPdf(MultipartFile file) throws Exception {
        return PdfGenerator.convertImageToPdf(file);
    }
}
