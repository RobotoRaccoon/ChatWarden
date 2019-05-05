package org.mcau.robotoraccoon.chatwarden.utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.mcau.robotoraccoon.chatwarden.mMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class uParseJson {

    private static final JsonParser jsonParser = new JsonParser();
    private static File autokickFile = new File(mMain.getPlugin().getDataFolder(), "autokick.json");

    // Convert the json from the specified file into an array of maps.
    public static void main() {

        try {
            // Ensure the file exists.
            if (!getAutokickFile().exists()) mMain.getPlugin().saveResource("autokick.json", true);

            BufferedReader autokickFileReader = new BufferedReader(new FileReader(getAutokickFile()));
            String autokickoneline = "";
            String autokickfile;

            // Convert all of the lines into a single string.
            while ((autokickfile = autokickFileReader.readLine()) != null)
                autokickoneline += autokickfile;

            JsonArray jsonObject = (JsonArray) jsonParser.parse(autokickoneline.replaceAll("\\r\\n|\\r|\\n", " "));

            // Clear array and add values from the json.
            uSwear.autokickRegexList.clear();
            for (Object currentAutoKickThing : jsonObject) {
                JsonObject autokickCurrentObjectJSON = (JsonObject) currentAutoKickThing;

                Map<String, String> autokickMap = new HashMap<>();

                autokickMap.put("regex", autokickCurrentObjectJSON.get("regex").getAsString());
                autokickMap.put("reason", autokickCurrentObjectJSON.get("reason").getAsString());

                uSwear.autokickRegexList.add(autokickMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static File getAutokickFile() {
        return autokickFile;
    }

}
