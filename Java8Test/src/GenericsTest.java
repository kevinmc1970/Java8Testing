// generic interface lists that there are 2 types required
// this interface can then be implemented (see below)
// it enforces that any implementation has to return the opposite type i.e. T1 for T2 
interface DemoInterface<T1, T2> {
    T2 doSomeOperation(T1 t);
    T1 doReverseOperation(T2 t);
}

// class implmenting interface
// as it has used the types String and Integer then the inputs can only be String and Integer
class DemoClass implements DemoInterface<String, Integer> {
    @Override
    public Integer doSomeOperation(String t) {
        // some code
        return 1;
    }

    public String doReverseOpertaion(Integer t) {
        // some code
        return "one";
    }

    // if used this override method instead then it wont compile because returning wrong type
    public String doReverseOperation(Integer t) {
        return new Double(1.0);
    }
}

// class with a generic method
// n.b. the initial <E> just indicates that this is a generic method
// in this example there is only 1 type so can pass in list of Strings and String but not list of Strings and an Integer
class GenericMethodTest {
        public static <E> int countAllOccurences(E[] list, E item) {
            int count = 0;
            if (item == null) {
                for(E listItem : list) {
                    if (listItem == null) {
                        count++;
                    }
                }
            } else {
                for (E listItem : list) {
                    if (item.equals(listItem)) {
                        count++;
                    }
                }
            }
            return count;
        }
}

// this class shows once instantiated the type cannot be changed
class GenericDemoClass<T> {
    //T stands for type
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    // type-safety for this class
    public static void main(String args[]) {
        GenericDemoClass<String> instance = new GenericDemoClass<String>();
        instance.set("lokesh"); // correct as using String
        instance.set(1); // fails as not using String
    }
}

