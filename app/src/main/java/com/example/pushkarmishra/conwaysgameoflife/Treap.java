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

/**
 * This is a simple API for a
 * Treap Data structure (Balanced
 * Binary Search Tree).
 */

public class Treap< T extends Comparable<T> > {
    private TreapNode<T> root;
    TreapNode<T> dummy; // dummy node tells whether subtree is empty

    public Treap() {
        dummy = new TreapNode<>(null, null, 0);
        dummy.priority = -1;

        root = dummy;
    }

    @SuppressWarnings("unchecked")
    private TreapNode insertRecursive(TreapNode current, T val) {
        if (current == dummy) {
            current = new TreapNode<>(dummy, val, 1);
            return current;
        }

        if (current.value.compareTo(val) < 0) {
            TreapNode temp = insertRecursive(current.rightChild, val);
            if(temp == null) {
                return null;

            } else {
                current.rightChild = temp;
            }

        } else if (current.value.compareTo(val) > 0) {
            TreapNode temp = insertRecursive(current.leftChild, val);
            if (temp == null) {
                return null;

            } else {
                current.leftChild = temp;
            }

        } else {
            System.out.format("Treap::insertRecursive: Key already exists\n");
            return null;
        }

        if (current.leftChild.priority > current.priority) {
            current = current.rotateRight();

        } else if (current.rightChild.priority > current.priority) {
            current = current.rotateLeft();
        }

        return current;
    }

    public boolean insert(T insertVal) {
        return (insertRecursive(root, insertVal) == null);
    }

    @SuppressWarnings("unchecked")
    public boolean isPresentRecursive(TreapNode current, T val) {
        if (current == dummy) {
            return false;
        }

        if (current.value.compareTo(val) < 0) {
            return isPresentRecursive(current.rightChild, val);

        } else if (current.value.compareTo(val) > 0) {
            return isPresentRecursive(current.leftChild, val);

        }

        return true;
    }

    public boolean isPresent(T val) {
        return isPresentRecursive(root, val);
    }
}

class TreapNode< T extends Comparable<T> > {
    public Integer priority;
    public T value;
    public TreapNode leftChild;
    public TreapNode rightChild;
    public Integer size;

    public TreapNode(TreapNode defaultNode, T mVal, int mSize) {
        leftChild = defaultNode;
        rightChild = defaultNode;
        value = mVal;
        size = mSize;
        priority = (int) (Math.random() * 1000000000);
    }

    TreapNode<T> rotateRight() {
        TreapNode x = this.leftChild;
        TreapNode y = x.rightChild;

        x.rightChild = this;
        this.leftChild = y;

        this.size = this.leftChild.size + this.rightChild.size + 1;
        x.size = x.leftChild.size + x.rightChild.size;

        return x;
    }

    TreapNode<T> rotateLeft() {
        TreapNode x = this.rightChild;
        TreapNode y = x.leftChild;

        x.leftChild = this;
        this.rightChild = y;

        this.size = this.leftChild.size + this.rightChild.size + 1;
        x.size = x.leftChild.size + x.rightChild.size;

        return x;
    }
}
