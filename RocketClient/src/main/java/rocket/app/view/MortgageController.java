package rocket.app.view;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	

	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private ComboBox loanTerm;
	@FXML
	private Button Calculate;
	@FXML
	private Label Output;
	@FXML
	private Label Income;
	@FXML
	private Label Expenses;
	@FXML
	private Label CreditScore;
	@FXML
	private Label HouseCost;
	@FXML
	private Label Term;
	
	private int intTerm;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	//Called in init method in MainApp to add items to combo box
	public void loanTermSetup(){
		loanTerm.getItems().addAll("15","30");
	}
	@FXML
	public void getTerm(ActionEvent event) throws Exception{
		intTerm = Integer.parseInt(event.getSource().toString());
	}
	
	@FXML
	public void btnCalculatePayment(ActionEvent event) throws NumberFormatException, Exception
	{
		Object message = null;
		
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();


		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		lq.setdRate(RateBLL.getRate(Integer.parseInt(txtCreditScore.getText())));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setiTerm(intTerm);
		
		
		a.setLoanRequest(lq);
		
				
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		
		lRequest.setdPayment(RateBLL.getPayment(lRequest.getdRate(), lRequest.getiTerm()*12, lRequest.getdAmount(), 0, false));
		if (lRequest.getIncome()*0.28 < lRequest.getdPayment()){
			Output.setText("You cannot afford this.");
		}
		else {Output.setText("$" + String.valueOf(lRequest.getdPayment()));}
		
	}
}
