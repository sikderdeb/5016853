import java.util.ArrayList;
import java.util.Scanner;

class Order{
    int orderId;
    String customerName;
    int totalPrice;
    Order(int orderId,String customerName, int totalPrice){
        this.orderId=orderId;
        this.customerName=customerName;
        this.totalPrice=totalPrice;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
}

//Bubble Sort
class BubbleSort{
    public ArrayList<Order> bubble(ArrayList<Order> orders){
        for(int i=0;i<orders.size()-1;i++)
        {
            for(int j=0;j<orders.size()-1-i;j++)
            {
                if(orders.get(j).getTotalPrice()>orders.get(j+1).getTotalPrice())
                {
                    Order t=orders.get(j);
                    orders.set(j,orders.get(j+1));
                    orders.set(j+1,t);
                }
            }
        }
        return orders;
    }
}

//Quick Sort
class QuickSort{
    public int partition(ArrayList<Order> orders,int l,int u){
        int pivot=orders.get(u).getTotalPrice();
        int i= l-1;
        for(int j=l;j<u;j++)
        {
            if(orders.get(j).getTotalPrice() <= pivot)
            {
                i++;
                Order t=orders.get(i);
                orders.set(i,orders.get(j));
                orders.set(j,t);
            }
        }
        Order t=orders.get(i+1);
        orders.set(i+1,orders.get(u));
        orders.set(u,t);
        return i+1;
    }
    public void sort(ArrayList<Order> orders,int l,int u)
    {
        if(l<u){
            int pi=partition(orders,l,u);
            sort(orders,l,pi-1);
            sort(orders,pi+1,u);
        }
    }
    public ArrayList<Order> quick(ArrayList<Order> orders){
        sort(orders,0,orders.size()-1);
        return orders;
    }
}

class Customer {
    public static void main(String[] args) {
        BubbleSort bs=new BubbleSort();
        QuickSort qs=new QuickSort();
        Scanner sc=new Scanner(System.in);
        ArrayList<Order> orders=new ArrayList<>();
        //User Input Function
        while(true)
        {
            System.out.println("Enter Product ID:");
            int OrderId = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Name:");
            String CustomerName = sc.nextLine();
            System.out.println("Enter Price:");
            int totalPrice = sc.nextInt();
            orders.add(new Order(OrderId, CustomerName, totalPrice));
            System.out.println("Enter 1 to exit input and any no for continue:");
            if(sc.nextInt()==1)
                break;
        }

        /* Pre defined 
        orders.add(new Order(1, "Arun", 45822));
        orders.add(new Order(4, "Geeta", 35200));
        orders.add(new Order(3, "Mohit", 25000));
        orders.add(new Order(2, "Raju", 40000));
        */
        System.out.println("Original Orders: " + orders);
        System.out.println("Orders after Bubble Sort: " + bs.bubble(new ArrayList<>(orders)));
        System.out.println("Orders after Quick Sort: " + qs.quick(new ArrayList<>(orders))); 

        sc.close();
    }
}