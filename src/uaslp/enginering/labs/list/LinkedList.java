package uaslp.enginering.labs.list;

import uaslp.enginering.labs.model.Student;

public class LinkedList {
    private Node front;
    private Node tail;
    private int count;

    public enum InsertPosition {
        BEFORE,
        AFTER
    }

    public class Iterator {
        private int index;
        private Node list;

        public Iterator(){
            list = front;
        }
        public boolean hasNext() {
            return index < count;
        }

        public Student next() {
            Student nextStudent;

            if(list == null) {
                nextStudent = null;
            }else {
                nextStudent = list.getStudent();

                if(list.getNext() != null)
                    list = list.getNext();
                else
                    list = null;
                index++;
            }
            return nextStudent;
        }
    }

    public void add(Student student) {
        Node newNode = new Node();
        newNode.setStudent(student);
        if(count == 0){
            front = tail = newNode;
        }else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }

        count++;
    }

    public void delete(Student student) {
        Node iteratorNode = front;

        while ((iteratorNode.getStudent().equals(student)) != true && iteratorNode != null){
            iteratorNode = iteratorNode.getNext();
        }
        if(iteratorNode == null){
            return;
        }else {
            if(front == iteratorNode){
                front = iteratorNode.getNext();
                iteratorNode.getNext().setPrevious(null);
                iteratorNode.setNext(null);
            }else if(tail == iteratorNode){
                tail = iteratorNode.getPrevious();
                iteratorNode.getPrevious().setNext(null);
                iteratorNode.setPrevious(null);
            }else {
                iteratorNode.getPrevious().setNext(iteratorNode.getNext());
                iteratorNode.getNext().setPrevious(iteratorNode.getPrevious());
                iteratorNode.setPrevious(null);
                iteratorNode.setNext(null);
            }
        }
    }

    public void delete(int index) {
        if(count == 0 || index > count || index < 0){
            return;
        }else if(tail == front){
            front = tail = null;
        }else {
            iteratorDelete(index);
        }
        count--;
    }

    private void iteratorDelete(int index){
        Node iteratorNode = front;
        Node previousNode;
        Node nextNode;
        int countIterator = 0;

        if(index == 0){
            front = front.getNext();
            front.setPrevious(null);
        }else {
            while (countIterator < index) {
                iteratorNode = iteratorNode.getNext();
                countIterator++;
            }

            previousNode = iteratorNode.getPrevious();
            previousNode.setNext(iteratorNode.getNext());
            if (iteratorNode.getNext() != null) {
                nextNode = iteratorNode.getNext();
                nextNode.setPrevious(iteratorNode.getPrevious());
            }

            //es necesrio para ser recolectaso por garbageCollector ?
            iteratorNode.setPrevious(null);
            iteratorNode.setNext(null);
        }
    }
    public Iterator getIterator() {
        return new Iterator();
    }

    public int size() {
        return count;
    }

    public Student getAt(int index) {
        Node iteratorNode = front;
        int countIterator = 0;

        while (countIterator < index && iteratorNode.getNext() != null){
            iteratorNode = iteratorNode.getNext();
            countIterator++;
        }

        return iteratorNode.getStudent();
    }

    public void insert(Student reference, Student newStudent, InsertPosition insertPosition) {
        Node iteratorNode = front;
        Node newNode = new Node();
        newNode.setStudent(newStudent);

        while ((iteratorNode.getStudent().equals(reference)) != true && iteratorNode != null){
            iteratorNode = iteratorNode.getNext();
        }

        if(iteratorNode == null){
            return;
        }else {
            reAssign(iteratorNode,newNode,insertPosition);
        }
        count++;
    }

    private void reAssign(Node iteratorNode,Node newNode,InsertPosition insertPosition){
        if(insertPosition.equals(InsertPosition.BEFORE)){
            if(iteratorNode.getPrevious() != null) {
                newNode.setPrevious(iteratorNode.getPrevious());
                iteratorNode.getPrevious().setNext(newNode);
                newNode.setNext(iteratorNode);
                iteratorNode.setPrevious(newNode);
            }else{
                newNode.setPrevious(null);
                iteratorNode.setPrevious(newNode);
                newNode.setNext(iteratorNode);
                front = newNode;
            }
            return;
        }

        if(insertPosition.equals(InsertPosition.AFTER)){
            if(iteratorNode.getNext() != null) {
                newNode.setPrevious(iteratorNode);
                newNode.setNext(iteratorNode.getNext());
                iteratorNode.getNext().setPrevious(newNode);
                iteratorNode.setNext(newNode);
            }else {
                newNode.setNext(null);
                iteratorNode.setNext(newNode);
                newNode.setPrevious(iteratorNode);
                tail = newNode;
            }
            return;
        }
    }
}

