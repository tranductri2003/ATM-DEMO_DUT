package Views;

import Controllers.AccountController;
import Models.Account;
import Models.Card;
import Models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class DetailView  extends JFrame implements ActionListener {
    private JLabel lb1,lb2,lb3,lb4;
    private JLabel lblName,lblDate,lblCCCD,lblBank;
    private JButton btnBack;
    private JPanel pn,pn1,pn2,pn3,pn4,pn5;
    Customer customer;
    Card card;
    Account account;
    public DetailView(String s, Customer customer1, Card card1) throws SQLException, ClassNotFoundException {
        super(s);
        customer = new Customer(customer1);
        card = new Card(card1);
        GUI();
        AccountController controller = new AccountController();
        account = controller.getAccount(card);
        lblBank.setText(account.getBankName());
//        lblName.setForeground(color);
        lblName.setFont(new Font("Serif", Font.BOLD, 20));
    }
    public void GUI(){
    	Color color = new Color(45, 191, 151);
    	lb1=new JLabel("Họ tên khách hàng: ");
    	lb1.setFont(new Font("Serif", Font.BOLD, 20));
    	lb1.setHorizontalAlignment(JLabel.CENTER);
        lb1.setVerticalAlignment(JLabel.CENTER);
        lb2=new JLabel("Ngày sinh: ");
        lb2.setFont(new Font("Serif", Font.BOLD, 20));
        lb2.setHorizontalAlignment(JLabel.CENTER);
        lb2.setVerticalAlignment(JLabel.CENTER);
        lb3=new JLabel("CCCD: ");
        lb3.setFont(new Font("Serif", Font.BOLD, 20));
        lb3.setHorizontalAlignment(JLabel.CENTER);
        lb3.setVerticalAlignment(JLabel.CENTER);
        lb4=new JLabel("Ngân hàng mở thẻ: ");
        lb4.setFont(new Font("Serif", Font.BOLD, 20));
        lb4.setHorizontalAlignment(JLabel.CENTER);
        lb4.setVerticalAlignment(JLabel.CENTER);
        
        lblName= new JLabel(customer.getName());
        lblName.setFont(new Font("Serif", Font.BOLD, 20));
        lblName.setHorizontalAlignment(JLabel.CENTER);
        lblName.setVerticalAlignment(JLabel.CENTER);
        lblName.setForeground(color);
        lblDate= new JLabel((customer.getDateOfBirth().toString()));
        lblDate.setForeground(color);
        lblDate.setFont(new Font("Serif", Font.BOLD, 20));
        lblDate.setHorizontalAlignment(JLabel.CENTER);
        lblDate.setVerticalAlignment(JLabel.CENTER);
        lblCCCD= new JLabel(customer.getCitizenID());
        lblCCCD.setForeground(color);
        lblCCCD.setFont(new Font("Serif", Font.BOLD, 20));
        lblCCCD.setHorizontalAlignment(JLabel.CENTER);
        lblCCCD.setVerticalAlignment(JLabel.CENTER);
        lblBank= new JLabel("CHUA BIET");
        lblBank.setForeground(color);
        lblBank.setFont(new Font("Serif", Font.BOLD, 20));
        lblBank.setHorizontalAlignment(JLabel.CENTER);
        lblBank.setVerticalAlignment(JLabel.CENTER);
        btnBack=new JButton("Back");
        btnBack.setBackground(color);
        btnBack.setForeground(Color.white);
        btnBack.addActionListener(this);
        

        pn=new JPanel(new GridLayout(5,1));
        pn1=new JPanel(new GridLayout(1,2));
        pn1.add(lb1);
        pn1.add(lblName);
        pn1.setAlignmentX(Component.CENTER_ALIGNMENT);
        pn1.setAlignmentY(Component.CENTER_ALIGNMENT);

        pn2=new JPanel(new GridLayout(1,2));
        pn2.add(lb2);
        pn2.add(lblDate);
        pn2.setAlignmentX(Component.CENTER_ALIGNMENT);
        pn2.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        pn3=new JPanel(new GridLayout(1,2));
        pn3.add(lb3);
        pn3.add(lblCCCD);
        pn3.setAlignmentX(Component.CENTER_ALIGNMENT);
        pn3.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        pn4=new JPanel(new GridLayout(1,2));
        pn4.add(lb4);
        pn4.add(lblBank);
        pn4.setAlignmentX(Component.CENTER_ALIGNMENT);
        pn4.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        pn5=new JPanel(new FlowLayout());
        pn5.add(btnBack);
        pn5.setAlignmentX(Component.CENTER_ALIGNMENT);
        pn5.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        
        pn.add(pn1);
        pn.add(pn2);
        pn.add(pn3);
        pn.add(pn4);
        pn.add(pn5);

        add(pn);
        setSize(500,300);
        setLocationRelativeTo(null);
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
    }
}
