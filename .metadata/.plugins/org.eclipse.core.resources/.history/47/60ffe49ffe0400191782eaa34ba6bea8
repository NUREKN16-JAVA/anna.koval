package ua.nure.kn.koval.usermanagement.gu;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import junit.extensions.jfcunit.JFCTestCase;
import ua.nure.kn.koval.usermanagement.User;
import ua.nure.kn.koval.usermanagement.db.DaoFactory;
import ua.nure.kn.koval.usermanagement.db.DatabaseException;
import ua.nure.kn.koval.usermanagement.db.UserDao;
import ua.nure.kn.koval.usermanagement.util.Messages;

public class MainFrame extends JFCTestCase {

	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private JPanel contentPanel;
	private JPanel browsePanel;
	private JPanel addPanel;
	private UserDao dao;
	private JPanel detailsPanel;
	private JPanel editPanel;

	public MainFrame() {
		super();
		dao = DaoFactory.getInstance().getUserDao();
		initialize();
	}

	public UserDao getDao() {
		return dao;
	}

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

	}

	public void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
		this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
		}
		return contentPanel;
	}

	JPanel getBrowsePanel() {
		if (browsePanel == null) {
			browsePanel = new BrowsePanel(this);
		}
		((BrowsePanel) browsePanel).initTable();
		return browsePanel;
	}

	public void showAddPanel() {
		showPanel(getAddPanel());
	}

	private void showPanel(JPanel panel) {
		getContentPanel().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();

	}

	private JPanel getAddPanel() {
		if (addPanel == null) {
			addPanel = new AddPanel(this);
			addPanel.setName("addPanel");
		}
		return addPanel;
	}

	private EditPanel getEditPanel(Long user_id) {
		if (editPanel == null) {
			editPanel = new EditPanel(this);
		}
		User user = null;
		if (user_id >= 0) {
			try {
				user = getDao().find(user_id);
			} catch (DatabaseException e) {
				new RuntimeException(e);
			}
		}
		((EditPanel) editPanel).fillFields(user);
		return (EditPanel) editPanel;
	}

	public void showEditPanel(Long user_id) {
		showPanel(getEditPanel(user_id));
	}

	public void showDetailsPanel(Long user_id) {
		showPanel(getDetailsPanel(user_id));
	}

	private JPanel getDetailsPanel(Long user_id) {
		if (detailsPanel == null) {
			detailsPanel = new DetailsPanel(this);
		}
		User user = null;
		if (user_id >= 0) {
			try {
				user = getDao().find(user_id);
			} catch (DatabaseException e) {
				new RuntimeException(e);
			}
		}
		((EditPanel) detailsPanel).fillFields(user);
		return detailsPanel;
	}
}
