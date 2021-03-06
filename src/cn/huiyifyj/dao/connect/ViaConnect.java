package cn.huiyifyj.dao.connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.huiyifyj.util.ConnectDb;
import cn.huiyifyj.bean.Via;
import cn.huiyifyj.dao.ViaDao;
import cn.huiyifyj.dao.query.ViaQuery;

public class ViaConnect{

	static Connection conn = null;

	// 插入头像表信息
	public static void save(int userId, String avatar) {
 
		try {

			conn = ConnectDb.getInstance().makeConnection();
			conn.setAutoCommit(false);

			ViaDao viaDao = new ViaQuery();
			Via via = new Via();

			via.setUserId(userId);
			via.setAvatar(avatar);

			viaDao.save(conn, via);

			conn.commit();

		} catch (SQLException e) {

			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} finally {

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	// 查询头像表信息
	public static Map<Object, Object> query(int userId) {

		Map<Object, Object> map = new HashMap<Object, Object>();
		
		try {
			
			conn = ConnectDb.getInstance().makeConnection();
			conn.setAutoCommit(false);

			ViaDao viaDao = new ViaQuery();
			Via via = new Via();
			
			via.setUserId(userId);

			ResultSet rs = viaDao.query(conn, via);
			
			while (rs.next()) {
				map.put("userId", rs.getInt(1));
				map.put("avatar", rs.getString(2));
			}

			conn.commit();
			
		} catch (SQLException e) {
			
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		} finally {
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return map;
	}
	
	// 修改头像表信息
	public static void update(int userId, String avatar) {

		// 暂时没有去完善这个功能，先写着放这
		try {
			
			conn = ConnectDb.getInstance().makeConnection();
			conn.setAutoCommit(false);

			ViaDao viaDao = new ViaQuery();
			Via via = new Via();

			via.setUserId(userId);
			via.setAvatar(avatar);

			viaDao.update(conn, via);
			
			conn.commit();
			
		} catch (SQLException e) {
			
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		} finally {
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}