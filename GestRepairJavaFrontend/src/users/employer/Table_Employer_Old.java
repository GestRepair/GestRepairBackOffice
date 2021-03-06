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
import users.user.Table_Users_Type;

/**
 *
 * @author Convite
 */
public final class Table_Employer_Old extends javax.swing.JFrame {

    /**
     *
     * @param login
     * @param idService
     * @param idEmployer
     * @throws IOException
     * @throws ParseException
     */
    public Table_Employer_Old(String login, int idService, int idEmployer) throws Exception {
        APIEmployer api = new APIEmployer();
        initComponents();
        showTable(api.ShowEmployer(login, 0, 0));
        tbl_usersStart();
        TableColumn col = tbl_users.getColumnModel().getColumn(1);
        tbl_users.removeColumn(col);
        bt_enable.setVisible((tbl_users.getModel().getRowCount() > 0) ? idEmployer != parseInt(linfoUser.getText()) : false);
        Events(login, idEmployer, api);
    }

    /**
     * Mostra os dados na tabela
     *
     * @param list
     */
    public void showTable(String[][] list) {
        if (list.length > 0) {
            DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
            Object[] row = new Object[4];
            for (String[] list1 : list) {
                for (int i = 0; i < row.length; i++) {
                    row[i] = list1[i];
                }
                mod.addRow(row);
            }
            bt_edit.setVisible(true);
            bt_enable.setVisible(true);
            tbl_users.setRowSelectionInterval(0, 0);
            linfoUser.setText((String) tbl_users.getModel().getValueAt(0, 0));
        } else {
            JOptionPane.showMessageDialog(this, "Sem Dados");
            bt_edit.setVisible(false);
            bt_enable.setVisible(true);
        }
    }

    /**
     * Cria os eventos
     *
     * @param login
     * @param idEmployer
     * @param api
     */
    private void Events(final String login, final int idEmployer, final APIEmployer api) {
        bt_enable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ENABLE(evt, login, api);
            }
        });
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EDIT(evt, login);
            }
        });
        tbl_users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_CLICKED(evt, idEmployer);
            }
        });

    }

    /**
     * "Readmite o cliente"
     *
     * @param evt
     * @param login
     * @param api
     */
    public void BT_ENABLE(java.awt.event.ActionEvent evt, String login, APIEmployer api) {
        try {
            int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer readmitir o funcionário " + l_username.getText() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String[] value = api.ActivityEmplyer(login, parseInt(linfoUser.getText()), 1);
                JOptionPane.showMessageDialog(this, value[1]);
                if ("ok".equals(value[0])) {
                    cleanTable();
                    showTable(api.ShowEmployer(login, 0, 0));
                    tbl_usersStart();
                }
            } else if (x == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "O funcionário não foi readmitido");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Interno");
        }
    }

    /**
     * Abre a form para editar o funcionário
     *
     * @param evt
     * @param login
     */
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

    /**
     * Seleciona o dado na tabela
     *
     * @param evt
     * @param idEmployer
     */
    private void TBL_CLICKED(java.awt.event.MouseEvent evt, int idEmployer) {
        int i = tbl_users.getSelectedRow();
        TableModel mod = tbl_users.getModel();
        linfoUser.setText(mod.getValueAt(i, 0) + "");
        l_username.setText(mod.getValueAt(i, 2) + "");
        bt_enable.setVisible(idEmployer != parseInt(mod.getValueAt(i, 0) + ""));
    }

    /**
     * Limpa os dados na tabela
     */
    public void cleanTable() {
        DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
        mod.setRowCount(0);
    }

    /**
     * Inicia os dados da tabela 
     */
    private void tbl_usersStart() {

        TableModel mod = tbl_users.getModel();
        if (mod.getRowCount() > 0) {
            tbl_users.setRowSelectionInterval(0, 0);
            linfoUser.setText(mod.getValueAt(0, 0) + "");
            l_username.setText(mod.getValueAt(0, 2) + "");
        } else {
            linfoUser.setText("");
            l_username.setText("");
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
        linfoUser = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        bt_enable = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Antigos Funcionários");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        tbl_users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "N.º Funcionário","N.º de Utilizador", "Nome", "Serviço"
            }
        ){
            boolean[] canEdit = new boolean [] {
                false, false, false
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

    bt_enable.setText("Reademitir");

    bt_edit.setText("Editar");

    jLabel2.setText("Lista de antigos funcionários");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addComponent(jLabel2)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(linfoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(l_username)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_enable, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bt_enable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_edit)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(linfoUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(l_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );

    setSize(new java.awt.Dimension(1216, 639));
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_enable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_username;
    private javax.swing.JLabel linfoUser;
    private javax.swing.JTable tbl_users;
    // End of variables declaration//GEN-END:variables
}
