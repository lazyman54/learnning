package com.ek.study.tx.tmp;

import lombok.Data;

/**
 * <p>please add class desc</p>
 *
 * @author lazyman
 * @version 1.0.0
 * @date 2021/8/25
 */
public class ListPartRerang {


    public static void main(String[] args){

    }


    public void reRangList(NodeList nodeList, int index){

        int i = 0;

        while(i < index){




        }







    }

}
    class NodeList{

        private com.ek.study.tx.tmp.Node head ;
        private com.ek.study.tx.tmp.Node tail ;
        private int count;

        public NodeList(){
            head = new com.ek.study.tx.tmp.Node(null);
            tail = new com.ek.study.tx.tmp.Node(null);
            count = 0;
            head.setNext(tail);
            head.setPre(null);
            tail.setPre(head);
            tail.setNext(null);
        }


        public int size(){
            return count;
        }
        public void put(com.ek.study.tx.tmp.Node node){
            com.ek.study.tx.tmp.Node tmp = head.getNext();
            node.setNext(tmp);
            head.setNext(node);
            tmp.setPre(node);
            node.setPre(head);
            count ++;

        }
        public void removeFromTail(){

            com.ek.study.tx.tmp.Node rmNode = tail.getPre();
            rmNode.getPre().setNext(tail);
            tail.setPre(rmNode.getPre());

            count--;
        }

        public void removeNode(com.ek.study.tx.tmp.Node node){
            node.getPre().setNext(node.getNext());
            node.getNext().setPre(node.getPre());
            count--;
        }

        public void reRange(String key){

            com.ek.study.tx.tmp.Node currentNode = head;

            while(currentNode.getNext() != null){

                if(key.equalsIgnoreCase(currentNode.getKey()) ){

                    removeNode(currentNode);
                    put(currentNode);
                }
            }
        }

    }

    @Data
    class Node{

        private com.ek.study.tx.tmp.Node next;

        private String data;

        public Node(String key){
            this.data = key;
        }



    }


