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
 * Trie Data structure for
 * Strings (which may contain letters,
 * numbers, other characters).
 */

public class Trie {
    TrieNode root = null;

    public Trie() {
        root = new TrieNode();
    }

    public boolean insertString(String str) {
        TrieNode curNode = root;

        for (int i = 0; i < str.length(); i += 1) {
            TrieNode newNode = curNode.getLetter(str.charAt(i));
            if(newNode == null) {
                boolean ok = curNode.insertLink(str.charAt(i));
                if(!ok) {
                    System.out.format("Trie::insertString: Insert of character %c failed\n", str.charAt(i));
                    return false;
                }

                newNode = curNode.getLetter(str.charAt(i));
                assert(newNode != null);
            }

            curNode = newNode;
        }

        return true;
    }

    public boolean isPresent(String str) {
        TrieNode curNode = root;

        for (int i = 0; i < str.length(); i += 1) {
            TrieNode newNode = curNode.getLetter(str.charAt(i));
            if (newNode == null) {
                return false;
            }

            curNode = newNode;
        }

        return true;
    }
}

class TrieNode {
    private HashMap <Character, TrieNode> NextLinks;

    public TrieNode() {
        NextLinks = new HashMap<>();
    }

    public boolean insertLink(Character key) {
        TrieNode getEntry = NextLinks.get(key);

        if (getEntry != null) {
            System.out.format("TrieNode::insertLink: Entry with key %c already exists\n", key);
            return false;
        }

        TrieNode newEntry = new TrieNode();
        NextLinks.put(key, newEntry);
        return true;
    }

    public TrieNode getLetter(Character key) {
        TrieNode getEntry = NextLinks.get(key);

        if (getEntry != null) {
            return getEntry;
        }

        return null;
    }
}
