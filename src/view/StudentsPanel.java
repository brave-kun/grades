package view;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.*;


@SuppressWarnings("serial")
public class StudentsPanel extends JFrame implements ActionListener {
	/*
	 * ѧ����½�����������
	 */
	JPanel contain;
	String id;
	JButton infoButton, gradeButton, courseButton, editButton, logOut, selectCourseButton, deleteCourseButton,classTableButton;

	public StudentsPanel(String id) {
		super("ѧ��");
		this.id = id;
		setLocation(300, 200);
		setSize(300, 480);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		infoButton = new JButton("��Ϣ��ѯ");
		gradeButton = new JButton("�ɼ���ѯ");
		courseButton = new JButton("�γ̲�ѯ");
		editButton = new JButton("�޸���Ϣ");
		selectCourseButton = new JButton("ѡ��");
		deleteCourseButton = new JButton("�˿�");
		classTableButton = new JButton("�鿴�α�");
		logOut = new JButton("�˳���¼");
		infoButton.setBounds(70, 40, 140, 30);
		gradeButton.setBounds(70, 80, 140, 30);
		courseButton.setBounds(70, 120, 140, 30);
		editButton.setBounds(70, 160, 140, 30);
		selectCourseButton.setBounds(70, 200, 140, 30);
		deleteCourseButton.setBounds(70, 240, 140, 30);
		classTableButton.setBounds(70, 280, 140, 30);
		logOut.setBounds(70, 320, 140, 30);

		contain.add(infoButton);
		infoButton.addActionListener(this);
		contain.add(gradeButton);
		gradeButton.addActionListener(this);
		contain.add(courseButton);
		courseButton.addActionListener(this);
		contain.add(editButton);
		editButton.addActionListener(this);
		contain.add(selectCourseButton);
		selectCourseButton.addActionListener(this);
		contain.add(deleteCourseButton);
		deleteCourseButton.addActionListener(this);
		contain.add(classTableButton);
		classTableButton.addActionListener(this);
		contain.add(logOut);
		logOut.addActionListener(this);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == infoButton) {
			new Info(id, 1);
		}
		if (e.getSource() == gradeButton) {
			new GradeInfo(id);
		}
		if (e.getSource() == courseButton) {
			new CourseView(id, 0);
		}
		if (e.getSource() == editButton) {
			new StuEditInfo(id);     ////
		}
		if (e.getSource() == selectCourseButton){
			new StuSelectCourse(id);
		}
		if (e.getSource() == deleteCourseButton){
			new StuDeleteCourse(id);
		}
		if(e.getSource() == logOut){
			this.dispose();
			new MainFrame();
		}
		if (e.getSource() == classTableButton){
			new ClassTable(id);
		}
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
			System.exit(0);
		}
	}
}
