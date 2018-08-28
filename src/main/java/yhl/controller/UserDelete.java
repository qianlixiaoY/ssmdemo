package yhl.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yhl.dao.model.Msg;
import yhl.service.UserService;

@Controller
public class UserDelete {


	@Autowired 
	private UserService userService;
	
	
	/*
	 * 单个批量二合一
	 * 批量删除：1-2-3
	 * 单个删除：1
	 * */
	@ResponseBody
	@RequestMapping(value="/userDel/{ids}",method=RequestMethod.DELETE)
	public Msg deleteUserById(@PathVariable("ids")String ids){
		if(ids.contains("-")){
			//批量删除
			String[] str_ids = ids.split("-");
			//组装id的集合
			List<Integer> del_ids = new ArrayList<>();
			for(String item:str_ids){
				del_ids.add(Integer.parseInt(item));
			}
			userService.deleteBatch(del_ids);
		}else{
			//单个删除
			Integer id = Integer.parseInt(ids);
			userService.deleteUser(id);
		}
		return Msg.success();
	}
}
