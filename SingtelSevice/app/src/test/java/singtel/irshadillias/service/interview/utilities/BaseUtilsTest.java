package singtel.irshadillias.service.interview.utilities;

import org.junit.Assert;
import org.junit.Test;

public class BaseUtilsTest {

    @Test
    public void getYear() {
        Assert.assertEquals("2018",BaseUtils.getYear("2018-05-26"));

    }
}