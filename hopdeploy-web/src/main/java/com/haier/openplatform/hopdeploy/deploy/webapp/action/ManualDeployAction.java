package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import java.util.List;

import com.haier.openplatform.hopdeploy.deploy.domain.PreTask;
import com.haier.openplatform.hopdeploy.deploy.domain.Task;
import com.haier.openplatform.util.ExecuteResult;

public class ManualDeployAction extends BaseDeployAction {
	private static final long serialVersionUID = 1779603231387563570L;
	private String packageName;
	private String packageNamePart1;
	private String packageNamePart2;
	private String packageNamePart3;
	private Task task;
	private String state;

	@Override
	public String execute() throws Exception {
		if (!"".equals(packageNamePart1)) {
			if (!"".equals(packageNamePart2)) {
				if (Integer.parseInt(packageNamePart2) < 10)
					packageNamePart2 = "0" + packageNamePart2;

				packageName = packageName.trim() + "." + packageNamePart1 + packageNamePart2 + "." + packageNamePart3;
			} else {
				packageName = packageName.trim() + "." + packageNamePart1 + "." + packageNamePart3;
			}
		} else {
			packageName = packageName.trim() + "." + packageNamePart3;
		}

		List<PreTask> preTaskList = deployService.composePreTaskByPackageName(packageName);

		ExecuteResult<Task> executeResult = deployService.manualDeploy(preTaskList);

		if (!executeResult.isSuccess()) {
			state = "error";
			task = executeResult.getResult();
		} else {
			state = "success";
			task = executeResult.getResult();
		}

		return SUCCESS;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageNamePart1() {
		return packageNamePart1;
	}

	public void setPackageNamePart1(String packageNamePart1) {
		this.packageNamePart1 = packageNamePart1;
	}

	public String getPackageNamePart2() {
		return packageNamePart2;
	}

	public void setPackageNamePart2(String packageNamePart2) {
		this.packageNamePart2 = packageNamePart2;
	}

	public String getPackageNamePart3() {
		return packageNamePart3;
	}

	public void setPackageNamePart3(String packageNamePart3) {
		this.packageNamePart3 = packageNamePart3;
	}

}
