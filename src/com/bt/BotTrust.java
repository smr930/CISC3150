package com.bt;

import java.awt.BorderLayout;
import javax.swing.*;

public class BotTrust {
	public static void main(String[] args) {
		BT_GUI b_gui = new BT_GUI();
		JFrame frame = new JFrame("Bot Trust");
		frame.setLayout(new BorderLayout());
		frame.add(b_gui,BorderLayout.CENTER);

		frame.setSize(1020, 300);
		frame.setVisible(true);
		//frame.setResizable(false);
		
		b_gui.start_timer();
	}
}
