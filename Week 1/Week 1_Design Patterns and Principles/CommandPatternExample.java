interface Command{
    void execute();
}

class LightOnCommand implements Command{
    private Light light;
    LightOnCommand(Light light){
        this.light = light;
    }
    public void execute(){
        light.turn_on();
    }
}

class LightOffCommand implements Command{
    private Light light;
    LightOffCommand(Light light){
        this.light = light;
    }
    public void execute(){
        light.turn_off();
    }
}

//Invoker class
class RemoteControl{
    Command command;
    public void setCommand(Command command){
        this.command = command;
    }
    public void pressButton(){
        command.execute();
    }

}

//Receiver class
class Light{
    public void turn_on(){
        System.out.println("Light on");
    }
    public void turn_off(){
        System.out.println("Light off");
    }
}

class CommandPatternTest {
    public static void main(String[] args) {
        Light light = new Light();
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);
        RemoteControl remote = new RemoteControl();
        remote.setCommand(lightOnCommand);
        remote.pressButton();
        remote.setCommand(lightOffCommand);
        remote.pressButton();

    }
}