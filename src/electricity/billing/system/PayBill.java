package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PayBill extends JFrame implements ActionListener{
    
    Choice cmonth;
    JButton pay, back;
    String meter;
    PayBill(String meter){
        super("Pay Bill");
        this.meter = meter;
        setLayout(null);
        setBounds(300,150,900,600);
        
        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120,5,400,30);
        add(heading);
        
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(35,80,150,20);
        add(lblmeter);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(200,80,200,20);
        add(meternumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35,140,150,20);
        add(lblname);
        
        JLabel labelname = new JLabel("");
        labelname.setBounds(200,140,200,20);
        add(labelname);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35,200,150,20);
        add(lblmonth);
        
        cmonth = new Choice();
        cmonth.setBounds(200,200,200,20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);
        
        JLabel lblunits = new JLabel("Units");
        lblunits.setBounds(35,260,150,20);
        add(lblunits);
        
        JLabel labelunits = new JLabel("");
        labelunits.setBounds(200,260,200,20);
        add(labelunits);
        
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35,320,150,20);
        add(lbltotalbill);
        
        JLabel labeltotalbill = new JLabel("");
        labeltotalbill.setBounds(200,320,200,20);
        add(labeltotalbill);
        
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35,380,150,20);
        add(lblstatus);
        
        JLabel labelstatus = new JLabel("");
        labelstatus.setBounds(200,380,200,20);
        labelstatus.setForeground(Color.red);
        add(labelstatus);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meterno = '"+meter+"'");
            while(rs.next()) {
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));
                
            }
            
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
            while(rs.next()) {
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        cmonth.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
               try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meterno = '"+meter+"'");
                    while(rs.next()) {
                        meternumber.setText(meter);
                        labelname.setText(rs.getString("name"));

                    }

                    rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
                    while(rs.next()) {
                        labelunits.setText(rs.getString("units"));
                        labeltotalbill.setText(rs.getString("totalbill"));
                        labelstatus.setText(rs.getString("status"));
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        pay = new JButton("Pay");
        pay.setBackground(Color.blue);
        pay.setForeground(Color.white);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);
        
        back = new JButton("Back");
        back.setBackground(Color.red);
        back.setForeground(Color.black);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(Color.white);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,120,600,300);
        add(image);
         
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == pay) {
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' and month = '"+cmonth.getSelectedItem()+"'");
            } catch(Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String args[]) {
        new PayBill("");
    }
}
