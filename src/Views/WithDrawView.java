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

    private JLabel lb1,lb2,lb3;
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
    	Color color = new Color(45, 191, 151);
        lb1=new JLabel("INPUT AMOUNT: ");
        lb1.setFont(new Font("Serif", Font.BOLD, 14));
        lb2= new JLabel("NOTE: Each withdrawal can only withdraw up to 10 million VNĐ");
        lb2.setForeground(Color.red);
        lb2.setFont(new Font("Serif", Font.BOLD, 12));
    	lb2.setHorizontalAlignment(JLabel.CENTER);
        lb2.setVerticalAlignment(JLabel.CENTER);
        lb3= new JLabel("withdrawal amount must be a multiple of 10 thousand VNĐ");
        lb3.setForeground(Color.red);
        lb3.setFont(new Font("Serif", Font.BOLD, 12));
    	lb3.setHorizontalAlignment(JLabel.CENTER);
        lb3.setVerticalAlignment(JLabel.CENTER);
        
        txtNum=new JTextField(7);
        //txtNum.setBounds(0,0,200,10);
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

        pn=new JPanel(new GridLayout(4,1));
        pn0=new JPanel(new GridLayout(2,1));
        pn0.add(lb2);
        pn0.add(lb3);

        pn1=new JPanel(new GridLayout(1,4));
        pn1.add(new JLabel());
        pn1.add(lb1);
        pn1.add(txtNum);
        pn1.add(new JLabel());
        
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(50); // đặt khoảng cách ngang là 10px
        flowLayout.setVgap(20); // đặt khoảng cách dọc là 20px
        pn3=new JPanel(flowLayout);
        pn3.add(btnOK);
        pn3.add(btnBack);
//
//        pn.add(new Label());
//        pn.add(pn0);
//        pn.add(pn1);
//
//        pn.add(pn3);
//		  add(pn);
        
        
        setSize(500,300);
        pn.add(pn0);
        pn.add(pn1);
        pn.add(pn3);
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
				WithDrawOptionView view = new WithDrawOptionView("With Draw Option", card);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            dispose();

        }
        else if (e.getSource()==btnOK)
        {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
            if (confirm==0)
            {
                double amount = Double.parseDouble(txtNum.getText());
                if (amount<=0 || amount>=10000000)
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
