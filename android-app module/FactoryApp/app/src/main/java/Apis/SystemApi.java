package Apis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.WaitingThread;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Classes.SystemApiClass;
import Classes.UserApiClass;

public class SystemApi extends AsyncTask<String, Void, ArrayList<SystemApiClass>> {
	private OnTaskComplete listener;
	private ProgressDialog loginProgress;
	private String system_id;

    public SystemApi(OnTaskComplete  listener,String system_id)
    {
        this.listener=listener;
		this.system_id=system_id;

    }
    
    protected void onPreExecute() 
    {
    };

	@Override
	protected ArrayList<SystemApiClass>  doInBackground(String... arg0)

	{ArrayList<SystemApiClass>alldata=new ArrayList<SystemApiClass>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		SystemApiClass systemdata;

		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost("http://gelvano.esy.es/itroom/api/get-data.php");

            /*JSONObject json = new JSONObject();
            json.put("username", arg0[0]);
            json.put("password",arg0[1]);

            //StringEntity se = new StringEntity( json.toString());
            //se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            //request.setEntity(se);
			//hna b7ot user name w pw f methodt authorization
            String authorizationString = "Basic " + Base64.encodeToString(
                    ("tester" + ":" + "tm-sdktest").getBytes(),
                    Base64.NO_WRAP); 
            request.setHeader("Authorization", authorizationString);*/
			//request.setHeader("Content-Type", "application/x-www-form-urlencoded");

			nameValuePairs.add(new BasicNameValuePair("system_id", system_id));
			Log.i("wezzaid",system_id);
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
			Log.i("wezza",response.toString());

			int statusCode=response.getStatusLine().getStatusCode();
			if(statusCode==200) {

				JSONObject jsonObject=new JSONObject(result);
				int error=0;
				   try{ error=jsonObject.getInt("error");}
				catch (Exception e){Log.i("wezza",error+"");}
if(error==0){
					String system_id = jsonObject.getString("id");
					String ac_mode = jsonObject.getString("ac_mode");
					String ac1 = jsonObject.getString("ac1");
					String ac2 = jsonObject.getString("ac2");
					String ac3 = jsonObject.getString("ac3");
					String ac4 = jsonObject.getString("ac4");
	String door_state = jsonObject.getString("door_state");
	String current_temp = jsonObject.getString("current_temp");
	String user_temp = jsonObject.getString("user_temp");
	String system_hash = jsonObject.getString("system_hash");
	String created_at = jsonObject.getString("created_at");
	String updated_at = jsonObject.getString("updated_at");
	Log.i("wezzatemp",current_temp);


	systemdata = new SystemApiClass(system_id,ac_mode,ac1,ac2,ac3,ac4,door_state,current_temp,user_temp,system_hash,created_at,updated_at);
					alldata.add(systemdata);

				return alldata;}
				else if(error==1)
{
	systemdata=new SystemApiClass(error);
	alldata.add(systemdata);
	return alldata;
}

			}
		}
		catch(Exception e)
		{
			Log.d("LoginAPI", "Exception");
			Log.d("Message", e.getMessage());
		}
		return null;
		
	}
	
	@Override
	protected void onPostExecute(ArrayList<SystemApiClass> result)
	{
		try {
			listener.onComplete();


		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.onPostExecute(result);
	}
	
}
