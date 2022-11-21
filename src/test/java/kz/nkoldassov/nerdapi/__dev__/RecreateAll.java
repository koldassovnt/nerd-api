package kz.nkoldassov.nerdapi.__dev__;

import kz.nkoldassov.nerdapi.beans.for_recreate.RecreateWorker;
import kz.nkoldassov.nerdapi.logging.LOG;
import kz.nkoldassov.nerdapi.util.ApplicationContextSource;

public class RecreateAll {

    public static void main(String[] args) throws Exception {
        LOG.resetThread();

        RecreateWorker.recreatePostgresDatabase("nerd", "nerd");

        ApplicationContextSource.withContext(ctx -> ctx.getBean(RecreateWorker.class).recreate());
    }
}
