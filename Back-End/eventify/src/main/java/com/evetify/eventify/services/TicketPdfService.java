package com.evetify.eventify.services;

import com.lowagie.text.DocumentException;
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

        String htmlContent = templateEngine.process("ticket", context);

        try {
            File tempFile = File.createTempFile("ticket-" + attendanceId, ".pdf");

            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(htmlContent);
                renderer.layout();
                renderer.createPDF(fos);
            }

            System.out.println("✅ Το PDF δημιουργήθηκε προσωρινά στο: " + tempFile.getAbsolutePath());

            String downloadsPath = System.getProperty("user.home") + File.separator + "Downloads";
            File downloadsDir = new File(downloadsPath);

            // Αν δεν υπάρχει ο φάκελος, προσπαθούμε να τον δημιουργήσουμε
            if (!downloadsDir.exists()) {
                boolean created = downloadsDir.mkdirs();
                if (!created) {
                    System.out.println("❌ Αποτυχία δημιουργίας φακέλου Λήψεις.");
                }
            }

            if (downloadsDir.exists() && downloadsDir.isDirectory()) {
                String finalFilename = String.format("ticket-attendance-%d.pdf", attendanceId);
                File destFile = new File(downloadsDir, finalFilename);

                Files.copy(tempFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("📥 Το αρχείο αποθηκεύτηκε στον φάκελο Λήψεις: " + destFile.getAbsolutePath());
            } else {
                System.out.println("⚠️ Δεν βρέθηκε φάκελος Λήψεις.");
            }

            return tempFile;

        } catch (IOException | DocumentException e) {
            throw new RuntimeException("Σφάλμα κατά τη δημιουργία του PDF: " + e.getMessage(), e);
        }
    }
}
