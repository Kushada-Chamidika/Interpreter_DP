package designpatterns02day07interpreter;

/**
 *
 * @author 1999k
 */
public class Test {

    public static void main(String[] args) {

        String text = "10 + 5 - 3 + 8";

        String data[] = text.split(" ");

        Expression expression = new Number("0");

        for (int i = 0; i < data.length; i++) {

            if (data[i].equals("+")) {

                Expression next = new Number(data[i + 1]);
                expression = new PlusOperator(expression, next);
                i++;

            } else if (data[i].equals("-")) {

                Expression next = new Number(data[i + 1]);
                expression = new MinusOperator(expression, next);
                i++;

            } else {

                expression = new Number(data[i]);

            }

            System.out.println(expression.interpret());

        }

        System.out.println("Result : " + expression.interpret());

    }

}

interface Expression {

    public abstract int interpret();

}

class Number implements Expression {

    String text;

    public Number(String text) {
        this.text = text;
    }

    @Override
    public int interpret() {
        return Integer.parseInt(text);
    }

}

class PlusOperator implements Expression {

    Expression n1;
    Expression n2;

    public PlusOperator(Expression n1, Expression n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    @Override
    public int interpret() {
        return n1.interpret() + n2.interpret();
    }

}

class MinusOperator implements Expression {

    Expression n1;
    Expression n2;

    public MinusOperator(Expression n1, Expression n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    @Override
    public int interpret() {
        return n1.interpret() - n2.interpret();
    }

}
