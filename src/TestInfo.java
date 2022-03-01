import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
  
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class TestInfo {
	private static FileWriter file;
	
	
	public void write_json(Vector<PhaseInfo> phase_info_vec) {
		JSONObject json_obj = new JSONObject();
		int counter = 1;
		
		for(PhaseInfo phase : phase_info_vec) {
			System.out.println(phase.get_phase_name());
			JSONArray phase_info_array = new JSONArray();
			phase_info_array.add("Phase: " + phase.get_phase_name());
			phase_info_array.add("URL: " + phase.get_url_name());
			phase_info_array.add("Element: " + phase.get_element());
			phase_info_array.add("Screenshot: " + phase.get_screenshot());
			phase_info_array.add("Interaction: " + phase.get_interaction_type());
			phase_info_array.add("Message: " + phase.get_message());
			json_obj.put(counter, phase_info_array);
			++counter;
		}
		
		try {
			file = new FileWriter("C:\\Users\\adamn\\Desktop\\jsontest.txt");
			file.write(json_obj.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void load_json(String file) throws IOException, ParseException {
		Object obj;
		FileReader read_file = new FileReader(file);
		obj = new JSONParser().parse(read_file);
		JSONObject json_obj = (JSONObject) obj;
	}
	
}
