package getInput;

import javax.swing.*;
import java.awt.event.*;  
import java.sql.*;
import java.awt.Color;

public class GetInput implements ActionListener
{
	JButton employee, exit;
	JFrame home;
	Connection conn;
	GetInput()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MG", "mahendra");
			if(conn!=null)
				System.out.println("Connected");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		home=new JFrame("Home");
		employee=new JButton("Employee");
		exit=new JButton("Exit");

		employee.setBounds(50, 100, 100, 20);
		exit.setBounds(250, 100, 100, 20);

		exit.setBackground(Color.RED);
		exit.setForeground(Color.WHITE);
		employee.setBackground(Color.GREEN);
		employee.setForeground(Color.WHITE);


		employee.addActionListener(this);
		exit.addActionListener(this);

		home.add(employee);
		home.add(exit);
		
		home.setSize(400, 300);
		home.setLocationRelativeTo(null);
		home.setLayout(null);
		home.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==employee)
		{
			getData();
		}
		else if(e.getSource()==exit)
			System.exit(0);
	}

	public void getData()
	{
		home.setVisible(false);
		JFrame data=new JFrame("Employee1");

		JLabel emp=new JLabel("Enter Employee ID");
		JLabel day=new JLabel("Enter day");
		JLabel month=new JLabel("Enter Month");
		JLabel year=new JLabel("Enter year");
		JLabel type=new JLabel("Enter Type");
		JLabel qty=new JLabel("Enter quantity");
		JLabel success=new JLabel();

		JButton submit=new JButton("Submit");
		JButton exit1=new JButton("Exit");

		JTextField t_emp=new JTextField();
		JTextField t_day=new JTextField();
		JTextField t_month=new JTextField();
		JTextField t_year=new JTextField();
		JTextField t_type=new JTextField();
		JTextField t_qty=new JTextField();

		emp.setBounds(50, 20, 130, 20);
		day.setBounds(50, 50, 100, 20);
		month.setBounds(50, 80, 100, 20);
		year.setBounds(50, 110, 100, 20);
		type.setBounds(50, 140, 100, 20);
		qty.setBounds(50, 170, 100, 20);
		success.setBounds(120, 230, 100, 20);

		t_emp.setBounds(180, 20, 100, 20);
		t_day.setBounds(180, 50, 100, 20);
		t_month.setBounds(180, 80, 100, 20);
		t_year.setBounds(180, 110, 100, 20);
		t_type.setBounds(180, 140, 100, 20);
		t_qty.setBounds(180, 170, 100, 20);

		submit.setBounds(50, 200, 100, 20);
		exit1.setBounds(180, 200, 100, 20);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
			{
				try
				{
					PreparedStatement stmt=conn.prepareStatement("insert into DataG values(?, ?, ?, ?, ?, ?)");
					stmt.setInt(1, Integer.parseInt(t_type.getText()));
					stmt.setInt(2, Integer.parseInt(t_qty.getText()));
					stmt.setInt(3, Integer.parseInt(t_emp.getText()));
					stmt.setInt(4, Integer.parseInt(t_day.getText()));
					stmt.setString(5, t_month.getText());
					stmt.setInt(6, Integer.parseInt(t_year.getText()));

					int rs=stmt.executeUpdate();
					success.setText("Record inserted successfully");
				}
				catch(Exception se){System.out.println(se);}
				// System.out.println("Hello MG");
			}
		});
		exit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2){
				data.setVisible(false);
				home.setVisible(true);
				try{conn.close();}
				catch(Exception ce){System.out.println(ce);}
			}
		});
		data.add(emp);
		data.add(day);
		data.add(month);
		data.add(year);
		data.add(type);
		data.add(qty);
		data.add(success);

		data.add(t_emp);
		data.add(t_day);
		data.add(t_month);
		data.add(t_year);
		data.add(t_type);
		data.add(t_qty);

		data.add(submit);
		data.add(exit1);
		
		data.setSize(400, 300);
		data.setLocationRelativeTo(null);
		data.setLayout(null);
		data.setVisible(true);
	}

	public static void main(String[] args) 
	{
		GetInput g=new GetInput();
	}
}