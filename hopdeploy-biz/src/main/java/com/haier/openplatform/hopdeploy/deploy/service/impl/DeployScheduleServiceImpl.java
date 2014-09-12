package com.haier.openplatform.hopdeploy.deploy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.haier.openplatform.hopdeploy.deploy.dao.DeployScheduleDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;
import com.haier.openplatform.hopdeploy.deploy.domain.PreTask;
import com.haier.openplatform.hopdeploy.deploy.domain.Task;
import com.haier.openplatform.hopdeploy.deploy.service.DeployScheduleService;
import com.haier.openplatform.hopdeploy.deploy.service.DeployService;
import com.haier.openplatform.hopdeploy.deploy.service.TaskService;
import com.haier.openplatform.hopdeploy.security.dao.UserDAO;
import com.haier.openplatform.util.ExecuteResult;
import com.haier.openplatform.util.Pager;

public class DeployScheduleServiceImpl implements DeployScheduleService {
	private DeployScheduleDAO deployScheduleDAO;
	private DeployService deployService;
	private TaskService taskService;
	private UserDAO userDAO;

	private static final Logger LOG = Logger.getLogger(DeployScheduleServiceImpl.class);

	@Override
	public List<DeploySchedule> searchExecutorForCombobox() {
		return deployScheduleDAO.searchExecutorForCombobox();
	}

	@Override
	public ExecuteResult<DeploySchedule> createDeploySchedule(DeploySchedule deploySchedule) {
		ExecuteResult<DeploySchedule> executeResult = new ExecuteResult<DeploySchedule>();
		List<String> appAbbrs = deployScheduleDAO.searchAllAppAbbrs();
		String appAbbr = deploySchedule.getPackageName().split("\\.")[0];
		if (!appAbbrs.contains(appAbbr)) {
			executeResult.addErrorMessage("包名不存在，请检查包名拼写是否正确！");
			return executeResult;
		}

		LOG.info("| ----------------createDeploySchedule---------------------- |");

		LOG.info(deploySchedule.toString());

		LOG.info("| ----------------createDeploySchedule---------------------- |");

		deployScheduleDAO.createDeploySchedule(deploySchedule);

		return executeResult;
	}

	@Override
	public ExecuteResult<DeploySchedule> deleteDeployScheduleById(Integer id) {
		ExecuteResult<DeploySchedule> executeResult = new ExecuteResult<DeploySchedule>();

		if (deployScheduleDAO.delete(id) != 1)
			executeResult.addErrorMessage("删除不成功!");
		return executeResult;
	}

	@Override
	public Pager<DeploySchedule> searchDeploySchedule(Pager<DeploySchedule> pager, DeploySchedule deploySchedule,
			String startTime, String endTime) {
		List<DeploySchedule> list = deployScheduleDAO.searchDeploySchedule(pager, deploySchedule, startTime, endTime);
		Long count = deployScheduleDAO.searchDeployScheduleCount(pager, deploySchedule, startTime, endTime);
		return Pager.cloneFromPager(pager, count, list);
	}

	@Override
	public ExecuteResult<DeploySchedule> confirmDeploySchedule(Integer id, Integer flag) {
		ExecuteResult<DeploySchedule> executeResult = new ExecuteResult<DeploySchedule>();
		if (deployScheduleDAO.confirmDeploySchedule(id, flag) != 1)
			executeResult.addErrorMessage("确认不成功！");
		return executeResult;
	}

	@Override
	public List<DeploySchedule> getToBeDeployedApps(Date date) {
		return deployScheduleDAO.getToBeDeployedApps(date);
	}

	@Override
	public void doDeploySchedule(List<DeploySchedule> deploySchedules) {
		if (deploySchedules == null) {
			return;
		} else {
			LOG.info("================DeployScheduleServiceImpl.doDeploySchedule==================");
			LOG.info(deploySchedules.toString());
			LOG.info("================DeployScheduleServiceImpl.doDeploySchedule==================");
			for (DeploySchedule ds : deploySchedules) {
				Integer id = ds.getId();
				String packageName = ds.getPackageName();
				LOG.info("======== deploy " + packageName + " begin ========");
				Task task = doDirtyJob(packageName);
				persistenceDeployMessage(id, task);

				List<String> deployerPhones = userDAO.getPhoneNums();
				List<String> phoneNums = new ArrayList<String>();

				if (null != ds.getExecutor() && !"".equals(ds.getExecutor()) && !"ALM".equals(ds.getExecutor())) {
					phoneNums.add(userDAO.getNumberOfExecutor(ds.getExecutor()));
				} else if ("ALM".equals(ds.getExecutor())) {
					phoneNums.addAll(deployerPhones);
				}
				handleDeployMessage(task, phoneNums);

				LOG.info("======== deploy " + packageName + "  end  ========");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String searchDeployMessage(Integer id) {
		return deployScheduleDAO.searchDeployMessage(id);
	}

	@Override
	public List<DeploySchedule> searchDeployScheduleFromAlm() {
		List<DeploySchedule> deploySchedules = deployScheduleDAO.searchDeployScheduleFromAlm();
		LOG.info("============searchDeployScheduleFromAlm=============");
		LOG.info(deploySchedules.toString());
		LOG.info("============searchDeployScheduleFromAlm=============");

		List<Integer> deployedPublishIds = deployScheduleDAO.getDeployedPublishIds();
		LOG.info("==========getDeployedPublishIds============");
		LOG.info(deployedPublishIds.toString());
		LOG.info("==========getDeployedPublishIds============");

		for (Iterator<DeploySchedule> iter = deploySchedules.iterator(); iter.hasNext();) {
			DeploySchedule ds = iter.next();
			if (deployedPublishIds.contains(ds.getPublishId())) {
				iter.remove();
				continue;
			}
		}
		LOG.info("============searchDeployScheduleFromAlm=============");
		LOG.info(deploySchedules.toString());
		LOG.info("============searchDeployScheduleFromAlm=============");

		return deploySchedules;
	}

	private void handleDeployMessage(Task task, List<String> phoneNums) {
		task = parseDeployMessage(task);
		taskService.sendEmails(task);
		if (task.getIsSuccessful() != 0) {
			taskService.sendMessages(task, phoneNums);
		}
	}

	private Task parseDeployMessage(Task task) {
		List<String> result = task.getResult();
		Integer mType = task.getMiddlewareType();
		int successFlag = task.getIsSuccessful();
		if (successFlag != 0) {
			if (successFlag == 1) {
				task.setDeployMessage(task.getPackageName() + "在系统中的参数信息不完整");
				return task;
			} else if (successFlag == 2) {
				task.setDeployMessage(task.getPackageName() + "不使用此系统部署");
				return task;
			} else if (successFlag == 3) {
				task.setDeployMessage(task.getPackageName() + "为windows操作系统，暂不使用此系统部署");
				return task;
			} else if (successFlag == 4) {
				task.setDeployMessage(task.getPackageName() + "包名和对应的打包类型组合没有存入数据库!");
				return task;
			} else if (successFlag == 5) {
				task.setDeployMessage(task.getPackageName() + "使用的是weblogic部署部分节点方法，这些节点你配置了吗(╯▔皿▔)╯");
				return task;
			} else if (successFlag == 6) {
				task.setDeployMessage(task.getPackageName() + "服务器口令错误");
				return task;
			} else if (successFlag == 7) {
				task.setDeployMessage(task.getPackageName() + "部署失败，FTP上查无此包.");
				return task;
			} else if (successFlag == 8) {
				task.setDeployMessage(task.getPackageName() + "部署失败，checkUrl为空.");
				return task;
			}
		}

		if (mType == 1) {/* 1:weblogic */
			for (String str : result) {
				if (str.contains("Deployer") && str.contains("deploy") && str.contains("succeeded")) {
					LOG.info("============ parseDeployMessage succeeds ============");
					task.setDeployMessage("[weblogic] " + task.getPackageName() + "部署成功.");
					LOG.info("task.getMessage() = " + task.getDeployMessage() + ", task.getIsSuccessful = "
							+ task.getIsSuccessful());
					LOG.info("============ parseDeployMessage succeeds ============");
					return task;
				} else if (str.contains("Deployer") && str.contains("deploy") && str.contains("progress")) {
					task.setDeployMessage("[weblogic] " + task.getPackageName() + "部署尚在进行中,请跟踪.");
					task.setIsSuccessful(9);
					return task;
				} else if (str.contains("*******") && str.contains("Deployment") && str.contains("removal")) {
					task.setDeployMessage("[weblogic] " + task.getPackageName() + "旧版本卸载失败,请及时解决.");
					task.setIsSuccessful(9);
					return task;
				} else if (str.contains("Exception")) {
					task.setDeployMessage("[weblogic] " + task.getPackageName() + "部署过程出现异常,请及时解决.");
					task.setIsSuccessful(9);
					return task;
				}

			}
			task.setDeployMessage(task.getPackageName() + ":未知的情况，请去检查历史记录!");
		} else {
			if (task.getResult() != null) {
				String str = task.getResult().toString();
				if (str.contains("wearehappynow")) {
					task.setDeployMessage(task.getPackageName() + "部署成功.");
					return task;
				} else if (str.contains("nothappywiththat")) {
					task.setDeployMessage(task.getPackageName() + "部署之后，页面访问出错，请及时处理!");
					task.setIsSuccessful(9);
					return task;
				} else if (str.contains("unpackwrong")) {
					task.setDeployMessage(task.getPackageName() + "解压时出错!");
					task.setIsSuccessful(9);
					return task;
				}
			}

			if (mType == 2) {/* 2:dubbo */
			} else if (mType == 3) {/* 3:tomcat */
			} else if (mType == 4) {/* 4:jboss *//* 5:weblogic脚本所在服务器 */
			} else if (mType == 6) {/* hop4.0 static */
			} else if (mType == 7) {/* hop4.0 war */
			}
		}
		return task;
	}

	// 将部署信息持久化到数据库中
	private void persistenceDeployMessage(Integer id, Task task) {
		StringBuilder sb = new StringBuilder();
		int flag = 0;

		List<String> m = new ArrayList<String>();
		if (task.getIsSuccessful() == 4) {
			m.add("是不是打包类型选错了？或者没有将相应的数据存入数据库？");
			flag = 1;
			task.setResult(m);
			deployScheduleDAO.confirmDeploySchedule(id, 2);
		} else if (task.getIsSuccessful() == 1) {
			m.add("参数信息不完整");
			flag = 1;
			task.setResult(m);
			deployScheduleDAO.confirmDeploySchedule(id, 2);
		} else if (task.getIsSuccessful() == 2) {
			m.add("不使用此系统部署");
			flag = 1;
			task.setResult(m);
			deployScheduleDAO.confirmDeploySchedule(id, 2);
		} else if (task.getIsSuccessful() == 3) {
			m.add("windows操作系统，暂不使用此系统部署");
			flag = 1;
			task.setResult(m);
			deployScheduleDAO.confirmDeploySchedule(id, 2);
		} else if (task.getIsSuccessful() == 5) {
			m.add("weblogic部署部分节点，这些节点你配置了吗(╯▔皿▔)╯");
			flag = 1;
			task.setResult(m);
			deployScheduleDAO.confirmDeploySchedule(id, 2);
		} else if (task.getIsSuccessful() == 6) {
			flag = 1;
			deployScheduleDAO.confirmDeploySchedule(id, 2);
		} else if (task.getIsSuccessful() == 7) {
			flag = 1;
			deployScheduleDAO.confirmDeploySchedule(id, 2);
		} else if (task.getIsSuccessful() == 8) {
			m.add("checkUrl为空");
			flag = 1;
			task.setResult(m);
			deployScheduleDAO.confirmDeploySchedule(id, 2);
		}

		if (task.getResult() != null) {
			List<String> tl = task.getResult();
			for (String s : tl) {
				sb.append(s);
				sb.append("\n");
			}

			String s = sb.toString();
			if (flag == 0) {
				deployScheduleDAO.confirmDeploySchedule(id, 1);
			}

			deployScheduleDAO.persistenceDM(id, s);
		}
	}

	// 根据包名进行部署，并返回包含部署信息的Task对象
	private Task doDirtyJob(String packageName) {

		List<PreTask> preTaskList = deployService.composePreTaskByPackageName(packageName);
		LOG.info("============== DeployScheduleServiceImpl.doDirtyJob =============");
		LOG.info("根据包名查找出的preTaskList为");
		LOG.info(preTaskList.toString());
		LOG.info("============== DeployScheduleServiceImpl.doDirtyJob =============");
		return taskService.deployTask(preTaskList);
	}

	public DeployScheduleDAO getDeployScheduleDAO() {
		return deployScheduleDAO;
	}

	public void setDeployScheduleDAO(DeployScheduleDAO deployScheduleDAO) {
		this.deployScheduleDAO = deployScheduleDAO;
	}

	public DeployService getDeployService() {
		return deployService;
	}

	public void setDeployService(DeployService deployService) {
		this.deployService = deployService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
