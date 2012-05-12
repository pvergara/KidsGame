package ecos.kidsgame.domainlogic.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TestingMockito {

	@Test
	public void IsThereAnybodyOutThere() {
		List<String> mockedList = mock(List.class);
		mockedList.add("one");
		mockedList.clear();

		verify(mockedList).add("one");
		verify(mockedList).clear();
	}

	@Test
	public void couldIStubSomeMethods() {
		LinkedList<String> mockedList = mock(LinkedList.class);
		when(mockedList.get(0)).thenReturn("first");

		// Obviously that works!!!...but... what can I do?.... I can't see some
		// test method without asserting!!!!
		assertEquals("first", mockedList.get(0));

	}

}
