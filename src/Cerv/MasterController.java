package Cerv;

import Classes.*;
import Classes.Error;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import org.xbill.DNS.Name;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.util.Date;
import java.util.ResourceBundle;

public class MasterController implements Initializable {

    @FXML
    private Label hostName;

    @FXML
    private Label sslLocation;

    @FXML
    private Label cnNAme;

    @FXML
    private Label organizationName;

    @FXML
    private Label sslAlgo;
    @FXML
    private Button closeButton;

    @FXML
    private TextArea mxRecords;

    @FXML
    private Label validSSL;

    @FXML
    private Label ipAddress;

    @FXML
    private TextField userDomain;

    @FXML
    private Label sslSerial;

    @FXML
    private Label expireData;

    @FXML
    void onSubmitClick(ActionEvent event) throws IOException, CertificateNotYetValidException, CertificateExpiredException {
        Error errorAlert = new Error();
        clearAllLabels();

        String urlToVerify = userDomain.getText();


        URLValidation verifyURL = new URLValidation();
        verifyURL.urlVerifier(urlToVerify);
        URL urlOK = new URL(verifyURL.urlVerifier(urlToVerify));

        HttpsURLConnection connection;
        try {
            connection = (HttpsURLConnection) urlOK.openConnection();
            connection.connect();

        } catch (IOException ex) {
            errorAlert.errorAlert("Connection error", ex);
            throw new IOException();
        }


        IpAddress.returnIpAddress(this, urlToVerify, errorAlert);
        CommonName.returnCommonName(this, urlOK, connection, errorAlert);
        //AlternateNames.returnAltNames(this, urlOK, connection, errorAlert);
        Organization.returnOrganization(this, urlOK, connection, errorAlert);
        Location.returnLocation(this, urlOK, connection, errorAlert);
        SSLSerial.returnSerial(this, urlOK, connection, errorAlert);
        Algorithm.returnAlgo(this, urlOK, connection, errorAlert);
        ExpiryDate.returnExpiry(this, urlOK, connection, errorAlert);
        SSLValidCertificate.returnValid(this, urlOK, connection, errorAlert);
        CustomHostnameVerifier.returnHostnameVerified(this, connection);
        MXRecord.mxLookup(this, urlToVerify);
        //AlternateNames.returnAltNames(this,urlOK, connection);

        //todo Add alternate names.
        //todo Add About app

    }

    @FXML
    private void closeButtonAction() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setIpAddress(String text) {
        ipAddress.setText(text);
    }

    public void setCommonName(String text) {

        cnNAme.setText(text);
    }

    public void setOrganizationName(String text) {
        organizationName.setText(text);
    }

    public void setSslLocation(String text) {
        sslLocation.setText(text);
    }

    public void setSslSerial(BigInteger serial) {
        sslSerial.setText(String.valueOf(serial));
    }

    public void setSslAlgo(String text) {
        sslAlgo.setText(text);
    }

    public void setExpireData(Date text) {
        expireData.setText(String.valueOf(text));
    }

    public void getHostname(boolean hostnameValid) {
        System.out.print("getHostname=" + hostnameValid);
        if (hostnameValid) {
            hostName.setTextFill(Color.GREEN);
            hostName.setFont(Font.font(String.valueOf(FontPosture.ITALIC)));
            hostName.setText("Hostname match");
        } else if (!hostnameValid) {
            hostName.setTextFill(Color.RED);
            hostName.setText("Hostname mis-match");
        }
    }

    public void setValidSSL(boolean validation) {
        if (validation) {
            validSSL.setTextFill(Color.web("#00CC00"));
            validSSL.setText("Valid certificate");
        } else {
            if (!validation) {
                validSSL.setTextFill(Color.web("#FF0000"));
                validSSL.setText("Invalid certificate.");
            }
        }

    }
    public void setMXRecord(Name mxRecord) {
        mxRecords.appendText(String.valueOf(mxRecord)+ "\n");
    }

    private void clearAllLabels() {
        sslLocation.setText("");
        cnNAme.setText("");
        organizationName.setText("");
        sslAlgo.setText("");
        ipAddress.setText("");
        sslSerial.setText("");
        expireData.setText("");
        validSSL.setText("");
        hostName.setText("");
        mxRecords.clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}


