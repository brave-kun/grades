package controller;


import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Exam;


@SuppressWarnings("serial")
public class AddExam extends JFrame implements ActionListener {
    /*
     * 管理员增加课程
     */


    JPanel contain;
    JButton submit;
    JLabel id, name, place, day, category;
    JTextField idt, namet, placet, dayt, categoryt;

    public AddExam() {
        super("增加考试");
        setSize(400, 400);
        setLocation(600, 400);
        contain = new JPanel();
        contain.setLayout(null);
        id = new JLabel("课程号");
        name = new JLabel("课程名");
        place = new JLabel("考试地点");
        day = new JLabel("考试时间");
        category = new JLabel("考试类型");

        submit = new JButton("提交");
        idt = new JTextField();
        namet = new JTextField();
        placet = new JTextField();
        dayt = new JTextField();
        categoryt = new JTextField();

        id.setBounds(42, 35, 75, 35);
        idt.setBounds(120, 35, 200, 35);
        name.setBounds(40, 90, 75, 35);
        namet.setBounds(120, 90, 200, 35);
        place.setBounds(45, 145, 75, 35);
        placet.setBounds(120, 145, 200, 35);
        day.setBounds(45, 200, 75, 35);
        dayt.setBounds(120, 200, 200, 35);

        category.setBounds(45, 245, 75, 35);
        categoryt.setBounds(120, 245, 200, 35);

        submit.setBounds(102, 320, 70, 30);
        contain.add(id);
        contain.add(idt);
        contain.add(name);
        contain.add(namet);
        contain.add(place);
        contain.add(placet);
        contain.add(day);
        contain.add(dayt);
        contain.add(category);
        contain.add(categoryt);
        contain.add(submit);
        submit.addActionListener(this);
        add(contain);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    public int hasExam(String id){  // 管理员增设考试前检查考试是否已经存在

        String file = System.getProperty("user.dir")+"/data/exam.txt";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); //构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                String[] result = s.split(" ");
                if(result[0].equals(id)){
                    return 1;

                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if ((idt.getText().equals("")) || (namet.getText().equals("")) || (placet.getText().equals(""))
                    || (dayt.getText().equals("")) || categoryt.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "信息不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if ((hasExam(idt.getText())) == 1) {
                    JOptionPane.showMessageDialog(null, "此课程考试已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    String file = System.getProperty("user.dir")+"/data/exam.txt";

                    ArrayList<String> modifiedContent = new ArrayList<String>();
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

                    Exam exam = new Exam(idt.getText(), namet.getText(), placet.getText(), dayt.getText(), categoryt.getText());
                    //检查课程和考试是否一致、是否存在
                    String path = System.getProperty("user.dir")+"/data/course.txt";
                    String s = null;
                    int flag = 0;
                    int flag1 = 0;
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(path));
                        while((s = br.readLine())!=null){   //使用readLine方法，一次读一行
                            String[] result = s.split(" ");
                            System.out.println(result[0]);
                            System.out.println(idt.getText());
                            System.out.println(idt.getText() == result[0]);
                            if(result[0].equals(idt.getText())) {
                                if(result[1].equals(namet.getText())) {
                                    flag = 1;
                                    modifiedContent.add(exam.getCourseId()+" "+exam.getCourseName()+" "+exam.getPlace()+" "+exam.getDay()+" "
                                            +exam.getCategory());

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

                                    JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);

                                }else {
                                    JOptionPane.showMessageDialog(null, "课程号和课程不匹配", "提示", JOptionPane.INFORMATION_MESSAGE);
                                    flag1 = 1;
                                    break;
                                }
                            }
                        }
                        if(flag == 0 && flag1 == 0) {JOptionPane.showMessageDialog(null, "课程号不存在，无法开设考试", "提示", JOptionPane.INFORMATION_MESSAGE);}
                        br.close();
                    } catch (IOException e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }


                }
            }
            this.dispose();
        }


    }

    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }
}
