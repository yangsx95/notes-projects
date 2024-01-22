package io.github.yangsx95.notes.spring.expression;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.ArrayList;
import java.util.List;

public class SpELApiTests {

    @Test
    public void helloWorld() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        Assert.assertEquals("Hello World", message);
    }

    @Test
    public void helloWorldEvaluationContext() {

        class Simple {
            public List<Boolean> booleanList = new ArrayList<Boolean>();
        }
        Simple simple = new Simple();
        simple.booleanList.add(true);

    }
}
