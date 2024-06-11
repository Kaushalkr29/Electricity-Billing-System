package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateInformation extends JFrame implements ActionListener{
    
    JTextField tfaddress, tfcity, tfstate, tfphone, tfemail;
    JLabel name;
    JButton update, cancel;
    String meter;
    UpdateInformation(String meter) {
        super("Update Information");
        this.meter = meter;
        setBounds(300,150,1050,450); 
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(110,0,400,30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30,70,100,20);
        add(lblname);
        
        name = new JLabel("");
        name.setBounds(230,70,200,20);
        add(name);
        
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(30,110,100,20);
        add(lblmeternumber);
        
        JLabel meterno = new JLabel("");
        meterno.setBounds(230,110,200,20);
        add(meterno);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(30,150,100,20);
        add(lbladdress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(230,150,200,20);
        add(tfaddress);
        
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(30,190,100,20);
        add(lblcity);
        
        tfcity = new JTextField();
        tfcity.setBounds(230,190,200,20);
        add(tfcity);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(30,230,100,20);
        add(lblstate);
        
        tfstate = new JTextField();
        tfstate.setBounds(230,230,200,20);
        add(tfstate);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(30,270,100,20);
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(230,270,200,20);
        add(tfphone);
        
        JLabel lblemail = new JLabel("E-mail");
        lblemail.setBounds(30,310,100,20);
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(230,310,200,20);
        add(tfemail);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meterno = '"+meter+"'");
            while(rs.next()){
                name.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
                meterno.setText(rs.getString("meterno"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        update = new JButton("Update");
        update.setBackground(Color.blue);
        update.setForeground(Color.white);
        update.setBounds(70,360,100,25);
        update.addActionListener(this);
        add(update);
         
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.red);
        cancel.setForeground(Color.white);
        cancel.setBounds(230,360,100,25);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550,50,400,300);
        add(image);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == update) {
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate("update customer set address = '"+address+"', city = '"+city+"', state = '"+state+"', phone = '"+phone+"', email = '"+email+"'where meterno = '"+meter+"'");
                
                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String args[]) {
        new UpdateInformation("");
    }
    
}
