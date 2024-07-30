class Computer{
    String CPU, RAM, Storage;
    Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.Storage = builder.Storage;
    }
    static class Builder{
        String CPU, RAM, Storage;
        Builder(String CPU, String RAM, String Storage){
            this.CPU = CPU;
            this.RAM = RAM;
            this.Storage = Storage;
        }
        public Computer build(){
            return new Computer(this);
        }
    }

    public String getCPU() {
        return CPU;
    }
    public String getRAM() {
        return RAM;
    }
    public String getStorage() {
        return Storage;
    }
}

class BuilderPatternTest{
    public static void main(String[] args) {
        Computer computer = new Computer.Builder("AMD Ryzen 7 5800H","16GB","512GB SSD").build();
        System.out.println("CPU: " + computer.getCPU());
        System.out.println("RAM: " + computer.getRAM());
        System.out.println("Storage: " + computer.getStorage());
    }
}
