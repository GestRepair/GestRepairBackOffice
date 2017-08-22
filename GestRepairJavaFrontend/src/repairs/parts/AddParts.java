/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.parts;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import parts.APIParts;
import services.APIService;

/**
 *
 * @author Rui Barcelos
 */
public final class AddParts extends javax.swing.JFrame {

    APIPartsRepair api = new APIPartsRepair();
    APIParts apiparts = new APIParts();
    APIService apiService = new APIService();

    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param idRepair
     * @param idService
     * @throws java.lang.Exception
     */
    public AddParts(final String login, final int idRepair, final int idService) throws Exception {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        
        l_idRepair.setText(idRepair + "");
        
        showService(apiparts.ListPartswhith(login));
        Events(login, idRepair, idService);
        showTableParts(login);
    }

    private void showService(String[][] list) {
        cb_service.removeAllItems();
        for (String[] list1 : list) {
            cb_service.addItem(list1[1]);
        }
    }

    private int Cb_Val(int val, String[][] list) {
        return parseInt(list[val][0]);
    }

    private void showTableParts(String login) throws Exception {
        String data[][] = apiparts.ListPartsZero(login, Cb_Val(cb_service.getSelectedIndex(), apiparts.ListPartswhith(login)));
        showTable(data);
    }

    private void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_parts.getModel();
        Object[] row = new Object[2];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }

    private void Events(final String login, final int idRepair, final int idService) {
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    BT_AddPart(evt, login, idRepair, idService);
                } catch (Exception ex) {
                    Logger.getLogger(AddParts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        cb_service.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    CB_SERVICE(evt, login);
                } catch (Exception ex) {
                    Logger.getLogger(AddParts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void cleanTable() {
        DefaultTableModel mod = (DefaultTableModel) tbl_parts.getModel();
        mod.setRowCount(0);
    }

    private void CB_SERVICE(java.awt.event.ActionEvent evt, String login) throws Exception {
        cleanTable();
        showTableParts(login);
    }

    private int sendData() {
        DefaultTableModel mod = (DefaultTableModel) tbl_parts.getModel();
        int i = tbl_parts.getSelectedRow();
        int val = parseInt((String) mod.getValueAt(i, 0));
        return val;
    }

    private void BT_AddPart(java.awt.event.ActionEvent evt, String login, int idRepair, int idService) {
        try {
            int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer inserir os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if ("ok".equals(api.PostParts(login, idRepair, sendData()))) {
                    JOptionPane.showMessageDialog(this, "A peça foi inserida com sucesso!");
                    new ListPartsRepair(login, idRepair, idService).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao inserir os dados!");
                }
                dispose();
            } else if (x == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "A peça não foi introduzida no sistema!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro a adicionar reparação!\n Verifique se os dados estão corretos");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cb_service = new javax.swing.JComboBox();
        bt_add = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        l_idRepair = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_parts = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar peça à reparação");

        jLabel1.setText("Serviço:");

        cb_service.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_serviceActionPerformed(evt);
            }
        });

        bt_add.setText("Adicionar");

        jLabel4.setText("ID:");

        l_idRepair.setText("reparação");

        jLabel2.setText("Selecione um peça para adicionar à reparação:");

        tbl_parts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Peça"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_parts);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_idRepair)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cb_service, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bt_add)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(l_idRepair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_service)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_add)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_serviceActionPerformed

    }//GEN-LAST:event_cb_serviceActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JComboBox cb_service;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idRepair;
    private javax.swing.JTable tbl_parts;
    // End of variables declaration//GEN-END:variables
}
