import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Test
public class RestAPITestClass {

    @Test
    public void Case5() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("https://jsonplaceholder.typicode.com/users");

        HttpResponse httpresponse = httpclient.execute(httpget);
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpresponse.getEntity().getContent(), "UTF-8"));
        StringBuilder builder = new StringBuilder();
        for (String line = null; (line = reader.readLine()) != null;) {
            builder.append(line).append("\n");
        }
        JSONTokener tokener = new JSONTokener(builder.toString());
        JSONArray finalResult = new JSONArray(tokener);

        for (int i = 0; i < finalResult.length(); i++){
            JSONObject obj = finalResult.getJSONObject(i);
            String name = obj.getString("name");
            String email = obj.getString("email");
            System.out.println(name + " | " + email);
            Assert.assertTrue(email.contains("@"), "Email not contains @!");
        }
    }
}
