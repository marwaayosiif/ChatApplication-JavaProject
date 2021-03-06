package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.CompoundValidationDecoration;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;
import org.kordamp.ikonli.javafx.FontIcon;

public class LoginController1 implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    LoginService loginService = new LoginService();

    ValidationSupport validationSupport=new ValidationSupport();

    @FXML
    private FontIcon backArrow;

    @FXML
    private Label createbutton;

    @FXML
    private GridPane grid;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginPaneContent;

    @FXML
    private Label phoneNumberError;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Label welcome;

    @FXML
    void ToWelcom(MouseEvent event){
        stageCoordinator.switchToWelcomScreen();
    }

    @FXML
    void OnLoginAction(ActionEvent event) throws RemoteException{

        int userId = loginService.getUserId(userModel.getPhoneNumber());

        if ((userId > -1)){
            stageCoordinator.switchToPasswordScreen();
           // userInfo.put("phone number",userModel.getPhoneNumber());
        }
        validationSupport.setValidationDecorator(new StyleClassValidationDecoration());

        validationSupport.setValidationDecorator(new CompoundValidationDecoration(
                new GraphicValidationDecoration(),
                new StyleClassValidationDecoration()));


        validationSupport.registerValidator(phoneNumberTextField, Validator.createRegexValidator(
                "Number is required",
                Pattern.compile("^[0-9]+$"), Severity.ERROR));


    }

    @FXML
    void OnRegestrationAction(MouseEvent ev1ent) {
        stageCoordinator.switchToRegistrationScreen();
    }


    @FXML
    void OnWelcomeAction(MouseEvent event) {
        stageCoordinator.switchToWelcomScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        phoneNumberTextField.textProperty().bindBidirectional(userModel.phoneNumberProperty());
        //System.out.println("phone number from prefrence "+userInfo.get("phone number" , "no phone number"));
    }
}
