package net.h2.web.mob.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.mob.systemkeys.ISystemKeysAPI;
import net.h2.web.mob.systemkeys.SystemKeys;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;




@Component
public class SmsUtil {
	
	private static final String DEFAULT_SMS_URL = "Http://www.sibsms.com/APISend.aspx";
	private static final String DEFAULT_SMS_USERNAME = "09121717974";
	private static final String DEFAULT_SMS_PASSWORD = "123a123A";
	private static final String DEFAULT_SMS_TEXT = "کد رهگیری شما در نرم افزار مباجم :";
	private static final String DEFAULT_SMS_FROM = "-1";
	
	@Autowired
	private ISystemKeysAPI systemKeys;
	
	
	
	@Async
	 public void sendSMS(Long mobile,Integer confirmCode) throws ClientProtocolException, IOException, InterruptedException, BaseServerBusinessException {

		//Thread.sleep(1000);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(systemKeys.getSystemValue(SystemKeys.KEY_SMS_URL, DEFAULT_SMS_URL));

		List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
		urlParameters.add(new BasicNameValuePair("username",systemKeys.getSystemValue(SystemKeys.KEY_SMS_USERNAME, DEFAULT_SMS_USERNAME)));
		urlParameters.add(new BasicNameValuePair("password", systemKeys.getSystemValue(SystemKeys.KEY_SMS_PASSWORD, DEFAULT_SMS_PASSWORD)));
		urlParameters.add(new BasicNameValuePair("to", mobile.toString()));
		urlParameters.add(new BasicNameValuePair("text", systemKeys.getSystemValue(SystemKeys.KEY_SMS_TEXT, DEFAULT_SMS_TEXT) +  confirmCode));
		urlParameters.add(new BasicNameValuePair("From", systemKeys.getSystemValue(SystemKeys.KEY_SMS_FROM, DEFAULT_SMS_FROM)));
		post.setEntity(new UrlEncodedFormEntity(urlParameters,"UTF-8"));

		HttpResponse response = client.execute(post);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader( new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
	}

}
