package blokkzaro_2.entities;

public class Tasks {
	private int id;
	private String task_name;
	private int project_id;
	private int owner_id;
	
	public Tasks(int id, String task_name, int project_id, int owner_id) {
		this.id = id;
		this.task_name = task_name;
		this.project_id = project_id;
		this.owner_id = owner_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tasks [id=");
		builder.append(id);
		builder.append(", task_name=");
		builder.append(task_name);
		builder.append(", project_id=");
		builder.append(project_id);
		builder.append(", owner_id=");
		builder.append(owner_id);
		builder.append("]");
		return builder.toString();
	}

}
