package mi.com.br.onc.service;

import java.util.List;

import mi.com.br.onc.model.Oferta;
import mi.com.br.onc.model.OfertaRepo;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

public class ONCService extends Service implements Runnable{

	private boolean ativo;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		ativo = true;
		new Thread(this, "ONCService"+ startId).start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		ativo = false;
		super.onDestroy();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(ativo){
			if(hasConnection()){
				
				if (verificaOfertas()){
					carregaOfertas();
				}
				
				
				stopSelf();
			} else{
				stopSelf();
			}
		} else {
			stopSelf();
		}
	}
	
	private boolean verificaOfertas() {
		// TODO Auto-generated method stub
		OfertaRepo _or = new OfertaRepo(ONCService.this);
		_or.deleteBanco();
		return true;
	}

	private void carregaOfertas() {
		// TODO Auto-generated method stub
		ONCWSCommunication oncwsc = new ONCWSCommunication();
		
		try {
			Oferta[] ofertas = oncwsc.carregarOfertas();
			if(ofertas.length > 0){
				for(Oferta item : ofertas){
					OfertaRepo _or = new OfertaRepo(ONCService.this);
					_or.addOferta(item);
				}
			}
		} catch (Exception e) {
			
		}
	}

	private boolean hasConnection() {
		// TODO Auto-generated method stub
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		
		NetworkInfo ni = connMgr.getActiveNetworkInfo();
		if (ni == null){
			return false;
		} else {
			return true;
		}
	}
}
