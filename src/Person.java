public class Person implements Comparable<Person>{
    int age;

    Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Person o) {
        return -o.age-this.age;
    }
}
