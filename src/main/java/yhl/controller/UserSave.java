package yhl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import yhl.dao.model.Msg;
import yhl.dao.model.User;
import yhl.service.UserService;

@Controller
@Path("/userSav")
public class UserSave {

	/*
	 * 员工保存
	 * 1、支持JSR303后端校验aaaaaaaaaaaaaaaaaaaaaaaaaaaa
	 * 2、导入Hibernate-Validator
	 * 
	 * */
	
	@Autowired 
	private UserService userService;
	
	@POST
	@Consumes
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_ATOM_XML})
	@Path("addUser")
	public Msg addUser(User user){
		//后端校验目前还有问题
		/*if(result.hasErrors()){
			Map<String,Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError:errors){
				System.out.println("错误字段名:"+fieldError.getField());
				System.out.println("错误信息:"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorField", map);//校验失败就返回，在模态框中返回失败信息失败
		}else{*/
			userService.addUser(user);
			return Msg.success();
		//}
	}
}
