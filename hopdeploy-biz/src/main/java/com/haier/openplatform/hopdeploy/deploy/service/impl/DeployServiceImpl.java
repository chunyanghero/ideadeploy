package com.haier.openplatform.hopdeploy.deploy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.haier.openplatform.hopdeploy.deploy.domain.PreTask;
import com.haier.openplatform.hopdeploy.deploy.domain.Task;
import com.haier.openplatform.hopdeploy.deploy.domain.WeblogicShell;
import com.haier.openplatform.hopdeploy.deploy.service.DeployService;
import com.haier.openplatform.hopdeploy.deploy.service.TaskService;
import com.haier.openplatform.util.ExecuteResult;
import com.haier.openplatform.util.Pager;

public class DeployServiceImpl implements DeployService {
	private TaskService taskService;
	private static final Logger LOG = Logger.getLogger(DeployServiceImpl.class);

	@Override
	public void autoDeploy() {
		// Step 1. 扫描数据库，得到组装后的要发版的项目
		// List<PreTask> preTaskList = taskService.scanPreTask();
		// Step 2. 对扫描到的项目进行自动发版，并将每次发版的结果都返回
		// List<Task> taskList = taskService.deployTask(preTaskList);
		// Step 3. 将所有的发版结果都回写数据库
		// taskService.feedbackTask(taskList);
	}

	/**
	 * 手动部署并将结果反馈到页面
	 */
	@Override
	public ExecuteResult<Task> manualDeploy(List<PreTask> preTaskList) {
		ExecuteResult<Task> executeResult = new ExecuteResult<Task>();
		LOG.info("=========== DeployServiceImpl.manualDeploy ===========");
		for (PreTask pt : preTaskList)
			LOG.info(pt.toString());
		LOG.info("=========== DeployServiceImpl.manualDeploy ===========");
		Task task = taskService.deployTask(preTaskList);
		if (task.getIsSuccessful() == 4) {
			executeResult.addErrorMessage("是不是打包类型选错了？或者没有将相应的数据存入数据库？");
			List<String> m = new ArrayList<String>();
			m.add("是不是打包类型选错了？或者没有将相应的数据存入数据库？");
			task.setResult(m);
		} else if (task.getIsSuccessful() == 1) {
			executeResult.addErrorMessage("参数信息不完整");
		} else if (task.getIsSuccessful() == 2) {
			executeResult.addErrorMessage("不使用此系统部署");
		} else if (task.getIsSuccessful() == 3) {
			executeResult.addErrorMessage("windows操作系统，暂不使用此系统部署");
		} else if (task.getIsSuccessful() == 5) {
			executeResult.addErrorMessage("weblogic部署部分节点，这些节点你配置了吗(╯▔皿▔)╯");
			List<String> m = new ArrayList<String>();
			m.add("weblogic部署部分节点，这些节点你配置了吗(╯▔皿▔)╯");
			task.setResult(m);
		} else if (task.getIsSuccessful() == 8) {
			executeResult.addErrorMessage("checkUrl为空!");
			List<String> m = new ArrayList<String>();
			m.add("checkUrl为空!");
			task.setResult(m);
		}
		LOG.info("================== task.toString() ==================");
		LOG.info(task.toString());
		LOG.info("================== task.toString() ==================");
		taskService.insertManualTask(task);
		executeResult.setResult(task);
		if (task.getIsSuccessful() != 1 && task.getIsSuccessful() != 2 && task.getIsSuccessful() != 3
				&& task.getIsSuccessful() != 4) {
			List<String> tl = task.getResult();
			LOG.info("++++++++++++++++++++++++ Task.getResult() +++++++++++++++++++++++++");
			if (tl != null)
				for (String s : tl)
					LOG.info(s);

			LOG.info("++++++++++++++++++++++++ Task.getResult() +++++++++++++++++++++++++");
		}

		return executeResult;
	}

	@Override
	public void confirmDeploySuccess(Task task) {
		taskService.confirmSuccess(task);
	}

	@Override
	public List<Task> searchDeployToDoList() {
		return taskService.searchTask();
	}

	@Override
	public List<PreTask> composePreTaskByPackageName(String packageName) {
		String[] sa = packageName.split("\\.");

		String appName = "";
		String packageType = "";
		if (sa.length == 3) {
			appName = sa[0];
			packageType = sa[2];
		} else if (sa.length == 2) {
			appName = sa[0];
			packageType = sa[1];
		}
		List<PreTask> pts = taskService.getPreTaskByPackage(appName, packageType);
		if (pts != null) {
			for (PreTask pt : pts)
				pt.setPackageName(packageName);
		}
		if (pts.size() < 1) {
			PreTask prt = new PreTask();
			prt.setPackageName(packageName);
			prt.setApplicationCode("AEIOU");
			pts.add(prt);
		}
		return pts;
	}

	@Override
	public WeblogicShell searchWeblogicShell() {

		return taskService.searchWeblogicShell();
	}

	@Override
	public ExecuteResult<WeblogicShell> updateWeblogicShell(WeblogicShell weblogicShell) {
		ExecuteResult<WeblogicShell> executeResult = new ExecuteResult<WeblogicShell>();
		if (taskService.updateWeblogicShell(weblogicShell) != 1) {
			executeResult.addErrorMessage("更新出错！");
		}
		return executeResult;
	}

	@Override
	public Pager<Task> searchManualDeployResult(Pager<Task> pager, Task task, String startTime, String endTime) {
		return taskService.searchManualDeployResult(pager, task, startTime, endTime);
	}

	@Override
	public String searchManualDeployResultByTaskId(int id) {
		return taskService.searchManualDeployResultByTaskId(id);
	}

	@Override
	public ExecuteResult<Task> deleteManualDeployResult(Integer id) {
		return taskService.deleteManualDeployResult(id);

	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

}
