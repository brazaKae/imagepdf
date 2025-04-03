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
    public ResponseEntity<?> convertImageToPdf(@RequestParam("file") MultipartFile file) {
        try {
            // Validação simples
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Por favor, envie um arquivo de imagem");
            }

            byte[] pdfBytes = imageToPdfService.convertImageToPdf(file);

            String pdfFilename = file.getOriginalFilename() == null ?
                    "converted.pdf" :
                    file.getOriginalFilename().replaceAll("\\.[^.]+$", "") + ".pdf";

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfFilename + "\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Erro ao converter imagem para PDF: " + e.getMessage());
        }
    }
    }











