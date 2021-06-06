package blokkzaro_2.entities;

import java.sql.Date;

public class Projects {
	
	private int id;
	private String project_name;
	private Date start_date;
	private Date end_date;
	private int created_by;
	
	public Projects(int id, String project_name, Date start_date, Date end_date, int created_by) {
		this.id = id;
		this.project_name = project_name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.created_by = created_by;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(project_name);
		return builder.toString();
	}
	

}
