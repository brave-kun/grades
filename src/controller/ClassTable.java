package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/24 17:36
 */

public class ClassTable  extends JFrame implements ActionListener {

    String stuId;
    public ClassTable(String id) {
        super("课表");
        this.stuId = id;
        // 创建表格的数据
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建表格的列名和行数据
        String[] columns = {"时间", "星期一", "星期二", "星期三", "星期四", "星期五"};
        //String[] rows = {"上午", "下午"};

        // 创建 DefaultTableModel 对象
        DefaultTableModel model = new DefaultTableModel(0, columns.length) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 设置列名
        model.setColumnIdentifiers(columns);


        // 创建 JTable 并设置数据模型
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false); // 禁止列头拖动

        // 将表格放入滚动面板
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 设置窗口大小并显示
        setSize(600, 230);
        setLocation(600, 400);
        setVisible(true);

        String[][] data = decode(stuId);


        for (String[] rowData : data) {
            model.addRow(rowData);
        }



        // 创建一个单元格渲染器，用于设置单元格的大小、文字居中和文字大小
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value == null || value.toString().isEmpty()) {
                    c.setBackground(Color.LIGHT_GRAY); // 设置背景颜色为灰色
                } else {
                    c.setBackground(Color.WHITE); // 设置背景颜色为白色
                }
                if (c instanceof JLabel) {
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER); // 设置文字居中
                    ((JLabel) c).setFont(((JLabel) c).getFont().deriveFont(30.0f)); // 设置文字大小
                }
                return c;
            }
        };

// 将渲染器应用于表格的所有列
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

// 设置表格的行高
        table.setRowHeight(100); // 设置单元格大小

// 创建一个单元格编辑器，用于设置单元格的大小和文字
        DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                Component editorComponent = super.getTableCellEditorComponent(table, value, isSelected, row, column);
                editorComponent.setPreferredSize(new Dimension(100, 100)); // 设置单元格大小
                if (editorComponent instanceof JTextField) {
                    ((JTextField) editorComponent).setHorizontalAlignment(JTextField.CENTER); // 设置文字居中
                    ((JTextField) editorComponent).setFont(((JTextField) editorComponent).getFont().deriveFont(30.0f)); // 设置文字大小
                }
                return editorComponent;
            }
        };

// 将编辑器应用于表格的所有列
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellEditor(editor);
        }





//        // 创建一个单元格渲染器，用于设置单元格的大小、文字居中和文字大小
//        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                if (c instanceof JLabel) {
//                    ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER); // 设置文字居中
//                    ((JLabel) c).setFont(((JLabel) c).getFont().deriveFont(16.0f)); // 设置文字大小
//                }
//                return c;
//            }
//        };
//
//// 将渲染器应用于表格的所有列
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
//        }
//
//// 设置表格的行高
//        table.setRowHeight(50); // 设置单元格大小
//
//// 创建一个单元格编辑器，用于设置单元格的大小和文字
//        DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
//            @Override
//            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//                Component editorComponent = super.getTableCellEditorComponent(table, value, isSelected, row, column);
//                editorComponent.setPreferredSize(new Dimension(100, 50)); // 设置单元格大小
//                if (editorComponent instanceof JTextField) {
//                    ((JTextField) editorComponent).setHorizontalAlignment(JTextField.CENTER); // 设置文字居中
//                    ((JTextField) editorComponent).setFont(((JTextField) editorComponent).getFont().deriveFont(16.0f)); // 设置文字大小
//                }
//                return editorComponent;
//            }
//        };
//
//// 将编辑器应用于表格的所有列
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellEditor(editor);
//        }
//


//        // 创建一个单元格渲染器，用于设置单元格的大小和文字
//        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//        renderer.setHorizontalAlignment(SwingConstants.CENTER); // 设置文字居中
//
//// 将渲染器应用于表格的所有列
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
//        }
//
//// 设置表格的行高
//        table.setRowHeight(50); // 设置单元格大小
//
//// 创建一个单元格编辑器，用于设置单元格的大小和文字
//        DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
//            @Override
//            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//                Component editorComponent = super.getTableCellEditorComponent(table, value, isSelected, row, column);
//                editorComponent.setPreferredSize(new Dimension(100, 50)); // 设置单元格大小
//                if (editorComponent instanceof JTextField) {
//                    ((JTextField) editorComponent).setHorizontalAlignment(JTextField.CENTER); // 设置文字居中
//                }
//                return editorComponent;
//            }
//        };
//
//// 将编辑器应用于表格的所有列
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellEditor(editor);
//        }





        // 创建一个单元格渲染器，用于设置单元格的大小和文字
//        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//        renderer.setPreferredSize(new Dimension(500, 100)); // 设置单元格大小
//        renderer.setHorizontalAlignment(SwingConstants.CENTER); // 设置文字居中
//
//// 将渲染器应用于表格的所有列
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
//        }
//
//// 创建一个单元格编辑器，用于设置单元格的大小和文字
//        DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
//            @Override
//            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//                Component editorComponent = super.getTableCellEditorComponent(table, value, isSelected, row, column);
//                editorComponent.setPreferredSize(new Dimension(500, 100)); // 设置单元格大小
//                if (editorComponent instanceof JTextField) {
//                    ((JTextField) editorComponent).setHorizontalAlignment(JTextField.CENTER); // 设置文字居中
//                }
//                return editorComponent;
//            }
//        };
//
//// 将编辑器应用于表格的所有列
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellEditor(editor);
//        }









    }

    public String[][] decode(String id){
        String [][] classTableData = new String[6][2];
        classTableData[0][0] = "上午";
        classTableData[0][1] = "下午";

        String path = System.getProperty("user.dir")+"/data/course_student";

        List<String> files = new ArrayList<>(); // 目录下所有文件
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
                // 文件名，不包含路径
                // String fileName = tempList[i].getName();
            }
            if (tempList[i].isDirectory()) {
                // 这里就不递归了，
            }
        }

        try {
            for (int i = 0; i < files.size(); i++) {
                BufferedReader br = new BufferedReader(new FileReader(
                        files.get(i)));
                String s = null;
                while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                    String[] result = s.split(" ");
                    if (result[2].equals(id)) { // 学生学号相等时
                        String timeNumber = result[5];
                        int length = timeNumber.length();

                        for (int j = 0; j < length; j += 2) {
                            String twoDigits = timeNumber.substring(j, j + 2);
                            int value = Integer.parseInt(twoDigits);
                            int row = value / 10; // 十位数作为行号
                            int col = value % 10; // 个位数作为列号
                            classTableData[row][col] = result[1]; // 将对应位置的值设置为
                        }
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[][] ClassTableData = new String[2][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                ClassTableData[j][i] = classTableData[i][j];
            }
        }
        return ClassTableData;

    }



    public void actionPerformed(ActionEvent e) {

    }

    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }

}
