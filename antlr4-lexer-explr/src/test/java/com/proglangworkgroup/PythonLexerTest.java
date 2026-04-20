package com.proglangworkgroup;

import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.Python.PythonLexer;

import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class PythonLexerTest {

    private static final String LOG_FILE = "logs/py-tests.log";

    @BeforeAll
    static void setup() throws Exception {
        new PrintWriter(LOG_FILE).close();
    }

    private void logHeader(String testName, String input) {
        System.out.println("Running: " + testName);
    }

    // ========================
    // FLOAT
    // ========================

    @Test
    void pyFloatAccepted1() {
        String input = "10.5";
        logHeader("pyFloatAccepted1", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.FLOAT_NUMBER, LOG_FILE));
    }

    @Test
    void pyFloatAccepted2() {
        String input = ".75";
        logHeader("pyFloatAccepted2", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.FLOAT_NUMBER, LOG_FILE));
    }

    @Test
    void pyFloatRejected1() {
        String input = "10_000.5";
        logHeader("pyFloatRejected1", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.FLOAT_NUMBER, LOG_FILE));
    }

    @Test
    void pyFloatRejected2() {
        String input = "abc";
        logHeader("pyFloatRejected2", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.FLOAT_NUMBER, LOG_FILE));
    }

    // ========================
    // STRING
    // ========================

    @Test
    void pyStringAccepted1() {
        String input = "\"hello\"";
        logHeader("pyStringAccepted1", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.STRING, LOG_FILE));
    }

    @Test
    void pyStringAccepted2() {
        String input = "'''multi\nline'''";
        logHeader("pyStringAccepted2", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.STRING, LOG_FILE));
    }

    @Test
    void pyStringRejected1() {
        String input = "\"unclosed";
        logHeader("pyStringRejected1", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.STRING, LOG_FILE));
    }

    @Test
    void pyStringRejected2() {
        String input = "hello";
        logHeader("pyStringRejected2", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.STRING, LOG_FILE));
    }

    // ========================
    // IDENTIFIER
    // ========================

    @Test
    void pyIdentifierAccepted1() {
        String input = "variavel";
        logHeader("pyIdentifierAccepted1", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.NAME, LOG_FILE));
    }

    @Test
    void pyIdentifierAccepted2() {
        String input = "_valor";
        logHeader("pyIdentifierAccepted2", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.NAME, LOG_FILE));
    }

    @Test
    void pyIdentifierRejected1() {
        String input = "123abc";
        logHeader("pyIdentifierRejected1", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.NAME, LOG_FILE));
    }

    @Test
    void pyIdentifierRejected2() {
        String input = "for";
        logHeader("pyIdentifierRejected2", input);

        var lexer = new PythonLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.NAME, LOG_FILE));
    }
}