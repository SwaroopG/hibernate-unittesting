package com.arris.hibernate.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;

import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arris.hibernate.entity.Genre;

/**
 * Tests the {@link GenreDao}.
 * @author Swaroop
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
public class GenreDaoTest {

    private static final String FLAT_XML_DATASET = "FlatXmlDataSet.xml";

    @Autowired
    BasicDataSource bds;

    @Autowired
    private GenreDao genreDao;

    /**
     * Setup.
     * @throws Exception s
     */
    @Before
    public void setUp() throws Exception {
        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
    }

    /**
     * Tests the getAll method of the {@link GenreDao}.
     */
    @Test
    public void testGetAll() {
        Collection<Genre> genres = genreDao.getAll();
        for (Genre genre : genres) {
            System.out.println("Genre Name: " + genre.getName() + " Id: " + genre.getId() + " Description: " + genre.getDescription());
        }

        assertFalse(genres.isEmpty());
    }

    /**
     * Tests the getById method of the {@link GenreDao}.
     */
    @Test
    public void testGetById() {
        Genre genre = genreDao.getById(Long.valueOf(1));
        System.out.println("Genre Name: " + genre.getName() + " Id: " + genre.getId() + " Description: " + genre.getDescription());
        assertTrue(genre.getActiveInd().booleanValue());
        assertEquals("action", genre.getName());
        assertEquals("Action genre.", genre.getDescription());
    }

    /**
     * Tests the update method of the {@link GenreDao}.
     */
    @Test
    @Ignore
    public void testUpdate() {
        Genre genre = genreDao.getById(Long.valueOf(1));
        System.out.println("Genre Name: " + genre.getName() + " Id: " + 
                genre.getId() + " Description: " + genre.getDescription());
        assertTrue(genre.getActiveInd().booleanValue());
        assertEquals("action", genre.getName());
        assertEquals("Action genre.", genre.getDescription());

        genre.setName("Changed action name");
        genre.setDescription("Changed the action description");
        genre.setActiveInd(Boolean.valueOf(false));
        genreDao.update(genre);

        Genre expectedGenre = genreDao.getById(Long.valueOf(1));
        System.out.println("Genre Name: " + expectedGenre.getName() + " Id: " + 
                expectedGenre.getId() + " Description: " + expectedGenre.getDescription());
        assertFalse(expectedGenre.getActiveInd().booleanValue());
        assertEquals("Changed action name", expectedGenre.getName());
        assertEquals("Changed the action description", expectedGenre.getDescription());
    }
    
    /**
     * Tests the delete method of the {@link GenreDao}.
     */
    @Test
    @Ignore
    public void testDelete() {
        assertEquals(3, genreDao.getAll().size());
        genreDao.delete(Long.valueOf(1));
        assertNull(genreDao.getById(Long.valueOf(1)));
    }

    @SuppressWarnings("deprecation")
    private IDataSet getDataSet() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FLAT_XML_DATASET);
        try {
            IDataSet dataset = new FlatXmlDataSet(inputStream);
            return dataset;
        } finally {
            inputStream.close();
        }
    }

    private IDatabaseConnection getConnection() throws Exception {
        Connection jdbcConnection = bds.getConnection();
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
        return connection;
    }
}
