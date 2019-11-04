package xupt.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class StudentInfo extends CommonsJDialog {

	private JPanel contentPane;
	private TablePane tablePane;

	public StudentInfo() {
		super(new Dimension(700, 550));
		// TODO Auto-generated constructor stub
		this.setTitle("学生信息管理");
		initContentPane();
		initData();
	}
	
	public void initContentPane() {
		contentPane = new JPanel();
		FlowLayout flow = new FlowLayout(5);
		contentPane.setLayout(flow);
		
		tablePane = new TablePane();
		tablePane.setPreferredSize(new Dimension(655, 300) );
		contentPane.add(tablePane);
		this.setContentPane(contentPane);
	}
	
	private void initData() {
		
	}
	
}
