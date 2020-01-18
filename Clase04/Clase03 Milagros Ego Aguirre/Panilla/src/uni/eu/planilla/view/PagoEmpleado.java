/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.eu.planilla.view;

import uni.eu.planilla.dto.PlanillaDTO;
import uni.eu.planilla.service.PlanillaService;
import java.util.HashSet;

public class PagoEmpleado extends javax.swing.JFrame {

    public PagoEmpleado() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    BtnProcesar = new javax.swing.JToggleButton();
    BtnSalir = new javax.swing.JToggleButton();
    Tqhoras = new javax.swing.JTextField();
    Tdias = new javax.swing.JTextField();
    TpagoHrs = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    TpagoBruto = new javax.swing.JLabel();
    Timpuesto = new javax.swing.JLabel();
    TpagoNeto = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setText("DATOS:");

    jLabel2.setText("Horas:");

    jLabel3.setText("Dias:");

    jLabel4.setText("Pago por hora:");

    jLabel5.setText("RESULTADO:");

    BtnProcesar.setText("Procesar");
    BtnProcesar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BtnProcesarActionPerformed(evt);
      }
    });

    BtnSalir.setText("Salir");
    BtnSalir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BtnSalirActionPerformed(evt);
      }
    });

    Tqhoras.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        TqhorasActionPerformed(evt);
      }
    });

    jLabel6.setText("Pago  Bruto:");

    jLabel7.setText("Impuesto:");

    jLabel8.setText("Pago Neto:");

    TpagoBruto.setText("           ");

    Timpuesto.setText("             ");

    TpagoNeto.setText("              ");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(35, 35, 35)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(32, 32, 32)
            .addComponent(BtnProcesar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnSalir)
            .addGap(94, 94, 94))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(32, 32, 32))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Tqhoras, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
              .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(98, 98, 98))
              .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(TpagoHrs, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel6)
                  .addComponent(jLabel7)
                  .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(TpagoNeto)
                  .addComponent(Timpuesto)
                  .addComponent(TpagoBruto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel3)
            .addGap(18, 18, 18)
            .addComponent(Tdias, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(47, 47, 47)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(jLabel5))
        .addGap(24, 24, 24)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(Tqhoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel6)
          .addComponent(TpagoBruto))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(Tdias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel7)
          .addComponent(Timpuesto))
        .addGap(26, 26, 26)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(TpagoHrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel8)
          .addComponent(TpagoNeto))
        .addGap(31, 31, 31)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(BtnProcesar)
          .addComponent(BtnSalir))
        .addContainerGap(53, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void TqhorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TqhorasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TqhorasActionPerformed

    private void BtnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProcesarActionPerformed
        PlanillaDTO datos = new PlanillaDTO();
        int qhoras = 0;
        int dias = 0;
        double pagoHrs = 0;
        double pagoBruto = 0;
        double impuesto = 0;
        double pagoNeto = 0;
      
        datos.setQhoras(Integer.parseInt(Tqhoras.getText()));
        datos.setDias(Integer.parseInt(Tdias.getText()));
        datos.setPagoHrs(Double.parseDouble(TpagoHrs.getText()));

        PlanillaService servicio = new PlanillaService();
        
        servicio.Proceso(datos);
 
          
        TpagoBruto.setText(" "+datos.getPagoBruto());
        Timpuesto.setText(" "+datos.getImpuesto());
        TpagoNeto.setText(" "+datos.getPagoNeto());
        
    }//GEN-LAST:event_BtnProcesarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PagoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PagoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PagoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PagoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PagoEmpleado().setVisible(true);
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JToggleButton BtnProcesar;
  private javax.swing.JToggleButton BtnSalir;
  private javax.swing.JTextField Tdias;
  private javax.swing.JLabel Timpuesto;
  private javax.swing.JLabel TpagoBruto;
  private javax.swing.JTextField TpagoHrs;
  private javax.swing.JLabel TpagoNeto;
  private javax.swing.JTextField Tqhoras;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  // End of variables declaration//GEN-END:variables
}
