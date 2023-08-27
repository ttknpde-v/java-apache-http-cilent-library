package com.ttknpdev.service.handler_response;

import com.ttknpdev.log.Logging;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;

public class HandlerResponsePut implements ResponseHandler<String> {
    @Override
    public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        short status = (short) httpResponse.getStatusLine().getStatusCode();
        Logging.myHttpClient.info("status response : " + status); /* status response : 202 */

        if (status >= 200 && status < 300) {

            HttpEntity httpEntity = httpResponse.getEntity();
            Logging.myHttpClient.info("entity of response : " + httpEntity); /* entity of response : ResponseEntityProxy{[Content-Type: application/json;charset=UTF-8,Chunked: true]} */

            if (httpEntity != null) {
                return String.valueOf(httpEntity); /* convert to string */
            } else {
                return null;
            }

        } else {
            throw new ClientProtocolException("Unexpected response status : " + status);
        }
    }
}
