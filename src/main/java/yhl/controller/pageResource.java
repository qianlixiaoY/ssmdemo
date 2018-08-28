package yhl.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yhl.dao.model.Msg;
import yhl.dao.model.User;
import yhl.service.UserService;

@Controller
@Path("/pageRes")
public class pageResource {

	@Autowired 
	private UserService userService;
	
//	public PageInfo<User> getAllPage(){
//		PageHelper.startPage(10, 5);
//		List<User> users = this.userService.getUserList();
//		//使用pageInfo包装查询后的信息,只需要将pageinfo交给页面就行了。
//		//封装了详细的分页信息,包括有我们查询出来的数据,传入:连续显示的页数
//		PageInfo<User> page=new PageInfo<User>(users,5); //5表示要连续显示的页数
//		List<User> finallist = page.getList();
//		for(int i = 0;i < finallist.size();i++){
//			System.out.println(finallist.get(i).getUsername()+finallist.get(i).getPassword());
//		}
//		return page;
	@GET
	@Consumes
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_ATOM_XML})
	@Path("getAllPage")
	public Msg getAllPage(){
		PageHelper.startPage(1, 5);
		List<User> users = this.userService.getUserList();
		//使用pageInfo包装查询后的信息,只需要将pageinfo交给页面就行了。
		//封装了详细的分页信息,包括有我们查询出来的数据,传入:连续显示的页数
		PageInfo<User> page=new PageInfo<User>(users,5); //5表示要连续显示的页数
		List<User> finallist = page.getList();
		for(int i = 0;i < finallist.size();i++){
			System.out.println(finallist.get(i).getUsername()+finallist.get(i).getPassword());
		}
		return Msg.success().add("pageInfo", page);
	}
}
