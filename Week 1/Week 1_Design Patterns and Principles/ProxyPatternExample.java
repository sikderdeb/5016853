interface Image{
    void display();
}

class RealImage implements Image{
    String fileName;
    public RealImage(String fileName){
        this.fileName = fileName;
        load();
    }
    private void load(){
        System.out.println("Loading " + fileName);
    }
    public void display(){
        System.out.println("Displaying " + fileName);
    }
}

class ProxyImage implements Image{
    private RealImage realImage;
    String fileName;
    public ProxyImage(String fileName){
        this.fileName = fileName;
    }
    public void display() {
        if(realImage == null)
            realImage = new RealImage(fileName);
        realImage.display();
    }
}

class ProxyPatternExample{
    public static void main(String[] args) {
        Image image = new ProxyImage("image.jpg");
        //Image will be loaded from disk
        image.display();
        //Image will not be loaded again
        image.display();
    }
}