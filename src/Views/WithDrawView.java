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

public class WithDrawView extends JFrame implements ActionListener {

    private JLabel lb1;
    private JTextField txtNum;
    private JButton btnOK,btnBack;
    private JPanel pn,pn0,pn1,pn3;

    Card card;

    public WithDrawView(String s, Card card1)  {
        super(s);
        card = new Card(card1);
        GUI();
    }

    public void GUI(){
        lb1=new JLabel("INPUT AMOUNT TO WITHDRAW");

        txtNum=new JTextField(5);
        txtNum.setBounds(0,0,200,15);
        txtNum.setForeground(Color.BLUE);
        txtNum.setFont(new Font("Arial", Font.PLAIN, 16));
        btnOK=new JButton("OK");
        btnOK.setBackground(Color.black);
        btnOK.setForeground(Color.white);
        btnBack=new JButton("BACK");
        btnBack.setBackground(Color.black);
        btnBack.setForeground(Color.white);
        btnOK.addActionListener(this);
        btnBack.addActionListener(this);

        pn=new JPanel(new GridLayout(5,1));
        pn0=new JPanel(new FlowLayout());
        pn0.add(lb1);

        pn1=new JPanel(new GridLayout(1,3));
        pn1.add(new JLabel());
        pn1.add(txtNum);
        pn1.add(new JLabel());

        pn3=new JPanel(new FlowLayout());
        pn3.add(btnOK);
        pn3.add(btnBack);

        pn.add(new Label());
        pn.add(pn0);
        pn.add(pn1);

        pn.add(pn3);

        setSize(500,300);
        add(pn);
        show();


    }
    public void windowClosing(WindowEvent we) {
        dispose();
        System.exit(0);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnBack)
        {
            try {
                MainView MainView = new MainView("Main View", card);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            dispose();

        }
        else if (e.getSource()==btnOK)
        {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
            if (confirm==0)
            {
                double amount = Double.parseDouble(txtNum.getText());
                if (amount<=0)
                {
                    JOptionPane.showMessageDialog(this,"Your information is not valid");
                }
                else
                {
                    if (amount>card.getAmount())
                    {
                        JOptionPane.showMessageDialog(this,"Not enough money");
                    }
                    else
                    {
                        card.setAmount(card.getAmount()-amount);
                        CardController controller = new CardController();
                        try {
                            controller.changeAmount(card);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(this,String.format("With draw successfully, your new amount is %.2f",card.getAmount()));
                        try {
                            MainView view=new MainView("Main View",card);
                            dispose();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
                    }
                }
            }
        }
    }
}
