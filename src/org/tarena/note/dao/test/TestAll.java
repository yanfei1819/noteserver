package org.tarena.note.dao.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
//利用suite套件批量执行多个Test类
@RunWith(Suite.class)
@SuiteClasses({MyUtilJunitTest.class,UserMapperDaoJunitTest.class})
public class TestAll {

}
