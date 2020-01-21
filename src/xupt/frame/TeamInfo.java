package xupt.frame;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class TeamInfo extends CommonsJDialog {
	
	/**
	 * @author 濃霧-遠方
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeamInfo() {
		super(new Dimension(400, 500));
		this.setTitle("班级信息管理");
		InitContentPane();
		addInsertAction(insertBtnAction());
		addUpdateAction(updateBtnAction());
		addDeleteAction(deleteBtnAction());
		addRefreshAction(refreshBtnAction());
		InitData();
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
