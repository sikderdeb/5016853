import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Product {
    int productId;
    String productName;
    String category;
    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
    public int getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public String getCategory() {
        return category;
    }
}

class LinearSearch {
    public Product linearSearch(ArrayList<Product> products, int productId) {
        for(Product product : products) 
        {
            if (product.getProductId() == productId) 
                return product;
        }
        return null;
    }
}

class Sortbyid implements Comparator<Product> {
    public int compare(Product a, Product b)
    {
        return a.productId- b.productId;
    }
}

class BinarySearch{
    public Product binarSearch(ArrayList<Product> products, int productId)
    {
        int l=0,u=products.size()-1;
        while(l<=u)
        {
            int mid=(l+u)/2;
            if(products.get(mid).getProductId()==productId)
                return products.get(mid);
            else if(products.get(mid).getProductId()>productId)
                u=mid-1;
            else if(products.get(mid).getProductId()<productId)
                l=mid+1;
        }
        return null;
    }
}

class Search{
    public static void main(String[] args) 
    {
        LinearSearch ls=new LinearSearch();
        BinarySearch bs=new BinarySearch();
        Scanner sc=new Scanner(System.in);

        //Taking user Input
        ArrayList<Product> products=new ArrayList<>();
        while(true)
        {
            System.out.println("Enter Product ID:");
            int productId = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Name:");
            String productName = sc.nextLine();
            System.out.println("Enter Category:");
            String category = sc.nextLine();
            products.add(new Product(productId, productName, category));
            System.out.println("Enter 1 to exit input and any no for continue:");
            if(sc.nextInt()==1)
                break;
        }
        System.out.println("Enter Product ID to search:");
        int search = sc.nextInt();
        Product foundProduct= ls.linearSearch(products, search);
        if(foundProduct!=null)
            System.err.println("The Product is present");
        else
            System.out.println("The Product is not present");
        Collections.sort(products,new Sortbyid());
        foundProduct= bs.binarSearch(products, search);
        if(foundProduct!=null)
            System.err.println("The Product is present");
        else
            System.out.println("The Product is not present");
        
        sc.close();
    }
}

