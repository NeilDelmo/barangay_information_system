/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package barangay_information_system;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Neil
 */
public class Document_Form extends javax.swing.JFrame {

    private Map<String, Integer> residentMap = new HashMap<>();
    private Map<String, Integer> docTypeMap = new HashMap<>();
    private JDialog previewDialog;
    private JEditorPane previewPane;

    /**
     * Creates new form Document_Form
     */
    public Document_Form() {
        initComponents();
        setLocationRelativeTo(null);
        loadResidents();
        loadDocumentTypes();
        initPreviewDialog();
        loadRequestHistory();
        businessInfoPanel.setVisible(false);
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        businessInfoPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Residentcombobox = new javax.swing.JComboBox<>();
        docTypeComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        businessInfoPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        businessNameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        businessAddressField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        requestHistoryTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(jTable8);

        scrollPane1.add(jScrollPane9);

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jTable7);

        scrollPane1.add(jScrollPane8);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable6);

        scrollPane1.add(jScrollPane7);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable5);

        scrollPane1.add(jScrollPane6);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable4);

        scrollPane1.add(jScrollPane5);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable3);

        scrollPane1.add(jScrollPane4);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        scrollPane1.add(jScrollPane3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        scrollPane1.add(jScrollPane2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel1.setText("Documents");
        jPanel2.add(jLabel1);

        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));

        Residentcombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Residentcombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResidentcomboboxActionPerformed(evt);
            }
        });

        docTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        docTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docTypeComboBoxActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Purpose");

        jLabel3.setText("Resident:");

        jLabel4.setText("Types of Document:");

        jButton1.setText("View and Download");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Residentcombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(docTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Residentcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(docTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        businessInfoPanel.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel5.setText("Business Name:");

        jLabel6.setText("Business Address:");

        businessAddressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                businessAddressFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout businessInfoPanelLayout = new javax.swing.GroupLayout(businessInfoPanel);
        businessInfoPanel.setLayout(businessInfoPanelLayout);
        businessInfoPanelLayout.setHorizontalGroup(
            businessInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(businessInfoPanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(businessInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(businessInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(businessNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(businessAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        businessInfoPanelLayout.setVerticalGroup(
            businessInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(businessInfoPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(businessInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(businessNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(businessInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(businessAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(188, Short.MAX_VALUE))
        );

        jPanel4.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel4.setLayout(new java.awt.BorderLayout());

        requestHistoryTable.setAutoCreateRowSorter(true);
        requestHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        requestHistoryTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane10.setViewportView(requestHistoryTable);

        jPanel4.add(jScrollPane10, java.awt.BorderLayout.CENTER);

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(businessInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(businessInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadBusinessDataForSelectedResident() {
        if (Residentcombobox.getSelectedItem() != null) {
            int residentId = residentMap.get(Residentcombobox.getSelectedItem());
            try {
                String[] businessInfo = BusinessDAO.loadBusinessInfo(residentId);
                businessNameField.setText(businessInfo[0]);
                businessAddressField.setText(businessInfo[1]);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading business data: " + ex.getMessage());
            }
        }
    }

    private void initPreviewDialog() {
        previewDialog = new JDialog(this, "Preview", true);
        previewPane = new JEditorPane();
        previewPane.setContentType("text/html");
        JScrollPane scroll = new JScrollPane(previewPane);
        previewDialog.add(scroll);
        previewDialog.setSize(600, 800);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        generatePDF();
        submitRequest();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void businessAddressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_businessAddressFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_businessAddressFieldActionPerformed

    private void docTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docTypeComboBoxActionPerformed
        // TODO add your handling code here:
        String selected = (String) docTypeComboBox.getSelectedItem();
        boolean isBusiness = "Business Permit".equals(selected);
        businessInfoPanel.setVisible(isBusiness);

        if (isBusiness) {
            loadBusinessDataForSelectedResident();
        } else {
            businessNameField.setText("");
            businessAddressField.setText("");
        }
    }//GEN-LAST:event_docTypeComboBoxActionPerformed

    private void ResidentcomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResidentcomboboxActionPerformed
        // TODO add your handling code here:
        if ("Business Permit".equals(docTypeComboBox.getSelectedItem())) {
            loadBusinessDataForSelectedResident();
        }
    }//GEN-LAST:event_ResidentcomboboxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void loadResidents() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(
                    "SELECT ResidentID, CONCAT(FirstName, ' ', LastName) AS FullName FROM Residents");
            ResultSet rs = pst.executeQuery();

            Residentcombobox.removeAllItems();
            while (rs.next()) {
                String fullName = rs.getString("FullName");
                int id = rs.getInt("ResidentID");
                residentMap.put(fullName, id);
                Residentcombobox.addItem(fullName);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading residents: " + e.getMessage());
        }
    }

    private void loadDocumentTypes() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(
                    "SELECT DocumentTypeID, DocumentName FROM DocumentTypes");
            ResultSet rs = pst.executeQuery();

            docTypeComboBox.removeAllItems();
            while (rs.next()) {
                String docName = rs.getString("DocumentName");
                int id = rs.getInt("DocumentTypeID");
                docTypeMap.put(docName, id);
                docTypeComboBox.addItem(docName);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading document types: " + e.getMessage());
        }
    }

   private void loadRequestHistory() {
    DefaultTableModel model = new DefaultTableModel(
        new Object[]{"Request Date", "Resident", "Document Type", "Status"}, 0
    );

    // Fixed query with proper aliases
    String query = "SELECT dr.RequestDate AS RequestDate, " +
                   "COALESCE(CONCAT(r.FirstName, ' ', r.LastName), 'Deleted Resident') AS ResidentName, " +
                   "COALESCE(dt.DocumentName, 'Deleted Document') AS DocumentName, " +
                   "dr.Status AS Status " +
                   "FROM DocumentRequests dr " +
                   "LEFT JOIN Residents r ON dr.ResidentID = r.ResidentID " +
                   "LEFT JOIN DocumentTypes dt ON dr.DocumentTypeID = dt.DocumentTypeID " +
                   "ORDER BY dr.RequestDate DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery()) {

        while(rs.next()) {
            model.addRow(new Object[]{
                rs.getTimestamp("RequestDate"),
                rs.getString("ResidentName"),  // Use alias
                rs.getString("DocumentName"),  // Use alias
                rs.getString("Status")
            });
        }

        requestHistoryTable.setModel(model);

        // Rest of your formatting code...
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading history: " + e.getMessage());
    }
}

    

    private void submitRequest() {
        String selectedResident = (String) Residentcombobox.getSelectedItem();
        String selectedDoc = (String) docTypeComboBox.getSelectedItem();
        String purpose = jTextArea1.getText();

        if (selectedResident == null || selectedDoc == null || purpose.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(
                    "INSERT INTO DocumentRequests (ResidentID, DocumentTypeID, Purpose) VALUES (?, ?, ?)");

            pst.setInt(1, residentMap.get(selectedResident));
            pst.setInt(2, docTypeMap.get(selectedDoc));
            pst.setString(3, purpose);

            pst.executeUpdate();
            loadRequestHistory();
            JOptionPane.showMessageDialog(this, "Document request submitted!");
            jTextArea1.setText(""); // Clear purpose field
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }

    private void generatePDF() {
        String selectedDoc = (String) docTypeComboBox.getSelectedItem();
        String selectedResident = (String) Residentcombobox.getSelectedItem();
        String purpose = jTextArea1.getText();

        // Validation
        if (selectedResident == null || purpose.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select resident and enter purpose");
            return;
        }

        // Business Permit specific validation
        int residentId = residentMap.get(selectedResident);
        Resident resident = getResidentDetails(residentId);
        if ("Business Permit".equals(selectedDoc)) {
            try {
                BusinessDAO.saveBusinessInfo(
                        residentId,
                        businessNameField.getText(),
                        businessAddressField.getText()
                );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error saving business info: " + ex.getMessage());
                return;
            }
        }

        if (resident != null) {
            String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String pdfPath = "D:/GeneratedDocuments/"
                    + selectedDoc.replace(" ", "_") + "_"
                    + resident.getLastName() + "_"
                    + timestamp + ".pdf";

            try {
                switch (selectedDoc) {
                    case "Business Permit":
                        PDFGenerator.generateBusinessPermit(
                                resident,
                                businessNameField.getText(),
                                businessAddressField.getText(),
                                pdfPath
                        );
                        break;

                    case "Barangay Clearance":
                        PDFGenerator.generateBarangayClearance(resident, purpose, pdfPath);
                        break;

                    case "Certificate of Indigency":
                        PDFGenerator.generateCertificateOfIndigency(resident, purpose, pdfPath);
                        break;

                    default:
                        JOptionPane.showMessageDialog(this, "Unsupported document type");
                        return;
                }
                showPDFPreview(pdfPath);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Generation failed: " + e.getMessage());
            }
        }
    }

    private void showPDFPreview(String pdfPath) {
        try {
            File pdfFile = new File(pdfPath);
            if (!pdfFile.exists()) {
                JOptionPane.showMessageDialog(this, "PDF file not found!");
                return;
            }

            // Open the PDF in the default viewer
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                JOptionPane.showMessageDialog(this, "PDF preview not supported on this system.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error opening PDF: " + e.getMessage());
        }
    }

    private Resident getResidentDetails(int residentId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(
                    "SELECT FirstName, LastName, Address, CivilStatus, BirthDate "
                    + "FROM Residents WHERE ResidentID = ?");
            pst.setInt(1, residentId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Convert SQL Date to String (or LocalDate)
                LocalDate birthDate = rs.getDate("BirthDate").toLocalDate();

                return new Resident(
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Address"),
                        rs.getString("CivilStatus"),
                        birthDate.toString() // Convert LocalDate to String (YYYY-MM-DD)
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching resident: " + e.getMessage());
        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Document_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Document_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Document_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Document_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Document_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Residentcombobox;
    private javax.swing.JTextField businessAddressField;
    private javax.swing.JPanel businessInfoPanel;
    private javax.swing.JTextField businessNameField;
    private javax.swing.JComboBox<String> docTypeComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable requestHistoryTable;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
