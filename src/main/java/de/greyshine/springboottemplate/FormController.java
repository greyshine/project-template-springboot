package de.greyshine.springboottemplate;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class FormController {
	
	public final static Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	
	@PostMapping( value="/form-submit", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String submitForm(@RequestParam("field") String inField, @RequestParam("fields")String[] inFields) {
		
		JsonObject jo = new JsonObject();
		jo.addProperty( "field" , inField);
		jo.addProperty( "fields" , Arrays.asList( inFields ).toString() );
		
		return GSON.toJson( jo );
	}

	@PostMapping( value="/mp-form-submit", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String submitForm2( @RequestParam( name="field", required=false ) String inField, @RequestParam(value="fields", required=false)String[] inFields, @RequestParam(value="file",required=false) MultipartFile inFileUpload ) {
		
		String theFields = Arrays.asList( inFields == null ? new String[0] : inFields ).toString();
		
		String fileData = null;
		
		try {
			
			fileData = inFileUpload == null ? null : new String(inFileUpload.getBytes());
		
		} catch (IOException e) {
			
			System.out.println( e );
		}
		
		JsonObject jo = new JsonObject();
		jo.addProperty( "field" , inField);
		jo.addProperty( "fields" , theFields);
		jo.addProperty( "file" , fileData);
		jo.addProperty( "nada" , (String)null);
		
		return GSON.toJson( jo );
	}

	@PostMapping( value="/form-submit-model", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String submitForm2( SomeModel inModel ) {
		
		JsonObject jo = new JsonObject();
		jo.addProperty( "status" , "OK");
		jo.addProperty( "model-toString" , String.valueOf( inModel ));
		jo.add( "model" , GSON.toJsonTree(inModel) );
		
		return GSON.toJson( jo );
	}
	
	@PostMapping( value="/secured/entity-post-simple")
	@ResponseBody
	public void postEntityWithCredentialsSimple(@RequestBody(required=true) String inJson) {
		System.out.println( new JsonParser().parse( inJson ) );
	}

	@PostMapping( value="/secured/entity-post-model")
	@ResponseBody
	public void postEntityWithCredentialsSimple(@RequestBody(required=true) SomeModel inModel) {
		System.out.println( inModel );
	}

	/**
	 * must be static
	 */
	public static class SomeModel {
		
		private String value;
		
		/**
		 * must have a setter!
		 * @param value
		 */
		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Model: "+ value;
		}
	}
	
}
