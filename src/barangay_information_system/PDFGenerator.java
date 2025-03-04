/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barangay_information_system;

import static java.awt.font.TextHitInfo.leading;
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
import java.util.Random;

/**
 *
 * @author Neil
 */
public class PDFGenerator {

    private static final float LEADING = 16f;

    public static void generateBarangayClearance(Resident resident, String purpose, String pdfPath) {
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
            String fileName = pdfPath;
            document.save(fileName);
            JOptionPane.showMessageDialog(null, "Document generated: " + fileName);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
            e.printStackTrace(); // For better debugging
        }
    }

    public static void generateCertificateOfIndigency(Resident resident, String purpose, String pdfPath) {
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

            String fileName = pdfPath;
            document.save(fileName);
            JOptionPane.showMessageDialog(null, "Document generated: " + fileName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void generateBusinessPermit(Resident resident, String businessName, String businessAddress, String pdfPath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                // Reuse header setup from other documents
                PDType1Font headerFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font bodyFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

                float pageWidth = page.getMediaBox().getWidth();
                float margin = 60;
                float yPosition = 700;
                float leading = 16f;

                // Header Section
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

                // Title
                content.beginText();
                content.setFont(headerFont, 16);
                String title = "BUSINESS PERMIT";
                float titleWidth = headerFont.getStringWidth(title) / 1000 * 16;
                content.newLineAtOffset((pageWidth - titleWidth) / 2, yPosition - 40);
                content.showText(title);
                content.endText();
                yPosition -= 100;

                // Business Details
                LocalDate issueDate = LocalDate.now();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

                String contentText = "This certifies that\n\n"
                        + businessName.toUpperCase() + "\n\n"
                        + businessAddress.toUpperCase() + "\n\n"
                        + "is a business name registered in this office pursuant to the provisions of Act 3883.\n\n"
                        + "This certificate issued to\n\n"
                        + resident.getFullName().toUpperCase() + "\n\n"
                        + "is valid from " + issueDate.format(dateFormatter) + " to "
                        + issueDate.plusYears(5).format(dateFormatter) + ".\n\n"
                        + "Certificate No. " + String.format("%08d", new Random().nextInt(100000000)) + "\n\n"
                        + "Issued on " + issueDate.format(dateFormatter) + " in the Philippines.";

                // Text wrapping
                content.beginText();
                content.setFont(bodyFont, 12);
                content.newLineAtOffset(margin, yPosition);
                content.setLeading(leading);

                for (String line : contentText.split("\n")) {
                    content.showText(line);
                    content.newLine();
                }
                content.endText();

                // Signatures
                content.beginText();
                content.setFont(headerFont, 12);
                content.newLineAtOffset(pageWidth - margin - 150, 120);
                content.showText("HON. JUAN M. DELA CRUZ");
                content.newLineAtOffset(0, -leading);
                content.showText("Punong Barangay");
                content.endText();

            }

            document.save(pdfPath);
            JOptionPane.showMessageDialog(null, "Business permit generated: " + pdfPath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error generating business permit: " + e.getMessage());
        }
    }

    public static void generateResidencyCertificate(Resident resident, String purpose, String pdfPath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                // Set up fonts
                PDType1Font headerFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font bodyFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

                float pageWidth = page.getMediaBox().getWidth();
                float margin = 60;
                float yPosition = 700;
                float leading = 16f;
                int bodyFontSize = 12;

                // Updated headers to include province
                String[] headers = {
                    "REPUBLIC OF THE PHILIPPINES",
                    "PROVINCE OF BATANGAS",
                    "MUNICIPALITY OF CALATAGAN",
                    "BARANGAY BALIBAGO",
                    "OFFICE OF THE PUNONG BARANGAY"
                };

                // Write headers
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
                String title = "CERTIFICATE OF RESIDENCY";
                float titleWidth = headerFont.getStringWidth(title) / 1000 * 16;
                content.newLineAtOffset((pageWidth - titleWidth) / 2, yPosition);
                content.showText(title);
                content.endText();
                yPosition -= leading * 3;

                // Date components with suffix
                LocalDate currentDate = LocalDate.now();
                int day = currentDate.getDayOfMonth();
                String month = currentDate.format(DateTimeFormatter.ofPattern("MMMM"));
                int year = currentDate.getYear();
                String dayWithSuffix = getDayWithSuffix(day);

                // Main content paragraphs matching the image's structure
                String paragraph1 = "THIS IS TO CERTIFY that " + resident.getFullName() + ", of legal age, "
                        + resident.getCivilStatus().toLowerCase() + ", Filipino citizen, whose signature appears below, "
                        + "is a PERMANENT RESIDENT of Barangay Balibago, Calatagan, Batangas.";

                String paragraph2 = "Based on records of this office, the above-named person has been residing at "
                        + resident.getAddress() + ".";

                String paragraph3 = "This CERTIFICATION is being issued upon the request of the above-named person for "
                        + purpose + ".";

                String paragraph4 = "Issued this " + dayWithSuffix + " day of " + month + ", " + year
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
                paragraphs.add(paragraph4);

                // Write paragraphs with text wrapping
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

                // Signature section (applicant and punong barangay)
                float signatureY = 180;

                // Applicant's signature
                content.beginText();
                content.setFont(bodyFont, bodyFontSize);
                content.newLineAtOffset(margin + 50, signatureY);
                content.showText("_________________________");
                content.newLineAtOffset(0, -leading);
                content.showText("Signature over Printed Name");
                content.endText();

                // Punong Barangay's signature
                content.beginText();
                content.setFont(bodyFont, bodyFontSize);
                content.newLineAtOffset(pageWidth - margin - 150, signatureY);
                content.showText("HON. JUAN M. DELA CRUZ");
                content.newLineAtOffset(0, -leading);
                content.showText("Punong Barangay");
                content.endText();
            }

            document.save(pdfPath);
            JOptionPane.showMessageDialog(null, "Residency Certificate generated: " + pdfPath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void generateCommunityTaxCertificate(Resident resident, String purpose, String pdfPath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                // Set up fonts
                PDType1Font headerFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font bodyFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

                float pageWidth = page.getMediaBox().getWidth();
                float margin = 50;
                float yPosition = 750; // Starting vertical position
                float boxHeight = 20;
                float boxWidth = 150;
                float leading = 25;

                // Header Section
                String[] headers = {
                    "REPUBLIC OF THE PHILIPPINES",
                    "MUNICIPALITY OF CALATAGAN, BATANGAS",
                    "BARANGAY BALIBAGO"
                };

                for (String header : headers) {
                    content.beginText();
                    content.setFont(headerFont, 14);
                    float textWidth = headerFont.getStringWidth(header) / 1000 * 14;
                    float xPosition = (pageWidth - textWidth) / 2;
                    content.newLineAtOffset(xPosition, yPosition);
                    content.showText(header);
                    content.endText();
                    yPosition -= leading;
                }

                yPosition -= leading; // Add spacing after header

                // Title
                content.beginText();
                content.setFont(headerFont, 16);
                String title = "COMMUNITY TAX CERTIFICATE";
                float titleWidth = headerFont.getStringWidth(title) / 1000 * 16;
                content.newLineAtOffset((pageWidth - titleWidth) / 2, yPosition);
                content.showText(title);
                content.endText();
                yPosition -= leading * 2;

                // Draw Boxes for Details
                float leftColumnX = margin;
                float rightColumnX = pageWidth / 2 + margin;

                // Left Column
                drawBoxWithLabel(content, "Name:", resident.getFullName(), leftColumnX, yPosition, boxWidth, boxHeight, bodyFont, 12);
                yPosition -= leading;

                drawBoxWithLabel(content, "Address:", resident.getAddress(), leftColumnX, yPosition, boxWidth, boxHeight, bodyFont, 12);
                yPosition -= leading;

                drawBoxWithLabel(content, "Civil Status:", resident.getCivilStatus(), leftColumnX, yPosition, boxWidth, boxHeight, bodyFont, 12);
                yPosition -= leading;

                // Right Column
                yPosition += leading * 3; // Reset yPosition for right column alignment
                drawBoxWithLabel(content, "Date Issued:", LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")), rightColumnX, yPosition, boxWidth, boxHeight, bodyFont, 12);
                yPosition -= leading;

                drawBoxWithLabel(content, "CTC Number:", "123456789", rightColumnX, yPosition, boxWidth, boxHeight, bodyFont, 12);
                yPosition -= leading;

                drawBoxWithLabel(content, "Purpose:", purpose, rightColumnX, yPosition, boxWidth, boxHeight, bodyFont, 12);

                // Footer
                yPosition -= leading * 2;
                content.beginText();
                content.setFont(bodyFont, 10);
                content.newLineAtOffset(margin, yPosition);
                content.showText("This certificate is valid until December 31 of the current year.");
                content.endText();

                // Signature Box
                yPosition -= leading * 2;
                float signatureBoxWidth = 200;
                float signatureBoxHeight = 50;
                float signatureX = pageWidth - margin - signatureBoxWidth;

                content.addRect(signatureX, yPosition, signatureBoxWidth, signatureBoxHeight);
                content.stroke();

                content.beginText();
                content.setFont(bodyFont, 10);
                content.newLineAtOffset(signatureX + 10, yPosition + 30);
                content.showText("Signature of Treasurer");
                content.endText();
            }

            document.save(pdfPath);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
        }
    }

    public static void generateGoodMoralCertificate(Resident resident, String purpose, String pdfPath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                // Set up fonts
                PDType1Font headerFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font bodyFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

                float pageWidth = page.getMediaBox().getWidth();
                float margin = 50;
                float yPosition = 750; // Starting vertical position
                float leading = 20;

                // Header Section
                String[] headers = {
                    "REPUBLIC OF THE PHILIPPINES",
                    "MUNICIPALITY OF CALATAGAN, BATANGAS",
                    "BARANGAY BALIBAGO"
                };

                for (String header : headers) {
                    content.beginText();
                    content.setFont(headerFont, 14);
                    float textWidth = headerFont.getStringWidth(header) / 1000 * 14;
                    float xPosition = (pageWidth - textWidth) / 2;
                    content.newLineAtOffset(xPosition, yPosition);
                    content.showText(header);
                    content.endText();
                    yPosition -= leading;
                }

                yPosition -= leading; // Add spacing after header

                // Title
                content.beginText();
                content.setFont(headerFont, 16);
                String title = "CERTIFICATE OF GOOD MORAL CHARACTER";
                float titleWidth = headerFont.getStringWidth(title) / 1000 * 16;
                content.newLineAtOffset((pageWidth - titleWidth) / 2, yPosition);
                content.showText(title);
                content.endText();
                yPosition -= leading * 2;

                // Get current date components
                LocalDate currentDate = LocalDate.now();
                int day = currentDate.getDayOfMonth();
                String month = currentDate.format(DateTimeFormatter.ofPattern("MMMM"));
                int year = currentDate.getYear();

                // Main Content with text wrapping
                String paragraph1 = "TO WHOM IT MAY CONCERN:";
                List<String> paragraph1Lines = wrapText(paragraph1, bodyFont, 12, pageWidth - 2 * margin);
                drawTextBlock(content, bodyFont, 12, paragraph1Lines, margin, yPosition);
                yPosition -= (paragraph1Lines.size() * leading + 10);

                String paragraph2 = "THIS IS TO CERTIFY that " + resident.getFullName() + ", of legal age, "
                        + resident.getCivilStatus().toLowerCase() + ", Filipino citizen, with postal address at "
                        + resident.getAddress() + ", is known to me to be a person of good moral character.";
                List<String> paragraph2Lines = wrapText(paragraph2, bodyFont, 12, pageWidth - 2 * margin);
                drawTextBlock(content, bodyFont, 12, paragraph2Lines, margin, yPosition);
                yPosition -= (paragraph2Lines.size() * leading + 10);

                String paragraph3 = "This certification is issued upon the request of the interested party for " + purpose + ".";
                List<String> paragraph3Lines = wrapText(paragraph3, bodyFont, 12, pageWidth - 2 * margin);
                drawTextBlock(content, bodyFont, 12, paragraph3Lines, margin, yPosition);
                yPosition -= (paragraph3Lines.size() * leading + 10);

                String paragraph4 = "Issued this " + day + " day of " + month + " " + year + " at Barangay Balibago, Calatagan, Batangas.";
                List<String> paragraph4Lines = wrapText(paragraph4, bodyFont, 12, pageWidth - 2 * margin);
                drawTextBlock(content, bodyFont, 12, paragraph4Lines, margin, yPosition);

                // Signature Box
                yPosition -= leading * 3;
                float signatureBoxWidth = 200;
                float signatureBoxHeight = 50;
                float signatureX = pageWidth - margin - signatureBoxWidth;

                content.addRect(signatureX, yPosition, signatureBoxWidth, signatureBoxHeight);
                content.stroke();

                content.beginText();
                content.setFont(bodyFont, 10);
                content.newLineAtOffset(signatureX + 10, yPosition + 30);
                content.showText("Signature of Punong Barangay");
                content.endText();
            }

            document.save(pdfPath);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
        }
    }

    private static void drawTextBlock(PDPageContentStream content, PDType1Font font, int fontSize, List<String> lines, float x, float y) throws IOException {
        float leading = 16f; // Default leading value

        content.beginText();
        content.setFont(font, fontSize);
        content.newLineAtOffset(x, y);

        for (String line : lines) {
            content.showText(line);
            content.newLineAtOffset(0, -leading);
        }
        content.endText();
    }

    public static void generateEventPermit(Resident resident, String eventName, String eventDate, String eventVenue, String pdfPath) {
    try (PDDocument document = new PDDocument()) {
        PDPage page = new PDPage(PDRectangle.LETTER);
        document.addPage(page);

        try (PDPageContentStream content = new PDPageContentStream(document, page)) {
            PDType1Font headerFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            PDType1Font bodyFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            float pageWidth = page.getMediaBox().getWidth();
            float margin = 50;
            float yPosition = 720;
            float leading = 20;

            // Header Section
            String[] headers = {
                "REPUBLIC OF THE PHILIPPINES",
                "MUNICIPALITY OF CALATAGAN, BATANGAS",
                "BARANGAY BALIBAGO"
            };

            for (String header : headers) {
                content.beginText();
                content.setFont(headerFont, 14);
                float textWidth = headerFont.getStringWidth(header) / 1000 * 14;
                float xPosition = (pageWidth - textWidth) / 2;
                content.newLineAtOffset(xPosition, yPosition);
                content.showText(header);
                content.endText();
                yPosition -= leading;
            }

            yPosition -= leading;

            // Title
            content.beginText();
            content.setFont(headerFont, 16);
            String title = "EVENT PERMIT";
            float titleWidth = headerFont.getStringWidth(title) / 1000 * 16;
            content.newLineAtOffset((pageWidth - titleWidth) / 2, yPosition);
            content.showText(title);
            content.endText();
            yPosition -= leading * 2;

            LocalDate currentDate = LocalDate.now();
            int day = currentDate.getDayOfMonth();
            String month = currentDate.format(DateTimeFormatter.ofPattern("MMMM"));
            int year = currentDate.getYear();

            content.beginText();
            content.setFont(bodyFont, 12);
            content.newLineAtOffset(margin, yPosition);
            content.showText("TO WHOM IT MAY CONCERN:");
            content.endText();
            yPosition -= leading;

            String certificationText = "THIS IS TO CERTIFY that " + resident.getFullName() + ", of legal age, "
                    + resident.getCivilStatus().toLowerCase() + ", Filipino citizen, with postal address at "
                    + resident.getAddress() + ", has been granted permission to conduct the following event:";

            List<String> wrappedCertText = wrapText(certificationText, bodyFont, 12, pageWidth - 2 * margin);
            for (String line : wrappedCertText) {
                content.beginText();
                content.setFont(bodyFont, 12);
                content.newLineAtOffset(margin, yPosition);
                content.showText(line);
                content.endText();
                yPosition -= leading;
            }
            yPosition -= leading;

            // Event Details Table
            float tableX = margin;
            float initialTableY = yPosition - 30;
            float col1Width = 140;
            float col2Width = pageWidth - 2 * margin - col1Width;
            float lineHeight = 16f;

            String[][] eventDetails = {
                {"Event Name:", eventName},
                {"Event Date:", eventDate},
                {"Event Venue:", eventVenue}
            };

            // Calculate row heights
            List<Integer> rowLineCounts = new ArrayList<>();
            for (String[] detail : eventDetails) {
                List<String> wrapped = wrapText(detail[1], bodyFont, 12, col2Width - 20);
                rowLineCounts.add(wrapped.size());
            }

            // Calculate total table height
            float totalTableHeight = 0;
            for (int lineCount : rowLineCounts) {
                totalTableHeight += (lineCount * lineHeight) + 10;
            }

            // Draw table borders
            content.setLineWidth(1);
            content.addRect(tableX, initialTableY - totalTableHeight, col1Width + col2Width, totalTableHeight);
            content.stroke();

            // Draw vertical line between columns
            content.moveTo(tableX + col1Width, initialTableY - totalTableHeight);
            content.lineTo(tableX + col1Width, initialTableY);
            content.stroke();

            float currentY = initialTableY - 10;

            for (int i = 0; i < eventDetails.length; i++) {
                String[] detail = eventDetails[i];
                int lineCount = rowLineCounts.get(i);

                // Draw label
                content.beginText();
                content.setFont(headerFont, 12);
                content.newLineAtOffset(tableX + 5, currentY - 15);
                content.showText(detail[0]);
                content.endText();

                // Draw value
                List<String> wrappedText = wrapText(detail[1], bodyFont, 12, col2Width - 20);
                float textY = currentY - 15;

                for (String line : wrappedText) {
                    content.beginText();
                    content.setFont(bodyFont, 12);
                    content.newLineAtOffset(tableX + col1Width + 5, textY);
                    content.showText(line);
                    content.endText();
                    textY -= lineHeight;
                }

                currentY -= (lineCount * lineHeight) + 10;
            }

            yPosition = initialTableY - totalTableHeight - leading;

            // Issuance Details
            String issuanceText = "Issued this " + day + " day of " + month + " " + year
                    + " at Barangay Balibago, Calatagan, Batangas.";

            content.beginText();
            content.setFont(bodyFont, 12);
            content.newLineAtOffset(margin, yPosition);
            content.showText(issuanceText);
            content.endText();

            // Signature Section
            float signatureY = 150;
            float signatureBoxWidth = 200;
            float signatureBoxHeight = 50;
            float signatureX = pageWidth - margin - signatureBoxWidth;

            content.addRect(signatureX, signatureY, signatureBoxWidth, signatureBoxHeight);
            content.stroke();

            content.beginText();
            content.setFont(bodyFont, 10);
            content.newLineAtOffset(signatureX + 10, signatureY + 30);
            content.showText("Signature of Punong Barangay");
            content.endText();
        }

        document.save(pdfPath);
        JOptionPane.showMessageDialog(null, "Event Permit generated: " + pdfPath);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
    }
}


// Helper method to draw a box with a label and value
    private static void drawBoxWithLabel(PDPageContentStream content, String label, String value, float x, float y, float boxWidth, float boxHeight, PDType1Font font, int fontSize) throws IOException {
        // Draw label
        content.beginText();
        content.setFont(font, fontSize);
        content.newLineAtOffset(x, y);
        content.showText(label);
        content.endText();

        // Draw box
        float boxX = x + 100; // Offset for the box
        content.addRect(boxX, y - boxHeight + 5, boxWidth, boxHeight);
        content.stroke();

        // Draw value inside the box
        content.beginText();
        content.setFont(font, fontSize);
        content.newLineAtOffset(boxX + 5, y - boxHeight + 10);
        content.showText(value);
        content.endText();
    }

// Helper method for date suffix (now used)
    private static String getDayWithSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return day + "th";
        }
        switch (day % 10) {
            case 1:
                return day + "st";
            case 2:
                return day + "nd";
            case 3:
                return day + "rd";
            default:
                return day + "th";
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
        return switch (civilStatus.toLowerCase()) {
            case "married" ->
                "Mr./Mrs.";
            case "single" ->
                "Mr./Ms.";
            default ->
                "Mr./Ms.";
        };
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
