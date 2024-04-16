package Client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;

public class AuthRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            requestTemplate.header("Authorization", "Bearer " + getToken());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getToken() throws IOException
    {
        RequestBody formBody = new FormBody.Builder()
                .add("client_id", "sgtestapp")
                .add("client_secret", "105cbdbe6cbee42560258a08ab358af4")
                .add("grant_type", "client_credentials")
                .build();

        Request request = new Request.Builder()
                .url("http://coop.apps.symfonycasts.com/token")
                .post(formBody)
                .build();

        Call call = new OkHttpClient().newCall(request);
        Response response = call.execute();
        assert response.body() != null;
        HashMap<String, String> resp = new Gson().fromJson(response.body().string(), new TypeToken<HashMap>(){}.getType());
        return resp.get("access_token");
    }
}
