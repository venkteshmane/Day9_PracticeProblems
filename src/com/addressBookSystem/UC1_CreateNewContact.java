package com.addressBookSystem;

class AddressBook {
	String firstName, lastName, address, city, state, phoneNo, email;
    long zip;
    public void setDetails(){

        firstName = "Venktesh";
        lastName = "Mane";
        address = "Pune";
        city ="Pune";
        state = "Maharashtra";
        zip = 411043;
        phoneNo = "9527872718";
        email = "venktesh.mane18@gmail.com";
    }
    public void printValue(){

        System.out.println("First Nmae : " + firstName  + "\nLast Name : " + lastName + "\nAddress : " + address + "\nCity : " + city + "\nState : " + state + "\nZip : " + zip + "\nPhone Number : " + phoneNo + "\nE-mail : " + email); 
    }
}
public class UC1_CreateNewContact {
	public static void main(String[] args) {
		System.out.println("Welcome In Adress Book Program On Master Branch");

		AddressBook contact = new AddressBook();
		contact.setDetails();
		contact.printValue();

	}
}