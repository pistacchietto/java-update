package update;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
public class DownloadFile {
	public static void GetFile(String url, String dest){
		try{
		URL u = new URL(url);
		try{
	    URLConnection uc = u.openConnection();
		
	    String contentType = uc.getContentType();
	    int contentLength = uc.getContentLength();
	    /*if (contentType.startsWith("text/") || contentLength == -1) {
	      throw new IOException("This is not a binary file.");
	    }*/
	    InputStream raw = uc.getInputStream();
	    InputStream in = new BufferedInputStream(raw);
	    byte[] data = new byte[contentLength];
	    int bytesRead = 0;
	    int offset = 0;
	    while (offset < contentLength) {
	      bytesRead = in.read(data, offset, data.length - offset);
	      if (bytesRead == -1)
	        break;
	      offset += bytesRead;
	    }
	    in.close();

	    if (offset != contentLength) {
	      throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
	    }

	    //String filename = u.getFile().substring(filename.lastIndexOf('/') + 1);
	    FileOutputStream out = new FileOutputStream(dest);
	    out.write(data);
	    out.flush();
	    out.close();
		}catch(IOException ex){
			//do exception handling here
		}
	}catch(MalformedURLException malex){
		//do exception handling here
	}
	}
}
