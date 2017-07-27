/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestrepairjavafrontend;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Convite
 */
public final class tableUsers extends javax.swing.JFrame {

    
    public String log;

    /**
     *
     * @param login
     * @throws IOException
     * @throws ParseException
     */
    public tableUsers(String login) throws IOException, ParseException {
        initComponents();
        getListUsers gld = new getListUsers();
        mostraTabela(contTableUser(gld.getUL(login)));
        log = login;
    }

    /**
     *
     * @param list
     */
    public void mostraTabela(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
        Object[] row = new Object[10];
        for (String[] list1 : list) {
            for (int i = 0; i < 10; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }


    /**
     *
     * @param list
     * @return
     */
    @SuppressWarnings("empty-statement")
    public String[][] contTableUser(String list) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][10];
            for (int i = 0; i < data.size(); i++) {

                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idUser") + "";
                dataTable[i][1] = (String) datas.get("name");
                dataTable[i][2] = (String) datas.get("street");
                dataTable[i][3] = (String) datas.get("zipcode");
                dataTable[i][4] = (String) datas.get("city");
                dataTable[i][5] = (String) datas.get("email");
                dataTable[i][6] = (String) datas.get("nif");
                dataTable[i][7] = (String) datas.get("contact");
                dataTable[i][8] = (String) datas.get("username");
                long x = (long) datas.get("isEmployer");
                if ( x == 1 ) {
                    dataTable[i][9] = "Funcionário";
                }else{
                    dataTable[i][9] = "Cliente";
                }
                         
            };
            return dataTable;
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(this,"Erro a mostrar a tabela");
            return null;
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
        bt_alterar = new javax.swing.JButton();
        tfnome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfmorada = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tflocalidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfemail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfnif = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        linfoUser = new javax.swing.JLabel();
        tfcontacto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        bt_add_user = new javax.swing.JButton();
        tfcodp = new javax.swing.JFormattedTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tbl_users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "N.º Utilizador", "Nome", "Morada", "Código de Postal","Localidade","E-mail","NIF","Contacto","Username","Tipo de Utilizador"
            }
        ));
        tbl_users.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_usersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_users);

        bt_alterar.setText("Editar Utilizador");
        bt_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_alterarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nº. de Utilizador:");

        jLabel2.setText("Nome:");

        jLabel3.setText("Morada:");

        jLabel4.setText("Código Postal:");

        jLabel5.setText("Localidade:");

        jLabel6.setText("Username:");

        tfemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfemailActionPerformed(evt);
            }
        });

        jLabel8.setText("E-Mail:");

        tfnif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfnifActionPerformed(evt);
            }
        });

        jLabel9.setText("NIF:");

        jLabel10.setText("Contacto");

        bt_add_user.setText("Adicionar Utilizador");
        bt_add_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_userActionPerformed(evt);
            }
        });

        try {
            tfcodp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jMenu1.setText("File");

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Utilizadores");

        jMenuItem3.setText("Todos os Utilizadores");
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Utilizadores Por Categoria");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfnome, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfemail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfmorada, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(linfoUser)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfnif, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfcontacto, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(tfcodp))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addComponent(l_username))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tflocalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(bt_add_user, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(linfoUser)
                    .addComponent(jLabel6)
                    .addComponent(l_username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(tfcontacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfmorada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(tflocalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(tfcodp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(tfnif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_add_user))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_alterar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tfemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfemailActionPerformed

    private void tfnifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfnifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfnifActionPerformed

    private void tbl_usersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_usersMouseClicked
        // TODO add your handling code here:
        int i = tbl_users.getSelectedRow();
        TableModel mod = tbl_users.getModel();
        linfoUser.setText(mod.getValueAt(i, 0) + "");
        tfnome.setText(mod.getValueAt(i, 1) + "");
        tfmorada.setText(mod.getValueAt(i, 2) + "");
        tfcodp.setText(mod.getValueAt(i, 3) + "");
        tflocalidade.setText(mod.getValueAt(i, 4) + "");
        tfemail.setText(mod.getValueAt(i, 5) + "");
        tfnif.setText(mod.getValueAt(i, 6) + "");
        tfcontacto.setText(mod.getValueAt(i, 7) + "");
        l_username.setText(mod.getValueAt(i, 8) + "");

        //addItem(mod.getValueAt(i, 9)+"");
    }//GEN-LAST:event_tbl_usersMouseClicked

    private void bt_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_alterarActionPerformed
        // TODO add your handling code here:
        putUser pt = new putUser();
        try {
            pt.putUsr(log,linfoUser.getText(),tfnome.getText(), tfmorada.getText(), tfcodp.getText(), tflocalidade.getText(), tfemail.getText(), tfnif.getText(), tfcontacto.getText());
            getListUsers gld = new getListUsers();
            DefaultTableModel mod = (DefaultTableModel)tbl_users.getModel();
            mod.setRowCount(0);
            mostraTabela(contTableUser(gld.getUL(log)));
        } catch (Exception ex) {
            Logger.getLogger(tableUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_alterarActionPerformed

    private void bt_add_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_userActionPerformed
        // TODO add your handling code here:
        new AddUserForm(log).setVisible(true);       
    }//GEN-LAST:event_bt_add_userActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            // TODO add your handling code here:
            new tableUserType(log).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(tableUsers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(tableUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_user;
    private javax.swing.JButton bt_alterar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_username;
    private javax.swing.JLabel linfoUser;
    private javax.swing.JTable tbl_users;
    private javax.swing.JFormattedTextField tfcodp;
    private javax.swing.JTextField tfcontacto;
    private javax.swing.JTextField tfemail;
    private javax.swing.JTextField tflocalidade;
    private javax.swing.JTextField tfmorada;
    private javax.swing.JTextField tfnif;
    private javax.swing.JTextField tfnome;
    // End of variables declaration//GEN-END:variables
}
