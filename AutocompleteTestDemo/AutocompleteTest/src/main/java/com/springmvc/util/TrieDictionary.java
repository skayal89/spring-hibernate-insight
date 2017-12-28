/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springmvc.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springmvc.model.Employee;

/**
 * @author Somnath
 * This class has marked as Bean, so it can be instantiate using @Autowired annotation.
 * In order to search this bean, we need to use @ComponentScan on Configuration class.
 */
@Component
public class TrieDictionary {
    
	@Autowired
    private TrieNode root;

	// we do not need to manually set root, @Autowired do this for us.
	// To make @Autowired annotation work, we need to mark TrieNode class
	// as Bean using @Component or @Service or @Repository.
    /*public TrieDictionary(TrieNode root) {
        this.root=root;
    }*/

    public TrieNode getRoot() {
        return root;
    }

    public void setRoot(TrieNode root) {
        this.root = root;
    }
    
    
    
    /*public static void main(String[] ar)
    {
        TrieNode root=new TrieNode();
        TrieDictionary dictionary=new TrieDictionary(root);
        String str[]=new String[]{"cat","dog","duno", "come", "done", "do"};
        for (String str1 : str) {
            System.out.println(str1);
            dictionary.insert(str1.toCharArray());
        }
        System.out.println(dictionary.search("dog".toCharArray()));
        dictionary.delete("cat".toCharArray(), 0);
        System.out.println(root);
        
        System.out.println("Suggestion");
        String suggestion[]=dictionary.suggest("do");
        System.out.println("{");
        for(String sugg : suggestion){
            if(sugg!=null){
                System.out.println("\t"+sugg);
            }
        }
        System.out.println("}");
    }*/
    
    public void buildDictionary(List<Employee> employees){
    	for(Employee emp : employees){
    		insert(emp.getName().toLowerCase().toCharArray());
    	}
    }
    
    public void insert(char[] word)
    {
        TrieNode temp=root;
        for(int i=0;i<word.length;i++)
        {
            TrieNode node=temp.child.get(word[i]);
            if(node==null)
            {
                node = new TrieNode();
                temp.child.put(word[i], node);
            }
            temp=node;
        }
        System.out.println(root);
        temp.isEndOfWord=true;
    }
    
    public boolean search(char[] word)
    {
        TrieNode temp=root;
        for(int i=0; i<word.length; i++)
        {
            TrieNode node=temp.child.get(word[i]);
            if(node==null)
            {
                return false;
            }
            temp=node;
        }
        if(temp.isEndOfWord)
            return true;
        return false;
    }
    
    public List<String> suggest(String exp){
        TrieNode temp=root;
        //this array will hold individual words
        char[] ch=new char[26]; // size should be the max depth of trie
        List<String> result=new ArrayList<String>();
        int i=0;
        // traverse upto common characters (similar to longest prefix)
        for(;i<exp.length();i++){
            TrieNode node=temp.child.get(exp.charAt(i));
            if(node==null){
                // if the word is not present
                //System.out.println(exp); 
            	result.add(exp);
                return result;
            }
            // if word exist, add char to output array
            ch[i]=exp.charAt(i);
            // forward temp to search next char
            temp=node;
        }
        // traverse all branches (childs) to generate all words starting 
        // with exp and store in result array.
        dfs(temp,result,ch,i);
        return result;
    }
    
    private void dfs(TrieNode dict, List<String> result, char[] current, int index){
        if(dict==null)  return;
        if(dict.isEndOfWord){
            // found a word starting with exp, so add it to result set
            result.add(new String(current, 0, index));
            // new String(char[] ch, int startPoint, int noOfChar)
            //System.out.println(new String(current, 0, index)); // print the word
        }
        
        if(isLastNode(dict)){
            return;
        }
        for(Map.Entry<Character, TrieNode> entry : dict.child.entrySet()){
            current[index]=entry.getKey();
            dfs(entry.getValue(),result,current,index+1);
        }
    }
    
    private boolean isLastNode(TrieNode t){
        return t.child.size()==0;
    }
    
    boolean delete(char[] word, int i)
    {
        if(i==word.length)
        {
            if(!root.isEndOfWord)
                return false;
            root.isEndOfWord=false;
            return root.child.size()==0;
        }
        TrieNode temp=root.child.get(word[i]);
        if(temp==null)
            return false;
        boolean shouldDelete=delete(word, i+1);
        if(shouldDelete)
        {
            root.child.remove(word[i]);
            return root.child.size()==0;
        } 
        return false;
    }
    
}
