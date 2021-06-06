package blokkzaro_2.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import blokkzaro_2.entities.Projects;
import blokkzaro_2.entities.Tasks;
import blokkzaro_2.entities.Users;

public class Repository {

	private final String URL = "jdbc:mysql://localhost:3306/ProjectPlanner";
	private final String USERNAME = "root";
	private final String PW = "";

	private Connection con = null;
	private Statement statement = null;

	public Repository() {
		initDatabase();
	}

	private void initDatabase() {
		try {
			con	= DriverManager.getConnection(URL, USERNAME, PW);
			statement = con.createStatement();

		} catch (Exception e) {
			System.out.println("Error happened during connection... \n " + e.getMessage());
		}
	}

	public void close() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			System.out.println("Could not close the application...\n" + e.getMessage());
		}
	}

	//	--------------------FELHASZNÁLÓK LISTÁZÁSA
	public List<Users> listUsers() {
		String query = "SELECT * FROM Users";
		List<Users> users = new ArrayList<>();

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {			
				users.add(
						new Users(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("email")));
			}

		} catch (SQLException e) {
			System.out.println("Error happened during reading from Users... \n " + e.getMessage());
		}

		return users;
	}
	
	//	--------------------FELHASZNÁLÓ HOZZÁADÁSA
	public void addUser(Users user) {
		String query = "INSERT INTO `users`(`id`, `name`, `email`) "
				+ "VALUES (?,?,?)";
		
		try {
			PreparedStatement prdstm;
			prdstm = con.prepareStatement(query);
			prdstm.setInt(1, user.getId());
			prdstm.setString(2, user.getName());
			prdstm.setString(3, user.getEmail());
			prdstm.executeUpdate();
			prdstm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error happened during add new User..." + e.getMessage());
		}
		
	}
	
	//	--------------------PROJECTEK LISTÁZÁSA(COMBOBOX)
	public List<Projects> listProjects() {
		String query = "SELECT * FROM project";
		List<Projects> project = new ArrayList<>();

		try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                project.add(
                        new Projects(
                                rs.getInt("id"),
                                rs.getString("project_name"),
                                rs.getDate("start_date"),
                                rs.getDate("end_date"),
                                rs.getInt("created_by")));
            }

        } catch (SQLException e) {
            System.out.println("Error happened during reading from Project... \n " + e.getMessage());
        }

        return project;
    }
	
	//	--------------------PROJECT HOZZÁADÁSA
	public void addProject(Projects project) {
		String query = "INSERT INTO `project`(`id`, `project_name`, `start_date`, `end_date`, `created_by`)"
				+ " VALUES (?,?,?,?,?)";
		
		try {
			PreparedStatement prdstm;
			prdstm = con.prepareStatement(query);
			prdstm.setInt(1, project.getId());
			prdstm.setString(2, project.getProject_name());
			prdstm.setDate(3, project.getStart_date());
			prdstm.setDate(4, project.getEnd_date());
			prdstm.setInt(5, project.getCreated_by());
			prdstm.executeUpdate();
			prdstm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error happened during add new Project..." + e.getMessage());
		}
	}
	
	//	--------------------FELADAT HOZZÁADÁSA
	public void addTask(Tasks task) {
		String query = "INSERT INTO `task`(`id`, `task_name`, `project_id`, `owner_id`)"
				+ "VALUES (?,?,?,?)";
		
		try {
			PreparedStatement prdstm;
			prdstm = con.prepareStatement(query);
			prdstm.setInt(1, task.getId());
			prdstm.setString(2, task.getTask_name());
			prdstm.setInt(3, task.getProject_id());
			prdstm.setInt(4, task.getOwner_id());
		} catch (SQLException e) {
			System.out.println("Error happened during add new Task..." + e.getMessage());
		}
		
	}
	
	//	--------------------PROJEKTENKÉNT FELADATOK LISTÁZÁSA
	public List<Tasks> listByProjects(Projects project) {
		String query = "SELECT * from task t "
				+ "WHERE t.project_id = " + project.getId();
		List<Tasks> task = new ArrayList<>();

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {			
				task.add(
						new Tasks(
								rs.getInt("id"),
								rs.getString("task_name"),
								rs.getInt("project_id"),
								rs.getInt("owner_id")));
			}

		} catch (SQLException e) {
			System.out.println("Error happened during reading from Task... \n " + e.getMessage());
		}

		return task;
	}
}
