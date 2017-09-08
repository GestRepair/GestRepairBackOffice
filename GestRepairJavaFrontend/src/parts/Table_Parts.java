/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Convite
 */
public final class Table_Parts extends javax.swing.JFrame {

    /**
     *
     * @param login
     * @param idService
     * @throws IOException
     * @throws ParseException
     */
    public Table_Parts(String login, int idService) throws Exception {
        APIParts api = new APIParts();
        initComponents();
        showTable(api.ListParts(login, 0));
        tbl_usersStart();
        row(0);
        Events(login, idService);
    }

    private void Events(final String login, final int idService) {
        bt_add_amount.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int idPart = parseInt(linfoUser.getText());
                BT_ADDAmount(evt, login, idPart, idService);
            }
        });
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

        bt_edit_price.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int idPart = parseInt(linfoUser.getText());
                BT_EditPrice(evt, login, idPart, idService);
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

    public void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_part.getModel();
        Object[] row = new Object[6];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }

    private void tbl_usersStart() {
        // TODO add your handling code here:
        TableModel mod = tbl_part.getModel();
        row(0);
    }

    private void row(int val) {
        TableModel mod = tbl_part.getModel();
        linfoUser.setText(mod.getValueAt(val, 0) + "");
        l_username.setText(mod.getValueAt(val, 1) + "");
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
        tbl_part = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        linfoUser = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        bt_info = new javax.swing.JButton();
        bt_add_amount = new javax.swing.JButton();
        bt_edit_price = new javax.swing.JButton();
        bt_add_service = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Peças");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        tbl_part.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_part.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_part.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_partMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_part);

        jLabel1.setText("Nº. de Utilizador:");

        jLabel6.setText("Username:");

        linfoUser.setText("id");

        l_username.setText("username");

        bt_edit.setText("Editar");

        bt_info.setText("Info");

        bt_add_amount.setText("Adicionar Quantidade");

        bt_edit_price.setText("Alterar Preço");

        bt_add_service.setText("Adicionar Serviço");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(linfoUser))
                    .addComponent(jLabel1))
                .addGap(36, 36, 36)
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
                .addComponent(bt_edit)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(linfoUser)
                            .addComponent(jLabel6)
                            .addComponent(l_username))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bt_add_amount)
                                .addComponent(bt_edit_price)
                                .addComponent(bt_add_service))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bt_edit)
                                .addComponent(bt_info)))
                        .addContainerGap())))
        );

        setSize(new java.awt.Dimension(1216, 584));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_partMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_partMouseClicked
        // TODO add your handling code here:
        int i = tbl_part.getSelectedRow();
        row(i);
    }//GEN-LAST:event_tbl_partMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_amount;
    private javax.swing.JButton bt_add_service;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_edit_price;
    private javax.swing.JButton bt_info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_username;
    private javax.swing.JLabel linfoUser;
    private javax.swing.JTable tbl_part;
    // End of variables declaration//GEN-END:variables
}
