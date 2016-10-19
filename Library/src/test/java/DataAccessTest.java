
import com.app.LibraryApp;
import com.data_access.HibernateBookDao;
import com.data_access.config.DataAccessConfig;
import com.domain.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by Dragana.Nikolovska on 10/19/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataAccessConfig.class)
@Transactional
public class DataAccessTest {

    @Autowired
    private HibernateBookDao hibernateBookDao;

    @Test
    public void testInsertBook(){
        Book book = new Book();
        book.setIsbn("115");
        book.setTitle("DraganasBook");
        hibernateBookDao.insert(book);
        Book persistedBook = hibernateBookDao.findByIsbn("115");
        Assert.assertNotNull(persistedBook);
    }
}
