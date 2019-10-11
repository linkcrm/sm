package com.owen.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.owen.bean.User;
import com.owen.dao.UserDao;
import com.owen.util.DateUtil;
import com.owen.util.Page;
import com.owen.util.StringUtil;

/**
 * Created by tengj on 2017/4/8.
 */
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int add(User user) {
		return jdbcTemplate.update("insert into sm.t_user(f_user_name,f_password,f_create_time) values(?,?,?)",
				user.getFUserName(), user.getFPassword(), DateUtil.getYYYYMMDDHHMISS());
	}

	public int update(User user) {
		return jdbcTemplate.update("update sm.t_user set f_user_name=?,f_password=?, where f_user_id = ?",
				new Object[] { user.getFUserName(), user.getFPassword(), user.getFUserId() });
	}

	@Override
	public int updatePw(int userId, String pw) {
		return jdbcTemplate.update("update sm.t_user set f_password=? where f_user_id = ?",
				new Object[] { pw, userId });
	}

	public int deleteById(int id) {
		return jdbcTemplate.update("delete from sm.t_user where f_user_id = ?", id);
	}

	public User queryLearnResouceById(int id) {
		List<User> list = jdbcTemplate.query("select * from  sm.t_user where f_user_id = ?", new Object[] { id },
				new BeanPropertyRowMapper<User>(User.class));
		if (null != list && list.size() > 0) {
			User user = list.get(0);
			return user;
		} else {
			return null;
		}
	}

	public List<User> queryAllUsers() {
		List<User> list = jdbcTemplate.query("select * from  sm.t_user order by f_user_id desc limit 1000",
				new BeanPropertyRowMapper<User>(User.class));
		return list;
	}

	@Override
	public List<User> queryAllUsers(String userName) {
		String sql = "select * from sm.t_user where  1=1 ";
		List <Object> queryList=new  ArrayList<Object>();
        if ( !StringUtil.isEmpty(userName) && ! "".equals(userName.trim()) ) {
                       sql += " and f_user_name like ? ";
            queryList.add("%" + userName + "%");
        }
        sql += " order by f_user_id desc limit 1000";
		List<User> list = jdbcTemplate.query(sql,queryList.toArray(), new BeanPropertyRowMapper<User>(User.class));
		return list;
	}

	public Page queryLearnResouceList(Map<String, Object> params) {
//        StringBuffer sql =new StringBuffer();
//        sql.append("select * from learn_resource where 1=1");
//        if(!StringUtil.isNull((String)params.get("author"))){
//            sql.append(" and author like '%").append((String)params.get("author")).append("%'");
//        }
//        if(!StringUtil.isNull((String)params.get("title"))){
//            sql.append(" and title like '%").append((String)params.get("title")).append("%'");
//        }
//        Page page = new Page(sql.toString(), Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
//        return page;
		return null;
	}

	public User queryUserByAccount(String fUserName) {
		List<User> list = jdbcTemplate.query("select * from sm.t_user where f_user_name = ?",
				new Object[] { fUserName }, new BeanPropertyRowMapper<User>(User.class));

		if (null != list && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
