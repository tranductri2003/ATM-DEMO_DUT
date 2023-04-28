package Views;

import Controllers.CardController;
import Models.Card;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class PinChangeView  extends JFrame implements ActionListener {
    JLabel lb1,lb2;
    JTextField txtOld,txtNew;
    JButton btnOK;
    Card card;
    public PinChangeView(String st, Card card1){
        super(st);
        card = new Card(card1);
        GUI();
    }
    public void GUI(){
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setSize(500,300);
        lb1=new JLabel("Old Password");
        lb2=new JLabel("New Password");
        lb1.setHorizontalAlignment(SwingConstants.RIGHT);
        lb2.setHorizontalAlignment(SwingConstants.RIGHT);
        txtOld=new JTextField(5);
        txtNew=new JTextField(5);

        btnOK=new JButton("OK");
        btnOK.setBackground(Color.black);
        btnOK.setForeground(Color.white);
        btnOK.addActionListener(this);

        lb1.setBounds(0,50,150,50);
        lb2.setBounds(0,120,150,50);
        txtOld.setBounds(200,50,200,40);
        txtNew.setBounds(200,120,200,40);
        btnOK.setBounds(200,200,70,30);
        add(lb1);
        add(lb2);
        add(txtOld);
        add(txtNew);
        add(btnOK);
        show();
    }
    public void windowClosing(WindowEvent we) {
        dispose();
        System.exit(0);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnOK)
        {
            int confirm = JOptionPane.showConfirmDialog(this,"Are you sure?");
            if (confirm==0)
            {
                if (txtOld.getText().length()==0 || txtNew.getText().length()==0)
                {
                    JOptionPane.showMessageDialog(this, "You old password or new password is empty");
                }
                else
                {
                    try
                    {
                        if (txtOld.getText().compareTo(card.getPassword())==0)
                        {
                            if (txtOld.getText().compareTo(txtNew.getText())==0)
                            {
                                JOptionPane.showMessageDialog(this, "Your old password and your new password is the same");
                            }
                            else
                            {
                                card.setPassword(txtNew.getText());
                                CardController controller = new CardController();
                                controller.changePassword(card);
                                MainView MainView = new MainView("Main View", card);
                                dispose();
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this,"Your old password is not corrent!");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }
}
