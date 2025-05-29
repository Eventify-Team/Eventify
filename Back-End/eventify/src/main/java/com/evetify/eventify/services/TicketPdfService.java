package com.evetify.eventify.services;

import com.lowagie.text.DocumentException;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.util.ImageUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Date;

import static java.lang.System.in;
import static java.nio.file.Files.readAllBytes;
import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Service
public class TicketPdfService {
    //print ticket in pdf
    private final TemplateEngine templateEngine;

    public TicketPdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public File generateTicket(String name, String surname, String nameEvent, String location, String description, String date, String time, double fee, Long attendanceId) {
        // Προετοιμασία HTML περιεχομένου μέσω Thymeleaf
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
            // Δημιουργία προσωρινού αρχείου PDF
            String filenamePrefix = "ticket-" + attendanceId;
            File tempFile = File.createTempFile(filenamePrefix, ".pdf");

            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(htmlContent);
                renderer.layout();
                renderer.createPDF(fos);
            }

            System.out.println("✅ Το PDF δημιουργήθηκε προσωρινά στο: " + tempFile.getAbsolutePath());
            String downloadsPath = System.getProperty("user.home") + File.separator + "Downloads";
            File downloadsDir = new File(downloadsPath);

            if (downloadsDir.exists() && downloadsDir.isDirectory()) {
                // Χρήση του attendanceId για μοναδική ονομασία
                String finalFilename = String.format("ticket-attendance-%d.pdf", attendanceId);
                File destFile = new File(downloadsDir, finalFilename);

                Files.copy(tempFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Το αρχείο αποθηκεύτηκε ως: " + destFile.getAbsolutePath());
            } else {
                System.out.println("Δεν βρέθηκε φάκελος Λήψεις.");
            }

            return tempFile;

        } catch (IOException | DocumentException e) {
            throw new RuntimeException("Σφάλμα κατά τη δημιουργία του PDF", e);
        }
    }

}
