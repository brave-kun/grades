package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/24 11:14
 */

public class StuSelectCourse extends JFrame implements ActionListener {


    String stuId;
    JPanel contain;
    JButton submit;
    JLabel courseId, courseName, teacherId, teacherName;
    JTextField courseIdT, courseNameT, teacherIdT, teacherNameT;

    public StuSelectCourse(String stuId) {
        super("选课");
        this.stuId = stuId;
        setSize(400, 450);
        setLocation(600, 400);
        contain = new JPanel();
        contain.setLayout(null);

        courseId = new JLabel("课程号");
        courseName = new JLabel("课程名");
        teacherId = new JLabel("教师号");
        teacherName = new JLabel("教师名");
        submit = new JButton("提交");

        courseIdT = new JTextField();
        courseNameT = new JTextField();
        teacherIdT = new JTextField();
        teacherNameT = new JTextField();

        courseId.setBounds(40, 35, 75, 35);
        courseIdT.setBounds(80, 35, 150, 35);

        courseName.setBounds(40, 90, 75, 35);
        courseNameT.setBounds(80, 90, 150, 35);


        teacherId.setBounds(40, 145, 75, 35);
        teacherIdT.setBounds(80, 145, 150, 35);

        teacherName.setBounds(40, 200, 75, 35);
        teacherNameT.setBounds(80, 200, 150, 35);

        submit.setBounds(102, 300, 70, 30);

        contain.add(courseId);
        contain.add(courseIdT);
        contain.add(courseName);
        contain.add(courseNameT);
        contain.add(teacherId);
        contain.add(teacherIdT);
        contain.add(teacherName);
        contain.add(teacherNameT);
        contain.add(submit);

        submit.addActionListener(this);
        add(contain);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    public void actionPerformed(ActionEvent e) {
        String number = hasCourse(courseIdT.getText(), courseNameT.getText(), teacherIdT.getText(), teacherNameT.getText());
        if (e.getSource() == submit) {
            if ((courseIdT.getText().equals("")) || (courseNameT.getText().equals("")) || (teacherIdT.getText().equals("")) || (teacherNameT.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "信息不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (number.equals("none")) {
                    JOptionPane.showMessageDialog(null, "此课程不存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    File csFile = new File(System.getProperty("user.dir") + "/data/course_student/" + courseNameT.getText() + "_student.txt");

                    ArrayList<String> modifiedContent = new ArrayList<>();

                    try {
                        BufferedReader br = new BufferedReader(new FileReader(csFile));
                        String s = null;
                        while ((s = br.readLine()) != null) {  // 先将原来存在的信息存储起来
                            String[] result = s.split(" ");

                            String s1 = "";
                            for (int i = 0; i < result.length - 1; i++) {
                                s1 = s1 + result[i];
                                s1 = s1 + " ";
                            }
                            s1 = s1 + result[result.length - 1];
                            // System.out.println(s1);
                            modifiedContent.add(s1);
                        }
                        br.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    modifiedContent.add(courseIdT.getText() + " " + courseNameT.getText() + " " + stuId + " " + teacherIdT.getText() + " " + teacherNameT.getText() + " " + number);

                    try {
                        FileWriter fw = new FileWriter(csFile);
                        BufferedWriter bw = new BufferedWriter(fw);

                        for (int i = 0; i < modifiedContent.size(); i++) {
                            bw.write(modifiedContent.get(i));
                            bw.newLine();
                        }

                        bw.close();
                        fw.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "选课成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    setVisible(false);
                }

            }
        }
    }

    public String hasCourse(String courseId, String courseName, String teacherId, String teacherName) {  //  教师开课前检查课程是否已经存在

        String file = System.getProperty("user.dir") + "/data/course.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file)); //构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                String[] result = s.split(" ");
                if ((result[0].equals(courseId)) && (result[1].equals(courseName) && (result[4].equals(teacherId) && (result[5].equals(teacherName))))) {
                    return result[6];

                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "none";
    }

    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }
}
