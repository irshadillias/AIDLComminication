package singtel.irshadillias.language.interview.utilities;

import org.junit.Assert;
import org.junit.Test;

public class AppUtilityTest {

    @Test
    public void getFormattedDate() {
        Assert.assertEquals("May 26th, 2018",AppUtility.getFormattedDate("2018-05-26"));
    }
}