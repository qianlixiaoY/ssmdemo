package yhl.service;

import java.util.List;

import yhl.dao.model.User;

public interface UserService {
	List<User> getUserList();

	void addUser(User user);

	boolean checkUser(String username);

	User getUser(Integer id);

	void updateUser(User user);

	void deleteUser(Integer id);

	void deleteBatch(List<Integer> ids);
}
