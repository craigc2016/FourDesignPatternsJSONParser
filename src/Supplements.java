
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class Supplements extends JFrame implements ListSelectionListener,ActionListener{

	private JPanel panelC,panelE,panelS;
	private JList<String> jlist;
	private JLabel labelImage,labelDisplay,labelComp;
	private JButton buy,comp,back;
	private JRadioButton yes,no;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Jersey jersey = new Jersey ();
		jersey.setVisible(true);
		jersey.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Create the frame.
	 */
	
	public Supplements() {
		super("Supplement Page ");
		getContentPane().add(buildCenterPanel(),BorderLayout.CENTER);
		getContentPane().add(buildSouthPanel(), BorderLayout.SOUTH);
		getContentPane().add(buildEastPanel(), BorderLayout.EAST);
		setVisible(true);
		setSize(500,500);
		setLocationRelativeTo(null);
	}
	
	public JPanel buildCenterPanel(){
		panelC = new JPanel(new GridLayout(2,2));
		/*
		 * Create the jersey list by using the simple factory pattern
		 * Get the values from a json file that will be used in the
		 * factory pattern
		 */
		String list = new String (Json().getSupplement());
		ItemsFactory factory = new ItemsFactory();
		factory.getItemsList(list);
		jlist = factory.getItemsList(list).display();
		jlist.addListSelectionListener(this);
		labelDisplay = new JLabel();
		labelImage = new JLabel();
		labelComp = new JLabel();
		
		//add components to panel
		panelC.add(jlist);
		panelC.add(labelDisplay);
		panelC.add(labelImage);
		panelC.add(labelComp);
		
		return panelC;
	}//end buildCenterPanel
	
	public JPanel buildEastPanel(){
		panelE = new JPanel(new GridLayout(2,1));
		/*
		 * Create the radio buttons by using the
		 * facade pattern. And add the action listener
		 * to the radio buttons. And set the radio buttons to hide
		 */
		yes = getComp().displayRadioYes();
		no = getComp().displayRadioNo();
		yes.addActionListener(this);
		no.addActionListener(this);
		yes.setVisible(false);
		no.setVisible(false);
		/*
		 * Add the components to the panel
		 */
		panelE.add(yes);
		panelE.add(no);
		return panelE;
	}//end buildEastPanel
	
	public JPanel buildSouthPanel(){
		panelS = new JPanel(new GridLayout(1,3));
		/*
		 * Create the buttons for the GUI
		 */
		buy = new JButton("Buy");
		comp = new JButton("Enter Competition");
		back = new JButton("Back");
		/*
		 * Add the ActionListener to the buttons.
		 * And set the buttons to hide on the GUI
		 */
		buy.addActionListener(this);
		comp.addActionListener(this);
		back.addActionListener(this);
		buy.setVisible(false);
		comp.setVisible(false);
		back.setVisible(false);
		/*
		 * Add the components to the panel
		 */
		panelS.add(buy);
		panelS.add(comp);
		panelS.add(back);
		return panelS;
	}//end buildSouthPanel
	
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()){
			if(jlist.getSelectedValue().equals("Protein")){
				/*
				 * Get the index of the item chosen. Passed to the json file
				 * which uses it to display the data of the item selected in the list
				 */
				int index = jlist.getSelectedIndex();
				labelDisplay.setText(Json().getSupplementDescription(index));
				labelImage.setIcon(new ImageIcon(Json().getSupplementImage(index)));
				getBuyComp(); // set the buttons visible 
			}
			if(jlist.getSelectedValue().equals("Creatine")){
				/*
				 * Get the index of the item chosen. Passed to the json file
				 * which uses it to display the data of the item selected in the list
				 */
				int index = jlist.getSelectedIndex();
				labelDisplay.setText(Json().getSupplementDescription(index));
				labelImage.setIcon(new ImageIcon(Json().getSupplementImage(index)));
				getBuyComp();// set the buttons visible 
			}
			
		}//end outter if
		
	}//end list
	
	
	@SuppressWarnings("deprecation")
	/*
	 * Method to respond to the users input from the buttons and radio buttons
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == yes){
			/*
			 * Set the data from the label by using the facade pattern
			 */
			labelComp.setText("<html>"+getComp().displaySupplementYes()+"<br></html>");
			getRadioBuy();
		}
		if(e.getSource() == no){
			/*
			 * Set the data from the label by using the facade pattern
			 */
			labelComp.setText("<html>"+getComp().displaySupplementNo()+"<br></html>");
			getRadioBuy();
		}
		if(e.getSource() == buy){
			/*
			 * Set the test of the label to empty and show a dialog when the user
			 * clicks the buy button. Set the competition button visible
			 */
			labelComp.setText("");
			JOptionPane.showMessageDialog(null,"Item purchased");
			comp.setVisible(true);
			
		}
		if(e.getSource() == comp){
			/*
			 * Set the radio buttons to show. Sets the radio buttons to hide if 
			 * they are already been checked
			 */
			yes.setVisible(true);
			no.setVisible(true);
			if(yes.isSelected() || no.isSelected()){
				yes.setSelected(false);
				no.setSelected(false);
			}
		}//end if 
		if(e.getSource() == back){
			/*
			 * Creates a new MainApplicationPage when the user
			 * clicks the button. And disposes the current page 
			 */
			MainAppilcationPage frame = new MainAppilcationPage();
			frame.show();
			dispose();
		}
		
	}//end actionPerformed
	
	/*
	 * Method to show the buttons buy and back
	 */
	public void getBuyComp(){
		buy.setVisible(true);
		back.setVisible(true);
	}
	
	/*
	 * Method to hide the button for competition and radio buttons
	 * Allow the user to then use the list 
	 */
	public void getRadioBuy(){
		yes.setVisible(false);
		no.setVisible(false);
		comp.setVisible(false);
		jlist.setEnabled(true);
	}
	
	public JsonUtil Json(){
		return new JsonUtil();
	}
	
	/*
	 * Method to create and object of the CompMaker class
	 */
	public CompMaker getComp(){
		return new CompMaker();
	}
}

