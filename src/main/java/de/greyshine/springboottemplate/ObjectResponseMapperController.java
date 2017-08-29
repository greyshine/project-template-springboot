package de.greyshine.springboottemplate;

import java.time.LocalDateTime;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
public class ObjectResponseMapperController {
	
	@GetMapping( value="/getSomeJson1")
	@ResponseBody
	public Result returnSomeObjectAsJson1() {
		return new Result();
	}

	@GetMapping( value="/getSomeJson2", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JsonElement returnSomeObjectAsJson2() {
		
		final JsonObject theResult = new JsonObject();
	
		theResult.addProperty("status" , "OK");
		theResult.addProperty("time" , LocalDateTime.now().toString() );
		
		return theResult;
	}
	
	public static class Result {
		
		public String status = "OK";
		public final String time = LocalDateTime.now().toString();
		
	} 
	
}
