package com.addressBookSystem;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class AddressBook4{
	// class variable
    static final Scanner scanner = new Scanner(System.in);
    static Set<String> emptyContacts = new HashSet<>();
    static Set<String> nonEmptyContacts = new HashSet<>();

    // instance method
    void createNewContact() throws Exception 
    {
        System.out.print("Enter name of the contact:");
        String contactName = scanner.nextLine();
        File file = new File(contactName);
        if (file.exists()) 
        {
            System.out.println("contact " + file.getName() + " already exists!");
        } 
        else 
        {
            if (file.createNewFile()) 
            {
                System.out.println("new contact " + file.getName() + " is created successfully");
                emptyContacts.add(file.getName());
            } 
            else 
            {
                System.out.println("file creation failed!");
            }
        }
    }
    void writeFile(String fileName,String content)throws Exception
    {
        FileWriter fw = new FileWriter(fileName);
        fw.write(content);
        fw.close();
        nonEmptyContacts.add(fileName);
        System.out.println("the given contents are successfully added in " + fileName);
    }
    void fillContactDetails()throws Exception
    {
        System.out.print("enter empty contact name which is going to be filed:");
        String contactName = scanner.nextLine();
        if (emptyContacts.contains(contactName)) 
        {
            String details = "";
            System.out.print("enter first name:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter last name:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter address:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter city:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter state:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter zip:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter phone number:");
            details += scanner.nextLine() + "\n";
            writeFile(contactName, details);
            emptyContacts.remove(contactName);
        }
        else 
        {
            System.out.println(contactName + " is not empty contact or it is not created");
            System.out.println("use other option 1 to create new contact or option 5 to edit already created one");
        }
    }
    void readFile(String fileName)throws Exception
    {
        FileReader fileReader = new FileReader(fileName);
        int character;
        while ((character = fileReader.read()) != -1) 
        {
            System.out.print((char) character);
        }
        fileReader.close();
    }	    
    void editContactInfo()throws Exception
    {
        System.out.print("enter name of the contact to edit:");
        String contactName = scanner.nextLine();
        if(emptyContacts.contains(contactName))
        {
            System.out.println("pleast fill the contact "+ contactName + " before editing it");
            return;
        }
        else if(!nonEmptyContacts.contains(contactName))
        {
            System.out.println("please create the contact "+ contactName +" before editing it");
            return;
        }
        System.out.println("The content of " + contactName + " at present is:");
        readFile(contactName);
        System.out.println("Enter the 7 lines of  new content to write:");
        String newContent="";
        for (int i = 1; i <= 7; i++) 
        {
            newContent += scanner.nextLine() + "\n";
        }
        String option;
        do 
        {
            System.out.println("enter... S for SAVE     SA for SAVE AS      C for CANCEL");
            option = scanner.nextLine().trim().toLowerCase();
        } while (!(option.equals("s")  || option.equals("sa")) || option.equals("c"));
        switch(option)
        {
            //the new contents are saved as givenfilename
            case "s":
                writeFile(contactName, newContent);
                System.out.println(contactName + " is saved with new content");
                break;
            //the new contents are saved as givenfilenamewithoutextension.csv
            case "sa":
                String extension = ".csv";
                String newContactName = contactName.replaceFirst("[.][^.]+$", "") + extension;
                writeFile(newContactName, newContent);
                nonEmptyContacts.add(newContactName);
                break;
            case "c":
                System.out.println("changes are not saved");
                return;
            default:
                System.out.println("please select either S or C");
        }        
    }
    void deleteContact()
    {
        System.out.print("enter contact name to delete:");
        String contactName = scanner.nextLine().trim();
        File file = new File(contactName);
        if(file.exists())
        {
            if(file.delete())
            {
                if(emptyContacts.contains(contactName))
                {
                    emptyContacts.remove(contactName);
                }
                else
                {
                    nonEmptyContacts.remove(contactName);
                }
                System.out.println(contactName + " is deleted succesfully");
            }
            else
            {
                System.out.println("file deletion failed");
            }
        }
        else
        {
            System.out.println(contactName + "doesn't exists");
        }
    }
}
public class UC4_DeleteContact {
	  // class variable
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception 
    {
        AddressBook4 addressBook = new AddressBook4();
        while (true) 
        {
            System.out.println("-----------------------------");
            System.out.println("1.create a new contact");
            System.out.println("2.fill contact details");	          
            System.out.println("3.edit contact information");
            System.out.println("4.delete contact information");
            System.out.println("5.exit");
            System.out.print("enter option:");
            String option = scanner.nextLine().trim();
            switch (option) 
            {
                case "1":
                    addressBook.createNewContact();
                    break;
                case "2":
                    addressBook.fillContactDetails();
                    break;	             
                case "3":
                    addressBook.editContactInfo();
                    break;
                case "4":
                    addressBook.deleteContact();
                    break;
                case "5":
                    System.out.println("exiting....");
                    System.exit(0);
                default:
                    System.out.println("please enter the correct choice");
                    break;
            }
        }
    }
}