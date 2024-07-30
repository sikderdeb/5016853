interface Notifier{
    public String send();
}

class EmailNotifier implements Notifier{
    public String send(){
        return "Email notification";
    }
}

abstract class NotifierDecorator implements Notifier{
    Notifier notifier;
    NotifierDecorator(Notifier notifier){
        this.notifier = notifier;
    }
    public String send(){
        return notifier.send();
    }
}

class SMSNotifierDecorator extends NotifierDecorator{
    public SMSNotifierDecorator(Notifier notifier){
        super(notifier);
    }
    public String send(){
        return super.send() + ", SMS notification";
    }
}
class SlackNotifierDecorator extends NotifierDecorator{
    public SlackNotifierDecorator(Notifier notifier){
        super(notifier);
    }
    public String send(){
        return super.send() + ", Slack notification";
    }
}

class DecoratorPatternTest{
    public static void main(String[] args) {
        EmailNotifier notifier = new EmailNotifier();
        Notifier sms = new SMSNotifierDecorator(notifier);
        Notifier slack = new SlackNotifierDecorator(notifier);
        Notifier combine = new SlackNotifierDecorator(sms);
        System.out.println(notifier.send());
        System.out.println(sms.send());
        System.out.println(slack.send());
        System.out.println(combine.send());
    }
}