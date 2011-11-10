/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainWindow.java
 *
 * Created on May 24, 2011, 6:20:38 PM
 */
package nubisave.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import nubisave.*;

/**
 *
 * @author demo
 */
public class MainWindow extends javax.swing.JFrame {

    /** Creates new form MainWindow */
    public MainWindow() {
        tableModel = new NubiTableModel();
        initComponents();
        providerTable.setDefaultRenderer(String.class, new ShowSupportedCellRenderer());
        providerTable.getColumn(tableModel.headers[NubiTableModel.Headers.OPTIONS.ordinal()]).setCellRenderer(new ButtonRenderer());
        providerTable.getColumn(tableModel.headers[NubiTableModel.Headers.OPTIONS.ordinal()]).setCellEditor(new ButtonEditor(new JCheckBox(), this));
        providerTable.getColumn(tableModel.headers[NubiTableModel.Headers.REMOVE.ordinal()]).setCellRenderer(new ButtonRenderer());
        providerTable.getColumn(tableModel.headers[NubiTableModel.Headers.REMOVE.ordinal()]).setCellEditor(new ButtonEditor(new JCheckBox(), this));
        providerTable.getColumn(tableModel.headers[NubiTableModel.Headers.CONNECT.ordinal()]).setCellRenderer(new ButtonRenderer());
        providerTable.getColumn(tableModel.headers[NubiTableModel.Headers.CONNECT.ordinal()]).setCellEditor(new ButtonEditor(new JCheckBox(), this));

        mntDirTxtField.setText(Nubisave.mainSplitter.getMountpoint());

        String redundancyStr = Properties.getProperty("redundancy");
        if (redundancyStr == null) {
            redundancyStr = "100";
        }
        int redundancy = Integer.parseInt(redundancyStr);
        redundancySlider.setValue(redundancy);
        //

        if (!Nubisave.mainSplitter.isMounted()) {
            JOptionPane.showMessageDialog(null, "Nubisave is not mounted.", "Mount Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customStorageserviceChooser = new javax.swing.JFileChooser();
        jOptionPane1 = new javax.swing.JOptionPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        providerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        providerTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        optionPanel = new javax.swing.JPanel();
        mntDirTxtField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        redundancySlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        openMntDirBtn = new javax.swing.JButton();
        matchMakerLabel = new javax.swing.JLabel();
        matchMakerURLField = new javax.swing.JTextField();
        changeMatchMakerURLBtn = new javax.swing.JButton();
        matchMakerLabel1 = new javax.swing.JLabel();
        matchMakerField = new javax.swing.JTextField();

        customStorageserviceChooser.setCurrentDirectory(new java.io.File("../splitter/mountscripts"));
        customStorageserviceChooser.setDialogTitle("Custom Service");
        customStorageserviceChooser.setFileFilter(new IniFileFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nubisave");

        providerTable.setModel(tableModel);
        providerTable.setColumnSelectionAllowed(true);
        providerTable.setRowHeight(30);
        jScrollPane1.setViewportView(providerTable);
        providerTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton1.setText("Service");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Agreement");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Custom");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("add by:");

        javax.swing.GroupLayout providerPanelLayout = new javax.swing.GroupLayout(providerPanel);
        providerPanel.setLayout(providerPanelLayout);
        providerPanelLayout.setHorizontalGroup(
            providerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, providerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(providerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        providerPanelLayout.setVerticalGroup(
            providerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(providerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(providerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addGroup(providerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Provider", providerPanel);

        mntDirTxtField.setEditable(false);
        mntDirTxtField.setText("mntDirTxtField");
        mntDirTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mntDirTxtFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Mount directory");

        redundancySlider.setValue(100);
        redundancySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                redundancySliderStateChanged(evt);
            }
        });

        jLabel3.setText("Redundancy");

        openMntDirBtn.setText("Open");
        openMntDirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMntDirBtnActionPerformed(evt);
            }
        });

        matchMakerLabel.setText("MatchMaker ");

        matchMakerURLField.setText(Properties.getProperty("matchmakerURI"));

        changeMatchMakerURLBtn.setText("Apply");
        changeMatchMakerURLBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeMatchMakerURLBtnActionPerformed(evt);
            }
        });

        matchMakerLabel1.setText("MatchMaker ");

        matchMakerField.setEditable(false);
        matchMakerField.setText("MatchMaker URL");
        matchMakerField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchMakerFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout optionPanelLayout = new javax.swing.GroupLayout(optionPanel);
        optionPanel.setLayout(optionPanelLayout);
        optionPanelLayout.setHorizontalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(redundancySlider, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionPanelLayout.createSequentialGroup()
                        .addComponent(mntDirTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                        .addGap(36, 36, 36)
                        .addComponent(openMntDirBtn))
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(matchMakerLabel)
                            .addComponent(matchMakerURLField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addComponent(changeMatchMakerURLBtn))))
        );
        optionPanelLayout.setVerticalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mntDirTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openMntDirBtn))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redundancySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(matchMakerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matchMakerURLField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeMatchMakerURLBtn))
                .addGap(176, 176, 176))
        );

        jTabbedPane1.addTab("Options", optionPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AddServiceDialog addServiceDlg = new AddServiceDialog(this, true);
        addServiceDlg.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int returnVal = customStorageserviceChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = customStorageserviceChooser.getSelectedFile();
            StorageService newService = new StorageService(file);
            Nubisave.services.getMmServices().add(newService);
        }
        tableModel.fireTableDataChanged();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void matchMakerFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void mntDirTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mntDirTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mntDirTxtFieldActionPerformed

    private void openMntDirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMntDirBtnActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(new File(Nubisave.mainSplitter.getDataDir()));
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_openMntDirBtnActionPerformed

    private void redundancySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_redundancySliderStateChanged
        Properties.setProperty("redundancy", String.valueOf(redundancySlider.getValue()));
        Nubisave.mainSplitter.setRedundancy(redundancySlider.getValue());
    }//GEN-LAST:event_redundancySliderStateChanged

    private void changeMatchMakerURLBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeMatchMakerURLBtnActionPerformed
        Properties.setProperty("matchmakerURI", matchMakerURLField.getText());
    }//GEN-LAST:event_changeMatchMakerURLBtnActionPerformed
    public NubiTableModel tableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changeMatchMakerURLBtn;
    private javax.swing.JFileChooser customStorageserviceChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField matchMakerField;
    private javax.swing.JLabel matchMakerLabel;
    private javax.swing.JLabel matchMakerLabel1;
    private javax.swing.JTextField matchMakerURLField;
    private javax.swing.JTextField mntDirTxtField;
    private javax.swing.JButton openMntDirBtn;
    private javax.swing.JPanel optionPanel;
    private javax.swing.JPanel providerPanel;
    private javax.swing.JTable providerTable;
    private javax.swing.JSlider redundancySlider;
    // End of variables declaration//GEN-END:variables

    class IniFileFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            // Allow only directories, or files with ".ini" extension
            return file.isDirectory() || file.getAbsolutePath().endsWith(".ini");
        }
        @Override
        public String getDescription() {
            // This description will be displayed in the dialog,
            // hard-coded = ugly, should be done via I18N
            return "*.ini";
        }
    }

}
