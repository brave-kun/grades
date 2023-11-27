package controller;

import java.awt.AWTEvent;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EditExam extends JFrame implements ActionListener {
    /*
     * 管理员修改考试信息
     */
    JPanel contain;
    JButton submit;
    JLabel place, day, category, id;
    JTextField placet, dayt, categoryt, idt;

    public EditExam() {
        super("修改考试");
        setSize(300, 420);
        setLocation(600, 400);
        contain = new JPanel();
        contain.setLayout(null);
        id = new JLabel("考试课程号");
        place = new JLabel("考试地点");
        day = new JLabel("考试日期");
        category = new JLabel("考试类型");
        submit = new JButton("提交");
        idt = new JTextField();
        placet = new JTextField();
        dayt = new JTextField();
        categoryt = new JTextField();
        id.setBounds(40, 20, 75, 35);
        place.setBounds(40, 90, 75, 35);
        day.setBounds(40, 160, 75, 35);
        category.setBounds(40, 250, 75, 35);
        submit.setBounds(102, 325, 70, 30);
        idt.setBounds(130, 20, 150, 35);
        placet.setBounds(130, 90, 150, 35);
        dayt.setBounds(130, 160, 150, 35);
        categoryt.setBounds(130, 250, 150, 35);
        contain.add(id);
        contain.add(place);
        contain.add(day);
        contain.add(category);
        contain.add(submit);
        contain.add(idt);
        contain.add(placet);
        contain.add(dayt);
        contain.add(categoryt);

        submit.addActionListener(this);
        add(contain);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if ((idt.getText().equals("")) || (placet.getText().equals("")) || (dayt.getText().equals(""))
                    || (categoryt.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "信息不能为空！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if((new CheckInfo().isExam("exam", idt.getText()) != 2)){
                JOptionPane.showMessageDialog(null, "该考试不存在！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {

                ArrayList<String> modifiedContent = new ArrayList<String>();
                String file = System.getProperty("user.dir")
                        + "/data/exam.txt";

                try {
                    BufferedReader br = new BufferedReader(
                            new FileReader(file));

                    String s = null;
                    while ((s = br.readLine()) != null) {
                        String[] result = s.split(" ");
                        if (result[0].equals(idt.getText())) {
                            result[0] = result[0]; // 号码不能更改
                            result[1] = result[1];
                            result[2] = result[2].replace(result[2],
                                    placet.getText());
                            result[3] = result[3].replace(result[3],
                                    dayt.getText());
                            result[4] = result[4].replace(result[4],
                                    categoryt.getText());

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


                JOptionPane.showMessageDialog(null, "修改成功！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);

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
