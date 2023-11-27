package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Exam {
    //考试基本信息：课程编号、课程名、考试地点、考试性质、考试日期
    private String courseId;
    private String courseName;
    private String place;
    //private String teacherName;
    private String category;
    private String day;

    public Exam(String courseId, String courseName, String place, String day, String category) {
        super();
        this.courseId = courseId;
        this.courseName = courseName;
        this.place = place;
        this.category = category;
        this.day = day;
    }

//	public Exam(String courseId, String courseName, String teacherId,
//			String teacherName, String credit, String hour) {
//
//		this.courseId = courseId;
//		this.courseName = courseName;
//		this.teacherId = teacherId;
//		this.teacherName = teacherName;
//		this.credit = credit;
//		this.hour = hour;
//	}
//
//	public Exam(String courseId, String courseName, String teacherId,
//			String teacherName, float fial, float pass, float good,
//			float excellent) {
//
//		this.courseId = courseId;
//		this.courseName = courseName;
//		this.teacherId = teacherId;
//		this.teacherName = teacherName;
//		this.fail = fial;
//		this.pass = pass;
//		this.good = good;
//		this.excellent = excellent;
//	}

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public int hasExam(){
        String file = System.getProperty("user.dir")+"/data/exam.txt";
        // String file = "D://test//course.txt";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                String[] result = s.split(" ");
                if(result[0].equals(this.courseId)){
                    return 1;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }



}
