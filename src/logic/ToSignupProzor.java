package logic;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import gui.SignupProzor;

public class ToSignupProzor implements Komanda{
	private JFrame loginFrame;
	
	public ToSignupProzor(JFrame loginFrame) {
		this.loginFrame = loginFrame;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
		SignupProzor.main(null);
	}

}
