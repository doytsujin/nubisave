/*
 * MigrationDialog.java
 *
 * Created on 06.03.2012, 20:49:48
 */

package nubisave.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import nubisave.Nubisave;
import nubisave.StorageService;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

/**
 *
 * @author joe
 */
public class MigrationDialog extends javax.swing.JDialog {
    private Timer timer;
    private String previouslySelectedValueInSourceStoreList = null; //current selection is deleted from destination list, to forbid moving a store's data to itself

    /** Creates new form MigrationDialog */
    public MigrationDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        java.net.URL imageURL = this.getClass().getResource("/images/arrow_right.png");
        ImageIcon arrow = new ImageIcon(imageURL);
        moveDataBtn.setIcon(arrow);
        DefaultListModel sourceListModel = new DefaultListModel();
        DefaultListModel destinationListModel = new DefaultListModel();

        for(StorageService store: Nubisave.services){
            if(Nubisave.mainSplitter.isModuleMounted(store)){
                sourceListModel.addElement(store.getUniqName());
                destinationListModel.addElement(store.getUniqName());
            }
        }
        sourceStoreList.setModel(sourceListModel);
        sourceStoreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        destinationStoreList.setModel(destinationListModel);
        destinationStoreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        migrationProgressBar = new javax.swing.JProgressBar();
        moveDataBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        destinationStoreList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        sourceStoreList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        migrationStatusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        migrationProgressBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        moveDataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDataBtnActionPerformed(evt);
            }
        });

        destinationStoreList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(destinationStoreList);

        sourceStoreList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        sourceStoreList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                sourceStoreListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(sourceStoreList);

        jLabel1.setText("Move Data:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(203, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(moveDataBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(migrationStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(migrationProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(155, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(298, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveDataBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(migrationProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(migrationStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moveDataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDataBtnActionPerformed
        if(sourceStoreList.isSelectionEmpty() || destinationStoreList.isSelectionEmpty()){
            return;
        }
        final String sourceStore = (String) sourceStoreList.getSelectedValue();
        String destinationStore = (String) destinationStoreList.getSelectedValue();
        moveDataBtn.setEnabled(false);
        migrationStatusLabel.setText("");
        initializeMigration(Nubisave.mainSplitter.getConfigFile(sourceStore));
        Nubisave.mainSplitter.moveStoreData(sourceStore, destinationStore);
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                migrationProgressBar.setValue(getMigrationProgress(Nubisave.mainSplitter.getConfigFile(sourceStore)));
                if(migrationIsSuccessful(Nubisave.mainSplitter.getConfigFile(sourceStore)) != null){
                    timer.stop();
                    moveDataBtn.setEnabled(true);
                    migrationProgressBar.setValue(0);
                    if(migrationIsSuccessful(Nubisave.mainSplitter.getConfigFile(sourceStore))){
                        migrationStatusLabel.setText("Migration successful!");
                    } else {
                        migrationStatusLabel.setText("Migration failed!");
                    }
                }
            }
        });
        timer.start();
    }//GEN-LAST:event_moveDataBtnActionPerformed

    private void sourceStoreListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_sourceStoreListValueChanged
        if (!evt.getValueIsAdjusting()) {
            JList list = (JList)evt.getSource();

            String selected = (String) list.getSelectedValues()[0];
            DefaultListModel destinationStoreListModel = (DefaultListModel) destinationStoreList.getModel();
            destinationStoreList.clearSelection();
            if(previouslySelectedValueInSourceStoreList != null){
                destinationStoreListModel.addElement(previouslySelectedValueInSourceStoreList);
            }
            destinationStoreListModel.removeElement(selected);
            previouslySelectedValueInSourceStoreList = selected;
        }
    }//GEN-LAST:event_sourceStoreListValueChanged

    /**
     * Get the migration progress in percent
     * @return value between 0 and 100
     */
    private int getMigrationProgress(File storeConfig) {
        Ini ini;
        try {
            ini = new Ini(storeConfig);
            return ini.get("splitter", "migrationprogress", Integer.class);
        } catch (IOException ex) {
            return 0;
        } catch (NullPointerException e) {
            return 0;
        }
    }


    /**
     * Get the final status of the last migration progress in percent or null if this configuration's store is not the source of a complete migration progress.
     * @return value true if the last data migration from this service has been sucessful, false if it failed or null if there is no complete migration
     */
    private Boolean migrationIsSuccessful(File storeConfig) {
        Ini ini;
        try {
            ini = new Ini(storeConfig);
            return ini.get("splitter", "migrationissuccessful", Boolean.class);
        } catch (IOException ex) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }


    /**
     * Remove previous migration parameters from service.
     */
    private void initializeMigration(File storeConfig) {
        Ini ini;
        try {
            ini = new Ini(storeConfig);
            ini.remove("splitter", "migrationprogress");
            ini.remove("splitter", "migrationissuccessful");
            ini.store(new File("/home/joe/test"));
        } catch (IOException ex) {
            Logger.getLogger(MigrationDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList destinationStoreList;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JProgressBar migrationProgressBar;
    private javax.swing.JLabel migrationStatusLabel;
    private javax.swing.JButton moveDataBtn;
    private javax.swing.JList sourceStoreList;
    // End of variables declaration//GEN-END:variables

}
