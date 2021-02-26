package Client;

import APIs.IPetService;
import APIs.PMApi;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.okhttp.OkHttpClient;

public class APIClient {
    public IPetService petService;
    public PMApi postmanService;
    private static APIClient APIClient = null;

    private APIClient()
    {
        /*okhttp3.OkHttpClient.Builder clientBuilder = new okhttp3.OkHttpClient.Builder();
        okhttp3.OkHttpClient OKClient = clientBuilder.followRedirects(false).followSslRedirects(false).build();
        Client client = new OkHttpClient(OKClient);*/

        petService = Feign.builder()
                .contract(new JAXRSContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .client(new OkHttpClient())
                .target(IPetService.class,"https://petstore.swagger.io/v2");

        postmanService = Feign.builder()
                .contract(new JAXRSContract())
                //.encoder(new JacksonEncoder())
                //.decoder(new JacksonDecoder())
                .client(new OkHttpClient())
                .requestInterceptor(new BasicAuthRequestInterceptor("postman", "password"))
                .target(PMApi.class,"https://postman-echo.com");
    }

    public static APIClient GetClient()
    {
        if (APIClient == null)
            APIClient = new APIClient();
        return APIClient;
    }

}
