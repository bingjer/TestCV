
public class PhaseInfo {
	private String element_path;
	private String expected_path;
	private String interaction_type;
	private String phase_name;
	private String message;
	
	public PhaseInfo() {
		this.element_path = "";
		this.expected_path = "";
		this.interaction_type = "";
		this.phase_name = "";
		this.message = "";
	}
	
	public void set_element_path(String element_path) {
		this.element_path = element_path;
	}
	
	public void set_expected_path(String expected_path) {
		this.expected_path = expected_path;
	}
	
	public void set_interaction_type(String interaction_type) {
		this.interaction_type = interaction_type;
	}
	
	public void set_phase_name(String phase_name) {
		this.phase_name = phase_name;
	}
	
	public void set_message(String message) {
		this.message = message;
	}
	
	public String get_phase_name() {
		return phase_name;
	}
	
	
	public String get_element() {
		return element_path;
	}
	
	public String get_screenshot() {
		return expected_path;
	}
	
	public String get_interaction_type() {
		return interaction_type;
	}
	
	public String get_message() {
		return message;
	}
	
	

}
