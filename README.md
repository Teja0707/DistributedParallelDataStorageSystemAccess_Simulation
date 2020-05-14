# DistributedParallelDataStorageSystemAccess_Simulation
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
Simulation of query search using Global primary and
secondary Index in Java
OBJECTIVE:
The Primary objective of this project is to understand and simulate the parallel data storage
system access. The Project will cover how the indexing is done over distributed databases for
efficient query retrieval i.e. creation of Global and local indices over multiple records which are
usually stored across different nodes. The concept of Global primary index and secondary index
will be introduced, and their applicability will be simulated.
The distributed storage systems are useful for efficient access of record storage and retrieval over
billions of data. Indexing over them is an overhead as records are over multiple tablespaces.
Therefore, we create global and local indices in order to make the query search efficient.
The simulation is being done using Java by creating simple database. The Simulation of Global
primary index will be done, i.e. how the records are partitioned and how the search is guided
from Query → Global Primary Index, Global Secondary Index -→ node (local primary index).
Implementation:
1. Multiple records would be stored across different nodes with different tabular data
initially.
2. These records can be accessed locally using a local index in the second step, which would
be either a range or a hash index depending on type of primary attribute the table is stored
on locally.
3. In the third stage a global primary index would be created to access different nodes. This
will mostly be a range index where the start of each primary attribute will be stored as an
index.
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
4. In the third stage a global secondary index would be created to access different nodes.
This will mostly be a range index where the start of each secondary attribute will be
stored as an index.
5. Using this simulation simple operations will be performed such as querying, insertion.
The whole implementation is done using Java and in Eclipse IDE.
Relationship with the textbook section:
This Simulation covers Parallel Indexing concept of the textbook, section 21.5 where query
retrieval over parallel data storage system using global primary index and secondary index is
discussed.
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
fig : tb.
Methodology used:
Tables with Citizens (SSN, Name,Phone,Address) are created for simplicity and are
distributed over multiple nodes (Java objects).
The Simulation is being done using java.
5 different classes are created:
• Record
• Node
• PrimaryIndex
• SecondaryIndex
• Query
1. The Record class is a general record where different records are stored in this format.
It has an integer type variable called SSN and String type variables called Name, Phone number
and address.
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
The Record class consists of methods getters and setters used for storing and retrieving record
given SSN -> Name or given Name -> SSN and Phone and Address.
2. The Node class is used to simulate a computer or a server or a virtual node where
multiple records are stored. The records are partitioned over multiple nodes.
Node uses record as object and methods such as addRecord(), localSearch() and toString()
methods are created.
addRecord() method is used to append records to each node. The size of each node is static for
simplicity and the option is given for the user while simulation. In general, size of each node
varies.
The localSearch() method is used to search records locally, given SSN the Name of the record is
retrieved. We use linear search in the project. In Realtime since large number of records are
stored a binary tree search or key-value pairs are used for retrieval.
The SSN is the local index on which nodes are partitioned and used for query retrieval.
The toString() method is used to retrieve the Node value, and the record when a search is
performed.
3. The PrimaryIndex class consists of collection of nodes. These nodes are created
dynamically, and the option is given to the user while simulation. It has setGlobalIndex(),
getGlobalIndex() and globalSearch() methods.
The setGlobalIndex() uses b-tree format and creates indices using the partitioned nodes. In
general, we use the first value of each local primary index to create the global index and mapping
them to the nodes.
The globalSearch() method is used to search for a record globally. The global search maps the
SSN to the node on which the record is stored.
The toString() method returns the GlobalIndex of the record, and the node on which the record is
stored.
The getGlobalIndex() method returns the Global Index table created.
4. The SecondarytIndex class consists collection of nodes. We aim to simulate materialized
view over Name using this class. The code format of secondaryIndex is similar to that of
the primary index.
5. The Query Class is the main class where the simulation is performed. The Database
object is called initially, and user is given the option to input number of nodes and
records. Once the user enters the node values and the records, the table on which how
nodes are distributed is displayed.
The Global indices are displayed in order to show how the list initially is created for a search.
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
SSN is the Global primary index for the simulation.
Name is the Global Secondary index for the simulation.
A Search query is performed. User should be able to enter SSN of a person and the Global
Primary Index, Local Primary Index, the node on which the record is stored and the record itself
is given as output.
Also User should be able to search for the Name of a person and the Global Secondary Index,
Local index on which the record is stored and the record itself is given as the output.
For both the searches the partition vectors will be displayed.
Discussion:
Performing the simulation helped me explore information available over the internet i.e. how the
data is stored in real time, what are the challenges in maintaining the records over multiple
nodes, how the data is partitioned over different tools such as Oracle, Dynamo DB and the
insertion, deletion anomalies etc.
Remarks:
The simulation is done with just 4 attributes namely SSN, Name, Phone and Address.
SSN is usually a 9-digit number, but we consider only 3-4 digits.
Name is a String value (i.e. name of a person) usually contains first name and last name, we
generate random string and store it in the database to make querying easy.
Phone and address are used random value, we donot perform any operations on them therefore
they are popped with same values.
In real-time the storage and retrieval is much complex as there are multiple attributes and also
many different entities with some relationship among them. These are joined, and records are
retrieved.
Conclusion:
In this way records are partitioned, and local and global primary indices are formed for efficient
storage and retrieval.
Source Code:
Class Query
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class Query {
public static void main(String[] args) {
// TODO Auto-generated method stub
Scanner scan = new Scanner(System.in);
System.out.println("Enter number of nodes for partition");
String numOfNodesAsString = scan.nextLine();
int numOfNodes = Integer.parseInt(numOfNodesAsString);
System.out.println("Enter number of records for partition");
String numOfRecordsAsString = scan.nextLine();
int numOfRecords = Integer.parseInt(numOfRecordsAsString);
PrimaryIndex primary = new PrimaryIndex(numOfNodes, numOfRecords);
SecondaryIndex secondary = new SecondaryIndex(numOfNodes, numOfRecords);
// int SSN;
// String Name;
Random rnd = new Random();
String addS = "ab";
int SSN = 100;
int namecount = 0;
int index = 0;
Record[] temp = new Record[numOfNodes * numOfRecords];
for (int i = 0; i < numOfNodes; i++) {
for (int j = 0; j < numOfRecords; j++) {
/*
* System.out.println("Enter Record " + (++records));
*
* System.out.println("Enter SSN:\n"); String stringSSN =
scan.nextLine(); int
* SSN = Integer.parseInt(stringSSN);
*
* System.out.println("Enter Name:\n"); String Name =
scan.nextLine();
*/
namecount = namecount + 1;
;
char a = (char) (rnd.nextInt(25) + 1 + 'a');
String address = "abc";
String phone = "123";
temp[index] = new Record(SSN, addS, address, phone);
index++;
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
primary.getNodes()[i].addRecord(new Record(SSN, addS,
address, phone));
secondary.getNodes()[i].addRecord(new Record(SSN, addS,
address, phone));
SSN = SSN + 100;
addS = addS + a;
}
}
Arrays.sort(temp, (Record n1, Record n2) ->
n1.getName().compareTo(n2.getName()));
System.out.println("Database before partition: ");
System.out.println(primary.beforePartition());
System.out.println("\nDatabase after partition:");
System.out.println(primary.toString());
primary.setGlobalIndex();
secondary.setGlobalIndex();
// System.out.println("List of Global Indices");
// System.out.println(Arrays.toString(primary.getGlobalIndex()));
// System.out.println(Arrays.toString(secondary.getGlobalIndex()));
System.out.println("\n");
boolean cont = true;
do {
System.out.println("Choose one of the following options:");
System.out.println("1. Search by SSN");
System.out.println("2. Search by Name");
String option = scan.nextLine();
switch (option) {
case "1":
System.out.println("Enter SSN to search:");
String search = scan.nextLine();
int searchSSN = Integer.parseInt(search);
String val = primary.globalSearch(searchSSN);
System.out.println("\n Global primary index vector\n");
System.out.println(Arrays.toString(primary.getGlobalIndex()));
System.out.println("\n");
System.out.println(val);
break;
case "2":
System.out.println("Enter Name to search:");
String name = scan.nextLine();
String found = secondary.globalSearch(name);
System.out.println("\n Global secondary index vector\n");
System.out.println(Arrays.toString(secondary.getGlobalIndex()));
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
System.out.println("\n");
System.out.println(found);
break;
}
System.out.println("Would you like to continue? Type yes or no
");
if (scan.nextLine().equalsIgnoreCase("no")) {
cont = false;
}
} while (cont);
scan.close();
}
}
Class Node
public class Node {
private Record records[];
public Record[] getRecords() {
return records;
}
public void setRecords(Record[] records) {
this.records = records;
}
Node(int numOfRecords) {
records = new Record[numOfRecords];
}
public void addRecord(Record add) {
boolean added = false;
for (int i = 0; i < records.length; i++) {
if (records[i] == null) {
records[i] = add;
added = true;
break;
}
}
if (!added) {
System.out.println("Can't add more records: Storage is full");
}
}
public String searchLocal(Integer ssn) {
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
String name = "Record not found";
if (ssn == null) {
return name;
} else {
for (int i = 0; i < records.length; i++) {
if (ssn.equals(records[i].getSSN())) {
name = "Local Primary index: " + records[i].getSSN()
+ "\nName: " + records[i].getName();
}
}
return name;
}
}
public String searchLocal(String name) {
String returnName = "Record not found";
if (name == null) {
return returnName;
} else {
for (int i = 0; i < records.length; i++) {
if (name.equals(records[i].getName())) {
returnName = "Local Primary index: " +
records[i].getSSN() + "\nName: " + records[i].getName();
}
}
return returnName;
}
}
@Override
public String toString() {
String toReturn = "";
for (int i = 0; i < records.length; i++) {
toReturn += records[i].toString() + "\n";
}
return toReturn;
}
public String beforePartition() {
String toReturn = "";
for (int i = 0; i < records.length; i++) {
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
toReturn += records[i].toString();
if(i!=records.length-1)
{
toReturn += "\n";
}
}
return toReturn;
}
}
Class Primary Index
public class PrimaryIndex {
private Node nodes[];
Integer[] globalIndex;
public PrimaryIndex(int numOfNodes, int numOfRecords) {
nodes = new Node[numOfNodes];
for (int i = 0; i < numOfNodes; i++) {
nodes[i] = new Node(numOfRecords);
}
globalIndex = new Integer[numOfNodes - 1];
}
public Integer[] getGlobalIndex() {
return globalIndex;
}
public void setGlobalIndex() {
for (int i = 1; i < nodes.length; i++) {
globalIndex[i - 1] = nodes[i].getRecords()[0].getSSN();
}
}
public String globalSearch(Integer searchSSN) {
String toReturn = "";
if (searchSSN < globalIndex[0]) {
// search in node 0
toReturn += "Global Index: " + globalIndex[0] + "\n" +
nodes[0].searchLocal(searchSSN) + "\nAt Node: 1";
} else if (searchSSN >= globalIndex[globalIndex.length - 1]) {
// search in last node
toReturn += "Global Primary Index: " +
globalIndex[globalIndex.length - 1] + "\n"
+ nodes[globalIndex.length].searchLocal(searchSSN) +
"\nAt Node: " + (globalIndex.length + 1);
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
} else {
for (int i = 1; i < globalIndex.length; i++) {
if (searchSSN < globalIndex[i] && searchSSN >=
globalIndex[i - 1]) {
// search in node i-1
toReturn += "Global Index: " + globalIndex[i - 1] +
"\n" + nodes[i].searchLocal(searchSSN)
+ "\nAt Node: " + (i + 1);
break;
}
}
}
return toReturn;
}
public Node[] getNodes() {
return nodes;
}
public void setNodes(Node nodes[]) {
this.nodes = nodes;
}
@Override
public String toString() {
String toReturn = "";
for (int i = 0; i < nodes.length; i++) {
toReturn += "Node: " + (i + 1) + "\n";
toReturn += nodes[i].toString() + "\n\n\n";
}
return toReturn;
}
public String beforePartition() {
String toReturn = "";
for (int i = 0; i < nodes.length; i++) {
toReturn += nodes[i].beforePartition() + "\n";
}
return toReturn;
}
}
Class Secondary Index :
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
public class SecondaryIndex {
private Node nodes[];
String[] globalIndex;
public SecondaryIndex(int numOfNodes, int numOfRecords) {
nodes = new Node[numOfNodes];
for (int i = 0; i < numOfNodes; i++) {
nodes[i] = new Node(numOfRecords);
}
globalIndex = new String[numOfNodes - 1];
}
public String[] getGlobalIndex() {
return globalIndex;
}
public void setGlobalIndex() {
for (int i = 1; i < nodes.length; i++) {
globalIndex[i - 1] = nodes[i].getRecords()[0].getName();
}
}
public String globalSearch(String searchName) {
String toReturn = "";
if (searchName.compareTo(globalIndex[0]) < 0) {
// search in node 0
toReturn += "Global Index: " + globalIndex[0] + "\n" +
nodes[0].searchLocal(searchName) + "\nAt Node: 1";
} else if (searchName.compareTo(globalIndex[globalIndex.length - 1]) >=
0) {
// search in last node
toReturn += "Global Secondary Index: " +
globalIndex[globalIndex.length - 1] + "\n"
+ nodes[globalIndex.length].searchLocal(searchName)
+ "\nAt Node: " + (globalIndex.length + 1);
} else {
for (int i = 1; i < globalIndex.length; i++) {
if (searchName.compareTo(globalIndex[i]) < 0 &&
searchName.compareTo(globalIndex[i - 1]) >= 0) {
// search in node i-1
toReturn += "Global Index: " + globalIndex[i - 1] +
"\n" + nodes[i].searchLocal(searchName)
+ "\nAt Node: " + (i + 1);
break;
}
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
}
}
return toReturn;
}
public Node[] getNodes() {
return nodes;
}
public void setNodes(Node nodes[]) {
this.nodes = nodes;
}
@Override
public String toString() {
String toReturn = "";
for (int i = 0; i < nodes.length; i++) {
toReturn += "Node: " + (i + 1) + "\n";
toReturn += nodes[i].toString() + "\n\n\n";
}
return toReturn;
}
}
Class Record
public class Record {
private Integer SSN;
private String Name;
private String address;
private String phone;
public String getAddress() {
return address;
}
public void setAddress(String address) {
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
this.address = address;
}
public String getPhone() {
return phone;
}
public void setPhone(String phone) {
this.phone = phone;
}
public Record(int SSN, String Name, String address, String phone) {
this.SSN = SSN;
this.Name = Name;
this.address = address;
this.phone = phone;
}
public String getRecord(String SSN) {
return this.Name;
}
public Integer getSSN() {
return SSN;
}
public void setSSN(Integer sSN) {
SSN = sSN;
}
public String getName() {
return Name;
}
public void setName(String name) {
Name = name;
}
@Override
public String toString() {
return "SSN: " + SSN + "\t Name: " + Name + "\t phone: " + phone + "\t
address: " + address;
}
}
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
Output
Enter number of nodes for partition
3
Enter number of records for partition
3
Database before partition:
SSN: 100 Name: ab phone: 123 address: abc
SSN: 700 Name: abgzxhrx phone: 123 address: abc
SSN: 300 Name: abgz phone: 123 address: abc
SSN: 800 Name: abgzxhrxi phone: 123 address: abc
SSN: 500 Name: abgzxh phone: 123 address: abc
SSN: 600 Name: abgzxhr phone: 123 address: abc
SSN: 400 Name: abgzx phone: 123 address: abc
SSN: 900 Name: abgzxhrxil phone: 123 address: abc
SSN: 200 Name: abg phone: 123 address: abc
Database after partition:
Node: 1
SSN: 100 Name: ab phone: 123 address: abc
SSN: 200 Name: abg phone: 123 address: abc
SSN: 300 Name: abgz phone: 123 address: abc
Node: 2
SSN: 400 Name: abgzx phone: 123 address: abc
SSN: 500 Name: abgzxh phone: 123 address: abc
SSN: 600 Name: abgzxhr phone: 123 address: abc
Node: 3
SSN: 700 Name: abgzxhrx phone: 123 address: abc
SSN: 800 Name: abgzxhrxi phone: 123 address: abc
SSN: 900 Name: abgzxhrxil phone: 123 address: abc
Choose one of the following options:
1. Search by SSN
2. Search by Name
1
Enter SSN to search:
600
Global primary index vector
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
[400, 700]
Global Index: 400
Local Primary index: 600
Name: abgzxhr
At Node: 2
Would you like to continue? Type yes or no
yes
Choose one of the following options:
1. Search by SSN
2. Search by Name
2
Enter Name to search:
abgzx
Global secondary index vector
[abgzx, abgzxhrx]
Global Index: abgzx
Local Primary index: 400
Name: abgzx
At Node: 2
Would you like to continue? Type yes or no
Choose one of the following options:
1. Search by SSN
2. Search by Name
2
Enter Name to search:
teja
Global secondary index vector
[abgzx, abgzxhrx]
Global Secondary Index: abgzxhrx
Record not found
At Node: 3
Would you like to continue? Type yes or no
No
CSCI 8390 Final Report Sai Teja Sirivolu
 NUID:28449008
Sample Execution:
CSCI 8390 Final Report Sai Teja Sirivolu NUID:28449008
CSCI 8390 Final Report Sai Teja Sirivolu NUID:28449008
