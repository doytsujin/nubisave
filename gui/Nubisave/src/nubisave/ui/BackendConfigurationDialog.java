/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJDialog.java
 *
 * Created on 23.10.2011, 13:22:38
 */

package nubisave.ui;

import com.github.joe42.splitter.util.file.PropertiesUtil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import nubisave.StorageService;

/**
 *
 * @author joe
 */
public class BackendConfigurationDialog extends javax.swing.JDialog {
    private final StorageService service;
    private final DefaultListModel listModel;
    private final ListSelectionListener availableBackendServicesListSelectionListener;

    BackendConfigurationDialog(java.awt.Frame parent, boolean modal, StorageService service) {
        super(parent, modal);
        this.service = service;
        initComponents();
        this.listModel = new DefaultListModel();
        availableBackendServicesListSelectionListener = new AvailableBackendServicesListSelectionListener();
        availableBackendServices.setModel(listModel);
        for(StorageService s: nubisave.Nubisave.services){
            if(! s.getUniqName().equals(service.getUniqName())){
                listModel.addElement(s.getUniqName());
            }
        }
        //set current selection
        for(StorageService s: service.getBackendServices()){
            availableBackendServices.setSelectedValue(s.getUniqName(), false);
        }
        availableBackendServices.addListSelectionListener(availableBackendServicesListSelectionListener);
    }

    /**
     * Adapt storage services to changes in the selection of backend stores.
     * Add the backend stores to the current service and set
     */
    private void availableBackendServicesValueChanged(javax.swing.event.ListSelectionEvent evt) {
        if(! evt.getValueIsAdjusting()){
            List<String> selectedServiceNames = new ArrayList<String>();
            for(Object o: availableBackendServices.getSelectedValues()){
                selectedServiceNames.add((String)o);
            }
            for(String serviceName: selectedServiceNames){
                StorageService selectedService = nubisave.Nubisave.services.getByUniqueName(serviceName);
                service.addBackendService(selectedService);
            }
            nubisave.Nubisave.services.update();
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

        customservicesScrollPane = new javax.swing.JScrollPane();
        availableBackendServices = new javax.swing.JList();
        okButton = new javax.swing.JButton();
        descriptionLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        customservicesScrollPane.setViewportView(availableBackendServices);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        descriptionLabel.setText("Choose the backend service(s):");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addComponent(customservicesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(97, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                .addComponent(okButton)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(customservicesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(55, Short.MAX_VALUE)))
        );

        okButton.getAccessibleContext().setAccessibleName("OKButton");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList availableBackendServices;
    private javax.swing.JScrollPane customservicesScrollPane;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

    class AvailableBackendServicesListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            availableBackendServicesValueChanged(e);
        }

    }
}
