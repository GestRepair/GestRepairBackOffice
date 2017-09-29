/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Rui Barcelos
 */
public class Create_Service extends javax.swing.JFrame {

    /**
     * Creates new form CreateServiceIO
     *
     * @param login
     */
    public Create_Service(String login) {
        initComponents();
        tf_localUpload.setEditable(true);
        Events(login);
    }

    /**
     * Declara os eventos
     *
     * @param login
     */
    private void Events(final String login) {
        bt_upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_UPLOAD(evt);
            }
        });
        br_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADD(evt, login);
            }
        });

    }

    /**
     * Função que faz o upload de imagens
     *
     * @param evt
     */
    private void BT_UPLOAD(java.awt.event.ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        if (".png".equals(filename.substring(filename.length() - 4)) || ".jpg".equals(filename.substring(filename.length() - 4)) || ".jpeg".equals(filename.substring(filename.length() - 5))) {
            tf_localUpload.setText(filename);
            ImageIcon icon = new ImageIcon(filename);
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(260, 200, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            l_img.setIcon(icon);
        } else {
            JOptionPane.showMessageDialog(this, "Ficheiro Inválido \nUtilize os formatos \".png\", \".jpeg\" ou \".jpg\"");
            tf_localUpload.setText("");
            l_img.setIcon(null);
        }
    }
    /**
     * Botão de adicionar o serviço
     * 
     * @param evt
     * @param login 
     */
    private void BT_ADD(java.awt.event.ActionEvent evt, String login) {
        APIService pst = new APIService();
        if (tf_price.getText().length() > 0 && tf_service.getText().length() > 5 && tf_desc.getText().length() > 5) {
            if (".png".equals(tf_localUpload.getText().substring(tf_localUpload.getText().length() - 4)) || ".jpg".equals(tf_localUpload.getText().substring(tf_localUpload.getText().length() - 4)) || ".jpeg".equals(tf_localUpload.getText().substring(tf_localUpload.getText().length() - 5))) {
                File f = new File(tf_localUpload.getText());
                try {
                    pst.PostService(login, tf_service.getText(), tf_price.getText(), tf_desc.getText(), f);
                    JOptionPane.showMessageDialog(this, "Dados Inseridos com sucesso");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Ficheiro Não Existe");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ficheiro Inválido \n Utilize os formatos \".png\", \".jpeg\" ou \".jpg\"");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Verifique o se os campos nome do serviço, preço e descrição estão preenchidos!");
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

        l_img = new javax.swing.JLabel();
        tf_localUpload = new javax.swing.JTextField();
        bt_upload = new javax.swing.JButton();
        tf_price = new javax.swing.JTextField();
        tf_service = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tf_desc = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        br_add = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        l_img.setBackground(new java.awt.Color(255, 40, 40));
        l_img.setToolTipText("");
        l_img.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        bt_upload.setText("Upload");

        jLabel1.setText("Serviço");

        jLabel2.setText("Preço");

        tf_desc.setColumns(20);
        tf_desc.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tf_desc.setRows(5);
        jScrollPane1.setViewportView(tf_desc);

        jLabel3.setText("Imagem");

        jLabel4.setText("Descrição");

        br_add.setText("Adicionar Serviço");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l_img, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(br_add, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addGap(85, 85, 85)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_service, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(tf_price, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_localUpload, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_upload)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_img, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(br_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_localUpload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_upload))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(654, 335));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton br_add;
    private javax.swing.JButton bt_upload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_img;
    private javax.swing.JTextArea tf_desc;
    private javax.swing.JTextField tf_localUpload;
    private javax.swing.JTextField tf_price;
    private javax.swing.JTextField tf_service;
    // End of variables declaration//GEN-END:variables
}
