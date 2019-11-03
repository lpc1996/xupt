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
	 * ������
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
		this.setTitle("�����ʵ��ѧ-����ϵͳ");
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
		return mainMenu;
	}
	
	private JMenuBar setTeacherMenu() {
		mainMenu = new JMenuBar();
		mainMenu.add(createUserInfoJMenu());
		
		return mainMenu;
	}
	
	private JMenu createUserInfoJMenu() {
		userMenu = new JMenu();
		userMenu.setText("�û���Ϣ");
		changeItem = new JMenuItem("�û���Ϣ");
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
		changePassItem = new JMenuItem("�޸�����");
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
		logoutItem = new JMenuItem("ע����½");
		logoutItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫע���˻���"+Login.getUser().getId() +" ��", "��ʾ��Ϣ", JOptionPane.YES_NO_OPTION, 0, new ImageIcon( new Images().getWarring2() ) );
				if(i == 0) {
					Login.reLogin();
					dispose();
				}
			}
		});
		exitItem = new JMenuItem("�˳�");
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳���", "��ʾ��Ϣ", JOptionPane.YES_NO_OPTION, 0, new ImageIcon( new Images().getWarring2() ) );
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
