package xupt.frame;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import xupt.dao.MajorDao;
import xupt.mode.MajorModel;

public class MajorInfo extends CommonsJDialog {
	
	/**
	 * @author 濃霧-遠方
	 */
	private static final long serialVersionUID = 1L;

	public MajorInfo() {
		super(new Dimension(400, 500));
		this.setTitle("学院信息管理");
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
		MajorDao majorDao = new MajorDao();
		List<MajorModel> majorList = majorDao.getList();
		Vector<String> title = majorDao.getComments();
		DefaultTableModel model = new DefaultTableModel(title, majorList.size());
		int i=0;
		for(MajorModel major:majorList) {
			model.setValueAt(major.getId(), i, 0);
			model.setValueAt(major.getName(), i, 1);
			model.setValueAt(major.getCollegeId(), i, 2);
			model.setValueAt(major.getDepartmentId(), i, 3);
			i++;
		}
		tablePane.setTableModel(model);
		tablePane.setColumnWidth(80);
	}

	@Override
	protected JPanel InitTextPane() {
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
