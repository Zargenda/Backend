package com.backend.Backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import static com.backend.Backend.model.SQLCalendar.GenCalendar;

@SpringBootTest
class BackendApplicationTests {

	@Test
	void testCreacionCalendario() throws ParseException {
		GenCalendar("2021-09-15","2022-02-06","2022-02-07","2022-07-15");
	}

}
