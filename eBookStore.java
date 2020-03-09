package MySQL;

import java.sql.*;
import java.util.Scanner;

public class eBookStore {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		String jdbc_url = "jdbc:mysql://localhost:3306/ebookstore";
		String username = "root";
		String password = "timzo4527";
		
		
		try {
			Connection conn = DriverManager.getConnection(jdbc_url, username, password);
			
			if (conn != null) {
				System.out.println("Connected to the database!");
			}
			
			 	Scanner input = new Scanner(System.in);
	            int selection = 0;
	          
	           //Menu for action selection and user input
	           while(selection != 5) {
	           System.out.println("Please enter the number corresponding to the action you would like to take:\n"
	                        + "1. Enter book\n"
	                        + "2. Update book\n"
	                        + "3. Delete book\n"
	                        + "4. Search books\n"
	                        + "0. Exit");
	            selection = input.nextInt();
	                
	            //Selection sorting 
	            if(selection == 1) {
	           
	            System.out.println("insert a book.");
				Scanner myReader= new Scanner(System.in);
				//the scanner asks a user to prompt in information about the book
				System.out.println("Enter a New ID ");
				String myID = myReader.nextLine();
				
				System.out.println("Enter a title ");
				String thisTitle = myReader.nextLine();
				
				System.out.println("Enter a Author ");
				String myAuthor= myReader.nextLine();
				
				System.out.println("Enter a Qty ");
				String myQty = myReader.nextLine();
				//sql query
				String sql_insert = "insert into books (id, title, author, qty) values ('"+myID+"','"+ thisTitle +"','"+myAuthor+"','"+myQty+"')";
				Statement statement = conn.createStatement();
			 
			    
			    int rowsInserted = statement.executeUpdate(sql_insert);
				if (rowsInserted > 0) {//success message
				    System.out.println("A new book is inserted successfully!");
				}else {//failure message
					System.out.println("An error has occured");
				}
				   // selection sorting 2
				} else if(selection == 2) {
					System.out.println("Update a book");
					Statement statement1 = conn.createStatement();
					Scanner update = new Scanner(System.in);
					//ask a user to enter the qty of the book
					System.out.println("Please enter a new quantity of the book you updating");
					String qty = update.nextLine();
					//ask a user to enter the ID number of the book
					System.out.println("Please enter the ID no. of the book");
					String id = update.nextLine();
					//sql  query
					String sql_update = "update  books set qty = '" + qty + "' where id = '" + id + "'";
					
				    int count = statement1.executeUpdate(sql_update);
				    if (count > 0) {//success message
				    	System.out.println("Database updated successfully. \n "
				    			+ "Updated queries: "+ count);
				    }else {//failure message
				    	System.out.println("An error has ocurred");
				    }
				    
					//selection sorting
				} else if(selection == 3) {
					  System.out.println("Delete a book");
					  Scanner delete_book = new Scanner(System.in);
					  
					  System.out.println("Enter an ID no of the book you want to delete");
					  String deleteBook = delete_book.nextLine();
					  //sql query
				      String sql_delete = "delete from books where id = " + deleteBook + "";
				      Statement statement3 = conn.createStatement();
				      //int count counts the record if its found and deleted
				      int count = statement3.executeUpdate(sql_delete);
				      if(count > 0 ) {//success message
				    	  System.out.println("You have deleted a book successfully.\n"
				    	  		+ "book delected: " + count);
				      }else {//failure message
				    	  System.out.println("An error has ocurred");
				      }
				      
				      //selection sorting
				}else if(selection == 4){
					  System.out.println("Search for books");
					  Statement statement = conn.createStatement();
					  Scanner search = new Scanner(System.in);
					  //ask a user to enter to enter a ID number of the book
					  System.out.println("Enter the ID number of the book you are looking for");
					  String idno = search.nextLine();
					  //ask a user to enter the name of the author 
					  System.out.println("Enter the name of the author you are searching.");
					  String nameOfAuthor = search.nextLine();
					  //select query
					  String sql_select = "Select * from books where id ='"+ idno +"' and author ='"+ nameOfAuthor +"'";
					  
					  ResultSet rs =statement.executeQuery(sql_select);
						//to print the resultset on console
						if(rs.next())
						{
							System.out.println("Information about "+ nameOfAuthor +"'s book");
							do{//system out prints the data in horizontal line
								System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4));
							}while(rs.next());
						}
						else
						{//failure message
							System.out.println("Record Not Found...");
						}
						
						//selection sorting
				}else if(selection == 0) {
					//when users enters '0' they will exit the program 
					System.out.println("You have successfully exited the program.");
					break;
				}
				
			    conn.close();
	           }
		 }catch (SQLException ex){
			 System.out.println("failed connecting to the database");
			ex.printStackTrace();    //  Failed Connection to database.
			
		}

	}
	
}

