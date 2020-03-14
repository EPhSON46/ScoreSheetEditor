package panel;

import java.awt.CardLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame{

	public String[] PanelName= {"Home","Score","AddTeam","DelTeam","AddPlay","DelPlay"};

	Home homepnl	=new Home(this,PanelName[0]);
	Score scorepnl	=new Score(this,PanelName[1]);
	AddTeam addTeam	=new AddTeam(this,PanelName[2]);
	DelTeam delTeam=new DelTeam(this,PanelName[3]);
	AddPlay addPlay	=new AddPlay(this,PanelName[4]);
	DelPlay delPlay	=new DelPlay(this,PanelName[5]);

	public JPanel[] panels= {homepnl,scorepnl,addTeam,delTeam,addPlay,delPlay};
	private final CardLayout layout = new CardLayout();

	public MainFrame() {
		setBounds(100,100,800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(layout);
		for(JPanel panel:panels) {
			mainPanel.add(panel,panel.getName());
		}
		add(mainPanel);
		setVisible(true);
	}

	public void ChangePanel(String str) {
		int index=Arrays.asList(PanelName).indexOf(str);
		layout.show(panels[index].getParent(), panels[index].getName());
		this.setTitle(PanelName[index]);
	}
}