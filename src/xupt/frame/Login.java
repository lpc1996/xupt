package xupt.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import xupt.dao.BaseInfoDao;
import xupt.dao.LoginDao;
import xupt.images.Images;
import xupt.mode.BaseInfoModel;
import xupt.mode.LoginModel;

public class Login extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static BaseInfoModel user = null;
	private static LoginModel loginModel = null;
	private JButton loginBtn;
	private JPanel cententPane;
	private JButton exitBtn;
	private JTextField nameText;
	private JPasswordField passText;
	public LoginDao loginDao;
	private static MainFrame mainFrame;
	private static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * @throws HeadlessException
	 */
	public Login() throws HeadlessException {
		super();
		loginDao = new LoginDao();
		if( Login.mainFrame != null)
			Login.mainFrame.dispose();
		initFrame();
	}

	public void initFrame() {
		
		this.setSize(new Dimension(296, 356));
		this.setResizable(false);
		this.setTitle("欢迎登陆");
		
		this.setIconImage(new Images().getSchoolLogo());
		this.setLocationRelativeTo(null);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginBtn = new JButton();
		loginBtn.setText("登陆");
		loginBtn.setBounds((296/2 - 85), 285, 80, 32); 
		
		exitBtn = new JButton();
		exitBtn.setText("退出");
		exitBtn.setBounds((296/2 + 5), 285, 80, 32);
	
		JLabel userLab = new JLabel();
		userLab.setIcon(new ImageIcon(new Images().getUser() ) );
		userLab.setBounds(new Rectangle(0, 160, 280, 110));
		
		JLabel nameLab = new JLabel("用户名：");
		nameLab.setBounds(new Rectangle(40, 195, 80, 32));
		JLabel passLab = new JLabel("密码：");
		passLab.setBounds(new Rectangle(40, 230, 80,32));
		
		nameText = new JTextField();
		nameText.setBounds(new Rectangle(120, 195, 124, 32));
		
		passText = new JPasswordField();
		passText.setBounds(new Rectangle(120, 230, 124, 32));
		
		JLabel logoLab = new JLabel();
		logoLab.setIcon(new ImageIcon(new Images().getLoginBG()));
		logoLab.setBounds(new Rectangle(0, 0, 296, 150));
		
		getRootPane().setDefaultButton(loginBtn);
		
		cententPane = new JPanel();
		cententPane.setLayout(null);
		cententPane.setBackground(new Color(255, 255, 255));
		cententPane.add(logoLab);
		cententPane.add(loginBtn);
		cententPane.add(exitBtn);
		cententPane.add(nameLab);
		cententPane.add(nameText);
		cententPane.add(passLab);
		cententPane.add(passText);
		cententPane.add(userLab);
		this.setContentPane(cententPane);
		
		BtnListener btnListener = new BtnListener();
		loginBtn.addActionListener(btnListener);
		exitBtn.addActionListener(btnListener);
	}
	
	public class BtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == loginBtn) {
				String id = nameText.getText();
				String pass = new String( passText.getPassword() );
				if(id.length() != 8) {
					JOptionPane.showMessageDialog(getContentPane(), "请输入正确的用户名");
					return;
				}
				if(pass.length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "请输入密码");
					return ;
				}
				loginModel = loginDao.login(id, pass);
				if(loginModel != null) {
					BaseInfoDao baseInfoDao = new BaseInfoDao();
					Login.setUser(baseInfoDao.getUser(loginModel.getId()));
					JOptionPane.showMessageDialog(getContentPane(), loginModel.getName()+"欢迎");
					dispose();
					mainFrame = new MainFrame();
					mainFrame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "用户名或密码错误,登陆失败");
					return ;
				}
			}else if(e.getSource() == exitBtn) {
				System.exit(0);
			}
		}
		
	}
	
	public static void main(String [] args) {
		Login login = new Login();
		login.setVisible(true);
	}
	
	public static void reLogin() {
		Login login = new Login();
		login.setVisible(true);
	}

	public static BaseInfoModel getUser() {
		return Login.user;
	}

	public static void setUser(BaseInfoModel user) {
		Login.user = user;
	}
	
	public static LoginModel getLoginModel() {
		return Login.loginModel;
	}
	
	public static Dimension getScreenSize() {
		return Login.ScreenSize;
	}
	
	public static String getNowTime() {
		Calendar now = Calendar.getInstance(); 
		String year = now.get(Calendar.YEAR)+"";
		String month = now.get(Calendar.MONTH)+"";
		String day = now.get(Calendar.DAY_OF_MONTH)+"";
		return year+"-"+month+"-"+day;
	}

}
