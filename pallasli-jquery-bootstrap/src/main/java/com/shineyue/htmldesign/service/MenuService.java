package com.shineyue.htmldesign.service;

import java.util.List;

import com.shineyue.htmldesign.model.Module;
import com.shineyue.htmldesign.model.Page;

public interface MenuService {
	public List<Module> listModule(Module parent);

	public List<Page> listPage(Module parent);

	public boolean addModule(Module record);

	public boolean addPage(Page record);

	public boolean alterModule(Module record);

	public boolean alterPage(Page record);

	public boolean delModule(int id);

	public boolean delPage(int id);
}
