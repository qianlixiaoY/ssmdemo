package yhl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yhl.dao.UserMapper;
import yhl.dao.model.User;
@Service
public class UserServiceImpl2{
	@Autowired
	UserMapper userMapper;
	/**
	 * 查询员工数据(分页查询)
	 * @return
	 */
	public List<User> getAll(){
		System.out.println("查询所有列表数据");
		return userMapper.selectByExample(null);//没有条件，查询所有
	}

}
