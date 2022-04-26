import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;



public class WelcomeFrame extends JInternalFrame {
	
	private JFrame jFrame;
	private JTextField txtField_workspace;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeFrame frame = new WelcomeFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
	
	public WelcomeFrame() {
		
		jFrame = new JFrame("Welcome!");
		jFrame.setAlwaysOnTop(true);
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setBounds(100, 100, 450, 300);
		
		jFrame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		    	String opt_buttons[] = {"Yes","No"};
		        int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[1]);
		        if(result == JOptionPane.YES_OPTION) {
		        	jFrame.dispose();
		        }
		    }
		});
		
		JButton btnNewButton = new JButton("Exisiting test");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(is_workfolder_valid(txtField_workspace)) {
					try {
						JFileChooser j = new JFileChooser();
						j.showOpenDialog(null);
						String file = j.getSelectedFile().getAbsolutePath();
						System.out.println(file);
						
						
						Vector<PhaseInfo> phase_vec = new Vector<PhaseInfo>();
						TestInfo test = new TestInfo();
						test.load_json(file, phase_vec);
						TestFrame tFrame = new TestFrame(phase_vec, txtField_workspace.getText());
						jFrame.dispose();
					} 
					catch (JsonSyntaxException err) {
						err.printStackTrace();
						NotifyFrame nf = new NotifyFrame("Could not read the JSON file. Please make sure the correct file is chosen.");
					} 
					catch (FileNotFoundException err) {
						// TODO Auto-generated catch block
						err.printStackTrace();
						NotifyFrame nf = new NotifyFrame("Could not open file.");
					} 
				} 
				
				else {
					NotifyFrame nf = new NotifyFrame("Please select a workfolder first.");
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("New Test");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(is_workfolder_valid(txtField_workspace)) {
					jFrame.dispose();
					TestFrame tFrame = new TestFrame(txtField_workspace.getText());
				}
				else {
					NotifyFrame nf = new NotifyFrame("Please select a workfolder first.");
				}
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("TestCV");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 30));
		
		txtField_workspace = new JTextField();
		txtField_workspace.setEditable(false);
		txtField_workspace.setColumns(10);
		
		JButton btn_workspace = new JButton("Select Workspace");
		
		btn_workspace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser j = new JFileChooser();
					j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int option = j.showOpenDialog(null);
					
					if(option == JFileChooser.APPROVE_OPTION) {
						System.out.println("ok");
						String folder = j.getSelectedFile().getAbsolutePath();
						txtField_workspace.setText(folder);
					} 
					else {
						System.out.println("bad");
					}
					
					
				}  
				catch (Exception err){
					err.printStackTrace();
					NotifyFrame nf = new NotifyFrame("Could not open file.");
				}
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(jFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(140)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(165, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtField_workspace, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
							.addComponent(btnNewButton_1)))
					.addGap(90))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(152)
					.addComponent(btn_workspace)
					.addContainerGap(165, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(lblNewLabel)
					.addGap(44)
					.addComponent(txtField_workspace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_workspace)
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(62))
		);
		jFrame.getContentPane().setLayout(groupLayout);

	}
	
	public boolean is_workfolder_valid(JTextField txtField) {
		return txtField.getText().isEmpty() ? false : true;
	}
}
