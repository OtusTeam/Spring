package ru.otus.example.conditionalandprofilesdemo.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.example.conditionalandprofilesdemo.model.base.Friend;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class ConditionalAndProfilesDemoCommands {

    private final List<Friend> partyMembers;

    @ShellMethod(value = "Print party members", key = {"print-party-members", "ppm"})
    public String printPartyMembers() {
        return partyMembers.stream().map(Friend::getName).collect(Collectors.joining("\n"));
    }
}
