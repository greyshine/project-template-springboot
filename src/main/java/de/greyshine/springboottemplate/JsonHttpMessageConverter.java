package de.greyshine.springboottemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonHttpMessageConverter implements HttpMessageConverter<JsonElement> {
	
	final static Charset UTF8 = Charset.forName( "UTF-8" );
	
	final static List<MediaType> MEDIATTYPES = Collections.unmodifiableList( Arrays.asList( MediaType.APPLICATION_JSON_UTF8 ) );
	
	private final Gson gson;

	public JsonHttpMessageConverter() {
		this( null ); 
	}
	
	public JsonHttpMessageConverter(Gson inGson) {
		gson = inGson != null ? inGson : new GsonBuilder().setPrettyPrinting().create(); 
	}
	
	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return false;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return JsonElement.class.isAssignableFrom( clazz ) && MediaType.APPLICATION_JSON_UTF8.isCompatibleWith( mediaType );
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return MEDIATTYPES;
	}

	@Override
	public JsonObject read(Class<? extends JsonElement> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void write(JsonElement t, MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		
		outputMessage.getHeaders().add( HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		outputMessage.getBody().write( gson.toJson(t).getBytes( UTF8 ) );
	}
}
