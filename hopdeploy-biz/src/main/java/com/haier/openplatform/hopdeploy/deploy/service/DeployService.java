package com.haier.openplatform.hopdeploy.deploy.service;

import java.util.List;

import com.haier.openplatform.hopdeploy.deploy.domain.PreTask;
import com.haier.openplatform.hopdeploy.deploy.domain.Task;
import com.haier.openplatform.hopdeploy.deploy.domain.WeblogicShell;
import com.haier.openplatform.util.ExecuteResult;
import com.haier.openplatform.util.Pager;

public interface DeployService {
	/**
	 * 自动发版，定时任务来调
	 * 
	 * @param task
	 */
	public void autoDeploy();

	/**
	 * 根据包名取必要发版信息
	 * 
	 * @return
	 */
	public List<PreTask> composePreTaskByPackageName(String packageName);

	/**
	 * 个别应用手动发版，如自动发版失败等
	 * 
	 * @param task
	 */
	public ExecuteResult<Task> manualDeploy(List<PreTask> preTaskList);

	/**
	 * 经人工检查后，确认发版成功
	 * 
	 * @param task
	 */
	public void confirmDeploySuccess(Task task);

	/**
	 * 查询待发版应用
	 * 
	 * @return
	 */
	public List<Task> searchDeployToDoList();

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
	public ExecuteResult<WeblogicShell> updateWeblogicShell(WeblogicShell weblogicShell);

	/**
	 * 查询手动发版历史记录
	 * 
	 * @param task
	 * @return
	 */
	public Pager<Task> searchManualDeployResult(Pager<Task> pager, Task task, String startTime, String endTime);

	/**
	 * 手动发版服务器返回信息
	 * 
	 * @param id
	 * @return
	 */
	public String searchManualDeployResultByTaskId(int id);

	/**
	 * 删除手动发版结果
	 * 
	 * @param id
	 * @return
	 */
	public ExecuteResult<Task> deleteManualDeployResult(Integer id);
}
