package io.chengine;

import io.chengine.annotation.Command;
import io.chengine.annotation.Handler;

@Handler
public class SomeHandler {

	// Will Create
	// **********************************************
	// *       someButton1   *  someButton2         *
	// **********************************************
	// *                 someButton3                *
	// **********************************************
	// * someButton4  *  someButton4  * someButton5 *
	// **********************************************
	//
	@Command("/somecommand")
	@InlineKeyboard(rows = {
		"{someButton1, someButton2}",
		"{someButton3}",
		"{someButton4, someButton4, someButton5}"
	})
	public void getSomeInlineKeyboard() {

	}

}
