package mi.com.br.onc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;

public class FileUtils {
	public static String readRawFile(Context context, int raw) throws IOException {
		if(context == null) {
			return null;
		}
		Resources resources = context.getResources();
		InputStream in = resources.openRawResource(raw);
		String s = IOUtils.toString(in, "UTF-8");
		return s;
	}

	public static List<String> readLines(Context context, int raw) throws IOException {
		if(context == null) {
			return new ArrayList<String>();
		}
		Resources resources = context.getResources();
		InputStream in = resources.openRawResource(raw);
		InputStreamReader reader = new InputStreamReader(in, "UTF-8");
		return readLines(reader);
	}

	/**
	 * Get the contents of a <code>Reader</code> as a list of Strings, one entry
	 * per line.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedReader</code>.
	 * 
	 * @param input
	 *            the <code>Reader</code> to read from, not null
	 * @return the list of Strings, never null
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since Commons IO 1.1
	 */
	public static List<String> readLines(Reader input) throws IOException {
		BufferedReader reader = new BufferedReader(input);
		List<String> list = new ArrayList<String>();
		String line = reader.readLine();
		while (line != null) {
			list.add(line);
			line = reader.readLine();
		}
		return list;
	}
}
