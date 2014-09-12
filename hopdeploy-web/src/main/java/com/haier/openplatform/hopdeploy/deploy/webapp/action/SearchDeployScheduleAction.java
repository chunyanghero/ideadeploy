package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;
import com.haier.openplatform.util.Pager;

public class SearchDeployScheduleAction extends BaseDeployAction {
	private static final long serialVersionUID = -2299222354653682059L;
	private Pager<DeploySchedule> pager = new Pager<DeploySchedule>();
	private DeploySchedule deploySchedule = new DeploySchedule();
	private String startTime;
	private String endTime;

	@Override
	public String execute() throws Exception {
		this.pager = this.buildPagerFromRequestParameters();
		pager = deployScheduleService.searchDeploySchedule(pager, deploySchedule, startTime, endTime);
		return SUCCESS;
	}

	public Pager<DeploySchedule> getPager() {
		return pager;
	}

	public void setPager(Pager<DeploySchedule> pager) {
		this.pager = pager;
	}

	public DeploySchedule getDeploySchedule() {
		return deploySchedule;
	}

	public void setDeploySchedule(DeploySchedule deploySchedule) {
		this.deploySchedule = deploySchedule;
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

}
