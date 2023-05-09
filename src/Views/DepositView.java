package Views;

import Controllers.CardController;
import Models.Card;
import Models.CardModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class DepositView extends JFrame implements ActionListener {

    private JLabel lb1,lb2;
    private JTextField txtNum;
    private JButton btnOK,btnBack;
    private JPanel pn,pn0,pn1,pn3;
    private Card card;
    public DepositView(String s, Card card1)  {
        super(s);
        card = new Card(card1);
        GUI();
    }

    public void GUI(){
    	Color color = new Color(45, 191, 151);
        lb1=new JLabel("INPUT AMOUNT TO DEPOSIT");
        lb1.setHorizontalAlignment(JLabel.CENTER);
        lb1.setVerticalAlignment(JLabel.CENTER);
        lb2 = new JLabel("Amount of money must be a multiple of 10 thousand VNĐ");
        lb2.setForeground(Color.red);
        lb2.setFont(new Font("Serif", Font.BOLD, 12));
        lb2.setHorizontalAlignment(JLabel.CENTER);
        lb2.setVerticalAlignment(JLabel.CENTER);
        
        txtNum=new JTextField(5);
        txtNum.setBounds(0,0,200,15);
        txtNum.setForeground(Color.BLUE);
        txtNum.setFont(new Font("Arial", Font.PLAIN, 16));
        btnOK=new JButton("OK");
        btnOK.setBackground(color);
        btnOK.setForeground(Color.white);
        btnBack=new JButton("BACK");
        btnBack.setBackground(color);
        btnBack.setForeground(Color.white);
        btnOK.addActionListener(this);
        btnBack.addActionListener(this);

        pn=new JPanel(new GridLayout(6,1));
        pn0=new JPanel(new GridLayout(2,1));
        pn0.add(lb1);
        pn0.add(lb2);

        pn1=new JPanel(new GridLayout(1,3));
        pn1.add(new JLabel());
        pn1.add(txtNum);
        pn1.add(new JLabel());
        
        FlowLayout layout = new FlowLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        pn3=new JPanel(layout);
        pn3.add(btnOK);
        pn3.add(btnBack);

        pn.add(new Label());
        pn.add(pn0);
        pn.add(pn1);
        pn.add(new Label());
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
            int confirm = JOptionPane.showConfirmDialog(this,"Are you sure");
            if (confirm==0)
            {
                double ammount = Double.parseDouble(txtNum.getText());
                if (ammount<=0)
                {
                    JOptionPane.showMessageDialog(this, "Your information is invalid");
                }
                else
                {
                    card.setAmount(card.getAmount()+ammount);
                    CardController controller = new CardController();
                    try {
                        controller.changeAmount(card);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(this,String.format("Deposit successfully, your new amount is %.2f",card.getAmount()));
                    try {
                        MainView view = new MainView("Main View", card);
                    } catch (SQLException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    dispose();

                }
            }
        }
    }
}
