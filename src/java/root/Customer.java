package root;

import java.util.Date;
 
public class Customer{
 
	public long customerID;
	public String name;
	public String address;
	public Date created_date;

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public long getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getCreated_date() {
        return created_date;
    }
 
	
}