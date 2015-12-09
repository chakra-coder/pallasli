/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pallasli.ftp.panel;

import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;

import com.pallasli.ftp.extClass.FileInterface;
import com.pallasli.ftp.extClass.FtpFile;

@SuppressWarnings("serial")
public class FTPTableCellRanderer extends DefaultTableCellRenderer {
	private final ImageIcon folderIcon = new ImageIcon(getClass().getResource(
			"/com/lzw/ftp/res/folderIcon.JPG")); // 文件夹图标
	private final ImageIcon fileIcon = new ImageIcon(getClass().getResource(
			"/com/lzw/ftp/res/fileIcon.JPG")); // 文件图标
	private static FTPTableCellRanderer instance = null; // 渲染器的实例对象

	/**
	 * 被封闭的构造方法
	 */
	private FTPTableCellRanderer() {
	}

	/**
	 * 获取渲染器实例对象的方法
	 * 
	 * @return 渲染器的实例对象
	 */
	public static FTPTableCellRanderer getCellRanderer() {
		if (instance == null)
			instance = new FTPTableCellRanderer();
		return instance;
	}

	/**
	 * 重写设置表格数据的方法
	 * 
	 * @see javax.swing.table.DefaultTableCellRenderer#setValue(java.lang.Object)
	 */
	@Override
	protected void setValue(Object value) {
		if (value instanceof FileInterface) {
			FileInterface file = (FileInterface) value;
			// 获取FileSystemView类的实例对象
			FileSystemView view = FileSystemView.getFileSystemView();
			if (file.isDirectory()) {
				setText(file.toString());
				setIcon(folderIcon);
			} else {
				if (file instanceof File) { // 如果数据为File类
					Icon icon = view.getSystemIcon((File) file);// 获取文件的图标
					setIcon(icon); // 设置表格单元图标
				} else if (file instanceof FtpFile) { // 如果数据为FtpFile类
					FtpFile ftpfile = (FtpFile) file;
					try {
						// 使用FtpFile的文件名称创建临时文件
						File tempFile = File.createTempFile("tempfile_",
								ftpfile.getName());
						// 获取临时文件的图标
						Icon icon = view.getSystemIcon(tempFile);
						tempFile.delete(); // 删除临时文件
						setIcon(icon); // 设置表格单元图标
					} catch (IOException e) {
						e.printStackTrace();
						setIcon(fileIcon);
					}
				}
				setText(file.toString()); // 设置文本内容
			}
		} else { // 如果选择的不是文件或文件夹
			setIcon(folderIcon); // 设置备用的文件夹图标
			setText(value.toString()); // 设置名称
		}
	}
}
