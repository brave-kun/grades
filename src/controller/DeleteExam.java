package controller;

import java.awt.AWTEvent;
import java.awt.Choice;
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
import javax.swing.JTextField;

public class DeleteExam extends JFrame implements ActionListener {
    /**
     * 管理员删除考试
     */
    private static final long serialVersionUID = 1L;
    JPanel contain;
    JLabel id;
    JTextField idt;
    Choice chooice;
    JButton submit;

    public DeleteExam() {
        super("删除考试");
        setSize(300, 340);
        setLocation(600, 400);
        contain = new JPanel();
        contain.setLayout(null);
        id = new JLabel("考试课程号");
        submit = new JButton("提交");
        idt = new JTextField();
        id.setBounds(42, 45, 75, 35);
        idt.setBounds(80, 45, 150, 35);
        submit.setBounds(102, 150, 70, 30);
        contain.add(id);
        contain.add(idt);
        contain.add(submit);
        submit.addActionListener(this);
        add(contain);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if ((new CheckInfo().isExam("exam", idt.getText()) == 2)) {

                String file = System.getProperty("user.dir")+"/data/"+"exam.txt";

                ArrayList<String> modifiedContent = new ArrayList<String>();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String s = null;
                    while ((s = br.readLine()) != null) {  // 先将原来存在的信息存储起来
                        String[] result = s.split(" ");

                        if(result[0].equals(idt.getText())){
                            continue;
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


                JOptionPane.showMessageDialog(null, "删除考试成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "该考试不存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
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
