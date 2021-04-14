# OrderingSandwiches
A software system that allows customers to customize sandwiches and place an order.
For simplicity, let’s abstract away the details of the customer who is ordering the sandwiches, and abstract away the checkout functionality. However, your software meets the following functional requirements:

1) The system provides the options of 3 types of sandwiches, Chicken, Beef and Fish. Each type of sandwiches has its basic ingredients. Customers can customize the sandwiches by adding extra ingredients to the sandwiches.
2) The default sandwiches selected on the GUI are be set to Chicken.
3) Upon the selection of the sandwich type, an image of the sandwich is displayed on the GUI, together
with the basic ingredients and the price of the sandwich.
4) The system provides a list of at least 10 extra ingredients for the customer to choose from. The customer
can add or remove the extra ingredients.
5) Maximum number of extra ingredients added to the selected sandwich will be 6. The customer can also add
the sandwich to the order as is, without adding any extra ingredients.
6) When the customer is adding the extra ingredients, the system does not allow the same ingredients to be
added more than once.
7) The system keeps track of the sandwich price and display the price while the customer is adding and
removing the ingredients.
8) The customer can add multiple sandwiches to an order. Each sandwich is identified by a serial number in the
order; that is, each added sandwich is an order line on the order.
9) The system is able to show the order details with a list of sandwiches added in a new window.
10) For each sandwich in the order, the system prints out the serial number (line number), sandwich type,
the list of basic ingredients, extra ingredients and the price. At the end of the list, a total amount of the order
is displayed.
11) In the order details window, the customer can
• select a sandwich on the order and add the same sandwich to the order with a new serial number.
• select a sandwich on the order and remove the sandwich from the order and reorder the serial numbers on
the order (move everyone up.)
• clear the order; that is, remove all the sandwiches on the order and start a new order; in this case, the
serial number will be reset.
12) The system is able to save the order to a file, one order at a time.
