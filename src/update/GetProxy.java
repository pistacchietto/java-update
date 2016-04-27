package update;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

public class GetProxy {

    public static String GetParameters () {
    	String sPar="";
        try {
            System.setProperty("java.net.useSystemProxies","true");
            List<Proxy> l = ProxySelector.getDefault().select(
                        new URI("http://www.yahoo.com/"));

            for (Iterator<Proxy> iter = l.iterator(); iter.hasNext(); ) {

                Proxy proxy = iter.next();

                System.out.println("proxy hostname : " + proxy.type());

                InetSocketAddress addr = (InetSocketAddress)proxy.address();

                if(addr == null) {
                	sPar="No Proxy";
                    //System.out.println("No Proxy");

                } else {
                	sPar=addr.getHostName()+":"+addr.getPort();
                    //System.out.println("proxy hostname : " + addr.getHostName());
                    //System.out.println("proxy port : " + addr.getPort());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sPar;
    }
}
