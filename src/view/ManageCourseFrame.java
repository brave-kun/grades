package view;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AddCourse;
import controller.DeleteCourse;
//import controller.EditCourse;


@SuppressWarnings("serial")
public class ManageCourseFrame extends JFrame implements ActionListener {
    /*
     * ����Ա��½�����������
     */
    JButton deleteCourse, addCourse, editCourse, goBack;
    JPanel contain;
    String idd;

    public ManageCourseFrame(String idd) {
        super("�γ̹������");
        this.idd  = idd;
        setLocation(300, 200);
        setSize(300, 340);
        contain = new JPanel();
        contain.setLayout(null);
        add(contain);
        addCourse = new JButton("����γ�");
        deleteCourse = new JButton("ɾ���γ�");
        editCourse = new JButton("�޸Ŀγ�");
        goBack = new JButton("����");
        addCourse.setBounds(70, 45, 140, 30);
        deleteCourse.setBounds(70, 100, 140, 30);
        editCourse.setBounds(70, 100, 140, 30);
        goBack.setBounds(70, 100, 140, 30);
        contain.add(addCourse);
        contain.add(deleteCourse);
        contain.add(editCourse);
        contain.add(goBack);
        goBack.addActionListener(this);
        addCourse.addActionListener(this);
        deleteCourse.addActionListener(this);
        editCourse.addActionListener(this);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourse) {
            new AddCourse();    // �û������ʼ��ͳһΪ123456
        } else if (e.getSource() == deleteCourse) {
            new DeleteCourse();
        } else if (e.getSource() == editCourse) {
            //new EditCourse();
        } else if (e.getSource() == goBack) {
            new AdministratorPanel(idd);
        }
    }

    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }
}