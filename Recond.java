package com.app;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import databasetool.DBtool;

public class ReStart implements Runnable {
	int status = 0;

	public void run() {
		DBtool con = new DBtool();
		ResultSet rs = null;
		String select = "select * from restart";
		String restar = "update restart set status = '0'";// 准备启动程序，设置Status为0，表示已启动
		try {
			int result = con.executeUpdate(restar);
			System.out.println("初始化，并将status状态设置为0，表示程序正常被启动了！");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		while (true) {
			while (true) {
				if (status == 2) {// 2：表示关闭的程序等待重启
					System.out.println("status状态为2，表示需要重新启动数采程序！");
					try {
						int result = con.executeUpdate(restar);
						System.out.println("程序马上就被启动，并将status状态设置为0，表示程序正常运行！");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					String cmd = "cmd /c start E:\\Bats\\MainThread.bat";// pass
					try {
						Process ps = Runtime.getRuntime().exec(cmd);
						ps.waitFor();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					rs = con.executeQuery(select);
					while (rs.next()) {
						status = rs.getInt("status");
						System.out.println("检测当前状态status:"+status);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		ReStart res = new ReStart();
		res.run();
	}

}
