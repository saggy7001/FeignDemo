package Client;

import APIs.IPetService;
import APIs.ISymfonyCasts;
import APIs.PMApi;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.okhttp.OkHttpClient;

public class APIClient {
    public IPetService petService;
    public ISymfonyCasts iSymfonyCastsService;
    public PMApi postmanService;
    private static APIClient APIClient = null;

    private APIClient()
    {
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

        iSymfonyCastsService = Feign.builder()
                .contract(new JAXRSContract())
                .client(new OkHttpClient())
                .requestInterceptor(new AuthRequestInterceptor())
                .target(ISymfonyCasts.class, "http://coop.apps.symfonycasts.com");
    }

    public static APIClient GetClient()
    {
        if (APIClient == null)
            APIClient = new APIClient();
        return APIClient;
    }

}
