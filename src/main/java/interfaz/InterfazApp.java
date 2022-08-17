/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import javax.swing.JOptionPane;

/**
 *
 * @author Juan Sebastian
 */
public class InterfazApp extends javax.swing.JFrame {

    /**
     * Creates new form InterfazApp
     */
    public InterfazApp() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("PANEL DE INICIO DEL PROGRAMA");

        jButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton1.setText("INSTRUCCIONES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel2.setText("BIENVENIDO");

        jButton2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton2.setText("INGRESAR CUERPO DE AGUA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton3.setText("PROCESAR CUERPOS DE AGUA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton4.setText("CONSULTA Y OPERACIONES");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(444, 444, 444)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(jButton3)
                                .addGap(175, 175, 175)
                                .addComponent(jButton4)))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addGap(83, 83, 83)
                .addComponent(jLabel1)
                .addGap(109, 109, 109)
                .addComponent(jButton1)
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(220, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Botón de instrucciones
        String instrucciones = "SISTEMA DE LECTURA IRCA \n INSTRUCCIONES: \n"
                + "BIEVENIDO USUARIO, POR FAVOR LEE LAS INSTRUCCIONES PARA PODER USAR CORRECTAMENTE EL PROGRAMA"
                + "\n"
                + "\n En el panel principal, tienes las opciones de (INSTRUCCIONES, INGRESAR, CUERPO DE AGUA, PROCESAR CUERPOS DE AGUA Y CONSULTA Y OPERACIONES)"
                + "\n 1. EN LA SECCION DE INGRESAR CUERPO DE AGUA: "
                + "\n    o. Es un formulario con los datos necesarios para registrar un nuevo cuerpo de agua."
                + "\n 2. EN LA SECCIÓN DE PROCESAR CUERPOS DE AGUA"
                + "\n    o. Veras un panel donde hay dos áreas de texto, la primera debes dar click en la opción de obtener datos y verás la información de todos los cuerpos de agua, con el botón de procesar, en la segunda área de texto, verás el resultado del procesamiento de la información."
                + "\n 3. EN LA SECCIÓN DE CONSULTA Y OPERACIONES"
                + "\n    o. Verás un formulario donde podrás consultar un cuerpo de agua, cuando obtengas los datos, los verás en el formulario y podrás editarlos o eliminar el registro por completo"
                + "\n    o. POR FAVOR REVISA BIEN LOS DATOS ANTES DE ACTUALIZAR"
                + "\n"
                + "\n CUALQUIER EVENTO MALO QUE OCURRA EN EL PROGRAMA (ERORES) POR FAVOR COMUNICAR CON EL DESARROLLADOR Y TRABAJAREMOS EN SOLUCIONARLO, GRACIAS POR TU ATENCIÓN"
                + "\n"
                + "\n SE RECOMIENDA VER EL DIRECTORIO DEL CÓDIGO FUENTE PARA LOS DESARROLLADORES, AHÍ TAMBIÉNE ESTÁ EL DIRECTORIO DE DOCUMENTOS, SE RECOMIENDA QUE POR FAVOR REVISE LAS NOTAS QUE SE DEJARÓN PARA TENER EN CUENTA, GRACIAS"
                + "\n"
                + "\n REALIZADO POR: JUAN SEBASTIAN ARIAS (juansedev2 Github)"
                + "\n o CRÉDITOS DE DESARROLLO DE ESTE PROGRAMA AL AUTOR MENCIONADO ARRIBA"
                + "\n o Prohibida su distribución, alteración y/o manipulación sin acceso autorizado por el autor";
        JOptionPane.showMessageDialog(rootPane, instrucciones);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BOTÓN DE INGRESAR UN NUEVO CUERPO DE AGUA
        // Debemos llamar al formulario de agregar
        ViewPanelAgregar panel_agregar = new ViewPanelAgregar();
        panel_agregar.setVisible(true);
        // NOTA: PARA QUE LOS OTROS JFRAME NO CIERREN EL PROGRAMA PRINCIPAL, MODIFICAR LA PROPIEDAD DE DEFAULT CLOSE OPERATION EN DISPOSE
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // BOTÓN DE PROCESAR CUERPOS DE AGUA
        // Debemos llamar al panel de procesar los cuerpos de agua
        ViewPanelProcesar panel_procesar = new ViewPanelProcesar();
        panel_procesar.setVisible(true);
        // NOTA: PARA QUE LOS OTROS JFRAME NO CIERREN EL PROGRAMA PRINCIPAL, MODIFICAR LA PROPIEDAD DE DEFAULT CLOSE OPERATION EN DISPOSE
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // BOTÓN DE CONSULTAS Y OPERACIONES
        // DEBEMOS LLAMAR AL PANEL DE CONSULTAR Y OPERAR CON LOS CUERPOS DE AGUA
        ViewPanelConsulta panel_consulta = new ViewPanelConsulta();
        panel_consulta.setVisible(true);
        // NOTA: PARA QUE LOS OTROS JFRAME NO CIERREN EL PROGRAMA PRINCIPAL, MODIFICAR LA PROPIEDAD DE DEFAULT CLOSE OPERATION EN DISPOSE
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}