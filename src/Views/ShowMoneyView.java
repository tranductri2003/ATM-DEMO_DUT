package Views;

import Models.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class ShowMoneyView extends JFrame implements ActionListener {
    JLabel lb1;
    JLabel lblMoney;
    JButton btnBack;

    Card card;
    public ShowMoneyView(String st, Card card1){
        super(st);
        card = new Card(card1);
        GUI(card);
    }
    public void GUI(Card card){
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setResizable(false);
        //setLayout(null);
        setSize(500,300);
        Color color = new Color(45, 191, 151);
        lb1=new JLabel("Your account balance is: ");
        lb1.setFont(new Font("Serif", Font.BOLD, 16));
        lb1.setHorizontalAlignment(SwingConstants.RIGHT);
        
        DecimalFormat formatter = new DecimalFormat("#,###.## VND");
        String Money = formatter.format(card.getAmount());
        
        lblMoney= new JLabel(Money);
        lblMoney.setForeground(color);
        lblMoney.setFont(new Font("Serif", Font.BOLD, 20));


        btnBack=new JButton("BACK");
        btnBack.setBackground(color);
        btnBack.setForeground(Color.white);
        btnBack.addActionListener(this);
        lb1.setBounds(0,100,200,50);
        lblMoney.setBounds(200,100,150,40);
        btnBack.setBounds(220,200,70,30);
        setBackground(Color.white);
        
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(100, 100));
        JLabel lblheader = new JLabel("ACCOUNT BALANCE");
        lblheader.setFont(new Font("Serif", Font.BOLD, 20));
        lblheader.setForeground(Color.white);
        header.add(lblheader);
        header.setBackground(color);
        header.setForeground(Color.white);
        
        JPanel Center = new JPanel();
        //Center.setLayout(new BorderLayout());
        //Center.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // set chế độ trái qua phải
        labelPanel.add(lb1);
        labelPanel.add(lblMoney);
        Center.add(labelPanel, BorderLayout.NORTH);
        
        JPanel footer = new JPanel();
        footer.add(btnBack,BorderLayout.SOUTH);
        
        

        
        JPanel pn = new JPanel(new GridLayout(4,1));
        pn.add(header);
        pn.add(new JPanel());
        pn.add(Center);
        pn.add(footer);
        
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
                MainView view = new MainView("Main View", card);
                dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
