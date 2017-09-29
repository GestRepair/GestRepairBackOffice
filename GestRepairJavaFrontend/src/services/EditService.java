/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Rui Barcelos
 */
public final class EditService extends javax.swing.JFrame {

    /**
     * Creates new form EditService
     *
     * @param login
     * @param id
     * @throws java.lang.Exception
     */
    public EditService(String login, int id) throws Exception {
        APIService api = new APIService();
        initComponents();
        info(api.GetInfo(login, id));
        Events(login, id, api);
        ta_desc.setLineWrap(true);
    }

    /**
     *
     * @param login
     * @param id
     * @param api
     */
    private void Events(final String login, final int id, final APIService api) {
        bt_upload.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_UPLOAD(evt, login, id, api);
            }
        });

        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Edit(evt, login, id, api);
            }
        });
    }

    /**
     * Chama a class para fazer o upload de imágem
     *
     * @param evt
     * @param login
     * @param id
     * @param api
     */
    private void BT_UPLOAD(java.awt.event.ActionEvent evt, String login, int id, APIService api) {
        try {
            upload(login, id, api);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ficheiro Não Existe");
        }
    }

    /**
     * Edita a base de dados
     *
     * @param evt
     * @param login
     * @param id
     * @param api
     */
    private void BT_Edit(java.awt.event.ActionEvent evt, String login, int id, APIService api) {
        try {
            if (tf_price.getText().length() > 0 && tf_service.getText().length() > 5 && ta_desc.getText().length() > 5) {
                if (tf_upload.getText().length() > 0) {
                    if (".png".equals(tf_upload.getText().substring(tf_upload.getText().length() - 4)) || ".jpg".equals(tf_upload.getText().substring(tf_upload.getText().length() - 4)) || ".jpeg".equals(tf_upload.getText().substring(tf_upload.getText().length() - 5))) {
                        File f = new File(tf_upload.getText());
                        api.PutService(login, id, tf_service.getText(), tf_price.getText(), ta_desc.getText(), f);
                        JOptionPane.showMessageDialog(this, "Dados Inseridos com sucesso");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Ficheiro Inválido \n Utilize os formatos \".png\", \".jpeg\" ou \".jpg\"");
                    }
                } else {
                    String value[] = api.PutServiceWithout(login, id, tf_service.getText(), tf_price.getText(), ta_desc.getText());
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Verifique o se os campos nome do serviço, preço e descrição estão preenchidos!");
            }
        } catch (Exception ex) {
            Logger.getLogger(EditService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Faz o upload de imágem
     *
     * @param login
     * @param id
     * @param api
     * @throws Exception
     */
    private void upload(String login, int id, APIService api) throws Exception {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        if (".png".equals(filename.substring(filename.length() - 4)) || ".jpg".equals(filename.substring(filename.length() - 4)) || ".jpeg".equals(filename.substring(filename.length() - 5))) {
            tf_upload.setText(filename);
            ImageIcon icon = new ImageIcon(filename);
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(300, 180, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            l_photo.setIcon(icon);
        } else {
            ImageIcon icon = new ImageIcon(new URL(api.GetInfo(login, id)[4]));
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(300, 180, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            l_photo.setIcon(icon);
            JOptionPane.showMessageDialog(this, "Ficheiro Inválido \nUtilize os formatos \".png\", \".jpeg\" ou \".jpg\"");
            tf_upload.setText("");
        }
    }

    /**
     * Mostra a informação dos serviços e cuja imagem que está associada
     *
     * @param info
     * @throws MalformedURLException
     * @throws IOException
     */
    private void info(String[] info) throws MalformedURLException, IOException {
        l_service.setText(info[0]);
        tf_service.setText(info[1]);
        tf_price.setText(info[2]);
        ta_desc.setText(info[3]);
        ImageIcon icon = new ImageIcon(new URL(info[4]));
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(300, 180, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        l_photo.setIcon(icon);
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
        l_service = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_service = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tf_price = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        l_photo = new javax.swing.JLabel();
        tf_upload = new javax.swing.JTextField();
        bt_upload = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_desc = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Alterar Serviço");
        setResizable(false);

        jLabel1.setText("Alterar serviço n.º:");

        l_service.setText("serviço");

        jLabel3.setText("Nome:");

        jLabel2.setText("Preço:");

        jLabel4.setText("Descrição:");

        jLabel5.setText("Imagem:");

        bt_upload.setText("Upload");

        bt_edit.setText("Editar Serviço");

        ta_desc.setColumns(20);
        ta_desc.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ta_desc.setRows(5);
        jScrollPane2.setViewportView(ta_desc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l_service)
                                .addGap(267, 267, 267))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_edit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(309, 309, 309))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tf_upload, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_upload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_service)
                            .addComponent(jScrollPane2)
                            .addComponent(tf_price))
                        .addGap(18, 18, 18)
                        .addComponent(l_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_service)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_upload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_upload)
                    .addComponent(bt_edit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(786, 283));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_upload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_photo;
    private javax.swing.JLabel l_service;
    private javax.swing.JTextArea ta_desc;
    private javax.swing.JTextField tf_price;
    private javax.swing.JTextField tf_service;
    private javax.swing.JTextField tf_upload;
    // End of variables declaration//GEN-END:variables
}
