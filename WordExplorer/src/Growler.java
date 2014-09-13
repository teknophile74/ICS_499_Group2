import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.lang.reflect.Method;

public class Growler extends JFrame implements ActionListener {

	/**
	 * @author sstruhar
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Timer timer1;

	/**
	 * Create the frame and set the text message
	 * @param the message to set in the growler
	 */
	public Growler( String message , Color borderColor , Color bodyColor) {
		setBackground(bodyColor);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(50, 50, 218, 40);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(borderColor, 3, true));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(bodyColor);
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel label = new JLabel("");
		panel.add(label);
		this.setSize(message.length()*10, this.getHeight()+10);
		label.setText(message);
		timer1 = new Timer(1000, this);
		timer1.setInitialDelay(1000);
		timer1.start();
	}

	/**
	 * stop the timer and initiate the fade (if your machine can do it) then close and dispose of the growl
	 * Borrowed some of this code from http://www.rune-server.org/runescape-development/rs2-client/snippets/257338-jframe-transparency.html
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		timer1.stop();
		try{
			Class<?> awtUtilitiesClass = Class.forName("com.sun.awt.AWTUtilities");
			Method mSetWindowOpacity = awtUtilitiesClass.getMethod("setWindowOpacity", Window.class, float.class);
			for(float i = 0.95f; i > 0.0; i = i-0.001f){
				mSetWindowOpacity.invoke(null, this, Float.valueOf(i));
			}
		}
		catch (Exception e1)
		{   
			//Swallow the error.  Might consider changing this to indicate that an update is needed.
		}
		finally{
			this.setVisible(false);//hide
			this.dispose();//destroy
		}


	}

}
