package blokkzaro_2.view;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import blokkzaro_2.entities.Projects;
import blokkzaro_2.entities.Tasks;
import blokkzaro_2.entities.Users;
import blokkzaro_2.repository.Repository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewController implements Initializable{
	
	private Repository repo;
	
	@FXML
	private ComboBox<Users> userCombo;
	@FXML
	private ComboBox<Projects> projectCombo;
	@FXML
	private TableView<Users> userTable;
	@FXML
	private TableView<Tasks> taskTable;
	@FXML
	private Button userButton, projectButton, closeButton,
				taskButton, saveUserButton, saveProjectButton, saveTaskButton;
	@FXML
	private Pane taskPane, userPane, projectPane, newTaskPane;
	@FXML
	private Label taskLabel;
	@FXML
	private TextField idTextField, nameTextField, emailTextField,
				idProjectTextField, projectNameTextField, startProjectTextField,
				endProjectTextField, createdProjectTextField,
				taskIdTextField, taskNameTextField, taskProjectIdTextField, ownerIdTextField;
	@FXML
	private TabPane projectTab;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initPanels(true);
		repo = new Repository();
		//	COMBOBOX FELTÖLTÉSE DT-BÕL
		initUserComboBox();
		initProjectComboBox();
		initTaskTable();
		initUserTable();
	}
	
	//	--------------------USER LISTA COMBOBOX
	public void selectUser(ActionEvent event) {
		Object source = event.getSource();
		Users users = ((ComboBox<Users>) source).getValue();
		refreshUserTable(users);
	}
	
	//	--------------------PROJECT LISTA COMBOBOX
	public void selectProject(ActionEvent event) {
		Object source = event.getSource();
		Projects project = ((ComboBox<Projects>) source).getValue();
		refreshTable(project);
	}
	
	//	--------------------FRISSÍTEM A USER TÁBLÁZATOT
	private void refreshUserTable(Users user) {
		userTable.setItems(
				FXCollections.observableArrayList(
						repo.listUsers())
				);
	}
	
	/*	--------------------FRISSÍTEM A TÁBLÁZATOT
	 * 	--------------------A KIVÁLASZTOTT PROJECTHEZ TARTOZÓ
	 * 	--------------------FELADATTAL
	 */
	private void refreshTable(Projects project) {
		taskTable.setItems(
				FXCollections.observableArrayList(
						repo.listByProjects(project))
				);
	}
	
	//	--------------------USER COMBOBOX FELTÖLTÉSE
	private void initUserComboBox() {
		userCombo.getItems().addAll(repo.listUsers());
		userCombo.setVisible(true);
		userCombo.setVisibleRowCount(3);
		taskPane.setVisible(false);
	}
	
	//	--------------------PROJECT COMBOBOX FELTÖLTÉSE
	private void initProjectComboBox() {
		projectCombo.getItems().addAll(repo.listProjects());
		projectCombo.setVisible(true);
		projectCombo.setVisibleRowCount(3);
		taskPane.setVisible(true);
	}
	
	private void initPanels(boolean visible) {
		taskPane.setVisible(!visible);
	}
	
	//	--------------------USER TÁBLÁZAT INICIALIZÁLÁSA
	private void initUserTable() {
		TableColumn<Users, Integer> idCol = new TableColumn<>("ID");
		idCol.setCellFactory(col -> new TableCell<Users, Integer>());
		idCol.setCellValueFactory(
				new PropertyValueFactory<Users, Integer>("id"));
		idCol.setMinWidth(160);
		
		TableColumn<Users, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameCol.setCellValueFactory(
				new PropertyValueFactory<Users, String>("name"));
		nameCol.setMinWidth(160);
		
		TableColumn<Users, String> emailCol = new TableColumn<>("Email");
		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		emailCol.setCellValueFactory(
				new PropertyValueFactory<Users, String>("email"));
		emailCol.setMinWidth(160);
		
		userTable.getColumns().addAll(idCol, nameCol, emailCol);
		
	}
	
	//	--------------------FELADAT TÁBLÁZAT INICIALIZÁLÁSA
	private void initTaskTable() {
		TableColumn<Tasks, Integer> idCol = new TableColumn<>("ID");
		idCol.setCellFactory(col -> new TableCell<Tasks, Integer>());
		idCol.setCellValueFactory(
				new PropertyValueFactory<Tasks, Integer>("id"));
		idCol.setMinWidth(140);
		
		TableColumn<Tasks, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameCol.setCellValueFactory(
				new PropertyValueFactory<Tasks, String>("task_name"));
		nameCol.setMinWidth(140);
		
		TableColumn<Tasks, Integer> projectIdCol = new TableColumn<>("Project ID");
		projectIdCol.setCellFactory(col -> new TableCell<Tasks, Integer>());
		projectIdCol.setCellValueFactory(
				new PropertyValueFactory<Tasks, Integer>("project_id"));
		projectIdCol.setMinWidth(140);
		
		TableColumn<Tasks, Integer> ownerIdCol = new TableColumn<>("Owner ID");
		ownerIdCol.setCellFactory(col -> new TableCell<Tasks, Integer>());
		ownerIdCol.setCellValueFactory(
				new PropertyValueFactory<Tasks, Integer>("owner_id"));
		ownerIdCol.setMinWidth(140);
		
		taskTable.getColumns().addAll(idCol, nameCol, projectIdCol, ownerIdCol);
		
	}
	
	//	--------------------ÚJ USER HOZZÁADÁSA
	public void addNewUser(ActionEvent event) {
		int id = checkId(idTextField);
		String name = nameTextField.getText();
		String email = emailTextField.getText();
		Users user = new Users(id, name, email);
		disablePane(userPane);
		System.out.println(user);
	}
	
	//	--------------------ÚJ PROJECT HOZZÁADÁSA
	public void addNewProject(ActionEvent event) {
		int id = checkId(idProjectTextField);
		String name = projectNameTextField.getText();
		Date startDate = null;
		Date endDate = null;
		int createdById = checkId(createdProjectTextField);
		Projects project = new Projects(id, name, startDate, endDate, createdById);
		disablePane(projectPane);
	}
	
	//	--------------------ÚJ FELADAT HOZZÁADÁSA
	public void addNewTask(ActionEvent event) {
		int id = checkId(taskIdTextField);
		String name = taskNameTextField.getText();
		int projectId = checkId(taskProjectIdTextField);
		int ownerId = checkId(ownerIdTextField);
		Tasks task = new Tasks(id, name, projectId, ownerId);
		disablePane(newTaskPane);
	}
	
	//	--------------------
	private void disablePane(Pane pane) {
		taskPane.setVisible(false);
		userPane.setVisible(false);
		projectPane.setVisible(false);
		newTaskPane.setVisible(false);
		projectTab.setVisible(false);
		pane.setVisible(true);
		
	}
	
	//	--------------------ID ELLENÕRZÉSE
	public int checkId(TextField textField) {
		int input = 0;
		
		try {
			input = Integer.parseInt(textField.getText());
			if (input <= 0) {
				return 0;
			}
		} catch (NumberFormatException e) {
			return 0;
		}
		return input;
	}
	
	@FXML
	private void closeButtonAction(){
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
		
	}

}
