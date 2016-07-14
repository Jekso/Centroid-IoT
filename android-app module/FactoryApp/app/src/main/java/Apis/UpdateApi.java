package Apis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Classes.UserApiClass;

public class UpdateApi extends AsyncTask<String, Void, String> {
	private OnTaskComplete listener;
	private ProgressDialog loginProgress;
	private String system_id,user_temp,ac_mode,ac1,ac2,ac3,ac4;

    public UpdateApi(OnTaskComplete listener, ProgressDialog dialog, String system_id, String user_temp,String ac_mode)
    {
        this.listener=listener;
        loginProgress = dialog;
		this.system_id=system_id;
		this.user_temp=user_temp;
		this.ac_mode=ac_mode;
    }

	public UpdateApi(OnTaskComplete listener, ProgressDialog loginProgress, String system_id, String user_temp, String ac_mode, String ac1, String ac2, String ac3, String ac4) {
		this.listener = listener;
		this.loginProgress = loginProgress;
		this.system_id = system_id;
		this.user_temp = user_temp;
		this.ac_mode = ac_mode;
		this.ac1 = ac1;
		this.ac2 = ac2;
		this.ac3 = ac3;
		this.ac4 = ac4;
	}

	protected void onPreExecute()
    {
    	loginProgress.setMessage("Loading...");

    	loginProgress.setCancelable(false);

    	loginProgress.show();
    };

	@Override
	protected String  doInBackground(String... arg0)
	{
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		UserApiClass userdata;

		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost("http://gelvano.esy.es/itroom/api/update-data.php");

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
			nameValuePairs.add(new BasicNameValuePair("user_temp", user_temp));
			nameValuePairs.add(new BasicNameValuePair("ac_mode", ac_mode));
			if(ac_mode.equals("1"))
			{
			    nameValuePairs.add(new BasicNameValuePair("ac1", ac1));
			    nameValuePairs.add(new BasicNameValuePair("ac2", ac2));
				nameValuePairs.add(new BasicNameValuePair("ac3", ac3));
				nameValuePairs.add(new BasicNameValuePair("ac4", ac4));

		}

			request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

			int statusCode=response.getStatusLine().getStatusCode();
			if(statusCode==200) {
return result;


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
	protected void onPostExecute(String result)
	{
		loginProgress.dismiss();
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
