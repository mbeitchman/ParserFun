package parsers;

import com.google.common.collect.Lists;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;

import java.util.List;

public class SQLParser {

        public List<String> parse(List<String> values, String expression) {
            final List<String> results = Lists.newArrayList();
            try {
                Expression parseExpression = CCJSqlParserUtil.parseCondExpression(expression);
                ExpressionDeParser deParser = new ExpressionDeParser() {

                    @Override
                    public void visit(StringValue stringValue) {
                        super.visit(stringValue);
                    }

                    @Override
                    public void visit(LongValue longValue) {
                        super.visit(longValue);
                        System.out.println(longValue.getValue());
                    }

                    @Override
                    public void visit(GreaterThan greaterThan) {
                        super.visit(greaterThan);
                        System.out.println(greaterThan.getStringExpression());
                    }

                    @Override
                    public void visit(GreaterThanEquals greaterThanEquals) {
                        super.visit(greaterThanEquals);
                        System.out.println(greaterThanEquals.getStringExpression());
                    }

                    @Override
                    public void visit(final EqualsTo equalsTo) {
                        super.visit(equalsTo);
                        String predicate = equalsTo.getRightExpression().toString().replace("'", "");
                        values.parallelStream().filter(v -> (v.equals(predicate))).forEach(r -> results.add(r));
                    }

                    @Override
                    public void visit(MinorThanEquals minorThanEquals) {
                        super.visit(minorThanEquals);
                        System.out.println(minorThanEquals.getStringExpression());
                    }

                    @Override
                    public void visit(MinorThan minorThan) {
                        super.visit(minorThan);
                        System.out.println(minorThan.getStringExpression());
                    }

                    @Override
                    public void visit(LikeExpression likeExpression) {
                        super.visit(likeExpression);
                        System.out.println(likeExpression.getStringExpression());
                    }
                };

                StringBuilder b = new StringBuilder();
                deParser.setBuffer(b);
                parseExpression.accept(deParser);

            } catch (JSQLParserException ex) {
                System.out.print(ex.getMessage());
            }

            return results;
        }
}
