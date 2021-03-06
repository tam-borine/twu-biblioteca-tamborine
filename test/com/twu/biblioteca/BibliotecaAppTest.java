package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.*;

import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.mockito.Mockito.*;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by tam on 13/01/2017.
 */
public class BibliotecaAppTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock MainMenu menu;

    BibliotecaApp app;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        app = new BibliotecaApp(menu);
    }

    @After
    public void tearDown() throws Exception {
        //note to self: look up what this does !!!!
    }

    @Test
    public void weCanGetUserInput() {
        InputStream in = new ByteArrayInputStream("some input".getBytes());
        assertEquals("some input", app.getInput(new Scanner(in)));
    }

    @Test
    public void weCanOutputMessagesToUser() {
        InputStream in = new ByteArrayInputStream("some INVALID input\nquit".getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        app.askUserToSelectOptionUntilQuits(in);
        String expectedOutput  = "Enter an option in the list or 'quit'\n" +
                "invalid option try again\n" +
                "Enter an option in the list or 'quit'\n";
        // Notice the \n for new line. PAULO HELP THE ABOVE LINE IS sorta UGLY...
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void noSuchElementExceptionIsThrownWhenUserDoesNotQuit() throws NoSuchElementException {
        InputStream in = new ByteArrayInputStream("some input".getBytes());
        exception.expect(NoSuchElementException.class); //line before line that will throw exception
        app.askUserToSelectOptionUntilQuits(in); //should throw exception
    }

    @Test
    public void noExceptionIsThrownWhenUserQuits() {
        InputStream in = new ByteArrayInputStream("quit".getBytes());
        app.askUserToSelectOptionUntilQuits(in);
    }

    @Test
    public void selectOptionIsCalledByMainLoop() {
        InputStream in = new ByteArrayInputStream("List Books\nquit".getBytes());
        when(menu.optionIsValid(anyString())).thenReturn(true);
        app.askUserToSelectOptionUntilQuits(in);
        //verify selectOption has been called with args:
        verify(menu).selectOption("List Books");
        verify(menu).selectOption("quit");
    }

}



/// available in junit 4.13 version BUT it's unstable atm:
//    assertThrows(NoSuchElementException.class,
//                () -> app.askUserToSelectOptionUntilQuits(in));
//}