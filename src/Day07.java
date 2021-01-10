import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// Used Java for this challenge because Kotlin doesn't support bit shifts on short vars
public class Day07 {

    /*
    Holds a wire's name, value, the "parent" wires that determine its value, and the operation performed on
    the parent values to achieve the new value. A wire can have 0 (constant value), 1 (NOT), or 2 (other ops) parents.
     */
    private static class Wire {
        public String name;
        public ArrayList<Wire> parents = new ArrayList<>();
        public Character value = null;
        public String op;

        public Wire(String name) {
            this.name = name;
        }
    }

    // Recursively evaluate the value of a wire given its network
    private static char getWireValue(Wire root){
        if(root.value != null){
            return root.value;
        }
        // Evaluate
        Wire p0 = root.parents.get(0);
        Wire p1 = (root.parents.size() == 2) ? root.parents.get(1) : null;
        if(root.op == null) {
            root.value = getWireValue(p0);
        }else {
            switch (root.op) {
                case "NOT": root.value = (char) ~getWireValue(p0); break;
                case "AND": root.value = (char) (getWireValue(p0) & getWireValue(p1)); break;
                case "OR": root.value = (char) (getWireValue(p0) | getWireValue(p1)); break;
                case "LSHIFT": root.value = (char) (getWireValue(p0) << getWireValue(p1)); break;
                case "RSHIFT": root.value = (char) (getWireValue(p0) >>> getWireValue(p1));
            }
        }
        return root.value;
    }

    public static void main(String[] args){

        String[][] src = null;
        try{
            src = Files.lines(Paths.get("inputs/day07.txt"))
                    .map(s -> s.split(" "))
                    .toArray(String[][]::new);
        }catch(IOException e){
            e.printStackTrace();
        }

        /* PART 1 */
        // Build the framework of wires
        HashMap<String, Wire> wires = new HashMap<>();
        for(String[] line : src){
            // Fetch the product wire of this expression. Either get it from the set of existing wires
            // or make a new one with this name
            String name = line[line.length-1];
            Wire w = wires.get(name);
            if(w == null){
                w = new Wire(name);
                wires.put(name, w);
            }

            for(String token : line){
                // Operation
                if(token.equals("NOT") || token.equals("AND") || token.equals("OR")
                        || token.equals("LSHIFT") || token.equals("RSHIFT")) {
                    w.op = token;

                    // We've reached the end of the expression
                }else if(token.equals("->")) {
                    break;

                }else{
                    // Store a constant inside a wrapper wire
                    if(Character.isDigit(token.charAt(0))){
                        Wire input = new Wire("input");
                        input.value = (char)Integer.parseInt(token);
                        w.parents.add(input);

                    }else{
                        if(wires.get(token) == null){
                            Wire newWire = new Wire(token);
                            w.parents.add(newWire);
                            wires.put(token, newWire);
                        }else{
                            w.parents.add(wires.get(token));
                        }
                    }
                }
            }
        }

        // Evaluate the value of wire 'a'
        char a = getWireValue(wires.get("a"));
        System.out.println((int)a);

    }

}
