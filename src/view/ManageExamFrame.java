package view;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AddExam;
import controller.DeleteExam;
import controller.EditExam;
import controller.ExamView;

@SuppressWarnings("serial")
public class ManageExamFrame extends JFrame implements ActionListener {
    /*
     * 管理员登陆后操作主界面
     */
    JButton deleteCourse, addCourse, editCourse, goBack, examView;
    JPanel contain;
    String idd;

    public ManageExamFrame(String idd) {
        super("考试管理界面");
        this.idd  = idd;
        setLocation(300, 200);
        setSize(300, 340);
        contain = new JPanel();
        contain.setLayout(new GridLayout(4,1,0,0));
        add(contain);
        addCourse = new JButton("增设考试");
        deleteCourse = new JButton("删除考试");
        editCourse = new JButton("修改考试");
        examView = new JButton("查询考试");
        goBack = new JButton("返回");
        addCourse.setBounds(70, 50, 140, 30);
        deleteCourse.setBounds(70, 100, 140, 30);
        editCourse.setBounds(70, 100, 140, 30);
        goBack.setBounds(70, 250, 140, 30);
        editCourse.setBounds(70, 150, 140, 30);
        examView.setBounds(70,200,140,30);
        contain.add(addCourse);
        contain.add(deleteCourse);
        contain.add(editCourse);
        contain.add(examView);
        //contain.add(goBack);
        goBack.addActionListener(this);
        addCourse.addActionListener(this);
        deleteCourse.addActionListener(this);
        editCourse.addActionListener(this);
        examView.addActionListener(this);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourse) {
            new AddExam();    // 用户密码初始化统一为123456
        } else if (e.getSource() == deleteCourse) {
            new DeleteExam();
        } else if (e.getSource() == editCourse) {
            new EditExam();
        } else if (e.getSource() == goBack) {
            //new AdministratorPanel(idd);
            this.dispose();
            setVisible(false);
        } else if (e.getSource() == examView){
            new ExamView(idd,2);
        }
    }

    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }
}