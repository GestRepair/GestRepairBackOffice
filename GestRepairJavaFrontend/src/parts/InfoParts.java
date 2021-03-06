/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public final class InfoParts extends javax.swing.JFrame {

    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param idPart
     * @param idService
     * @throws java.lang.Exception
     */
    public InfoParts(String login, int idPart, int idService) throws Exception {
        initComponents();
        Events(login, idPart, idService);
        GetData(login, idPart);
    }

    /**
     * Declara os eventos
     *
     * @param login
     * @param idPart
     * @param idService
     */
    private void Events(final String login, final int idPart, final int idService) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Edit(evt, login, idPart, idService);
            }
        });
        bt_add_amount.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADDAmount(evt, login, idPart, idService);
            }
        });
        bt_edit_price.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EditPrice(evt, login, idPart, idService);
            }
        });
        bt_service.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ListService(evt, login, idPart, idService);
            }
        });
        bt_add_service.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    BT_AddService(evt, login, idPart, idService);
                } catch (Exception ex) {
                    Logger.getLogger(Table_Parts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * Mostra o formulário para adicionar serviço a uma determinada peça
     *
     * @param evt
     * @param login
     * @param idPart
     * @param idService
     * @throws Exception
     */
    private void BT_AddService(java.awt.event.ActionEvent evt, String login, int idPart, int idService) throws Exception {
        new AddServicePart(login, idPart, idService).setVisible(true);
        dispose();
    }

    /**
     * Mostra o formulário para adicionar quantidade a uma determinada peça
     *
     * @param evt
     * @param login
     * @param idPart
     * @param idService
     */
    private void BT_ADDAmount(java.awt.event.ActionEvent evt, String login, int idPart, int idService) {
        try {
            new AddAmount(login, idPart, idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(InfoParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostra o formulário para editar detalhe de uma determinada peça
     *
     * @param evt
     * @param login
     * @param idPart
     * @param idService
     */
    private void BT_Edit(java.awt.event.ActionEvent evt, String login, int idPart, int idService) {
        try {
            new EditParts(login, idPart, idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(InfoParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostra o formulário para editar preço de uma determinada peça
     *
     * @param evt
     * @param login
     * @param idPart
     * @param idService
     */
    private void BT_EditPrice(java.awt.event.ActionEvent evt, String login, int idPart, int idService) {
        try {
            new EditPrice(login, idPart, idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(InfoParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostra o formulário da listagem de peças a uma determinada peça
     *
     * @param evt
     * @param login
     * @param idPart
     * @param idService
     */
    private void BT_ListService(java.awt.event.ActionEvent evt, String login, int idPart, int idService) {
        try {
            new ListServicesPart(login, idPart).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(InfoParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostra os detalhes da peça no formulário
     *
     * @param login
     * @param idPart
     * @throws Exception
     */
    private void GetData(String login, int idPart) throws Exception {
        APIParts api = new APIParts();
        String[] data = api.InfoParts(login, idPart);
        l_idPart.setText(data[0]);
        l_name.setText(data[1]);
        l_desc.setText("<html>" + data[2] + "</html>");
        l_amount.setText(data[3]);
        l_price.setText(data[4]);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        l_idPart = new javax.swing.JLabel();
        l_name = new javax.swing.JLabel();
        l_amount = new javax.swing.JLabel();
        l_price = new javax.swing.JLabel();
        l_desc = new javax.swing.JLabel();
        bt_add_amount = new javax.swing.JButton();
        bt_edit_price = new javax.swing.JButton();
        bt_service = new javax.swing.JButton();
        bt_add_service = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Informação da Peça");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel2.setText("Descrição da Peça:");

        jLabel3.setText("Nome:");

        jLabel4.setText("Quantidade");

        jLabel5.setText("Preço");

        bt_edit.setText("Editar");

        jLabel1.setText("Detalhe da peça n.º");

        l_idPart.setText("id");

        l_name.setText("jLabel6");

        l_amount.setText("jLabel6");

        l_price.setText("jLabel6");

        l_desc.setText("jLabel6");

        bt_add_amount.setText("Adicionar Quantidade");

        bt_edit_price.setText("Alterar Preço");

        bt_service.setText("Serviços");

        bt_add_service.setText("Adicionar Serviço");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_desc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_add_service, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(bt_add_amount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_edit_price)
                        .addGap(18, 18, 18)
                        .addComponent(bt_service)
                        .addGap(18, 18, 18)
                        .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(l_name))
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l_idPart)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l_price)
                            .addComponent(l_amount))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(l_idPart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(l_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(l_amount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(l_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_desc, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit)
                    .addComponent(bt_add_amount)
                    .addComponent(bt_edit_price)
                    .addComponent(bt_service)
                    .addComponent(bt_add_service))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(616, 639));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_amount;
    private javax.swing.JButton bt_add_service;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_edit_price;
    private javax.swing.JButton bt_service;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel l_amount;
    private javax.swing.JLabel l_desc;
    private javax.swing.JLabel l_idPart;
    private javax.swing.JLabel l_name;
    private javax.swing.JLabel l_price;
    // End of variables declaration//GEN-END:variables
}
