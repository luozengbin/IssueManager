package magicware.scm.redmine.tools.model;

import java.util.Map;

public class Issue {

	private String project_id;
	private String subject;
	private String tracker_id;
	private String status;
	private String description;
	private String category_id;
	private String assigned_to_id;
	private String due_date;
	private String start_date;
	private String done_ratio;
	private String priority_id;
	private Map<String, String> custom_field_values;
	private String[] watcher_user_ids;

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTracker_id() {
		return tracker_id;
	}

	public void setTracker_id(String tracker_id) {
		this.tracker_id = tracker_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getAssigned_to_id() {
		return assigned_to_id;
	}

	public void setAssigned_to_id(String assigned_to_id) {
		this.assigned_to_id = assigned_to_id;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getDone_ratio() {
		return done_ratio;
	}

	public void setDone_ratio(String done_ratio) {
		this.done_ratio = done_ratio;
	}

	public String getPriority_id() {
		return priority_id;
	}

	public void setPriority_id(String priority_id) {
		this.priority_id = priority_id;
	}

	public Map<String, String> getCustom_field_values() {
		return custom_field_values;
	}

	public void setCustom_field_values(Map<String, String> custom_field_values) {
		this.custom_field_values = custom_field_values;
	}

	public String[] getWatcher_user_ids() {
		return watcher_user_ids;
	}

	public void setWatcher_user_ids(String[] watcher_user_ids) {
		this.watcher_user_ids = watcher_user_ids;
	}
}
