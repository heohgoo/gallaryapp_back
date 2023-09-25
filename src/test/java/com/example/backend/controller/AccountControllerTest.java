package com.example.backend.controller;

import com.example.backend.dto.MemberDto;
import com.example.backend.entity.Member;
import com.example.backend.repository.MemberRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;


import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//JUnit4
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private MemberRepository memberRepository;
    HttpServletResponse response = new MockHttpServletResponse();

    @Test
    public void 가입_성공() throws Exception {
        String email = "email@email.com";
        String password = "password";
        MemberDto memberdto = MemberDto.builder()
                .email(email)
                .password(password)
                .build();

        String url = "http://localhost:" + port + "/api/account/signup";

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, memberdto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Member> all = memberRepository.findAll();
        assertThat(all.get(0).getEmail()).isEqualTo(email);
        assertThat(all.get(0).getPassword()).isEqualTo(password);
    }

    @Test
    public void 가입_실패() throws Exception {
        String email = "email@email.com";
        String password = "password";
        MemberDto memberdto = MemberDto.builder()
                .email(email)
                .password(password)
                .build();

        String url = "http://localhost:" + port + "/api/account/signup";

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, memberdto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    public void 로그인_성공() throws Exception {
        String email = "email@email.com";
        String password = "password";
        MemberDto memberdto = MemberDto.builder()
                .email(email)
                .password(password)
                .build();

        String url = "http://localhost:" + port + "/api/account/login";

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, memberdto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 로그인_실패() throws Exception {
        String email = "email@email.com";
        String password = "password1";
        MemberDto memberdto = MemberDto.builder()
                .email(email)
                .password(password)
                .build();

        String url = "http://localhost:" + port + "/api/account/login";

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, memberdto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        memberRepository.deleteAll();
    }
}
