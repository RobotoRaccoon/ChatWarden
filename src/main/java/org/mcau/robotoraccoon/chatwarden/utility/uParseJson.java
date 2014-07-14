package org.mcau.robotoraccoon.chatwarden.utility;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.org.apache.commons.io.output.StringBuilderWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mcau.robotoraccoon.chatwarden.mMain;

public class uParseJson {

    private static File autokickFile = new File(mMain.getPlugin().getDataFolder(), "autokick.json");
    private static final JSONParser jsonParser = new JSONParser();

    // Convert the json from the specified file into an array of maps.
    public static void main() {

        try {

            // Ensure the file exists.
            if ( !getAutokickFile().exists() ) mMain.getPlugin().saveResource("autokick.json", true);

            BufferedReader autokickFileReader = new BufferedReader( new FileReader( getAutokickFile() ) );
            StringBuilderWriter autokickoneline = new StringBuilderWriter();
            String autokickfile;

            // Convert all of the lines into a single string.
            while( (autokickfile = autokickFileReader.readLine()) != null) autokickoneline.append(autokickfile);

            JSONArray jsonObject = (JSONArray) jsonParser.parse( autokickoneline.toString().replaceAll("\\r\\n|\\r|\\n", " ") );

            // Clear array and add values from the json.
            uSwear.autokickRegexList.clear();
            for( Object currentAutoKickThing : jsonObject ){

                JSONObject autokickCurrentObjectJSON = (JSONObject) currentAutoKickThing;

                Map<String, String> autokickMap = new HashMap<>();

                autokickMap.put( "regex", (String) autokickCurrentObjectJSON.get( "regex" ) );
                autokickMap.put( "reason", (String) autokickCurrentObjectJSON.get( "reason" ) );

                uSwear.autokickRegexList.add(autokickMap);

            }

        } catch (ParseException | IOException e) {

            e.printStackTrace();

        }

    }

    public static File getAutokickFile() {
        return autokickFile;
    }

}
