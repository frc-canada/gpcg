package src.GPCG;

public class Company {

    private String Name;
    private String Price;
    private String Customers;
    private String Revenue;
    private String Expenses;
    private String Profit;
    private String Total;

    public Company(String Name, String Price, String Customers, String Revenue, String Expenses, String Profit, String Total) {
        this.Name = Name;
    this.Price = Price;
    this.Customers = Customers;
	this.Revenue = Revenue;
	this.Expenses = Expenses;
	this.Profit = Profit;
	this.Total = Total;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getCustomers() {
        return Customers;
    }

    public void setCustomers(String Customer) {
        this.Customers = Customer;
    }

    public String getRevenue() {
        return this.Revenue;
    }

    public void setRevenue(String Revenue) {
        this.Revenue = Revenue;
    }
    public String getExpenses() {
        return Expenses;
    }

    public void setExpenses(String Expenses) {
        this.Expenses = Expenses;
    }
    public String getProfit() {
        return Profit;
    }

    public void setProfit(String Profit) {
        this.Profit = Profit;
    }
    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }
}


