package Apis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Classes.UserApiClass;

public class UserApi extends AsyncTask<String, Void, ArrayList<UserApiClass>> {
	private OnTaskComplete listener;
	private ProgressDialog loginProgress;
	private String username,password;
	private Context context;

    public UserApi(OnTaskComplete listener, ProgressDialog dialog,String username,String passsword)
    {
        this.listener=listener;
        loginProgress = dialog;
		this.username=username;
		this.password=passsword;
		this.context=context;
    }
    
    protected void onPreExecute() 
    {
    	loginProgress.setMessage("Loading...");

    	loginProgress.setCancelable(false);

    	loginProgress.show();
    };

	@Override
	protected ArrayList<UserApiClass>  doInBackground(String... arg0)
	{ArrayList<UserApiClass>alldata=new ArrayList<UserApiClass>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		UserApiClass userdata;

		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost("http://gelvano.esy.es/itroom/api/login.php");

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

			nameValuePairs.add(new BasicNameValuePair("username", username));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

			int statusCode=response.getStatusLine().getStatusCode();
			if(statusCode==200) {

				JSONObject jsonObject=new JSONObject(result);
				int error=0;
				   try{ error=jsonObject.getInt("error");}
				catch (Exception e){}
if(error==0){
					int id = jsonObject.getInt("id");
					int role = jsonObject.getInt("role");
					int system_id = jsonObject.getInt("system_id");
					String username = jsonObject.getString("username");
					String token = jsonObject.getString("token");
					String password = jsonObject.getString("password");
					 userdata = new UserApiClass(username, password, token, id,system_id,role);//,error);
					alldata.add(userdata);

				return alldata;}
				else if(error==1)
{
	userdata=new UserApiClass(error);
	alldata.add(userdata);
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
	protected void onPostExecute(ArrayList<UserApiClass> result)
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
