package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML ComboBox<String> cb_type = new ComboBox<>();
    @FXML ListView<String> lv_basic_toppings = new ListView<>();
    @FXML ListView<String> lv_all_extras = new ListView<>();
    @FXML ListView<String> lv_selected_extras = new ListView<>();
    @FXML Button btn_addTopping;
    @FXML Button btn_removeTopping;
    @FXML ImageView sandwich_image;
    @FXML Button btn_clear;
    @FXML Button btn_addOrder;
    @FXML Button btn_showOrder;
    @FXML TextArea textarea_price;
    @FXML TextArea tv_area;
    Image image_chicken;
    Image image_beef;
    Image image_fish;
    ObservableList<String> basic_toppings;
    ObservableList<String> selected_extras = FXCollections.observableArrayList();
    ObservableList<String> all_extras;

    Order list= new Order();


    /**
     *  Initialize this controller after the root element has been completely processed
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     * @author Kanishka Borana
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> type_list = FXCollections.observableArrayList("Chicken","Beef","Fish");
        cb_type.setItems(type_list);
        cb_type.setValue("Chicken");

        //adding the default list view for basic ingredients
        basic_toppings = FXCollections.observableArrayList("Fried Chicken", "Spicy Sauce", "Pickles");
        lv_basic_toppings.setItems(basic_toppings);
        display_price("Chicken", 0);


        all_extras = FXCollections.observableArrayList("lettuce", "tomato", "onion", "bacon", "mushrooms", "spinach", "pickles", "american", "provolone", "swiss");
        lv_all_extras.setItems(all_extras);

        lv_all_extras.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lv_selected_extras.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        image_chicken = new Image("file:///Users/kanishkaborana/Desktop/cs213 software meth/my projects/Project4/src/sample/chicken.jpeg");
        sandwich_image.setImage(image_chicken);
        image_beef = new Image("file:///Users/kanishkaborana/Desktop/cs213 software meth/my projects/Project4/src/sample/beef.jpeg");
        image_fish = new Image ("file:///Users/kanishkaborana/Desktop/cs213 software meth/my projects/Project4/src/sample/fish.jpeg");
        lv_basic_toppings.setDisable(true);
    }

    /**
     * Lists all the sandwich style
     * @author Kanishka Borana
     */
    public void on_cb_type()
    {
        set_default();
        String type=cb_type.getSelectionModel().getSelectedItem();
        if (type.equals("Chicken"))
        {
            sandwich_image.setImage(image_chicken);
            basic_toppings = FXCollections.observableArrayList("Fried Chicken", "Spicy Sauce", "Pickles");
            display_price(type, 0);
        }
        else if(type.equals("Beef"))
        {
            sandwich_image.setImage(image_beef);
            basic_toppings = FXCollections.observableArrayList("Roast Beef", "Provolone Cheese", "Mustard");
            display_price(type, 0);
        }
        else if (type.equals("Fish"))
        {
            sandwich_image.setImage(image_fish);
            basic_toppings = FXCollections.observableArrayList("Grilled Snapper", "Cilantro", "Lime");
            display_price(type,0);
        }
        lv_basic_toppings.setItems(basic_toppings);
    }

    /**
     * Adds the selected topping to the selected list View.
     * @author Kanishka Borana
     */
    public void on_adding_extras()
    {
        String type = cb_type.getSelectionModel().getSelectedItem();
        ObservableList<String> selected =  lv_all_extras.getSelectionModel().getSelectedItems();
        tv_area.clear();

        //checking if limit is exceeded
        if((selected.size() + selected_extras.size()) >= 7)
        {
            //tv_area.clear();
            displayText("Sorry... cannot have more than 6 extra toppings");
            return;
        }

        for(int i=0 ; i<selected.size() ; i++)
        {
            String topping= selected.get(i);
            if(lv_selected_extras.getItems().contains(topping))
            {
                //tv_area.clear();
                displayText("Extras: " + topping + " is already present.");
                return;
            }
            selected_extras.add(topping);
            lv_selected_extras.setItems(selected_extras);
        }
        display_price(type, selected_extras.size());
    }

    /**
     * Adds the extra topping to selected list
     * @param topping topping to be added
     * @author Kanishka Borana
     */
    /*
    private void add_extras_to_selected(String topping){
        tv_area.clear();

        //checking the topping is already present

    }
    */

    /**
     * Removes the selected topping from the selected toppings list
     * @author Kanishka Borana
     */
    public void on_remove_topping()
    {
        String type = cb_type.getSelectionModel().getSelectedItem();
        ObservableList<String> remove_list =  lv_selected_extras.getSelectionModel().getSelectedItems();

        //adding extras to temp variable
        ArrayList<String> temp= new ArrayList<>();
        for(int i=0; i< remove_list.size(); i++)
        {
            temp.add(remove_list.get(i));
        }

        for(int i=0; i< temp.size(); i++)
        {
            selected_extras.remove(temp.get(i));
        }

        lv_selected_extras.setItems(selected_extras);
        display_price(type, selected_extras.size());
    }

    private void display_price(String type, int extras)
    {
        textarea_price.clear();
        double sum=0.0;
        if(type.equals("Chicken"))
            sum=8.99;
        else if(type.equals("Beef"))
            sum=10.99;
        else if(type.equals("Fish"))
            sum=12.99;

        sum= sum + (extras*1.99);
        textarea_price.appendText(String.format("%.2f", sum));
    }

    /**
     * Set all the fields to default
     * @author Kanishka Borana
     */
    public void on_clear(){
        cb_type.setValue("Chicken");
        basic_toppings = FXCollections.observableArrayList("Fried Chicken", "Spicy Sauce", "Pickles");
        lv_basic_toppings.setItems(basic_toppings);
        display_price("Chicken", 0);
        set_default();
    }

    /**
     * Set all the fields to default
     * @author Kanishka Borana
     */
    private void set_default() {
        lv_selected_extras.getItems().clear();
        selected_extras.clear();
        lv_selected_extras.setDisable(false);
        lv_all_extras.setDisable(false);
        btn_addTopping.setDisable(false);
        btn_clear.setDisable(false);
        btn_removeTopping.setDisable(false);
        lv_basic_toppings.setDisable(true);
    }


    /**
     * Add the current order to the Sandwich orders
     * @author Kanishka Borana
     */
    public void on_add_to_order()
    {
        OrderLine order_to_add;
        Sandwich add_sandwich= null;
        String type = cb_type.getSelectionModel().getSelectedItem();

        //convert extras into arraylist of EXTRA
        ArrayList<String> s_extras = new ArrayList<>(selected_extras);
        ArrayList<Extra> extras= new ArrayList<>();
        for(int i=0; i<s_extras.size(); i++)
        {
            extras.add(new Extra(s_extras.get(i)));
        }

        if(type.equals("Chicken"))
        {
            add_sandwich= new Chicken(extras);
        }
        else if(type.equals("Beef"))
        {
            add_sandwich= new Beef(extras);
        }
        else if(type.equals("Fish"))
        {
            add_sandwich= new Fish(extras);
        }

        //handling null exceptions
        if(add_sandwich == null)
        {
            displayText("Error in adding order");
            return;
        }

        order_to_add= new OrderLine(list.size(), add_sandwich, add_sandwich.price());
        list.add(order_to_add);
        set_default();
        displayText(type+ " sandwich is added to your cart.");
        cb_type.setValue("Chicken");
        display_price("Chicken", 0);

    }

    /**
     * Display text in the text area
     * @param to_display String to display
     * @author Kanshka Borana
     */
    private void displayText(String to_display)
    {
        tv_area.appendText(to_display + "\n");
    }

    /**
     * Open a new window to show the total order
     * @author Kanishka Borana
     */

    public void on_showOrder(){

        set_default();
        textarea_price.clear();
        tv_area.clear();
        cb_type.setValue("Chicken");
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("second.fxml"));
            Parent root = loader.load();
            Controller2 cont2 = loader.getController();
            cont2.print_order(list);
            cont2.get_controller(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Sandwich cart");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
