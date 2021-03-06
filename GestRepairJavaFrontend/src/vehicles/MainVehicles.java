/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

import vehicles.vehicles.Table_Vehicles;
import vehicles.models.Table_Model;
import vehicles.models.AddModel;
import vehicles.brands.AddBrand;
import vehicles.brands.Table_Brand;
import java.util.logging.Level;
import java.util.logging.Logger;
import vehicles.fuel.AddFuel;
import vehicles.fuel.Table_Fuel;

/**
 *
 * @author Convite
 */
public class MainVehicles extends javax.swing.JFrame {

    /**
     * Creates new form mainVehicles
     *
     * @param login
     * @param idEmployer
     * @param idService
     */
    public MainVehicles(String login, int idEmployer, int idService) {
        initComponents();
        Events(login, idEmployer, idService);
    }

    /**
     * Eventos
     *
     * @param login
     * @param idEmployer
     * @param idService
     */
    private void Events(final String login, final int idEmployer, final int idService) {
        bt_showvehicles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_SH_VH(evt, login, idEmployer, idService);
            }
        });

        bt_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADDModel(evt, login);
            }
        });

        bt_addBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADDBrand(evt, login);
            }
        });

        bt_lBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_SH_BR(evt, login);
            }
        });

        bt_lmodel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_SH_MO(evt, login);
            }
        });

        bt_fuel_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_SH_FU(evt, login);
            }
        });

        bt_fuel_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADD_FU(evt, login);
            }
        });
    }

    /**
     * Abre o formulário para adicionar marca
     *
     * @param evt
     * @param login
     */
    private void BT_ADDBrand(java.awt.event.ActionEvent evt, String login) {
        new AddBrand(login).setVisible(true);
        dispose();
    }

    /**
     * Abre o formulário para adicionar modelo
     *
     * @param evt
     * @param login
     */
    private void BT_ADDModel(java.awt.event.ActionEvent evt, String login) {
        try {
            new AddModel(login).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a tabela da lista de viaturas
     *
     * @param evt
     * @param login
     * @param idEmployer
     * @param idService
     */
    private void BT_SH_VH(java.awt.event.ActionEvent evt, String login, int idEmployer, int idService) {
        try {
            new Table_Vehicles(login, idEmployer, idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a lista de marcas
     *
     * @param evt
     * @param login
     */
    private void BT_SH_BR(java.awt.event.ActionEvent evt, String login) {
        try {
            new Table_Brand(login).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a lista de modelos
     *
     * @param evt
     * @param login
     */
    private void BT_SH_MO(java.awt.event.ActionEvent evt, String login) {
        try {
            new Table_Model(login).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a tabela para listar combustiveis
     *
     * @param evt
     * @param login
     */
    private void BT_SH_FU(java.awt.event.ActionEvent evt, String login) {
        try {
            new Table_Fuel(login).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a form para adicionar combustiveis
     *
     * @param evt
     * @param login
     */
    private void BT_ADD_FU(java.awt.event.ActionEvent evt, String login) {
        try {
            new AddFuel(login).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
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

        bt_showvehicles = new javax.swing.JButton();
        bt_model = new javax.swing.JButton();
        bt_addBrand = new javax.swing.JButton();
        bt_lBrand = new javax.swing.JButton();
        bt_lmodel = new javax.swing.JButton();
        bt_fuel_list = new javax.swing.JButton();
        bt_fuel_add = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Menu Viaturas ");
        setResizable(false);

        bt_showvehicles.setText("Lista de Viaturas");

        bt_model.setText("Adicionar Modelo");

        bt_addBrand.setText("Adicionar Marca");

        bt_lBrand.setText("Lista de Marcas");

        bt_lmodel.setText("Lista de Modelos");

        bt_fuel_list.setText("Lista de Combustíveis");

        bt_fuel_add.setText("Adicionar Combustível");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_showvehicles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_lBrand, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(bt_fuel_list, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(bt_lmodel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_addBrand, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_model, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_fuel_add, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_showvehicles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_lBrand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_addBrand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_model, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_lmodel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_fuel_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_fuel_list, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(654, 171));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addBrand;
    private javax.swing.JButton bt_fuel_add;
    private javax.swing.JButton bt_fuel_list;
    private javax.swing.JButton bt_lBrand;
    private javax.swing.JButton bt_lmodel;
    private javax.swing.JButton bt_model;
    private javax.swing.JButton bt_showvehicles;
    // End of variables declaration//GEN-END:variables
}
