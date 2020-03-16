package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import package1.FileManage;

public class Score extends JPanel implements ActionListener{

	MainFrame mainFrame;
	String panelName,path;
	JButton btnMakeFile,btnChoose,btnDecision,btnBackHome;
	JTextField fileName;
	JLabel lblTitle;

	public Score(MainFrame m,String s){

		mainFrame=m;
		panelName=s;

		m.setTitle("ScoreSheet");
		this.setName(s);
		this.setLayout(null);
		this.setSize(800,600);

		btnMakeFile = new JButton("作成");
		btnChoose=new JButton("ファイル選択");
		btnDecision = new JButton("決定");
		btnBackHome = new JButton("戻る");
		lblTitle = new JLabel("ファイル名");
		fileName = new JTextField();

		btnMakeFile.setBounds(583, 49, 110, 21);
		btnChoose.setBounds(583, 100, 110, 21);
		btnDecision.setBounds(583, 530, 87, 21);
		btnBackHome.setBounds(680, 530, 90, 20);
		lblTitle.setBounds(172, 49, 62, 20);
		fileName.setBounds(236, 50, 338, 19);
		fileName.setColumns(10);

		btnMakeFile.addActionListener(this);
		btnChoose.addActionListener(this);
		btnDecision.addActionListener(this);
		btnBackHome.addActionListener(this);

		add(btnMakeFile);
		add(btnChoose);
		add(btnDecision);
		add(btnBackHome);
		add(lblTitle);
		add(fileName);
	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnMakeFile) {
			if(fileName.getText().equals(null)) {

			}else {
				String str=fileName.getText();
				FileManage.createFile(str);
				fileName.setText(null);
			}
		}else if(e.getSource()==btnChoose) {
			path=FileManage.chooseFile(mainFrame);		//選択したファイルのパスを取得
		}else if(e.getSource()==btnDecision) {
			if(path.equals(null)) {

			}else {
				FrameInput frame=new FrameInput();
			}
		}else if(e.getSource()==btnBackHome) {
			CP(mainFrame.PanelName[0]);
		}
	}

	public void CP(String str) {
		mainFrame.ChangePanel(str);
	}
}
