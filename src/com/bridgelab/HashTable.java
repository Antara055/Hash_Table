package com.bridgelab;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashTable {
    private int noOfArray;
    private int size;
    private HNode array[];

  //default constructor
    public HashTable() {
        this(100);
    }

    public HashTable(int input) {
        this.noOfArray = input;
        this.array = new HNode[noOfArray];
        this.size = 0;
    }

    //creating Node to store two type value like linked list
    public class HNode {
        private Integer key;
        private String value;
        private HNode next;
      // Hash Node constructor
        public HNode(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    //getIbdex method will generate the index number and retun it
    public int getIndex(Integer key) {
        int indexNo = key % noOfArray;
        return indexNo;
    }

    //putData method will put data into Hash Table
    private void putData(Integer key, String value) {
        if (key == null || value == null) {
            System.out.println("Either Key or the Value is null");
        }
        int index = getIndex(key);
        HNode head = array[index];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = array[index];
        HNode node = new HNode(key, value);
        node.next = head;
        array[index] = node;
    }

    //Checking given string no.of times in Hash Table
    public int frequenceCheck(String value) {
        HNode node = array[0];
        int count = 0;
        while (node != null) {
            if (node.value.equals(value)) {
                count++;
            }
            node = node.next;
        }
        return count;
    }

    //this method check the value of given key and return corresponding value
    public String getKey(Integer key){
        int arrayIndex=getIndex(key);
        HNode head=array[arrayIndex];
        while (head!=null){
            if(head.key.equals(key)){
                return head.value;
            }
            head=head.next;
        }
        return null;
    }

    //
    public void frequencOfWords(String value){
        Map<String, Integer> map=new HashMap<>();
        String array[]=value.split(" ");
        for (int i=0;i<array.length;i++){
            if (map.containsKey(array[i])){
                map.put(array[i],map.get(array[i]+1));
            }
            else
                map.put(array[i],1);
        }
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
    }

    //this method will remove word
    public void removeWord(String value){
        Map<String, Integer> map = new HashMap<>();

        Iterator<String> word = map.keySet().iterator();
        while (word.hasNext()){
            if (word.next().equals(value)){
                word.remove();
            }
        }
    }

    //main method
    public static void main(String[] args) {
        //System.out.println("*********************HASH TABLE**********************");
        //creating object of HashTable
        HashTable hashTable = new HashTable();
        //putting data to HasgTable
        hashTable.putData(10, "To");
        hashTable.putData(20, "Be");
        hashTable.putData(30, "Or");
        hashTable.putData(40, "Not");
        hashTable.putData(50, "To");
        hashTable.putData(60, "Be");

        //Checking how many times those value comes
        System.out.println("To comes " + hashTable.frequenceCheck("To")+ " times");
        System.out.println("Be comes " + hashTable.frequenceCheck("Be") + " times");
        System.out.println("Or comes " + hashTable.frequenceCheck("Or") + " times");
        System.out.println("Not comes " + hashTable.frequenceCheck("Not") + " times");

        //Checking size of the HashTable
        System.out.println("The size of hash table : " + hashTable.size);

        //Checking the values of the key
        System.out.println("Finding Given key value : "+hashTable.getKey(10));

        //checking the frequency of word in paragraph
        String paragraph = "paranoids are not paranoids because they are paranoid but " +
                "they keep putting themselves deliberately into paranoid avoidable situation";
        System.out.println("Given Para String : " + paragraph);
        hashTable.frequencOfWords(paragraph);

        //removing word "but"
        hashTable.removeWord("but");
    }
}
