package com.haier.openplatform.hopdeploy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class SshUtil {
	private static Connection conn = null;
	private static Session session = null;
	private static final Log LOG = LogFactory.getLog(SshUtil.class);

	private SshUtil() {
	}

	public static void closeSession() {
		if (session != null) {
			session.close();
			if (conn != null) {
				conn.close();
			}
		} else if (conn != null) {
			conn.close();
		}
	}

	public static List<String> cmd(String ip, String username, String password, String command) {
		List<String> list = new ArrayList<String>();
		conn = new Connection(ip);
		if (conn != null) {
			try {
				conn.connect();
				boolean isAuthenticated = conn.authenticateWithPassword(username, password);
				if (isAuthenticated == false) {
					list.add("服务器口令错误.");
					return list;
				}
				session = conn.openSession();

				session.requestPTY("bash");
				session.startShell();
				InputStream stdout = new StreamGobbler(session.getStdout());
				InputStream stderr = new StreamGobbler(session.getStderr());
				BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
				BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));
				PrintWriter out = new PrintWriter(session.getStdin());
				out.println(command);
				out.println("exit");
				out.close();
				session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS,
						30000);
				LOG.info("下面是从stdout输出:");
				while (true) {
					String line = stdoutReader.readLine();
					if (line == null)
						break;
					list.add(line);
				}
				LOG.info("下面是从stderr输出:");

				while (true) {
					String line = stderrReader.readLine();
					if (line == null)
						break;
					list.add(line);
				}
				System.out.println("ExitCode: " + session.getExitStatus());

			} catch (IOException e) {
				e.printStackTrace(System.err);
				System.exit(2);
			} finally {
				closeSession();
			}
		} else {
			list.add("当前ip连接不可用");
			return list;
		}

		return list;
	}

	public static List<String> cmd(List<String> ipArray, String username, String password, String command) {
		List<String> lists = new ArrayList<String>();
		for (String ip : ipArray) {
			String titleStart = "============================ " + ip + " ===========================\n";
			lists.add(titleStart);
			lists.addAll(cmd(ip, username, password, command));
			String titleEnd = "=============================== END ==============================\n";
			lists.add(titleEnd);
		}
		return lists;
	}
}
