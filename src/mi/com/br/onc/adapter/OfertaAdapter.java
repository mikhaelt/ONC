package mi.com.br.onc.adapter;

import java.util.List;

import mi.com.br.onc.R;
import mi.com.br.onc.R.layout;
import mi.com.br.onc.model.Oferta;
import mi.com.br.onc.model.OfertaRepo;
import mi.com.br.onc.util.WebImages;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class OfertaAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Activity activity;
	private List<Oferta> listaOfertas;
	
	public OfertaAdapter(Activity activity) {
		super();
		inflater = LayoutInflater.from(activity);
		this.activity = activity;
		
		OfertaRepo _or = new OfertaRepo(activity);
		listaOfertas = _or.getAll();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaOfertas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if(listaOfertas.get(position) != null ){
			return listaOfertas.get(position).Id;
		} else {
			return 0;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final View view = inflater.inflate(R.layout.adapter_oferta, parent, false);
		final Oferta _oferta = listaOfertas.get(position);
		
		ImageView imgOferta = (ImageView) view.findViewById(R.id.imgOferta);
		TextView txtOferta = (TextView) view.findViewById(R.id.tTituloOferta);
		
		WebImages _webImg = new WebImages(imgOferta);
		_webImg.execute(_oferta.ImageUrl);
		
		txtOferta.setText(_oferta.Title);
		
		return view;
	}
	

}
