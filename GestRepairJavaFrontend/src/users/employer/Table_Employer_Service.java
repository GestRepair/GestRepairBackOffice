/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.employer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import org.json.simple.parser.ParseException;
import services.APIService;
import users.user.Table_Users_Type;

/**
 *
 * @author Convite
 */
public final class Table_Employer_Service extends javax.swing.JFrame {

    /**
     * Start the interface and need elements
     *
     * @param login
     * @param idService
     * @param idEmployer
     * @throws IOException
     * @throws ParseException
     */
    public Table_Employer_Service(String login, int idService, int idEmployer) throws Exception {
        APIEmployer api = new APIEmployer();
        APIService apiService = new APIService();
        initComponents();
        TableColumn col = tbl_users.getColumnModel().getColumn(1);
        tbl_users.removeColumn(col);
        insertCb(apiService.Service(login));
        showTable(api.ShowEmployer(login, 1, 1));
        tbl_usersStart();
        bt_disable.setVisible((tbl_users.getModel().getRowCount() > 0) ? idEmployer != parseInt(linfoUser.getText()) : false);
        Events(login, idEmployer, api, apiService);
    }

    private void Events(final String login, final int idEmployer, final APIEmployer api, final APIService apiService) throws Exception {
        bt_disable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_DISABLE(evt, login, api, apiService);
            }
        });
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EDIT(evt, login);
            }
        });
        cb_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_TYPE(evt, login, idEmployer, api, apiService);
            }
        });
        tbl_users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_CLICKED(evt,idEmployer);
            }
        });
    }

    private void TBL_CLICKED(java.awt.event.MouseEvent evt, int idEmployer) {
        // TODO add your handling code here:
        int i = tbl_users.getSelectedRow();
        TableModel mod = tbl_users.getModel();
        linfoUser.setText(mod.getValueAt(i, 0) + "");
        l_username.setText(mod.getValueAt(i, 2) + "");
        bt_disable.setVisible(idEmployer != parseInt(linfoUser.getText()));
    }

    private void insertCb(String[][] list) throws Exception {
        cb_type.removeAllItems();
        for (String[] list1 : list) {
            cb_type.addItem(list1[1]);
        }
    }

    private void cleanTable() {
        DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
        mod.setRowCount(0);
    }

    /**
     *
     * @param login
     * @throws Exception
     */
    private void upTable(String login, APIEmployer api, APIService apiService) throws Exception {
        cleanTable();
        int cb = cb_type.getSelectedIndex();
        showTable(api.ShowEmployer(login, 1, newIdCb(cb, apiService.Service(login))));
        tbl_usersStart();
    }

    /**
     *
     * @param list
     */
    private void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
        Object[] row = new Object[3];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }

    /**
     *
     * @param val
     * @param list
     * @return
     */
    private int newIdCb(int val, String[][] list) {
        return parseInt(list[val][0]);
    }

    /**
     *
     * @throws Exception
     */
    private void tbl_usersStart() throws Exception {

        TableModel mod = tbl_users.getModel();
        if (tbl_users.getModel().getRowCount() > 0) {
            tbl_users.setRowSelectionInterval(0, 0);
            linfoUser.setText(mod.getValueAt(0, 0) + "");
            l_username.setText(mod.getValueAt(0, 2) + "");
        } else {
            linfoUser.setText("");
            l_username.setText("");
        }
    }

    /**
     *
     * @param login
     */
    private void BT_DISABLE(java.awt.event.ActionEvent evt, String login, APIEmployer api, APIService apiService) {
        try {
            int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer desabilitar o funcionário " + l_username.getText() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String[] value = api.ActivityEmplyer(login, parseInt(linfoUser.getText()), 0);
                JOptionPane.showMessageDialog(this, value[1]);
                if ("ok".equals(value[0])) {
                    DefaultTableModel model = (DefaultTableModel) tbl_users.getModel();
                    model.setRowCount(0);
                    int cb = cb_type.getSelectedIndex();
                    showTable(api.ShowEmployer(login, 1, newIdCb(cb, apiService.Service(login))));
                    tbl_usersStart();
                }
            } else if (x == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "O funcionário não foi desativado");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Interno");
        }
    }

    private void BT_EDIT(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            TableModel mod = tbl_users.getModel();
            int val = parseInt((String) mod.getValueAt(i, 0));
            new EditEmployer(login, val).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CB_TYPE(java.awt.event.ActionEvent evt, String login, int idEmployer, APIEmployer api, APIService apiService) {
        try {
            upTable(login, api, apiService);
            bt_disable.setVisible((tbl_users.getModel().getRowCount() > 0) ? idEmployer != parseInt(linfoUser.getText()) : false);
        } catch (Exception ex) {
            Logger.getLogger(Table_Employer_Service.class.getName()).log(Level.SEVERE, null, ex);
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
        tbl_users = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        linfoUser = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        cb_type = new javax.swing.JComboBox();
        bt_disable = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Funcionários por Serviço");
        setResizable(false);

        tbl_users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "N.º Funcionário","N.º de Utilizador","Nome"
            }
        ));
        jScrollPane2.setViewportView(tbl_users);

        jLabel1.setText("Nº. de Utilizador:");

        jLabel6.setText("Username:");

        jLabel7.setText("Selecione o serviço em que o utilizador pretença:");

        linfoUser.setText("Número");

        l_username.setText("Nome");

        cb_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        bt_disable.setText("Desabilitar");

        bt_edit.setText("Editar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linfoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_username, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_disable, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_type, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(728, 728, 728))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cb_type))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(linfoUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(bt_disable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1216, 639));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_disable;
    private javax.swing.JButton bt_edit;
    private javax.swing.JComboBox cb_type;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_username;
    private javax.swing.JLabel linfoUser;
    private javax.swing.JTable tbl_users;
    // End of variables declaration//GEN-END:variables
}
