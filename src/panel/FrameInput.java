package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameInput extends JFrame implements ActionListener{

	JPanel panelInput;
	JButton btnFinish;

	public FrameInput() {

		setBounds(200,200,800,600);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		panelInput=new JPanel();
		getContentPane().add(panelInput);
		panelInput.setLayout(null);
		setVisible(true);

		btnFinish = new JButton("終了");
		btnFinish.setBounds(680, 530, 90, 20);
		btnFinish.addActionListener(this);
		panelInput.add(btnFinish);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnFinish) {
			dispose();
		}
	}
}
