package jp.co.internous.kabuki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import jp.co.internous.kabuki.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.kabuki.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.kabuki.model.session.LoginSession;

@Controller
@RequestMapping("/kabuki/history")
public class PurchaseHistoryController {
	@Autowired
	private LoginSession loginSession;

	@Autowired
	private TblPurchaseHistoryMapper historyMapper;
	
	@RequestMapping("/")
	public String index(Model m) {
		long userId = loginSession.getUserId();
		List<PurchaseHistoryDto> historyList = historyMapper.findByUserId(userId);
		m.addAttribute("historyList", historyList);
		m.addAttribute("loginSession", loginSession);
		
		return "purchase_history";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete() {
		long userId = loginSession.getUserId();
		long result = historyMapper.logicalDeleteByUserId(userId);
		
		return result > 0;
	}
}
	
