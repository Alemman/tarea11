package uaslp.enginering.labs.model;

public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
    public boolean equals(Student obj) {
=======
    public boolean equals(Object obj) {
>>>>>>> d23eb1464d66af21a67eba86d76cc81c4234d4c4
        if (!(obj instanceof Student)) {
            return false;
        }
        Student otherStudent = (Student) obj;

        return this.name.equals(otherStudent.name);
    }
}
