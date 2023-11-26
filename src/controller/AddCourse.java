package controller;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import model.Course;


@SuppressWarnings("serial")
public class AddCourse extends JFrame implements ActionListener {
    /*
     * 教师增加课程
     */


    JPanel contain;
    JButton submit;
    JLabel id, name, gredit, classH, teacherId, teacherName, time;
    JTextField idt, namet, greditt, classHt, teacherIdt, teacherNamet;


    JCheckBox[][] checkboxes;

    String[] daysOfWeek = {"星期一", "星期二", "星期三", "星期四", "星期五"};
    String[] timeSlots = {"上午", "下午"};
    public AddCourse() {
        super("增加课程");

        checkboxes = new JCheckBox[5][2];


        setSize(700, 550);
        setLocation(600, 300);
        contain = new JPanel();
        contain.setLayout(null);
        id = new JLabel("课程号");
        name = new JLabel("课程名");
        gredit = new JLabel("学分");
        classH = new JLabel("学时");

        teacherId = new JLabel("教师号");
        teacherName = new JLabel("教师名");
        time = new JLabel("请选择上课时间");
        time.setBounds(360, 35, 150, 35);
        time.setBorder(new LineBorder(Color.BLACK, 1));
        time.setBackground(Color.LIGHT_GRAY);
        time.setOpaque(true);
        time.setHorizontalAlignment(SwingConstants.CENTER);
        contain.add(time);

        submit = new JButton("提交");

        idt = new JTextField();
        namet = new JTextField();
        greditt = new JTextField();
        classHt = new JTextField();
        teacherIdt = new JTextField();
        teacherNamet = new JTextField();

        id.setBounds(40, 65, 75, 35);
        idt.setBounds(80, 65, 150, 35);

        name.setBounds(40, 120, 75, 35);
        namet.setBounds(80, 120, 150, 35);

        gredit.setBounds(45, 175, 75, 35);
        greditt.setBounds(80, 175, 150, 35);

        classH.setBounds(45, 230, 75, 35);
        classHt.setBounds(80, 230, 150, 35);

        teacherId.setBounds(40, 285, 75, 35);
        teacherIdt.setBounds(80, 285, 150, 35);

        teacherName.setBounds(40, 340, 75, 35);
        teacherNamet.setBounds(80, 340, 150, 35);//310

        submit.setFont(new Font("华文宋体", Font.PLAIN, 20));
        submit.setBounds(220, 420, 80, 50);



        for (int i = 0; i < 5; i++) {
            JPanel dayPanel = new JPanel();
            dayPanel.setBorder(BorderFactory.createTitledBorder(daysOfWeek[i]));

            for (int j = 0; j < 2; j++) {
                checkboxes[i][j] = new JCheckBox(timeSlots[j]);
                dayPanel.add(checkboxes[i][j]);
                dayPanel.setBounds(250, 70+i*60, 380, 60);
            }

            contain.add(dayPanel);
        }

        contain.add(id);
        contain.add(idt);
        contain.add(name);
        contain.add(namet);
        contain.add(gredit);
        contain.add(greditt);
        contain.add(classH);
        contain.add(classHt);
        contain.add(teacherId);
        contain.add(teacherIdt);
        contain.add(teacherName);
        contain.add(teacherNamet);
        //contain.add(timeChoose);
        contain.add(submit);
        submit.addActionListener(this);
        add(contain);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    public int hasCourse(String id) {  //  教师开课前检查课程是否已经存在

        String file = System.getProperty("user.dir") + "/data/course.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file)); //构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                String[] result = s.split(" ");
                if (result[0].equals(id)) {
                    return 1;

                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    public String getClassTime() {
        StringBuilder selectedSlots = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                if (checkboxes[i][j].isSelected()) {
                    selectedSlots.append(i+1).append(j);
                }
            }
        }
        return selectedSlots.toString();
    }

    //String chooseTime = null;
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {
            String time = getClassTime();
            if ((idt.getText().equals("")) || (namet.getText().equals("")) || (greditt.getText().equals(""))
                    || (classHt.getText().equals("")) || teacherIdt.getText().equals("") || teacherNamet.getText().equals("")  || time.equals("")   ) {
                JOptionPane.showMessageDialog(null, "信息不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if ((hasCourse(idt.getText())) == 1) {
                    JOptionPane.showMessageDialog(null, "此课程已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //System.out.println(time);
                    String file = System.getProperty("user.dir") + "/data/course.txt";

                    ArrayList<String> modifiedContent = new ArrayList<>();
                    // StringBuilder result = new StringBuilder();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
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

                    Course course = new Course(idt.getText(), namet.getText(), greditt.getText(), classHt.getText(), teacherIdt.getText(), teacherNamet.getText());
                    //System.out.println(course.getCourseId() + " " + course.getCourseName() + " " + course.getCredit() + " " + course.getHour() + " " + course.getTeacherId() + " " + course.getTeacherName());

                    modifiedContent.add(course.getCourseId() + " " + course.getCourseName() + " " + course.getCredit() + " " + course.getHour() + " "
                            + course.getTeacherId() + " " + course.getTeacherName() + " " + time);

                    try {
                        FileWriter fw = new FileWriter(file);
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

                    //  除了添加对应课程文件外，还需要添加课程成绩文件以及课程学生文件
                    try {
                        File gradeFile = new File(System.getProperty("user.dir") + "/data/grade/" + course.getCourseName() + ".txt");
                        gradeFile.createNewFile();
                        File studentFile = new File(System.getProperty("user.dir") + "/data/course_student/" + course.getCourseName() + "_student.txt");
                        studentFile.createNewFile();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }


                    JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    setVisible(false);
                }
            }
        }
    }

    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }
}
