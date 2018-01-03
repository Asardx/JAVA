# JAVA-
Student Registration
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.sql.*;

class frame extends JFrame{
    Container c;
    String[] columns = {"Name", "Dept", "Number"};

    Object[][] data = {
        {"MBF", "CITYGROUP", 10.16},
        {"MBL", "BANK OF AMERICA", 12.66},
        {"MJP", "Morgan Stanley Dean Witter & Co.", 24.97}
    };
    JLabel uLabel=new JLabel("User Name");   
    JLabel pLabel =new JLabel("Email"); 
    JLabel cLabel =new JLabel("Contact");     
    JTextField uname=new JTextField();  
    JTextField pass=new JTextField();
    JTextField contact=new JTextField();  
    JButton loginbtn=new JButton("Register"); 
    frame(){
        c = this.getContentPane();   
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model); 
        table.setFillsViewportHeight(true);
        c.setLayout(null);       
        uLabel.setBounds(20,30,150,40);     
        pLabel.setBounds(250,30,150,40);
        cLabel.setBounds(470,30,150,40);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_test","root","");
            Statement st = (Statement) con.createStatement();
            String query = " SELECT * FROM student_login ";     
            ResultSet result_set = st.executeQuery(query); 
            String roll= "";
            String name= "";
            String cl = "";
            String sec = ""; 
            int i=0;
            while(result_set.next()){
                roll = result_set.getString("name");
                name = result_set.getString("Dept");
                cl = result_set.getString("Number");
                model.addRow(new Object[]{roll, name, cl});
                i++; 
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null," Error : "+ex.getMessage());
        } 
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,200,680,230);

        uname.setBounds(20,70,200,40);
        pass.setBounds(250,70,200,40); 
        contact.setBounds(470,70,200,40); 

        loginbtn.setBounds(270,120,140,50);

        Font f=new Font("Arial", Font.BOLD,18);  
        uLabel.setFont(f);
        pLabel.setFont(f);
        cLabel.setFont(f); 
        uname.setFont(f);
        pass.setFont(f);
        contact.setFont(f);


        JLabel lblHeading = new JLabel("Stock Quotes");
        lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
        
        c.add(scrollPane); 

        c.add(uLabel);
        c.add(pLabel);
        c.add(cLabel);
        c.add(uname);
        c.add(pass);
        c.add(contact);
        c.add(loginbtn);  
        loginbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String username = uname.getText();
                String password = pass.getText();
                String contact_num = contact.getText();

                //create connection
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_test","root","");
                    Statement st = (Statement) con.createStatement();
                    String query = " insert into student_login (name, Dept, Number) values ('"+username+"', '"+password+"', "+contact_num+")";
                    int result = st.executeUpdate(query);
                    if(result == 1){
                        model.addRow(new Object[]{username, password, contact_num});
                        JOptionPane.showMessageDialog(null, "Updated Successfully.");
                    }else{
                        JOptionPane.showMessageDialog(null, "Error : Something Went Wrong.");
                    }
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null," Error : "+ex.getMessage());
                }
            }
        });
    }  
}

public abstract class Project1 extends Frame implements ActionListener{
    public static void main(String[] args) {
        frame frame=new frame();   
        frame.setVisible(true);
        frame.setTitle("Login Form Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,700,500);
    } 
}
