/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import controller.KioskCont;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author mattloidolt
 */
public class KioskPlaceOrder extends javax.swing.JFrame {

    ArrayList<String> order ;
    /**
     * Creates new form KioskPlaceOrder
     */
    public KioskPlaceOrder(ArrayList<String> order) {
        initComponents();
        this.order = order ;
        String output = "<html><h1> Order Summary </h1> <br> <table>" ;
        double total = 0 ;
        for(int i = 1; i < order.size(); i++){
            String[] item = order.get(i).split("-") ;
            output += "<tr> <td> " + item[0] + "</td><td></td><td> " + item[0] + "</td> </tr>" ;
            total += Double.parseDouble(item[1]) ;
        }
        output += "<tr> <td></td><td> TOTAL </td> <td>" + roundTwoDecimals(total) + "</td></tr></table></html>" ;
        orderTxt.setText(output);
    }
    
    static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name4 = new javax.swing.JTextField();
        orderTxt = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        cardName = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        cardNum = new javax.swing.JTextField();
        expDate = new javax.swing.JTextField();
        place = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        name4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name4ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pizzas'R'Us");
        setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());

        orderTxt.setText("<order>");
        orderTxt.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        orderTxt.setMinimumSize(new java.awt.Dimension(200, 600));

        jLabel1.setText("Name: ");

        jLabel2.setText("Phone:");

        jLabel3.setText("Address:");

        jLabel4.setText("Name On Card:");

        jLabel5.setText("Card Number:");

        jLabel6.setText("Expiration Date:");

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });

        cardName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardNameActionPerformed(evt);
            }
        });

        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });

        cardNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardNumActionPerformed(evt);
            }
        });

        expDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expDateActionPerformed(evt);
            }
        });

        place.setText("Finalize Order");
        place.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeActionPerformed(evt);
            }
        });

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(orderTxt, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(jLabel6)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                            .add(expDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(layout.createSequentialGroup()
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(phone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .add(71, 71, 71)
                                .add(name, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel4)
                                .add(jLabel3)
                                .add(jLabel5))
                            .add(18, 18, 18)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(cardNum, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(address, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(cardName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(cancel)
                        .add(29, 29, 29)
                        .add(place)))
                .add(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(27, 27, 27)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(name, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel2)
                            .add(phone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(address, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(20, 20, 20)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(cardName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel5)
                            .add(cardNum, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(expDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel6))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 317, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(place)
                            .add(cancel)))
                    .add(orderTxt, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        placeActionPerformed(evt) ;
    }//GEN-LAST:event_nameActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        placeActionPerformed(evt) ;
    }//GEN-LAST:event_phoneActionPerformed

    private void cardNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardNameActionPerformed
        placeActionPerformed(evt) ;
    }//GEN-LAST:event_cardNameActionPerformed

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        placeActionPerformed(evt) ;
    }//GEN-LAST:event_addressActionPerformed

    private void name4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name4ActionPerformed
        placeActionPerformed(evt) ;
    }//GEN-LAST:event_name4ActionPerformed

    private void cardNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardNumActionPerformed
        placeActionPerformed(evt) ;
    }//GEN-LAST:event_cardNumActionPerformed

    private void expDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expDateActionPerformed
        placeActionPerformed(evt) ;
    }//GEN-LAST:event_expDateActionPerformed

    private void placeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeActionPerformed
        // TODO enter code to save the order
        if(name.getText().equals("") || address.getText().equals("") ||
								phone.getText().equals("") || cardName.getText().equals("") ||
								cardNum.getText().equals("") || expDate.getText().equals("")) {
            JOptionPane.showOptionDialog(this, "You must fill in all fields.", "Error", JOptionPane.DEFAULT_OPTION, 
                            JOptionPane.ERROR_MESSAGE, null, null, evt) ;
        }
	else {
            if(phone.getText().length() != 10) {
                JOptionPane.showOptionDialog(this, "Invalid phone number. Use Format: 9991234567.", "Error", JOptionPane.DEFAULT_OPTION, 
                                        JOptionPane.ERROR_MESSAGE, null, null, evt) ;
            }
            else{
		if(cardNum.getText().length() != 16) {
                    JOptionPane.showOptionDialog(this, "Invalid Card Number. Use Format: 1111222233334444", "Error", JOptionPane.DEFAULT_OPTION, 
                                                JOptionPane.ERROR_MESSAGE, null, null, evt) ;
                }
		else {
                    //TODO: check for validity of expiration date
                    // Theoretically this is where we would do card authorization
                    JOptionPane.showOptionDialog(this, "Your order has been placed.", "Order Placed", JOptionPane.DEFAULT_OPTION, 
						JOptionPane.PLAIN_MESSAGE, null, null, evt) ;
                    // creating the output file for the kitchen display to read
                    ArrayList<String> output = new ArrayList<String>();
                    output.add(order.get(0)) ;
                    output.add(name.getText());
                    output.add(phone.getText());
                    output.add(address.getText()) ;
                    output.add(cardName.getText()) ;
                    output.add(cardNum.getText()) ;
                    output.add(expDate.getText()) ;
                    for(int i=1 ; i < order.size(); i++){
			output.add(order.get(i)) ;
                    }
                    KioskCont.saveOrder(output) ;
                    System.out.println("Order for " + output.get(1) + " has been placed.") ;
                    cancelActionPerformed(evt) ;
		}
            }
	}
    }//GEN-LAST:event_placeActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        Kiosk newKiosk = new Kiosk(new ArrayList<String>()) ;
        newKiosk.setVisible(true) ;
        this.dispose() ;
    }//GEN-LAST:event_cancelActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
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
            java.util.logging.Logger.getLogger(KioskPlaceOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KioskPlaceOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KioskPlaceOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KioskPlaceOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KioskPlaceOrder(new ArrayList<String>()).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField cardName;
    private javax.swing.JTextField cardNum;
    private javax.swing.JTextField expDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField name;
    private javax.swing.JTextField name4;
    private javax.swing.JLabel orderTxt;
    private javax.swing.JTextField phone;
    private javax.swing.JButton place;
    // End of variables declaration//GEN-END:variables
}
