package com.yangsx95.notes.spring.expression;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class EvaluationTests {

    @Test
    public void testParseExpression() {
        // ExpressionParser接口负责解析表达式字符串
        ExpressionParser parser = new SpelExpressionParser();
        // 使用引号包裹的为字符串字面量，将字符串表达式解析为Expression对象
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        Assert.assertEquals("Hello World", message);
    }

    @Test
    public void testParseExpressionWithContext() {
        ExpressionParser parser = new SpelExpressionParser();
        ParserContext context = new TemplateParserContext();
        context.getExpressionPrefix();
        Expression exp = parser.parseExpression("'Hello World'", context);
    }
}
