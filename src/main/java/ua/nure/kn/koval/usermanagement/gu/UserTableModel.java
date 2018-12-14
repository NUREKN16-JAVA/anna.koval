package ua.nure.kn.koval.usermanagement.gu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.nure.kn.koval.usermanagement.User;
import ua.nure.kn.koval.usermanagement.util.Messages;

public class UserTableModel extends AbstractTableModel {

	private static final String[] COLUMN_NAMES = { Messages.getString("user_management.UserTableModel.0"), //$NON-NLS-1$
			Messages.getString("user_management.UserTableModel.1"), //$NON-NLS-1$
			Messages.getString("user_management.UserTableModel.2") }; //$NON-NLS-1$
	private static final Class[] COLUMN_CLASSES = { Long.class, String.class, String.class };
	private List users = null;

	public UserTableModel(Collection users) {
		this.users = new ArrayList(users);
	}

	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public int getColumnCount() {

		return COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Class getColumnClass(int columnIndex) {

		return COLUMN_CLASSES[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		}
		return null;
	}

}
