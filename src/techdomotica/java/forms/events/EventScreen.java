/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techdomotica.java.forms.events;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import techdomotica.objs.Ambiente;
import techdomotica.objs.Conectar;
import techdomotica.objs.Event;
import techdomotica.objs.Perfil;
import techdomotica.objs.Util;

/**
 *
 * @author Andres
 */
public class EventScreen extends javax.swing.JFrame {

    /**
     * Creates new form EventScreen
     */
    private ArrayList<Event> eventosList = new ArrayList();
    //private ArrayList<Perfil> perfilList = new ArrayList();
    private Ambiente ambiente;
    private Conectar conx = null;
    
    private int selectedRow = 0;
    
    public EventScreen(Ambiente amb) {
        initComponents();
        ambiente = amb;
        conx = amb.getConnection();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/media/L4.png")).getImage());
        loadTable();
    }

    private void loadTable() {
        selectedRow = 0;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("#");
        model.addColumn("Perfil para el evento");
        model.addColumn("Fecha del evento");
        model.addColumn("Hora del evento");
        String query = "SELECT * FROM evento INNER JOIN perfil ON perfil.id_perfil = evento.id_perfil WHERE fecha >= CURRENT_DATE AND perfil.habilitado >= 1;";
        if(conx.executeRS(query)) {
            tableEvents.getTableHeader().setReorderingAllowed(false);
            conx.setBeforeFirst();
            eventosList.clear();
            while(conx.nextRow()) {
                Perfil perfil = new Perfil(techdomotica.objs.Util.parseInteger(conx.getResultSetRow("id_perfil")), techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp1")), techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp2")), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp1_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp2_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("proyector_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("sensor1_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("sensor2_on")) == 1));
                Event evento = new Event(Util.parseInteger(conx.getResultSetRow("id_evento")), LocalDate.parse(String.valueOf(conx.getResultSetRow("fecha"))), LocalTime.parse(String.valueOf(conx.getResultSetRow("hora"))), perfil);
                eventosList.add(evento);
                //perfilList.add(perfil);
                Object[] fila = {conx.getResultSetRow("id_evento"), conx.getResultSetRow("id_perfil"), conx.getResultSetRow("fecha"), conx.getResultSetRow("fecha")};
                model.addRow(fila);
            }
        }
        else {
            Object[] lol = {"No hay datos"};
            model.addRow(lol);
        }
        if(conx.getResultSet() != null) conx.destroyResultSet();
        if(eventosList.isEmpty()) {
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
        }
        else {
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
        }
        tableEvents.setModel(model);
        tableEvents.setRowSelectionInterval(0, 0);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEvents = new javax.swing.JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEvents = new javax.swing.JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eventos - Tech Domótica");
        setResizable(false);

        tableEvents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableEvents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEventsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableEvents);

        tableEvents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableEvents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEventsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableEvents);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Eventos del sistema");

        jButton1.setText("Eliminar evento");

        jButton2.setText("Modificar evento");

        jLabel3.setText("<html>Para ver los detalles del perfil seleccionado para el evento, has doble click en la fila escogida.</html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(153, 153, 153)
                                .addComponent(jButton1)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Añadir un evento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableEventsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEventsMouseClicked
        if(evt.getClickCount() == 2) {
            Perfil perfil = eventosList.get(selectedRow).getPerfilEvento();
            String msg = String.format("Temperatura del aire 1: %d°C.\nTemperatura del aire 2: %d°C.\n¿Aire acondicionado 1 encendido?: %s.\n¿Aire acondicionado 2 encendido?: %s.\n¿Proyector encendido?: %s.\n¿Sensor de puerta encendido?: %s.\n¿Sensor de movimiento encendido?: %s.\n", perfil.getTempAire1(), perfil.getTempAire2(), (perfil.isAire1On() ? "Si" : "No"), (perfil.isAire2On() ? "Si" : "No"),  (perfil.isProyectorOn() ? "Si" : "No"), (perfil.isSensor1On() ? "Si" : "No"), (perfil.isSensor2On() ? "Si" : "No"));
            JOptionPane.showMessageDialog(null, msg, "Perfil del evento #" + eventosList.get(selectedRow).getEventID(), JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            selectedRow = tableEvents.getSelectedRow();
        }
    }//GEN-LAST:event_tableEventsMouseClicked

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
            java.util.logging.Logger.getLogger(EventScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EventScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EventScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EventScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventScreen(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableEvents;
    // End of variables declaration//GEN-END:variables
}
