package com.job5156.core;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ibatis.common.resources.Resources;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * <p></p>
 * Date:2015/5/21 9:34
 *
 * @author pzm
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
//@Ignore
public class TestBase {
    protected Integer DEFAULT_PID = 4220268;
    @Resource
    protected SessionFactory sessionFactory;
    @Resource
    protected SessionFactory sessionFactoryAction;
    @Resource
    protected SessionFactory sessionFactoryIrc;
    @Resource
    protected SessionFactory sessionFactorySystemQuery;
    @Resource
    DataSource dataSource;

    private SessionFactory[] sessionFactoryArr;

    @Before
    public void setUp() throws Exception {
        initTestData();
        sessionFactoryArr = new SessionFactory[]{sessionFactory,sessionFactoryAction,sessionFactoryIrc,sessionFactorySystemQuery};
        openSession();
    }

    @After
    public void tearDown() throws Exception {
        closeSession();
    }

    protected void openSession(){
        for(SessionFactory sf:sessionFactoryArr){
            Object o = TransactionSynchronizationManager.getResource(sf);
            if(o==null){
                Session session = sf.openSession();
                SessionHolder sessionHolder = new SessionHolder(session);
                TransactionSynchronizationManager.bindResource(sf, sessionHolder);
            }
        }
    }

    protected void closeSession(){
        for(SessionFactory sf:sessionFactoryArr){
            Object o = TransactionSynchronizationManager.getResource(sf);
            if(o instanceof SessionHolder){
                SessionFactoryUtils.closeSession(((SessionHolder) o).getSession());
                TransactionSynchronizationManager.unbindResource(sf);
            }
        }
    }

    protected void clearSessionCache(){
        for(SessionFactory sf:sessionFactoryArr){
            sf.getCurrentSession().clear();
        }
    }

    public void initTestData() throws SQLException, IOException {
        Connection conn = dataSource.getConnection();
        try {
            ScriptRunner runner = new ScriptRunner(conn, false, false);
            runner.setErrorLogWriter(new PrintWriter(System.out));
//            runner.setErrorLogWriter(null);
            runner.setLogWriter(null);
            List<String> sqlFilesToRun = new ArrayList();
            sqlFilesToRun.add("testdata/5156base.sql");
            sqlFilesToRun.add("testdata/5156base_initdata.sql");
            sqlFilesToRun.add("testdata/5156query.sql");
            sqlFilesToRun.add("testdata/5156query_initdata.sql");
            sqlFilesToRun.add("testdata/5156action.sql");
            sqlFilesToRun.add("testdata/5156action_initdata.sql");
            sqlFilesToRun.add("testdata/5156countdb.sql");
            sqlFilesToRun.add("testdata/5156countdb_initdata.sql");
            for(String sqlFile : sqlFilesToRun){
                InputStream inputStream = Resources.getResourceAsStream(sqlFile);
                try{
                    runner.runScript(new InputStreamReader(inputStream,"utf-8"));
                } finally {
                    inputStream.close();
                }
            }
        } finally {
            conn.close();
        }
    }

    @Test
    public void testSetup(){

    }
}
