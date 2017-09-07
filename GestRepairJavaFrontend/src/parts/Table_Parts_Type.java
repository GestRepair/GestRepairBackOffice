/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import org.json.simple.parser.ParseException;
import services.APIService;
import users.employer.APIEmployer;

/**
 *
 * @author Convite
 */
public final class Table_Parts_Type extends javax.swing.JFrame {

    /**
     * Start the interface and need elements
     *
     * @param login
     * @param idService
     * @throws IOException
     * @throws ParseException
     */
    public Table_Parts_Type(String login, int idService) throws Exception {
        APIParts api = new APIParts();
        APIService apiService = new APIService();
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
        showService(apiService.Service(login));
        Events(login, idService, api, apiService);
        if (idService != 1 || idService != 2) {
            cbType.setSelectedItem(apiService.GetInfo(login, idService)[1]);
        }
        int i = cbType.getSelectedIndex();
        showTable(api.ListParts(login, (idService == 1 || idService == 2) ? Cb_Val(i + 2, apiService.Service(login)) : idService));
        row(0);
    }

    private void Events(final String login, final int idService, final APIParts api, final APIService apiService) {
        bt_add_service.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    int idPart = parseInt(linfoUser.getText());
                    BT_AddService(evt, login, idPart, idService);
                } catch (Exception ex) {
                    Logger.getLogger(Table_Parts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    BT_Edit(evt, login, idService);
                } catch (Exception ex) {
                    Logger.getLogger(Table_Parts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        bt_info.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    BT_Info(evt, login, idService);
                } catch (Exception ex) {
                    Logger.getLogger(Table_Parts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        bt_add_amount.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int idPart = parseInt(linfoUser.getText());
                BT_ADDAmount(evt, login, idPart, idService);
            }
        });
        bt_edit_price.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int idPart = parseInt(linfoUser.getText());
                BT_EditPrice(evt, login, idPart, idService);
            }
        });
        cbType.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_SERVICE(evt, login, api, apiService);
            }
        });
    }

    private void BT_AddService(java.awt.event.ActionEvent evt, String login, int idPart, int idService) throws Exception {
        new AddServicePart(login, idPart, idService).setVisible(true);
        dispose();
    }

    private void BT_Edit(java.awt.event.ActionEvent evt, String login, int idService) throws Exception {
        int idPart = parseInt(linfoUser.getText());
        new EditParts(login, idPart, idService).setVisible(true);
        dispose();
    }

    private void BT_Info(java.awt.event.ActionEvent evt, String login, int idService) throws Exception {
        int idPart = parseInt(linfoUser.getText());
        new InfoParts(login, idPart, idService).setVisible(true);
        dispose();
    }

    private void BT_EditPrice(java.awt.event.ActionEvent evt, String login, int idPart, int idService) {
        try {
            new EditPrice(login, idPart, idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(InfoParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_ADDAmount(java.awt.event.ActionEvent evt, String login, int idPart, int idService) {
        try {
            new AddAmount(login, idPart, idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(InfoParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CB_SERVICE(java.awt.event.ActionEvent evt, String login, APIParts api, APIService apiService) {
        try {
            int i = cbType.getSelectedIndex();
            String data[][] = api.ListParts(login, Cb_Val(i + 2, apiService.Service(login)));
            if (data.length > 0) {
                cleanTable();
                showTable(data);
                row(0);
                bt_add_amount.setVisible(true);
                bt_add_service.setVisible(true);
                bt_edit.setVisible(true);
                bt_edit_price.setVisible(true);
                bt_info.setVisible(true);
            } else {
                bt_add_amount.setVisible(false);
                bt_add_service.setVisible(false);
                bt_edit.setVisible(false);
                bt_edit_price.setVisible(false);
                bt_info.setVisible(false);
                JOptionPane.showMessageDialog(this, "Não Contem dados");
            }
        } catch (Exception ex) {
        }
    }

    public void cleanTable() {
        DefaultTableModel mod = (DefaultTableModel) tbl_parts.getModel();
        mod.setRowCount(0);
    }

    private void showService(String[][] list) {
        cbType.removeAllItems();
        for (String[] list1 : list) {
            cbType.addItem(list1[1]);
        }
        cbType.removeItemAt(0);
        cbType.removeItemAt(0);
    }

    private int Cb_Val(int val, String[][] list) {
        return parseInt(list[val][0]);
    }

    private void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_parts.getModel();
        Object[] row = new Object[6];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }

    private void row(int val) {
        TableModel mod = tbl_parts.getModel();
        if (mod.getRowCount() > 0) {
            jLabel1.setText("ID:");
            jLabel6.setText("Nome:");
            linfoUser.setText(mod.getValueAt(val, 0) + "");
            l_username.setText(mod.getValueAt(val, 1) + "");
        } else {
            jLabel1.setText("");
            jLabel6.setText("");
            linfoUser.setText("");
            l_username.setText("Não contem Dados!");
            //bt_edit.setVisible(false);
            //bt_info.setVisible(false);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_parts = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        linfoUser = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        cbType = new javax.swing.JComboBox<String>();
        bt_edit = new javax.swing.JButton();
        bt_info = new javax.swing.JButton();
        bt_add_amount = new javax.swing.JButton();
        bt_edit_price = new javax.swing.JButton();
        bt_add_service = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Peeças por catergoria");

        tbl_parts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Descrição", "Quantidade", "Preço", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_parts.getTableHeader().setReorderingAllowed(false);
        tbl_parts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_partsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_parts);
        tbl_parts.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jLabel1.setText("ID");

        jLabel6.setText("Nome da Peça");

        jLabel7.setText("Tipo de Peça");

        linfoUser.setText("Número");

        l_username.setText("Nome");

        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Funcionário" }));
        cbType.setAutoscrolls(true);

        bt_edit.setText("Editar");

        bt_info.setText("Info");

        bt_add_amount.setText("Adicionar Quantidade");

        bt_edit_price.setText("Alterar Preço");

        bt_add_service.setText("Adicionar Serviço");
        bt_add_service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_serviceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(linfoUser)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_username)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_add_service)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_add_amount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_edit_price)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_edit)))
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_add_amount)
                        .addComponent(bt_edit_price)
                        .addComponent(bt_add_service))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(linfoUser)
                        .addComponent(jLabel6)
                        .addComponent(l_username)
                        .addComponent(bt_edit)
                        .addComponent(bt_info)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_partsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_partsMouseClicked
        int i = tbl_parts.getSelectedRow();
        row(i);
    }//GEN-LAST:event_tbl_partsMouseClicked

    private void bt_add_serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_serviceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_serviceActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_amount;
    private javax.swing.JButton bt_add_service;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_edit_price;
    private javax.swing.JButton bt_info;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_username;
    private javax.swing.JLabel linfoUser;
    private javax.swing.JTable tbl_parts;
    // End of variables declaration//GEN-END:variables
}
