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
     * 管理员登陆后操作主界面
     */
    JButton deleteCourse, addCourse, editCourse, goBack;
    JPanel contain;
    String idd;

    public ManageCourseFrame(String idd) {
        super("课程管理界面");
        this.idd  = idd;
        setLocation(300, 200);
        setSize(300, 340);
        contain = new JPanel();
        contain.setLayout(null);
        add(contain);
        addCourse = new JButton("增设课程");
        deleteCourse = new JButton("删除课程");
        editCourse = new JButton("修改课程");
        goBack = new JButton("返回");
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
            new AddCourse();    // 用户密码初始化统一为123456
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