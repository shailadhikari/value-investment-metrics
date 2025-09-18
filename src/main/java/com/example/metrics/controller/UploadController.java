package com.example.metrics.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping("/filing")
    public ResponseEntity<String> uploadFiling(@RequestParam("file") MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();

        // Simplified demo: just return first 500 chars
        String preview = text.substring(0, Math.min(500, text.length()));
        return ResponseEntity.ok("Extracted text preview:\n" + preview);
    }
}
