package fi.chaocompany.online.network;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Http {
    public void get(String url, ResponseHandler<?> responseHandler) throws Exception {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet(url);
            httpclient.execute(httpget, responseHandler);
        }
    }
}
