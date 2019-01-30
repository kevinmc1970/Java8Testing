public class FunctionalProgrammingTest {
    public static void main(String args[]) {
    // functional programming - is removing side-affects and complexity - 
    // the processNext() method, below, has no inputs or outputs 
    // BUT it does depend on the state of the InboxQueue so that is considered an input 
    // AND whatever process (message) does is an 'output'
    // These are hidden inputs/outputs known as side-effects.. 
    // when we call this function, what does it need that isn't in the argument list, 
    // and what does it do that isn't part of the return value?"
    // functions that do not have side-effects are 'pure functions' in 
    // so functional programming is just using pure functions 
    // "just describes a relationship between inputs and outputs. 
    // or put another way: Let's not hide what a piece of code needs, nor what results it will yield. If a piece of 
    // code needs something to run correctly, let it say so. If it does something useful, let it declare it as an 
    // output. When we do this, our code will be clearer. Complexity will come to the surface, where we can break it 
    // down and deal with it." 

    // Mocking side-affects (like the process (message) below) as opposed to side-causes is a code smell and shows 
    //that the code is impure 

    // If there were a book of side-effects, the two easiest targets to spot would be functions that take no 
    // arguments, and functions that return no value. 
    }
    
    //no inputs or outputs probably means side-effects 
    public static void processNext() {
        // side affect as depends on Inboxqueue and that is not passed in as parameter but still an 'input' 
        Message message = InboxQueue. popMessage(); 
        // side affect as depends on whatever process method does and that is an 'output'
        if (message != null) { 
            process (message);
        }
    }

    // the new Date() is a side-effect (as it's an input that is not in the parameters) - hard to test because 
    // cant set the date
    public Program getCurrentProgram(TVGuide guide, int channel) {
        Schedule schedule = guide.getSchedule(channel);
        Program current = schedule.programAt(new Date());
        return current;
    }

    // fix the side affect by moving the date into the parameter list
    // can not pass in different date tests
    // this also highlights any unnecessary complexity in the function
    // this is now a pure function
    public Program getCurrentProgram(TVGuide guide, int channel, Date date) {
        Schedule schedule = guide.getSchedule(channel);
        Program current = schedule.programAt(date);
        return current;
    }
}