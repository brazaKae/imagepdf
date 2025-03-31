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
//@CrossOrigin(origins = "*") // quando o front estiver em outro servidor
public class ImageToPdfController {

    private final ImageToPdfService imageToPdfService;

    public ImageToPdfController(ImageToPdfService imageToPdfService) {
        this.imageToPdfService = imageToPdfService;
    }


    @PostMapping("/converter")
    public ResponseEntity<byte[]> convertImageToPdf(@RequestParam("file") MultipartFile file) {
        try {
            byte[] pdfBytes = imageToPdfService.convertImageToPdf(file);

            // pegar o nome do arquivo
            String originalFilename = file.getOriginalFilename();
            if (originalFilename.isEmpty()) {
                originalFilename = "converted";
            } else {
                originalFilename = originalFilename.replaceAll("\\.[^.]+$", ""); // Remove a extensão original
            }
            String pdfFilename = originalFilename + ".pdf";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", pdfFilename);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}



















