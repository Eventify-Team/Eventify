package com.evetify.eventify.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.DocumentException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class TicketPdfService {

    private final TemplateEngine templateEngine;

    public TicketPdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public File generateTicket(String name, String surname, String nameEvent, String location,
                               String description, String date, String time, double fee, Long attendanceId) {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("surname", surname);
        context.setVariable("event", nameEvent);
        context.setVariable("location", location);
        context.setVariable("description", description);
        context.setVariable("date", date);
        context.setVariable("time", time);
        context.setVariable("fee", fee);

        try {
            // Load images from resources
            String logoPath = new ClassPathResource("static/images/logo.png").getFile().toURI().toString();
            String eventImagePath = new ClassPathResource("static/images/event.jpg").getFile().toURI().toString();

            context.setVariable("logo", logoPath);
            context.setVariable("eventImage", eventImagePath);

            // Generate QR code
            String qrText = "https://eventify.com/validate/" + attendanceId;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, 200, 200);
            File qrFile = File.createTempFile("qr-" + attendanceId, ".png");
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrFile.toPath());
            context.setVariable("qrCode", qrFile.toURI().toString());

            // Render HTML to PDF
            String htmlContent = templateEngine.process("ticket", context);
            File tempFile = File.createTempFile("ticket-" + attendanceId, ".pdf");

            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(htmlContent, new File(".").toURI().toString());
                renderer.layout();
                renderer.createPDF(fos);
            }

            // Save to Downloads
            String downloadsPath = System.getProperty("user.home") + File.separator + "Downloads";
            File downloadsDir = new File(downloadsPath);

            if (!downloadsDir.exists()) {
                boolean created = downloadsDir.mkdirs();
                if (!created) {
                    System.out.println("❌ Failed to create Downloads folder.");
                }
            }

            if (downloadsDir.exists() && downloadsDir.isDirectory()) {
                File destFile = new File(downloadsDir, String.format("ticket-attendance-%d.pdf", attendanceId));
                Files.copy(tempFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("📥 File saved to Downloads: " + destFile.getAbsolutePath());
            }

            return tempFile;

        } catch (IOException | DocumentException | WriterException e) {
            throw new RuntimeException("Error generating PDF: " + e.getMessage(), e);
        }
    }
}