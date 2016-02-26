import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;


public class CarWashGUI {

	static CarWash EthanCarWash;
	static JTextField hoursField;
	static JTextField minutesField;
	static JTextField carsWaitingField;
	static JTextArea display;
	
	public CarWashGUI() {
		Container c;
		JFrame window;
		JPanel main, inputPanel;
		JScrollPane displayPane;
		JLabel hours, minutes, carsWaiting;
		JButton simulate;
		
		window = new JFrame("Ethan's Car Wash");
		main = new JPanel();
		inputPanel = new JPanel();
		hours = new JLabel("Hours: ");
		minutes = new JLabel("Minutes: ");
		carsWaiting = new JLabel("Cars Waiting: ");
		hoursField = new JTextField();
		minutesField = new JTextField();
		carsWaitingField = new JTextField();
		simulate = new JButton("Simulate");
		display = new JTextArea();
		c = window.getContentPane();
		
		BorderLayout bl = new BorderLayout();
		GridLayout inputLayout = new GridLayout(3,2);
		
		c.add(main);
		
		window.setSize(500,200);
		window.setLocation(450,200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		simulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int hours, minutes, carsWaiting;
				
				hours = Integer.parseInt(hoursField.getText());
				minutes = Integer.parseInt(minutesField.getText());
				carsWaiting = Integer.parseInt(carsWaitingField.getText());
				CarWash EthanCarWash = new CarWash(hours, minutes, carsWaiting);
				display.setText(EthanCarWash.simulate());
			}
		});
		
		main.setLayout(bl);
		main.add(display, BorderLayout.CENTER);
		main.add(inputPanel, BorderLayout.WEST);
		main.add(simulate, BorderLayout.SOUTH);
		
		displayPane = new JScrollPane(display);
		displayPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		main.add(displayPane);
		
		inputPanel.setLayout(inputLayout);
		inputPanel.add(hours);
		inputPanel.add(hoursField);
		inputPanel.add(minutes);
		inputPanel.add(minutesField);
		inputPanel.add(carsWaiting);
		inputPanel.add(carsWaitingField);
		
		
		window.setVisible(true);
		
	}
}
