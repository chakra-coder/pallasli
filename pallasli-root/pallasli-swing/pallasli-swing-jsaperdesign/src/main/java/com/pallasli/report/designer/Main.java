package com.pallasli.report.designer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.pallasli.report.designer.tooloper.AboutDesignerDialog;
import com.pallasli.report.designer.tooloper.NewFileDialog;
import com.pallasli.report.designer.tooloper.PageSettingDialog;
import com.pallasli.report.designer.tooloper.ReportPropsSettingDialog;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		instance = this;
		setType(Type.POPUP);
		setTitle("设计器");
		setBackground(Color.BLUE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);

		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		verticalBox.add(panel_2);

		JMenuBar menuBar = new JMenuBar();
		panel_2.add(menuBar);

		JMenu mnf = new JMenu("文件(F)");
		menuBar.add(mnf);

		JMenuItem menu2 = new JMenuItem("新建");
		menu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewFileDialog dialog = new NewFileDialog();
				dialog.setLocationRelativeTo(Main.getInstance());
				dialog.setVisible(true);
				// dialog.setLocation(300, 300);
			}
		});
		mnf.add(menu2);
		JMenuItem menu3 = new JMenuItem("打开");
		menu3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(Main.getInstance());
			}
		});
		mnf.add(menu3);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("另存为");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showSaveDialog(Main.getInstance());
			}
		});
		mnf.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem = new JMenuItem("保存");
		mnf.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("关闭");
		mnf.add(mntmNewMenuItem_2);

		JSeparator separator = new JSeparator();
		mnf.add(separator);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("页面设置");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PageSettingDialog dialog = new PageSettingDialog();
				dialog.setLocationRelativeTo(Main.getInstance());
				dialog.setVisible(true);
			}
		});
		mnf.add(mntmNewMenuItem_4);

		JSeparator separator_1 = new JSeparator();
		mnf.add(separator_1);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("报表属性");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReportPropsSettingDialog dialog = new ReportPropsSettingDialog();
				dialog.setLocationRelativeTo(Main.getInstance());
				dialog.setVisible(true);
			}
		});
		mnf.add(mntmNewMenuItem_5);

		JSeparator separator_2 = new JSeparator();
		mnf.add(separator_2);

		JMenu menu = new JMenu("切换工作空间");
		mnf.add(menu);

		JMenuItem menuItem_12 = new JMenuItem("新建");
		menu.add(menuItem_12);

		JSeparator separator_10 = new JSeparator();
		mnf.add(separator_10);

		JMenu mnNewMenu = new JMenu("最近历史");
		mnf.add(mnNewMenu);

		JSeparator separator_3 = new JSeparator();
		mnf.add(separator_3);

		JMenuItem menuItem_9 = new JMenuItem("退出");
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnf.add(menuItem_9);

		JSeparator separator_4 = new JSeparator();
		menuBar.add(separator_4);

		JMenu mne = new JMenu("编辑(E)");
		menuBar.add(mne);

		JMenuItem menuItem = new JMenuItem("撤销");
		mne.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("重做");
		mne.add(menuItem_1);

		JSeparator separator_8 = new JSeparator();
		mne.add(separator_8);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("复制");
		mne.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("剪切");
		mne.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("粘贴");
		mne.add(mntmNewMenuItem_8);

		JMenuItem menuItem_2 = new JMenuItem("删除");
		mne.add(menuItem_2);

		JSeparator separator_5 = new JSeparator();
		menuBar.add(separator_5);

		JMenu mni = new JMenu("插入(I)");
		menuBar.add(mni);

		JMenuItem menuItem_3 = new JMenuItem("标签");
		mni.add(menuItem_3);

		JMenuItem menuItem_4 = new JMenuItem("文本框");
		mni.add(menuItem_4);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("图形");
		mni.add(mntmNewMenuItem_9);

		JMenuItem mntmNewMenuItem_10 = new JMenuItem("二维码");
		mni.add(mntmNewMenuItem_10);

		JMenuItem mntmNewMenuItem_12 = new JMenuItem("条形码");
		mni.add(mntmNewMenuItem_12);

		JMenuItem mntmNewMenuItem_11 = new JMenuItem("统计图");
		mni.add(mntmNewMenuItem_11);

		JMenuItem menuItem_10 = new JMenuItem("面板");
		mni.add(menuItem_10);

		JMenuItem menuItem_5 = new JMenuItem("表格");
		mni.add(menuItem_5);

		JSeparator separator_6 = new JSeparator();
		menuBar.add(separator_6);

		JMenu mno = new JMenu("格式(O)");
		menuBar.add(mno);

		JMenuItem menuItem_6 = new JMenuItem("置前");
		mno.add(menuItem_6);

		JMenuItem menuItem_7 = new JMenuItem("置后");
		mno.add(menuItem_7);

		JMenu mnNewMenu_1 = new JMenu("对齐");
		mno.add(mnNewMenu_1);

		JMenuItem menuItem_8 = new JMenuItem("左对齐");
		mnNewMenu_1.add(menuItem_8);

		JMenuItem mntmNewMenuItem_14 = new JMenuItem("右对齐");
		mnNewMenu_1.add(mntmNewMenuItem_14);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("水平居中");
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_16 = new JMenuItem("垂直居中");
		mnNewMenu_1.add(mntmNewMenuItem_16);

		JMenuItem mntmNewMenuItem_17 = new JMenuItem("上对齐");
		mnNewMenu_1.add(mntmNewMenuItem_17);

		JMenuItem mntmNewMenuItem_15 = new JMenuItem("下对齐");
		mnNewMenu_1.add(mntmNewMenuItem_15);

		JMenuItem mntmNewMenuItem_13 = new JMenuItem("居中");
		mno.add(mntmNewMenuItem_13);

		JMenu mnNewMenu_2 = new JMenu("大小");
		mno.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_20 = new JMenuItem("等高");
		mnNewMenu_2.add(mntmNewMenuItem_20);

		JMenuItem mntmNewMenuItem_19 = new JMenuItem("等宽");
		mnNewMenu_2.add(mntmNewMenuItem_19);

		JMenuItem mntmNewMenuItem_18 = new JMenuItem("等高宽");
		mnNewMenu_2.add(mntmNewMenuItem_18);

		JSeparator separator_7 = new JSeparator();
		menuBar.add(separator_7);

		JMenu mnh = new JMenu("帮助(H)");
		menuBar.add(mnh);

		JMenuItem menuItem_11 = new JMenuItem("关于设计器");
		menuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AboutDesignerDialog dialog = new AboutDesignerDialog();
				dialog.setLocationRelativeTo(Main.getInstance());
				dialog.setVisible(true);
			}
		});
		mnh.add(menuItem_11);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setVgap(1);
		flowLayout_2.setHgap(1);
		verticalBox.add(panel_3);

		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fontFamilies = ge.getAvailableFontFamilyNames();
		String[] fontSizes = new String[] { "6", "8", "10", "12", "14", "16",
				"18", "20", "22", "24", "26", "28", "36", "48", "72" };

		JToolBar toolBar = new JToolBar();
		panel_3.add(toolBar);
		JComboBox comboBox_2 = new JComboBox();
		toolBar.add(comboBox_2);
		comboBox_2.setSize(new Dimension(100, 0));

		JToolBar toolBar_1 = new JToolBar();
		panel_3.add(toolBar_1);

		JComboBox fontFamilyCombo = new JComboBox(fontFamilies);
		toolBar_1.add(fontFamilyCombo);
		fontFamilyCombo.setPreferredSize(new Dimension(100, 21));

		JComboBox fontSizeCombo = new JComboBox(fontSizes);
		toolBar_1.add(fontSizeCombo);

		JButton btnNewButton_4 = new JButton("");
		toolBar_1.add(btnNewButton_4);
		btnNewButton_4.setPreferredSize(new Dimension(23, 23));
		btnNewButton_4.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/font/fbold.gif")));

		JButton button_4 = new JButton("");
		toolBar_1.add(button_4);
		button_4.setPreferredSize(new Dimension(23, 23));
		button_4.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/font/fitalic.gif")));

		JToolBar toolBar_2 = new JToolBar();
		panel_3.add(toolBar_2);

		JButton btnNewButton = new JButton("");
		toolBar_2.add(btnNewButton);
		btnNewButton.setPreferredSize(new Dimension(23, 23));
		btnNewButton.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/arrow.gif")));

		JButton btnNewButton_3 = new JButton("");
		toolBar_2.add(btnNewButton_3);
		btnNewButton_3.setPreferredSize(new Dimension(23, 23));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/table.gif")));

		JButton btnNewButton_1 = new JButton("");
		toolBar_2.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/chart.gif")));
		btnNewButton_1.setPreferredSize(new Dimension(23, 23));

		JButton btnNewButton_2 = new JButton("");
		toolBar_2.add(btnNewButton_2);
		btnNewButton_2.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/table.gif")));
		btnNewButton_2.setPreferredSize(new Dimension(23, 23));

		JButton button_3 = new JButton("");
		toolBar_2.add(button_3);
		button_3.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/barcode.gif")));
		button_3.setPreferredSize(new Dimension(23, 23));

		JButton button_2 = new JButton("");
		toolBar_2.add(button_2);
		button_2.setPreferredSize(new Dimension(23, 23));

		JButton button_1 = new JButton("");
		toolBar_2.add(button_1);
		button_1.setPreferredSize(new Dimension(23, 23));

		JButton button = new JButton("");
		toolBar_2.add(button);
		button.setPreferredSize(new Dimension(23, 23));

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		verticalBox.add(panel_4);

		JToolBar toolBar_3 = new JToolBar();
		panel_4.add(toolBar_3);

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/new.gif")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolBar_3.add(btnNewButton_5);

		JButton button_5 = new JButton("");
		toolBar_3.add(button_5);
		button_5.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/open.gif")));

		JButton button_6 = new JButton("");
		button_6.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/save.gif")));
		toolBar_3.add(button_6);

		JSeparator separator_9 = new JSeparator();
		separator_9.setForeground(Color.LIGHT_GRAY);
		separator_9.setBorder(null);
		separator_9.setToolTipText("");
		toolBar_3.add(separator_9);

		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.setBorder(null);
		btnNewButton_6.setPreferredSize(new Dimension(10, 9));
		btnNewButton_6.setEnabled(false);
		toolBar_3.add(btnNewButton_6);

		JButton button_7 = new JButton("");
		button_7.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/undo.gif")));
		toolBar_3.add(button_7);

		JButton button_8 = new JButton("");
		button_8.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/redo.gif")));
		toolBar_3.add(button_8);

		JButton button_15 = new JButton("");
		button_15.setBorder(null);
		button_15.setPreferredSize(new Dimension(10, 9));
		button_15.setEnabled(false);
		toolBar_3.add(button_15);

		JButton button_9 = new JButton("");
		button_9.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/copy.gif")));
		toolBar_3.add(button_9);

		JButton button_10 = new JButton("");
		button_10.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/cut.gif")));
		toolBar_3.add(button_10);

		JButton button_11 = new JButton("");
		button_11.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/paste.gif")));
		toolBar_3.add(button_11);

		JButton button_12 = new JButton("");
		button_12.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/delete.gif")));
		toolBar_3.add(button_12);

		JButton button_16 = new JButton("");
		button_16.setBorder(null);
		button_16.setPreferredSize(new Dimension(10, 9));
		button_16.setEnabled(false);
		toolBar_3.add(button_16);

		JComboBox comboBox_3 = new JComboBox();
		toolBar_3.add(comboBox_3);

		JComboBox comboBox = new JComboBox();
		toolBar_3.add(comboBox);

		JButton button_13 = new JButton("");
		button_13.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/align/center.gif")));
		toolBar_3.add(button_13);

		JComboBox comboBox_1 = new JComboBox();
		toolBar_3.add(comboBox_1);

		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.setBorder(null);
		btnNewButton_7.setPreferredSize(new Dimension(200, 9));
		btnNewButton_7.setEnabled(false);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar_3.add(btnNewButton_7);

		JButton button_14 = new JButton("");
		button_14.setIcon(new ImageIcon(Main.class
				.getResource("/designer/icons/tool/preview.gif")));
		toolBar_3.add(button_14);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(210, 10));
		contentPane.add(panel_1, BorderLayout.WEST);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setPreferredSize(new Dimension(205, 500));
		panel_1.add(tabbedPane_1);

		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("设计", null, panel_5, null);

		Box verticalBox_2 = Box.createVerticalBox();
		panel_5.add(verticalBox_2);

		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		verticalBox_2.add(tabbedPane_3);

		JPanel panel_10 = new JPanel();
		tabbedPane_3.addTab("New tab", null, panel_10, null);
		panel_10.setPreferredSize(new Dimension(10, 200));

		JTree tree_1 = new JTree();
		tree_1.setPreferredSize(new Dimension(200, 190));
		panel_10.add(tree_1);

		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		verticalBox_2.add(tabbedPane_2);
		tabbedPane_2.setPreferredSize(new Dimension(205, 250));

		JPanel panel_7 = new JPanel();
		tabbedPane_2.addTab("属性", null, panel_7, null);

		JLabel lblName = new JLabel("name:");
		panel_7.add(lblName);

		JPanel panel_8 = new JPanel();
		tabbedPane_2.addTab("事件", null, panel_8, null);

		// Object[][] playerInfo = {
		// { "阿呆", new Integer(66), new Integer(32), new Integer(98),
		// new Boolean(false) },
		// { "阿呆", new Integer(82), new Integer(69), new Integer(128),
		// new Boolean(true) } };
		// String[] Names = { "姓名", "语文", "数学", "总分", "及格" };
		// table = new JTable(playerInfo, Names);
		// JScrollPane scrollPane = new JScrollPane(table);
		// JFrame f = new JFrame();
		// f.getContentPane().add(scrollPane, BorderLayout.CENTER);
		// f.setTitle("Simple Table");
		// f.pack();
		// f.setVisible(true);

		table_1 = new JTable();
		table_1.setBounds(0, 0, 200, 221);
		table_1.setModel(new DefaultTableModel(new Object[][] {
				{ "\u4E8B\u4EF6\u540D", "\u5C5E\u6027\u503C" },
				{ "\u4E8B\u4EF6\u540D", "\u5C5E\u6027\u503C" }, },
				new String[] { "\u4E8B\u4EF6\u540D", "\u5C5E\u6027\u503C" }) {
			Class[] columnTypes = new Class[] { String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.setVisible(true);
		panel_8.setLayout(null);
		// table.setPreferredScrollableViewportSize(new Dimension(550, 30));
		panel_8.add(table_1);

		JPanel panel_9 = new JPanel();
		tabbedPane_2.addTab("变量", null, panel_9, null);

		JPanel panel_6 = new JPanel();
		tabbedPane_1.addTab("报表列表", null, panel_6, null);

		Box verticalBox_1 = Box.createVerticalBox();
		panel_6.add(verticalBox_1);

		JPanel panel_12 = new JPanel();
		panel_12.setPreferredSize(new Dimension(200, 200));
		verticalBox_1.add(panel_12);

		JList list = new JList();
		list.setPreferredSize(new Dimension(200, 190));
		panel_12.add(list);

		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(200, 270));
		verticalBox_1.add(panel_11);

		JTree tree = new JTree();
		tree.setPreferredSize(new Dimension(200, 270));
		panel_11.add(tree);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setPreferredSize(new Dimension(10, 5));
		contentPane.add(tabbedPane, BorderLayout.CENTER);

	}

	private static Main instance;
	private JTabbedPane tabbedPane;

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	private JTable table_1;

	public static Main getInstance() {
		return instance;
	}

}
