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
 * ���ܣ�
 * ���ߣ� bravekun
 * ���ڣ� 2023/11/24 17:36
 */

public class ClassTable  extends JFrame implements ActionListener {

    String stuId;
    public ClassTable(String id) {
        super("�α�");
        this.stuId = id;
        // ������������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ��������������������
        String[] columns = {"ʱ��", "����һ", "���ڶ�", "������", "������", "������"};
        //String[] rows = {"����", "����"};

        // ���� DefaultTableModel ����
        DefaultTableModel model = new DefaultTableModel(0, columns.length) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // ��������
        model.setColumnIdentifiers(columns);


        // ���� JTable ����������ģ��
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false); // ��ֹ��ͷ�϶�

        // ��������������
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // ���ô��ڴ�С����ʾ
        setSize(600, 230);
        setLocation(600, 400);
        setVisible(true);

        String[][] data = decode(stuId);


        for (String[] rowData : data) {
            model.addRow(rowData);
        }



        // ����һ����Ԫ����Ⱦ�����������õ�Ԫ��Ĵ�С�����־��к����ִ�С
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value == null || value.toString().isEmpty()) {
                    c.setBackground(Color.LIGHT_GRAY); // ���ñ�����ɫΪ��ɫ
                } else {
                    c.setBackground(Color.WHITE); // ���ñ�����ɫΪ��ɫ
                }
                if (c instanceof JLabel) {
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER); // �������־���
                    ((JLabel) c).setFont(((JLabel) c).getFont().deriveFont(30.0f)); // �������ִ�С
                }
                return c;
            }
        };

// ����Ⱦ��Ӧ���ڱ���������
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

// ���ñ����и�
        table.setRowHeight(100); // ���õ�Ԫ���С

// ����һ����Ԫ��༭�����������õ�Ԫ��Ĵ�С������
        DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                Component editorComponent = super.getTableCellEditorComponent(table, value, isSelected, row, column);
                editorComponent.setPreferredSize(new Dimension(100, 100)); // ���õ�Ԫ���С
                if (editorComponent instanceof JTextField) {
                    ((JTextField) editorComponent).setHorizontalAlignment(JTextField.CENTER); // �������־���
                    ((JTextField) editorComponent).setFont(((JTextField) editorComponent).getFont().deriveFont(30.0f)); // �������ִ�С
                }
                return editorComponent;
            }
        };

// ���༭��Ӧ���ڱ���������
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellEditor(editor);
        }





//        // ����һ����Ԫ����Ⱦ�����������õ�Ԫ��Ĵ�С�����־��к����ִ�С
//        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                if (c instanceof JLabel) {
//                    ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER); // �������־���
//                    ((JLabel) c).setFont(((JLabel) c).getFont().deriveFont(16.0f)); // �������ִ�С
//                }
//                return c;
//            }
//        };
//
//// ����Ⱦ��Ӧ���ڱ���������
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
//        }
//
//// ���ñ����и�
//        table.setRowHeight(50); // ���õ�Ԫ���С
//
//// ����һ����Ԫ��༭�����������õ�Ԫ��Ĵ�С������
//        DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
//            @Override
//            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//                Component editorComponent = super.getTableCellEditorComponent(table, value, isSelected, row, column);
//                editorComponent.setPreferredSize(new Dimension(100, 50)); // ���õ�Ԫ���С
//                if (editorComponent instanceof JTextField) {
//                    ((JTextField) editorComponent).setHorizontalAlignment(JTextField.CENTER); // �������־���
//                    ((JTextField) editorComponent).setFont(((JTextField) editorComponent).getFont().deriveFont(16.0f)); // �������ִ�С
//                }
//                return editorComponent;
//            }
//        };
//
//// ���༭��Ӧ���ڱ���������
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellEditor(editor);
//        }
//


//        // ����һ����Ԫ����Ⱦ�����������õ�Ԫ��Ĵ�С������
//        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//        renderer.setHorizontalAlignment(SwingConstants.CENTER); // �������־���
//
//// ����Ⱦ��Ӧ���ڱ���������
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
//        }
//
//// ���ñ����и�
//        table.setRowHeight(50); // ���õ�Ԫ���С
//
//// ����һ����Ԫ��༭�����������õ�Ԫ��Ĵ�С������
//        DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
//            @Override
//            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//                Component editorComponent = super.getTableCellEditorComponent(table, value, isSelected, row, column);
//                editorComponent.setPreferredSize(new Dimension(100, 50)); // ���õ�Ԫ���С
//                if (editorComponent instanceof JTextField) {
//                    ((JTextField) editorComponent).setHorizontalAlignment(JTextField.CENTER); // �������־���
//                }
//                return editorComponent;
//            }
//        };
//
//// ���༭��Ӧ���ڱ���������
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellEditor(editor);
//        }





        // ����һ����Ԫ����Ⱦ�����������õ�Ԫ��Ĵ�С������
//        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//        renderer.setPreferredSize(new Dimension(500, 100)); // ���õ�Ԫ���С
//        renderer.setHorizontalAlignment(SwingConstants.CENTER); // �������־���
//
//// ����Ⱦ��Ӧ���ڱ���������
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
//        }
//
//// ����һ����Ԫ��༭�����������õ�Ԫ��Ĵ�С������
//        DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
//            @Override
//            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//                Component editorComponent = super.getTableCellEditorComponent(table, value, isSelected, row, column);
//                editorComponent.setPreferredSize(new Dimension(500, 100)); // ���õ�Ԫ���С
//                if (editorComponent instanceof JTextField) {
//                    ((JTextField) editorComponent).setHorizontalAlignment(JTextField.CENTER); // �������־���
//                }
//                return editorComponent;
//            }
//        };
//
//// ���༭��Ӧ���ڱ���������
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellEditor(editor);
//        }









    }

    public String[][] decode(String id){
        String [][] classTableData = new String[6][2];
        classTableData[0][0] = "����";
        classTableData[0][1] = "����";

        String path = System.getProperty("user.dir")+"/data/course_student";

        List<String> files = new ArrayList<>(); // Ŀ¼�������ļ�
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
                // �ļ�����������·��
                // String fileName = tempList[i].getName();
            }
            if (tempList[i].isDirectory()) {
                // ����Ͳ��ݹ��ˣ�
            }
        }

        try {
            for (int i = 0; i < files.size(); i++) {
                BufferedReader br = new BufferedReader(new FileReader(
                        files.get(i)));
                String s = null;
                while ((s = br.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
                    String[] result = s.split(" ");
                    if (result[2].equals(id)) { // ѧ��ѧ�����ʱ
                        String timeNumber = result[5];
                        int length = timeNumber.length();

                        for (int j = 0; j < length; j += 2) {
                            String twoDigits = timeNumber.substring(j, j + 2);
                            int value = Integer.parseInt(twoDigits);
                            int row = value / 10; // ʮλ����Ϊ�к�
                            int col = value % 10; // ��λ����Ϊ�к�
                            classTableData[row][col] = result[1]; // ����Ӧλ�õ�ֵ����Ϊ
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
