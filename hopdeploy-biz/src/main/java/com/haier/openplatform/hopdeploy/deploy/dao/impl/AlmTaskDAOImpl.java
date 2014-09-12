package com.haier.openplatform.hopdeploy.deploy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.haier.openplatform.hopdeploy.deploy.dao.AlmTaskDAO;
import com.haier.openplatform.hopdeploy.deploy.dao.DAOException;
import com.haier.openplatform.hopdeploy.deploy.domain.AlmTask;
import com.haier.openplatform.hopdeploy.util.DBUtil;

public class AlmTaskDAOImpl implements AlmTaskDAO {

	@Override
	public void save(AlmTask object) {
		// TODO Auto-generated method stub

	}

	@Override
	public int update(AlmTask object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AlmTask get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlmTask> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AlmTask findUniqueBy(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlmTask> searchAlmTask() throws DAOException {
		List<AlmTask> almTaskList = new ArrayList<AlmTask>();
		String sql = "select publish_id, task_id, package_name, is_urgent from DEPLOY_SIMULATION where = '5'";
		Connection con = DBUtil.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				almTaskList.add(assembleAlmTask(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("查询资费数据失败！", e);
		}
		return almTaskList;
	}

	private AlmTask assembleAlmTask(ResultSet rs) throws SQLException {
		AlmTask almTask = new AlmTask();
		almTask.setPublishId(rs.getLong("publish_id"));
		almTask.setTaskId(rs.getLong("task_id"));
		almTask.setPackageName(rs.getString("package_name"));
		almTask.setIsUrgent(rs.getInt("is_urgent"));
		return almTask;
	}
}
