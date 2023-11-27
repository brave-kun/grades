package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ExamView extends JFrame {
    /*
     * 学生查询考试，教师查询所教授课程的考试
     */

    JPanel contain;
    JTextArea list;

    public ExamView(String id, int flag) {
        super("考试");
        setSize(600, 400);
        contain = new JPanel();
        setLocation(600, 400);
        list = new JTextArea();
        list.setEditable(false);
        contain.add(list);
        list.append("课程编号\t课程名\t考试地点\t考试时间\t考试类型\n");

        String courseid = null;
        String coursename = null;
        String place = null;
        String day = null;
        String category = null;

        if(flag == 0){   // 学生查询课程

            // String path = "D://test//course_student";
            String path = System.getProperty("user.dir")+"/data/course_student";
            List<String> files = new ArrayList<String>(); // 目录下所有文件
            File file = new File(path);
            File[] tempList = file.listFiles();

            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    files.add(tempList[i].toString());
                    // 文件名，不包含路径
                    // String fileName = tempList[i].getName();
                }
                if (tempList[i].isDirectory()) {
                    // 这里就不递归了
                }
            }

            try {
                for (int i = 0; i < files.size(); i++) {
                    BufferedReader br = new BufferedReader(new FileReader(files.get(i)));
                    String s = null;
                    place = null;
                    while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                        String[] result = s.split(" ");
                        if (result[2].equals(id)) {      // 学生学号相等时
                            courseid = result[0];
                            coursename = result[1];


                            String path1 = System.getProperty("user.dir")+"/data/exam.txt";
                            BufferedReader br1 = new BufferedReader(
                                    new FileReader(path1));        // 构造一个BufferedReader类来读取文件

                            while ((s = br1.readLine()) != null) { // 使用readLine方法，一次读一行
                                String[] result1 = s.split(" ");
                                if (result[0].equals(result1[0])) {
                                    place = result1[2];
                                    day = result1[3];
                                    category = result1[4];
                                }
                            }
                            if(place != null) {
                                list.append(courseid + "\t");
                                list.append(coursename + "\t");
                                list.append(place + "\t");
                                list.append(day + "\t");
                                list.append(category + "\n");

                                br1.close();
                            }}

                    }

                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(flag == 1){      // 教师查询自己教授课程
            String path = System.getProperty("user.dir")+"/data/course.txt";
            try {
                String s = null;
                BufferedReader br = new BufferedReader(
                        new FileReader(path));        // 构造一个BufferedReader类来读取文件

                while ((s = br.readLine()) != null) { // 使用readLine方法，一次读一行
                    category = null;
                    String[] result = s.split(" ");
                    if (result[4].equals(id)) {      // 教师工号相等时
                        courseid = result[0];
                        coursename = result[1];

                        String path1 = System.getProperty("user.dir")+"/data/exam.txt";
                        BufferedReader br1 = new BufferedReader(
                                new FileReader(path1));        // 构造一个BufferedReader类来读取文件

                        while ((s = br1.readLine()) != null) { // 使用readLine方法，一次读一行
                            String[] result1 = s.split(" ");
                            if (result[0].equals(result1[0])) {
                                place = result1[2];
                                day = result1[3];
                                category = result1[4];
                            }
                        }
                        if(category != null) {
                            list.append(courseid + "\t");
                            list.append(coursename + "\t");
                            list.append(place + "\t");
                            list.append(day + "\t");
                            list.append(category + "\n");

                            br1.close();}
                    }

                }

                br.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(flag == 2){      // 管理员查询查询所有考试
            String path = System.getProperty("user.dir")+"/data/exam.txt";
            String s = null;

            try {
                BufferedReader br = new BufferedReader(new FileReader(path));
                while((s = br.readLine())!=null){   //使用readLine方法，一次读一行
                    String[] result2 = s.split(" ");
                    courseid = result2[0];
                    coursename = result2[1];
                    place = result2[2];
                    day = result2[3];
                    category = result2[4];

                    list.append(courseid + "\t");
                    list.append(coursename + "\t");
                    list.append(place + "\t");
                    list.append(day + "\t");
                    list.append(category + "\n");


                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }



        add(contain);
        setVisible(true);
    }
}
