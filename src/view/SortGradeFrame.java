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
		super("����γ̺źͳɼ���׼");
		setSize(400, 300);
		setLocation(600, 400);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		id = new JLabel("�γ̺�");
		idt = new JTextField();

		name = new JLabel("�γ���");
		namet = new JTextField();
		
		pass = new JLabel("����");
		passt = new JTextField();
		good = new JLabel("����");
		goodt = new JTextField();
		excellent = new JLabel("����");
		excellentt = new JTextField();
		
		submit = new JButton("�ύ");
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
				JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ�գ�", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
				
			}else if(new GradeSort(idt.getText(), Float.parseFloat(passt.getText()),
					Float.parseFloat(goodt.getText()), Float.parseFloat(excellentt.getText())).isValidate() == 1){
				
				JOptionPane.showMessageDialog(null, "�ɼ���׼���ô���", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
				
			}
			else {
				GradeSort gradeSort = new GradeSort(idt.getText(), Float.parseFloat(passt.getText()),
						Float.parseFloat(goodt.getText()), Float.parseFloat(excellentt.getText()));
				
				if (gradeSort.hasCourse()==0) {
					JOptionPane.showMessageDialog(null, "�˿γ̲����ڣ�", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE);
				}else{
				
				this.grade = gradeSort.sortGrade();
				showResult(idt.getText(), namet.getText(), Float.parseFloat(passt.getText()), Float.parseFloat(goodt.getText()), Float.parseFloat(excellentt.getText()));
				}
			}

		}
	}
	
	
	
	void showResult(String courseId, String courseName, float Pass, float Good, float Excellent){
		
		JFrame fm = new JFrame("�ɼ�ͳ�ƽ��");
		fm.setSize(900, 640);
		JPanel contain = new JPanel();
		fm.setLocation(600, 400);
		contain.setLayout(null);

		JTextArea list;
		list = new JTextArea();
		list.setEditable(false);
		contain.add(list);
		list.append("�γ̱��\t�γ���\tѧ��ѧ��\tѧ������\t����\t�ɼ�����\n");
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
			list.append(Grade[4] + "\t"); //ѧ��
			list.append(Grade[5] + "\t"); //����
			list.append(Grade[6] + "\t"); //�ɼ�
			if (Float.parseFloat(Grade[6]) < Pass) {
				list.append("������" + "\n"); //����

			} else if (Float.parseFloat(Grade[6]) < Good) {
				list.append("����" + "\n"); //����

			}else if(Float.parseFloat(Grade[6])< Excellent){
				list.append("����" + "\n"); //����

			}else{
				list.append("����" + "\n"); //����

			}


		}







		JLabel fail = new JLabel("������");
		JLabel pass = new JLabel("����");
		JLabel good = new JLabel("����");
		JLabel excellent = new JLabel("����");
		
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
		
		failt.setText(Integer.toString(this.grade[0])+"��");
		passt.setText(Integer.toString(this.grade[1])+"��");
		goodt.setText(Integer.toString(this.grade[2])+"��");
		excellentt.setText(Integer.toString(this.grade[3])+"��");
		
		
		fm.setVisible(true);
		
	}
	
	
	
	
}
