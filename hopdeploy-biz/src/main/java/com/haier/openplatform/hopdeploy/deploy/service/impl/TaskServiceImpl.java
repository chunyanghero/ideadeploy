package com.haier.openplatform.hopdeploy.deploy.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.haier.openplatform.hmc.domain.Email;
import com.haier.openplatform.hmc.domain.HmcMessage;
import com.haier.openplatform.hmc.domain.Recipient;
import com.haier.openplatform.hmc.domain.SMS;
import com.haier.openplatform.hmc.domain.SMSMessage;
import com.haier.openplatform.hmc.sender.SendMsgService;
import com.haier.openplatform.hmc.sender.email.SendEmailService;
import com.haier.openplatform.hopdeploy.deploy.dao.AlmTaskDAO;
import com.haier.openplatform.hopdeploy.deploy.dao.DAOException;
import com.haier.openplatform.hopdeploy.deploy.dao.PreTaskDAO;
import com.haier.openplatform.hopdeploy.deploy.dao.TaskDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.AlmTask;
import com.haier.openplatform.hopdeploy.deploy.domain.PreTask;
import com.haier.openplatform.hopdeploy.deploy.domain.Task;
import com.haier.openplatform.hopdeploy.deploy.domain.WeblogicShell;
import com.haier.openplatform.hopdeploy.deploy.service.TaskService;
import com.haier.openplatform.hopdeploy.security.dao.UserDAO;
import com.haier.openplatform.hopdeploy.security.domain.User;
import com.haier.openplatform.hopdeploy.util.SshUtil;
import com.haier.openplatform.util.ExecuteResult;
import com.haier.openplatform.util.Pager;

public class TaskServiceImpl implements TaskService {
	private PreTaskDAO preTaskDAO;
	private TaskDAO taskDAO;
	private AlmTaskDAO almTaskDAO;
	private UserDAO userDAO;
	private SendEmailService emailSender;
	private SendMsgService smsSender;

	private static final Logger LOG = Logger.getLogger(TaskServiceImpl.class);

	@Override
	public List<PreTask> scanPreTask() {
		List<PreTask> preTaskList = new ArrayList<PreTask>();
		PreTask pt = new PreTask();
		try {
			// 从alm数据库中取出待发版的应用(只取当天的)
			List<AlmTask> almTaskList = almTaskDAO.searchAlmTask();
			// taskDAO.insertAlmTask(almTaskList);
			for (AlmTask at : almTaskList) {
				pt = preTaskDAO.scanPreTask(at);
				pt.setPublishId(at.getPublishId());
				pt.setTaskId(at.getTaskId());
				pt.setIsUrgent(at.getIsUrgent());
				pt.setPackageName(at.getPackageName());
				pt.setState(at.getState());
				preTaskList.add(pt);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return preTaskList;
	}

	@Override
	public Task deployTask(List<PreTask> preTaskList) {
		LOG.info("========================= TaskServiceImpl.deployTask begin =======================");
		Task task = new Task();
		task.setDeployDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		if (preTaskList.size() == 1 && "AEIOU".equals(preTaskList.get(0).getApplicationCode())) {
			task.setIsSuccessful(4);
			task.setPackageName(preTaskList.get(0).getPackageName());
			List<String> ltask = new ArrayList<String>();
			ltask.add("包名对应打包类型没有入库!");
			task.setResult(ltask);
			return task;
		}
		for (PreTask preTask : preTaskList) {
			LOG.info("========================= deploy " + preTask.getPackageName() + " begin =======================");
			task.setDeployType(preTask.getDeployType());
			task.setPackageName(preTask.getPackageName());
			task.setPublishId(preTask.getPublishId());
			task.setTaskId(preTask.getTaskId());
			task.setMiddlewareType(preTask.getMiddlewareType());

			// 1:weblogic, 2:tar, 3:tomcat, 4:jboss
			Integer mType = preTask.getMiddlewareType();
			// 0:不使用此系统部署 1:热部署 2:先卸载后脚本部署
			Integer dType = preTask.getDeployType();
			// 0:linux, 1:windows
			Integer oType = preTask.getOsType();

			String ip = preTask.getIp();
			String ftpIp = preTask.getFtpIp();
			String ftpUsername = preTask.getFtpUsername();
			String ftpPassword = preTask.getFtpPassword();
			String packageName = preTask.getPackageName();
			String unixUsername = preTask.getUnixUsername();
			String unixPassword = preTask.getUnixPassword();

			// //////////////////////////////////////////////////////////////////////
			LOG.info("//////////////////////////////////////////////////////////////");
			LOG.info(ip);
			LOG.info(unixUsername);
			LOG.info(unixPassword);
			LOG.info(ftpIp);
			LOG.info(ftpUsername);
			LOG.info(ftpPassword);
			LOG.info(packageName);
			LOG.info("//////////////////////////////////////////////////////////////");

			// //////////////////////////////////////////////////////////////////////

			if (ip == null || ftpIp == null || ftpUsername == null || ftpPassword == null || packageName == null
					|| unixUsername == null || unixPassword == null || mType == null || dType == null) {
				// 参数信息不完整
				task.setIsSuccessful(1);
				return task;
			}
			if (dType == 0) {
				// 不使用此系统部署
				task.setIsSuccessful(2);
				return task;
			}
			if (oType == 1) {
				// windows操作系统，暂不使用此系统部署
				task.setIsSuccessful(3);
				return task;
			}

			task.setIsSuccessful(0);

			/* 1:weblogic */
			if (mType == 1) {
				WeblogicShell ws = preTaskDAO.searchWeblogicShell();
				String wsIp = ws.getWeblogicShellIp();
				String wsUsername = ws.getWeblogicShellUsername();
				String wsPassword = ws.getWeblogicShellPassword();
				String login = preTask.getLogin();
				String passwd = preTask.getPasswd();
				String port = preTask.getPort();
				String consoleUrl = "t3://" + ip + ":" + port;
				String command = "";
				if (dType == 1) {
					/* 热部署 */
					command = "./autodeploy_v_1.sh " + " " + login + " " + passwd + " " + ftpUsername + " "
							+ ftpPassword + " " + ip + " " + ftpIp + " " + consoleUrl + " " + packageName;
				} else if (dType == 2) {
					/* 卸载后部署 */
					command = "./autodeploy_v_1.sh " + " " + login + " " + passwd + " " + ftpUsername + " "
							+ ftpPassword + " " + ip + " " + ftpIp + " " + consoleUrl + " " + packageName + " 1";
				} else if (dType == 3) {
					/* 部署到部分节点 （注意：第一次部署到部分节点后以后热部署就可以（如果支持热部署的话）） */
					if (preTask.getDeployNodes() == null) {
						task.setIsSuccessful(5);
						return task;
					}
					String dNodes = preTask.getDeployNodes().trim();
					command = "./autodeploy_v_1.sh " + " " + login + " " + passwd + " " + ftpUsername + " "
							+ ftpPassword + " " + ip + " " + ftpIp + " " + consoleUrl + " " + packageName + " ,,,...@"
							+ dNodes;

				} else if (dType == 4) {
					/* 解压后部署 */
					if (preTask.getDeployNodes() == null) {
						task.setIsSuccessful(5);
						return task;
					}
					String dNodes = preTask.getDeployNodes().trim();
					String weblogicDomain = preTask.getMiddlewarePath();
					command = "./mdm_deploy.sh " + " " + weblogicDomain + " " + login + " " + passwd + " " + consoleUrl
							+ " " + dNodes + " " + packageName + " " + ftpIp + " " + ftpUsername + " " + ftpPassword;
				} else if (dType == 5) {
					/* 解压后热部署 */
					if (preTask.getDeployNodes() == null) {
						task.setIsSuccessful(5);
						return task;
					}
					String dNodes = preTask.getDeployNodes().trim();
					String weblogicDomain = preTask.getMiddlewarePath();
					command = "./mdm_deploy.sh " + " " + weblogicDomain + " " + login + " " + passwd + " " + consoleUrl
							+ " " + dNodes + " " + packageName + " " + ftpIp + " " + ftpUsername + " " + ftpPassword
							+ " " + "1";
				}

				LOG.info("================== weblogic command ====================");
				LOG.info(command);
				LOG.info("================== weblogic command ====================");

				task.setResult(SshUtil.cmd(wsIp, wsUsername, wsPassword, command));
				/* 2:dubbo */
			} else if (mType == 2) {
				String middlewarePath = preTask.getMiddlewarePath();

				String command = "./autoDeployTar.sh " + ip + " " + ftpUsername + " " + ftpPassword + " "
						+ middlewarePath + " " + packageName;

				LOG.info("================== dubbo command ====================");
				LOG.info(command);
				LOG.info("================== dubbo command ====================");

				task.setResult(SshUtil.cmd(ip, unixUsername, unixPassword, command));
				/* 3:tomcat */
			} else if (mType == 3) {
				String tomcatHomeDir = preTask.getMiddlewarePath();
				String checkUrl = preTask.getCheckUrl();
				if (checkUrl == null || "".equals(checkUrl)) {
					task.setIsSuccessful(8);
					return task;
				}
				String specificPath = preTask.getSpecificPath();
				String command = "./tomcat_deploy.sh " + tomcatHomeDir + " " + packageName + " " + ftpIp + " "
						+ ftpUsername + " " + ftpPassword + " " + checkUrl + " " + specificPath;

				LOG.info("================== tomcat command ====================");
				LOG.info(command);
				LOG.info("================== tomcat command ====================");

				String titleStart = "============================ " + ip + " ===========================\n";
				task.getResult().add(titleStart);
				task.getResult().addAll(SshUtil.cmd(ip, unixUsername, unixPassword, command));
				String titleEnd = "=============================== END ==============================\n";
				task.getResult().add(titleEnd);
				/* 4:jboss */
			} else if (mType == 4) {
				/* hop4.0 static */
			} else if (mType == 6) {
				String middlewarePath = preTask.getMiddlewarePath();
				String jettyDir = preTask.getSpecificPath();
				String checkUrl = preTask.getCheckUrl();
				if (checkUrl == null || "".equals(checkUrl)) {
					task.setIsSuccessful(8);
					return task;
				}
				String command = "./unpackstatic.sh " + packageName + " " + middlewarePath + " " + jettyDir + " "
						+ ftpIp + " " + ftpUsername + " " + ftpPassword + " " + checkUrl;

				LOG.info("=============== hop4.0 static command ================");
				LOG.info(command);
				LOG.info("=============== hop4.0 static command ================");

				task.setResult(SshUtil.cmd(ip, unixUsername, unixPassword, command));
				/* hop4.0 war */
			} else if (mType == 7) {
				String middlewarePath = preTask.getMiddlewarePath();
				String jettyDir = preTask.getSpecificPath();
				String checkUrl = preTask.getCheckUrl();
				if (checkUrl == null || "".equals(checkUrl)) {
					task.setIsSuccessful(8);
					return task;
				}
				String command = "./unpackwar.sh " + packageName + " " + middlewarePath + " " + jettyDir + " " + ftpIp
						+ " " + ftpUsername + " " + ftpPassword + " " + checkUrl;
				LOG.info("=============== hop4.0 war command ================");
				LOG.info(command);
				LOG.info("=============== hop4.0 war command ================");
				task.setResult(SshUtil.cmd(ip, unixUsername, unixPassword, command));
			}
			LOG.info("========================= deploy " + preTask.getPackageName() + "  end  =======================");
		}

		/* *************************************************************************** */

		// 一些公用的通过脚本打印出的错误在这里进行分析处理，如ftp上找不到文件、服务器密码不对 //

		List<String> taskResult = task.getResult();
		if (taskResult != null) {
			String tr = taskResult.toString();
			if (tr.contains("服务器口令错误")) {
				task.setIsSuccessful(6);
				return task;
			} else if (tr.contains("ftpfilenotexsits")) {
				task.setIsSuccessful(7);
				return task;
			}
		}

		/* *************************************************************************** */
		LOG.info("========================= TaskServiceImpl.deployTask  end  =======================");
		return task;
	}

	@Override
	public List<PreTask> getPreTaskByPackage(String appName, String packageType) {
		Integer pType = null;
		if ("war".equals(packageType))
			pType = 1;
		else if ("tar".equals(packageType))
			pType = 2;
		else if ("zip".equals(packageType))
			pType = 3;
		return preTaskDAO.getPreTaskByPackage(appName, pType);
	}

	@Override
	public WeblogicShell searchWeblogicShell() {
		return preTaskDAO.searchWeblogicShell();
	}

	@Override
	public int updateWeblogicShell(WeblogicShell weblogicShell) {
		return preTaskDAO.changeWeblogicShell(weblogicShell);
	}

	@Override
	public void insertManualTask(Task task) {
		task.setDeployMessage(convertListToString(task));
		taskDAO.insertManualTask(task);

	}

	@Override
	public void sendEmails(Task task) {
		emailSender.sendEmail(this.buildEmail(task));
	}

	@Override
	public void sendMessages(Task task, List<String> phoneNums) {
		smsSender.sendMsg(this.buildMsg(task, phoneNums));
	}

	private Email buildEmail(Task task) {
		Email email = new Email();
		List<User> users = userDAO.searchForSendingEM();
		List<Recipient> recipients = new ArrayList<Recipient>();
		if (users != null)
			for (User user : users) {
				Recipient r = new Recipient();
				if (user.getEmail() != null && !"".equals(user.getEmail())) {
					r.setUserName(user.getRealName());
					r.setEmailAddress(user.getEmail());
					recipients.add(r);
				}
			}
		email.setSubject("HOPDEPLOY发版系统发布" + task.getPackageName() + "结果反馈");
		email.setToRecipient(recipients);
		email.setSender(new Recipient("hopPlatform@haier.com", "服务开放平台"));
		email.setBodyContent(task.getDeployMessage(), false);
		return email;
	}

	private HmcMessage buildMsg(Task task, List<String> phoneNums) {
		List<SMS> smses = new ArrayList<SMS>();
		for (String phoneNum : phoneNums) {
			SMS sms = new SMS();
			sms.setDepartment("HOP");
			// sms.setMsgCode(new Date().getTime() + "");
			sms.setMsgCode(System.currentTimeMillis() + "");
			sms.setPhoneNum(phoneNum);
			sms.setMsgContent(task.getDeployMessage());
			smses.add(sms);
		}
		SMSMessage smsm = new SMSMessage();
		smsm.setSenderName("HOPDEPLOY");
		smsm.setSmsList(smses);
		return smsm;
	}

	private String convertListToString(Task task) {
		List<String> list = task.getResult();
		StringBuilder sb = new StringBuilder();
		if (list != null)
			for (String s : list) {
				sb.append(s);
				sb.append("\n");
			}
		return sb.toString();
	}

	@Override
	public Pager<Task> searchManualDeployResult(Pager<Task> pager, Task task, String startTime, String endTime) {
		List<Task> records = taskDAO.searchManualDeployResult(pager, task, startTime, endTime);
		Long totalRecords = taskDAO.searchManualDeployResultCount(pager, task, startTime, endTime);
		return Pager.cloneFromPager(pager, totalRecords, records);
	}

	@Override
	public String searchManualDeployResultByTaskId(int id) {
		return taskDAO.searchManualDeployResultByTaskId(id);
	}

	@Override
	public ExecuteResult<Task> deleteManualDeployResult(Integer id) {
		ExecuteResult<Task> result = new ExecuteResult<Task>();
		if (taskDAO.delete(id) != 1)
			result.addErrorMessage("删除发版历史记录时出错！");
		return result;
	}

	@Override
	public void feedbackTask(List<Task> taskList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void feedbackTask(Task task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void feedbackTaskToAlm(Task task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void confirmSuccess(Task task) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Task> searchTask() {
		// TODO Auto-generated method stub
		return null;
	}

	public PreTaskDAO getPreTaskDAO() {
		return preTaskDAO;
	}

	public void setPreTaskDAO(PreTaskDAO preTaskDAO) {
		this.preTaskDAO = preTaskDAO;
	}

	public TaskDAO getTaskDAO() {
		return taskDAO;
	}

	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	public AlmTaskDAO getAlmTaskDAO() {
		return almTaskDAO;
	}

	public void setAlmTaskDAO(AlmTaskDAO almTaskDAO) {
		this.almTaskDAO = almTaskDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public SendEmailService getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(SendEmailService emailSender) {
		this.emailSender = emailSender;
	}

	public SendMsgService getSmsSender() {
		return smsSender;
	}

	public void setSmsSender(SendMsgService smsSender) {
		this.smsSender = smsSender;
	}

}
