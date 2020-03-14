package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import package1.DB;

public class AddTeam extends JPanel implements ActionListener{

	MainFrame mainFrame;
	JLabel lblTeamName,lblNickname;
	JTextField txtTeamName,txtNickname;
	JButton btnDecision,btnBackHome;
	JTable table;
	static JScrollPane sp;
	DefaultTableModel model;
	String panelName,teamName,nickName;

	JPanel panel=this;

	public AddTeam(MainFrame m,String s){

		mainFrame=m;
		panelName=s;

		m.setTitle("AddTeam");
		this.setName(s);
		this.setLayout(null);
		this.setSize(800,600);

		lblTeamName = new JLabel("チーム名");
		lblNickname = new JLabel("略称");
		txtTeamName = new JTextField();
		txtNickname = new JTextField();
		btnDecision = new JButton("追加");
		btnBackHome = new JButton("戻る");

		lblTeamName.setBounds(10, 10, 52, 31);
		lblNickname.setBounds(10, 35, 52, 31);
		txtTeamName.setBounds(90, 16, 86, 19);
		txtNickname.setBounds(90, 41, 86, 19);
		btnDecision.setBounds(580, 530, 90, 20);
		btnBackHome.setBounds(680, 530, 90, 20);

		txtTeamName.setColumns(10);
		txtNickname.setColumns(10);

		btnDecision.addActionListener(this);
		btnBackHome.addActionListener(this);

		this.add(lblTeamName);
		this.add(lblNickname);
		this.add(txtTeamName);
		this.add(txtNickname);
		this.add(btnDecision);
		this.add(btnBackHome);

		try {
			DB.connect();
			sp=DB.makeTbl(2,1);
			this.add(sp);
			DB.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnDecision) {
			if(txtTeamName==null||txtNickname==null) {

			}else {
				try {
					DB.connect();
					String[] info= {txtTeamName.getText(),txtNickname.getText()};
					DB.addInfo(info, 1);
					txtTeamName.setText(null);
					txtNickname.setText(null);
					panel.remove(sp);
					sp=DB.makeTbl(2,1);
					panel.add(sp);
					DB.close();
				} catch (SQLException e1) {

				}
			}
		}else if(e.getSource()==btnBackHome) {
			CP(mainFrame.PanelName[0]);
		}
	}

	public static void reload(JPanel panel) {
		try{
			DB.connect();
			panel.remove(sp);
			sp=DB.makeTbl(2,1);
			panel.add(sp);
			DB.close();
		}catch(SQLException e1) {
			
		}
		
	}
	
	public void CP(String str) {
		mainFrame.ChangePanel(str);
	}
}