package com.proglangworkgroup;

import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.JavaScript.JavaScriptLexer;

import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class JavaScriptLexerTest {

    private static final String LOG_FILE = "logs/js-tests.log";

    @BeforeAll
    static void setup() throws Exception {
        // limpa o arquivo antes de rodar os testes
        new PrintWriter(LOG_FILE).close();
    }

    private void logHeader(String testName, String input) {
        System.out.println("Running: " + testName);
    }

    // ========================
    // FLOAT
    // ========================

    @Test
    void jsFloatAccepted1() {
        String input = "10.50";
        logHeader("jsFloatAccepted1", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.DecimalLiteral, LOG_FILE));
    }

    @Test
    void jsFloatAccepted2() {
        String input = ".25";
        logHeader("jsFloatAccepted2", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.DecimalLiteral, LOG_FILE));
    }

    @Test
    void jsFloatRejected1() {
        String input = ".";
        logHeader("jsFloatRejected1", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.DecimalLiteral, LOG_FILE));
    }

    @Test
    void jsFloatRejected2() {
        String input = "abc";
        logHeader("jsFloatRejected2", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.DecimalLiteral, LOG_FILE));
    }

    // ========================
    // STRING
    // ========================

    @Test
    void jsStringAccepted1() {
        String input = "\"hello\"";
        logHeader("jsStringAccepted1", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.StringLiteral, LOG_FILE));
    }

    @Test
    void jsStringAccepted2() {
        String input = "'world'";
        logHeader("jsStringAccepted2", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.StringLiteral, LOG_FILE));
    }

    @Test
    void jsStringRejected1() {
        String input = "\"unclosed";
        logHeader("jsStringRejected1", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.StringLiteral, LOG_FILE));
    }

    @Test
    void jsStringRejected2() {
        String input = "f\"hello\"";
        logHeader("jsStringRejected2", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.StringLiteral, LOG_FILE));
    }

    // ========================
    // IDENTIFIER
    // ========================

    @Test
    void jsIdentifierAccepted1() {
        String input = "minhaVar";
        logHeader("jsIdentifierAccepted1", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.Identifier, LOG_FILE));
    }

    @Test
    void jsIdentifierAccepted2() {
        String input = "$valor";
        logHeader("jsIdentifierAccepted2", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.Identifier, LOG_FILE));
    }

    @Test
    void jsIdentifierRejected1() {
        String input = "123abc";
        logHeader("jsIdentifierRejected1", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.Identifier, LOG_FILE));
    }

    @Test
    void jsIdentifierRejected2() {
        String input = "var";
        logHeader("jsIdentifierRejected2", input);

        var lexer = new JavaScriptLexer(CharStreams.fromString(input));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.Identifier, LOG_FILE));
    }
}