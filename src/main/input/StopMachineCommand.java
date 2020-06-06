package main.input;

public class StopMachineCommand implements Command{
    @Override
    public CommandType getCommandType() {
        return CommandType.STOP_MACHINE;
    }
}
