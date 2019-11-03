package xupt.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PersonalInfo extends CommonsJDialog {
	
	private JPanel contentPane;
	private JLabel idLab;
	private JLabel nameLab;
	private JLabel nickNameLab;
	private JLabel formarNameLab;
	private JLabel ageLab;
	private JLabel sexLab;
	private JLabel nativeLab;
	private JLabel idcardTypeLab;
	private JLabel idcardNumLab;
	private JLabel telLab;
	private Dimension labSize;

	public PersonalInfo() {
		super(new Dimension(534,246));
		labSize = new Dimension(200,30);
		this.setTitle("�û���Ϣ");
		initContentPane();
		InitData();
	}
	
	private void initContentPane() {
		contentPane = new JPanel();
		FlowLayout flow = new FlowLayout(5);
		contentPane.setLayout(flow);
		contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		idLab = new JLabel();
		idLab.setPreferredSize(labSize);
		contentPane.add(idLab);
		nameLab = new JLabel();
		nameLab.setPreferredSize(labSize);
		contentPane.add(nameLab);
		nickNameLab = new JLabel();
		nickNameLab.setPreferredSize(labSize);
		contentPane.add(nickNameLab);
		formarNameLab = new JLabel();
		formarNameLab.setPreferredSize(labSize);
		contentPane.add(formarNameLab);
		ageLab = new JLabel();
		ageLab.setPreferredSize(labSize);
		contentPane.add(ageLab);
		sexLab = new JLabel();
		sexLab.setPreferredSize(labSize);
		contentPane.add(sexLab);
		nativeLab = new JLabel();
		nativeLab.setPreferredSize(labSize);;
		contentPane.add(nativeLab);
		idcardTypeLab = new JLabel();
		idcardTypeLab.setPreferredSize(labSize);
		contentPane.add(idcardTypeLab);
		idcardNumLab = new JLabel();
		idcardNumLab.setPreferredSize(labSize);
		contentPane.add(idcardNumLab);
		telLab = new JLabel();
		telLab.setPreferredSize(labSize);
		contentPane.add(telLab);
		
		this.setContentPane(contentPane);
	}
	
	public void InitData() {
		// TODO Auto-generated method stub
		idLab.setText("ѧ���ţ�"+Login.getUser().getId());
		nameLab.setText("������"+Login.getUser().getName());
		nickNameLab.setText("�ǳƣ�"+Login.getUser().getName());
		formarNameLab.setText("��������"+Login.getUser().getFormarName());
		ageLab.setText("���䣺"+Login.getUser().getAge());
		sexLab.setText("�Ա�"+Login.getUser().getSex());
		nativeLab.setText("���᣺"+Login.getUser().getNativePlace());
		idcardTypeLab.setText("֤�����ͣ�"+Login.getUser().getIDCARDTYPE());
		idcardNumLab.setText("֤�����룺"+Login.getUser().getIDCARDNUM());
		telLab.setText("�绰���룺"+Login.getUser().getTel());
	}
}
