package com.pallasli.bpm.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.pallasli.bpm.bean.RemarkInfo;

public class RemarkService {

	/**
	 * 获取流程流转记录列表
	 * 
	 * @param processKey
	 *            流程定义唯一标识
	 * @param instanceId
	 *            流程实例唯一标识
	 * @return
	 */
	@WebMethod
	public List<RemarkInfo> getRemarkList(
			@WebParam(name = "processKey") String processKey,
			@WebParam(name = "instanceId") String instanceId) {
		return historyServiceDao.getRemarkList(instanceId);
	}
}
