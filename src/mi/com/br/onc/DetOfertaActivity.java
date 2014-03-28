package mi.com.br.onc;

import mi.com.br.onc.model.Oferta;
import mi.com.br.onc.model.OfertaRepo;
import mi.com.br.onc.util.WebImages;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class DetOfertaActivity extends Activity {
	
	private static Long id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_det_oferta);
		
		// if has extra();
		Bundle extras = getIntent().getExtras(); 
		if (extras != null){
			id = extras.getLong("id");
		}

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.det_oferta, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_det_oferta,
					container, false);
			
			CarregarCampos(rootView);
			return rootView;
		}

		private void CarregarCampos(View _view) {
			// TODO Auto-generated method stub
			TextView txtTitulo = (TextView) _view.findViewById(R.id.tTituloDet);
			TextView txtPreco = (TextView) _view.findViewById(R.id.tPrecoDet);
			TextView txtDescricao = (TextView) _view.findViewById(R.id.tDescricaoDet);
			ImageView imgLogo = (ImageView) _view.findViewById(R.id.imgOfertaDet);
			
			OfertaRepo _or = new OfertaRepo(getActivity());
			Oferta _oferta = _or.getByID(id);
			
			txtTitulo.setText(_oferta.Title);
			txtPreco.setText("R$ "+_oferta.Price.toString());
			txtDescricao.setText(_oferta.Description);
			
			//determina a imagem.
			WebImages _wi = new WebImages(imgLogo);
			_wi.execute(_oferta.ImageUrl);
		}
	}

}
