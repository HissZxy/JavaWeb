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
		String restar = "update restart set status = '0'";// ׼��������������StatusΪ0����ʾ������
		try {
			int result = con.executeUpdate(restar);
			System.out.println("��ʼ��������status״̬����Ϊ0����ʾ���������������ˣ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		while (true) {
			while (true) {
				if (status == 2) {// 2����ʾ�رյĳ���ȴ�����
					System.out.println("status״̬Ϊ2����ʾ��Ҫ�����������ɳ���");
					try {
						int result = con.executeUpdate(restar);
						System.out.println("�������Ͼͱ�����������status״̬����Ϊ0����ʾ�����������У�");
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
						System.out.println("��⵱ǰ״̬status:"+status);
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
