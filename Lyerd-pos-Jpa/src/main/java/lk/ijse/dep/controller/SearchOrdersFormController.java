package lk.ijse.dep.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dep.util.OrderTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SearchOrdersFormController {
    public TextField txtSearch;
    public TableView<OrderTM> tblOrders;
    private ArrayList<OrderTM> searchOrdersArray = new ArrayList<>();


    public void initialize() {

        tblOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderTotal"));
        loadOrderDetails();

/*
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });
*/
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ObservableList<OrderTM> search = tblOrders.getItems();
                search.clear();
                String lowerCaseFilter = newValue.toLowerCase();

                for (OrderTM order : searchOrdersArray) {
                    if ((order.getOrderId().toLowerCase().contains(lowerCaseFilter))||
                            order.getCustomerId().toLowerCase().contains(lowerCaseFilter) ||
                            order.getCustomerName().toLowerCase().contains(lowerCaseFilter) ||
                            order.getOrderDate().toString().contains(newValue) ||
                            String.valueOf(order.getOrderTotal()).contains(lowerCaseFilter)){
                        search.add(order);
                    }
                }
            }
        });

    }
    public void loadOrderDetails(){
//        try {
//            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement
//                    ("SELECT o.OrderID, o.OrderDate, o.CustomerID,c.CustomerName, SUM(od.OrderQTY * od.UnitPrice) AS Total\n" +
//                            "FROM `order` o INNER JOIN customer c ON c.CustomerID = o.CustomerID\n" +
//                            "INNER JOIN orderdetail od ON od.OrderID = o.OrderID\n" +
//                            "GROUP BY o.OrderID");
//            ResultSet rst = pstm.executeQuery();
//            ObservableList<OrderTM> searchDetails = tblOrders.getItems();
//            searchDetails.clear();
//            while(rst.next()){
//                String orderID = rst.getString("OrderID");
//                String orderDate = rst.getString("OrderDate");

/*                String customerID = rst.getString("CustomerID");
                String customerName = rst.getString("CustomerName");
                double total = rst.getDouble("Total");
                OrderTM searchitem = new OrderTM(orderID,LocalDate.valueOf(orderDate),customerID,customerName,total);
                searchDetails.add(searchitem);
                searchOrdersArray.add(searchitem);*/
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.txtSearch.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

/*    public void tblOrders_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        if (tblOrders.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (mouseEvent.getClickCount() == 2) {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/PlaceOrderForm.fxml"));
            Parent root = fxmlLoader.load();
            PlaceOrderFormController lk.ijse.dep.controller = fxmlLoader.getController();
            lk.ijse.dep.controller.initializeWithSearchOrderForm(tblOrders.getSelectionModel().getSelectedItem().getOrderId());
            Scene orderScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(orderScene);
            stage.centerOnScreen();
            stage.show();
        }
    }*/

    public void tblOrders_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        if (tblOrders.getSelectionModel().getSelectedItem() == null){
            return;
        }
        if (mouseEvent.getClickCount() == 2){
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/PlaceOrderForm.fxml"));
            Parent root = fxmlLoader.load();
            PlaceOrderFormController controller = (PlaceOrderFormController) fxmlLoader.getController();
            controller.initializeWithSearchOrderForm(tblOrders.getSelectionModel().getSelectedItem().getOrderId());
            Scene orderScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(orderScene);
            stage.centerOnScreen();
            stage.show();
        }
    }
}
