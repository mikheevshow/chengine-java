package io.chengine.testhandlers;

import io.chengine.annotation.HandleCommand;
import io.chengine.annotation.CommandParameter;
import io.chengine.annotation.Handler;

@Handler
public class TestHandlerSomeCommand {

	@HandleCommand("/product#/color#")
	public void sayHi(@CommandParameter("product") Long id, @CommandParameter("color") String color) {

	}

}
