import java.util.HashMap;
import java.util.Scanner;

class Product
{
    int productId,quantity, price;
    String productName;
    Product(int productId,String productName,int quantity, int price)
    {
        this.productId=productId;
        this.productName=productName;
        this.quantity=quantity;
        this.price=price;
    }
    public void setProductName(String productName)
    {
        this.productName=productName;
    }
    public void setQuantity(int quantity)
    {
        this.quantity=quantity;
    }
    public void setPrice(int price)
    {
        this.price=price;
    }
    public void display()
    {
        System.out.println("{ ID-> "+productId+" : Product Name: "+productName+" Quantity:  "+quantity+" Price: "+price+"}");
    }
}

class Inventory
{
    private HashMap<Integer,Product> inventory = new HashMap <Integer, Product>();
    static Scanner sc=new Scanner(System.in);

    public void add(Product obj)
    {
        if(inventory.containsKey(obj.productId))
        {
            System.out.println("This Product id is already Present.");
            return;
        }
        inventory.put(obj.productId,obj);
    }

    public void update(int productId)
    {
        if(!inventory.containsKey(productId))
        {
            System.out.println("These ProductId does not contain in the Inventory.");
            return;
        }
        Product obj= inventory.get(productId);
        obj.display();
        System.out.println("Which portion you want to update:");
        System.out.println("1. ProductName \n2. Quantity \n3. Price \n4. All");
        int ch=sc.nextInt();
        int flag=0;
        if(ch==4)
        {
            flag=1;
            ch=1;
        }
        switch (ch) {
            case 1:
                System.out.println("Enter Name:");
                sc.nextLine();
                String productname=sc.nextLine();
                obj.setProductName(productname);
                if(flag==0)
                    break;
            case 2:
                System.out.println("Enter Quantity:");
                int quantity=sc.nextInt();
                obj.setQuantity(quantity);
                if(flag==0)
                    break;
            case 3:
                System.out.println("Enter Price:");
                int price=sc.nextInt();
                obj.setPrice(price);
                if(flag==0)
                    break;
            default:
                System.out.println("Wrong Choice!!");
                break;
        }
    }

    public void delete(int productId)
    {
        if(!inventory.containsKey(productId))
        {
            System.out.println("These ProductId does not contain in the Inventory.");
            return;
        }
        inventory.remove(productId);
    }

    public static void main(String[] args) {
        Inventory obj = new Inventory();
        while(true)
        {
            System.out.println("Which operation to perform:");
            System.out.println("1. Add Product \n2. Update \n3. Remove \n4. To terminate the loop");
            int ch=sc.nextInt();
            int productId,quantity, price;
            String productName;
            switch (ch) {
                case 1:
                    System.out.println("Enter ProductID:");
                    productId=sc.nextInt();
                    System.out.println("Enter Name:");
                    sc.nextLine();
                    productName=sc.nextLine();
                    System.out.println("Enter Quantity:");
                    quantity=sc.nextInt();
                    System.out.println("Enter Price:");
                    price=sc.nextInt();
                    Product p = new Product(productId, productName, quantity, price);
                    obj.add(p);
                break;
                case 2:
                    System.out.println("Enter ProductID:");
                    productId=sc.nextInt();
                    obj.update(productId);
                break;
                case 3:
                    System.out.println("Enter ProductID:");
                    productId=sc.nextInt();
                    obj.delete(productId);
                break;
                default:
                    System.out.println("Wrong Choice!!");
                break;
            }
            if(ch==4)
                break;
        }
    }
}