package yhl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yhl.dao.UserMapper;
import yhl.dao.model.User;
import yhl.dao.model.UserExample;
import yhl.dao.model.UserExample.Criteria;
import yhl.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserMapper userMapper;

	public List<User> getUserList() {
		System.out.println("user service执行");
		//拼接字符串
		UserExample example=new UserExample();
		example.createCriteria().andIdIsNotNull();//设置查询条件
		List<User> users=this.userMapper.selectByExample(example);
		System.out.println("users的数据"+users.size());
		return users;
	}

	public void addUser(User user) {
		System.out.println("要向usermapper中添加数据了"+user.getUsername()+user.getPassword());
		userMapper.insert(user);
	}
	/*检验前端输入的用户名是否可用
	 * true:代表当前username可用
	 */
	
	public boolean checkUser(String username) {
		System.out.println("当前输入的username为:"+username);
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		System.out.println(criteria.andUsernameEqualTo(username)+">>>>>");
		System.out.println(userMapper.countByExample(example));
		long count = userMapper.countByExample(example);
		System.out.println("有重名吗 "+count);
		return count == 0?true:false;
	}

	//按照员工id查询员工
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	//员工更新方法
	public void updateUser(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	//员工删除
	public void deleteUser(Integer id) {
		userMapper.deleteByPrimaryKey(id);
		
	}

	//员工批量删除
	public void deleteBatch(List<Integer> ids) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		userMapper.deleteByExample(example);
	}

}
