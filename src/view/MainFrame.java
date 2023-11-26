package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.CheckInfo;


public class MainFrame extends JFrame implements ActionListener {
	/**
	 * ��½������
	 */
	private static final long serialVersionUID = 1L;
	JTextField idTextField;
	JPasswordField passwdTextField;
	JLabel idLabel, passwdLabel;
	Choice chooice;
	JButton logon;
	JPanel contain;
	
	int count = 0;

	public MainFrame() {
		super("�˺ŵ�½");
		setLocation(300, 200);
		setSize(300, 340);
		contain = new JPanel();
		contain.setLayout(null);
		idLabel = new JLabel("ID��");
		idLabel.setFont(new Font("����", Font.BOLD + Font.ITALIC, 14));
		idLabel.setForeground(new Color(111,222,0));
		passwdLabel = new JLabel("����");
		passwdLabel.setFont(new Font("����", Font.BOLD + Font.ITALIC, 14));
		passwdLabel.setForeground(new Color(111,222,0));
		idTextField = new JTextField();
		passwdTextField = new JPasswordField();
		logon = new JButton("��½");
		chooice = new Choice();
		chooice.addItem("ѧ��");
		chooice.addItem("��ʦ");
		chooice.addItem("ϵͳ����Ա");
		idLabel.setBounds(42, 45, 75, 35);
		idTextField.setBounds(80, 45, 150, 35);
		passwdLabel.setBounds(40, 100, 75, 35);
		passwdTextField.setBounds(80, 100, 150, 35);
		chooice.setBounds(80, 160, 150, 35);
		logon.setBounds(102, 220, 70, 30);
		contain.add(idLabel);
		contain.add(idTextField);
		contain.add(passwdLabel);
		contain.add(passwdTextField);
		contain.add(chooice);
		contain.add(logon);
		logon.addActionListener(this);
		add(contain);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logon) {
			String ch = (String) chooice.getSelectedItem();
			if (ch == "ѧ��") {
				if ((new CheckInfo().isMember("student", idTextField.getText(),
						new String(passwdTextField.getPassword()))) == 1) {
					this.dispose();
					// setVisible(false);
					new StudentsPanel(idTextField.getText());
				} else {
					count += 1;
					if (count <= 5) {
						JOptionPane.showMessageDialog(null, "�޴��û������������������",
								"����", JOptionPane.INFORMATION_MESSAGE);
					}
					if (count > 5) {
						JOptionPane.showMessageDialog(null, "�����������5�Σ�",
								"����", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						setVisible(false);
						System.exit(0);
					}
				}
			} else if (ch == "��ʦ") {
				if ((new CheckInfo().isMember("teacher", idTextField.getText(),
						new String(passwdTextField.getPassword(), 0,
								passwdTextField.getPassword().length))) == 1) {
					this.dispose();
					//setVisible(false);
					new TeachersPanel(idTextField.getText());
				} else {
					count += 1;
					if (count <= 5) {
						JOptionPane.showMessageDialog(null, "�޴��û������������������",
								"����", JOptionPane.INFORMATION_MESSAGE);
					}
					if (count > 5) {
						JOptionPane.showMessageDialog(null, "�����������5�Σ�",
								"����", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						setVisible(false);
						System.exit(0);
					}
				}
			} else if (ch == "ϵͳ����Ա") {
				if ((new CheckInfo().isMember("administrator", idTextField
						.getText(), new String(passwdTextField.getPassword(),
						0, passwdTextField.getPassword().length))) == 1) {
					this.dispose();
					//setVisible(false);  //��Ϊ�ɼ�
					new AdministratorPanel(idTextField.getText());
				} else {
					count += 1;
					if (count <= 5) {
						JOptionPane.showMessageDialog(null, "�޴��û������������������",
								"����", JOptionPane.INFORMATION_MESSAGE);
					}
					if (count > 5) {
						JOptionPane.showMessageDialog(null, "�����������5�Σ�",
								"����", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						setVisible(false);
						System.exit(0);
					}
				}
			}
		}
		
	}

	

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new MainFrame();

	}
}