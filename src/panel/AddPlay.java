
package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import package1.DB;

public class AddPlay extends JPanel implements ActionListener{

	MainFrame mainFrame;
	String panelName,playerName,playerAffi,affiliate;
	JLabel lblAffi,lblName;
	JTextField txtAffi;
	static JTextField txtName;
	static JComboBox<Object> affi;
	JButton btnDecision,btnBackHome;
	static JScrollPane sp;
	static String[] teamName;

	JPanel panel=this;

	public AddPlay(MainFrame m,String s) {

		mainFrame=m;
		panelName=s;

		m.setTitle("AddPlayer");
		this.setName(s);
		this.setLayout(null);
		this.setSize(800,600);

		lblAffi=new JLabel("所属");
		lblName=new JLabel("名前");
		txtName = new JTextField();
		btnDecision = new JButton("追加");
		btnBackHome = new JButton("戻る");

		lblAffi.setBounds(10, 10, 60, 31);
		lblName.setBounds(10, 35, 60, 31);
		txtName.setBounds(72, 41, 86, 19);
		btnDecision.setBounds(580, 530, 90, 20);
		btnBackHome.setBounds(680, 530, 90, 20);

		txtName.setColumns(10);

		btnDecision.addActionListener(this);
		btnBackHome.addActionListener(this);

		this.add(lblAffi);
		this.add(lblName);
		this.add(txtName);
		this.add(btnDecision);
		this.add(btnBackHome);


		try{
			DB.connect();
			teamName = DB.getRowInfo(2,1);
			affi=new JComboBox<Object>(teamName);
			affi.setBounds(72, 16, 86, 19);
			add(affi);
			sp=DB.makeTbl(2,0);
			add(sp);
			DB.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnDecision) {
			if(txtName.getText().equals("")) {

			}else {
				try {
					DB.connect();
					affiliate=teamName[affi.getSelectedIndex()];
					String[] info= {affiliate,txtName.getText()};
					DB.addInfo(info,0);
					reload(this);
					DB.close();
					txtName.setText(null);
		
				}catch(SQLException e3) {

				}
			}
		}else if(e.getSource()==btnBackHome) {
			CP(mainFrame.PanelName[0]);
		}
	}

	public static void reload(JPanel panel) {
		panel.remove(affi);
		panel.remove(sp);
		try {
			DB.connect();
			teamName = DB.getRowInfo(2,1);
			affi=new JComboBox<Object>(teamName);
			affi.setBounds(72, 16, 86, 19);
			panel.add(affi);
			sp=DB.makeTbl(2,0);
			panel.add(sp);
			DB.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void CP(String str) {
		mainFrame.ChangePanel(str);
	}
}
