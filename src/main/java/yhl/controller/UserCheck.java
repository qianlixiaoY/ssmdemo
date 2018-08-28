package yhl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yhl.dao.model.Msg;
import yhl.service.UserService;

@Controller
public class UserCheck {

	@Autowired 
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/userChe/{username}",method=RequestMethod.POST)
	public Msg checkSameUser(@PathVariable("username")String username){
		System.out.println("要校验的username为:"+username);
		//先判断用户名是否合法
		String regx = "(^[a-zA-Z0-9_-]{3,10}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!username.matches(regx)){
			return Msg.fail().add("va_msg", "用户名必须是3-10的英文数字组合或者2-5位中文数字组合");
		}
		
		boolean b = userService.checkUser(username);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "用户名不可用");
		}
		
	}
}
