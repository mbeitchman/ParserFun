package parsers;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SQLParserUnitTests {
    @Test
    public void validEqualToExpression() {
        List<String> values = Lists.newArrayList("one", "two");
        SQLParser parser = new SQLParser();
        List<String> results = parser.parse(values, "value=one");
        assertEquals(1, results.size());
        assertEquals("one", results.get(0));
    }
}
