class Logger{
    private static Logger instance;
    private Logger(){

    }
    public static Logger getInstance()
    {
        if(instance==null)
            instance=new Logger();

        return instance;
    }
    public void log(String msg){
        System.out.println(msg);
    }
}

class SingletonTest{
    public static void main(String[] args) {
        Logger l1=Logger.getInstance();
        Logger l2=Logger.getInstance();
        l1.log("Hello World");
        l2.log("How are you?");
        if(l1==l2)
            System.out.println("Both have same instance.");
        else
            System.out.println("Both have different instances.");
    }
}