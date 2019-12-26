package xupt.frame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import xupt.images.Images;

public class MainFrame extends JFrame {

	/**
	 * 主窗口
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem changeItem;
	private JMenuItem changePassItem;
	private JMenuItem exitItem;
	private JMenu userMenu;
	private JMenuItem logoutItem;
	protected PersonalInfo personalInfo;
	protected UpdatePassWord updatePassWord;
	protected StudentInfo studentInfo;
	protected TeacherInfo teacherInfo;
	protected CollegeInfo collegeInfo;
	protected DepartmentInfo departmentInfo;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
		initFrame();
	}
	
	private void initFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("西安邮电大学-教务系统");
		this.setIconImage(new Images().getSchoolLogo());
		this.setSize(new Dimension(800, 500));
		this.setResizable(false);
		this.setLocation(new Point((Login.getScreenSize().width - 800)/2, 
				(Login.getScreenSize().height-500)/2));
		if(Login.getLoginModel().getLimit() < 4) {
			setJMenuBar(createStudentBar());
		}else if(Login.getLoginModel().getLimit() >= 4 && Login.getLoginModel().getLimit() < 7) {
			setJMenuBar(createTeacherBar());
		}else if(Login.getLoginModel().getLimit() >= 7) {
			setJMenuBar(createAdminBar());
		}
		
		JPanel contentPane = new JPanel();
		JLabel backgroundLab = new JLabel(new ImageIcon(new Images().getBackground2()));
		contentPane.add(backgroundLab);
		setContentPane(contentPane);
	} 
	private JMenuBar createStudentBar() {
		JMenuBar studentBar = new JMenuBar();
		studentBar.add(createUserInfoJMenu());
		return studentBar;
	}
	
	private JMenuBar createTeacherBar() {
		JMenuBar teacherBar = new JMenuBar();
		teacherBar.add(createUserInfoJMenu());
		return teacherBar;
	}
	
	private JMenuBar createAdminBar() {
		JMenuBar adminBar = new JMenuBar();
		adminBar.add(createUserInfoJMenu());
		adminBar.add(createBackJMenu());
		return adminBar;
	}
	private JMenu createUserInfoJMenu() {
		userMenu = new JMenu();
		userMenu.setText("用户信息");
		changeItem = new JMenuItem("用户信息");
		changeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(personalInfo == null) {
					personalInfo = new PersonalInfo();
				}
				personalInfo.repaint();
				personalInfo.setVisible(true);
			}
		});
		changePassItem = new JMenuItem("修改密码");
		changePassItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(updatePassWord == null) {
					updatePassWord = new UpdatePassWord();
				}
				updatePassWord.repaint();
				updatePassWord.setVisible(true);
			}
		});
		logoutItem = new JMenuItem("注销登陆");
		logoutItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = JOptionPane.showConfirmDialog(null, "确认要注销账户："+Login.getUser().getId() +" 吗？", "提示消息", JOptionPane.YES_NO_OPTION, 0, new ImageIcon( new Images().getWarring2() ) );
				if(i == 0) {
					Login.reLogin();
					dispose();
				}
			}
		});
		exitItem = new JMenuItem("退出");
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int i = JOptionPane.showConfirmDialog(null, "确认要退出吗？", "提示消息", JOptionPane.YES_NO_OPTION, 0, new ImageIcon( new Images().getWarring2() ) );
				if(i == 0) {
					System.exit(0);
				}
			}
		});
		
		userMenu.add(changeItem);
		userMenu.add(changePassItem);
		userMenu.add(logoutItem);
		userMenu.addSeparator();
		userMenu.add(exitItem);
		
		return userMenu;
	}
	
	private JMenu createBackJMenu() {
		JMenu backJMenu = new JMenu("后台管理");
		JMenuItem studentItem = new JMenuItem("学生信息管理");
		studentItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(studentInfo == null) {
					studentInfo = new StudentInfo();
				}
				studentInfo.repaint();
				studentInfo.setVisible(true);
			}
		});
		backJMenu.add(studentItem);
		JMenuItem teacherItem = new JMenuItem("教职工信息管理");
		teacherItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(teacherInfo == null) {
					teacherInfo = new TeacherInfo();
				}
				teacherInfo.repaint();
				teacherInfo.setVisible(true);
			}
		});
		backJMenu.add(teacherItem);
		JMenuItem collegeItem = new JMenuItem("学院信息管理");
		collegeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(collegeInfo == null) {
					collegeInfo = new CollegeInfo();
				}
				collegeInfo.repaint();
				collegeInfo.setVisible(true);
			}
		});
		backJMenu.add(collegeItem);
		JMenuItem departmentItem = new JMenuItem("系部信息管理");
		departmentItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(departmentInfo == null) {
					departmentInfo = new DepartmentInfo();
				}
				departmentInfo.repaint();
				departmentInfo.setVisible(true);
			}
		});
		backJMenu.add(departmentItem);
		return backJMenu;
	}
}
