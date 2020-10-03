package uaslp.enginering.labs.list;
import uaslp.enginering.labs.model.*;

public class Node {
    private Student student;
    private Node previous;
    private Node Next;

    public void setStudent(Student student){
        this.student = student;
    }

    public Student getStudent(){
        return student;
    }

    public void setPrevious(Node previous){
        this.previous = previous;
    }

    public Node getPrevious(){
        return previous;
    }

    public void setNext(Node Next){
        this.Next = Next;
    }

    public Node getNext(){
        return Next;
    }
}