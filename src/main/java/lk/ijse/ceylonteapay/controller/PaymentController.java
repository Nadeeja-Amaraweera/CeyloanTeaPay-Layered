
package lk.ijse.ceylonteapay.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.ceylonteapay.bo.BOFactory;
import lk.ijse.ceylonteapay.bo.custom.EmployeeBO;
import lk.ijse.ceylonteapay.bo.custom.TeaRateBO;
import lk.ijse.ceylonteapay.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.ceylonteapay.dao.custom.*;
import lk.ijse.ceylonteapay.dao.custom.impl.*;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;
import lk.ijse.ceylonteapay.dto.PaymentDTO;
import lk.ijse.ceylonteapay.dto.TeaRateDTO;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


public class PaymentController implements Initializable {

    @FXML
    private ComboBox<EmployeeDTO> cmbEmployee;
    @FXML
    private ComboBox<String> monthCombo;
    @FXML
    private ComboBox<TeaRateDTO> cmbTeaRate;

    @FXML
    private ComboBox<String> monthReportCombo;
    @FXML
    private ComboBox<Integer> yearReportCombo;

    @FXML
    private TextField txtTeaSalary;
    @FXML
    private TextField txtOtherSalary;
    @FXML
    private TextField txtFinalSalary;


    @FXML
    private TableView<PaymentDTO> tableView;

    @FXML
    private TableColumn<PaymentDTO, Integer> col_empId;
    @FXML
    private TableColumn<PaymentDTO, String> col_empName;
    @FXML
    private TableColumn<PaymentDTO, Double> col_finalSalary;
    @FXML
    private TableColumn<PaymentDTO, Integer> col_id;
    @FXML
    private TableColumn<PaymentDTO, Double> col_otherSalary;
    @FXML
    private TableColumn<PaymentDTO, LocalDate> col_paymentDate;
    @FXML
    private TableColumn<PaymentDTO, Integer> col_rateId;
    @FXML
    private TableColumn<PaymentDTO, Double> col_teaSalary;

    @FXML
    private Label lblTeaRate;

    EmployeeBO employeeBO = new EmployeeBOImpl();

    PaymentDAO paymentDAO = new PaymentDAOImpl();

    TeaRateDAO teaRateDAO = new TeaRateDAOImpl();

    OtherWorkDAO otherWorkDAO = new OtherWorkDAOImpl();

    DailyTeaDAO dailyTeaDAO = new DailyTeaDAOImpl();

    ObservableList<PaymentDTO> paymentDTOS = FXCollections.observableArrayList();

    TeaRateBO teaRateBO = (TeaRateBO) BOFactory.getInstance().getBO(BOFactory.BOType.TEA_RATE);

    private int selectEmpid;
    private int selectRateid;
    private String selecetEmpName;
    private Month selectedMonth;
    private int selectedMonthNumber;
    private int selectedYear;
    private double selectedTeaRate;

    private Month selectedReportMonth;
    private int selectedReportMonthNumber;
    private int selectedReportYear;

    private final String NUMBER_REGEX = "^[0-9]+(\\.[0-9]+)?$";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadEmployees();
        loadMonthsAndYears();
        loadTeaRateCombo();
        setTableColumn();

        tableView.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        tableSelection(newValue);
                    }
                })
        );

    }

    @FXML
    private void calculateSalary() {

//        must need to get this year
        loadTeaDataByMonth(selectedMonthNumber, selectEmpid);
        loadOtherWorkByMonth(selectedMonthNumber, selectEmpid);

        // Read values from text fields
        double teaSalary = txtTeaSalary.getText().isEmpty()
                ? 0
                : Double.parseDouble(txtTeaSalary.getText());

        double otherWorkSalary = txtOtherSalary.getText().isEmpty()
                ? 0
                : Double.parseDouble(txtOtherSalary.getText());

        // Calculate final salary
        double finalSalary = (teaSalary*50.0) + otherWorkSalary;

        // Set final salary
        txtFinalSalary.setText(String.valueOf(finalSalary));
    }

    @FXML
    private void savePayment() {

        if (cmbEmployee.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Select Employee").show();

        } else if (monthCombo.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Select Month").show();

        } else if (cmbTeaRate.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Select Tea Rate").show();

        } else if (!txtTeaSalary.getText().matches(NUMBER_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Tea Salary").show();

        } else if (!txtFinalSalary.getText().matches(NUMBER_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Final Salary").show();

        } else if (!txtOtherSalary.getText().matches(NUMBER_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Other Work Salary").show();

        } else {
            try {
                double teaSalary = Double.parseDouble(txtTeaSalary.getText());
                double expenseSalary = Double.parseDouble(txtOtherSalary.getText());
                double finalSalary = teaSalary + expenseSalary;

//            int rateId, int employeeId, String employeeName, double teaSalary, double expenseSalary, double finalSalary, Month month, LocalDate date
                PaymentDTO paymentDTO = new PaymentDTO(selectRateid, selectEmpid, selecetEmpName, teaSalary, expenseSalary, finalSalary, selectedMonth, LocalDate.now());
                boolean result = paymentDAO.savePayment(paymentDTO);

                if (result) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success !");
                    alert.setHeaderText("Payment Successfully.");
                    alert.show();
                    refreshTable();
//                clearFields();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error !");
                    alert.setHeaderText("Payment Successfully.");
                    alert.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void updatePayment() {
        PaymentDTO selectedID = tableView.getSelectionModel().getSelectedItem();

        if (selectedID == null) {
            new Alert(Alert.AlertType.ERROR, "Select Row").show();

        } else if (cmbEmployee.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Select Employee").show();

        } else if (monthCombo.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Select Month").show();

        } else if (cmbTeaRate.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Select Tea Rate").show();

        } else if (!txtTeaSalary.getText().matches(NUMBER_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Tea Salary").show();

        } else if (!txtFinalSalary.getText().matches(NUMBER_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Final Salary").show();

        } else if (!txtOtherSalary.getText().matches(NUMBER_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Other Work Salary").show();

        } else {

        }
        try {

            double teaSalary = Double.parseDouble(txtTeaSalary.getText());
            double expenseSalary = Double.parseDouble(txtOtherSalary.getText());
            double finalSalary = teaSalary + expenseSalary;


            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Update");
            confirmAlert.setHeaderText("Update Payment Record");
            confirmAlert.setContentText(
                    "Are you sure you want to Update Employee Name: "
                            + selectedID.getEmployeeName() + " ?");

            Optional<ButtonType> confirm = confirmAlert.showAndWait();

            if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                int paymentID = selectedID.getPaymentId();

//           int paymentId int rateId, int employeeId, String employeeName, double teaSalary, double expenseSalary, double finalSalary, Month month, LocalDate date
                PaymentDTO paymentDTO = new PaymentDTO(paymentID, selectRateid, selectEmpid, selecetEmpName, teaSalary, expenseSalary, finalSalary, selectedMonth, LocalDate.now());
                boolean result = paymentDAO.updatePayment(paymentDTO);

                if (result) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success !");
                    alert.setHeaderText("Payment Update Successfully.");
                    alert.show();
                    refreshTable();
                    clearFields();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error !");
                    alert.setHeaderText("Payment Update Successfully.");
                    alert.show();
                }
            }


        } catch (Exception e) {

        }
    }

    @FXML
    private void resetAll() {
        clearFields();
    }

    private void clearFields() {
        cmbTeaRate.setValue(null);
        cmbEmployee.setValue(null);
        monthCombo.setValue(null);
        tableView.getSelectionModel().clearSelection();
        txtOtherSalary.clear();
        txtFinalSalary.clear();
        txtTeaSalary.clear();
        lblTeaRate.setText("0");
    }

    @FXML
    private void deletePayment() {
        PaymentDTO selectTableItem = tableView.getSelectionModel().getSelectedItem();

        if (selectTableItem == null) {
            new Alert(Alert.AlertType.ERROR, "Select Row").show();

        } else {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Delete");
            confirmAlert.setHeaderText("Delete Payment Record");
            confirmAlert.setContentText(
                    "Are you sure you want to delete Employee Name: "
                            + selectTableItem.getEmployeeName() + " ?");

            Optional<ButtonType> confirm = confirmAlert.showAndWait();

            if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                try {
                    int id = selectTableItem.getPaymentId();

                    System.out.println(selectTableItem);

                    boolean result = paymentDAO.deletePayment(id);

                    if (result) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success !");
                        alert.setHeaderText("Delete Successfully.");
                        alert.show();
                        refreshTable();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error !");
                        alert.setHeaderText("Delete Successfully.");
                        alert.show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @FXML
    private void viewReport(){

        if (monthReportCombo.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select Month").show();
        } else if (yearReportCombo.getValue()==null) {
            new Alert(Alert.AlertType.ERROR,"Please Select Year").show();
        } else {
            try {
                int selectedReportYear = yearReportCombo.getSelectionModel().getSelectedItem();
                System.out.println(selectedReportMonthNumber + " - " + selectedReportYear);

                paymentDAO.printPaymentReport(selectedReportMonthNumber, selectedReportYear);
            }catch (Exception e){
                e.printStackTrace(); // keep this

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Report Error");
                alert.setHeaderText("Unable to generate Payment Report");
                alert.setContentText(e.getMessage()); // 👈 shows real cause
                alert.show();
            }
        }
    }

    private void tableSelection(PaymentDTO newValue) {
//        Set Employee ComboBox
        for (EmployeeDTO empDTO : cmbEmployee.getItems()) {
            if (empDTO.getId() == newValue.getEmployeeId()) {
                cmbEmployee.setValue(empDTO);
            }
        }

//        Set Month ComboBox
        Month valueMonth = newValue.getMonth();
        String monthName = valueMonth.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        monthCombo.setValue(monthName);

//        Set Tea Rate Date and Rate
        for (TeaRateDTO rateDTO : cmbTeaRate.getItems()) {
            if (rateDTO.getRateId() == newValue.getRateId()) {
                cmbTeaRate.setValue(rateDTO);
            }
        }

        txtTeaSalary.setText(String.valueOf(newValue.getTeaSalary()));
        txtOtherSalary.setText(String.valueOf(newValue.getExpenseSalary()));
        txtFinalSalary.setText(String.valueOf(newValue.getFinalSalary()));

    }

    private void refreshTable() {
        paymentDTOS.clear();
        paymentDTOS.addAll(loadPaymentTable());
        tableView.setItems(paymentDTOS);
    }

    private ObservableList<PaymentDTO> loadPaymentTable() {
        try {
            ObservableList<PaymentDTO> list = paymentDAO.loadPaymentTable();
            return list;
        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    private void loadOtherWorkByMonth(int selectedMonthNumber, int selectedEmpId) {

        try {

            Double otherWorkSalary = otherWorkDAO.loadOtherWorkByMonth(
                    selectedMonthNumber,
                    selectedEmpId
            );

            txtOtherSalary.setText(String.valueOf(otherWorkSalary));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void loadTeaDataByMonth(int selectedMonthNumber, int selectedEmpId) {


        try {

            Double teaSalary = dailyTeaDAO.loadTeaSalaryByMonth(
                    selectedMonthNumber,
                    selectedEmpId
            );

            txtTeaSalary.setText(String.valueOf(teaSalary));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableColumn() {

//        int paymentId, int rateId, int employeeId, String employeeName, double teaSalary, double expenseSalary, double finalSalary, LocalDate date

        col_id.setCellValueFactory(new PropertyValueFactory<PaymentDTO, Integer>("paymentId"));
        col_rateId.setCellValueFactory(new PropertyValueFactory<PaymentDTO, Integer>("rateId"));
        col_empId.setCellValueFactory(new PropertyValueFactory<PaymentDTO, Integer>("employeeId"));
        col_empName.setCellValueFactory(new PropertyValueFactory<PaymentDTO, String>("employeeName"));
        col_teaSalary.setCellValueFactory(new PropertyValueFactory<PaymentDTO, Double>("teaSalary"));
        col_otherSalary.setCellValueFactory(new PropertyValueFactory<PaymentDTO, Double>("expenseSalary"));
        col_finalSalary.setCellValueFactory(new PropertyValueFactory<PaymentDTO, Double>("finalSalary"));
        col_paymentDate.setCellValueFactory(new PropertyValueFactory<PaymentDTO, LocalDate>("date"));

        tableView.setItems(loadPaymentTable());
    }

    private void loadTeaRateCombo() {

        try {
            ObservableList<TeaRateDTO> list = teaRateBO.getAllTeaRate();
            cmbTeaRate.setItems(list);

            cmbTeaRate.setCellFactory(cb -> new ListCell<>() {
                @Override
                protected void updateItem(TeaRateDTO item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getMonth() + " - " + item.getYear());
                }
            });

            cmbTeaRate.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(TeaRateDTO item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getMonth() + " - " + item.getYear());
                }
            });

            cmbTeaRate.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    selectedTeaRate = newVal.getRate();
                    selectRateid = newVal.getRateId();
                    lblTeaRate.setText(String.valueOf(selectedTeaRate));
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMonthsAndYears() {
        monthCombo.setItems(FXCollections.observableArrayList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        ));

        monthReportCombo.setItems(FXCollections.observableArrayList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        ));

        // Year range
        ObservableList<Integer> years = FXCollections.observableArrayList();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear - 1; i <= currentYear + 1; i++) {
            years.add(i);
        }
        yearReportCombo.setItems(years);

        monthCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedMonth = Month.valueOf(newVal.toUpperCase());
                selectedMonthNumber = selectedMonth.getValue();
                selectedYear = LocalDate.now().getYear();
                System.out.println(selectedYear + " - " + selectedMonthNumber + " - " + selectedMonth);
            }
        });

        monthReportCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedReportMonth = Month.valueOf(newVal.toUpperCase());
                selectedReportMonthNumber = selectedReportMonth.getValue();
                System.out.println( selectedReportMonthNumber + " - " + selectedReportMonth);
            }
        });


    }

    private void loadEmployees() {
        try {
            ObservableList<EmployeeDTO> list = employeeBO.getAllEmployees();
            cmbEmployee.setItems(list);

            cmbEmployee.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    selectEmpid = newVal.getId();
                    selecetEmpName = newVal.getName();
                    System.out.println(newVal.getName() + " - " + newVal.getId());
                }
            });

            cmbEmployee.setCellFactory(cb -> new ListCell<>() {
                @Override
                protected void updateItem(EmployeeDTO item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getName());
                }
            });

            cmbEmployee.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(EmployeeDTO item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openTeaRateWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/ceylonteapay/TeaRate.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.setTitle("Tea Rate");
            stage.show();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
