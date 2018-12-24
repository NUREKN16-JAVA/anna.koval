package ua.nure.kn.koval.usermanagement.gu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ua.nure.kn.koval.usermanagement.User;
import ua.nure.kn.koval.usermanagement.db.DatabaseException;
import ua.nure.kn.koval.usermanagement.util.Messages;

public class BrowsePanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JPanel buttonPanel;
	private JButton addButton;
	private JButton detailsButton;
	private JButton deleteButton;
	private JButton editButton;
	private JScrollPane tablePanel;
	private JTable userTable;

	public BrowsePanel(MainFrame frame) {
		parent = frame;
		initialize();
	}

	private void initialize() {
		this.setName("browsePanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(), BorderLayout.CENTER);
		this.add(getButtonsPanel(), BorderLayout.SOUTH);

	}

	private JPanel getButtonsPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getAddButton(), null);
			buttonPanel.add(getEditButton(), null);
			buttonPanel.add(getDeleteButton(), null);
			buttonPanel.add(getDetailsButton(), null);
		}
		return buttonPanel;
	}

	private JButton getDetailsButton() {
		if (detailsButton == null) {
			detailsButton = new JButton();
			detailsButton.setName("detailsButton"); //$NON-NLS-1$
			detailsButton.setActionCommand("details"); //$NON-NLS-1$
			detailsButton.addActionListener(this);
		}
		return detailsButton;
	}

	private JButton getEditButton() {
		if (editButton == null) {
			editButton = new JButton();
			editButton.setText(Messages.getString("BrowsePanel.edit")); //$NON-NLS-1$
			editButton.setName(Messages.getString("BrowsePanel.4")); //$NON-NLS-1$
			editButton.setActionCommand("edit"); //$NON-NLS-1$
			editButton.addActionListener(this);
		}
		return editButton;
	}

	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText(Messages.getString("BrowsePanel.delete")); //$NON-NLS-1$
			deleteButton.setName("deleteButton"); //$NON-NLS-1$
			deleteButton.setActionCommand("delete"); //$NON-NLS-1$
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}

	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText(Messages.getString("BrowsePanel.add")); //$NON-NLS-1$
			addButton.setName(Messages.getString("BrowsePanel.10")); //$NON-NLS-1$
			addButton.setActionCommand(Messages.getString("BrowsePanel.11")); //$NON-NLS-1$
			addButton.addActionListener(this);

		}
		return addButton;
	}

	private JScrollPane getTablePanel() {
		if (tablePanel == null) {
			tablePanel = new JScrollPane(getUserTable());
		}
		return tablePanel;
	}

	private JTable getUserTable() {
		if (userTable == null) {
			userTable = new JTable();
			userTable.setName("userTable"); //$NON-NLS-1$

		}
		return userTable;
	}

	public void initTable() {
		UserTableModel model;
		try {
			model = new UserTableModel(parent.getDao().findAll());
		} catch (DatabaseException e) {
			model = new UserTableModel(new ArrayList());
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		getUserTable().setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if ("add".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
			this.setVisible(false);
			parent.showAddPanel();
		}
		if ("edit".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
			int row = getUserTable().getSelectedRow();
			final int ID_COLUMN = 0;
			Long user_id = (Long) getUserTable().getModel().getValueAt(row, ID_COLUMN);
			this.setVisible(false);
			parent.showEditPanel(user_id);
		}
		if ("delete".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
			final int YES = 0;
			int yes_no = JOptionPane.showConfirmDialog(this, Messages.getString("BrowsePanel.message"), //$NON-NLS-1$
					Messages.getString("BrowsePanel.title"), //$NON-NLS-1$
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (yes_no == YES) {
				int row = getUserTable().getSelectedRow();
				final int ID_COLUMN = 0;
				Long user_id = (Long) getUserTable().getModel().getValueAt(row, ID_COLUMN);
				User user = new User();
				user.setId(user_id);
				try {
					parent.getDao().delete(user);
				} catch (DatabaseException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
				}
				initTable();
				this.repaint();

			}
		}
		if ("details".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
			int row = getUserTable().getSelectedRow();
			final int ID_COLUMN = 0;
			Long user_id = (Long) getUserTable().getModel().getValueAt(row, ID_COLUMN);
			this.setVisible(false);
			parent.showDetailsPanel(user_id);
		}
	}

}
