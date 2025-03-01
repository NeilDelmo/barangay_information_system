/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barangay_information_system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Neil
 */
public class PDFGenerator {

    public static void generateBarangayClearance(Resident resident, String purpose) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                // Set up fonts and measurements
                PDType1Font headerFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font bodyFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

                float pageWidth = page.getMediaBox().getWidth();
                float margin = 60;  // Increased margin
                float yPosition = 700;
                float leading = 16f;
                int bodyFontSize = 12;

                // Header Section (centered)
                String[] headers = {
                    "REPUBLIC OF THE PHILIPPINES",
                    "MUNICIPALITY OF CALATAGAN, BATANGAS",
                    "BARANGAY BALIBAGO",
                    "OFFICE OF THE PUNONG BARANGAY"
                };

                // Write each header line separately to ensure proper positioning
                for (String header : headers) {
                    content.beginText();
                    content.setFont(headerFont, 14);
                    float textWidth = headerFont.getStringWidth(header) / 1000 * 14;
                    float xPosition = (pageWidth - textWidth) / 2;
                    content.newLineAtOffset(xPosition, yPosition);
                    content.showText(header);
                    content.endText();
                    yPosition -= leading * 1.5f;
                }

                // Horizontal line
                content.setLineWidth(1);
                content.moveTo(margin, yPosition);
                content.lineTo(pageWidth - margin, yPosition);
                content.stroke();
                yPosition -= leading * 2;

                // Document Title
                content.beginText();
                content.setFont(headerFont, 16);
                String title = "BARANGAY CLEARANCE";
                float titleWidth = headerFont.getStringWidth(title) / 1000 * 16;
                content.newLineAtOffset((pageWidth - titleWidth) / 2, yPosition);
                content.showText(title);
                content.endText();
                yPosition -= leading * 3;
                // Get current date components
                java.time.LocalDate currentDate = java.time.LocalDate.now();
                int day = currentDate.getDayOfMonth();
                String month = currentDate.format(java.time.format.DateTimeFormatter.ofPattern("MMMM"));
                int year = currentDate.getYear();

                // Main Content with text wrapping
                float currentX = margin;

                // Create the text content
                String certificationText = "TO WHOM IT MAY CONCERN:";

                String paragraph1 = "THIS IS TO CERTIFY that " + resident.getFullName() + ", of legal age, "
                        + resident.getCivilStatus().toLowerCase() + ", Filipino citizen, with postal address at "
                        + resident.getAddress() + ", has neither pending case nor derogatory record on file in this office.";

                String paragraph2 = "I further certify that " + resident.getHonorific() + " " + resident.getLastName()
                        + " is known to me to be a person of good moral character and reputation.";

                String paragraph3 = "Issued this " + day + " day of " + month + " " + year
                        + " at Barangay Balibago, Calatagan, Batangas upon the request of the interested party for "
                        + purpose + ".";

                String paragraph4 = "(For local purposes)";

                // Combine paragraphs with explicit spacing between them
                List<String> paragraphs = new ArrayList<>();
                paragraphs.add(certificationText);
                paragraphs.add(""); // Empty line
                paragraphs.add(paragraph1);
                paragraphs.add(""); // Empty line
                paragraphs.add(paragraph2);
                paragraphs.add(""); // Empty line
                paragraphs.add(paragraph3);
                paragraphs.add(""); // Empty line
                paragraphs.add(paragraph4);

                float maxWidth = pageWidth - 2 * margin;

                // Process each paragraph
                content.beginText();
                content.setFont(bodyFont, bodyFontSize);
                content.newLineAtOffset(currentX, yPosition);
                content.setLeading(leading);

                // Process each paragraph
                for (String paragraph : paragraphs) {
                    // If paragraph is empty, just add a new line
                    if (paragraph.isEmpty()) {
                        content.newLine();
                    } else {
                        // Wrap the paragraph text and display each line
                        for (String line : wrapText(paragraph, bodyFont, bodyFontSize, maxWidth)) {
                            content.showText(line);
                            content.newLine();
                        }
                    }
                }
                content.endText();

                // Calculate position for signature section
                // Get current y position after writing all paragraphs
                yPosition = yPosition - (paragraphs.size() * leading) - (wrapText(paragraph1, bodyFont, bodyFontSize, maxWidth).size() * leading)
                        - (wrapText(paragraph2, bodyFont, bodyFontSize, maxWidth).size() * leading)
                        - (wrapText(paragraph3, bodyFont, bodyFontSize, maxWidth).size() * leading)
                        - (leading * 2);  // Extra space

                // Signature Section 
                float signatureY = 180; // Fixed position from bottom of page

                // Left Signature (Applicant)
                content.beginText();
                content.setFont(bodyFont, bodyFontSize);
                content.newLineAtOffset(margin + 50, signatureY);
                content.showText("_________________________");
                content.newLineAtOffset(0, -leading);
                content.showText("Signature over Printed Name");
                content.endText();

                // Right Signature (Punong Barangay)
                content.beginText();
                content.setFont(bodyFont, bodyFontSize);
                content.newLineAtOffset(pageWidth - margin - 150, signatureY);
                content.showText("HON. JUAN M. DELA CRUZ");
                content.newLineAtOffset(0, -leading);
                content.showText("Punong Barangay");
                content.endText();
            }

            // Save the document
            String fileName = "Barangay_Clearance_" + resident.getLastName() + "_"
                    + java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE) + ".pdf";
            document.save(fileName);
            JOptionPane.showMessageDialog(null, "Document generated: " + fileName);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
            e.printStackTrace(); // For better debugging
        }
    }

    public static void generateCertificateOfIndigency(Resident resident, String purpose) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                PDType1Font headerFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font bodyFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

                float pageWidth = page.getMediaBox().getWidth();
                float margin = 60;
                float yPosition = 700;
                float leading = 16f;
                int bodyFontSize = 12;

                String[] headers = {
                    "REPUBLIC OF THE PHILIPPINES",
                    "MUNICIPALITY OF CALATAGAN, BATANGAS",
                    "BARANGAY BALIBAGO",
                    "OFFICE OF THE PUNONG BARANGAY"
                };

                for (String header : headers) {
                    content.beginText();
                    content.setFont(headerFont, 14);
                    float textWidth = headerFont.getStringWidth(header) / 1000 * 14;
                    content.newLineAtOffset((pageWidth - textWidth) / 2, yPosition);
                    content.showText(header);
                    content.endText();
                    yPosition -= leading * 1.5f;
                }

                content.setLineWidth(1);
                content.moveTo(margin, yPosition);
                content.lineTo(pageWidth - margin, yPosition);
                content.stroke();
                yPosition -= leading * 2;

                content.beginText();
                content.setFont(headerFont, 16);
                String title = "CERTIFICATE OF INDIGENCY";
                float titleWidth = headerFont.getStringWidth(title) / 1000 * 16;
                content.newLineAtOffset((pageWidth - titleWidth) / 2, yPosition);
                content.showText(title);
                content.endText();
                yPosition -= leading * 3;

                java.time.LocalDate currentDate = java.time.LocalDate.now();
                int day = currentDate.getDayOfMonth();
                String month = currentDate.format(java.time.format.DateTimeFormatter.ofPattern("MMMM"));
                int year = currentDate.getYear();

                String paragraph1 = "This is to certify that " + resident.getFullName() + ", "
                        + resident.getAge() + " years of age, " + resident.getCivilStatus().toUpperCase()
                        + ", permanently residing at " + resident.getAddress()
                        + ", belongs to the indigent families of this Barangay.";

                String paragraph2 = "This certification is issued upon the request of " + resident.getFullName()
                        + " for " + purpose + ".";

                String paragraph3 = "Issued this " + day + " day of " + month + " " + year
                        + " at Barangay Balibago, Calatagan, Batangas.";

                List<String> paragraphs = new ArrayList<>();
                paragraphs.add("TO WHOM IT MAY CONCERN:");
                paragraphs.add("");
                paragraphs.add(paragraph1);
                paragraphs.add("");
                paragraphs.add(paragraph2);
                paragraphs.add("");
                paragraphs.add(paragraph3);
                paragraphs.add("");

                float maxWidth = pageWidth - 2 * margin;
                content.beginText();
                content.setFont(bodyFont, bodyFontSize);
                content.newLineAtOffset(margin, yPosition);
                content.setLeading(leading);

                for (String paragraph : paragraphs) {
                    if (paragraph.isEmpty()) {
                        content.newLine();
                    } else {
                        for (String line : wrapText(paragraph, bodyFont, bodyFontSize, maxWidth)) {
                            content.showText(line);
                            content.newLine();
                        }
                    }
                }
                content.endText();

                float signatureY = 180;

                content.beginText();
                content.setFont(bodyFont, bodyFontSize);
                content.newLineAtOffset(margin + 50, signatureY);
                content.showText("_________________________");
                content.newLineAtOffset(0, -leading);
                content.showText("Signature over Printed Name");
                content.endText();

                content.beginText();
                content.setFont(bodyFont, bodyFontSize);
                content.newLineAtOffset(pageWidth - margin - 150, signatureY);
                content.showText("HON. JUAN M. DELA CRUZ");
                content.newLineAtOffset(0, -leading);
                content.showText("Punong Barangay");
                content.endText();
            }

            String fileName = "Indigency_Certificate_" + resident.getLastName() + ".pdf";
            document.save(fileName);
            JOptionPane.showMessageDialog(null, "Document generated: " + fileName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<String> wrapText(String text, PDType1Font font, int fontSize, float maxWidth) throws IOException {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            // Skip if the word is empty
            if (word.isEmpty()) {
                continue;
            }

            String testLine = currentLine.toString();
            if (testLine.length() > 0) {
                testLine += " ";
            }
            testLine += word;

            float testWidth = font.getStringWidth(testLine) / 1000 * fontSize;

            if (testWidth > maxWidth && currentLine.length() > 0) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            } else {
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            }
        }

        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        return lines;
    }
}

// Resident class with additional helper methods
class Resident {

    private String firstName;
    private String lastName;
    private String address;
    private String civilStatus;
    private String birthDate; // Store birthdate as String in "yyyy-MM-dd" format

    public Resident(String firstName, String lastName, String address, String civilStatus, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.civilStatus = civilStatus;
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getHonorific() {
        return civilStatus.equalsIgnoreCase("married") ? "Mr./Mrs." : "Mr./Ms.";
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public int getAge() {
        LocalDate birthDateFormatted = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Period.between(birthDateFormatted, LocalDate.now()).getYears();
    }

}
