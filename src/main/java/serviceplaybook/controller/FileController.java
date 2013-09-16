package serviceplaybook.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import serviceplaybook.model.FileMeta;
import serviceplaybook.service.FileFormService;

@Controller
@RequestMapping("/file-controller")
public class FileController {
	@Autowired
	FileFormService fileFormService;
	
	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	serviceplaybook.model.FileMeta fileMeta = null;
	/***************************************************
	 * URL: /rest/controller/upload  
	 * upload(): receives files
	 * @param request : MultipartHttpServletRequest auto passed
	 * @param response : HttpServletResponse auto passed
	 * @return LinkedList<FileMeta> as json format
	 ****************************************************/
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public @ResponseBody LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
 
		
		return files; 
	}
	
	@RequestMapping(value = "/remove/{value}", method = RequestMethod.POST) 
	public @ResponseBody LinkedList<FileMeta> remove(HttpServletResponse response,@PathVariable String value){
		System.out.println ("REMOVE " + value);
		files.remove(Integer.parseInt(value));
		return files;
	}
	
	/***************************************************
	 * URL: /rest/controller/get/{value}
	 * get(): get file as an attachment
	 * @param response : passed by the server
	 * @param value : value from the URL
	 * @return void
	 ****************************************************/
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	 public void get(HttpServletResponse response,@PathVariable String value){
		 
		 try {		
			 	fileFormService.get(value,response);
		 }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
	 }
 
}
