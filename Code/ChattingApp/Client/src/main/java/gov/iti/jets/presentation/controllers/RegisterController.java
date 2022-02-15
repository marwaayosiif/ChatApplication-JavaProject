package gov.iti.jets.presentation.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.dtos.RegisterDto;
import gov.iti.jets.service.impl.RegisterService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    RegisterService registerService = new RegisterService();
    Boolean isRegistered;
    @FXML
    private TextArea bioTextArea;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private DatePicker dateOfBirthTextField;

    @FXML
    private TextField emailTextField;

//    @FXML
//    private Label female;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private TextField fullNameTextField;

//    @FXML
//    private Label male;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Circle profilePicture;

    @FXML
    private Button regbutton;

    LocalDate date;

    public RegisterController() throws RemoteException {
    }

    @FXML
    void addYourProfilePicture(MouseEvent event) {
        String picPath = stageCoordinator.openFile();
        if (picPath != null) {
            Image image;
            try {
                image = new Image(new FileInputStream(picPath));
                String imagePath = encodeImage(picPath);
                userModel.setImagePath(imagePath);
                profilePicture.setFill(new ImagePattern(image));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String encodeImage(String imgPath) throws IOException {
        FileInputStream stream = new FileInputStream(imgPath);
        byte[] imageData = stream.readAllBytes();
        String imageString = Base64.getEncoder().encodeToString(imageData);
        stream.close();
        return  imageString;
    }

    @FXML
    void changeOnAction(ActionEvent event) {
        date = dateOfBirthTextField.getValue();
        userModel.setDate(date.toString());
    }

    @FXML
    void Clicked(ActionEvent event) {
        ToggleGroup toggleGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(toggleGroup);
        femaleRadioButton.setToggleGroup(toggleGroup);
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        String gender = selectedRadioButton.getText();
        userModel.setGender(gender);
    }


    @FXML
    void registerOnMouseClick(MouseEvent event) {

        RegisterDto registerDto = new RegisterDto();
        registerDto.setPhoneNumber(userModel.getPhoneNumber());
        registerDto.setName(userModel.getUserName());
        registerDto.setEmail(userModel.getEmail());
        registerDto.setPassword(userModel.getPassword());
        registerDto.setCountry(userModel.getCountry());
        registerDto.setBio(userModel.getBio());
        registerDto.setPicture(userModel.getImagePath());
        registerDto.setGender(userModel.getGender());
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(userModel.getDate());
            registerDto.setDateOfBirth(date);
        } catch (ParseException e) {
        }
        System.out.println(registerDto);
        try {
            isRegistered = registerService.registUser(registerDto);
            stageCoordinator.switchToLoginScreen();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println(isRegistered);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        phoneNumberTextField.textProperty().bindBidirectional(userModel.phoneNumberProperty());
        fullNameTextField.textProperty().bindBidirectional(userModel.userNameProperty());
        emailTextField.textProperty().bindBidirectional(userModel.emailProperty());
        passwordTextField.textProperty().bindBidirectional(userModel.passwordProperty());
        bioTextArea.textProperty().bindBidirectional(userModel.bioProperty());
//        dateOfBirthTextField.accessibleTextProperty().bindBidirectional(userModel.dateProperty());
        countryTextField.textProperty().bindBidirectional(userModel.countryProperty());
    }
}
