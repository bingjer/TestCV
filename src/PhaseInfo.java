
public class PhaseInfo {
	private String url;
	private String element_path;
	private String expected_path;
	private String interaction_type;
	private String phase_name;
	
//	public PhaseInfo(String url, String element_path, String expected_path) {
//		this.url = url;
//		this.element_path = element_path;
//		this.expected_path = expected_path;
//	}
//	
	
	public void set_url(String url) {
		this.url = url;
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
	
	public String get_phase_name() {
		return phase_name;
	}
	
	public String get_url_name() {
		return url;
	}
	

}
