package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Home extends JPanel implements ActionListener{

	JButton btnScore,btnAddTeam,btnDelTeam,btnAddPlay,btnDelPlay;
	MainFrame mainFrame;
	String panelName;

	public Home(MainFrame m,String s) {

		mainFrame=m;
		panelName=s;
		
		m.setTitle("Home");
		this.setName(s);
		this.setLayout(null);
		this.setSize(800,600);

		btnScore=new JButton("スコアシート");
		btnAddTeam=new JButton("チーム追加");
		btnDelTeam=new JButton("チーム削除");
		btnAddPlay=new JButton("選手追加");
		btnDelPlay=new JButton("選手削除");

		btnScore.setBounds(10, 10, 120, 40);
		btnAddTeam.setBounds(10, 56, 120, 40);
		btnDelTeam.setBounds(10, 102, 120, 40);
		btnAddPlay.setBounds(10, 148, 120, 40);
		btnDelPlay.setBounds(10, 194, 120, 40);

		btnScore.addActionListener(this);
		btnAddTeam.addActionListener(this);
		btnDelTeam.addActionListener(this);
		btnAddPlay.addActionListener(this);
		btnDelPlay.addActionListener(this);

		this.add(btnScore);
		this.add(btnAddTeam);
		this.add(btnDelTeam);
		this.add(btnAddPlay);
		this.add(btnDelPlay);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnScore) {
			CP(mainFrame.PanelName[1]);
		}else if(e.getSource()==btnAddTeam) {
			AddTeam.reload(mainFrame.addTeam);
			CP(mainFrame.PanelName[2]);
		}else if(e.getSource()==btnDelTeam) {
			DelTeam.reload(mainFrame.delTeam);
			CP(mainFrame.PanelName[3]);
		}else if(e.getSource()==btnAddPlay) {
			AddPlay.reload(mainFrame.addPlay);
			CP(mainFrame.PanelName[4]);
		}else if(e.getSource()==btnDelPlay) {
			DelPlay.reload(mainFrame.delPlay);
			CP(mainFrame.PanelName[5]);
		}
	}

	public void CP(String str) {
		mainFrame.ChangePanel(str);
	}
}