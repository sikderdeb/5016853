import java.util.ArrayList;
import java.util.List;

interface Stock{
    public void register(Observer observer);
    public void deregister(Observer observer);
    public void notifyObserver();
}

class StockMarket implements Stock{
    private double stockPrice;
    private final List<Observer> observers;
    StockMarket() {
        observers = new ArrayList<>();
    }
    public void register(Observer observer) {
        observers.add(observer);
    }
    public void deregister(Observer observer) {
        observers.remove(observer);
    }
    public  void  notifyObserver() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }
    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObserver();
    }
}

interface Observer{
    public void update(double stockPrice);
}

class MobileApp implements Observer{
    private double stockPrice;
    public void update(double stockPrice) {
        this.stockPrice = stockPrice;
        display();
    }
    public void display()
    {
        System.out.println("MobileApp Stock Price updated: " + stockPrice);
    }
}

class WebApp implements Observer{
    private double stockPrice;
    public void update(double stockPrice) {
        this.stockPrice = stockPrice;
        display();
    }
    public void display()
    {
        System.out.println("WebApp Stock Price updated: " + stockPrice);
    }
}

class ObserverPatternExample{
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        MobileApp mobileApp = new MobileApp();
        WebApp webApp = new WebApp();
        stockMarket.register(mobileApp);
        stockMarket.register(webApp);
        stockMarket.setStockPrice(125.65);
        stockMarket.setStockPrice(625.35);
        stockMarket.deregister(mobileApp);
        stockMarket.setStockPrice(325.85);


    }
}
