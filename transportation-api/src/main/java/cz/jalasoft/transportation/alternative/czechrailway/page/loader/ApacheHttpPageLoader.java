package cz.jalasoft.transportation.alternative.czechrailway.page.loader;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-06.
 */
public final class ApacheHttpPageLoader implements PageLoader {

    public static ApacheHttpPageLoader defaultHttpClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return customHttpClient(httpClient);
    }

    public static ApacheHttpPageLoader customHttpClient(CloseableHttpClient httpClient) {
        return new ApacheHttpPageLoader(httpClient);
    }

    //----------------------------------------------------------------------------
    //INSTANCE SCOPE
    //----------------------------------------------------------------------------

    private CloseableHttpClient httpClient;

    private ApacheHttpPageLoader(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public String postForm(String uri, List<FormParameter> parameters) throws IOException {
        HttpPost request = new HttpPost(uri);

        List<NameValuePair> pairs = parameters.stream()
                .map(p -> new BasicNameValuePair(p.name(), p.value()))
                .collect(toList());

        HttpEntity formEntity = new UrlEncodedFormEntity(pairs);
        request.setEntity(formEntity);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            assertStatusCode(uri, response);

            String payloadAsString = EntityUtils.toString(response.getEntity());
            return payloadAsString;
        }
    }

    private void assertStatusCode(String uri, CloseableHttpResponse response) throws IOException {
        StatusLine statusLine = response.getStatusLine();

        if (statusLine.getStatusCode() != 200) {
            throw new IOException("Server for uri " + uri + " responded with status " + statusLine.getStatusCode() + " and reason: " + statusLine.getReasonPhrase());
        }
    }

    @Override
    public String get(String uri) throws IOException {

        HttpGet request = new HttpGet(uri);

        try(CloseableHttpResponse response = httpClient.execute(request)) {
            assertStatusCode(uri, response);

            String payloasAsString = EntityUtils.toString(response.getEntity());
            return payloasAsString;
        }
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }
}
