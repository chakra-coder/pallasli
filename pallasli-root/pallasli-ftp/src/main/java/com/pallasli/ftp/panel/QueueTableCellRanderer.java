/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pallasli.ftp.panel;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.pallasli.ftp.extClass.ProgressArg;

/**
 * @author lzwJava
 */
public class QueueTableCellRanderer extends JProgressBar implements
		TableCellRenderer {
	public QueueTableCellRanderer() {
		setStringPainted(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof ProgressArg) {
			ProgressArg arg = (ProgressArg) value;
			setMinimum(arg.getMin());
			setMaximum(arg.getMax());
			setValue(arg.getValue());
			table.setRowSelectionInterval(row, row);
			table.setColumnSelectionInterval(column, column);
		}
		if (getValue() < getMaximum())
			return this;
		else {
			if (getMaximum() == -1)
				return new JLabel();
			return new JLabel("完成");
		}
	}
}
