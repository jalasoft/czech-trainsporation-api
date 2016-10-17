package cz.jalasoft.transportation.alternative.czechrailway.content;

import cz.jalasoft.transportation.alternative.czechrailway.ContentNotAvailableException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
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
public final class ApacheHttpClientContentProvider implements ContentProvider {

    public static ApacheHttpClientContentProvider defaultHttpClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return customHttpClient(httpClient);
    }

    public static ApacheHttpClientContentProvider customHttpClient(CloseableHttpClient httpClient) {
        return new ApacheHttpClientContentProvider(httpClient);
    }

    //----------------------------------------------------------------------------
    //INSTANCE SCOPE
    //----------------------------------------------------------------------------

    private CloseableHttpClient httpClient;

    private ApacheHttpClientContentProvider(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public PageContent postForm(String uri, List<FormParameter> parameters) throws IOException, ContentNotAvailableException {
        HttpPost request = new HttpPost(uri);

        List<NameValuePair> pairs = parameters.stream()
                .map(p -> new BasicNameValuePair(p.name(), p.value()))
                .collect(toList());

        HttpEntity formEntity = new UrlEncodedFormEntity(pairs);
        request.setEntity(formEntity);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            StatusLine statusLine = response.getStatusLine();

            if (statusLine.getStatusCode() != 200) {
                throw new ContentNotAvailableException("Server for uri " + uri + " responded with status " + statusLine.getStatusCode() + " and reason: " + statusLine.getReasonPhrase());
            }

            String payloadAsString = EntityUtils.toString(response.getEntity());
            return new PageContent(payloadAsString);
        }
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }
}
