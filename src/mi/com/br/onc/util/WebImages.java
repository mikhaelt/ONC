package mi.com.br.onc.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class WebImages extends AsyncTask<String, Void, Bitmap>{

		private ImageView imagem;
		
		public WebImages(ImageView imagem) {
			super();
			this.imagem = imagem;
		}

		@Override
		protected Bitmap doInBackground(String... urls) {
			// TODO Auto-generated method stub
			Bitmap map = null;
			for (String url : urls){
				map = downloadImage(url);
			}
			return map;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			imagem.setImageBitmap(result);
		}

		private Bitmap downloadImage(String url) {
			// TODO Auto-generated method stub
			Bitmap bitmap = null;
			InputStream stream = null;
			BitmapFactory.Options bmOptions = new BitmapFactory.Options();
			bmOptions.inSampleSize = 1;
			
			try {
				stream = getHttpConnection(url);
				bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
				stream.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return bitmap;
		}

		private InputStream getHttpConnection(String urlString) throws IOException{
			// TODO Auto-generated method stub
			InputStream stream = null;
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			
			try {
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				httpConnection.setRequestMethod("GET");
				httpConnection.connect();
				
				if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
					stream = httpConnection.getInputStream();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return stream;
		}
		
}

