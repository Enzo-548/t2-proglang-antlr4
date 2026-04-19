package com.proglangworkgroup;

import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import com.example.Python.PythonLexer;

import static org.junit.jupiter.api.Assertions.*;

public class PythonLexerTest {

    // ========================
    // FLOAT
    // ========================

    @Test
    void pyFloatAccepted1() {
        var lexer = new PythonLexer(CharStreams.fromString("10.5"));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.FLOAT_NUMBER));
    }

    @Test
    void pyFloatAccepted2() {
        var lexer = new PythonLexer(CharStreams.fromString(".75"));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.FLOAT_NUMBER));
    }

    @Test
    void pyFloatRejected1() {
        var lexer = new PythonLexer(CharStreams.fromString("10_000.5"));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.FLOAT_NUMBER));
    }

    @Test
    void pyFloatRejected2() {
        var lexer = new PythonLexer(CharStreams.fromString("abc"));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.FLOAT_NUMBER));
    }

    // ========================
    // STRING
    // ========================

    @Test
    void pyStringAccepted1() {
        var lexer = new PythonLexer(CharStreams.fromString("\"hello\""));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.STRING));
    }

    @Test
    void pyStringAccepted2() {
        var lexer = new PythonLexer(CharStreams.fromString("'''multi\nline'''"));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.STRING));
    }

    @Test
    void pyStringRejected1() {
        var lexer = new PythonLexer(CharStreams.fromString("\"unclosed"));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.STRING));
    }

    @Test
    void pyStringRejected2() {
        var lexer = new PythonLexer(CharStreams.fromString("hello"));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.STRING));
    }

    // ========================
    // IDENTIFIER
    // ========================

    @Test
    void pyIdentifierAccepted1() {
        var lexer = new PythonLexer(CharStreams.fromString("variavel"));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.NAME));
    }

    @Test
    void pyIdentifierAccepted2() {
        var lexer = new PythonLexer(CharStreams.fromString("_valor"));
        assertTrue(LexerApp.validateInput(lexer, PythonLexer.NAME));
    }

    @Test
    void pyIdentifierRejected1() {
        var lexer = new PythonLexer(CharStreams.fromString("123abc"));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.NAME));
    }

    @Test
    void pyIdentifierRejected2() {
        var lexer = new PythonLexer(CharStreams.fromString("for"));
        assertFalse(LexerApp.validateInput(lexer, PythonLexer.NAME));
    }
}