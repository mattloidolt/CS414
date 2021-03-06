/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import controller.KioskCont;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author mattloidolt
 */
public class Kiosk extends javax.swing.JFrame {
    
    GridBagConstraints gBC = new GridBagConstraints() ;
    static String[] arguments ; // this is used to restart the program after an order is canceled/completed
    // adding items to the order should add to this list
    ArrayList<String> menus = KioskCont.getMenuNames() ;
    ArrayList<String> order = new ArrayList<String>() ;
    // format of menu:
    //
    // menu name
    // item on menu, example: burger-8.99
    // item on menu, example: salad-6.99
    // ...
    ArrayList<String> menu = new ArrayList<String>() ;
    
    /**
     * Creates new form Kiosk
     */
    public Kiosk(ArrayList<String> menu) {
        this.menu = menu ;
        initComponents();        
        
        ////// Building the menu bar for selecting menu
        for(int i = 0; i < menus.size() ; i++){
            final javax.swing.JMenuItem menuItem = new javax.swing.JMenuItem();
            menuItem.setText(menus.get(i));
            menuItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    menuItemActionPerformed(menuItem.getText());
                }
            });
            jMenu1.add(menuItem) ;
        }
        
        if (menu.size() >= 1) {
            menuNameLabel.setText(menu.get(0));
        }
        else {
            menuNameLabel.setText("No Menu Selected") ;
        }
        
        /////// placing the buttons based on the given menu
        jPanel2.setLayout(new GridBagLayout());
        gBC.gridx = 0 ;
        gBC.gridy = 0 ;
        gBC.fill = GridBagConstraints.NONE ;
        for(int i = 1; i < menu.size() ; i++) {
            JButton plus = new JButton("+") ;
            JButton minus = new JButton("-") ;
            plus.setMaximumSize(new Dimension(100, 100));
            minus.setMaximumSize(new Dimension(100, 100)) ;
            plus.setMinimumSize(new Dimension(40, 40)) ;
            minus.setMinimumSize(new Dimension(40, 40)) ;
            plus.setPreferredSize(new Dimension(60, 60));
            minus.setPreferredSize(new Dimension(60, 60));
            final String[] item = menu.get(i).split("-") ;
            plus.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    order.add(item[0] + "-" + item[1]) ;
                    updateOrder() ;
                }
            });
            minus.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    order.remove(item[0] + "-" + item[1]);
                    updateOrder() ;
                }
            }) ;
            JLabel label = new JLabel(item[0] + "\n$" + item[1]);
            jPanel2.add(plus, gBC) ;
            gBC.gridx++ ;
            jPanel2.add(label, gBC) ;
            gBC.gridx++ ;
            jPanel2.add(minus, gBC) ;
            gBC.gridx++ ;
            // 4 elements per row, unlimited rows
            if(gBC.gridx >= 12) {
                gBC.gridx = 0 ;
                gBC.gridy++ ;
            }
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

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        place = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        orderLabel = new javax.swing.JLabel();
        menuNameLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        menuSpecial = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 231, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pizzas'R'Us");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 204, 51));
        setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        setPreferredSize(new java.awt.Dimension(300, 200));

        jLabel1.setText("Menu: ");

        jLabel2.setText(getName());

        place.setText("Place Order");
        place.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeActionPerformed(evt);
            }
        });

        cancel.setText("Cancel Order");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        orderLabel.setText("No items yet");

        menuNameLabel.setText("<menuName>");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 32, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        menuSpecial.setText("Special: None");

        jMenu1.setText("Menus");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSeparator1)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(cancel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(place))
                    .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(menuNameLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel2)
                        .add(18, 18, 18)
                        .add(menuSpecial)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(orderLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 250, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(menuNameLabel)
                    .add(menuSpecial))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(orderLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(35, 35, 35))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(cancel)
                            .add(place))
                        .addContainerGap())))
        );

        getAccessibleContext().setAccessibleDescription("UserKiosk");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateOrder(){
        String output = "<html><h1> Order Summary </h1> <br> <table>" ;
        double total = 0 ;
        for(int i = 0; i < order.size(); i++){
            String[] item = order.get(i).split("-") ;
            output += "<tr> <td> " + item[0] + "</td><td></td><td> $" + item[1] + "</td> </tr>" ;
            total += Double.parseDouble(item[1]) ;
        }
        output += "<tr> <td></td><td> TOTAL </td> <td>$" + roundTwoDecimals(total) + "</td></tr></table></html>" ;
        orderLabel.setText(output);
    }
    
    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.00");
        return Double.valueOf(twoDForm.format(d));
    }
    
    private void menuItemActionPerformed(String menu) {
        Kiosk newKiosk = new Kiosk(KioskCont.getMenu(menu)) ;
        newKiosk.setVisible(true);
        this.dispose();
    }
    
    private void placeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeActionPerformed
        KioskPlaceOrder placeScreen = new KioskPlaceOrder(order) ;
        placeScreen.setTitle("New Order") ;
        placeScreen.setVisible(true) ;
        this.dispose();
    }//GEN-LAST:event_placeActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        restartProgram() ;
    }//GEN-LAST:event_cancelActionPerformed

    
    public void restartProgram()
    {
		// pull this programs ID from the args and build the command to execute
		StringBuilder cmd = new StringBuilder();
		cmd.append(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
		for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
                    cmd.append(jvmArg + " ");
		}
		cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
		cmd.append(Kiosk.class.getName()).append(" ");
		for (String arg : arguments) {
                    cmd.append(arg).append(" ");
		}
		// execute the command to start this program again
		try {
                    Runtime.getRuntime().exec(cmd.toString());
		} catch (IOException e) {
                    System.err.println(e) ;
		}
		// then end this program
		System.exit(0);
    }
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
            java.util.logging.Logger.getLogger(Kiosk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kiosk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kiosk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kiosk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        arguments = args ;

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Kiosk(new ArrayList<String>()).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel menuNameLabel;
    private javax.swing.JLabel menuSpecial;
    private javax.swing.JLabel orderLabel;
    private javax.swing.JButton place;
    // End of variables declaration//GEN-END:variables

    
}
