package io.chengine.testhandlers;

import io.chengine.annotation.Command;
import io.chengine.annotation.CommandDescription;
import io.chengine.annotation.CommandParameter;
import io.chengine.annotation.Handler;

@Handler
public class TestHandlerSomeCommand {

	@Command("/product#/color#")
	public void sayHi(@CommandParameter("product") Long id, @CommandParameter("color") String color) {

	}
}
