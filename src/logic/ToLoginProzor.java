package logic;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import gui.LoginProzor;

public class ToLoginProzor implements Komanda{
	private JFrame signupFrame;
	
	public ToLoginProzor(JFrame signupFrame) {
		this.signupFrame = signupFrame;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		signupFrame.dispatchEvent(new WindowEvent(signupFrame, WindowEvent.WINDOW_CLOSING));
		LoginProzor.main(null);
	}

}
