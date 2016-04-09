package com.web.anuyog.RestApplication.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.anuyog.RestApplication.domain.Balance;
import com.web.anuyog.RestApplication.domain.User;
import com.web.anuyog.RestApplication.services.BalanceService;
import com.web.anuyog.RestApplication.services.UserService;

@Controller
public class UserController {

	@RequestMapping("/")
	public String index() {
		return "index";

	}

	@RequestMapping("/newUser")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "userReg";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute User user) {

		List<User> list = userService.findByAccNum(user.getAccNum());

		if (list.isEmpty()) {
			Balance userBal = new Balance();
			userBal.setUser(user);
			userService.save(user);
			balService.save(userBal);
			return "success";
		}

		else

			return "failure";

	}

	@RequestMapping("/login")
	public String login() {

		return "login";
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	@ResponseBody
	@Transactional
	public String withdraw(@RequestBody Map<String, String> payload) throws Exception {
		double amount = Double.parseDouble(payload.get("amount"));
		double bal = 0.00;

		int accNum = Integer.parseInt(payload.get("account"));

		List<User> list = userService.findByAccNum(accNum);
		User user = list.get(0);

		List<Balance> userBal = balService.findByUser(user);
		Balance balance = userBal.get(0);

		bal = balance.getBalance() - amount;

		if (bal > 0) {
			balance.setBalance(bal);
			balService.save(balance);

			return "OK";
		} else
			return "ERROR";
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	@ResponseBody
	@Transactional
	public String deposit(@RequestBody Map<String, String> payload) throws Exception {
		double amount = Double.parseDouble(payload.get("amount"));
		double bal = 0.00;

		int accNum = Integer.parseInt(payload.get("account"));

		List<User> list = userService.findByAccNum(accNum);
		User user = list.get(0);

		List<Balance> userBal = balService.findByUser(user);
		Balance balance = userBal.get(0);

		bal = balance.getBalance() + amount;

		balance.setBalance(bal);
		balService.save(balance);

		return "OK";

	}

	@RequestMapping("/logout")
	public String logout() {

		return "logout";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	@ResponseBody
	public String signin(@RequestBody Map<String, String> payload) throws Exception {
		int acc = Integer.parseInt(payload.get("acc"));
		int pin = Integer.parseInt(payload.get("pin"));
		int upin = 0;
		List<User> list = userService.findByAccNum(acc);

		for (java.util.Iterator<User> iter = list.iterator(); iter.hasNext();) {

			User usr = iter.next();
			upin = usr.getPinNum();
		}

		if (upin == pin)
			return "OK";
		else
			return "ERROR";

	}

	@RequestMapping("/signin-success")
	public String signin_success() {
		return "signinSuccess";

	}

	@Autowired
	private UserService userService;
	@Autowired
	private BalanceService balService;
}