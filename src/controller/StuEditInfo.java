package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.*;
/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/23 17:33
 */

public class StuEditInfo extends JFrame implements ActionListener {

    String id;
    JPanel contain;
    JButton submit;
    JLabel name, inst, birth, pass1, pass2, major, sex;
    JLabel namet, instt, birtht, majort, sext;
    String nameD, insD, birthD, majorD, sexD;
    JTextField pass1t, pass2t;
    String file = System.getProperty("user.dir")+"/data/student.txt";


    public StuEditInfo(String id){
        super("修改信息");
        setSize(300, 420);
        setLocation(600, 400);
        this.id = id;
        contain = new JPanel();
        contain.setLayout(null);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null) {//使用readLine方法，一次读一行
                String[] result = s.split(" ");
                if (result[0].equals(id)) {
    //                    id = result[0];
    //                    pwd = result[1];
    //                    name = result[2];
    //                    gender = result[3];
    //                    birthday = result[4];
    //                    institute = result[5];
    //                    major = result[6];
    //
                    nameD = result[2];
                    insD =result[5];
                    birthD = result[4];
                    majorD = result[6];
                    sexD = result[3];

                    namet = new JLabel(nameD);
                    instt = new JLabel(insD);
                    birtht = new JLabel(birthD);
                    majort = new JLabel(majorD);
                    sext = new JLabel(sexD);

                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        name = new JLabel("姓名");
        sex = new JLabel("性别");
        birth = new JLabel("生日");
        inst = new JLabel("学院");
        major = new JLabel("专业");
        pass1 = new JLabel("新密码");
        pass2 = new JLabel("确认密码");
        submit = new JButton("提交");

        pass1t = new JPasswordField();
        pass2t = new JPasswordField();

        name.setBounds(42, 20, 75, 35);
        namet.setBounds(80, 20, 150, 35);
        namet.setBorder(BorderFactory.createEtchedBorder());
        namet.setBackground(Color.LIGHT_GRAY);
        namet.setOpaque(true);


        sex.setBounds(42, 65, 75, 35);
        sext.setBounds(80, 65, 150, 35);
        sext.setBorder(BorderFactory.createEtchedBorder());
        sext.setBackground(Color.LIGHT_GRAY);
        sext.setOpaque(true);

        birth.setBounds(42, 110, 75, 35);
        birtht.setBounds(80, 110, 150, 35);
        birtht.setBorder(BorderFactory.createEtchedBorder());
        birtht.setBackground(Color.LIGHT_GRAY);
        birtht.setOpaque(true);

        inst.setBounds(40, 155, 75, 35);
        instt.setBounds(80, 155, 150, 35);
        instt.setBorder(BorderFactory.createEtchedBorder());
        instt.setBackground(Color.LIGHT_GRAY);
        instt.setOpaque(true);

        major.setBounds(40, 200, 75, 35);
        majort.setBounds(80, 200, 150, 35);
        majort.setBorder(BorderFactory.createEtchedBorder());
        majort.setBackground(Color.LIGHT_GRAY);
        majort.setOpaque(true);

        pass1.setBounds(36, 245, 75, 35);
        pass1t.setBounds(80, 245, 150, 35);
        pass2.setBounds(28, 290, 75, 35);
        pass2t.setBounds(80, 290, 150, 35);
        submit.setBounds(102, 335, 70, 30);

        contain.add(name);
        contain.add(namet);
        contain.add(birth);
        contain.add(birtht);
        contain.add(sex);
        contain.add(sext);
        contain.add(inst);
        contain.add(instt);
        contain.add(major);
        contain.add(majort);
        contain.add(pass1);
        contain.add(pass1t);
        contain.add(pass2);
        contain.add(pass2t);
        contain.add(submit);
        submit.addActionListener(this);
        add(contain);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if ((pass1t.getText().equals("")) || (pass2t.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "信息不能为空！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (!(pass1t.getText().equals(pass2t.getText()))) {
                    JOptionPane.showMessageDialog(null, "新密码与确认密码不同！", "提示",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if (pass1t.getText().length() < 6) {
                    JOptionPane.showMessageDialog(null, "密码长度至少为6位！", "提示",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ArrayList<String> modifiedContent = new ArrayList<String>();
                    String file = System.getProperty("user.dir")
                            + "/data/student.txt";
                    // String file = "D://test//student.txt";
                    // StringBuilder result = new StringBuilder();
                    try {
                        BufferedReader br = new BufferedReader(
                                new FileReader(file));

                        String s = null;
                        while ((s = br.readLine()) != null) {
                            String[] result = s.split(" ");
                            if (result[0].equals(id)) {
                                result[1] = result[1].replace(result[1],
                                        pass2t.getText());
                            }
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
                }
            }
        }
        JOptionPane.showMessageDialog(null, "修改成功！", "提示",
                JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        setVisible(false);
    }

    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }
}
