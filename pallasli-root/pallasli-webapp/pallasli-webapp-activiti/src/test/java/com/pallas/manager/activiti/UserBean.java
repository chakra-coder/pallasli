package com.pallas.manager.activiti;

import org.activiti.engine.RuntimeService;
import org.springframework.transaction.annotation.Transactional;

public class UserBean {
	/** ��Springע�� */
	private RuntimeService runtimeService;

	@Transactional
	public void hello() {
		// �������������ǵ�����ģ������һЩ���ﴦ��
		// ���ڵ���Activiti RuntimeService��startProcessInstanceByKey����ʱ��
		// �������ϵ�ͬһ�������С�
		runtimeService.startProcessInstanceByKey("helloProcess");
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
}
