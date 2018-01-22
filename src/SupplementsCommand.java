

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SupplementsCommand extends JFrame implements Command {

	
	private JFrame frame;
	private JPanel pnl;
	
	 public SupplementsCommand(JFrame fr, JPanel p) {
	      frame = fr;
	      pnl = p;
	   }
	public void Execute() {
		new Supplements().setVisible(true);
	}	

}
