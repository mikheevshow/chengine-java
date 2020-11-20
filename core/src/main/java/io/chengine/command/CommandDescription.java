package io.chengine.command;

import java.lang.annotation.*;

/**
 * Annotation is used to add a description to {@link HandleCommand}
 * At this time, the description must match the specified format.
 * Further development is planned due to the addition of locale
 * <p>
 * Example of adding a description:
 * Over the method to add a description for, you need to annotate
 * <pre>
 *      @CommandDescription(description = "{RU-ru : Method description}, {EN-en : Method description}");
 * </pre>
 * After that, when calling the /help command, the BOT will describe this command
 * as specified in the description, taking into account the localization.
 *
 * @author Andrey Borisov
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandDescription {

    String[] description() default "";

}
