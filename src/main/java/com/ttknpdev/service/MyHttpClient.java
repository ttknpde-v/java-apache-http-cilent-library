package com.ttknpdev.service;

import com.ttknpdev.log.Logging;
import com.ttknpdev.service.handler_response.HandlerResponsePost;
import com.ttknpdev.service.handler_response.HandlerResponsePut;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/*
    Http client work for sending http
    So Should you set your server for testing http yet ?

            NOTE :
            basic method
            double getPiValue() {
                return 3.1415;
            }
            convert to lambda
            () -> 3.1415
*/
public class MyHttpClient implements ResponseHandler<String> {

    private final CloseableHttpClient closeableHttpClient = HttpClients.createDefault(); // Create instance of CloseableHttpClient using helper class HttpClients.

    // Create a custom response handler
    private final HttpGet httpGet = new HttpGet("https://spring-boot-retrieve-weather-c07da85c4156.herokuapp.com/api-weather/bangkok");
    private final HttpGet httpGetEmployee = new HttpGet("http://localhost:8080/ApacheHttpCilent/api/read/1");
    private final HttpGet httpGetEmployees = new HttpGet("http://localhost:8080/ApacheHttpCilent/api/reads");
    private final HttpPost httpPostEmployee = new HttpPost("http://localhost:8080/ApacheHttpCilent/api/create");
    private final HttpPut httpPutEmployee = new HttpPut("http://localhost:8080/ApacheHttpCilent/api/update/9");
    private final HttpDelete httpDeleteEmployee = new HttpDelete("http://localhost:8080/ApacheHttpCilent/api/delete/2");

    public void testHttpGet() {
        ResponseHandler<String> responseHandler = httpResponse -> {
            // store status res
            short status = (short) httpResponse.getStatusLine().getStatusCode();
            Logging.myHttpClient.info("status : " + status);
            if (status > 200 && status < 300) {
                HttpEntity httpEntity = httpResponse.getEntity();
                Logging.myHttpClient.info("httpEntity : " + httpEntity);
                if (httpEntity != null) return String.valueOf(httpEntity);
                else return null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        try {
            String responseBody = closeableHttpClient.execute(httpGet, responseHandler);
            Logging.myHttpClient.info("responseBody ++++++++++++++++++++++++");
            Logging.myHttpClient.info(responseBody); // ResponseEntityProxy{[Content-Type: application/json,Chunked: true]}

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /* way fourth for using http request */
    public void testCreate() {
        // add header http to Post request
        // must specify
        httpPostEmployee.setHeader("Accept", "application/json");
        httpPostEmployee.setHeader("Content-type", "application/json");
        // create data object
        String jsonFormat = "{\r\n" +
                "  \"id\": \"4\",\r\n" +
                "  \"fullname\": \"em4\",\r\n" +
                "  \"position\": \"java backend\",\r\n" +
                "  \"salary\": \"95000.50\"\r\n" +
                "}";
        try {
            // convert to string entity (json)
            StringEntity entity = new StringEntity(jsonFormat);
            httpPostEmployee.setEntity(entity); // set entity
            Logging.myHttpClient.info("executing request (before request): " + httpPostEmployee.getRequestLine()); // Executing request POST /ApacheHttpCilent/api/create HTTP/1.1
            // Create a custom response handler
            String responseBody = closeableHttpClient.execute(httpPostEmployee, new HandlerResponsePost()::handleResponse); // called overide method other class
            Logging.myHttpClient.info(responseBody); // ResponseEntityProxy{[Content-Type: application/json,Chunked: true]}

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* way second for using http request */
    public void testRead() {
        Logging.myHttpClient.info("executing request (before request): " + httpGetEmployee.getRequestLine()); /* executing request (before request): GET http://localhost:8080/ApacheHttpCilent/api/read/1 HTTP/1.1 */

        try {

            String responseBody = closeableHttpClient.execute(httpGetEmployee, this); /* this part is working for executing */
            Logging.myHttpClient.info(responseBody); // ResponseEntityProxy{[Content-Type: application/json,Chunked: true]}

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String handleResponse(HttpResponse httpResponse) throws IOException {
        short status = (short) httpResponse.getStatusLine().getStatusCode(); /* get status from response */
        Logging.myHttpClient.info("status response : " + status); /* status response : 202 */

        if (status > 200 && status < 300) {

            HttpEntity httpEntity = httpResponse.getEntity();
            Logging.myHttpClient.info("entity of response : " + httpEntity); /* entity of response : ResponseEntityProxy{[Content-Type: application/json;charset=UTF-8,Chunked: true]} */

            if (httpEntity != null) {
                return String.valueOf(httpEntity);
            } else {
                return null;
            }

        } else {
            throw new ClientProtocolException("Unexpected response status : " + status);
        }
    }


    /* way third for using http request */
    public void testReads() {
        Logging.myHttpClient.info("executing request (before request): " + httpGetEmployee.getRequestLine());
        try {

            String responseBody = closeableHttpClient.execute(httpGetEmployees, new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    short status = (short) httpResponse.getStatusLine().getStatusCode();
                    Logging.myHttpClient.info("status response : " + status); /* status response : 202 */

                    if (status > 200 && status < 300) {

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
            });

            Logging.myHttpClient.info(responseBody);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void testUpdate() {
        // must specify when request Post , Put
        httpPutEmployee.setHeader("Accept", "application/json");
        httpPutEmployee.setHeader("Content-type", "application/json");
        String jsonFormat = "{\r\n" +
                "  \"fullname\": \"em1 update\",\r\n" +
                "  \"position\": \"java backend\",\r\n" +
                "  \"salary\": \"809900.50\"\r\n" +
                "}";
        try {
            // convert to string entity (json)
            StringEntity entity = new StringEntity(jsonFormat);
            httpPutEmployee.setEntity(entity); // set entity
            Logging.myHttpClient.info("executing request (before request): " + httpPutEmployee.getRequestLine()); // executing request (before request): PUT http://localhost:8080/ApacheHttpCilent/api/update/1 HTTP/1.1
            // Create a custom response handler
            String responseBody = closeableHttpClient.execute(httpPutEmployee, new HandlerResponsePut()::handleResponse); // called overide method other class
            Logging.myHttpClient.info(responseBody); // ResponseEntityProxy{[Content-Type: application/json,Chunked: true]}

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void testDelete() {
        try {
            String responseBody = closeableHttpClient.execute(httpDeleteEmployee, new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse httpResponse) {
                    short status = (short) httpResponse.getStatusLine().getStatusCode();

                    if (status >= 200 && status < 300) {

                        HttpEntity httpEntity = httpResponse.getEntity();
                        Logging.myHttpClient.info("entity of response : " + httpEntity); /* entity of response : ResponseEntityProxy{[Content-Type: application/json;charset=UTF-8,Chunked: true]} */

                        if (httpEntity != null) {
                            return String.valueOf(httpEntity); /* convert to string */
                        } else {
                            return null;
                        }

                    } else {

                        try {

                            throw new ClientProtocolException("Unexpected response status : " + status);

                        } catch (ClientProtocolException e) {

                            Logging.myHttpClient.warn("result " + e.getMessage());
                            throw new RuntimeException(e);

                        }

                    }
                }
            });
            Logging.myHttpClient.info(responseBody); // ResponseEntityProxy{[Content-Type: application/json,Chunked: true]}

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
