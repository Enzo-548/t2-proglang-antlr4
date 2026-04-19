package com.proglangworkgroup;

import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import com.example.JavaScript.JavaScriptLexer;

import static org.junit.jupiter.api.Assertions.*;

public class JavaScriptLexerTest {

    // ========================
    // FLOAT
    // ========================

    @Test
    void jsFloatAccepted1() {
        var lexer = new JavaScriptLexer(CharStreams.fromString("10.50"));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.DecimalLiteral));
    }

    @Test
    void jsFloatAccepted2() {
        var lexer = new JavaScriptLexer(CharStreams.fromString(".25"));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.DecimalLiteral));
    }

    @Test
    void jsFloatRejected1() {
        var lexer = new JavaScriptLexer(CharStreams.fromString("."));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.DecimalLiteral));
    }

    @Test
    void jsFloatRejected2() {
        var lexer = new JavaScriptLexer(CharStreams.fromString("abc"));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.DecimalLiteral));
    }

    // ========================
    // STRING
    // ========================

    @Test
    void jsStringAccepted1() {
        var lexer = new JavaScriptLexer(CharStreams.fromString("\"hello\""));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.StringLiteral));
    }

    @Test
    void jsStringAccepted2() {
        var lexer = new JavaScriptLexer(CharStreams.fromString("'world'"));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.StringLiteral));
    }

    @Test
    void jsStringRejected1() {
        var lexer = new JavaScriptLexer(CharStreams.fromString("\"unclosed"));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.StringLiteral));
    }

    @Test
    void jsStringRejected2() {
        var lexer = new JavaScriptLexer(CharStreams.fromString("f\"hello\""));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.StringLiteral));
    }

    // ========================
    // IDENTIFIER
    // ========================

    @Test
    void jsIdentifierAccepted1() {
        var lexer = new JavaScriptLexer(CharStreams.fromString("minhaVar"));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.Identifier));
    }

    @Test
    void jsIdentifierAccepted2() {
        var lexer = new JavaScriptLexer(CharStreams.fromString("$valor"));
        assertTrue(LexerApp.validateInput(lexer, JavaScriptLexer.Identifier));
    }

    @Test
    void jsIdentifierRejected1() {
        var lexer = new JavaScriptLexer(CharStreams.fromString("123abc"));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.Identifier));
    }

    @Test
    void jsIdentifierRejected2() {
        var lexer = new JavaScriptLexer(CharStreams.fromString("var"));
        assertFalse(LexerApp.validateInput(lexer, JavaScriptLexer.Identifier));
    }
}