package com.ek.study.designPattern;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/30
 */
public class CommandPatternStudy {
    public static void main(String[] args) {

        ICommand command = new SoutCommand(new Receiver());

        Invoke invoke = new Invoke(command);

        invoke.exeAction();

    }
}

interface ICommand {
    void execute();
}

class SoutCommand implements ICommand {

    private Receiver receiver;

    public SoutCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.doSomething();
    }
}

class PlayCommand implements ICommand {

    private Receiver receiver = null;

    public PlayCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.play();
    }
}

class StopCommand implements ICommand {

    private Receiver receiver;

    public StopCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.stop();
    }
}


class Invoke {

    private ICommand iCommand;

    public Invoke(ICommand iCommand) {
        this.iCommand = iCommand;
    }

    public void exeAction() {
        this.iCommand.execute();
    }
}

class Receiver {
    public void doSomething() {
        System.out.println("I am doing something");
    }

    public void play() {
        System.out.println("play the audio");
    }

    public void stop() {
        System.out.println("stop the audio");
    }

}


