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
	private JMenuBar mainMenu;
	private JMenuItem changeItem;
	private JMenuItem changePassItem;
	private JMenuItem exitItem;
	private JMenu backstageMenu;
	private JMenuItem teacherMenu;
	private JMenuItem studentManageItem;
	private JMenuItem collegeManageItem;
	private JMenu userMenu;
	private JMenuItem departmentItem;
	private JMenuItem majorItem;
	private JMenuItem schoolYearItem;
	private JMenuItem schoolTremItem;
	private JMenuItem semesterItem;
	private JMenuItem logoutItem;
	private JMenuItem xclassItem;
	private JMenuItem coursesItem;
	private JMenuItem offeringCoursesItem;
	private JMenu courseMenu;
	private JMenuItem studentCourseItem;
	private JMenu settingMenu;
	private JMenuItem userManageItem;
	private JMenu studentMenu;
	private JMenuItem infoSearchItem;
	private JMenuItem courseSearchItem;
	protected PersonalInfo personalInfo;
	protected UpdatePassWord updatePassWord;

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
			setJMenuBar(setStudentMenu());
		}else if(Login.getLoginModel().getLimit() >= 4 && Login.getLoginModel().getLimit() < 7) {
			setJMenuBar(setTeacherMenu());
		}else if(Login.getLoginModel().getLimit() >= 7) {
			setJMenuBar(setadminMenuBar());
		}
		
		JPanel contentPane = new JPanel();
		JLabel backgroundLab = new JLabel(new ImageIcon(new Images().getBackground2()));
		contentPane.add(backgroundLab);
		setContentPane(contentPane);
	} 
	
	private JMenuBar setStudentMenu() {
		mainMenu = new JMenuBar();
		mainMenu.add(createUserInfoJMenu());
		return mainMenu;
	}
	
	private JMenuBar setadminMenuBar() {
		mainMenu = new JMenuBar();
		mainMenu.add(createUserInfoJMenu());
		backstageMenu = new JMenu();
		backstageMenu.setText("后台管理");
		teacherMenu = new JMenuItem();
		teacherMenu.setText("教职工管理");
		backstageMenu.add(teacherMenu);
		studentManageItem = new JMenuItem("学生信息管理");
		backstageMenu.add(studentManageItem);
		mainMenu.add(backstageMenu);
		collegeManageItem =  new JMenuItem("学院信息管理");
		backstageMenu.add(collegeManageItem);
		departmentItem = new JMenuItem("系/部信息管理");
		backstageMenu.add(departmentItem);
		majorItem = new JMenuItem("专业信息管理");
		backstageMenu.add(majorItem);
		schoolYearItem = new JMenuItem("学年信息管理");
		backstageMenu.add(schoolYearItem);
		schoolTremItem = new JMenuItem("学期信息管理");
		backstageMenu.add(schoolTremItem);
		semesterItem = new JMenuItem("年级信息管理");
		backstageMenu.add(semesterItem);
		xclassItem = new JMenuItem("班级信息管理");
		backstageMenu.add(xclassItem);
		courseMenu = new JMenu();
		courseMenu.setText("课程管理");
		mainMenu.add(courseMenu);
		coursesItem = new JMenuItem("课程信息管理");
		courseMenu.add(coursesItem);
		offeringCoursesItem = new JMenuItem("开设课程管理");
		courseMenu.add(offeringCoursesItem);
		studentCourseItem = new JMenuItem("学生选课管理");
		courseMenu.add(studentCourseItem);
		studentMenu = new JMenu("学生信息管理");
		infoSearchItem = new JMenuItem("学生信息查询");
		studentMenu.add(infoSearchItem);
		courseSearchItem = new JMenuItem("学生成绩查询");
		studentMenu.add(courseSearchItem);
		mainMenu.add(studentMenu);
		settingMenu = new JMenu("系统设置");
		mainMenu.add(settingMenu);
		userManageItem = new JMenuItem("用户管理"); 
		settingMenu.add(userManageItem);
		return mainMenu;
	}
	
	private JMenuBar setTeacherMenu() {
		mainMenu = new JMenuBar();
		mainMenu.add(createUserInfoJMenu());
		
		return mainMenu;
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
		changeItem.addActionListener(new ActionListener() {
			
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
}
