//package com.ek.study.tx.tmp;
//
//import java.util.HashMap;
//import java.util.Map;
//import lombok.Data;
//
///**
// * <p>please add class desc</p>
// *
// * @author lazyman
// * @version 1.0.0
// * @date 2021/8/25
// */
//public class LruCache {
//
//
//    private Map<String, String> dataMap;
//
//    private NodeList privaligeNodeList;
//
//    private int size;
//
//    public LruCache(int size){
//        this.size = size;
//        dataMap = new HashMap<>(size << 1);
//        privaligeNodeList = new NodeList();
//    }
//
//
//    public String get(String key){
//        String result =  dataMap.get(key);
//
//        if(null != result){
//            privaligeNodeList.reRange(key);
//
//        }
//        return result;
//
//    }
//
//    public void put(String key, String value){
//
//        //if(dataMap.containsKey(key) ){
//           // dataMap.put(key, value);
//
//        //}else{
//            dataMap.put(key, value);
//         //   rangeNodeList(key);
//        //}
//
//        //if( dataMap.size() > size ){
//            rangeNodeList(key);
//        //}
//    }
//
//
//    private void rangeNodeList(String key){
//
//        privaligeNodeList.put(new Node(key));
//
//        while(privaligeNodeList.size() > size){
//            privaligeNodeList.removeFromTail();
//        }
//    }
//
//
//}
//
//class NodeList{
//
//    private Node head ;
//    private Node tail ;
//    private int count;
//
//    public NodeList(){
//        head = new Node(null);
//        tail = new Node(null);
//        count = 0;
//        head.setNext(tail);
//        head.setPre(null);
//        tail.setPre(head);
//        tail.setNext(null);
//    }
//
//
//    public int size(){
//        return count;
//    }
//    public void put(Node node){
//        Node tmp = head.getNext();
//        node.setNext(tmp);
//        head.setNext(node);
//        tmp.setPre(node);
//        node.setPre(head);
//        count ++;
//
//    }
//    public void removeFromTail(){
//
//        Node rmNode = tail.getPre();
//        rmNode.getPre().setNext(tail);
//        tail.setPre(rmNode.getPre());
//
//        count--;
//    }
//
//    public void removeNode(Node node){
//        node.getPre().setNext(node.getNext());
//        node.getNext().setPre(node.getPre());
//        count--;
//    }
//
//    public void reRange(String key){
//
//        Node currentNode = head;
//
//        while(currentNode.getNext() != null){
//
//            if(key.equalsIgnoreCase(currentNode.getKey()) ){
//
//                removeNode(currentNode);
//                put(currentNode);
//            }
//        }
//    }
//
//}
//
//@Data
//class Node{
//
//    private Node next;
//    private Node pre;
//
//    private String key;
//
//    public Node(String key){
//        this.key = key;
//    }
//
//
//
//}
//
