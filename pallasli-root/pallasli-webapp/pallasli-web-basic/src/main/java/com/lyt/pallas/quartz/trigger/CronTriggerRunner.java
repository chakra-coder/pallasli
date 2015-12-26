package com.lyt.pallas.quartz.trigger;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.lyt.pallas.quartz.job.SimpleQuartzJob;

public class CronTriggerRunner {

	public static void task() throws SchedulerException {

		SchedulerFactory schedulerFactory = new StdSchedulerFactory();

		Scheduler scheduler = schedulerFactory.getScheduler();

		long ctime = System.currentTimeMillis();

		JobDetail jobDetail = new JobDetail("jobDetail2", "jobDetailGroup2",
				SimpleQuartzJob.class);

		JobDataMap params = new JobDataMap();
		params.put("name", "handson");
		jobDetail.setJobDataMap(params);

		CronTrigger cronTrigger = new CronTrigger("cronTrigger",
				"triggerGroup2");
		try {

			// ���ʽ��ʹ�÷���
			// Cron���ʽ��6��7���ɿո�ָ��ʱ���ֶ����

			/*
			 * 1 �� 0-59 , - * /
			 * 
			 * 2 ���� 0-59 , - * /
			 * 
			 * 3 Сʱ 0-23, - * /
			 * 
			 * 4 ����1-31 , - * ? / L W C
			 * 
			 * 5 �·� 1-12 , - * /
			 * 
			 * 6 ���� 1-7 , - * ? / L C #
			 * 
			 * 7 ��(��ѡ)��ֵ1970-2099, - * /
			 */

			/*
			 * Cron���ʽ��ʱ���ֶγ�����������ֵ�⣬����ʹ��һЩ������ַ��ṩ�б?��Χ��ͨ���ȹ��ܣ�
			 * ϸ˵���£�
			 * 
			 * ���Ǻ�(*)�������������ֶ��У���ʾ��Ӧʱ�����ÿһ��ʱ�̣����磬*�ڷ����ֶ�ʱ����ʾ�
			 * �ÿ���ӡ���
			 * 
			 * ���ʺţ�?�������ַ�ֻ�����ں������ֶ���ʹ�ã���ͨ��ָ��Ϊ���������ֵ�����൱�ڵ�λ
			 * ��
			 * 
			 * ����(-)�����һ��Χ������Сʱ�ֶ���ʹ�á�10-12�������ʾ��10��12�㣬��10,11,12
			 * ��
			 * 
			 * �񶺺�(,)�����һ���б�ֵ�����������ֶ���ʹ�á�MON,WED,FRI�������ʾ����һ����
			 * ����������壻
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * ��б��(/)��x/y���һ��Ȳ������У�xΪ��ʼֵ��yΪ������ֵ�����ڷ����ֶ���ʹ��0/15
			 * �����ʾΪ0,15,30��45�룬��5 /15�ڷ����ֶ��б�ʾ5,20,35,50����Ҳ����ʹ��*
			 * /y�����ͬ��0/y��
			 * 
			 * ��L�����ַ�ֻ�����ں������ֶ���ʹ�ã���?Last������˼��������}���ֶ�����˼��
			 * ͬ��L�������ֶ��У���ʾ����·ݵ����һ�죬 ��һ�µ�31��
			 * ����������µ�28�ţ����L�
			 * ��������У����ʾ�������ͬ��7�����ǣ����L��������
			 * ���ֶ��������ǰ����һ����ֵX��
			 * ���ʾ������µ����X�족�����磬6L��ʾ���µ���������壻
			 * 
			 * ��W�����ַ�ֻ�ܳ����������ֶ���Ƕ�ǰ�����ڵ����Σ���ʾ����������Ĺ����ա����
			 * �15W��ʾ�����15�����Ĺ����գ�
			 * ������15������������ƥ��14�������壻���15�
			 * ��������գ���ƥ��16������һ�����15�������ڶ����ǽ�����15�����ڶ���
			 * ������ע���j��ƥ�����ڲ��ܹ�����
			 * ������ָ��1W�����1������������ƥ�����3�
			 * �����һ������ϸ����������졣W�ַ�ֻ��ָ����һ���ڣ�����ָ�����ڷ�Χ��
			 * 
			 * ��LW��ϣ��������ֶο������ʹ��LW�������˼�ǵ��µ����һ�����գ�
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * �񾮺�(#)�����ַ�ֻ���������ֶ���ʹ�ã���ʾ����ĳ�����ա���6#3��ʾ���µĵ������
			 * ����(6��ʾ�����壬#3��ʾ��ǰ�ĵ����)��
			 * ��4#5��ʾ���µĵ������������赱��û�е������������Բ�������
			 * 
			 * ��
			 * C�����ַ�ֻ�����ں������ֶ���ʹ�ã���?Calendar������˼�������˼�Ǽƻ����j
			 * �����ڣ��������û�б���j�����൱���������������� ��
			 * ����5C�������ֶ��о��
			 * ൱������5���Ժ�ĵ�һ�졣1C�������ֶ����൱�������պ�ĵ�һ�졣Cron
			 * ���ʽ�������ַ�Ĵ�Сд����У�
			 * �Դ�����ڵ���дӢ�Ĵ�СдҲ����С���2������һЩ�����Cron��ʾʽ��ʵ��
			 */

			CronExpression cexp = new CronExpression("0/5 * * * * ?");

			/*
			 * ��2 Cron��ʾʽʾ��
			 * 
			 * ��ʾʽ ˵��
			 * 
			 * "0 0 12 * * ? " ÿ��12������
			 * 
			 * "0 15 10 ? * *" ÿ��10:15����
			 * 
			 * "0 15 10 * * ?" ÿ��10:15����
			 * 
			 * "0 15 10 * * ? *" ÿ��10:15����
			 * 
			 * "0 15 10 * * ? 2008" ��2008���ÿ��10��15����
			 * 
			 * "0 * 14 * * ?"
			 * ÿ��14�㵽15��֮��ÿ��������һ�Σ���ʼ��14:00��������14:59��
			 * 
			 * "0 0/5 14 * * ?"
			 * ÿ��14�㵽15��ÿ5��������һ�Σ���ʼ��14:00��������14:55��
			 * 
			 * "0 0/5 14,18 * * ?"
			 * ÿ��14�㵽15��ÿ5��������һ�Σ�����ÿ��18�㵽19��ÿ5��Ҳ����һ�Ρ�
			 * 
			 * "0 0-5 14 * * ?" ÿ��14:00�㵽14:05��ÿ��������һ�Ρ�
			 * 
			 * "0 10,44 14 ? 3 WED" 3��ÿ�����14:10�ֵ�14:44��ÿ��������һ�Ρ�
			 * 
			 * "0 15 10 ? * MON-FRI" ÿ��һ���������ģ����10:15�����С�
			 * 
			 * "0 15 10 15 * ?" ÿ��15��10:15�����С�
			 * 
			 * "0 15 10 L * ?" ÿ�����һ��10:15�����С�
			 * 
			 * "0 15 10 ? * 6L" ÿ�����һ��������10:15�����С�
			 * 
			 * "0 15 10 ? * 6L 2007-2009"
			 * ��2007,2008,2009��ÿ���µ����һ���������10:15�����С�
			 * 
			 * "0 15 10 ? * 6#3" ÿ�µ�����������10:15������
			 */

			cronTrigger.setCronExpression(cexp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		scheduler.scheduleJob(jobDetail, cronTrigger);

		scheduler.start();
	}

	public static void main(String args[]) {
		try {
			task();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
