package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import package1.DB;

public class DelPlay extends JPanel implements ActionListener{

	MainFrame mainFrame;
	String panelName;
	JButton btnDelete,btnBackHome;
	static JComboBox<Object> keyBox;
	static JScrollPane sp;
	String[] playName;
	static String[] key;

	JPanel panel=this;

	public DelPlay(MainFrame m,String s){
		mainFrame=m;
		panelName=s;

		m.setTitle("DeletePlayer");
		this.setName(s);
		this.setLayout(null);
		this.setSize(800,600);

		btnDelete=new JButton("削除");
		btnBackHome = new JButton("戻る");

		btnDelete.setBounds(580, 530, 90, 20);
		btnBackHome.setBounds(680, 530, 90, 20);

		this.add(btnDelete);
		this.add(btnBackHome);

		btnDelete.addActionListener(this);
		btnBackHome.addActionListener(this);

		try {
			DB.connect();
			key = DB.getRowInfo(2,0);
			keyBox=new JComboBox<Object>(key);
			keyBox.setBounds(72, 16, 86, 19);
			this.add(keyBox);
			sp=DB.makeTbl(2,0);
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
				DB.delInfo(0, key[keyNum]);
				reload(this);
				DB.close();
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
			key = DB.getRowInfo(2,0);
			keyBox=new JComboBox<Object>(key);
			keyBox.setBounds(72, 16, 86, 19);
			panel.add(keyBox);
			sp=DB.makeTbl(2,0);
			panel.add(sp);
			DB.close();
		}catch(SQLException e) {

		}
	}

	public void CP(String str) {
		mainFrame.ChangePanel(str);
	}
}