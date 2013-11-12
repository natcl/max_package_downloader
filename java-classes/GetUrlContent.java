import com.cycling74.max.*;
import java.net.*;
import java.io.*;

public class GetUrlContent extends MaxObject
{

	private String _url = null;

	private static final String[] INLET_ASSIST = new String[]{
		"inlet 1 help"
	};
	private static final String[] OUTLET_ASSIST = new String[]{
		"outlet 1 help"
	};
	
	public GetUrlContent(String url)
	{
		declareInlets(new int[]{DataTypes.ALL});
		declareOutlets(new int[]{DataTypes.ALL});
		
		setInletAssist(INLET_ASSIST);
		setOutletAssist(OUTLET_ASSIST);
		
		_url = url;
	}

	public void url(String url)
	{	
		_url = url;
	}
    
	public void bang()
	{
		String content = getText(_url);
		outlet(0,content);
	}
    
	public void inlet(int i)
	{
	}
    
	public void inlet(float f)
	{
	}
    
    
	public void list(Atom[] list)
	{
	}

    public static String getText(String url){
		try
		{ 
			URL website = new URL(url);
        	URLConnection connection = website.openConnection();
        	BufferedReader in = new BufferedReader(
           	                     new InputStreamReader(
           	                         connection.getInputStream(), "UTF-8"));

        	StringBuilder response = new StringBuilder();
        	String inputLine;

        	while ((inputLine = in.readLine()) != null) 
            	response.append(inputLine);

        	in.close();

        	return response.toString();
		} 
		catch(Exception e){
			return e.getMessage();
		}

    }
     
}










