import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator implements ActionListener  {
	
	JFrame frame;
	JPanel panel;
	JTextField textfield;
	JButton[] numberButtons = new JButton[10];
	JButton[] funcButtons = new JButton[8];
	JButton addButton, subButton, mulButton, divButton;
	JButton decButton, equButton, delButton, clrButton;

    Font hel = new Font("Helvetica", Font.BOLD,30);
    double num1 = 0, num2 = 0, result = 0;
    char operator = '+';
    //Color Palette
    Color bGround = new Color(31,31,31);
    Color yellow = new Color(181,159,59,255);
    Color lightGrey = new Color(128,130,132,255);
    Color green = new Color(83,141,79,255);
    Color darkGrey = new Color(59,59,60,255);
	
	Calculator () {
		//Set up the JFrame
		frame = new JFrame("Calculator");						// new Frame - set title as calc
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// if this was missing, program would not close
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 				// max the screen
		frame.setSize(420, 560); 							// set size to this for now
		frame.setLayout(null);								// using grid layout
		frame.getContentPane().setBackground(bGround); 		  			// set background color
		
		// Display the text
		textfield = new JTextField("0");
		textfield.setBounds(50,25,300,50);					       	// Position x, y, length, width
		textfield.setFont(hel);								// Use Helvetica
		textfield.setHorizontalAlignment(JTextField.CENTER);				// Center align the input
		textfield.setEditable(false); 					          	// Doesn't allow input - only from buttons
		
		// Button - Operators
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("DEL");
		clrButton = new JButton("CLEAR");
		
		funcButtons[0] = addButton;
		funcButtons[1] = subButton;
		funcButtons[2] = mulButton;
		funcButtons[3] = divButton;
		funcButtons[4] = decButton;
		funcButtons[5] = equButton;
		funcButtons[6] = delButton;
		funcButtons[7] = clrButton;
		
		for(int i = 0; i < 8; i++) {
			funcButtons[i].addActionListener(this);
			funcButtons[i].setFont(hel);
			funcButtons[i].setFocusable(false);			        	// Removes annoying default
			funcButtons[i].setForeground(bGround);
			funcButtons[i].setBackground(Color.WHITE);
			
		}
		// Numbers 0 - 9
		for(int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(hel);
			numberButtons[i].setFocusable(false);
			numberButtons[i].setForeground(bGround);
		}
		
		// Layout of buttons
		delButton.setBounds(50,430,145,50);
		clrButton.setBounds(205,430,145,50);
		// Store Buttons in panel (container)
		panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(bGround);
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4,4,10,10));
		// 1st row
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		// 2nd row
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		// 3rd row
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		//4th row
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);
		
		// Finalize the JFrame
		frame.add(panel);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textfield);
		frame.setVisible(true);						          	// Required for window to open
	}

	public static void main(String[] args) {
		new Calculator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 10; i++) {
			if(e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}
		if (e.getSource()==decButton) 
			textfield.setText(textfield.getText().concat("."));
		
		if (e.getSource()==addButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '+';
			textfield.setText("");
		}
		if (e.getSource()==subButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '-';
			textfield.setText("");
		}
		if (e.getSource()==mulButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '*';
			textfield.setText("");
		}
		if (e.getSource()==divButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '/';
			textfield.setText("");
		}
		if(e.getSource()==equButton) {
			num2=Double.parseDouble(textfield.getText());
			textfield.setText(String.valueOf(solve(num1, num2, operator)));
		}
		if (e.getSource()==delButton) {
			String s = textfield.getText();
			textfield.setText(s.substring(0, s.length() - 1));
			
		}
		if (e.getSource()==clrButton) 
			textfield.setText("");
	}

	public double solve(double num1, double num2, char operator) {
		double result = 0;
		
		switch(operator) {
		case '+': result=num1+num2;break;
		case '-': result=num1-num2;break;
		case '*': result=num1*num2;break;
		case '/': result=num1/num2;break;
		}
		num1 = result;
		return result;
	}
}
