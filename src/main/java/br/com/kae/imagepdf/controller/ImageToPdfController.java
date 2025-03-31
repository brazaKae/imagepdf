package br.com.kae.imagepdf.controller;

import br.com.kae.imagepdf.service.ImageToPdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*") // quando o front estiver em outro servidor
public class ImageToPdfController {

    private final ImageToPdfService imageToPdfService;

    @PostMapping("/converter")
    public ResponseEntity<byte[]> convertImageToPdf(@RequestParam("file") MultipartFile file) {
        try {
            byte[] pdfBytes = imageToPdfService.convertImageToPdf(file);

            // pegar o nome do arquivo
            String originalFileName = file.getOriginalFilename();
            String pdfFileName = (originalFileName.isEmpty() ? originalFileName.replaceAll("\\.[^.]+$", "") : "converted") + ".pdf";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", pdfFileName);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}



















