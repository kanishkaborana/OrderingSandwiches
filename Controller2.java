package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Controller2 {

    @FXML Button btn_clear;
    @FXML Button btn_back;
    @FXML Button btn_add;
    @FXML Button btn_remove;
    @FXML Button btn_save;
    @FXML ListView<String> lv_orders = new ListView<>();
    @FXML TextArea total_price;
    ObservableList<String> all_orders;
    Order main_list;

    /**
     * Displays the sandwiches along with the price in text area
     * @param list list of sandwiches
     * @author Kanishka Borana
     */
    public void print_order(Order list)
    {
        List<String> orderlist= new ArrayList<String>();
        for(int i=0 ; i<list.size() ; i++)
        {
            orderlist.add(list.get(i).toString());
        }
        all_orders= FXCollections.observableArrayList(orderlist);
        lv_orders.setItems(all_orders);
        total_price.setText("$" + String.format("%.2f", list.get_total_price()) );
    }

    /**
     * Removes all the sandwiches from the list and clears the list view
     * @author Kanishka Borana
     */
    public void on_clear()
    {
        main_list.delete();
        all_orders.clear();
        lv_orders.getItems().clear();
        total_price.setText(" $0");
        lv_orders.setDisable(false);
        btn_back.setDisable(false);
        btn_add.setDisable(false);
        btn_remove.setDisable(false);
        btn_save.setDisable(false);

    }

    /**
     * Gets the instance of the previous controller
     * @param sample_controller first controller from where this controller was called
     * @author Kanishka Borana
     */
    public void get_controller(Controller sample_controller)
    {
        main_list = sample_controller.list;
    }

    /**
     * Close the stage and go back to the previous stage
     * @author Kanishka Borana
     */
    public void on_back()
    {
        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.close();
    }

    /**
     * Adds a sandwich from the list
     * @author Kanishka Borana
     */
    public void on_add()
    {
        /*first pick what ORDERLINE to duplicate:
         * extract the line->
         * get the position of that item from the list->
         * extract the ORDERLINE from main_list using the index
         */
        if(main_list.size() == 0) {
            return;
        }

            int index= lv_orders.getSelectionModel().getSelectedIndex();
            OrderLine to_add= main_list.get(index);
            main_list.add( new OrderLine(main_list.size(), to_add.getSandwich(), to_add.getPrice()) );
            print_order(main_list);

    }

    /**
     * Removes a selected sandwich from the list
     * @author Kanishka Borana
     */
    public void on_remove()
    {
        if(main_list.size() == 0) {
            return;
        }

            int index= lv_orders.getSelectionModel().getSelectedIndex();
            OrderLine to_add= main_list.get(index);
            main_list.remove(to_add);

            //reset the order list
            for(int i=index; i< main_list.size(); i++)
            {
                OrderLine temp= main_list.get(i);
                temp.changeLineNumber(i);
            }

            print_order(main_list);


    }

    /**
     * Saves the entire order in a file
     * @author Kanishka Borana
     */
    public void on_save()
    {
        if(all_orders.size() ==  0)
        {
            Alert a = new Alert(Alert.AlertType.WARNING, "Cannot save empty order.");
            a.show();
            return;
        }
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for Saving the Order");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file

        //write code to write to the file.
        try {
            PrintWriter writer = new PrintWriter(targetFile, "UTF-8");
            //writing file
            for(int i=0; i< all_orders.size(); i++)
            {
                writer.print(all_orders.get(i) + "\n");
            }
            writer.print("Total price: "+ total_price.getText());
            writer.close();
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return;
        }
    }
}
