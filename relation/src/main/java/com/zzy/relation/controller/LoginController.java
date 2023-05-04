package com.zzy.relation.controller;

import com.zzy.relation.entity.Admin;
import com.zzy.relation.entity.User;
import com.zzy.relation.service.AdminService;
import com.zzy.relation.service.AuthorityService;
import com.zzy.relation.service.UserService;
import com.zzy.relation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 系统操作控制器
 */
@RestController
public class LoginController {

	// 依赖注入
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;

	/**
	 * 去登录
	 */
	@RequestMapping(value = "/")
	public String gologin() {
		return "login";
	}

	/**
	 * 登录
	 */
	@PostMapping("adminLogin")
	public Result adminLogin(@RequestBody HashMap<String, String> map, HttpSession session) {
		if(map.get("role").equals("01")){
			Admin admin = new Admin();
			admin.setUsername(map.get("username"));
			admin.setPassword(map.get("password"));
			List<Admin> admins = adminService.queryCondition(admin);
			if (admins != null && admins.size()>0) {
				User user = new User();
				user.setId(admins.get(0).getId());
				user.setUsername(admins.get(0).getUsername());
				user.setPassword((admins.get(0).getPassword()));
				session.setAttribute("userInfo", user);
				session.setAttribute("type", "01");
				return Result.success("管理员登录成功", user);
			}
		}else{
			User user = new User();
			user.setPhone(map.get("username"));
			user.setPassword(map.get("password"));
			List<User> users = userService.queryCondition(user);
			if (users != null && users.size()>0) {
				session.setAttribute("userInfo", users.get(0));
				session.setAttribute("type", "02");
				return Result.success("用户登录成功", users.get(0));
			}
		}
		return Result.error("登录信息错误");
	}

	/**
	 * 用户注册
	 */
	@PostMapping("register")
	public Result register(@RequestBody HashMap<String, String> map) {
		if (!map.get("password").equals(map.get("repassword")))
			return Result.error("请输入两次相同密码");
		User user = new User();
		String phone = map.get("phone");
		String password = map.get("password");
		String did = map.get("did");
		String name=map.get("name");
		String sex=map.get("sex");
		user.setPhone(phone);
		user.setDid(did);
		user.setPassword(password);
		user.setRealname(name);
		user.setSex(sex);
		List<User> userList = userService.queryCondition(new User());
		for(int i=0;i<userList.size();i++){
			if(user.getPhone().equals(userList.get(i).getPhone())){
				return Result.error("手机号已注册，请重试！");
			}
		}
		Date date = new Date();
		user.setCreateTime(date);
		userService.insert(user);
		return Result.success("注册成功");
	}



}
