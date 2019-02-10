package es.ucm.fdi.tfg.app.vuforia.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import es.ucm.fdi.tfg.app.vuforia.service.SignatureBuilder;

public class Vuforia {

    private static String url = "https://vws.vuforia.com";

    private String serverAccessKey;
    private String serverSecretKey;

    public Vuforia(String serverAccessKey, String serverSecretKey) {
        this.serverAccessKey = serverAccessKey;
        this.serverSecretKey = serverSecretKey;
    }

    public JSONObject getTargets() throws URISyntaxException, ClientProtocolException, IOException {
        HttpGet getRequest = new HttpGet();
        HttpClient client = new DefaultHttpClient();
		getRequest.setURI(new URI(url + "/targets"));
		setHeaders(getRequest);
		
        HttpResponse response = client.execute(getRequest);
        return new JSONObject(EntityUtils.toString(response.getEntity()));
    }

	public JSONObject getTarget(String targetId) throws URISyntaxException, ClientProtocolException, IOException {
		HttpGet getRequest = new HttpGet();
		HttpClient client = new DefaultHttpClient();
		getRequest.setURI(new URI(url + "/targets/" + targetId));
		setHeaders(getRequest);
		
        HttpResponse response = client.execute(getRequest);
        return new JSONObject(EntityUtils.toString(response.getEntity()));
	}
	
	private void setHeaders(HttpUriRequest request) {
		SignatureBuilder sb = new SignatureBuilder();
		request.setHeader(new BasicHeader("Date", DateUtils.formatDate(new Date()).replaceFirst("[+]00:00$", "")));
		request.setHeader("Authorization", "VWS " + serverAccessKey + ":" + sb.tmsSignature(request, serverSecretKey));
	}

}