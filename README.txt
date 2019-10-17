Main 
- the class containing the main method where the database is created and the reading from the input file, as well
- buttons such as UPDATE, INSERT, GET are enabled here

DataBase 	
- the class containing 2 atributes: nr_of_nodes (the actual number of nodes) and max_capacity (maximum capacity of the database).
- one atribute of type ArrayList <Node> containing all the nodes in our database
- the classes most important methods are CREATEDB and SNAPSHOTDB

Node 
- the class containing 2 atributes of type ArrayList
- the first one, an ArrayList which saves all elements of type Entitaty stored inside that Node
- the second one, an ArrayList which saves all elements of type Attribute and Instances stored inside that Node, that are about to be printed once SNAPSHOTDB method gets called

Attribute
- the class containing contine the name of an Attribute and its type (float, integer, double, char)
- one atribute of type Int with the type coded as 0
- one atribute of type String with the type coded as 1
- one atribute of type Float with the type coded as 2

Val_Attribute
- the class containing the attribute's type and its actual value
- 3 attributes (Int, String, Float)
- type acordingly, only one type is actually completed at a time

Instance
- the class that saves one ArrayList of Val_Attributes

Entitate
- containing name, RF and the number of atributes
- 2 ArrayLists (one of type Attribute and one of type Instance)
- method Create_Entitaty
- method Insert_Instance
- method Delete_Instance
- method Update_Instance
