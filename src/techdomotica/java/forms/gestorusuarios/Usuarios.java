package techdomotica.java.forms.gestorusuarios;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import techdomotica.objs.Ambiente;
import techdomotica.objs.Conectar;
import techdomotica.objs.Usuario;

/**
 *
 * @author SENA
 */
public class Usuarios extends javax.swing.JFrame {

    private Ambiente ambiente;
    private int selectedRow = -1;
    private ArrayList<Usuario> users = new ArrayList();
    private Conectar conx;
    
    public Usuarios(Ambiente amb) {
        ambiente = amb;
        conx = ambiente.getConnection();
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/resources/media/L4.png")).getImage());

        ImageIcon img = new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/L1.png")).getImage().getScaledInstance(200, 111, Image.SCALE_SMOOTH));
        Imageplace.setIcon(img);
        
        setLocationRelativeTo(null);
        loadTable();
        
    }

    private void loadTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("1er nombre");
        model.addColumn("2do nombre");
        model.addColumn("1er apellido");
        model.addColumn("2do apellido");
        model.addColumn("Email");
        model.addColumn("Cedula de ciudadanía");
        model.addColumn("Rol");
        String query = "";
        if(disabledUsersCheck.isSelected()) {
            model.addColumn("Estado");
            query = "SELECT * FROM usuario INNER JOIN rol ON rol.id_rol = usuario.id_rol WHERE 1;";
        }
        else {
            query = "SELECT * FROM usuario INNER JOIN rol ON rol.id_rol = usuario.id_rol AND habilitado = 1 WHERE 1;";
        }
        if(conx.executeRS(query)) {
            tableUsers.getTableHeader().setReorderingAllowed(false);            
            users.clear();
            while(conx.nextRow()) {
                Usuario user = new Usuario(String.valueOf(conx.getResultSetRow("id_usuario")), String.valueOf(conx.getResultSetRow("nom1")), String.valueOf(conx.getResultSetRow("nom2")), String.valueOf(conx.getResultSetRow("apellido1")), String.valueOf(conx.getResultSetRow("apellido2")), String.valueOf(conx.getResultSetRow("correo")), String.valueOf(conx.getResultSetRow("dni")), String.valueOf(conx.getResultSetRow("password")));
                users.add(user);
                Object[] fila;
                if(disabledUsersCheck.isSelected()){
                    String hab = (Integer.parseInt(String.valueOf(conx.getResultSetRow("habilitado"))) == 1) ? "Habilitado" : "Deshabilitado";
                    Object[] filaCustom = {conx.getResultSetRow("nom1"), conx.getResultSetRow("nom2"), conx.getResultSetRow("apellido1"), conx.getResultSetRow("apellido2"), conx.getResultSetRow("correo"), conx.getResultSetRow("dni"), conx.getResultSetRow("tipo_rol"), hab};
                    fila = java.util.Arrays.copyOf(filaCustom, filaCustom.length);
                }
                else {
                    Object[] filaCustom = {conx.getResultSetRow("nom1"), conx.getResultSetRow("nom2"), conx.getResultSetRow("apellido1"), conx.getResultSetRow("apellido2"), conx.getResultSetRow("correo"), conx.getResultSetRow("dni"), conx.getResultSetRow("tipo_rol")};
                    fila = java.util.Arrays.copyOf(filaCustom, filaCustom.length);
                }
                model.addRow(fila);
            }
            
        }
        else {
            Object[] lol = {"No hay datos"};
            model.addRow(lol);
        }
        if(conx.getResultSet() != null) conx.destroyResultSet();
        tableUsers.setRowSelectionInterval(0, 0);
        selectedRow = 0;
        tableUsers.setModel(model);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Imageplace = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        disabledUsersCheck = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Lista de usuarios - Tech Domotica");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mostrando todos los usuarios registrados");

        tableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableUsers);

        jButton1.setText("Borrar seleccionado");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Enviar correo electrónico");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Opciones de seleccionado:");

        jLabel3.setText("Haz doble click a un usuario para modificar.");

        disabledUsersCheck.setText("Mostrar usuarios deshabiitados");
        disabledUsersCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disabledUsersCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(Imageplace, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 32, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(disabledUsersCheck)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(Imageplace, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disabledUsersCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void tableUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUsersMouseClicked
        if(evt.getClickCount() == 2 && tableUsers.getSelectedRow() == selectedRow) {
            Modificar modify = new Modificar(ambiente) {
                @Override
                public void onLoad() {
                    tfd_Nombre1.setText(users.get(selectedRow).getNombre1());
                    tfd_Nombre2.setText(users.get(selectedRow).getNombre2());
                    tfd_Apellido1.setText(users.get(selectedRow).getApellido1());
                    tfd_Apellido2.setText(users.get(selectedRow).getApellido2());
                    tfd_correo.setText(users.get(selectedRow).getCorreo());
                    tfd_documento.setText(users.get(selectedRow).getDocumento());
                }
                
                @Override
                public void windowCloseCall() {
                    loadTable();
                    this.dispose();
                }
            };
            modify.setModifiedID(Integer.parseInt(users.get(selectedRow).getID()));
            modify.setVisible(true);
        }
        else if(evt.getClickCount() == 1) {
            selectedRow = tableUsers.getSelectedRow();
        }
    }//GEN-LAST:event_tableUsersMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int conf = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar al usuario " + users.get(selectedRow).getFullName() + "?\nEsta acción es irreversible.", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(conf == JOptionPane.YES_OPTION) {
            if(conx.execute("DELETE FROM usuario WHERE id_usuario = " + Integer.parseInt(users.get(selectedRow).getID()) +";") == 1) { 
                loadTable();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(java.awt.Desktop.isDesktopSupported()) {
            try {
                String subject = JOptionPane.showInputDialog(null, "Para enviar un correo electrónico, escribe el asunto del correo electrónico.\nTen en cuenta que esto abrirá la aplicación de correo de tu sistema operativo.", "Envío de correo electrónico", JOptionPane.QUESTION_MESSAGE);
                if(!subject.isEmpty()) {
                    String convertedSubject = subject.replace(" ", "%20");
                    System.out.println(convertedSubject);
                    java.awt.Desktop.getDesktop().mail(new java.net.URI(String.format("mailto:%s?subject=%s", users.get(selectedRow).getCorreo(), convertedSubject)));
                }
                else JOptionPane.showMessageDialog(null, "No deberías de enviar un correo electrónico sin asunto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
        else {
            int conf = JOptionPane.showConfirmDialog(null, "Tu sistema operativo no soporta esta funcionalidad, ¿te gustaría copiar el correo electrónico de " + users.get(selectedRow).getFullName() + "?", "Función no soportada", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(conf == JOptionPane.YES_OPTION) {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                clip.setContents(new java.awt.datatransfer.StringSelection(users.get(selectedRow).getCorreo()), null);
                JOptionPane.showMessageDialog(null, "Correo electrónico copiado.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void disabledUsersCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disabledUsersCheckActionPerformed
        if(disabledUsersCheck.isSelected()) {
            loadTable();
        }
        else {
            loadTable();
        }
    }//GEN-LAST:event_disabledUsersCheckActionPerformed
    //<editor-fold>
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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuarios(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Imageplace;
    private javax.swing.JCheckBox disabledUsersCheck;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUsers;
    // End of variables declaration//GEN-END:variables
}
