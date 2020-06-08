package salary_pak;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
public class Salary 
{
	JFrame main;
	JButton show, exit;
	Connection conn;
	JTextField emp_id;
	JTextField enter_month;
	JTextField enter_year;
	JComboBox<String> jcb;

	String choice;
	Salary()
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

		main=new JFrame("Show data window");
		show=new JButton("Show Data");
		exit=new JButton("Exit");

		show.setBounds(50, 100, 100, 20);
		exit.setBounds(180, 100, 100, 20);

		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent showev)
			{
				showData();
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exitev)
			{
				System.exit(0);
			}
		});
		main.add(show);
		main.add(exit);
		
		main.setSize(400, 300);
		main.setLocationRelativeTo(null);
		main.setLayout(null);
		main.setVisible(true);
	}

	public void showData()
	{
		JFrame que=new JFrame("Show Data");
		JLabel enter_id=new JLabel("Enter employee id :");
		emp_id=new JTextField();
		JLabel month=new JLabel("Enter Month :");
		enter_month=new JTextField();
		JLabel year=new JLabel("Enter Year");
		enter_year=new JTextField();		
		JButton submit=new JButton("Submit");
		JLabel choose_dur=new JLabel("Choose duation");

		String[] dur={"1 to 15", "16 to 31"};
		jcb=new JComboBox<String>(dur);

		enter_id.setBounds(50, 50, 150, 20);
		emp_id.setBounds(170, 50, 150, 20);
		month.setBounds(50, 80, 150, 20);
		enter_month.setBounds(170, 80, 150, 20);
		year.setBounds(50, 110, 150, 20);
		enter_year.setBounds(170, 110, 150, 20);
		choose_dur.setBounds(50, 140, 150, 20);
		jcb.setBounds(170, 140, 150, 20);
		submit.setBounds(150, 170, 100, 20);

		jcb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent jc)
			{
				choice=""+jcb.getItemAt(jcb.getSelectedIndex());
			}
		});

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eub)
			{
				showDetails();
			}
		});

		que.add(enter_id);
		que.add(emp_id);
		que.add(month);
		que.add(enter_month);
		que.add(submit);
		que.add(year);
		que.add(enter_year);
		que.add(choose_dur);
		que.add(jcb);
		
		que.setSize(400, 300);
		que.setLocationRelativeTo(null);
		que.setLayout(null);
		que.setVisible(true);
	}

	public void showDetails()
	{
		JFrame details=new JFrame("Details");
		JLabel emp_name=new JLabel("Employee Name:");
		JLabel tot_envs=new JLabel("Total Envelops:");
		JLabel tot_sal=new JLabel("Total Salary:");
		JLabel name=new JLabel();
		JLabel envs=new JLabel();
		JLabel sal=new JLabel();

		emp_name.setBounds(20, 50, 100, 20);
		tot_envs.setBounds(20, 80, 150, 20);
		tot_sal.setBounds(20, 110, 100, 20);
		name.setBounds(120, 50, 150, 20);
		envs.setBounds(120, 80, 500, 20);
		sal.setBounds(120, 110, 150, 20);

		details.add(emp_name);
		details.add(tot_envs);
		details.add(tot_sal);
		details.add(name);
		details.add(envs);
		details.add(sal);

		details.setSize(600, 200);
		details.setLocationRelativeTo(null);
		details.setLayout(null);
		details.setVisible(true);

		try
		{
			if(choice.equals("1 to 15"))
			{
				double sum1=0d;
				PreparedStatement pstmt1=conn.prepareStatement("select quantity from DataG where Employee_id=? and month=? and year=? and type=? and day between 1 and 15");
				pstmt1.setInt(1, Integer.parseInt(emp_id.getText()));
				pstmt1.setString(2, enter_month.getText());
				pstmt1.setInt(3, Integer.parseInt(enter_year.getText()));
				pstmt1.setInt(4, 1);
				ResultSet rs1=pstmt1.executeQuery();
				while(rs1.next())
				{
					sum1+=(Double.parseDouble(rs1.getString(1)));
				}

				double sum2=0d;
				PreparedStatement pstmt2=conn.prepareStatement("select quantity from DataG where Employee_id=? and month=? and year=? and type=? and day between 1 and 15");
				pstmt2.setInt(1, Integer.parseInt(emp_id.getText()));
				pstmt2.setString(2, enter_month.getText());
				pstmt2.setInt(3, Integer.parseInt(enter_year.getText()));
				pstmt2.setInt(4, 2);
				ResultSet rs2=pstmt2.executeQuery();
				while(rs2.next())
				{
					sum2+=(Double.parseDouble(rs2.getString(1)));
				}

				PreparedStatement pstmt3=conn.prepareStatement("select Employee_name from GEmployee where Employee_id=?");
				pstmt3.setInt(1, Integer.parseInt(emp_id.getText()));
				ResultSet rs3=pstmt3.executeQuery();
				rs3.next();

				name.setText(rs3.getString(1));
				envs.setText("Envelops of type 1 are :"+sum1+" "+"Envelops of type 2 are :"+sum2);
				double total=((sum1/1000)*30)+((sum2/1000)*40);
				sal.setText("Total salary is :"+total);
			}

			if(choice.equals("16 to 31"))
			{
				double sum1=0d;
				PreparedStatement pstmt1=conn.prepareStatement("select quantity from DataG where Employee_id=? and month=? and year=? and type=? and day between 1 and 15");
				pstmt1.setInt(1, Integer.parseInt(emp_id.getText()));
				pstmt1.setString(2, enter_month.getText());
				pstmt1.setInt(3, Integer.parseInt(enter_year.getText()));
				pstmt1.setInt(4, 1);
				ResultSet rs1=pstmt1.executeQuery();
				while(rs1.next())
				{
					sum1+=(Double.parseDouble(rs1.getString(1)));
				}

				double sum2=0d;
				PreparedStatement pstmt2=conn.prepareStatement("select quantity from DataG where Employee_id=? and month=? and year=? and type=? and day between 1 and 15");
				pstmt2.setInt(1, Integer.parseInt(emp_id.getText()));
				pstmt2.setString(2, enter_month.getText());
				pstmt2.setInt(3, Integer.parseInt(enter_year.getText()));
				pstmt2.setInt(4, 2);
				ResultSet rs2=pstmt2.executeQuery();
				while(rs2.next())
				{
					sum2+=(Double.parseDouble(rs2.getString(1)));
				}

				PreparedStatement pstmt3=conn.prepareStatement("select Employee_name from GEmployee where Employee_id=?");
				pstmt3.setInt(1, Integer.parseInt(emp_id.getText()));
				ResultSet rs3=pstmt3.executeQuery();
				rs3.next();

				name.setText(rs3.getString(1));
				envs.setText("Envelops of type 1 are :"+sum1+" "+"Envelops of type 2 are :"+sum2);
				double total=((sum1/1000)*30)+((sum2/1000)*40);
				sal.setText("Total salary is :"+total);
			}
		}
		catch(Exception e2)
		{
			System.out.println(e2);
		}

	}

	public static void main(String[] args) 
	{
		Salary sal=new Salary();	
	}
}
