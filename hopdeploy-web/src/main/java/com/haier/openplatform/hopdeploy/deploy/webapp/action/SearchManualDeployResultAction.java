package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.Task;
import com.haier.openplatform.util.Pager;

public class SearchManualDeployResultAction extends BaseDeployAction {
	private static final long serialVersionUID = 653606586937642226L;
	private Pager<Task> pager = new Pager<Task>();
	private Task task = new Task();
	private String startTime;
	private String endTime;

	@Override
	public String execute() throws Exception {
		this.pager = this.buildPagerFromRequestParameters();
		pager = deployService.searchManualDeployResult(pager, task, startTime, endTime);
		return SUCCESS;
	}

	public Pager<Task> getPager() {
		return pager;
	}

	public void setPager(Pager<Task> pager) {
		this.pager = pager;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
