//package view;
//
//import java.awt.AWTEvent;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//import controller.AddUser;
//import controller.DeleteUser;
//import controller.EditInfo;
//
//
//@SuppressWarnings("serial")
//public class AdministratorPanel extends JFrame implements ActionListener {
//	/*
//	 * 管理员登陆后操作主界面
//	 */
//	JButton deleteUser, addUser, selfInfo, logOut;
//	JPanel contain;
//	String idd;
//
//	public AdministratorPanel(String idd) {
//		super("系统管理员");
//		this.idd = idd;
//		setLocation(300, 200);
//		setSize(300, 340);
//		contain = new JPanel();
//		contain.setLayout(null);
//		add(contain);
//		selfInfo = new JButton("修改信息");
//		addUser = new JButton("增加用户");
//		deleteUser = new JButton("删除用户");
//		logOut = new JButton("退出登录");
//		selfInfo.setBounds(70, 45, 140, 30);
//		addUser.setBounds(70, 100, 140, 30);
//		deleteUser.setBounds(70, 155, 140, 30);
//		logOut.setBounds(70, 210, 140, 30);
//		contain.add(selfInfo);
//		contain.add(addUser);
//		contain.add(deleteUser);
//		contain.add(logOut);
//		selfInfo.addActionListener(this);
//		addUser.addActionListener(this);
//		deleteUser.addActionListener(this);
//		logOut.addActionListener(this);
//		setVisible(true);
//		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == addUser) {
//			new AddUser();    // 用户密码初始化统一为123456
//		} else if (e.getSource() == deleteUser) {
//			new DeleteUser();
//		} else if (e.getSource() == selfInfo) {
//			new EditInfo(idd, 3);
//		} else if (e.getSource() == logOut) {
//			this.dispose();
//			new MainFrame();
//
//		}
//	}
//
//	public void processWindowEvent(WindowEvent e) {
//		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
//			this.dispose();
//			setVisible(false);
//			System.exit(0);
//		}
//	}
//}

package view;

		import java.awt.AWTEvent;
		import java.awt.event.ActionEvent;
		import java.awt.event.ActionListener;
		import java.awt.event.WindowEvent;
		import java.awt.GridLayout;

		import javax.swing.JButton;
		import javax.swing.JFrame;
		import javax.swing.JPanel;

		import controller.EditInfo;



@SuppressWarnings("serial")
public class AdministratorPanel extends JFrame implements ActionListener {
	/*
	 * 管理员登陆后操作主界面
	 */
	JButton manageUser, selfInfo, manageExam, manageCourse, logOut;
	JPanel contain;
	String idd;

	public AdministratorPanel(String idd) {
		super("系统管理员");
		this.idd = idd;
		setLocation(300, 200);
		setSize(300, 400);
		contain = new JPanel();
		contain.setLayout(new GridLayout(5,1,0,0));
		add(contain);
		selfInfo = new JButton("修改信息");
		manageUser = new JButton("管理用户");
		manageCourse = new JButton("管理课程");
		manageExam = new JButton("管理考试");
		logOut = new JButton("退出登录");
		selfInfo.setBounds(70, 45, 140, 30);
		manageUser.setBounds(70, 100, 140, 30);
		manageCourse.setBounds(70, 155, 140, 30);
		manageExam.setBounds(70, 130, 140, 30);
		logOut.setBounds(70, 160, 140, 30);
		contain.add(selfInfo);
		contain.add(manageUser);
		contain.add(manageCourse);
		contain.add(manageExam);
		contain.add(logOut);
		selfInfo.addActionListener(this);
		manageCourse.addActionListener(this);
		manageExam.addActionListener(this);
		manageUser.addActionListener(this);
		logOut.addActionListener(this);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == manageUser) {
			new ManageUserFrame(idd);    // 用户密码初始化统一为123456
		} else if (e.getSource() == manageCourse) {
			new ManageCourseFrame(idd);
		} else if (e.getSource() == manageExam) {
			new ManageExamFrame(idd);
		} else if (e.getSource() == selfInfo) {
			new EditInfo(idd, 3);
		} else if (e.getSource() == logOut) {
			this.dispose();
			new MainFrame();
		}
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
			System.exit(0);
		}
	}
}