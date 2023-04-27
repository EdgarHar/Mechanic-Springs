import java.util.Stack;

public class SpringArray {

    public static Spring equivalentSpring(String springExpr) {
        final String sequence = springExpr.substring(1, springExpr.length() - 1);
        boolean isSpringSequence = springExpr.charAt(0) == '{'
                && springExpr.charAt(springExpr.length() - 1) == '}';

        if (sequence.isEmpty()) {
            return new Spring(0);
        }
        final Stack<Spring> stack = new Stack<>();

        int i = 0;
        while (i < sequence.length()) {
            char ch = sequence.charAt(i);
            if (ch == '{' || ch == '[') {
                stack.push(new Spring());
            } else if (ch == '}' || ch == ']') {
                mergeSpring(stack, isSpringSequence);
            }
            i++;
        }

        return stack.pop();
    }

    public static Spring equivalentSpring(String springExpr, Spring[] springs) {

        final boolean isSpringSequence = springExpr.charAt(0) == '{'
                && springExpr.charAt(springExpr.length() - 1) == '}';
        final String sequence = springExpr.substring(1, springExpr.length() - 1);

        if (sequence.isEmpty()) {
            return new Spring(0);
        }
        final Stack<Spring> stack = new Stack<>();

        int i = 0;
        while (i < sequence.length()) {
            char ch = sequence.charAt(i);
            if (ch == '{' || ch == '[') {
                stack.push(springs[0]);
            } else if (ch == '}' || ch == ']') {
                mergeSpring(stack, isSpringSequence);
            }
            i++;
        }

        return stack.pop();
    }

    private static void mergeSpring(Stack<Spring> stack, boolean isSpringSequence) {
        final Spring s1 = stack.pop();
        if (!stack.isEmpty()) {
            final Spring s2 = stack.pop();
            stack.push(isSpringSequence ? s2.inSeries(s1) : s2.inParallel(s1));
        } else {
            stack.push(s1);
        }
    }
}
