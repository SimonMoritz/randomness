package randomness;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import framework.RetrievalStrategy;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class APIRetrievalStrategy implements RetrievalStrategy {

    public String getAPIKey(){
        String key;
        try{
            File f = new File("src/main/java/randomness/apikey.txt");
            Scanner sc = new Scanner(f);
            key = sc.nextLine();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return key;
    }

    @Override
    public int[] getBitsFromExternalRG(int amount) {
        int[] bits = new int[amount];
        int id = 12691;
        String apiKey = getAPIKey();
        String url = "https://api.random.org/json-rpc/4/invoke";
        String load = "{\"jsonrpc\": \"2.0\",\"method\": \"generateIntegerSequences\",\"params\": {" +
                "\"apiKey\": \"" + apiKey + "\"," +
                "\"n\": 1," +
                "\"length\": " + amount +"," +
                "\"min\": 0," +
                "\"max\": 1," +
                "\"replacement\": True," +
                "\"base\": 2" +
                "}, \"id\": " + id + "}";

        JsonObject jsonObject = new JsonParser().parse(load).getAsJsonObject();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject responseLoad = new JsonParser().parse(response.body()).getAsJsonObject();
            JsonElement j = responseLoad.get("result").getAsJsonObject().get("random").getAsJsonObject().get("data");
            JsonArray arrayOfBits = j.getAsJsonArray().get(0).getAsJsonArray();
            for (int i=0; i<amount; i++){
                bits[i] = arrayOfBits.get(i).getAsInt();
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return bits;
    }
}
