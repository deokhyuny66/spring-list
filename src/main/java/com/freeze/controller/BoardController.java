package com.freeze.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freeze.dao.BoardDaoImpl;
import com.freeze.model.BoardModel;
import com.freeze.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService service;
	
	BoardModel model = new BoardModel();
	@RequestMapping("pages-account-settings-notifications")
	public String boardList(Model model) throws Exception {
		List<BoardModel> boardList = service.boardList(); 
		model.addAttribute("boardList", boardList);
		return "pages-account-settings-notifications";
	}
	
	@ResponseBody
	@RequestMapping(value = "itemAjax" , method = RequestMethod.POST)
	public Object listOfIndex(@RequestParam(value="click_ItemNm") String clickItemNm) throws Exception {
		List<BoardModel> data = service.listOfIndex(clickItemNm);
		return data;
	}

	@RequestMapping("pages-account-settings-account")
	public String boardRegister(Model model) throws Exception {
		return "pages-account-settings-account";
	}
	
	@RequestMapping("list-register-proc")
	public void boardRegisterProc(@RequestParam(value="maker-param") String makerParam, @RequestParam(value="model-param") String modelParam) throws Exception {
		String redirect_url = null;
		model.setMaker(makerParam);
		model.setModel(modelParam);
		model.setVolumn(modelParam); 
		model.setEa(modelParam);
	  	model.setUse_date(modelParam); 
	  	model.setDetail_contents(modelParam);
	  	model.setImage(modelParam); 
	  	model.setPrice(modelParam);
		service.boardRegister(model);
	}
}