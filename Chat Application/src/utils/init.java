package utils;

import java.awt.EventQueue;

public class init {
	public static void main(String[] args ) {
		EventQueue.invokeLater(
			new Runnable() {
				public void run() {
					try {
						Login window = new Login();
						window.loginWindow.setVisible(true);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		);
	}
}
