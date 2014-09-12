package com.haier.openplatform.hopdeploy.deploy.service;

import java.util.List;

import com.haier.openplatform.hopdeploy.deploy.domain.PreTask;
import com.haier.openplatform.hopdeploy.deploy.domain.Task;
import com.haier.openplatform.hopdeploy.deploy.domain.WeblogicShell;
import com.haier.openplatform.util.ExecuteResult;
import com.haier.openplatform.util.Pager;

public interface TaskService {
	/**
	 * 扫描待发版的应用
	 * 
	 * @return
	 */
	public List<PreTask> scanPreTask();

	/**
	 * 来自页面的手动发版
	 * 
	 * @param task
	 * @return
	 */
	public Task deployTask(List<PreTask> preTaskList);

	/**
	 * 将发版结果写回Task表中供发版结果处理
	 */
	public void feedbackTask(List<Task> taskList);

	/**
	 * 手动发版结果回写
	 * 
	 * @param task
	 */
	public void feedbackTask(Task task);

	/**
	 * 发版情况回馈给alm
	 * 
	 * @param task
	 */
	public void feedbackTaskToAlm(Task task);

	/**
	 * 确认发版成功
	 * 
	 * @param task
	 */
	public void confirmSuccess(Task task);

	/**
	 * 发版结果
	 * 
	 * @return
	 */
	public List<Task> searchTask();

	/**
	 * 根据包名获取发版必须信息
	 * 
	 * @param appName
	 * @param deployType
	 * @return
	 */
	public List<PreTask> getPreTaskByPackage(String appName, String packageType);

	/**
	 * 查询weblogic脚本所在服务器
	 * 
	 * @return
	 */
	public WeblogicShell searchWeblogicShell();

	/**
	 * 更新weblogic脚本所在服务器
	 * 
	 * @return
	 */
	public int updateWeblogicShell(WeblogicShell weblogicShell);

	public void insertManualTask(Task task);

	public void sendEmails(Task task);

	public void sendMessages(Task task, List<String> phoneNums);

	public Pager<Task> searchManualDeployResult(Pager<Task> pager, Task task, String startTime, String endTime);

	public String searchManualDeployResultByTaskId(int id);

	public ExecuteResult<Task> deleteManualDeployResult(Integer id);
}
