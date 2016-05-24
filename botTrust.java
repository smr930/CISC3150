package botTrust;

import static java.lang.Math.abs;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import static java.lang.Math.max;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BotTrust {
	JLabel questionLabel;
	JButton button;
	JFrame frame = new JFrame();
	JPanel boxes = new JPanel();
	JButton GUIbutton;
	Color c = null;
	
	public static int cases;

	void runTest() throws FileNotFoundException, InterruptedException {
    	//Create and set up the window.		
		frame.setTitle("Bot Trust");
		frame.setSize(900, 480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		System.setIn(new FileInputStream("A-small-practice.in"));
		System.setOut(new PrintStream(new FileOutputStream("A-small-practice.out")));

		Scanner in = new Scanner(System.in);
		cases = in.nextInt(); in.nextLine();
		int time = 0;
		boxes.setLayout(new GridLayout(2, 2, 2,2));
		for (int i = 1; i <= BotTrust.cases; i++) {
			button = new JButton(Integer.toString(i));
			button.setFont(new Font("Arial", Font.BOLD, 16));				
			button.setBackground(c.white);			
			boxes.add(button);
		}
		
		frame.add(boxes);
		frame.pack();
		
		for (long i = 1; i <= cases; i++) {    	
	    	int posOrange = 1;
	    	int posBlue = 1;
	    	int timeOrange = 0;
	    	int timeBlue = 0;
	    	int n = in.nextInt();
	    		
	    	for (int j = 0; j < n; j++) {				
	    		if ("O".equals(in.next())) {
	    	 		int btn = in.nextInt();
	        		int add =  max(1 - timeOrange, abs(btn-posOrange) + 1 - timeBlue);
	 	    		timeOrange += add;
		    		time += add;
	 	    		timeBlue = 0;
	 	    		posOrange = btn;	
	 	    		Thread.sleep(500);
	 	    		button.setBackground(c.orange);

	    		} else {
	    	 		int btn = in.nextInt();
	        		int add =  max(1 - timeBlue, abs(btn-posBlue) + 1 - timeOrange);
	 	    		timeBlue += add;
		    		time += add;
		    		timeOrange = 0;
	 	    		posBlue = btn;
	 	    		Thread.sleep(500);
	 	    		button.setBackground(c.blue);
	    		}   	
    	}
    	Thread.sleep(500);
    	System.out.println(String.format("Case #%d: %d", i, time));
    }

}
	
	public static void main(String[] args) throws FileNotFoundException {
		BotTrust b = new BotTrust();
		try {
			b.runTest();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            }
        });

	}

}
