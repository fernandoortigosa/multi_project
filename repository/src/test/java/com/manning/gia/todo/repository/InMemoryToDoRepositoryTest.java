package com.manning.gia.todo.repository;

import com.manning.gia.todo.model.ToDoItem;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class InMemoryToDoRepositoryTest {
    private ToDoRepository inMemoryToDoRepository;

    @Before
    public void setUp() {
        inMemoryToDoRepository = new InMemoryToDoRepository();
    }

    @Test
    public void testInsertToDoItem() {

        String sItems = System.getProperty("items");
        int nItems = Integer.parseInt(sItems == null ? "1" : sItems);

        int sizeBefore = inMemoryToDoRepository.countAll();

        for(int i = 0; i < nItems; i++) {
            ToDoItem newToDoItem = new ToDoItem();
            newToDoItem.setName("Write unit tests");
            Long newId = inMemoryToDoRepository.insert(newToDoItem);
            assertNotNull(newId);

            ToDoItem persistedToDoItem = inMemoryToDoRepository.findById(newId);
            assertNotNull(persistedToDoItem);
            assertEquals(newToDoItem, persistedToDoItem);
        }

        int sizeAfter = inMemoryToDoRepository.countAll();

        assertEquals(sizeBefore + nItems, sizeAfter);


    }

    @Test
    public void testInsertAddsNewItem() {

        int sizeBefore = inMemoryToDoRepository.countAll();

        ToDoItem newToDoItem = new ToDoItem();
        newToDoItem.setName("Write unit tests");
        Long newId = inMemoryToDoRepository.insert(newToDoItem);
        assertNotNull(newId);

        int sizeAfter = inMemoryToDoRepository.countAll();

        assertEquals(sizeBefore + 1, sizeAfter);

    }


}

