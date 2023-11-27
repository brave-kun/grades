package view;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;

import controller.GradeSort;


@SuppressWarnings("serial")
public class SortGradeFrame extends JFrame implements ActionListener{
	
	JPanel contain;
	JLabel id, pass, good, excellent, name;
	JTextField idt, passt, goodt, excellentt, namet;
	
	JButton submit, bn;
	
	int[] grade = null;
	
	public SortGradeFrame(){
		super("输入课程号和成绩标准");
		setSize(400, 300);
		setLocation(600, 400);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		id = new JLabel("课程号");
		idt = new JTextField();

		name = new JLabel("课程名");
		namet = new JTextField();
		
		pass = new JLabel("及格");
		passt = new JTextField();
		good = new JLabel("良好");
		goodt = new JTextField();
		excellent = new JLabel("优秀");
		excellentt = new JTextField();
		
		submit = new JButton("提交");
		name.setBounds(38, 10, 75, 35);
		namet.setBounds(80, 10, 150, 35);
		id.setBounds(38, 50, 75, 35);
		idt.setBounds(80, 50, 150, 35);
		
		pass.setBounds(38, 90, 75, 35);
		passt.setBounds(80, 90, 150, 35);
		good.setBounds(38, 130, 75, 35);
		goodt.setBounds(80, 130, 150, 35);
		excellent.setBounds(38, 170, 75, 35);
		excellentt.setBounds(80, 170, 150, 35);
		
		submit.setBounds(102, 210, 70, 30);
		contain.add(id);
		contain.add(idt);

		contain.add(name);
		contain.add(namet);
		
		contain.add(pass);
		contain.add(passt);
		contain.add(good);
		contain.add(goodt);
		contain.add(excellent);
		contain.add(excellentt);
		
		contain.add(submit);
		submit.addActionListener(this);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		
		idt.setText("");
		namet.setText("");
		passt.setText("");
		goodt.setText("");
		excellentt.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submit) {
			if ((idt.getText().equals("")) || (passt.getText().equals(""))|| (goodt.getText().equals(""))|| (excellentt.getText().equals("")) || (namet.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "信息不能为空！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				
			}else if(new GradeSort(idt.getText(), Float.parseFloat(passt.getText()),
					Float.parseFloat(goodt.getText()), Float.parseFloat(excellentt.getText())).isValidate() == 1){
				
				JOptionPane.showMessageDialog(null, "成绩标准设置错误！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				
			}
			else {
				GradeSort gradeSort = new GradeSort(idt.getText(), Float.parseFloat(passt.getText()),
						Float.parseFloat(goodt.getText()), Float.parseFloat(excellentt.getText()));
				
				if (gradeSort.hasCourse()==0) {
					JOptionPane.showMessageDialog(null, "此课程不存在！", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				}else{
				
				this.grade = gradeSort.sortGrade();
				showResult(idt.getText(), namet.getText(), Float.parseFloat(passt.getText()), Float.parseFloat(goodt.getText()), Float.parseFloat(excellentt.getText()));
				}
			}

		}
	}
	
	
	
	void showResult(String courseId, String courseName, float Pass, float Good, float Excellent){
		
		JFrame fm = new JFrame("成绩统计结果");
		fm.setSize(900, 640);
		JPanel contain = new JPanel();
		fm.setLocation(600, 400);
		contain.setLayout(null);

		JTextArea list;
		list = new JTextArea();
		list.setEditable(false);
		contain.add(list);
		list.append("课程编号\t课程名\t学生学号\t学生姓名\t分数\t成绩级别\n");
		list.setBounds(245, 70, 500, 300);



		String file = System.getProperty("user.dir")+"/data/grade/" + courseName + ".txt";

		int lines = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while (reader.readLine() != null){
				lines++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader br = null;
		String[][] Grades = new String[lines][7];

		try {

			br = new BufferedReader(new FileReader(file));
			String s = null;
			int i = 0;
			while ((s = br.readLine()) != null) {
				String[] result = s.split(" ");
				Grades[i++] = result;

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Arrays.sort(Grades, Comparator.comparingInt((String[] Grade) ->  Integer.parseInt(Grade[6])).reversed());

		for (String[] Grade : Grades) {
			list.append(courseId + "\t");
			list.append(courseName + "\t");
			list.append(Grade[4] + "\t"); //学号
			list.append(Grade[5] + "\t"); //姓名
			list.append(Grade[6] + "\t"); //成绩
			if (Float.parseFloat(Grade[6]) < Pass) {
				list.append("不及格" + "\n"); //级别

			} else if (Float.parseFloat(Grade[6]) < Good) {
				list.append("及格" + "\n"); //级别

			}else if(Float.parseFloat(Grade[6])< Excellent){
				list.append("优良" + "\n"); //级别

			}else{
				list.append("优秀" + "\n"); //级别

			}


		}







		JLabel fail = new JLabel("不及格");
		JLabel pass = new JLabel("及格");
		JLabel good = new JLabel("良好");
		JLabel excellent = new JLabel("优秀");
		
		JTextField failt = new JTextField();
		JTextField passt = new JTextField();
		JTextField goodt = new JTextField();
		JTextField excellentt = new JTextField();
		
		fail.setBounds(38, 90, 75, 35);
		failt.setBounds(80, 90, 150, 35);
		pass.setBounds(38, 130, 75, 35);
		passt.setBounds(80, 130, 150, 35);
		good.setBounds(38, 170, 75, 35);
		goodt.setBounds(80, 170, 150, 35);
		excellent.setBounds(38, 210, 75, 35);
		excellentt.setBounds(80, 210, 150, 35);
		
		contain.add(fail);
		contain.add(failt);
		contain.add(pass);
		contain.add(passt);
		contain.add(good);
		contain.add(goodt);
		contain.add(excellent);
		contain.add(excellentt);
		fm.add(contain);
		
		failt.setText(Integer.toString(this.grade[0])+"人");
		passt.setText(Integer.toString(this.grade[1])+"人");
		goodt.setText(Integer.toString(this.grade[2])+"人");
		excellentt.setText(Integer.toString(this.grade[3])+"人");
		
		
		fm.setVisible(true);
		
	}
	
	
	
	
}
