import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONObject;
import org.junit.Test;

import java.util.List;

public class JsonTest {

    @Test
    public void shouldS() {
        String json = "{\n" +
                "    \"action\": \"get\",\n" +
                "    \"node\": [{\n" +
                "        \"key\": \"/switch-engine\",\n" +
                "        \"dir\": true,\n" +
                "        \"nodes\": [\n" +
                "            {\n" +
                "                \"key\": \"/switch-engine/52\",\n" +
                "                \"value\": \"10000\",\n" +
                "                \"modifiedIndex\": 52,\n" +
                "                \"createdIndex\": 52\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"/switch-engine/53\",\n" +
                "                \"value\": \"10000\",\n" +
                "                \"modifiedIndex\": 53,\n" +
                "                \"createdIndex\": 53\n" +
                "            }\n" +
                "        ],\n" +
                "        \"modifiedIndex\": 52,\n" +
                "        \"createdIndex\": 52\n" +
                "    }]\n" +
                "}";


        JSONObject jsonObj = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");

        List<Object> books = JsonPath.read(json, "$.node[?(@.key == '/switch-engine')].nodes.value");
        System.out.println("books.size() = " + books.size());
    }
}
