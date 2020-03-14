package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import package1.DB;

public class DelTeam extends JPanel implements ActionListener{

	MainFrame mainFrame;
	String panelName;
	JButton btnDelete,btnBackHome;
	static JComboBox<Object> keyBox;
	JTextField txtKey;
	static JScrollPane sp;

	static String[] key;

	JPanel panel=this;

	public DelTeam(MainFrame m,String s) {

		mainFrame=m;
		panelName=s;

		m.setTitle("DeleteTeam");
		this.setName(s);
		this.setLayout(null);
		this.setSize(800,600);

		btnDelete=new JButton("削除");
		btnBackHome = new JButton("戻る");

		btnDelete.setBounds(580, 530, 90, 20);
		btnBackHome.setBounds(680, 530, 90, 20);

		btnDelete.addActionListener(this);
		btnBackHome.addActionListener(this);

		this.add(btnDelete);
		this.add(btnBackHome);

		try {
			DB.connect();
			key = DB.getRowInfo(1,1);
			keyBox=new JComboBox<Object>(key);
			keyBox.setBounds(72, 16, 86, 19);
			this.add(keyBox);
			sp=DB.makeTbl(2,1);
			this.add(sp);
			DB.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnDelete) {
			try {
				DB.connect();
				int keyNum=keyBox.getSelectedIndex();
				DB.delInfo(1, key[keyNum]);
				DB.close();
				reload(this);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==btnBackHome) {
			CP(mainFrame.PanelName[0]);
		}
	}

	public static void reload(JPanel panel) {
		try {
			DB.connect();
			panel.remove(keyBox);
			panel.remove(sp);
			key = DB.getRowInfo(1,1);
			keyBox=new JComboBox<Object>(key);
			keyBox.setBounds(72, 16, 86, 19);
			panel.add(keyBox);
			sp=DB.makeTbl(2,1);
			panel.add(sp);
			DB.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void CP(String str) {
		mainFrame.ChangePanel(str);
	}
}