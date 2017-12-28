/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springmvc.util;

import java.util.HashMap;

import org.springframework.stereotype.Component;

/**
 * @author Somnath
 * 
 * This class has marked as Bean, so it can be instantiate using @Autowired annotation.
 * In order to search this bean, we need to use @ComponentScan on Configuration class.
 */

@Component
public class TrieNode {
    public HashMap<Character, TrieNode> child;
    public boolean isEndOfWord;

    public TrieNode() {
        child=new HashMap<Character, TrieNode>();
        isEndOfWord=false;
    }
    
    @Override
    public String toString()
    {
        return child.toString();
    }
}
