package com.example.maintenance.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class ReportController {

    @GetMapping("/report")
    public void downloadPdf(
            @RequestParam int prediction,
            @RequestParam double probability,
            @RequestParam String risk,
            @RequestParam double cycles,
            @RequestParam double temp,
            @RequestParam double pressure,
            @RequestParam double vibration,
            @RequestParam double fuel,
            @RequestParam int failures,
            HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");

        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);

        String status = (prediction == 1) ? "⚠ FAILURE RISK DETECTED" : "✅ HEALTHY";

        // ===== Title =====
        doc.add(new Paragraph("Predictive Maintenance Report")
                .setBold()
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER));

        doc.add(new Paragraph("\n"));

        // ===== Status =====
        doc.add(new Paragraph("Status: " + status)
                .setBold()
                .setFontSize(14));

        doc.add(new Paragraph("Risk Level: " + risk));
        doc.add(new Paragraph("Failure Probability: " + String.format("%.2f%%", probability * 100)));

        doc.add(new Paragraph("Generated at: " + LocalDateTime.now()));

        doc.add(new Paragraph("\n"));

        // ===== Input Table =====
        Table table = new Table(2);

        table.addCell("Parameter");
        table.addCell("Value");

        table.addCell("Cycles Since Maintenance");
        table.addCell(String.valueOf(cycles));

        table.addCell("Avg Turbine Temp (°C)");
        table.addCell(String.valueOf(temp));

        table.addCell("Compressor Pressure Ratio");
        table.addCell(String.valueOf(pressure));

        table.addCell("Vibration Level");
        table.addCell(String.valueOf(vibration));

        table.addCell("Fuel Flow Variation");
        table.addCell(String.valueOf(fuel));

        table.addCell("Previous Failures");
        table.addCell(String.valueOf(failures));

        doc.add(new Paragraph("Input Parameters").setBold());
        doc.add(table);

        doc.add(new Paragraph("\n"));

        // ===== Recommendation =====
        String recommendation;

        if (risk.equalsIgnoreCase("LOW")) {
            recommendation = "Engine is operating normally. Continue standard maintenance schedule.";
        } else if (risk.equalsIgnoreCase("MEDIUM")) {
            recommendation = "Moderate risk detected. Schedule inspection soon and monitor closely.";
        } else {
            recommendation = "High failure risk. Immediate maintenance recommended.";
        }

        doc.add(new Paragraph("Recommendation").setBold());
        doc.add(new Paragraph(recommendation));

        doc.close();
    }
}