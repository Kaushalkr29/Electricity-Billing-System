package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener{
    
    JTextField tfname, tfaddress, tfcity, tfstate, tfphone, tfemail;
    JButton next, cancel;
    JLabel lblmeterno;
    Choice meterlocation , metertype, phasecode, contype;
    String meternumber;
    MeterInfo(String meternumber){
        super("Meter Information");
        this.meternumber = meternumber;
        
        setSize(700,500);
        setLocation(400, 200);
        
        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,217,230));
        add(p);
        
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tehoma", Font.PLAIN,24));
        p.add(heading);
        
        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);
        
        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(240,80,100,20);
        p.add(lblmeternumber);
        
        JLabel lblmeter = new JLabel("Meter Location");
        lblmeter.setBounds(100,120,100,20);
        p.add(lblmeter); 
        
        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240,120,200,20);
        p.add(meterlocation);
        
        JLabel lbltype = new JLabel("Meter Type");
        lbltype.setBounds(100,160,100,20);
        p.add(lbltype);
        
        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240,160,200,20);
        p.add(metertype);
        
        JLabel lblphase = new JLabel("Phase Code");
        lblphase.setBounds(100,200,100,20);
        p.add(lblphase);
        
        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240,200,200,20);
        p.add(phasecode);
        
        JLabel lblcon = new JLabel("Connection Type");
        lblcon.setBounds(100,240,100,20);
        p.add(lblcon);
        
        contype = new Choice();
        contype.add("HouseHold");
        contype.add("Industrial");
        contype.setBounds(240,240,200,20);
        p.add(contype);
        
        JLabel lblday = new JLabel("Days");
        lblday.setBounds(100,280,100,20);
        p.add(lblday);
        
        JLabel lbldays = new JLabel("30 Days");
        lbldays.setBounds(240,280,100,20);
        p.add(lbldays);
        
        JLabel lblnote = new JLabel("Note");
        lblnote.setBounds(100,320,100,20);
        p.add(lblnote);
        
        JLabel lblnotes = new JLabel("By default Bill is calculated for the last 30 days only ");
        lblnotes.setBounds(240,320,500,20);
        p.add(lblnotes);
        
        next = new JButton("Submit");
        next.setBounds(220,390,100,25);
        next.setBackground(Color.blue);
        next.setForeground(Color.white);
        next.addActionListener(this);
        p.add(next);
        
        
        
        setLayout(new BorderLayout());
        
        add(p,"Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image,"West");
        
        getContentPane().setBackground(Color.white);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){
            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String conn = contype.getSelectedItem();
            String days = "30";
            
            String query = "insert into meter_info values('"+meter+"', '"+location+"', '"+type+"', '"+code+"', '"+conn+"', '"+days+"')";
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                setVisible(false);
             
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new MeterInfo("");
    }
}
