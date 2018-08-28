package yhl.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import yhl.dao.model.Msg;
import yhl.dao.model.User;
import yhl.service.UserService;

@Controller
@Path("/userRes")
public class UserResource {

	@Autowired 
	private UserService userService;
	
	@POST
	@Consumes
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_ATOM_XML})
	@Path("checkUser")
	public User checkUser(User user){
		List<User> users = this.userService.getUserList();
		if(users!=null && !users.isEmpty()){
			for(User u:users){
				if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())){
					System.out.println("用户名密码正确");
					return u;
				}
			}
		}
		return null;
	}
	
	@GET
	@Consumes
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_ATOM_XML})
	@Path("getAllUser")
	public List<User> getAllUser(){
		List<User> users = this.userService.getUserList();
		return users;
	}
}
