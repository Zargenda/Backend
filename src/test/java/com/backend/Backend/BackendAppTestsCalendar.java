package com.backend.Backend;

import com.backend.Backend.Service.CalendarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class BackendAppTestsCalendar {

	@Autowired
	CalendarService calendarService;

	@Test
	void testCreacionCalendario() throws ParseException {
		calendarService.dropCalendario();
	}

}
