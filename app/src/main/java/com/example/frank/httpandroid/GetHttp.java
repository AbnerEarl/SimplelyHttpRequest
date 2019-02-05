package com.example.frank.httpandroid;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by Frank on 2018/3/28.
 */

public class GetHttp {
    public static String sendPostMethod(String path,String encodeing){
        String result="";
        HttpClient httpClient=new DefaultHttpClient();

        try {
            HttpPost post=new HttpPost(path);
            HttpResponse response=httpClient.execute(post);
            if (response.getStatusLine().getStatusCode()==200){
                result=EntityUtils.toString(response.getEntity(),encodeing);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }
}
