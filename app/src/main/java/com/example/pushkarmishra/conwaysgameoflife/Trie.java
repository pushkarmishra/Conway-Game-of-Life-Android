/*
 * Author: Pushkar Mishra.
 * Date: March 2016
 *
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software
 * and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom
 * the Software is furnished to do so, subject to the condition
 * that the above ownership notice and this permission notice
 * shall be included in all copies or substantial portions
 * of the Software.
 */

package com.example.pushkarmishra.conwaysgameoflife;

import java.util.HashMap;

/**
 * This is a simple API for a
 * Trie Data structure for Strings.
 */

public class Trie {
    ;
}

class TrieNode {
    private char letter;
    private HashMap <Character, TrieNode> NextLinks;

    public TrieNode(char mLetter) {
        NextLinks = new HashMap<>();
        letter = mLetter;
    }

    public boolean insertLink(char key) {
        TrieNode getEntry = NextLinks.get(key);

        if (getEntry != null) {
            System.out.println("TrieNode::insertLink: Entry with key already exists\n");
            return false;
        }

        TrieNode newEntry = new TrieNode(key);
        NextLinks.put(key, newEntry);
        return true;
    }
}
