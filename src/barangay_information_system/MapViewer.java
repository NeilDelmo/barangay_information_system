/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package barangay_information_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author Neil
 */

public class MapViewer extends javax.swing.JFrame {
    private BufferedImage mapImage;
    private double scale = 1.0;
    private Point imagePos = new Point(0, 0);
    private Point dragStart;
    private JPanel mapPanel;
    private JScrollPane scrollPane;

    /**
     * Creates new form MapViewer
     */
    public MapViewer() {
        initComponents();
        setLocationRelativeTo(null);
        jPanel1.setLayout(new BorderLayout());
        initCustomComponents();
        loadImage();
    }

    private void initCustomComponents() {
        // Map panel setup
        mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (mapImage != null) {
                    int w = (int)(mapImage.getWidth() * scale);
                    int h = (int)(mapImage.getHeight() * scale);
                    g.drawImage(mapImage, imagePos.x, imagePos.y, w, h, this);
                }
            }
        };
        
        // Scroll pane setup
        scrollPane = new JScrollPane(mapPanel);
        scrollPane.setWheelScrollingEnabled(true);
        
        // Add components to main panel
        jPanel1.add(jPanel2, BorderLayout.NORTH);
        jPanel1.add(scrollPane, BorderLayout.CENTER);
        jPanel1.add(jPanel3, BorderLayout.SOUTH); // Control panel at bottom
        
        // Set proper button sizes
        btnZoomIn.setPreferredSize(new Dimension(80, 30));
        btnZoomOut.setPreferredSize(new Dimension(80, 30));
        btnReset.setPreferredSize(new Dimension(80, 30));
        mapPanel.addMouseListener(new MouseAdapter() {
    public void mousePressed(MouseEvent e) {
        dragStart = e.getPoint();
    }
});

mapPanel.addMouseMotionListener(new MouseAdapter() {
    public void mouseDragged(MouseEvent e) {
        if (dragStart != null) {
            int dx = e.getX() - dragStart.x;
            int dy = e.getY() - dragStart.y;
            imagePos.translate(dx, dy);
            dragStart = e.getPoint();
            mapPanel.repaint();
        }
    }
});
    }

    private void loadImage() {
        try {
            mapImage = javax.imageio.ImageIO.read(new File("barangay_map.png"));
            centerImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Map image not found!\nPlace 'barangay_map.png' in project folder.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void centerImage() {
        SwingUtilities.invokeLater(() -> {
            if (mapImage != null) {
                scale = Math.min(
                    (double)scrollPane.getViewport().getWidth() / mapImage.getWidth(),
                    (double)scrollPane.getViewport().getHeight() / mapImage.getHeight()
                );
                updateImagePosition();
                mapPanel.revalidate();
                mapPanel.repaint();
            }
        });
    }

    private void updateImagePosition() {
        if (mapImage != null) {
            int panelWidth = mapPanel.getWidth();
            int panelHeight = mapPanel.getHeight();
            int imgWidth = (int)(mapImage.getWidth() * scale);
            int imgHeight = (int)(mapImage.getHeight() * scale);
            
            imagePos.x = (panelWidth - imgWidth) / 2;
            imagePos.y = (panelHeight - imgHeight) / 2;
        }
    }

    // Generated code with proper button placement
    // ... [Keep the generated initComponents() method as is] ...

    // Add these event handlers                

    private void zoom(double factor) {
    // Store current view center
    Rectangle viewRect = scrollPane.getViewport().getViewRect();
    Point prevCenter = new Point(
        viewRect.x + viewRect.width/2,
        viewRect.y + viewRect.height/2
    );

    // Calculate new scale with limits
    scale *= factor;
    scale = Math.max(0.1, Math.min(scale, 10.0));

    // Adjust image position to maintain center
    int newWidth = (int)(mapImage.getWidth() * scale);
    int newHeight = (int)(mapImage.getHeight() * scale);
    imagePos.x = prevCenter.x - (int)(newWidth * (prevCenter.x / (double)(mapPanel.getWidth())));
    imagePos.y = prevCenter.y - (int)(newHeight * (prevCenter.y / (double)(mapPanel.getHeight())));

    // Update UI
    mapPanel.revalidate();
    mapPanel.repaint();
    
    // Center viewport after zoom
    SwingUtilities.invokeLater(() -> {
        int x = imagePos.x + newWidth/2 - viewRect.width/2;
        int y = imagePos.y + newHeight/2 - viewRect.height/2;
        mapPanel.scrollRectToVisible(new Rectangle(x, y, viewRect.width, viewRect.height));
    });
}

    // Add mouse motion listener to the mapPanel
    private void mapPanelMouseDragged(java.awt.event.MouseEvent evt) {                                      
        if (dragStart != null) {
            int dx = evt.getX() - dragStart.x;
            int dy = evt.getY() - dragStart.y;
            imagePos.translate(dx, dy);
            dragStart = evt.getPoint();
            mapPanel.repaint();
        }
    }                                     

    private void mapPanelMousePressed(java.awt.event.MouseEvent evt) {                                      
        dragStart = evt.getPoint();
    }                                     

    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnZoomIn = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("Map");
        jPanel2.add(jLabel1);

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setText("Zoom");
        jPanel3.add(jLabel2);

        btnZoomIn.setText("+");
        btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomInActionPerformed(evt);
            }
        });
        jPanel3.add(btnZoomIn);

        btnZoomOut.setText("-");
        btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomOutActionPerformed(evt);
            }
        });
        jPanel3.add(btnZoomOut);

        btnReset.setText("Reset");
        btnReset.setMaximumSize(new java.awt.Dimension(23, 23));
        btnReset.setMinimumSize(new java.awt.Dimension(23, 23));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel3.add(btnReset);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 902, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomInActionPerformed
        // TODO add your handling code here:
         zoom(1.2);
    }//GEN-LAST:event_btnZoomInActionPerformed

    private void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomOutActionPerformed
        // TODO add your handling code here:
        zoom(0.8);
    }//GEN-LAST:event_btnZoomOutActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
          centerImage();
        mapPanel.revalidate();
        mapPanel.repaint();
    }//GEN-LAST:event_btnResetActionPerformed

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
        java.util.logging.Logger.getLogger(MapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(MapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(MapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(MapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new MapViewer().setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnZoomIn;
    private javax.swing.JButton btnZoomOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
