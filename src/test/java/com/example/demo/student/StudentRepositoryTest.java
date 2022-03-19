package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void itShouldCheckIfStudentExistsEmail() {
        //given
        String email = "jamila@gmail.com";
        Student student = new Student(
                "Jamila", email,
                Gender.FEMALE
        );

        underTest.save(student);
        //when
        boolean exists = underTest.selectExistsEmail(email);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDpesNotExists() {
        //given
        String email = "jamila@gmail.com";

        //when
        boolean expected = underTest.selectExistsEmail(email);

        // then
        assertThat(expected).isFalse();
    }
}