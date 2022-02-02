import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Gui implements ActionListener  {
	
	private int count = 0;
	private JLabel label;
	private JFrame frame;
	private JPanel panel;
	private JButton button;
	
	public Gui() {
		frame = new JFrame();
		panel = new JPanel();
		button = new JButton("Click!");
		button.addActionListener(this);
		label = new JLabel("Number of clicks: " + 0);
		
		
		panel.setBorder(BorderFactory.createEmptyBorder(225,225,75,225));
		panel.setLayout(new GridLayout(0,1));
		panel.add(button);
		panel.add(label);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Click");
		frame.pack();
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // max the screen
	}

	public static void main(String[] args) {
		new Gui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count++;
		label.setText("Number of clicks: " + count);
	}

}
