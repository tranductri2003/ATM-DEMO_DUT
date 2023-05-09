package Views;

import Controllers.CardController;
import Controllers.CustomerController;
import Models.Card;
import Models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class WithDrawOptionView  extends JFrame implements ActionListener {
    private JButton btn5trieu, btn2trieu, btn5tram, btn2tram, btn1trieu,btn1tram, btnKhac,btnBack;
    private JLabel lblName;
    private JPanel pn,pn1,pn2,pn3,pn4,pn5;

    Card card;
    Customer customer;
    public WithDrawOptionView(String s, Card card1) throws SQLException, ClassNotFoundException {
        super(s);
        card = new Card(card1);
        CustomerController controller = new CustomerController();
        customer = controller.getUser(card.getAccountID());
        GUI(customer);
    }
    public void GUI(Customer customer){
        lblName=new JLabel("Welcome "+customer.getName());
        lblName.setFont(new Font("Serif", Font.BOLD, 20));
        lblName.setForeground(Color.white);
        lblName.setHorizontalAlignment(JLabel.CENTER);
        lblName.setVerticalAlignment(JLabel.CENTER);

        btn5trieu =new JButton("5,000,000 VNĐ");
        btn5trieu.setPreferredSize(new Dimension(180, 50));
        btn1trieu=new JButton("1,000,000 VNĐ");
        btn1trieu.setPreferredSize(new Dimension(180, 50));
        btn2tram=new JButton("200,000 VNĐ");
        btn2tram.setPreferredSize(new Dimension(180, 50));
        btn2trieu=new JButton("2,000,000 VNĐ");
        btn2trieu.setPreferredSize(new Dimension(180, 50));
        btn5tram=new JButton("500,000 VNĐ");
        btn5tram.setPreferredSize(new Dimension(180, 50));
        btn1tram=new JButton("100,000 VNĐ");
        btn1tram.setPreferredSize(new Dimension(180, 50));
        btnKhac=new JButton("Lựa chọn khác");
        btnKhac.setPreferredSize(new Dimension(180, 50));
        btnBack=new JButton("<- BACK ");
        btnBack.setPreferredSize(new Dimension(180, 50));
        
        Color color = new Color(45, 191, 151);
        
        btn5trieu.setBackground(color);
        btn5trieu.setForeground(Color.white);
        btn1trieu.setBackground(color);
        btn1trieu.setForeground(Color.white);
        btn1tram.setBackground(color);
        btn1tram.setForeground(Color.white);
        btn2tram.setBackground(color);
        btn2tram.setForeground(Color.white);
        btn2trieu.setBackground(color);
        btn2trieu.setForeground(Color.white);
        btn5tram.setBackground(color);
        btn5tram.setForeground(Color.white);
        btnKhac.setBackground(color);
        btnKhac.setForeground(Color.white);
        btnBack.setBackground(color);
        btnBack.setForeground(Color.white);

        btn5trieu.addActionListener(this);
        btn2trieu.addActionListener(this);
        btn1trieu.addActionListener(this);
        btn5tram.addActionListener(this);
        btn2tram.addActionListener(this);
        btn1tram.addActionListener(this);
        btnKhac.addActionListener(this);
        btnBack.addActionListener(this);
        


        pn=new JPanel(new GridLayout(9,1));
        

        pn1=new JPanel(new BorderLayout());
        pn1.add(lblName,BorderLayout.CENTER);
        pn1.setBackground(color);

        pn2=new JPanel(new BorderLayout());
        pn2.add(btn5trieu,BorderLayout.WEST);
        pn2.add(btn2trieu,BorderLayout.EAST);

        pn3=new JPanel(new BorderLayout());
        pn3.add(btn1trieu,BorderLayout.WEST);
        pn3.add(btn5tram,BorderLayout.EAST);

        pn4=new JPanel(new BorderLayout());
        pn4.add(btn2tram,BorderLayout.WEST);
        pn4.add(btn1tram,BorderLayout.EAST);
        
        pn5=new JPanel(new BorderLayout());
        pn5.add(btnBack,BorderLayout.WEST);
        pn5.add(btnKhac,BorderLayout.EAST);
        
        pn2.setBackground(Color.white);
        pn3.setBackground(Color.white);
        pn4.setBackground(Color.white);
        pn5.setBackground(Color.white);
        
        pn.setBackground(Color.white);
        pn.add(pn1);
        
        JLabel option = new JLabel("Please, Select amount of Money to withdraw!");
        option.setHorizontalAlignment(JLabel.CENTER);
        option.setVerticalAlignment(JLabel.CENTER);
        pn.add(option);
        pn.add(pn2);
        pn.add(new Panel());
        pn.add(pn3);
        pn.add(new Panel());
        pn.add(pn4);
        pn.add(new Panel());
        pn.add(pn5);
        add(pn);
//        setLocationRelativeTo(null);
        setSize(500,300);
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
                MainView view = new MainView("Main View",card);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        }
        else if (e.getSource()==btnKhac)
        {
            WithDrawView view = new WithDrawView("WithDraw",card);
            dispose();
        }
        else if (e.getSource()==btn5trieu) {
        	int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
        	if (confirm==0)
	        	if (5000000>card.getAmount())
	            {
	                JOptionPane.showMessageDialog(this,"Not enough money");
	            }
	            else
	            {
	                card.setAmount(card.getAmount()-5000000);
	                CardController controller = new CardController();
	                try {
	                    controller.changeAmount(card);
	                } catch (ClassNotFoundException ex) {
	                    throw new RuntimeException(ex);
	                } catch (SQLException ex) {
	                    throw new RuntimeException(ex);
	                }
	                JOptionPane.showMessageDialog(this,String.format("With draw successfully, your new amount is %.2f",card.getAmount()));
	            }
        }
        else if (e.getSource()==btn2trieu) {
        	int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
        	if (confirm==0)
        	if (2000000>card.getAmount())
            {
                JOptionPane.showMessageDialog(this,"Not enough money");
            }
            else
            {
                card.setAmount(card.getAmount()-2000000);
                CardController controller = new CardController();
                try {
                    controller.changeAmount(card);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this,String.format("With draw successfully, your new amount is %.2f",card.getAmount()));
            }
        }
        else if (e.getSource()==btn1trieu) {
        	int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
        	if (confirm==0)
        	if (1000000>card.getAmount())
            {
                JOptionPane.showMessageDialog(this,"Not enough money");
            }
            else
            {
                card.setAmount(card.getAmount()-1000000);
                CardController controller = new CardController();
                try {
                    controller.changeAmount(card);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this,String.format("With draw successfully, your new amount is %.2f",card.getAmount()));
            }
        }
        else if (e.getSource()==btn5tram) {
        	int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
        	if (confirm==0)
        	if (500000>card.getAmount())
            {
                JOptionPane.showMessageDialog(this,"Not enough money");
            }
            else
            {
                card.setAmount(card.getAmount()-500000);
                CardController controller = new CardController();
                try {
                    controller.changeAmount(card);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this,String.format("With draw successfully, your new amount is %.2f",card.getAmount()));
            }
        }
        else if (e.getSource()==btn2tram) {
        	int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
        	if (confirm==0)
	        	if (200000>card.getAmount())
	            {
	                JOptionPane.showMessageDialog(this,"Not enough money");
	            }
	            else
	            {
	                card.setAmount(card.getAmount()-200000);
	                CardController controller = new CardController();
	                try {
	                    controller.changeAmount(card);
	                } catch (ClassNotFoundException ex) {
	                    throw new RuntimeException(ex);
	                } catch (SQLException ex) {
	                    throw new RuntimeException(ex);
	                }
	                JOptionPane.showMessageDialog(this,String.format("With draw successfully, your new amount is %.2f",card.getAmount()));
	            }
        }
        else if (e.getSource()==btn1tram) {
        	int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
        	if (confirm==0)
        	if (100000>card.getAmount())
            {
                JOptionPane.showMessageDialog(this,"Not enough money");
            }
            else
            {
                card.setAmount(card.getAmount()-100000);
                CardController controller = new CardController();
                try {
                    controller.changeAmount(card);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this,String.format("With draw successfully, your new amount is %.2f",card.getAmount()));
            }
        }
        
    }
}
