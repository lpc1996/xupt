package xupt.frame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import image.Images;

public class MainFrame extends JFrame {

	/**
	 * ������
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar mainMenu;
	private JMenuItem changeItem;
	private JMenuItem changePassItem;
	private JMenuItem exitItem;
	private BtnListener itemListener;
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

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
		initFrame();
	}
	
	private void initFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("�����ʵ��ѧ-����ϵͳ");
		this.setIconImage(new Images().getSchoolLogo());
		this.setSize(new Dimension(800, 500));
		this.setResizable(false);
		this.setLocation(new Point((Toolkit.getDefaultToolkit().getScreenSize().width - 800)/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height-500)/2));
		itemListener = new BtnListener();
		if(Login.getLoginModel().getLimit() < 4) {
			setJMenuBar(setStudentMenu());
		}else if(Login.getLoginModel().getLimit() >= 4 && Login.getLoginModel().getLimit() < 7) {
			setJMenuBar(setTeacherMenu());
		}else if(Login.getLoginModel().getLimit() >= 7) {
			setJMenuBar(setadminMenuBar());
		}
		
		JPanel contentPane = new JPanel();
		JLabel versionLab = new JLabel();
//		String[] version = null;
//		try {
//			version = new ReadFile("src\\com\\lpc\\lib\\settings\\version.ini").ReadVersion();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		versionLab.setText("<html><body><div>"+version[0]+"<br/>"+version[1]+"</div></body></html>");
		contentPane.add(versionLab);
		setContentPane(contentPane);
	} 
	
	private JMenuBar setStudentMenu() {
		mainMenu = new JMenuBar();
		mainMenu.add(setPublicJMenu());
		return mainMenu;
	}
	
	private JMenuBar setadminMenuBar() {
		mainMenu = new JMenuBar();
		mainMenu.add(setPublicJMenu());
		backstageMenu = new JMenu();
		backstageMenu.setText("��̨����");
		teacherMenu = new JMenuItem();
		teacherMenu.setText("��ְ������");
		backstageMenu.add(teacherMenu);
		studentManageItem = new JMenuItem("ѧ����Ϣ����");
		backstageMenu.add(studentManageItem);
		mainMenu.add(backstageMenu);
		collegeManageItem =  new JMenuItem("ѧԺ��Ϣ����");
		backstageMenu.add(collegeManageItem);
		departmentItem = new JMenuItem("ϵ/����Ϣ����");
		backstageMenu.add(departmentItem);
		majorItem = new JMenuItem("רҵ��Ϣ����");
		backstageMenu.add(majorItem);
		schoolYearItem = new JMenuItem("ѧ����Ϣ����");
		backstageMenu.add(schoolYearItem);
		schoolTremItem = new JMenuItem("ѧ����Ϣ����");
		backstageMenu.add(schoolTremItem);
		semesterItem = new JMenuItem("�꼶��Ϣ����");
		backstageMenu.add(semesterItem);
		xclassItem = new JMenuItem("�༶��Ϣ����");
		backstageMenu.add(xclassItem);
		courseMenu = new JMenu();
		courseMenu.setText("�γ̹���");
		mainMenu.add(courseMenu);
		coursesItem = new JMenuItem("�γ���Ϣ����");
		courseMenu.add(coursesItem);
		offeringCoursesItem = new JMenuItem("����γ̹���");
		courseMenu.add(offeringCoursesItem);
		studentCourseItem = new JMenuItem("ѧ��ѡ�ι���");
		courseMenu.add(studentCourseItem);
		studentMenu = new JMenu("ѧ����Ϣ����");
		infoSearchItem = new JMenuItem("ѧ����Ϣ��ѯ");
		studentMenu.add(infoSearchItem);
		courseSearchItem = new JMenuItem("ѧ���ɼ���ѯ");
		studentMenu.add(courseSearchItem);
		mainMenu.add(studentMenu);
		settingMenu = new JMenu("ϵͳ����");
		mainMenu.add(settingMenu);
		userManageItem = new JMenuItem("�û�����"); 
		settingMenu.add(userManageItem);
		
		teacherMenu.addActionListener(itemListener);
		studentManageItem.addActionListener(itemListener);
		collegeManageItem.addActionListener(itemListener);
		departmentItem.addActionListener(itemListener);
		majorItem.addActionListener(itemListener);
		schoolYearItem.addActionListener(itemListener);
		schoolTremItem.addActionListener(itemListener);
		semesterItem.addActionListener(itemListener);
		xclassItem.addActionListener(itemListener);
		coursesItem.addActionListener(itemListener);
		offeringCoursesItem.addActionListener(itemListener);
		studentCourseItem.addActionListener(itemListener);
		infoSearchItem.addActionListener(itemListener);
		courseSearchItem.addActionListener(itemListener);
		userManageItem.addActionListener(itemListener);
		return mainMenu;
	}
	
	private JMenuBar setTeacherMenu() {
		mainMenu = new JMenuBar();
		mainMenu.add(setPublicJMenu());
		
		return mainMenu;
	}
	
	private JMenu setPublicJMenu() {
		userMenu = new JMenu();
		userMenu.setText("�û���Ϣ");
		changeItem = new JMenuItem("������Ϣ�޸�");
		changePassItem = new JMenuItem("�޸�����");
		logoutItem = new JMenuItem("ע����½");
		exitItem = new JMenuItem("�˳�");
		
		userMenu.add(changeItem);
		userMenu.add(changePassItem);
		userMenu.add(logoutItem);
		userMenu.addSeparator();
		userMenu.add(exitItem);
		changeItem.addActionListener(itemListener);
		changePassItem.addActionListener(itemListener);
		logoutItem.addActionListener(itemListener);
		exitItem.addActionListener(itemListener);
		
		return userMenu;
	}
	
	public class BtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			if(e.getSource() == changeItem) {
//				person = new PersonInfoChange();
//				person.setVisible(true);
//			}else if(e.getSource() == changePassItem) {
//				changePass = new ChangePass();
//				changePass.setVisible(true);
//			}else if(e.getSource() == exitItem) {
//				System.exit(0);
//			}else if(e.getSource() == teacherMenu) {
//				teacherManage = new TeacherManage();
//				teacherManage.setVisible(true);
//			}else if(e.getSource() == studentManageItem){
//				studentManage = new StudentManage();
//				studentManage.setVisible(true);
//			}else if(e.getSource() == collegeManageItem) {
//				collegeManage = new CollegeManage();
//				collegeManage.setVisible(true);
//			}else if(e.getSource() == departmentItem) {
//				departmentManage = new DepartmentManage();
//				departmentManage.setVisible(true);
//			}else if(e.getSource() == majorItem) {
//				majorManage = new MajorManage();
//				majorManage.setVisible(true);
//			}else if(e.getSource() == schoolYearItem) {
//				schoolYearManage = new SchoolYearManage();
//				schoolYearManage.setVisible(true);
//			}else if(e.getSource() == schoolTremItem) {
//				schoolTremManage = new SchoolTremManage();
//				schoolTremManage.setVisible(true);
//			}else if(e.getSource() == semesterItem) {
//				semesterManage = new SemesterManage();
//				semesterManage.setVisible(true);
//			}else if(e.getSource() == logoutItem) {
//				new Login().setVisible(true);
//				dispose();
//			}else if(e.getSource() == xclassItem) {
//				 xclassManage = new XClassManage();
//				 xclassManage.setVisible(true);
//			}else if(e.getSource() == coursesItem){
//				courseManage = new courseManage();
//				courseManage.setVisible(true);
//			}else if(e.getSource() == offeringCoursesItem ) {
//				offeringManage = new CourseClass();
//				offeringManage.setVisible(true);
//			}else if(e.getSource() == studentCourseItem) {
//				studentCourseManage = new StudentCourseManage();
//				studentCourseManage.setVisible(true);
//			}else if(e.getSource() == userManageItem) { 
//				userManage = new UserManage();
//				userManage.setVisible(true);
//			}else if(e.getSource() == infoSearchItem) {
//				JOptionPane.showMessageDialog(null,"���ܿ����У������ڴ�");
//			}else if(e.getSource() == courseSearchItem) {
//				JOptionPane.showMessageDialog(null,"���ܿ����У������ڴ�");
//			}
		}
	}
}
