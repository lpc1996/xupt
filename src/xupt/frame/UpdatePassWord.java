package xupt.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import xupt.dao.LoginDao;
import xupt.images.Images;

public class UpdatePassWord extends CommonsJDialog {
	
	private JPanel contentPane;
	private JPasswordField oldText;
	private JPasswordField newPassText1;
	private JPasswordField newPassText2;

	public UpdatePassWord() {
		super(new Dimension(350,220));
		this.setTitle("�޸�����");
		initContentPane();
		createOkBtn();
	}
	
	private void initContentPane() {
		contentPane = new JPanel();
		FlowLayout flow = new FlowLayout(5);
		contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		JLabel oldPassLab = new JLabel();
		oldPassLab.setPreferredSize(new Dimension(80,30));
		oldPassLab.setText("�����룺");
		oldText = new JPasswordField();
		oldText.setPreferredSize(new Dimension(200,30));
		JLabel newPass1Lab = new JLabel("�����룺");
		newPass1Lab.setPreferredSize(new Dimension(80,30));
		newPassText1 = new JPasswordField();
		newPassText1.setPreferredSize(new Dimension(200,30));
		JLabel newPassLab2 = new JLabel("ȷ�������룺");
		newPassLab2.setPreferredSize(new Dimension(80,30));
		newPassText2 = new JPasswordField();
		newPassText2.setPreferredSize(new Dimension(200,30));
		contentPane.add(oldPassLab);
		contentPane.add(oldText);
		contentPane.add(newPass1Lab);
		contentPane.add(newPassText1);
		contentPane.add(newPassLab2);
		contentPane.add(newPassText2);
		
		this.setContentPane(contentPane);
	}
	
	private void createOkBtn() {
		JButton submit = new JButton("�޸�����");
		contentPane.add(submit);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(oldText.getPassword().length == 0 || newPassText1.getPassword().length == 0 || newPassText2.getPassword().length ==0) {
					JOptionPane.showMessageDialog(null, "���������룡", "����", JOptionPane.WARNING_MESSAGE, new ImageIcon(new Images().getWarring2()));
					return;
				}
				String oldPass = new String( oldText.getPassword() );
				String newPass = new String( newPassText1.getPassword() );
				if(!newPass.equals(new String(newPassText2.getPassword()))) {
					JOptionPane.showMessageDialog(null, "������������벻ͬ������������", "����", JOptionPane.WARNING_MESSAGE, new ImageIcon(new Images().getWarring2()));
					return ;
				}
				LoginDao loginDao = new LoginDao();
				if(loginDao.changePass(Login.getUser().getId(), oldPass, newPass)) {
					JOptionPane.showMessageDialog(null, "�����޸ĳɹ��������µ�½��", "��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE , new ImageIcon(new Images().getYes2()));
					dispose();
					Login.reLogin();
				}else {
					JOptionPane.showMessageDialog(null, "�����޸�ʧ�ܣ�", "��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE , new ImageIcon(new Images().getError2()));
				}
			}
		});
	}

	@Override
	protected void InitData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JPanel InitTextPane(Dimension size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void InitComboBox() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setData(Object data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object getSelectData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ActionListener refreshBtnAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ActionListener deleteBtnAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ActionListener updateBtnAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ActionListener insertBtnAction() {
		// TODO Auto-generated method stub
		return null;
	}
}
