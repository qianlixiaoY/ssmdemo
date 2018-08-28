package yhl.controller;

import javax.ws.rs.Consumes;
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
@Path("/userUpd")
public class UserUpdate {

	//员工修改
	@Autowired
	private UserService userService;
	
	@POST
	@Consumes
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_ATOM_XML})
	@Path("updateUser")
	public Msg updateUser(Integer id){
		User user = userService.getUser(id);
		return Msg.success().add("updateUser",user);
	}
	
	
	//关于put方法
	//问题：
	//请求体中有数据:
	//但是User对象封装不上:
	//update user where id = ;
	//原因:
	//tomcat:
	//1、将请求体中的数据，封装成一个map
	//2、request.getParameter("username")就会从这个map中取值。
	//3、Springmvc封装POJO对象的时候。会把POJO中每个属性的值，
	//request.getParameter("age")
	//AJAX发送put请求引发的血案
	//PUT请求，骑牛体重的数据，request.getParameter("age")拿不到
	//根本原因是，tomcat一看是put请求不回封装请求体中的数据为map,
	//只有post形式的请求才可以封装请求体为map
	//org.apache.catalina.connector.Request--parseParameters()
	//员工修改后保存
	//解决方案:
	//我们要能直接发送put之类的请求还要封装请求体中的数据
	//1、配置上HttpPutFormContentFilter
	//2、他的作用：将请求体中的数据解析包装成一个map
	//3、request被重新包装，request.getParameter
	//被重写，就会从自己封装的map中
	@POST
	@Consumes
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_ATOM_XML})
	@Path("saveUser")
	public Msg saveUser(User user){
		//spring mvc将提交过来的数据进行封装，封装之后，调用员工的更新方法
		System.out.println("将要更新的user数据:"+user);
		userService.updateUser(user);
		return Msg.success();
	}
}
