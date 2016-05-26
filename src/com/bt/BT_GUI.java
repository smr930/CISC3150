package com.bt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BT_GUI extends JPanel implements ActionListener {
	static Scanner input;
	boolean bluepressed;
	boolean orangepressed;
	boolean finished;
	int numberOfOrders;
	int currOrder;
	Timer t;

	Bot BlueBot;
	Bot OrangeBot;

	int boxSize = 20;
	Color boxColor1 = Color.RED;
	Color boxColor2 = Color.PINK;
	int verticalSpace = 100;
	int cases;
	
	BT_GUI() {
		setBackground(Color.WHITE);
        t = new Timer(1000, this);
        BlueBot = new Bot(0, verticalSpace, boxSize, Color.BLUE);
        OrangeBot = new Bot(0,verticalSpace,boxSize, Color.ORANGE);

        try {
        	input = new Scanner(new File("A-small-practice.in"));
		}catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}

        cases = input.nextInt();
        cases++;
        currOrder = 0;
        numberOfOrders = -1;
        bluepressed = false;
        orangepressed = false;
        finished = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Draw 100 boxes
		for(int sq = 0; sq < 50; sq++) {
			if(sq % 2 == 0){
				g.setColor(boxColor1);
			}
			else g.setColor(boxColor2);
			g.fillRect(sq * boxSize, verticalSpace, boxSize, boxSize);
		}
		
		for(int sq = 0; sq < 50; sq++) {
			if(sq % 2 == 0){
				g.setColor(boxColor1);
			}
			else g.setColor(boxColor2);
			g.fillRect(sq * boxSize, verticalSpace + 40, boxSize, boxSize);
		}

		//Draw the Robots
		g.setColor(BlueBot.color);
		g.fillOval(BlueBot.topLeft.x, BlueBot.topLeft.y, boxSize, boxSize);
		g.setColor(OrangeBot.color);
		g.fillOval(OrangeBot.topLeft.x, OrangeBot.topLeft.y, boxSize, boxSize);

		int currCase = 20 - (cases-1);
		String dstr;

		if(currCase <= 0)
			dstr = "Case: 1";
		else if(finished)
			dstr = "Case: DONE";
		else dstr = "Case: " + currCase;
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString(dstr, 450, 30);
		
		//Draw OrangeBot Position
		dstr = "OrangeBot Position: " + OrangeBot.getPosition() ;
		g.setColor(Color.ORANGE);
		g.drawString(dstr, 400, 50); // 10, 30
		
		//Draw BlueBot Position
		dstr = "BlueBot Position: " + BlueBot.getPosition() ;
		g.setColor(Color.BLUE);
		g.drawString(dstr, 410, 70);
		
		//Check to see if a robot pressed a button and if one did, display it
		if(bluepressed == true) {
			dstr = "BlueBot Pressed Button " + BlueBot.getPosition();
			g.setColor(Color.BLUE);
			g.drawString(dstr, 600, 70);
			bluepressed = false;
		}else if(orangepressed == true) {
			dstr = "OrangeBot Pressed Button " + OrangeBot.getPosition();
			g.setColor(Color.ORANGE);
			g.drawString(dstr, 600, 50);
			orangepressed = false;
		}else if(finished == true) {
			dstr = "Completed";
			g.setColor(Color.BLACK);
			g.drawString(dstr, 800, 50);
			stop_timer();
		}
	}

	public void actionPerformed(ActionEvent e) {
		step();
	}

	public void start_timer() {
		t.start();
	}	
	
	public void stop_timer() {
		t.stop();
	}

	void step() {
		if(currOrder <= numberOfOrders) {
			if(BlueBot.getOrder() == currOrder) {
				if(BlueBot.getGoal() == BlueBot.getPosition()) {
					currOrder++;
					bluepressed = true;
					BlueBot.assign();
				} else {
					BlueBot.move();
				}
				OrangeBot.move();
				repaint();
			}

			else if(OrangeBot.getOrder() == currOrder) {
				if(OrangeBot.getGoal() == OrangeBot.getPosition()) {
					currOrder++;
					orangepressed = true;
					OrangeBot.assign();
				} else {
					OrangeBot.move();
				}
				BlueBot.move();
				repaint();
			}
		} else {
			nextOrdersSet();
			BlueBot.restart();
			OrangeBot.restart();
			repaint();
			cases--;
		}
	}

	void nextOrdersSet() {
		if(input.hasNext()) {
		int maxturn = input.nextInt();
		for(int index = 1; index <= maxturn; index++) {
			String currLetter = input.next();
			currLetter.toUpperCase();
			int desiredLocation = input.nextInt();			
			if(currLetter.matches("O") ) {
				OrangeBot.addPair(index, desiredLocation);
			} else if (currLetter.matches("B")) {
				BlueBot.addPair(index, desiredLocation);
			}
		}
		setOrders(maxturn);
		OrangeBot.assign();
		BlueBot.assign();
	} else {
		finished = true;
		}
	}

	void setOrders(int num) {
		numberOfOrders = num;
		currOrder = 1;
	}
}
