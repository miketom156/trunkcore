package com.job5156.core.common.util;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class JndiUtil {

    private static final Logger logger = Logger.getLogger(JndiUtil.class);

    public static <T> T getJndiService(String jndiName, String ip){
        T jndiService = null;
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        props.setProperty(Context.PROVIDER_URL, "jnp://"+ ip +":1099");
        props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");

        try {
            logger.info("lookup jndi --> " + jndiName);
            InitialContext ic = new InitialContext(props);
            jndiService = (T) ic.lookup(jndiName);
        } catch (NamingException e) {
            logger.error(e);
        }

        return jndiService;
    }
}
