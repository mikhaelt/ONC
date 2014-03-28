package mi.com.br.onc.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import mi.com.br.onc.model.Oferta;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import android.util.Log;

public class ONCWSCommunication{

	static String response = null;
	private static final String ERROR = "ONC_error";
	private static final String URL_SERVER = "http://onc2.azurewebsites.net/api/APIOffer";
	
	/***
	 *  USUÁRIO
	 * 
	 */
	//realiza a cadastro de um novo usuário.
	public Oferta[] carregarOfertas(){
		try {
			HttpClient _httpClient = new DefaultHttpClient();
			Gson gson = new Gson();
			String url = URL_SERVER;
			
			HttpGet _httpGet = new HttpGet(url);
			HttpResponse _httpResponse = _httpClient.execute(_httpGet);
					
			HttpEntity _httpEntity = _httpResponse.getEntity();
			InputStream content = _httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(content));
					
			String responseBody = reader.readLine();
			Oferta[] listaOfertas = gson.fromJson(responseBody, Oferta[].class);
			
			return listaOfertas;
			
		} catch (Exception ex) {
			// TODO: handle exception
			Log.e(ERROR, "erro: "+ ex);
			return null;
		}
	}
}
