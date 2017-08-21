/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.employer;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import org.json.simple.parser.ParseException;
import users.user.Table_Users_Type;

/**
 *
 * @author Convite
 */
public final class Table_Employer extends javax.swing.JFrame {

    APIEmployer api = new APIEmployer();

    /**
     *
     * @param login
     * @param idService
     * @param idEmployer
     * @throws IOException
     * @throws ParseException
     */
    public Table_Employer(String login, int idService, int idEmployer) throws IOException, ParseException, Exception {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        initComponents();
        showTable(api.ShowEmployer(login, 1, 0));
        tbl_usersStart();
        TableColumn col = tbl_users.getColumnModel().getColumn(1);
        Events(login, idService, idEmployer);
        tbl_users.removeColumn(col);
        bt_disable.setVisible((tbl_users.getModel().getRowCount() > 0) ? idEmployer != parseInt(linfoUser.getText()) : false);
    }

    /**
     *
     * @param list
     */
    public void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
        Object[] row = new Object[4];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }

    }

    public void cleanTable() {
        DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
        mod.setRowCount(0);
    }

    private void tbl_usersStart() {
        TableModel mod = tbl_users.getModel();
        if (mod.getRowCount() > 0) {
            linfoUser.setText(mod.getValueAt(0, 0) + "");
            l_username.setText(mod.getValueAt(0, 2) + "");
        } else {
            linfoUser.setText("");
            l_username.setText("");
        }
    }

    private void Events(final String login, final int idService, final int idEmployer) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Edit(evt, login);
            }
        });
        bt_disable.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Disable(evt, login);
            }
        });
        mi_upc.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_UPC(evt, login, idService);
            }
        });
        mi_exit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Exit(evt);
            }
        });
        tbl_users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt, int idEmployer) {
                TBL_users(evt, idEmployer);
            }
        });

    }

    private void TBL_users(java.awt.event.MouseEvent evt,int idEmployer) {
        int i = tbl_users.getSelectedRow();
        TableModel mod = tbl_users.getModel();
        linfoUser.setText(mod.getValueAt(i, 0) + "");
        l_username.setText(mod.getValueAt(i, 2) + "");
        bt_disable.setVisible(idEmployer != parseInt(linfoUser.getText()));
    }

    private void BT_Edit(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            TableModel mod = tbl_users.getModel();
            int val = parseInt((String) mod.getValueAt(i, 0));
            new EditEmployer(login, val ).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param evt
     * @param login
     */
    public void BT_Disable(java.awt.event.ActionEvent evt, String login) {
        try {
            int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer desabilitar o funcionário " + l_username.getText() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if ("ok".equals(api.ActivityEmplyer(login, parseInt(linfoUser.getText()), 0))) {
                    cleanTable();
                    showTable(api.ShowEmployer(login, 1, 0));
                    tbl_usersStart();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro Interno");
                }
            } else if (x == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "O funcionário não foi desativado");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Interno");
        }
    }

    private void MI_UPC(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new Table_Users_Type(login, idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Employer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void MI_Exit(java.awt.event.ActionEvent evt) {
        dispose();
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
        linfoUser = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        bt_disable = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mi_exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        mi_upc = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Funcionários");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tbl_users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "N.º Funcionário","N.º Utilizador" ,"Nome", "Serviço"
            }
        ){
            boolean[] canEdit = new boolean [] {
                false, false,false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        }

    );
    tbl_users.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    jScrollPane2.setViewportView(tbl_users);

    jLabel1.setText("Nº. de Utilizador:");

    jLabel6.setText("Username:");

    linfoUser.setText("id");

    l_username.setText("username");

    bt_disable.setText("Desabilitar");

    bt_edit.setText("Editar");
    bt_edit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bt_editActionPerformed(evt);
        }
    });

    jMenu1.setText("File");

    mi_exit.setText("Sair");
    jMenu1.add(mi_exit);

    jMenuBar1.add(jMenu1);

    jMenu2.setText("Edit");
    jMenuBar1.add(jMenu2);

    jMenu3.setText("Utilizadores");

    jMenuItem3.setText("Todos os Utilizadores");
    jMenu3.add(jMenuItem3);

    mi_upc.setText("Utilizadores Por Categoria");
    jMenu3.add(mi_upc);

    jMenuBar1.add(jMenu3);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addComponent(linfoUser))
                        .addComponent(jLabel1))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(36, 36, 36)
                            .addComponent(l_username))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bt_edit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(bt_disable)))
                    .addGap(0, 1036, Short.MAX_VALUE))))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(linfoUser))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(l_username))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bt_disable)
                .addComponent(bt_edit))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_editActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_disable;
    private javax.swing.JButton bt_edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_username;
    private javax.swing.JLabel linfoUser;
    private javax.swing.JMenuItem mi_exit;
    private javax.swing.JMenuItem mi_upc;
    private javax.swing.JTable tbl_users;
    // End of variables declaration//GEN-END:variables
}