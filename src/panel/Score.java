package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Score extends JPanel implements ActionListener{

	MainFrame mainFrame;
	String panelName;
	JButton btnBackHome;

	public Score(MainFrame m,String s){

		mainFrame=m;
		panelName=s;

		m.setTitle("ScoreSheet");
		this.setName(s);
		this.setLayout(null);
		this.setSize(800,600);

		btnBackHome = new JButton("戻る");

		btnBackHome.setBounds(680, 530, 90, 20);

		btnBackHome.addActionListener(this);

		this.add(btnBackHome);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnBackHome) {
			CP(mainFrame.PanelName[0]);
		}
	}

	public void CP(String str) {
		mainFrame.ChangePanel(str);
	}
}