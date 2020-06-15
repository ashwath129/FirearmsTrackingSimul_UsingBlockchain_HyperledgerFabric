package jbr.springmvc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.protobuf.InvalidProtocolBufferException;

import jbr.springmvc.sdk.sdksample;
import jbr.springmvc.service.firearmsService;
import jbr.springmvc.setup.BlockChainHFClient;

@Controller
public class HelloController {
	
	@Autowired
	firearmsService faService;
	
	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() throws CryptoException,
			InvalidArgumentException, IllegalAccessException,
			InstantiationException, ClassNotFoundException,
			NoSuchMethodException, InvocationTargetException,
			TransactionException, ProposalException {
		BlockChainHFClient.getInstance().setupCrypto();
		BlockChainHFClient.getInstance().getChannel();
	}
	
	@RequestMapping(value = "/")
	public ModelAndView login() {
		ModelAndView l_objLoginPage = new ModelAndView("/login");
		return l_objLoginPage;
	}

	@RequestMapping("/firearms")
	public ModelAndView firearms(HttpSession sess) throws Exception {
		ModelAndView l_objOrgPage = new ModelAndView("/firearmsmanu");
		Object uname = (String) sess.getAttribute("username");
		int noOfBlocks = faService.getNumberOfBlocks() - 1;
		l_objOrgPage.addObject("noOfBlocks", noOfBlocks);	
        return l_objOrgPage;
	}
	
	@RequestMapping("/retail")
	public ModelAndView retail(HttpSession sess) throws Exception {
		ModelAndView l_objOrgPage = new ModelAndView("/retail");
		Object uname = (String) sess.getAttribute("username");
		int noOfBlocks = faService.getNumberOfBlocks() - 1;
		l_objOrgPage.addObject("noOfBlocks", noOfBlocks);
        return l_objOrgPage;
		//return new ModelAndView("welcome", "message", queryResp);
	}
	
	@RequestMapping("/police")
	public ModelAndView police(HttpSession sess) throws Exception {
		ModelAndView l_objOrgPage = new ModelAndView("/police");
		Object uname = (String) sess.getAttribute("username");
		int noOfBlocks = faService.getNumberOfBlocks() - 1;
		l_objOrgPage.addObject("noOfBlocks", noOfBlocks);
        return l_objOrgPage;
		//return new ModelAndView("welcome", "message", queryResp);
	}
	
	

	
	
	@RequestMapping(value = "/validate")
	public String validateLogin(@RequestParam String p_sUsername,
			@RequestParam String p_sPassword, HttpSession session) {
		if (faService.validateLogin(p_sUsername, p_sPassword)) {

			if (p_sUsername.equals("retail")) {
				session.setAttribute("username", p_sUsername);
				return "redirect:/retail";
			}
			if (p_sUsername.equals("firearms")) {
				session.setAttribute("username", p_sUsername);
				return "redirect:/firearms";
			}
			if (p_sUsername.equals("police")) {
				session.setAttribute("username", p_sUsername);
				return "redirect:/police";
			}
			return "redirect:/";

		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView l_objLoginPage = new ModelAndView("/login");
		session.removeAttribute("username");
		session.invalidate();
		return l_objLoginPage;
	}

	
	@RequestMapping(value = "/blocksModal")
	public @ResponseBody ArrayList<String> getBlockInfoStateBasic(@RequestParam int blockno)
			throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidArgumentException, ProposalException, IOException, CryptoException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, TransactionException {
		//String Binfo = faService.getBlockBasicInfoAlert(blockno);   
		ArrayList<String> info  = faService.getBlockCompleteInfo(blockno);
		System.out.println(info);
		return info;
	}
	
	@RequestMapping(value = "/createGun")
	public @ResponseBody String createGun(
			@RequestParam String id, @RequestParam String make,
			@RequestParam String model, @RequestParam String type,
			@RequestParam String valid)
			throws Exception {

		 int status = faService.createGun(id,make,model,type,valid);
		 String statusReturn = Integer.toString(status);
		 System.out.println(statusReturn);
		 return statusReturn;
		
	}
	
	@RequestMapping(value = "/queryGuns")
	public @ResponseBody String queryGuns()
			throws Exception {
        
		
		String gunsData = faService.queryAllGuns();
         System.out.println(gunsData);
		 return gunsData;
		
	}
	

	
	
	
	
}
