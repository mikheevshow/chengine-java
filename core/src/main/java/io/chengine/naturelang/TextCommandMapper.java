package io.chengine.naturelang;

import io.chengine.command.Command;

import javax.annotation.Nullable;

public interface TextCommandMapper {

    void mapAll(Command command, String... text);

    void map(Command command, String text);

    @Nullable
    Command get(String text);

}
