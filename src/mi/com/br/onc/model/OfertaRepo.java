package mi.com.br.onc.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mi.com.br.onc.util.DatabaseHelper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;

public class OfertaRepo {
	
	private String URL_SERVER = "http://onc2.azurewebsites.net/api/APIOffer";
	private Context context;
	
	public OfertaRepo(Context context) {
		super();
		this.context = context;
	}
	
	public List<Oferta> getAll(){
		List<Oferta> listaOfertas = new ArrayList<Oferta>();
		Oferta _oferta;
		DatabaseHelper b = new DatabaseHelper(context);
		SQLiteDatabase db = null;
		Cursor _cursor = null;
		db = b.getReadableDatabase();
		
		try {
			_cursor = db.query("Oferta", null, null, null, null, null, null);
			
			if (_cursor.getCount() > 0){
				while(_cursor.moveToNext()){
					_oferta = new Oferta();
					_oferta.Id = _cursor.getLong(0);
					_oferta.Title = _cursor.getString(1);
					_oferta.Description = _cursor.getString(2);
					_oferta.Price = _cursor.getDouble(3);
					_oferta.ImageUrl = _cursor.getString(4);
					_oferta.Lat = _cursor.getDouble(5);
					_oferta.Lon = _cursor.getDouble(6);
					
					listaOfertas.add(_oferta);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		_cursor.close();
		db.close();
		
		return listaOfertas;
	}
	
	public Oferta getByID(Long id){
		Oferta _oferta = new Oferta();
		
		DatabaseHelper b = new DatabaseHelper(context);
		SQLiteDatabase db = null;
		Cursor _cursor = null;
		db = b.getReadableDatabase();
		
		try {
			_cursor = db.query("Oferta", null, "id = "+id, null, null, null, null);
			
			if (_cursor.getCount() > 0){
				while(_cursor.moveToNext()){
					_oferta = new Oferta();
					_oferta.Id = _cursor.getLong(0);
					_oferta.Title = _cursor.getString(1);
					_oferta.Description = _cursor.getString(2);
					_oferta.Price = _cursor.getDouble(3);
					_oferta.ImageUrl = _cursor.getString(4);
					_oferta.Lat = _cursor.getDouble(5);
					_oferta.Lon = _cursor.getDouble(6);
				}
			}
			return _oferta;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			_cursor.close();
			db.close();
		}
	}

	public void addOferta(Oferta item) {
		// TODO Auto-generated method stub
		DatabaseHelper b = new DatabaseHelper(context);
		SQLiteDatabase db = null;
		
		db = b.getWritableDatabase();
		db.beginTransactionNonExclusive();
		ContentValues valores = new ContentValues();
		long count = 0;
		
		try {
			valores.put("id", item.Id);
			valores.put("title", item.Title);
			valores.put("description", item.Description);
			valores.put("price", item.Price);
			valores.put("imageurl", item.ImageUrl);
			valores.put("lat", item.Lat);
			valores.put("lon", item.Lon);
			
			count = db.insert("Oferta", null, valores);
			db.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			db.endTransaction();
		}
		if(db != null){
			db.close();
			b.close();
		}
	}

	public void deleteBanco() {
		// TODO Auto-generated method stub
		DatabaseHelper b = new DatabaseHelper(context);
		SQLiteDatabase db = null;
		long count = 0;
		db = b.getWritableDatabase();
		db.beginTransactionNonExclusive();
		
		try {
			count = db.delete("Oferta", null, null);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			db.endTransaction();
		}
		if(db != null){
			db.close();
			b.close();
		}
	}
}
